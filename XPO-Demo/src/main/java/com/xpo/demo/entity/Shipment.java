package com.xpo.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity(name = "Shippment")
@Table(name = "shipment")
public class Shipment 
{

	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="shipment_id", nullable = false, updatable = false)
	private Long shipmentId;
	
	@Column(name="number_of_pieces", nullable = false, updatable = false, columnDefinition = "INT")
	private Integer numberOfPieces;
	
	@Column(name="value", nullable = false, updatable = false, columnDefinition = "DECIMAL(13,2)")
	private Double value;
	
	@Column(name="weight", nullable = false, updatable = false, columnDefinition = "DECIMAL(13,2)")
	private Double weight;
	
	@Column(name="volume", nullable = false, updatable = false, columnDefinition = "DECIMAL(13,2)")
	private Double volume;
	
	@Column(name="shippers_name", nullable = false, updatable = false, columnDefinition = "VARCHAR(26)")
	private String shipperName;
	
	@Column(name="consignee_name", nullable = false, updatable = false, columnDefinition = "VARCHAR(26)")
	private String consigneeName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_shipped", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date dateShipped;
	
	@Convert(converter=StatusConverter.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "claim_status", nullable = false, length = 24, 
	columnDefinition = "VARCHAR(12) DEFAULT 'OPEN'")
	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Claim.class)
	@JoinTable(name = "shipment_claims",  joinColumns = @JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id"), inverseJoinColumns = @JoinColumn(name = "claim_id"))
	private Set<Claim> claims;
	
	
	
	public Shipment() {
		// Do Nothing
	}



	public Long getShipmentId() {
		return shipmentId;
	}



	public void setShipmentId(Long shipmentId) {
		this.shipmentId = shipmentId;
	}



	public Integer getNumberOfPieces() {
		return numberOfPieces;
	}



	public void setNumberOfPieces(Integer numberOfPieces) {
		this.numberOfPieces = numberOfPieces;
	}



	public Double getValue() {
		return value;
	}



	public void setValue(Double value) {
		this.value = value;
	}



	public Double getWeight() {
		return weight;
	}



	public void setWeight(Double weight) {
		this.weight = weight;
	}



	public Double getVolume() {
		return volume;
	}



	public void setVolume(Double volume) {
		this.volume = volume;
	}



	public String getShipperName() {
		return shipperName;
	}



	public void setShipperName(String shipperName) {
		this.shipperName = shipperName;
	}



	public String getConsigneeName() {
		return consigneeName;
	}



	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}



	public Date getDateShipped() {
		return dateShipped;
	}



	public void setDateShipped(Date dateShipped) {
		this.dateShipped = dateShipped;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}



	public Set<Claim> getClaims() {
		return claims;
	}



	public void setClaims(Set<Claim> claims) {
		this.claims = claims;
	}

}
