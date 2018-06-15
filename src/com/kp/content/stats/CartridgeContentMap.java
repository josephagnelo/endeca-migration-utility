package com.kp.content.stats;

public class CartridgeContentMap {
	
	private String cartridgeId;
	private String contentPath;
	private String contentName;
	private boolean isActive;
	public boolean isActive() {
		return isActive;
	}
	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}
	public String getContentPath() {
		return contentPath;
	}
	public void setContentPath(String contentPath) {
		this.contentPath = contentPath;
	}
	public String getContentName() {
		return contentName;
	}
	public void setContentName(String contentName) {
		this.contentName = contentName;
	}
	public String getCartridgeId() {
		return cartridgeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cartridgeId == null) ? 0 : cartridgeId.hashCode());
		result = prime * result + ((contentPath == null) ? 0 : contentPath.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartridgeContentMap other = (CartridgeContentMap) obj;
		if (cartridgeId == null) {
			if (other.cartridgeId != null)
				return false;
		} else if (!cartridgeId.equals(other.cartridgeId))
			return false;
		if (contentPath == null) {
			if (other.contentPath != null)
				return false;
		} else if (!contentPath.equals(other.contentPath))
			return false;
		return true;
	}

}
