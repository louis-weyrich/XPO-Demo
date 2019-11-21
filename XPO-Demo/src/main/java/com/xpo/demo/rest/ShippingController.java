package com.xpo.demo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.xpo.demo.entity.Shipment;
import com.xpo.demo.service.ShipmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api("Shipping API")
@RestController
@RequestMapping("/rest/shipping")
public class ShippingController {
	
	@Autowired
	private ShipmentService shipmentService;

	@ApiOperation(value = "View a list of shipments", response = List.class)
	@GetMapping("/shipments")
	public ResponseEntity <List<Shipment>> getShipments()
	{
		List <Shipment> shipments = shipmentService.listAllShipments();
		return new ResponseEntity <List<Shipment>> (shipments, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Get a shipment by id", response = Shipment.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved list"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 500, message = "The resource you were trying to reach is having problems")
	})
	@GetMapping("/shipment/{id}")
	public ResponseEntity <Shipment> getShipment(@PathVariable("id")Long id)
	{
		Shipment shipment = shipmentService.getShipmentById(id);
		return new ResponseEntity <Shipment> (shipment, HttpStatus.OK);
	}
	
	
	@ApiOperation(value = "Create a new Shipment and get an ID in return", response = Shipment.class)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Successfully created shipment"),
		@ApiResponse(code = 401, message = "You are not authorized to create a shipment"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 500, message = "The resource you were trying to reach is having problems")
	})
	@PostMapping("/shipment/create")
	public ResponseEntity <Long> createNewShipment(@RequestBody Shipment shipment)
	{
		Long id = shipmentService.createOrUpdateShipment(shipment);
		return new ResponseEntity <Long> (id, HttpStatus.CREATED);
	}
	
	
	@ApiOperation(value = "Create a new Shipments by excel files", response = Shipment.class)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Successfully created shipments"),
		@ApiResponse(code = 401, message = "You are not authorized to create a shipment"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 500, message = "The resource you were trying to reach is having problems")
	})
	@PostMapping("/shipment/upload")
	public FileUploadResponse uploadXlsxFiles(@RequestParam("files") MultipartFile[] files)
	{
		return null;
	}
	
}
