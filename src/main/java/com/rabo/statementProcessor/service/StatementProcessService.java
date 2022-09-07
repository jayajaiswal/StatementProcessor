package com.rabo.statementProcessor.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.rabo.statementProcessor.exception.StatementProcessException;
import com.rabo.statementProcessor.model.StatementRecord;
import com.rabo.statementProcessor.model.ValidationReport;
import com.rabo.statementProcessor.processor.FileProcessorFactory;
import com.rabo.statementProcessor.validator.StatementValidator;


@Component
public class StatementProcessService {
	
	public List<ValidationReport> execute(MultipartFile file) {

        List<StatementRecord> input = Optional.of(file)
                .map(MultipartFile::getContentType)
                .map(fileType -> (fileType.equals("application/xml") ? "XML" : "CSV"))
                .map(FileProcessorFactory::getFileProcessor)
                .map((fileProcessor -> {
					try {
						return fileProcessor.process(file.getInputStream());
					} catch (IOException e) {
						throw new StatementProcessException("Can't read file");
					}
				})).get();

        List<ValidationReport> output = new ArrayList<ValidationReport>();
        output = (StatementValidator.validate(input));
        return output;
    }
}
