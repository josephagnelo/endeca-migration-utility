package com.kp.migration.utility;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

/*
 * This utility is used to first list all the content items with contentslot.
 * From version 11.2 XM template of contentslot have made the contentPaths as list.
 * This utility is used to convert content items from strings to list and rename the 
 * property.
 */
public class ContentSlotConvertor {

	private static final String PROPERTY_CONTENT_ITEM = "contentItem";
	private static final String PROPERTY_CONTENT_PATHS = "contentPaths";
	private static final String PROPERTY_CONTENT_COLLECTION = "contentCollection";
	public static void main(String[] args) {
		if(args.length != 2)
		{
			printUsage();
			return;
		}
		String inputDir = args[0];
		Boolean convertFiles = Boolean.valueOf(args[1]);
		File processDir = new File(inputDir); // current directory
		processDirectoryContents(processDir,convertFiles);

	}


	private static void printUsage() {
		
		System.out.println("#################################################################################################");
		System.out.println("Take two args - input directory path for contentItem, flag which determines whether to convert the files");
		System.out.println("#################################################################################################");
		
	}


	public static void processDirectoryContents(File dir,Boolean convertFiles) {
		try {
			File[] files = dir.listFiles();
			for (File file : files) {
				if (file.isDirectory()) {
					//System.out.println("directory:" + file.getCanonicalPath());
					processDirectoryContents(file,convertFiles);
				} else {
					System.out.println("processing    file:" + file.getCanonicalPath());
					Boolean needConversion = checkForContentSlotNeedConversion(file);
					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	private static boolean checkForContentSlotNeedConversion(File file) {
		
		boolean found = false;
		try {
			JsonParser jsonParser = new JsonFactory().createParser(file);
			if(! (jsonParser.nextToken() == JsonToken.START_OBJECT))
				System.out.println("InputStream is not valid JSON");
			while(!jsonParser.isClosed())
			{
				JsonToken token = jsonParser.nextToken();
				if(JsonToken.FIELD_NAME.equals(token))
				{
					String fieldName = jsonParser.getCurrentName();
					//System.out.println("Field Name:" + fieldName);
						if((null != fieldName) && fieldName.equalsIgnoreCase(PROPERTY_CONTENT_PATHS))
						{
							//System.out.println("Content Slot found With Content paths:" + file.getCanonicalPath());
							if(jsonParser.nextToken() != JsonToken.START_ARRAY)
							{
								System.out.println("Content Slot found With Content paths:" + file.getCanonicalPath());
								found = true;
							}
							
						}
						if((null != fieldName) && fieldName.equalsIgnoreCase(PROPERTY_CONTENT_COLLECTION))
						{
							System.out.println("Content Slot found With Content Collection:" + file.getCanonicalPath());
							found = true;
						}

							
						
				}
				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return found;
	}
	
}
