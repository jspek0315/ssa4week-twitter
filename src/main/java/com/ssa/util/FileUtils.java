package com.ssa.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

public class FileUtils {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String fileName ="c:\\temp\\test.html";
		
		String fileNameNew ="c:\\temp\\testNew.html";
		
		System.out.println(FileUtils.readFile(fileName));
		
		try{
			FileUtils.writeToFileByLine(fileName, "test add new line.");
		} catch (Exception e){
			e.printStackTrace();
		}
		
		System.out.println("***After add new line");
		System.out.println(FileUtils.readFile(fileName));
		

	}
	

	public static void writeToFileByLine (String newFileName, String oneLine) throws Exception {
				
     //open exiting file, and append new line
		File file = new File(newFileName);

		// if file doesn't exists, then create it
		if (!file.exists()) {
			file.createNewFile();
		}
		
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		
		bw.write(System.lineSeparator() + oneLine);
			
		bw.close();

	}
	public static StringBuffer readFile ( String fileName){
		
		StringBuffer sbContent = new StringBuffer();
		// This will reference one line at a time
        String line = null;

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = 
                new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = 
                new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                
                sbContent.append( line + System.lineSeparator());
            }   

            // Always close files.
            bufferedReader.close();         
        }
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                fileName + "'" + ex.toString());                        
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + fileName + "'" + ex.toString());                  
            // Or we could just do this: 
            // ex.printStackTrace();
        }
        
        return sbContent;
    }


}
