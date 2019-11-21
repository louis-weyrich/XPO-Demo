package com.xpo.demo.rest;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpo.demo.entity.Claim;
import com.xpo.demo.entity.Shipment;
import com.xpo.demo.service.ClaimService;
import com.xpo.demo.service.ShipmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@Api(value = "Claims API")
@RestController
@RequestMapping("rest/claim")
public class ClaimController {

	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private ShipmentService shipmentService;
	
	@ApiOperation(value = "View a list of Claims by shipment id", response = List.class)
	@GetMapping("/claims/{shipmentId}")
	public Set <Claim> getAllClaims(@PathVariable("shipmentId")Long id)
	{
		Shipment shipment = shipmentService.getShipmentById(id);
		return shipment.getClaims();
	}
	
	@ApiOperation(value = "Get a Claim by id", response = Shipment.class)
	@ApiResponses(value = {
		@ApiResponse(code = 200, message = "Successfully retrieved list of Claims"),
		@ApiResponse(code = 401, message = "You are not authorized to view the resource"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 500, message = "The resource you were trying to reach is having problems")
	})
	@GetMapping("/claim/{id}")
	public ResponseEntity <Claim> getClaimById(@PathVariable("id")Long id)
	{
		Claim claim = claimService.getClamById(id);
		return new ResponseEntity <Claim> (claim, HttpStatus.OK);
	}
	
	@ApiOperation(value = "Add Claims to a Shipment", response = Shipment.class)
	@ApiResponses(value = {
		@ApiResponse(code = 201, message = "Successfully Added Claim to Shipment"),
		@ApiResponse(code = 401, message = "You are not authorized to create a Claim"),
		@ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
		@ApiResponse(code = 500, message = "The resource you were trying to reach is having problems")
	})
	@PostMapping("/claims/add-to-shipment/{shipmentId}")
	public ResponseEntity <Shipment> addClaimsToShipment(@PathVariable("shipmentId") Long shipmentId, @RequestBody Set <Claim> claims)
	{
		Shipment shipment = shipmentService.getShipmentById(shipmentId);
		shipment.setClaims(claims);
		
		return new ResponseEntity <Shipment> (shipment, HttpStatus.CREATED);
	}

}
