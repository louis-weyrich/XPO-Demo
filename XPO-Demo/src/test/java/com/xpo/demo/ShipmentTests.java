package com.xpo.demo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.xpo.demo.entity.Claim;
import com.xpo.demo.entity.Shipment;
import com.xpo.demo.entity.Status;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = XpoDemoApplication.class)
@WebAppConfiguration
class ShipmentTests {

	private MockMvc mvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	
	public void setUp() {
		if(mvc == null)
			mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	private String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	private <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}
	
	@Test
	public void testCreateShipment()
	{
		setUp();
		
		Shipment shipment = new Shipment();
		shipment.setShipmentId(1l);
		shipment.setShipperName("Nike");
		shipment.setNumberOfPieces(100000);
		shipment.setValue(12465.10);
		shipment.setConsigneeName("Nike Store 2545");
		shipment.setVolume(46778.34);
		shipment.setWeight(64566.22);
		shipment.setDateShipped(new Date());
		shipment.setStatus(Status.OPEN);
		
		Set <Claim> claims = new HashSet <Claim> ();
		Claim claim = new Claim();
		claim.setClaimId(1l);
		claim.setExaminer("Phil Knight");
		claim.setClaimant("Nike Store 2545");
		claim.setFileBy("Nike Employee");
		claim.setStatus(Status.OPEN);
		claim.setAmount(10300340.00);
		claim.setDateFiled(new Date());
		claim.setPaid(0.0);
		claims.add(claim);
		
		claim = new Claim();
		claim.setClaimId(2l);
		claim.setExaminer("Vardaan Goyal");
		claim.setClaimant("Nike Store 154");
		claim.setFileBy("Nike Employee");
		claim.setStatus(Status.OPEN);
		claim.setAmount(300340.00);
		claim.setDateFiled(new Date());
		claim.setPaid(0.0);
		claims.add(claim);
		
		shipment.setClaims(claims);
		
		try {
			String json = mapToJson(shipment);
			
			String shipmentUri = "http://localhost:8080/rest/shipping/shipment/create";
			
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(shipmentUri)
				      .contentType(MediaType.APPLICATION_JSON_VALUE).content(json)).andReturn();
			
			int status = mvcResult.getResponse().getStatus();
			assertEquals(201, status);
			String content = mvcResult.getResponse().getContentAsString();
			assertNotNull(content);
			
		} catch (JsonProcessingException e) {
			fail(e.getMessage());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetAllShipments() throws Exception 
	{
		setUp();
		String shipmenturi = "http://localhost:8080/rest/shipping/shipments";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(shipmenturi)
			.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		String content = mvcResult.getResponse().getContentAsString();
		Shipment[] shipmentlist = mapFromJson(content, Shipment[].class);
		
		for(Shipment shipment : shipmentlist)
		{
			System.out.println("SHIPMENT_ID = ["+shipment.getShipmentId()+"]");
		}
		
		assertTrue(shipmentlist.length > 0);
	}


	@Test
	public void testShipmentById() throws Exception 
	{
		setUp();
		String shipmenturi = "http://localhost:8080/rest/shipping/shipment/1";
		
		try
		{
			MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(shipmenturi)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	
			int status = mvcResult.getResponse().getStatus();
			assertEquals(200, status);
			String content = mvcResult.getResponse().getContentAsString();
			Shipment shipment = mapFromJson(content, Shipment.class);
			assertNotNull(shipment);
		}
		catch(Exception e)
		{
			fail(e.getMessage());
		}
	}

}
