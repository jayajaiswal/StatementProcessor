package com.rabo.statementProcessor.processor;

public class FileProcessorFactory {
	
	public static FileProcessor getFileProcessor(String fileType) {
		
		switch(fileType) {
			case "CSV" : 
				return new CsvProcessor();
			
			case "XML" : 
				return new XmlProcessor();
				
			default: 
				return null;
		}
	}

}
