package com.rabo.statementProcessor.validator;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.rabo.statementProcessor.model.StatementRecord;
import com.rabo.statementProcessor.model.ValidationReport;

public class StatementValidator {

	public static List<ValidationReport> validate(List<StatementRecord> input){
		
		return input.parallelStream()
                .filter(record -> isReferenceNotUnique(input, record) || isEndBalanceIncorrect(record))
                .map(StatementValidator::getValidationResult)
                .collect(Collectors.toList());
		
	}
	
	 private static boolean isReferenceNotUnique(List<StatementRecord> input, StatementRecord record) {
	        return Collections.frequency(input, record) > 1;
	    }
	 
	 private static boolean isEndBalanceIncorrect(StatementRecord record) {
	        return !record.getStartBalance().add(record.getMutation()).equals(record.getEndBalance());
	    }
	 
	 private static ValidationReport getValidationResult(StatementRecord record) {
	        ValidationReport validationReport = new ValidationReport();
	        validationReport.setReference(record.getReference());
	        validationReport.setDescription(record.getDescription());
	        return validationReport;
	    }
	 
	 
}
