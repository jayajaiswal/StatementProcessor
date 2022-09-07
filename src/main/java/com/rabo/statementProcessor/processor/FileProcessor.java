package com.rabo.statementProcessor.processor;

import java.io.InputStream;
import java.util.List;

import com.rabo.statementProcessor.model.StatementRecord;

public interface FileProcessor {

	List<StatementRecord> process(InputStream stream);
	
}
