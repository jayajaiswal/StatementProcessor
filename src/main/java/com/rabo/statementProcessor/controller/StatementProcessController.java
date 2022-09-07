package com.rabo.statementProcessor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.rabo.statementProcessor.exception.StatementProcessException;
import com.rabo.statementProcessor.model.ValidationReport;
import com.rabo.statementProcessor.service.StatementProcessService;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/customer/api/")
public class StatementProcessController {
	
	private StatementProcessService statementProcessService;
	
	@Autowired
    public StatementProcessController(StatementProcessService statementProcessorService) {
        this.statementProcessService = statementProcessorService;
    }
	
	 @PostMapping("process-statement")
	  public ResponseEntity<List<ValidationReport>> handle(@NotNull @RequestParam("file") MultipartFile file) {
	        return ResponseEntity.ok(statementProcessService.execute(file));
	    }
    
	   @ExceptionHandler(StatementProcessException.class)
	    public ResponseEntity<String> handleStatementProcessException(RuntimeException re) {
	        return ResponseEntity.badRequest().body("Exception in process: " + re.getMessage());
	    }
    


}
