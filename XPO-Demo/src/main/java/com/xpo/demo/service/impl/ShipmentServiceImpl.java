package com.xpo.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xpo.demo.entity.Shipment;
import com.xpo.demo.repository.ShippingRepository;
import com.xpo.demo.service.ShipmentService;

@Service
public class ShipmentServiceImpl implements ShipmentService 
{

	@Autowired
	private ShippingRepository shippingRepository;
	
	public ShipmentServiceImpl() {
		// Do Nothing
	}

	@Override
	@Transactional()
	public List<Shipment> listAllShipments() 
	{
		return shippingRepository.findAll();
	}

	@Override
	@Transactional()
	public Shipment getShipmentById(Long id) 
	{
		return shippingRepository.getOne(id);
	}

	@Override
	@Transactional()
	public Long createOrUpdateShipment(Shipment shipment) {
		return shippingRepository.saveAndFlush(shipment).getShipmentId();
	}

}
