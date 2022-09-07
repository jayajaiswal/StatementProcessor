package com.rabo.statementProcessor.processor;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import com.opencsv.bean.CsvToBeanBuilder;
import com.rabo.statementProcessor.exception.StatementProcessException;
import com.rabo.statementProcessor.model.CsvStatementData;
import com.rabo.statementProcessor.model.StatementRecord;

public class CsvProcessor implements FileProcessor{

	@Override
	public List<StatementRecord> process(InputStream stream) {
		
		List<CsvStatementData> csvStatementDataList;
		
		try {
			csvStatementDataList = new CsvToBeanBuilder<CsvStatementData>(new BufferedReader(new InputStreamReader(stream)))
									.withType(CsvStatementData.class)
									.build()
									.parse();
		}
		catch(Exception e) {
			throw new StatementProcessException("Invalid CSV file", e);
		}
		
		return csvStatementDataList.stream().map(this :: mapCsvData).collect(Collectors.toList());
	}
	
	public StatementRecord mapCsvData(CsvStatementData data){
		
		StatementRecord record = new StatementRecord();
		try {
			record.setReference(Long.parseLong(data.getReference()));
			record.setAccountNumber(data.getAccountNumber());
			record.setDescription(data.getDescription());
			record.setStartBalance(new BigDecimal(data.getStartBalance()));
			record.setMutation(new BigDecimal(data.getMutation()));
			record.setEndBalance(new BigDecimal(data.getEndBalance()));
			
		}
		catch(NumberFormatException e) {
			throw new StatementProcessException("Error mapping csv data",e);
		}
		return record;
	}


}
