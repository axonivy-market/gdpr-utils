package com.axonivy.utils.gdprconnector.persistence.entities;

import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.COMMENT;
import static com.axonivy.utils.gdprconnector.persistence.entities.TableAndColumnNameDirectory.COMMENT_DATE;
import static com.axonivy.utils.gdprconnector.persistence.util.Constants.DEFAULT_XLARGE_STRING_LENGTH;

import java.time.LocalDateTime;

import javax.persistence.Column;

public class Comment extends CustomAuditableEntity {
	private static final long serialVersionUID = 1L;

	@Column(name = COMMENT, length = DEFAULT_XLARGE_STRING_LENGTH)
	private String comment;

	@Column(name = COMMENT_DATE)
	private LocalDateTime commentedDate;

	public Comment() {
	}

	public Comment(String comment, LocalDateTime commentedDate) {
		super();
		this.comment = comment;
		this.commentedDate = commentedDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public LocalDateTime getCommentedDate() {
		return commentedDate;
	}

	public void setCommentedDate(LocalDateTime commentedDate) {
		this.commentedDate = commentedDate;
	}
}
