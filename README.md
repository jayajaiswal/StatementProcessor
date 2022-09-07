# StatementProcessor

Assignment <br/>
Quick summary </br>

Rabobank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated. based on below conditions

•	all transaction references should be unique <br/>
•	end balance needs to be validated <br/>
•	Return both the transaction reference and description of each of the failed records <br/>

# How to run the application

The API can be reached at http://localhost:8080/customer/api/process-statement  <br/>
Upload the csv or xml file as 'file' attribute in POST body   <br/>

# Modules

•	"ControllerStatementProcessController" handles request & response.  <br/>
•	Service "StatementProcessService" prepares the output using other modules.   <br/>
•	"FileProcessorFactory" is to create input file processor based on the file type(CSV/XML)   <br/>
•	StatementValidator is for the validations.  <br/>

