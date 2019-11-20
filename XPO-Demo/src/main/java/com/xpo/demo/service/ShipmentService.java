package com.xpo.demo.service;

import java.util.List;

import com.xpo.demo.entity.Shipment;


public interface ShipmentService {
	
	

	public List <Shipment> listAllShipments();
	public Shipment getShipmentById(Long id);
	public Long createOrUpdateShipment(Shipment shipment);
}
