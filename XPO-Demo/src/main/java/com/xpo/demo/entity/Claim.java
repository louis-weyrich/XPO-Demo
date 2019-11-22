package com.xpo.demo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;


@Entity(name = "Claim")
@Table(name = "claim")
@ApiModel(description = "All details about a Claim. ")
public class Claim 
{
	@Id
	@Column(name="claim_id", nullable = false, updatable = false)
	@ApiModelProperty(notes = "Claim ID")
	private Long claimId;
	
	@Column(name="claimant", nullable = false, updatable = false, columnDefinition = "VARCHAR(26)")
	@ApiModelProperty(notes = "The claimant of the Claim.")
	private String claimant;
	
	@Column(name="examiner", nullable = true, updatable = true, columnDefinition = "VARCHAR(26)")
	@ApiModelProperty(notes = "The examiner of the Claim.")
	private String examiner;
	
	@Column(name="filed_by", nullable = true, updatable = true, columnDefinition = "VARCHAR(26)")
	@ApiModelProperty(notes = "Who filed the Claim.")
	private String fileBy;
	
	@Column(name="amount_claimed", nullable = false, updatable = true, columnDefinition = "DECIMAL(13,2)")
	@ApiModelProperty(notes = "The Claim amoun.")
	private Double amount;
	
	@Column(name="amount_paid", nullable = true, updatable = true, columnDefinition = "DECIMAL(13,2)")
	@ApiModelProperty(notes = "The amount paid on the Claim.")
	private Double paid;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "date_filed", nullable = true, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	@DateTimeFormat(pattern = "MM-dd-yyyy")
	@ApiModelProperty(notes = "The date the Claim was filed.")
	private Date DateFiled;
	
	@Convert(converter=StatusConverter.class)
	@Enumerated(EnumType.STRING)
	@Column(name = "claim_status", nullable = false, length = 24, 
	columnDefinition = "VARCHAR(12) DEFAULT 'OPEN'")
	@ApiModelProperty(notes = "The status of the Claim.")
	private Status status;
	
	
	

	public Claim() 
	{
		// Do Nothing
	}
	
	

	public Claim(Long claimId, String claimant, String examiner, String fileBy, Double amount, Double paid,
			Date dateFiled, Status status) {
		super();
		this.claimId = claimId;
		this.claimant = claimant;
		this.examiner = examiner;
		this.fileBy = fileBy;
		this.amount = amount;
		this.paid = paid;
		DateFiled = dateFiled;
		this.status = status;
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
