package com.kp.content.beans;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentJson {

	private String workflowState;
	private String previewable;
	public String getWorkflowState() {
		return workflowState;
	}
	public void setWorkflowState(String workflowState) {
		this.workflowState = workflowState;
	}
	public String getPreviewable() {
		return previewable;
	}
	public void setPreviewable(String previewable) {
		this.previewable = previewable;
	}
	
}
