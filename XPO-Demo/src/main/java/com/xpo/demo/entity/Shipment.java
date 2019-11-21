package com.xpo.demo.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity(name = "Shippment")
@Table(name = "shipment")
@ApiModel(description = "All details about a Shipment. ")
public class Shipment 
{

	@Id
	@Column(name="shipment_id", nullable = false, updatable = false)
	@ApiModelProperty(notes = "Shipment ID")
	private Long shipmentId;
	
	@Column(name="number_of_pieces", nullable = false, updatable = false, columnDefinition = "INT")
	@ApiModelProperty(notes = "The shipment number of pieces included.")
	private Integer numberOfPieces;
	
	@Column(name="value", nullable = false, updatable = false, columnDefinition = "DECIMAL(13,2)")
	@ApiModelProperty(notes = "The Shipment value of items")
	private Double value;
	
	@Column(name="weight", nullable = false, updatable = false, columnDefinition = "DECIMAL(13,2)")
	@ApiModelProperty(notes = "The Shipment weight")
	private Double weight;
	
	@Column(name="volume", nullable = false, updatable = false, columnDefinition = "DECIMAL(13,2)")
	@ApiModelProperty(notes = "The Shipment volume ")
	private Double volume;
	
	@Column(name="shippers_name", nullable = false, updatable = false, columnDefinition = "VARCHAR(26)")
	@ApiModelProperty(notes = "The Shippers name")
	private String shipperName;
	
	@Column(name="consignee_name", nullable = false, updatable = false, columnDefinition = "VARCHAR(26)")
	@ApiModelProperty(notes = "The Shipment consignmenee name")
	private String consigneeName;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_shipped", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@ApiModelProperty(notes = "The Shiopment date")
	private Date dateShipped;
	
	@Convert(converter=StatusConverter.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "claim_status", nullable = false, length = 24, 
	columnDefinition = "VARCHAR(12) DEFAULT 'OPEN'")
	@ApiModelProperty(notes = "The Shipment status")
	private Status status;
	
	@OneToMany(cascade = CascadeType.ALL, targetEntity = Claim.class)
	@JoinTable(name = "shipment_claims",  joinColumns = @JoinColumn(name = "shipment_id", referencedColumnName = "shipment_id"), inverseJoinColumns = @JoinColumn(name = "claim_id"))
	@ApiModelProperty(notes = "The Claims of Shipment")
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
