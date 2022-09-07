package com.rabo.statementProcessor.processor;

import java.math.BigDecimal;
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
public class XmlProcessorTest {

	 @Test
	    public void processSuccess() {
	        FileProcessor xmlProcessor = new XmlProcessor();
	        List<StatementRecord> input = xmlProcessor.process(getClass().getResourceAsStream("/records.xml"));

	        Assertions.assertEquals(10, input.size());
			/*
			 * Assertions.assertEquals(Long.valueOf("187997"), input.get(0).getReference());
			 * Assertions.assertEquals("NL91RABO0315273637",
			 * input.get(0).getAccountNumber());
			 * Assertions.assertEquals("Clothes for Rik King",
			 * input.get(0).getDescription());
			 * Assertions.assertEquals(BigDecimal.valueOf(57.6),
			 * input.get(0).getStartBalance());
			 * Assertions.assertEquals(BigDecimal.valueOf(-32.98),
			 * input.get(0).getMutation());
			 * Assertions.assertEquals(BigDecimal.valueOf(24.62),
			 * input.get(0).getEndBalance());
			 */
	    }

	    @Test
	    public void processFailureWrongData() {
	        FileProcessor xmlProcessor = new XmlProcessor();
	        Assertions.assertThrows(StatementProcessException.class,() -> xmlProcessor.process(getClass().getResourceAsStream("/wrong_records.xml")));
	    }

	    @Test
	    public void processFailure() {
	        FileProcessor xmlProcessor = new XmlProcessor();
	        Assertions.assertThrows(StatementProcessException.class,() -> xmlProcessor.process(getClass().getResourceAsStream("/invalid.xml")));
	    }
}
