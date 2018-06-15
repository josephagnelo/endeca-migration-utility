package com.kp.test;

import java.io.File;
import java.io.IOException;

import com.endeca.migration.MigrationLauncher;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class TestJackson {

	public static void main(String[] args) {
        Employee emp = new Employee(1, "Joseph Skariah", 34, "Chicago");
        
        ObjectMapper mapper = new ObjectMapper();
        try
        {
        	
            /*String json = mapper.writeValueAsString(emp);
            System.out.println(json);
 
            //Use pretty print for printing the output
            String beutifulJson = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(emp);
            System.out.println(beutifulJson);
            
            
            json = "{\"id\":1,\"name\":\"Joseph\",\"age\":34,\"location\":\"Gurnee\"}";
            Employee empFromJSON = mapper.readValue(json, Employee.class);
                 
                System.out.println(empFromJSON);
            
              
            String newJson = "{\"startTime\":\"\",\"workflowState\":\"JOSEPh\",\"previewable\":true}";
            
            ContentJson cj = mapper.readValue(newJson, ContentJson.class);
            System.out.println(cj);*/
            String filePath ="/Users/jskariah/Downloads/todel/BL/content/Kiosk/Browse/New Page/_.json";
            ContentJson cj2 = mapper.readValue(new File(filePath), ContentJson.class);
            System.out.println(cj2);
            MigrationLauncher m = null;
            
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

	}

}
