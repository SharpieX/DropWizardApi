package com.routify.core;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

public class Task {

	
	private Integer id;

	@NotBlank
	@Length(min = 2, max = 255)
	private String description;

	@NotBlank
	@Length(min = 2, max = 255)
	private String assignee;

	@NotBlank
	@Length(min = 2, max = 255)
	private String assignedTo;

	public Task() {
	}

	public Task(Integer id, String description, String assignee,
			String assignedTo) {
		this.id = id;
		this.description = description;
		this.assignee = assignee;
		this.assignedTo = assignedTo;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getAssignedTo() {
		return assignedTo;
	}

	public void setAssignedTo(String assignedTo) {
		this.assignedTo = assignedTo;
	}

	@Override
	public String toString() {
		return "Task [id=" + id + ", description=" + description
				+ ", assignee=" + assignee + ", assignedTo=" + assignedTo + "]";
	}
}
