package com.axonivy.utils.gdprconnector.service;

import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.REQUEST_BODY_PARAM;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.UPSERT_RESULT;
import static com.axonivy.utils.gdprconnector.constant.IPeopleConstants.UPSERT_START_NAME;
import static com.axonivy.utils.gdprconnector.enums.IPeopleObjectType.UPSERT_REQUEST;

import java.util.List;
import java.util.Objects;

import com.axonivy.connector.successfactors.connector.rest.SFODataUpsertResult;

import ch.ivyteam.ivy.process.call.SubProcessCall;
import ch.ivyteam.ivy.process.call.SubProcessCallResult;

public class IPeopleUpsertService {
	@SuppressWarnings("unchecked")
	public SFODataUpsertResult upsertEntity(Object requestBody) {
		SFODataUpsertResult upsertResult;
		SubProcessCallResult result = SubProcessCall.withPath(UPSERT_REQUEST.getSubprocessName())
				.withStartSignature(UPSERT_START_NAME).withParam(REQUEST_BODY_PARAM, requestBody).call();
		if (Objects.isNull(result)) {
			return null;
		}
		List<SFODataUpsertResult> response = (List<SFODataUpsertResult>) result.get(UPSERT_RESULT);
		upsertResult = response.stream().findFirst().orElse(null);
		return upsertResult;
	}
}
