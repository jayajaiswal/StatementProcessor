package com.rabo.statementProcessor.processor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.Ignore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import com.rabo.statementProcessor.exception.StatementProcessException;
import com.rabo.statementProcessor.model.StatementRecord;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class CsvProcessorTest {

	 @Test
	    public void processSuccess() {
	        FileProcessor csvProcessor = new CsvProcessor();
	        List<StatementRecord> input = csvProcessor.process(getClass().getResourceAsStream("/records.csv"));

	        assertEquals(10, input.size());
	    }

	    @Test
	    public void processFailureWrongData(){
	        FileProcessor csvProcessor = new CsvProcessor();
	        Assertions.assertThrows(StatementProcessException.class,() -> csvProcessor.process(getClass().getResourceAsStream("/wrong_records.csv")));
	    }

	    @Test
	    public void processFailureWrongFile(){
	        FileProcessor csvProcessor = new CsvProcessor();
	       Assertions.assertThrows(StatementProcessException.class,() -> csvProcessor.process(getClass().getResourceAsStream("/invalid.csv")));
	    }
}
