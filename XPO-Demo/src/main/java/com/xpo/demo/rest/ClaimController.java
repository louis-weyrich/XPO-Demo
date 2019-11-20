package com.xpo.demo.rest;

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

@RestController
@RequestMapping("rest/claim")
public class ClaimController {

	@Autowired
	private ClaimService claimService;
	
	@Autowired
	private ShipmentService shipmentService;
	
	
	@GetMapping("/claims/{shipmentId}")
	public Set <Claim> getAllClaims(@PathVariable("shipmentId")Long id)
	{
		Shipment shipment = shipmentService.getShipmentById(id);
		return shipment.getClaims();
	}
	
	@GetMapping("/claim/{id}")
	public ResponseEntity <Claim> getClaimById(@PathVariable("id")Long id)
	{
		Claim claim = claimService.getClamById(id);
		return new ResponseEntity <Claim> (claim, HttpStatus.OK);
	}
	
	@PostMapping("/claims/add-to-shipment/{shipmentId}")
	public ResponseEntity <Shipment> addClaimsToShipment(@PathVariable("shipmentId") Long shipmentId, @RequestBody Set <Claim> claims)
	{
		Shipment shipment = shipmentService.getShipmentById(shipmentId);
		shipment.setClaims(claims);
		
		return new ResponseEntity <Shipment> (shipment, HttpStatus.CREATED);
	}

}
