package com.kp.test;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ContentJson {

	private String workflowState;
	private boolean previewable;
	public String getWorkflowState() {
		return workflowState;
	}
	public void setWorkflowState(String workflowState) {
		this.workflowState = workflowState;
	}
	/*public ContentJson(String workflowState, String previewable) {
		super();
		this.workflowState = workflowState;
		this.previewable = previewable;
	}*/
	public boolean getPreviewable() {
		return previewable;
	}
	public void setPreviewable(boolean previewable) {
		this.previewable = previewable;
	}
	@Override
	public String toString()
	{
		return "Printing contentJSON - workflowstate - "+workflowState+" previewable -"+previewable;
	}
	
}
