package com.example.LicenseManagement.ServiceImplementation;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.LicenseManagement.Entity.License;
import com.example.LicenseManagement.Mail.MailService;
import com.example.LicenseManagement.Repository.LicenseRepo;
import com.example.LicenseManagement.Service.LicenseService;

@Service
public class LicenseServiceImpl implements LicenseService {
private static final String SECRET_KEY = "1234567890123456";
private static final String ALGORITHM = "AES";
@Autowired
public LicenseRepo licenserepo;
@Autowired
public MailService mailservice;

	@Override
	public License create(License license) {
		String licensekey = generateSecureRandomString(15);
		license.setLicenseKey(licensekey);
		 System.out.println("Generated License Key: " + license.getLicenseKey());
		 licenserepo.save(license);
		 String emailBody = "Your license key: " + licensekey +
	                "\nPlease keep this key safe and use it to activate your product.";
		 mailservice.sendEmail(license.getOrganisationEmail(), "Your License Key", emailBody);

	        return license;
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
	

	@Override
	public License getLicenseKeyAndOrganisationEmail(String licenseKey, String organisationEmail) {
		// TODO Auto-generated method stub
		 return licenserepo.findByLicenseKeyAndOrganisationEmail(licenseKey,organisationEmail);
	}
	
	@Override
	public String encrypt(String text) throws Exception {
		if (text == null) {
		        throw new IllegalArgumentException("License key to encrypt is null");
		}
		        SecretKeySpec secretkey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
		        
		    Cipher cipher = Cipher.getInstance(ALGORITHM);
		 cipher.init(Cipher.ENCRYPT_MODE, secretkey); 
		    System.out.println("Encrypting license key: " + text); byte[] encrypted = cipher.doFinal(text.getBytes());
	        String encoded =  Base64.getEncoder().encodeToString(encrypted);
	        System.out.println("original LicenseKey :  "+ text);
	       
			return encoded;
	}
	


	@Override
	public String decrypt(String encryptedText) throws Exception {
		SecretKeySpec secretkey = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
		Cipher cipher = Cipher.getInstance(ALGORITHM);
	    cipher.init(Cipher.DECRYPT_MODE, secretkey);
	    byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        String result =  new String(decrypted);
        System.out.println("EncryptedInput "+ encryptedText);
        System.out.println("decrypted"+ result);
		return result;
	}


	@Override
	public String ValidateandActivate(String encrypteLicenseKey, String organisationEmail) {
		try {
			String decryptKey = decrypt(encrypteLicenseKey);
			License license = licenserepo.findByLicenseKeyAndOrganisationEmail(decryptKey, organisationEmail);
			if(license==null) return "license not found";
			if(license.getStatus()==1) return"license already activated";
			license.setStatus(1);
			license.setActivationDate(LocalDate.now());
			license.setExpiryDate(LocalDate.now().plusDays(license.getGracePeriod()));
			licenserepo.save(license);
			mailservice.sendEmail(organisationEmail, "License Activated", "Your license key has been successfully activated.");

            return "License activated successfully.";
        } catch (Exception e) {
            return "Error during validation: " + e.getMessage();
        }
			
			}

}
