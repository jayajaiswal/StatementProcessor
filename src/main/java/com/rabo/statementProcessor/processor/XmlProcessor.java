package com.rabo.statementProcessor.processor;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.rabo.statementProcessor.exception.StatementProcessException;
import com.rabo.statementProcessor.model.StatementRecord;
import com.rabo.statementProcessor.model.XmlStatementData;
import com.rabo.statementProcessor.model.XmlStatementDataList;

public class XmlProcessor implements FileProcessor{

	@Override
	public List<StatementRecord> process(InputStream stream) {
		
        ObjectMapper xmlMapper = new XmlMapper();
        
        xmlMapper.registerModule(new ParameterNamesModule());
        xmlMapper.registerModule(new Jdk8Module());
        xmlMapper.registerModule(new JavaTimeModule());
        		
		XmlStatementDataList xmlStatementDataList;
		
		try {
			xmlStatementDataList = xmlMapper.readValue(stream, XmlStatementDataList.class);
		}
		catch(Exception e) {
			throw new StatementProcessException("Invalid XML file",e);
		}
		return xmlStatementDataList.getXmlStatementDataList().stream()
									.map(this :: mapXmlData).collect(Collectors.toList());
	}
	
	public StatementRecord mapXmlData(XmlStatementData data) {
		
		StatementRecord record =  new StatementRecord();
		
		try{
			record.setReference(Long.parseLong(data.getReference())); 	
			record.setAccountNumber(data.getAccountNumber());
			record.setDescription(data.getDescription());
			record.setStartBalance(new BigDecimal(data.getStartBalance()));
			record.setMutation(new BigDecimal(data.getMutation()));
			record.setEndBalance(new BigDecimal(data.getEndBalance()));
	}
		catch(NumberFormatException e) {
			throw new StatementProcessException("Error mapping xml data",e);
		}
	return record;
	}

}
