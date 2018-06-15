package com.kp.content.stats;

public class ContentStatus {

	private String contentPath;
	public ContentStatus(String contentPath, boolean status, boolean previewStatus) {
		super();
		this.contentPath = contentPath;
		this.isActive = status;
		this.previewStatus = previewStatus;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public boolean getPreviewStatus() {
		return previewStatus;
	}
	public void setPreviewStatus(boolean previewStatus) {
		this.previewStatus = previewStatus;
	}
	private boolean isActive;
	private boolean previewStatus;
}
