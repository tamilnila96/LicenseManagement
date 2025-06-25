package com.example.LicenseManagement.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.LicenseManagement.Entity.License;
@Repository

public interface LicenseRepo extends JpaRepository<License, Integer> {

	License findByLicenseKeyAndOrganisationEmail(String licenseKey, String organisationEmail);

}
