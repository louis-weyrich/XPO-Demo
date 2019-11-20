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


@RestController
@RequestMapping("/rest/shipping")
public class ShippingController {
	
	@Autowired
	private ShipmentService shipmentService;

	@GetMapping("/shipments")
	public ResponseEntity <List<Shipment>> getShipments()
	{
		List <Shipment> shipments = shipmentService.listAllShipments();
		return new ResponseEntity <List<Shipment>> (shipments, HttpStatus.OK);
	}
	
	@GetMapping("/shipment/{id}")
	public ResponseEntity <Shipment> getShipment(@PathVariable("id")Long id)
	{
		Shipment shipment = shipmentService.getShipmentById(id);
		return new ResponseEntity <Shipment> (shipment, HttpStatus.OK);
	}
	
	@PostMapping("/shipment/create")
	public ResponseEntity <Long> createNewShipment(@RequestBody Shipment shipment)
	{
		Long id = shipmentService.createOrUpdateShipment(shipment);
		return new ResponseEntity <Long> (id, HttpStatus.CREATED);
	}
	
	@PostMapping("/shipment/upload")
	public FileUploadResponse uploadXlsxFiles(@RequestParam("files") MultipartFile[] files)
	{
		return null;
	}
	
}
