package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.APPROVAL_DATE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.APPROVAL_HISTORY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.COMMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.DECISION;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.IS_EDITTING;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.PROCESS_STEP;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.SELECTED_CONFIRMATIONS;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_MEDIUM_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XLARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XXLARGE_STRING_LENGTH;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = APPROVAL_HISTORY)
public class ApprovalHistory extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = DECISION, length = DEFAULT_MEDIUM_STRING_LENGTH)
	private String decision;

	@Column(name = COMMENT, length = DEFAULT_XXLARGE_STRING_LENGTH)
	private String comment;

	@Column(name = SELECTED_CONFIRMATIONS, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String selectedConfirmations;

	@Column(name = IS_EDITTING)
	private Boolean isEditting;

	@Column(name = APPROVAL_DATE)
	private LocalDateTime approvalDate;

	@Column(name = PROCESS_STEP, length = DEFAULT_MEDIUM_STRING_LENGTH)
	private String processStep;

	@Transient
	private String displayUserName;

	@Transient
	private String displayApprovalDate;

	/**
	 * use to sort by approveDate in table
	 */
	@Transient
	private String sortableApprovalDate;

	public String getDecision() {
		return decision;
	}

	public void setDecision(String decision) {
		this.decision = decision;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSelectedConfirmations() {
		return selectedConfirmations;
	}

	public void setSelectedConfirmations(String selectedConfirmations) {
		this.selectedConfirmations = selectedConfirmations;
	}

	public Boolean getIsEditting() {
		return isEditting;
	}

	public void setIsEditting(Boolean isEditting) {
		this.isEditting = isEditting;
	}

	public LocalDateTime getApprovalDate() {
		return approvalDate;
	}

	public void setApprovalDate(LocalDateTime approvalDate) {
		this.approvalDate = approvalDate;
	}

	public String getProcessStep() {
		return processStep;
	}

	public void setProcessStep(String processStep) {
		this.processStep = processStep;
	}

	public String getDisplayUserName() {
		return displayUserName;
	}

	public void setDisplayUserName(String displayUserName) {
		this.displayUserName = displayUserName;
	}

	public String getDisplayApprovalDate() {
		return displayApprovalDate;
	}

	public void setDisplayApprovalDate(String displayApprovalDate) {
		this.displayApprovalDate = displayApprovalDate;
	}

	public String getSortableApprovalDate() {
		return sortableApprovalDate;
	}

	public void setSortableApprovalDate(String sortableApprovalDate) {
		this.sortableApprovalDate = sortableApprovalDate;
	}
}
