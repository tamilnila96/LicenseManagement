package com.example.LicenseManagement.Service;

import com.example.LicenseManagement.Entity.License;

public interface LicenseService {
	License create(License license);


	
	License getLicenseKey(String licenseKey);
		
	}


