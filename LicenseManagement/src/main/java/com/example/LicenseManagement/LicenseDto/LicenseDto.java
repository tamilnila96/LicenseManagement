package com.example.LicenseManagement.LicenseDto;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data

public class LicenseDto {
	
		@Column(name="organisation_email")
		@JsonProperty("organisationemail")
		private String organisationEmail;
		@Column(name="licenseKey")
		@JsonProperty("licensekey")
		private String licenseKey;
		
		public String getOrganisationEmail() {
			return organisationEmail;
		}
		public void setOrganisationEmail(String organisationEmail) {
			this.organisationEmail = organisationEmail;
		}
		public String getLicenseKey() {
			return licenseKey;
		}
		public void setLicenseKey(String licenseKey) {
			this.licenseKey = licenseKey;
		}
		
	}

	


