package com.xpo.demo.service.impl;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xpo.demo.entity.Claim;
import com.xpo.demo.entity.Shipment;
import com.xpo.demo.repository.ClaimRepository;
import com.xpo.demo.service.ClaimService;
import com.xpo.demo.service.ShipmentService;

@Service
public class ClaimServiceImpl implements ClaimService 
{
	
	@Autowired
	private ClaimRepository claimRepo;

	@Autowired
	private ShipmentService  shipmentService;
	

	@Override
	public List<Claim> listAllClaims() {
		return claimRepo.findAll();
	}

	@Override
	public Claim getClamById(Long id) {
		return claimRepo.getOne(id);
	}

	@Override
	public String claimToShipment(Long shipmentId, Set<Claim> claims) {
		Shipment shipment = shipmentService.getShipmentById(shipmentId);
		shipment.setClaims(claims);
		shipmentService.createOrUpdateShipment(shipment);
		return "Successful";
	}

}
