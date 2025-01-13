package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.BATCH_INDEX;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.HTTP_CODE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.KEY;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.MESSAGE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.REQUEST_RESPONSE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.REQUEST_TYPE;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.STATUS;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.STATUS_CODE;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_LARGE_STRING_LENGTH;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_SMALL_STRING_LENGTH;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.axonivy.utils.gdprconnector.enums.IPeopleRequestType;

@Entity
@Table(name = REQUEST_RESPONSE)
public class RequestResponse extends CustomAuditableEntity {
	private static final long serialVersionUID = 796443762475086962L;

	@Column(name = HTTP_CODE)
	private Integer httpCode;

	@Column(name = KEY, length = DEFAULT_LARGE_STRING_LENGTH)
	private String key;

	@Column(name = MESSAGE, length = DEFAULT_LARGE_STRING_LENGTH)
	private String message;

	@Column(name = STATUS, length = DEFAULT_LARGE_STRING_LENGTH)
	private String status;

	@Column(name = BATCH_INDEX)
	private int batchIndex;

	@Column(name = STATUS_CODE, length = DEFAULT_SMALL_STRING_LENGTH)
	private String statusCode;

	@Column(name = REQUEST_TYPE, length = DEFAULT_SMALL_STRING_LENGTH)
	private IPeopleRequestType requestType;

	public IPeopleRequestType getRequestType() {
		return requestType;
	}

	public void setRequestType(IPeopleRequestType requestType) {
		this.requestType = requestType;
	}

	public String getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	public int getBatchIndex() {
		return batchIndex;
	}

	public void setBatchIndex(int batchIndex) {
		this.batchIndex = batchIndex;
	}

	public Integer getHttpCode() {
		return httpCode;
	}

	public void setHttpCode(Integer httpCode) {
		this.httpCode = httpCode;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
