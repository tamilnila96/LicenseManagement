package com.example.LicenseManagement.ServiceImplementation;

import java.security.SecureRandom;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LicenseManagement.Entity.License;
import com.example.LicenseManagement.Repository.LicenseRepo;
import com.example.LicenseManagement.Service.LicenseService;
@Service
public class LicenseServiceImpl implements LicenseService {
@Autowired
public LicenseRepo licenserepo;
	@Override
	public License create(License license) {
		String licensekey = generateSecureRandomString(15);
		license.setLicenseKey(licensekey);
		 System.out.println("Generated License Key: " + license.getLicenseKey());
		return licenserepo.save(license);
	}
	
	
	private String generateSecureRandomString(int length) {
		final String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		SecureRandom securerandom = new SecureRandom();
		StringBuilder sb = new StringBuilder(length);
		for(int i=0;i<length;i++) {
			sb.append(characters.charAt(securerandom.nextInt(characters.length())));
		}
		return sb.toString();
	}
	public License getLicenseKey(String licensekey) {
		return licenserepo.findByLicenseKey(licensekey);
	}

}
