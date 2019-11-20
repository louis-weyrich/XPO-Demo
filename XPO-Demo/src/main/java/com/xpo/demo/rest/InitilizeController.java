package com.xpo.demo.rest;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xpo.demo.entity.Claim;
import com.xpo.demo.entity.Shipment;
import com.xpo.demo.entity.Status;
import com.xpo.demo.service.ShipmentService;

@RestController
@RequestMapping("rest/database")
public class InitilizeController {
	
	@Autowired
	private ShipmentService shipmentService;
	

	@GetMapping("/initialize")
	public String initializeDB()
	{
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
		
		shipmentService.createOrUpdateShipment(shipment);
		
		return "successful";
	}
	
	

}
