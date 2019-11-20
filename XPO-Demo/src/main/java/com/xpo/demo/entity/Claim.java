package com.xpo.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;


@Entity(name = "Claim")
@Table(name = "claim")
public class Claim 
{
	@Id
//	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="claim_id", nullable = false, updatable = false)
	private Long claimId;
	
	@Column(name="claimant", nullable = false, updatable = false, columnDefinition = "VARCHAR(26)")
	private String claimant;
	
	@Column(name="examiner", nullable = true, updatable = true, columnDefinition = "VARCHAR(26)")
	private String examiner;
	
	@Column(name="filed_by", nullable = true, updatable = true, columnDefinition = "VARCHAR(26)")
	private String fileBy;
	
	@Column(name="amount_claimed", nullable = false, updatable = true, columnDefinition = "DECIMAL(13,2)")
	private Double amount;
	
	@Column(name="amount_paid", nullable = true, updatable = true, columnDefinition = "DECIMAL(13,2)")
	private Double paid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_filed", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	private Date DateFiled;
	
	@Convert(converter=StatusConverter.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "claim_status", nullable = false, length = 24, 
	columnDefinition = "VARCHAR(12) DEFAULT 'OPEN'")
	private Status status;
	
	
	

	public Claim() 
	{
		// TODO Auto-generated constructor stub
	}

	public Long getClaimId() {
		return claimId;
	}

	public void setClaimId(Long claimId) {
		this.claimId = claimId;
	}

	public String getClaimant() {
		return claimant;
	}

	public void setClaimant(String claimant) {
		this.claimant = claimant;
	}

	public String getExaminer() {
		return examiner;
	}

	public void setExaminer(String examiner) {
		this.examiner = examiner;
	}

	public String getFileBy() {
		return fileBy;
	}

	public void setFileBy(String fileBy) {
		this.fileBy = fileBy;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getPaid() {
		return paid;
	}

	public void setPaid(Double paid) {
		this.paid = paid;
	}

	public Date getDateFiled() {
		return DateFiled;
	}

	public void setDateFiled(Date dateFiled) {
		DateFiled = dateFiled;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

}
