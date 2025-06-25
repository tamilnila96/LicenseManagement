package com.example.LicenseManagement.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.LicenseManagement.Entity.License;
import com.example.LicenseManagement.Service.LicenseService;


@RestController
@RequestMapping(value="/api")

public class LicenseController {
	@Autowired
	public LicenseService licenseservice;
	@PostMapping("/post")
	public License save(@RequestBody License license) {
		return licenseservice.create(license);
		
	}
	@GetMapping("/validate/{licenseKey}/{organisationEmail}")
	public License getLicenseInfo(
			@PathVariable ("licenseKey") String licenseKey,
			@PathVariable ("organisationEmail") String organisationEmail)  {
		return licenseservice.getLicenseKeyAndOrganisationEmail(licenseKey,organisationEmail);
		
	}
	

}
