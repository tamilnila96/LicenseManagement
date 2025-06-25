package com.example.LicenseManagement.Entity;


import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="License")


public class License {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="organisation_name")
	@JsonProperty("organisationname")
	private String  organisationName;
	@Column(name="organisation_email")
	@JsonProperty("organisationemail")
	private String organisationEmail;
	@Column(name="status")
	private int status;
	@Column(name="licenseKey")
	@JsonProperty("licensekey")
	private String licenseKey;
	@Column(name="ActivationDate")
	@JsonProperty("activationdate")
	private LocalDate ActivationDate;
	@Column(name="GracePeriod")
	@JsonProperty("graceperiod")
	private int GracePeriod;
	@Column(name="ExpiryDate")
	@JsonProperty("expirydate")
	private LocalDate ExpiryDate;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	

	
	
	public String getOrganisationEmail() {
		return organisationEmail;
	}
	public void setOrganisationEmail(String organisationEmail) {
		this.organisationEmail = organisationEmail;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLicenseKey() {
		return licenseKey;
	}
	public void setLicenseKey(String licenseKey) {
		this.licenseKey = licenseKey;
	}
	public LocalDate getActivationDate() {
		return ActivationDate;
	}
	public void setActivationDate(LocalDate activationDate) {
		this.ActivationDate = activationDate;
	}
	public int getGracePeriod() {
		return GracePeriod;
	}
	public void setGracePeriod(int gracePeriod) {
		this.GracePeriod = gracePeriod;
	}
	public LocalDate getExpiryDate() {
		return ExpiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.ExpiryDate = expiryDate;
	}
	

}
