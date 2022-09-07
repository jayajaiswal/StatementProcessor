package com.rabo.statementProcessor.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

import java.io.InputStream;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.rabo.statementProcessor.controller.StatementProcessController;
import com.rabo.statementProcessor.exception.StatementProcessException;
import com.rabo.statementProcessor.service.StatementProcessService;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ControllerTest {
	
	private InputStream in;
	
	@Autowired
	 private MockMvc mockMvc;

	    @Mock
	    private StatementProcessService statementProcessService;

	    @Spy
	    @InjectMocks
	    private StatementProcessController controller = new StatementProcessController(statementProcessService);
	    
	    @Before
	    public void init() {
	        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
	        in = controller.getClass().getClassLoader().getResourceAsStream("/records.xml");
	    }
	    
	    @Test
	    public void testUploadFileSuccess() throws Exception {
	        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.xml", "multipart/form-data", in);
	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/customer/api/process-statement").file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
	                .andExpect(MockMvcResultMatchers.status().is(200))
	                .andReturn();
	        assertEquals(200, result.getResponse().getStatus());
	        assertNotNull(result.getResponse().getContentAsString());
	    }
	    

	    @Test
	    public void testUploadFileHttp404() throws Exception {
	        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", "records.xml", "multipart/form-data", in);
	        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/process-statement").file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
	                .andExpect(MockMvcResultMatchers.status().is(404)).andReturn();
	        assertEquals(404, result.getResponse().getStatus());
	    }
	    
	    @Test
	    public void testFailure() throws Exception {
	        MockMultipartFile mockMultipartFile = new MockMultipartFile("file", in);
	        when(controller.handle(mockMultipartFile)).thenThrow(new StatementProcessException("Unexpected Exception"));
	        mockMvc.perform(MockMvcRequestBuilders.multipart("/customer/api/v1/process-statement").file(mockMultipartFile).contentType(MediaType.MULTIPART_FORM_DATA))
	                .andExpect(MockMvcResultMatchers.status().isBadRequest())
	                .andExpect(MockMvcResultMatchers.content().string("Exception in process: Unexpected Exception"));
	    }

}
