/**
 * @author Masool Baba Irfan
 */
package com.datdynix.controlX_IX.rest.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.datdynix.controlX_IX.rest.model.Certification;
import com.datdynix.controlX_IX.rest.model.Downloadreport;
import com.datdynix.controlX_IX.rest.model.GetReportList;
import com.datdynix.controlX_IX.rest.model.JwtRequest;
import com.datdynix.controlX_IX.rest.model.ListReport;
import com.datdynix.controlX_IX.rest.model.NOTIFIC;
import com.datdynix.controlX_IX.rest.model.StartExport;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.jayway.jsonpath.internal.Path;

import lombok.extern.slf4j.Slf4j;
import net.minidev.json.JSONObject;

/**
 * @author Masool Baba Irfan
 *
 */
@Slf4j
@Service
@RestController
@CrossOrigin
@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class })
@RequestMapping("api/v1/datdyn/")
public class UserManagerService {
	private static final Logger log = LoggerFactory.getLogger(UserManagerService.class);
	final String endpointURL = "https://ianalytics.infintus.com/masterFlow";
	public static String token;
	
	@Bean
	public RestTemplate restTemplate(RestTemplateBuilder builder) {
		return builder.build();
	}
	
	//Authenticate User and generate TOKEN
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	public String authenticate(@RequestBody JwtRequest n) throws KeyManagementException, IOException, NoSuchAlgorithmException {
		
		//Ignore SSL certificate verification
		Certification a = new Certification();
		a.certification();
   
		//Rest templates start here
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Operation-Type", "userAuthentication");
	    JSONObject json = new JSONObject();
	    json.put("username", n.getUsername());
	    json.put("password", n.getPassword());
	    log.info("Post parameters are" + json);
	    HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<HashMap<String, Object>>(json, headers);
		log.info("request entities  are" + requestEntity);
		ResponseEntity<String> result = restTemplate.exchange(endpointURL, HttpMethod.POST, requestEntity, String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonString = objectMapper.writeValueAsString(result.getHeaders());
		JsonNode node = objectMapper.readTree(jsonString);
//		String jsonString1 = objectMapper.writeValueAsString(result.getBody());
//		JsonNode node1 = objectMapper.readTree(jsonString1);
		String str = node.path("token").toString();
		String[] testArray = {str};
		String token1 = Arrays.toString(testArray);
		token = token1.substring(2, token1.length()-2).replaceAll("^\"|\"$", "");
		System.out.println(result.getBody());
		System.out.println(result.getHeaders());
//		System.out.println(node1);
//		ObjectMapper mapper = new ObjectMapper();
//		AuthenticationR staff2 = mapper.readValue(result.getBody(), AuthenticationR.class);
//		System.out.println(staff2);
		return result.getBody();
	    }
	
	// LIST Report Template code here:-
	@RequestMapping(value = "/List_Report_Template", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	public String ListReportTemplate(@RequestBody ListReport n) throws KeyManagementException, IOException, NoSuchAlgorithmException {
		
		//Ignore SSL certificate verification
		Certification a = new Certification();
		a.certification();
		
	    //Rest templates start here
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Operation-Type", "fetch");
	    headers.set("user", "pvscan");
	    headers.set("Authorization",token);
	    JSONObject json = new JSONObject();
		json.put("resourceId", n.getresourceId());
		log.info("Post parameters are" + json);
		HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<HashMap<String, Object>>(json, headers);
		log.info("request entities  are" + requestEntity);
		ResponseEntity<String> result = restTemplate.exchange(endpointURL, HttpMethod.POST, requestEntity, String.class);
	    System.out.println(result.getBody());
	    System.out.println(result.getHeaders());
		return result.getBody();
		}
	
	//List Data Discovery Sets
	@RequestMapping(value = "/Data_discovery_sets", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	public String ListDataDiscoverySets() throws KeyManagementException, NoSuchAlgorithmException, IOException {
	    
		//Ignore SSL certificate verification
		Certification a = new Certification();
		a.certification();
		
	    //Rest templates start here
		RestTemplate restTemplate = new RestTemplate();
		HashMap<String, String> postParams = new HashMap<String, String>();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Operation-Type", "allScanStatus");
	    headers.set("user", "pvscan");
	    headers.set("Authorization",token);
	    postParams.put("", "");
		log.info("Post parameters are" + postParams);
		HttpEntity<HashMap<String, String>> requestEntity = new HttpEntity<HashMap<String, String>>(postParams, headers);
		log.info("request entities  are" + requestEntity);
		ResponseEntity<String> result = restTemplate.exchange(endpointURL, HttpMethod.POST, requestEntity, String.class);
	    System.out.println(result.getBody());
	    System.out.println(result.getHeaders());
		return result.getBody();
		}
	//Start Export
	@RequestMapping(value = "/Start_export", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	public String Startexport(@RequestBody StartExport n) throws KeyManagementException, IOException, NoSuchAlgorithmException {
		
		//Ignore SSL certificate verification
		Certification a = new Certification();
		a.certification();
		
	    //Rest templates start here
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
	    headers.set("Operation-Type", "exportResults");
	    headers.set("user", "pvscan");
	    headers.set("Authorization",token);
	    JSONObject json = new JSONObject();
	    json.put("complianceId", n.getcomplianceId());
	    json.put("currentUser", n.getcurrentUser());
	    json.put("exportType", n.getexportType());
	    json.put("resourceId", n.getresourceId());
	    json.put("visualId", n.getvisualId());
		log.info("Post parameters are" + json);
		HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<HashMap<String, Object>>(json, headers);
		log.info("request entities  are" + requestEntity);
		ResponseEntity<String> result = restTemplate.exchange(endpointURL, HttpMethod.POST, requestEntity, String.class);
	    System.out.println(result.getBody());
	    System.out.println(result.getHeaders());
		return result.getBody();
		}
	
	//List of Notification
	@RequestMapping(value = "/List_Of_Notification",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String listofNotification(@RequestBody NOTIFIC n) throws KeyManagementException, IOException, NoSuchAlgorithmException {
		   
		//Ignore SSL certificate verification
		Certification a = new Certification();
		a.certification();

		//Rest templates start here
		RestTemplate restTemplate = new RestTemplate(); 
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Operation-Type", "listAlert");
		headers.set("user", "pvscan");
		headers.set("Authorization",token);	
		JSONObject json = new JSONObject();
		json.put("currentUser",n.getcurrentUser());
		json.put("page", n.getpage());
		json.put("pageSize", n.getpageSize());
		log.info("Post parameters are" + json);
		//HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<HashMap<String, Object>>((HashMap<String, Object>) json, headers);
		HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<HashMap<String, Object>>(json, headers);
		log.info("request entities  are" + requestEntity);
		ResponseEntity<String> result = restTemplate.exchange(endpointURL, HttpMethod.POST, requestEntity,String.class);
		System.out.println(result.getBody());
		System.out.println(result.getHeaders());
		return result.getBody();
		}
		
	//GET Export Report List
	@RequestMapping(value = "/Get_Export_List",method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	public String getexportreportlist(@RequestBody GetReportList n) throws KeyManagementException, IOException, NoSuchAlgorithmException {

		//Ignore SSL certificate verification
		Certification a = new Certification();
		a.certification();
			
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		RestTemplate restTemplate = new RestTemplate();
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Operation-Type", "fetch");
		headers.set("user", "pvscan");
		headers.set("Authorization",token);	
		JSONObject json = new JSONObject();
		json.put("resourceId", n.getResourceId());
		json.put("queryAttribute", n.getQueryAttribute());
		log.info("Post parameters are" + json);
		HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<HashMap<String, Object>>(json, headers);
		log.info("request entities  are" + requestEntity);
		ResponseEntity<String> result = restTemplate.exchange(endpointURL, HttpMethod.POST, requestEntity,String.class);
		System.out.println(result.getBody());
		System.out.println(result.getHeaders());
		return result.getBody();
		}
		
	// Download Report as Zip file
	@RequestMapping(value = "/Download_Report", method = RequestMethod.POST, consumes = "application/json",produces = "application/json")
	public ResponseEntity<byte[]> DownloadReport(@RequestBody Downloadreport n) throws KeyManagementException, IOException, NoSuchAlgorithmException {
		   
		//Ignore SSL certificate verification
		Certification a = new Certification();
		a.certification();
		
		//Rest templates start here
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new ByteArrayHttpMessageConverter());  
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set("Operation-Type", "exportDownload");
		headers.set("user", "pvscan");
		headers.set("Authorization",token);
		JSONObject json = new JSONObject();
		json.put("exportId", n.getexportId());
		json.put("fileName", n.getfileName());
		log.info("Post parameters are" + json);
		HttpEntity<HashMap<String, Object>> requestEntity = new HttpEntity<HashMap<String, Object>>(json, headers);
		log.info("request entities  are" + requestEntity);
		ResponseEntity<byte[]> result = restTemplate.exchange(endpointURL, HttpMethod.POST, requestEntity, byte[].class);
		System.out.println(result.getHeaders());
			//write ZIP folder and download to path     
//            Files.write(Paths.get("libXpm-3.5.7-w32-src.zip"), result.getBody());
		System.out.println(result.getBody());
            return result;  
	}
}

