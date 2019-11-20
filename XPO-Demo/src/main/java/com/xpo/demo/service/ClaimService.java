package com.xpo.demo.service;

import java.util.List;
import java.util.Set;

import com.xpo.demo.entity.Claim;

public interface ClaimService 
{
	public List <Claim> listAllClaims();
	public Claim getClamById(Long id);
	public String claimToShipment(Long shipmentId, Set <Claim> claims);
}
