package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing BIOS information.
 * Contains vendor, version, and release date of the BIOS.
 */
public class BiosInfoDTO {
	/** BIOS vendor/manufacturer. */
	private String vendor;
	/** BIOS version. */
	private String version;
	/** BIOS release date. */
	private String releaseDate;

	/**
	 * Gets the BIOS vendor/manufacturer.
	 * @return vendor
	 */
	public String getVendor() { return vendor; }

	/**
	 * Sets the BIOS vendor/manufacturer.
	 * @param vendor BIOS vendor
	 */
	public void setVendor(String vendor) { this.vendor = vendor; }

	/**
	 * Gets the BIOS version.
	 * @return version
	 */
	public String getVersion() { return version; }

	/**
	 * Sets the BIOS version.
	 * @param version BIOS version
	 */
	public void setVersion(String version) { this.version = version; }

	/**
	 * Gets the BIOS release date.
	 * @return release date
	 */
	public String getReleaseDate() { return releaseDate; }

	/**
	 * Sets the BIOS release date.
	 * @param releaseDate BIOS release date
	 */
	public void setReleaseDate(String releaseDate) { this.releaseDate = releaseDate; }
}
