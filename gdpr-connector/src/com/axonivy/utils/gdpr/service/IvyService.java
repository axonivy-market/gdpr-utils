package com.axonivy.utils.gdpr.service;

import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang.StringUtils;

import com.axonivy.utils.gdpr.constant.Constants;
import com.axonivy.utils.gdpr.enums.CustomField;
import com.axonivy.utils.gdpr.enums.Roles;
import com.axonivy.utils.gdpr.utils.Logger;

import ch.ivyteam.ivy.application.IApplication;
import ch.ivyteam.ivy.cm.ContentObject;
import ch.ivyteam.ivy.cm.ContentObjectType;
import ch.ivyteam.ivy.cm.exec.ContentManagement;
import ch.ivyteam.ivy.environment.Ivy;
import ch.ivyteam.ivy.persistence.query.IPagedResult;
import ch.ivyteam.ivy.process.model.value.SignalCode;
import ch.ivyteam.ivy.request.IHttpResponse;
import ch.ivyteam.ivy.security.IRole;
import ch.ivyteam.ivy.security.IUser;
import ch.ivyteam.ivy.security.exec.Sudo;
import ch.ivyteam.ivy.security.query.UserQuery;
import ch.ivyteam.ivy.workflow.ICase;
import ch.ivyteam.ivy.workflow.ITask;
import ch.ivyteam.ivy.workflow.IWorkflowContext;
import ch.ivyteam.ivy.workflow.IWorkflowSession;
import ch.ivyteam.ivy.workflow.TaskState;
import ch.ivyteam.ivy.workflow.query.CaseQuery;
import ch.ivyteam.ivy.workflow.query.TaskQuery;

public class IvyService {
	private static final Logger LOG = Logger.getLogger(IvyService.class);
	private static final String SESSION_KEY_BASE = IvyService.class.getCanonicalName() + ".";
	private static final String SESSION_OLD_CONTENT_LOCALE = SESSION_KEY_BASE + "oldContentLocale";
	private static final String SESSION_OLD_FORMATTING_LOCALE = SESSION_KEY_BASE + "oldFormattingLocale";
	private static final String DESIGNER_APPLICATION_NAME = "designer";
	private static final Pattern SINGLE_QUOTE_REGEX = Pattern.compile("(?<!'|\\})'(?!'|\\{|[^{]*})");
	private static final String CMS_MACROS_PATTERN = "<%=ivy.cms.co(\"{0}\")%>";
	private static final String CMS_MACROS_PATTERN_WITH_PARAMS = "<%=ivy.cms.co(\"{0}\",[{1}])%>";
	private static final Pattern EXTRACT_CMS_DATA_PATTERN = Pattern.compile("<%=ivy.cms.co\\(\"(.+?)\",\\[(.+?)\\]\\)%>");
	private static final Pattern EXTRACT_CMS_DATA_NO_PARAM_PATTERN = Pattern.compile("<%=ivy.cms.co\\(\"(.+?)\"\\)%>");

	private IvyService() {
	}

	/**
	 * Set the application's default {@link Locale}.
	 *
	 * The locale will be set to {@link Constants#DEFAULT_LOCALE} into the session
	 * and will be valid for this session until {@link #restoreSessionLocale()} is
	 * called.
	 */
	public static void setSessionDefaultLocale() {
		setSessionAttribute(SESSION_OLD_CONTENT_LOCALE, Ivy.session().getContentLocale());
		setSessionAttribute(SESSION_OLD_FORMATTING_LOCALE, Ivy.session().getFormattingLocale());

		Ivy.session().setContentLocale(Constants.DEFAULT_LOCALE);
		Ivy.session().setFormattingLocale(Constants.DEFAULT_LOCALE);
	}

	/**
	 * Restore the original {@link Locale} of the session.
	 */
	public static void restoreSessionLocale() {
		final Locale contentLocale = (Locale) getSessionAttribute(SESSION_OLD_CONTENT_LOCALE);
		final Locale formattingLocale = (Locale) getSessionAttribute(SESSION_OLD_FORMATTING_LOCALE);
		if (contentLocale != null) {
			Ivy.session().setContentLocale(Constants.DEFAULT_LOCALE);
		}
		if (formattingLocale != null) {
			Ivy.session().setFormattingLocale(Constants.DEFAULT_LOCALE);
		}
	}

	/**
	 * Just a shortcut to run code as system.
	 *
	 * @param <T>
	 * @param callable
	 * @return
	 * @throws Exception
	 */
	public static <T> T executeAsSystem(Callable<T> callable) throws Exception {
		return Sudo.call(callable);
	}

	/**
	 * Get the Id of the current application.
	 *
	 * @return
	 */
	public static long getApplicationId() {
		return IApplication.current().getId();
	}

	/**
	 * Get the top level role.
	 *
	 * @return
	 */
	public static IRole getApplicationTopLevelRole() {
		return Ivy.wf().getSecurityContext().roles().topLevel();
	}

	/**
	 * Find an {@link IRole}.
	 *
	 * @param role
	 * @return
	 */
	public static IRole findRole(Roles role) {
		return findRole(role.getIvyRoleName());
	}

	/**
	 * Find an ivy role.
	 *
	 * @param rolename
	 * @return
	 */
	public static IRole findRole(String rolename) {
		return Ivy.wf().getSecurityContext().roles().find(rolename);
	}

	/**
	 * Find an ivy user.
	 *
	 * @param username
	 * @return
	 */
	public static IUser findUser(String username) {
		return Ivy.wf().getSecurityContext().users().find(username);
	}

	/**
	 * Find an ivy user by id.
	 *
	 * @param securityMemberId
	 * @return
	 */
	public static IUser findUserById(String securityMemberId) {
		return Ivy.security().users().findById(securityMemberId);
	}

	/**
	 * Get a non-blank Ivy global variable as a String.
	 *
	 * If the variable is not set or blank, then return default value. If the
	 * variable is set, then it is trimmed before returning.
	 *
	 * @param name
	 * @param defValue
	 * @return
	 */
	public static String getNonBlankGlobalVariable(String name, String defValue) {
		String value = Ivy.var().get(name);
		if (StringUtils.isBlank(value)) {
			value = defValue;
		} else {
			value = value.trim();
		}
		return value;
	}

	/**
	 * Does the current user have global access?
	 *
	 * Global access will be granted to the system user and to users having the
	 * Administrator role.
	 *
	 * @return
	 */
	public static boolean hasGlobalAccess() {
		return hasRole(Roles.ADMINISTRATOR) || isSystemUser();
	}

	/**
	 * Get the current session user.
	 *
	 * @return
	 */
	public static IUser getSessionUser() {
		IUser user = null;
		final IWorkflowSession session = Ivy.session();
		if (session != null) {
			user = session.getSessionUser();
		}
		if (user == null) {
			LOG.error("Could not determine current user for session {0}", session);
		}

		return user;
	}

	/**
	 * Get the current session username.
	 *
	 * @return
	 */
	public static String getSessionUserName() {
		String userName = null;
		final IWorkflowSession session = Ivy.session();
		if (session != null) {
			userName = session.getSessionUserName();
		}
		if (userName == null) {
			LOG.error("Could not determine current user for session {0}", session);
		}

		return userName;
	}

	/**
	 * Get the system user.
	 *
	 * @return
	 */
	public static IUser getSystemUser() {
		IUser user = null;
		final IWorkflowSession session = Ivy.session();
		if (session != null) {
			try {
				user = executeAsSystem(() -> session.getSecurityContext().users().system());
			} catch (final Exception e) {
				LOG.error("Could not determine the system user.", e);
			}
		}
		return user;
	}

	/**
	 * Is the current session user the system user?
	 *
	 * @return
	 */
	public static boolean isSystemUser() {
		return isSystemUser(getSessionUser());
	}

	/**
	 * Is the given user the system user?
	 *
	 * @param user
	 * @return
	 */
	public static boolean isSystemUser(IUser user) {
		boolean result = false;
		if (user != null) {
			final IUser systemUser = getSystemUser();

			if (systemUser != null) {
				result = systemUser.getSecurityMemberId() == user.getSecurityMemberId();
			}
		}
		return result;
	}

	/**
	 * Are we running in Designer?
	 *
	 * Note: this function might be called by Logger so do not log here... :-)
	 *
	 * @return
	 * @throws Exception
	 */
	public static boolean isDesigner() throws Exception {
		boolean designer = false;
		final IWorkflowContext wf = Ivy.wf();

		if (wf != null) {
			final IApplication application = IApplication.current();

			if (application != null) {
				designer = DESIGNER_APPLICATION_NAME.equalsIgnoreCase(application.getName());
			}
		}
		return designer;
	}

	/**
	 * Check if the session user has any of the given Ivy roles.
	 *
	 * @param roleNames
	 * @return true if user has a role
	 */
	public static boolean hasRole(String... roleNames) {
		Boolean hasRole = false;
		if (isSystemUser()) {
			hasRole = true;
		} else {
			for (final String roleName : roleNames) {
				final IRole role = findRole(roleName);

				if (role != null) {
					if (Ivy.session().hasRole(role, true)) {
						hasRole = true;
						break;
					}
				}
			}
		}
		return hasRole;
	}

	/**
	 * Check if the session user has a concrete ivy role.
	 *
	 * Note, that the System user and the Developer user will have all roles.
	 *
	 * @param roles
	 * @return
	 */
	public static boolean hasRole(Roles... roles) {
		return hasRole(Stream.of(roles).map(r -> r.getIvyRoleName()).toArray(String[]::new));
	}

	/**
	 * Get list of user for a role.
	 *
	 * @param role
	 * @return
	 */
	public static IPagedResult<IUser> getUsersOfRole(Roles role) {
		return getUsersOfRole(role.getIvyRoleName());
	}

	/**
	 * Get list of user for a role.
	 *
	 * @param roleName
	 * @return List<IUser>
	 */
	public static IPagedResult<IUser> getUsersOfRole(String roleName) {
		IPagedResult<IUser> users = null;
		final IRole role = findRole(roleName);

		if (role != null) {
			users = role.users().allPaged();
		}

		return users;
	}

	/**
	 * Get an attribute from the current session.
	 *
	 * @param key
	 * @return
	 */
	public static Object getSessionAttribute(String key) {
		Object attribute = null;
		final IWorkflowSession session = Ivy.session();
		if (session != null) {
			attribute = session.getAttribute(key);
		} else {
			LOG.error("Could not determine current session.");
		}
		return attribute;
	}

	/**
	 * Set an attribute to the current session.
	 *
	 * @param key
	 * @param attribute
	 * @return
	 */
	public static Object setSessionAttribute(String key, Object attribute) {
		Object old = null;
		final IWorkflowSession session = Ivy.session();
		if (session != null) {
			old = session.setAttribute(key, attribute);
		} else {
			LOG.error("Could not determine current session.");
		}
		return old;
	}

	/**
	 * Send a signal.
	 *
	 * @param signalCode
	 */
	public static void sendSignal(String signalCode) {
		Ivy.wf().signals().send(new SignalCode(signalCode));
	}

	/**
	 * Execute a case query.
	 *
	 * @param caseQuery
	 * @return
	 */
	public static List<ICase> executeCaseQuery(CaseQuery caseQuery) {
		return Ivy.wf().getCaseQueryExecutor().getResults(caseQuery);
	}

	/**
	 * Execute a case query, force a single result.
	 *
	 * @param caseQuery
	 * @return
	 */
	public static ICase executeCaseQueryForceSingleResult(CaseQuery caseQuery) {
		final List<ICase> icases = executeCaseQuery(caseQuery);
		ICase result = null;
		if (icases != null && icases.size() > 0) {
			result = icases.get(0);
			if (icases.size() > 1) {
				try {
					// throw to get stack trace
					throw new RuntimeException("Case query returned " + icases.size() + " results: "
							+ icases.stream().map(c -> "" + c.getId()).collect(Collectors.joining(", ")));
				} catch (final RuntimeException e) {
					LOG.warn("CaseQuery did not return single result.", e);
				}
			}
		}
		return result;
	}

	/**
	 * Execute a task query.
	 *
	 * @param taskQuery
	 * @return
	 */
	public static List<ITask> executeTaskQuery(TaskQuery taskQuery) {
		return Ivy.wf().getTaskQueryExecutor().getResults(taskQuery);
	}

	/**
	 * Execute a task query, force a single result.
	 *
	 * @param taskQuery
	 * @return
	 */
	public static ITask executeTaskQueryForceSingleResult(TaskQuery taskQuery) {
		final List<ITask> itasks = executeTaskQuery(taskQuery);
		ITask result = null;
		if (itasks != null && itasks.size() > 0) {
			result = itasks.get(0);
			if (itasks.size() > 1) {
				try {
					// throw to get stack trace
					throw new RuntimeException("Task query returned " + itasks.size() + " results: "
							+ itasks.stream().map(c -> "" + c.getId()).collect(Collectors.joining(", ")));
				} catch (final RuntimeException e) {
					LOG.warn("TaskQuery did not return single result.", e);
				}
			}
		}
		return result;
	}

	/**
	 * Reset a task if it is in an active state.
	 *
	 * Active States are:
	 * <ul>
	 * <li>{@link TaskState#RESUMED}</li>
	 * <li>{@link TaskState#CREATED}</li>
	 * <li>{@link TaskState#PARKED}</li>
	 * <li>{@link TaskState#READY_FOR_JOIN}</li>
	 * <li>{@link TaskState#FAILED}</li>
	 * </ul>
	 *
	 */
	public static void resetTaskIfActive() {
		final List<TaskState> states = Arrays.asList(TaskState.RESUMED, TaskState.CREATED, TaskState.PARKED,
				TaskState.READY_FOR_JOIN, TaskState.FAILED);
		final ITask task = Ivy.wfTask();
		if (task != null && states.contains(Ivy.wfTask().getState())) {
			task.reset();
		} else {
			LOG.warn("Could not reset task {0}", task);
		}
	}

	/**
	 * Park a task if it is in an active state.
	 *
	 * Active States are:
	 * <ul>
	 * <li>{@link TaskState#RESUMED}</li>
	 * <li>{@link TaskState#CREATED}</li>
	 * <li>{@link TaskState#PARKED}</li>
	 * <li>{@link TaskState#READY_FOR_JOIN}</li>
	 * <li>{@link TaskState#FAILED}</li>
	 * </ul>
	 *
	 */
	public static void parkTaskIfActive() {
		final List<TaskState> states = Arrays.asList(TaskState.RESUMED, TaskState.CREATED, TaskState.PARKED,
				TaskState.READY_FOR_JOIN, TaskState.FAILED);
		final ITask task = Ivy.wfTask();
		if (task != null && states.contains(Ivy.wfTask().getState())) {
			Ivy.session().parkTask(task);
		} else {
			LOG.warn("Could not reset task {0}", task);
		}
	}

	// TODO: replace with correct methods for Axon Ivy 9.4 Portal
	// /**
	// * Navigate to portal home.
	// */
	// public static void navigateToPortalHome() {
	// if(getEmbedInFrame(Ivy.wfCase())) {
	// PortalNavigatorInFrameAPI.navigateToPortalHome();
	// } else {
	// PortalNavigatorAPI.navigateToPortalHome();
	// }
	// }
	//
	// /**
	// * Reset the current task (if possible) and navigate to the portal home.
	// */
	// public static void resetAndNavigateToPortalHome() {
	// resetTaskIfActive();
	// navigateToPortalHome();
	// }
	//
	// /**
	// * Park the current task (if possible) and navigate to the portal home.
	// */
	// public static void parkAndNavigateToPortalHome() {
	// resetTaskIfActive();
	// navigateToPortalHome();
	// }

	/**
	 * Set embed in frame flag to true.
	 *
	 * @param wfCase
	 */
	public static void setEmbedInFrame(ICase wfCase) {
		wfCase.customFields().stringField(CustomField.EMBED_IN_FRAME.getFieldName()).set(Boolean.TRUE.toString());
	}

	/**
	 * Get embedInFrame custom field from case.
	 *
	 * @param wfCase
	 */
	public static Boolean getEmbedInFrame(ICase wfCase) {
		return Boolean.valueOf(
				wfCase.customFields().stringField(CustomField.EMBED_IN_FRAME.getFieldName()).get().orElse("false"));
	}

	public static String getTranslateStringFromCms(String cmsURI, Object... objects) {
		return Ivy.cms().co(cmsURI, Arrays.asList(objects));
	}

	public static String getTranslatedCmsByLocale(String cmsURI, Locale locale, Object... objects) {
		var message = Ivy.cms().coLocale(cmsURI, locale);
		// Reto Weiss said: Here the parameters are replaces using class MessageFormat.
		// Here single quotes are used to escape format parameters. You have to encode
		// single quotes with two single quotes.
		return MessageFormat.format(SINGLE_QUOTE_REGEX.matcher(message).replaceAll("''"), objects);
	}

	public static Object[] getTranslatedCmsObjectsByLocale(Locale locale, Object... objects) {
		if (objects == null) {
			return new Object[0];
		}

		Object[] result = new Object[objects.length];
		if (objects.length > 0) {
			for (int i = 0; i < objects.length; i++) {
				result[i] = getTranslateStringFromCMSMacros(objects[i].toString(), locale);
			}
		}

		return result;
	}

	public static String getTranslateStringFromCms(String cmsURI) {
		return Ivy.cms().co(cmsURI);
	}

	public static boolean hasOpenedTasks(ICase iCase, Long currentTaskId) {
		List<ITask> tasks = iCase.tasks().allActive();
		return tasks.stream().anyMatch(iTask -> iTask.getId() != currentTaskId);
	}
	
	public static IUser findUserByEmail(String email) {
		return UserQuery.create().where().eMailAddress().isEqualIgnoreCase(email).executor().firstResult();
	}

	public static void redirectToURL(String url) throws IOException {
		IHttpResponse response = (IHttpResponse) Ivy.response();
		response.sendRedirect(url);
	}
	
	public static IUser findUserByUserName(String username) {
		return UserQuery.create().where().name().isEqual(username).executor().firstResult();
	}

	/**
	 * Formats a CMS URL using a message pattern without additional parameters.
	 *
	 * @param cmsUrl The CMS URL to be formatted.
	 * @return The formatted CMS URL.
	 */
	public static String getCMSMacrosValue(String cmsUrl) {
	    return MessageFormat.format(CMS_MACROS_PATTERN, cmsUrl);
	}

	/**
	 * Formats a CMS URL using a message pattern and additional parameters.
	 * If no parameters are provided or if they are null, it falls back to the version without parameters.
	 *
	 * @param cmsUrl   The CMS URL to be formatted.
	 * @param objects  Additional parameters to be inserted into the message pattern.
	 * @return The formatted CMS URL with parameters.
	 */
	public static String getCMSMacrosValue(String cmsUrl, Object... objects) {
	    if (Objects.isNull(objects) || objects.length == 0) {
	        // If no parameters are provided, use the version without parameters
	        return getCMSMacrosValue(cmsUrl);
	    } else {
	        // Format parameters and join them with commas
	        var params = Arrays.asList(objects)
	                .stream()
	                .map(param -> String.format("\"%s\"", param))
	                .collect(Collectors.joining(","));

	        // Format the CMS URL with parameters
	        return MessageFormat.format(CMS_MACROS_PATTERN_WITH_PARAMS, cmsUrl, params);
	    }
	}

	public static String getTranslateStringFromCMSMacros(String cmsMacros, Locale locale) {
		Matcher matcher = EXTRACT_CMS_DATA_PATTERN.matcher(cmsMacros);
		if (matcher.find() && matcher.groupCount() > 1) {
			String cmsUri = matcher.group(1);
			var params = Arrays.asList(matcher.group(2).split(",")).stream().map(param -> param.replaceAll("\"", ""))
					.toArray(Object[]::new);
			return locale == null ? getTranslateStringFromCms(cmsUri, params)
					: getTranslatedCmsByLocale(cmsUri, locale, params);
		} else {
			matcher = EXTRACT_CMS_DATA_NO_PARAM_PATTERN.matcher(cmsMacros);
			if (matcher.find() && matcher.groupCount() == 1) {
				String cmsUri = matcher.group(1);
				return locale == null ? getTranslateStringFromCms(cmsUri) : getTranslatedCmsByLocale(cmsUri, locale);
			}
		}

		return cmsMacros;
	}

	public static void insertFileToCurrentAppCMS(String uploadFolder, String imageExtension, InputStream inputStream) {
		ContentManagement.cms(IApplication.current()).root().child().file(uploadFolder, imageExtension).value().get()
				.write().inputStream(inputStream);
	}

	public static byte[] getFileInCMSAsByteArray(String fileName, String extension) {
		var cmsFile = ContentManagement.cms(IApplication.current()).root().child().file(fileName, extension);
		return cmsFile.value().get().read().bytes();
	}

	public static List<ContentObject> getAllChildsObject(String rootFolder, String uuidFolder, String versionIdFolder) {
		var cms = ContentManagement.cms(IApplication.current());
		ContentObject parentFolder = cms.root().child().folder(rootFolder);
		List<ContentObject> childrenImages = new ArrayList<>();
		if (parentFolder.child().exists(uuidFolder)
				&& parentFolder.child().get(uuidFolder).get().child().exists(versionIdFolder)) {
			childrenImages
					.addAll(parentFolder.child().get(uuidFolder).get().child().get(versionIdFolder).get().children());
		}
		return childrenImages;
	}
	
	public static List<ContentObject> getOnlyFilesFromUuidFolder(String rootFolder, String uuidFolder) {
		var cms = ContentManagement.cms(IApplication.current());
		ContentObject parentFolder = cms.root().child().folder(rootFolder);
		List<ContentObject> childrenImages = new ArrayList<>();
		if (parentFolder.child().exists(uuidFolder)) {
			childrenImages.addAll(parentFolder.child().get(uuidFolder).get().children());
		}
		return childrenImages.stream()
				.filter(contentObj -> ContentObjectType.FILE == contentObj.meta().contentObjectType())
				.collect(Collectors.toList());
	}

	public static void removeApplicationCMS(String uri, String extension) {
		ContentManagement.cms(IApplication.current()).root().child().file(uri, extension).delete();
	}

	public static void removeApplicationCMSFolder(String folder, String subFolder) {
		ContentObject parentFolder = ContentManagement.cms(IApplication.current()).root().child().get(folder).get();
		if (parentFolder.child().exists(subFolder)) {
			parentFolder.child().get(subFolder).get().delete();
		}
	}

	public static ContentObject getFileFromFolder(String folder) {
		var cms = ContentManagement.cms(IApplication.current());
		return cms.get(folder).orElse(null);
	}
}
