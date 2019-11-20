package com.xpo.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.xpo.demo.entity.Shipment;

@Repository
public interface ShippingRepository extends JpaRepository<Shipment, Long> {
	
}
