package com.kp.content.parser;

import java.io.File;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kp.content.beans.Constants;
import com.kp.content.beans.ContentItem;
import com.kp.content.beans.ContentItemList;
import com.kp.content.beans.Property;
import com.kp.content.stats.CartridgeContentMap;
import com.kp.content.stats.ContentStat;
import com.kp.content.stats.ContentStatus;
import com.kp.test.ContentJson;



public class DirectoryParser {
	
		private static ContentStat contentStat;
		public static ContentStat getContentStat() {
			if(null == contentStat)
			{
				contentStat = new ContentStat();
			}
			return contentStat;
		}

		public void parseDir(File node){
		
		//System.out.println(node.getAbsoluteFile());
		
		if(node.isDirectory()){
			String[] subNote = node.list();
			for(String filename : subNote){
				parseDir(new File(node, filename));
			}
		}
		// this is a file
		else
		{
			if(isLeafNode(node))
			{
				if(node.getName().equalsIgnoreCase(Constants.CONTENT_FILE_NAME))
				{
					parseContentFile(node);
				}
			}
		}
		
	}

		private static void parseContentFile(File node) {
			
			try
			{
				JAXBContext jaxbContext = JAXBContext.newInstance(ContentItem.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				//ContentItem contentItem = (ContentItem) jaxbUnmarshaller.unmarshal(node);
				Source source = new StreamSource(node);
				JAXBElement<ContentItem> root = jaxbUnmarshaller.unmarshal(source, ContentItem.class);
				ContentItem contentItem = root.getValue();
				// getting the json for status information
				ContentStatus contentStatus = getContentStatus(node);
				System.out.println("Parsing File - "+node.getAbsolutePath()+" Active -"+contentStatus.isActive());
				processContentItem(contentItem,contentStatus,node.getAbsolutePath());
			}
			catch(JAXBException e)
			{
				e.printStackTrace();
			}
			
			
		}

		private static void processContentItem(ContentItem contentItem, ContentStatus contentStatus,String filePath) {
			
			String templateId = contentItem.getTemplateId();
			CartridgeContentMap map = new CartridgeContentMap();
			map.setContentName(contentItem.getName());
			map.setActive(contentStatus.isActive());
			map.setContentPath(filePath);
			
			getContentStat().addCatridgeId(templateId);
			getContentStat().addToCartridgeContentMap(templateId, map);
			
			for(Property prop:contentItem.getProperty())
			{
				if(null != prop.getContentItem())
				{
					processContentItem(prop.getContentItem().getValue(), contentStatus, filePath);
				}
				if(null != prop.getContentItemList())
				{
					ContentItemList contentItemList= prop.getContentItemList().getValue();
					for(ContentItem content:contentItemList.getContentItem())
					{
						processContentItem(content, contentStatus, filePath);
					}
				}
			}
			
		}

		private static ContentStatus getContentStatus(File node) {
			ContentStatus conStat = new ContentStatus(null, true, false);
			try
			{
				ObjectMapper mapper = new ObjectMapper();
				File parent = node.getParentFile();
				for(File file:parent.listFiles())
				{
					if(Constants.JSON_FILE_NAME.equalsIgnoreCase(file.getName()))
					{
						ContentJson contentJson = mapper.readValue(file, ContentJson.class);
						conStat.setContentPath(node.getAbsolutePath());
						if(Constants.WORKFLOW_STATUS_ACTIVE.equalsIgnoreCase(contentJson.getWorkflowState()))
						{
							conStat.setActive(true);
						}
						else
						{
							conStat.setActive(false);
						}					
						conStat.setPreviewStatus(contentJson.getPreviewable());
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("Error while getting the content status of content item -"+node.getAbsolutePath());
			}
			return conStat;
		}

		private static boolean isLeafNode(File node) {
			boolean isLeaf=true;
			File parent = node.getParentFile();
			if(parent.isDirectory())
			{
				File[] children = parent.listFiles();
				for(File file:children)
				{
					if(file.isDirectory())
					{
						isLeaf=false;
					}
				}
			}
			return isLeaf;
		}
		
		public static void printResults()
		{
			
			if(null != getContentStat().getActiveCartridgeIdList())
			{
				System.out.println("Here is the list of all active cartridges");
				for(String cartridgeId:getContentStat().getActiveCartridgeIdList())
				{
					System.out.println(cartridgeId);
				}
			}
			else
			{
				System.out.println("There are no active cartridges");
			}
		}
		
		public static void printDetailedResults()
		{
			
			if(null != getContentStat().getActiveCartridgeIdList())
			{
				System.out.println("Detailed Results");
				System.out.println("CartridgeId | ContentPath | ContentName | Active?");
				for(String cartridgeId:getContentStat().getActiveCartridgeIdList())
				{
					Set<CartridgeContentMap> mapList = getContentStat().getCartridgeContentMap().get(cartridgeId);
					for(CartridgeContentMap map:mapList)
					{
						System.out.println(cartridgeId+" | "+map.getContentPath()+" | "+map.getContentName()+" | "+map.isActive());
					}
				}
			}
			else
			{
				System.out.println("There are no active cartridges");
			}
		}

}
