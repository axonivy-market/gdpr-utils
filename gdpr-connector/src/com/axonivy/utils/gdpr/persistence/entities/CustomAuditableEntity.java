package com.axonivy.utils.gdpr.persistence.entities;

import static com.axonivy.utils.gdpr.persistence.entities.TableAndColumnNameDirectory.CREATED_BY;
import static com.axonivy.utils.gdpr.persistence.entities.TableAndColumnNameDirectory.CREATED_DATE;
import static com.axonivy.utils.gdpr.persistence.entities.TableAndColumnNameDirectory.DELETED_BY;
import static com.axonivy.utils.gdpr.persistence.entities.TableAndColumnNameDirectory.DELETED_DATE;
import static com.axonivy.utils.gdpr.persistence.entities.TableAndColumnNameDirectory.ID;
import static com.axonivy.utils.gdpr.persistence.entities.TableAndColumnNameDirectory.MODIFIED_BY;
import static com.axonivy.utils.gdpr.persistence.entities.TableAndColumnNameDirectory.MODIFIED_DATE;
import static com.axonivy.utils.gdpr.persistence.entities.TableAndColumnNameDirectory.VERSION;

import java.util.Objects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import com.axonivy.utils.gdpr.utils.Logger;
import com.axonivy.utils.persistence.beans.AuditableEntity;

@MappedSuperclass
@AttributeOverrides(value = { @AttributeOverride(name = "id", column = @Column(name = ID, length = 32)),
		@AttributeOverride(name = "version", column = @Column(name = VERSION)),
		@AttributeOverride(name = "header.createdByUserName", column = @Column(name = CREATED_BY)),
		@AttributeOverride(name = "header.createdDate", column = @Column(name = CREATED_DATE)),
		@AttributeOverride(name = "header.modifiedByUserName", column = @Column(name = MODIFIED_BY)),
		@AttributeOverride(name = "header.modifiedDate", column = @Column(name = MODIFIED_DATE)),
		@AttributeOverride(name = "header.flaggedDeletedByUserName", column = @Column(name = DELETED_BY)),
		@AttributeOverride(name = "header.flaggedDeletedDate", column = @Column(name = DELETED_DATE)) })
public class CustomAuditableEntity extends AuditableEntity {
	private static final Logger LOG = Logger.getLogger(CustomAuditableEntity.class);

	/**
	 * auto generated serial version UID
	 */
	private static final long serialVersionUID = 8952430704722898141L;

	@Override
	public int hashCode() {
		if (this.getId() == null) {// if id is zero super.hashcode returns wrong hashcode 0
			return System.identityHashCode(this);
		} else {
			return super.hashCode();
		}
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getId() == null) {// if id is null super.equals is not consistent with hascode
			return obj != null && Objects.equals(hashCode(), obj.hashCode());
		} else {
			return super.equals(obj);
		}

	}

	@Override
	public boolean hasValidId() {

		if (super.hasValidId()) {
			if (!getId().isBlank()) {
				return true;
			} else {
				LOG.warn("Id is blank for {0}", this);
			}

		}
		return false;
	}
}
