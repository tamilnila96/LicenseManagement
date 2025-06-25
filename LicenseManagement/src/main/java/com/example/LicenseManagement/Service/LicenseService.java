package com.example.LicenseManagement.Service;

import com.example.LicenseManagement.Entity.License;

public interface LicenseService {
	License create(License license);


	
	License getLicenseKeyAndOrganisationEmail(String licenseKey,String organisationEmail);
		
	}


