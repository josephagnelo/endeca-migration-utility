/**
 * 
 */
package com.kp.contenttemplate.beans;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.lang.String;

/**
 * @author jskariah
 *
 */
public class ContentTemplateResult {

	Map<String,File> contentIdTemplateFileMap = null;
	List<ContentTemplate> cartridgeList = null;
	public Map<String, File> getContentIdTemplateFileMap() {
		return contentIdTemplateFileMap;
	}
	public void setContentIdTemplateFileMap(Map<String, File> contentIdTemplateFileMap) {
		this.contentIdTemplateFileMap = contentIdTemplateFileMap;
	}
	public List<ContentTemplate> getCartridgeList() {
		return cartridgeList;
	}
	public void setCartridgeList(List<ContentTemplate> cartridgeList) {
		this.cartridgeList = cartridgeList;
	}
	
}
