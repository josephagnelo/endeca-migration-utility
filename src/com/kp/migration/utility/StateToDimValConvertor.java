package com.kp.migration.utility;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.kp.dimensions.beans.DIMENSION;
import com.kp.dimensions.beans.DIMENSIONID;
import com.kp.dimensions.beans.DIMENSIONNODE;
import com.kp.dimensions.beans.DIMENSIONS;
import com.kp.dimensions.beans.DVAL;
import com.kp.dimensions.beans.SYN;
import com.kp.dimensions.beans.StateEntry;
import com.kp.dvalstate.beans.DVALSTATE;
import com.kp.dvalstate.beans.STATE;

public class StateToDimValConvertor {

	private static final char DEFAULT_SEPARATOR = ',';
	private static final String AUTOGEN_FILE ="--autogen_file";
	private static final String FCM_FILE ="--fcm_state_file";
	private static final String OUTPUT_CSV ="--output_csv";
	private static final String DEFAULT_ROOT_NODE ="/";
	public static void main(String[] args) {
		
		if((args.length != 4) && (args.length !=6) )
		{
			printUsage();
			return;
		}
		String autogenFile = null;
		String fcmStateFile = null;
		String outputFileName = null;
		for(int i=0;i< args.length;i++)
		{
			String arg = args[i];
			if(arg.equalsIgnoreCase(AUTOGEN_FILE))
			{
				autogenFile=args[++i];
			}
			else if(arg.equalsIgnoreCase(FCM_FILE))
			{
				fcmStateFile=args[++i];
			}
			else if(arg.equalsIgnoreCase(OUTPUT_CSV))
			{
				outputFileName=args[++i];
			}
		}
		if(((autogenFile !=null) && (!new File(autogenFile).exists())) || ((fcmStateFile !=null )&& (!new File(fcmStateFile).exists())) )
		{
			printUsage();
			return;
		}
		
		Set<StateEntry> stateEntrySet = processStateDimensions(autogenFile,fcmStateFile);
		if(stateEntrySet != null)
		{
			printErrors(stateEntrySet);
			printOutputFile(stateEntrySet,new File(outputFileName));
		}
		System.out.println("Output File Wirtten to - "+outputFileName);

	}

	private static void printErrors(Set<StateEntry> stateEntrySet) {
		Set<String> idSet = new HashSet(stateEntrySet.size());
		for(StateEntry st:stateEntrySet)
		{
			String id = st.getDimensionValueId();
			if(idSet.contains(id))
			{
				System.out.println("Duplicate values with Id - "+id);
			}
			else
			{
				idSet.add(id);
			}
		}
		
	}

	private static void printOutputFile(Set<StateEntry> stateEntrySet, File file) {
		try {
			if (!file.exists()) {

				file.createNewFile();

			}
			FileWriter fw = new FileWriter(file);
			writeLine(fw, Arrays.asList("Dimension Name","Spec","Id"), DEFAULT_SEPARATOR, '"');
			for (StateEntry st : stateEntrySet) {
				//st.print();
				writeLine(fw, st.getValues(), DEFAULT_SEPARATOR, '"');
			}
			fw.flush();
			fw.close();
			
		} catch (IOException e) {
			System.out.println("Error while creating output file" + e.getMessage());
		}

	}
	private static String followCVSformat(String value) {

        String result = value;
        if (result.contains("\"")) {
            result = result.replace("\"", "\"\"");
        }
        return result;

    }
	   public static void writeLine(Writer w, List<String> values, char separators, char customQuote) throws IOException {

	        boolean first = true;

	        //default customQuote is empty

	        if (separators == ' ') {
	            separators = DEFAULT_SEPARATOR;
	        }

	        StringBuilder sb = new StringBuilder();
	        for (String value : values) {
	            if (!first) {
	                sb.append(separators);
	            }
	            if (customQuote == ' ') {
	                sb.append(followCVSformat(value));
	            } else {
	                sb.append(customQuote).append(followCVSformat(value)).append(customQuote);
	            }

	            first = false;
	        }
	        sb.append("\n");
	        w.append(sb.toString());


	    }

	/**
	 * 
	 * @param inputFileName
	 * @param fcmStateFile 
	 * @return Set of state entries. 
	 * Processing Autogen file first, then FCM. FCM values will override the autogen if same dimension name and value is found. 
	 */
	private static Set<StateEntry> processStateDimensions(String autogenFileName, String fcmStateFileName) {
		Set<StateEntry> stateEntries = null;
		Set<StateEntry> stateEntriesFromAutogen = null;
		Set<StateEntry> stateEntriesFromFCM = null;
		try {
			if (autogenFileName != null) {
				File autogenFile = new File(autogenFileName);
				
				if (autogenFile.exists()) {
					System.out.println("Processing Autogen State File - "+autogenFileName);
					JAXBContext jaxbContext = JAXBContext.newInstance(DIMENSIONS.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					//ContentItem contentItem = (ContentItem) jaxbUnmarshaller.unmarshal(node);
					//jaxbUnmarshaller.setProperty(javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD, "all");
					Source source = new StreamSource(autogenFile);
					JAXBElement<DIMENSIONS> root = jaxbUnmarshaller.unmarshal(source, DIMENSIONS.class);
					DIMENSIONS dimensions = root.getValue();
					if (dimensions != null) {
						stateEntriesFromAutogen = getStateEntriesFromDimensions(dimensions);
					}
				} 
			}
			if(fcmStateFileName != null)
			{
				File fcmFile = new File(fcmStateFileName);
				if(fcmFile.exists())
				{
					System.out.println("Processing FCM State File - "+fcmStateFileName);
					JAXBContext jaxbContext = JAXBContext.newInstance(DVALSTATE.class);
					Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
					//ContentItem contentItem = (ContentItem) jaxbUnmarshaller.unmarshal(node);
					//jaxbUnmarshaller.setProperty(javax.xml.XMLConstants.ACCESS_EXTERNAL_DTD, "all");
					Source source = new StreamSource(fcmFile);
					JAXBElement<DVALSTATE> root = jaxbUnmarshaller.unmarshal(source, DVALSTATE.class);
					DVALSTATE dvalState = root.getValue();
					if (dvalState != null) {
						stateEntriesFromFCM = getStateEntriesFromFCMState(dvalState);
					}
				}
			}
			if((stateEntriesFromAutogen != null) || (stateEntriesFromFCM != null))
			{
				stateEntries = new HashSet<StateEntry>();
				if(stateEntriesFromAutogen != null)
				{
					stateEntries.addAll(stateEntriesFromAutogen);
				}
				if(stateEntriesFromFCM != null)
				{
					for(StateEntry st:stateEntriesFromFCM)
					{
						if(!stateEntries.add(st))
						{
							stateEntries.remove(st);
							stateEntries.add(st);
						}
					}
					//stateEntries.addAll(stateEntriesFromFCM);
				}
			}
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		return stateEntries;
	}

	private static Set<StateEntry> getStateEntriesFromFCMState(DVALSTATE dvalState) {
		Set<StateEntry> stateEntries = null;
		if(dvalState.getSTATE() != null)
		{
			List<STATE> stateList = dvalState.getSTATE();
			stateEntries = new HashSet<StateEntry>(stateList.size());
			for(STATE state:stateList)
			{
				String dimensionName = state.getDIM();
				String dimValue = DEFAULT_ROOT_NODE;
				String sourceId = state.getSOURCEID();
				if((sourceId != null) && (!sourceId.equalsIgnoreCase("-1")) && (!sourceId.equalsIgnoreCase(dimensionName)))
				{
					dimValue=sourceId;
				}
				String dimValId = state.getID();
				// creating state entry
				StateEntry st = new StateEntry(dimensionName, dimValue, dimValId);
				// replace old one if there exists
				if(!stateEntries.add(st))
				{
					stateEntries.remove(st);
					stateEntries.add(st);
				}
				st.print();
			}
		}
		return stateEntries;
	}

	private static Set<StateEntry> getStateEntriesFromDimensions(DIMENSIONS dimensions) {
		Set<StateEntry> stateEntries = null;
		if(null != dimensions && (!dimensions.getDIMENSION().isEmpty()))
		{
			int numOfDimVals = Integer.parseInt(dimensions.getNUMDVALS());
			stateEntries=new HashSet<StateEntry>(numOfDimVals+100);
			for(DIMENSION dimension:dimensions.getDIMENSION())
			{
				String dimensionName=dimension.getNAME();
				String dimensionId = null;
				DIMENSIONID dimensionIdObj= dimension.getDIMENSIONID();
				if(null != dimensionIdObj)
				{
					dimensionId = dimensionIdObj.getID();
					// Entry for root dimension
					StateEntry st = new StateEntry(dimensionName, DEFAULT_ROOT_NODE, dimensionId);
					stateEntries.add(st);
					st.print();
					
				}
				
				// iterate through the dimension values and create entries
				if(dimension.getDIMENSIONNODE() != null)
				{
					DIMENSIONNODE dimensionNode = dimension.getDIMENSIONNODE();
					addDimensionNodeStateEntries(dimensionNode,dimensionName,stateEntries);
				}
			}
		}
		return stateEntries;
	}
	private static void addDimensionNodeStateEntries(DIMENSIONNODE dimensionNode, String dimensionName,
			Set<StateEntry> stateEntries) {
		if(dimensionNode != null)
		{
			if(dimensionName.equalsIgnoreCase("clothing-sku.size"))
			{
				System.out.println("Foiund you");
			}
			// if this is leaf - then create the state entry
			if((dimensionNode.getDIMENSIONNODE() != null) && (!dimensionNode.getDIMENSIONNODE().isEmpty()))
			{
				for(DIMENSIONNODE dimNode:dimensionNode.getDIMENSIONNODE())
				{
					addDimensionNodeStateEntries(dimNode,dimensionName,stateEntries);
				}
			}
			else
			{
				DVAL dVal = dimensionNode.getDVAL();
				if(dVal != null)
				{
					String dValId = dVal.getDVALID().getID();
					String dValue = null;
					for(SYN syn:dVal.getSYN())
					{
						if(syn.getDISPLAY().equalsIgnoreCase("true"))
						{
							dValue=syn.getContent();
							break;
						}
					}
					// if dimension name and dimension value is the same - make it as the root.
					if((dValue != null) && (dValue.equals(dimensionName)))
					{
						dValue = DEFAULT_ROOT_NODE;
					}
					StateEntry stateEntry = new StateEntry(dimensionName, dValue, dValId);
					// replacing values if they already exist
					if(!stateEntries.add(stateEntry))
					{
						stateEntries.remove(stateEntry);
						stateEntries.add(stateEntry);
					}
					stateEntry.print();
				}
			}
		}
		
	}

	private static void printUsage() {
		System.out.println("###################################################################################");
		System.out.println("Takes six args --autogen_file <file> --fcm_state_file <file> --output_csv <file>");
		System.out.println("Also please place the dimensions.dtd,dval_state.dtd and common.dtd in the same folder - else remove the reference");
		System.out.println("You may also need add this flag to VM -Djavax.xml.accessExternalDTD=all");
		System.out.println("###################################################################################");
	}

}
