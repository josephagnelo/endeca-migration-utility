/**
 * 
 */
package com.kp.migration.utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.plaf.FileChooserUI;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.kp.contenttemplate.beans.ContentTemplate;
import com.kp.contenttemplate.beans.ContentTemplateResult;

/**
 * @author jskariah
 * This class will read the cartrdige templates used in Endeca XM 3.1.2 and convert it into 11.3 version
 * TODO - No support for locale yet. 
 */
public class CartrdigeTemplateMigration {

	private static final String JSON_FILE_NAME = "_.json";
	private static final String JSON_TEMPLATE_FOLDER = "{\"ecr:type\": \"templates-root\"}";
	private static final String JSON_TEMPLATE = "{\"ecr:type\": \"template\"}";
	private static final String TEMPLATE_FILE_NAME = "template.xml";
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		if(args.length != 2)
		{
			printUsage();
			return;
		}
		String inputDir = args[0];
		String outputDir = args[1];
		File inputDirFile = new File (inputDir);
		File outputDirFile = new File (outputDir);
		if(!inputDirFile.exists() || !inputDirFile.isDirectory())
		{
			System.out.println("Input Directory doesnt exist");
			printUsage();
			return;
		}
		ContentTemplateResult ctr = getCartrdigeTemplateList(inputDirFile);
		if(null != ctr)
		{
			convertCartridges(ctr.getCartridgeList(),outputDirFile,ctr.getContentIdTemplateFileMap());
		}
		else
		{
			System.out.println("No Cartridges to process");
		}
	}

	private static void convertCartridges(List<ContentTemplate> cartridgeList, File outputDirFile, Map<String, File> contentIdTemplateFileMap) {
		try {
			if (!outputDirFile.exists()) {
				outputDirFile.createNewFile();
			}
			outputTemplateJSON(outputDirFile,JSON_FILE_NAME,JSON_TEMPLATE_FOLDER);
			for(ContentTemplate contentTemplate:cartridgeList)
			{
				printWarningForThumbNail(contentTemplate);
				outputContentTemplate(outputDirFile,TEMPLATE_FILE_NAME,contentTemplate,contentIdTemplateFileMap);
			}
			System.out.println("Completed the conversion");
		} catch (IOException e) {
			System.out.println("Error outputing the cartridges "+e.getMessage());
		}

	}
	private static void printWarningForThumbNail(ContentTemplate contentTemplate) {
		
		if(null != contentTemplate.getThumbnailUrl())
		{
			System.out.println("ThumbNail not found for - "+contentTemplate.getId()+" Group - "+contentTemplate.getType());
		}
		
	}

	private static void outputContentTemplate(File outputDirFile, String templateFileName,
			ContentTemplate contentTemplate, Map<String, File> contentIdTemplateFileMap) {
		
		try {
			String idValue = contentTemplate.getId();
			File templateDir = new File(outputDirFile, idValue);
			if(!templateDir.exists())
			{
				templateDir.mkdirs();
			}
			// creating json file for template
			outputTemplateJSON(templateDir,JSON_FILE_NAME,JSON_TEMPLATE);
			// copying content template as is
			File sourceFile = contentIdTemplateFileMap.get(idValue);
			String outputFile = new File(templateDir,templateFileName).getPath();
			if(null != sourceFile)
			{
				Files.copy(new FileInputStream(sourceFile), Paths.get(outputFile), StandardCopyOption.REPLACE_EXISTING);
			}
			
		} catch (IOException e) {
			System.out.println("Error outputing the Cartridge Files "+e.getMessage());
		}
		
		
	}

	private static void outputTemplateJSON(File outputDirFile, String jsonFileName, String jsonContent) {
		
		try {
			File jsonFile = new File(outputDirFile, jsonFileName);
			if(!jsonFile.exists())
			{
				jsonFile.createNewFile();
			}
			FileWriter fw = new FileWriter(jsonFile);
			PrintUtility.writePlainLine(fw, Arrays.asList(jsonContent), ' ', ' ');
			fw.flush();
			fw.close();
			
		} catch (Exception e) {
			System.out.println("Error outputing the JSON Files "+e.getMessage());
		}
		
		
	}

	private static ContentTemplateResult getCartrdigeTemplateList(File inputDirFile) {
		ContentTemplateResult ctr = null;
		try
		{
			System.out.println("Processing Cartrdige Directory- "+inputDirFile);
			File[] cartridgeFileList = inputDirFile.listFiles();
			Map<String, File> contentIdTemplateFileMap = null;
			List<ContentTemplate> cartridgeList = null;
			if(null != cartridgeFileList)
			{
				ctr = new ContentTemplateResult();
				cartridgeList = new ArrayList<ContentTemplate>(cartridgeFileList.length);
				contentIdTemplateFileMap = new HashMap<String, File>(cartridgeFileList.length);
				JAXBContext jaxbContext = JAXBContext.newInstance(ContentTemplate.class);
				Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
				
				for(int i=0;i<cartridgeFileList.length;i++)
				{
					try {
						Source source = new StreamSource(cartridgeFileList[i]);
						JAXBElement<ContentTemplate> root = jaxbUnmarshaller.unmarshal(source, ContentTemplate.class);
						ContentTemplate contentTemplate = root.getValue();
						cartridgeList.add(contentTemplate);
						contentIdTemplateFileMap.put(contentTemplate.getId(), cartridgeFileList[i]);
					} catch (Exception e) {
						System.out.println("ERROR Parsing cartridge file - "+e.getMessage()+" file name -"+cartridgeFileList[i]);
					}
					
				}
				System.out.println("Completed Processing Cartrdige Directory- Number of Cartridges read -"+cartridgeList.size());
				ctr.setCartridgeList(cartridgeList);
				ctr.setContentIdTemplateFileMap(contentIdTemplateFileMap);
			}
			
		}
		catch(Exception e)
		{
			System.out.println("ERROR while reading the cartrdiges - "+e.getMessage());
			//e.printStackTrace();
		}
		return ctr;
	}
	private static void printUsage() {
		System.out.println("#################################################################################################");
		System.out.println("Take two args - input directory path for 3.1.2 cartrdiges and output directory");
		System.out.println("Have to place the dtds in the same folder");
		System.out.println("#################################################################################################");
	}

}
