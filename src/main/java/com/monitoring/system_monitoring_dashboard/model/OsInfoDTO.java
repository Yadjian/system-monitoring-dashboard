package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing operating system information.
 * Contains edition, version, install date, and uptime.
 */
public class OsInfoDTO {
	/** OS edition/family. */
	private String edition;
	/** OS version. */
	private String version;
	/** OS install date or boot time (epoch seconds). */
	private long installDate;
	/** OS uptime in seconds. */
	private long uptime;

	/**
	 * Gets the OS edition/family.
	 * @return edition
	 */
	public String getEdition() { return edition; }

	/**
	 * Sets the OS edition/family.
	 * @param edition OS edition
	 */
	public void setEdition(String edition) { this.edition = edition; }

	/**
	 * Gets the OS version.
	 * @return version
	 */
	public String getVersion() { return version; }

	/**
	 * Sets the OS version.
	 * @param version OS version
	 */
	public void setVersion(String version) { this.version = version; }

	/**
	 * Gets the OS install date or boot time (epoch seconds).
	 * @return install date
	 */
	public long getInstallDate() { return installDate; }

	/**
	 * Sets the OS install date or boot time (epoch seconds).
	 * @param installDate install date
	 */
	public void setInstallDate(long installDate) { this.installDate = installDate; }

	/**
	 * Gets the OS uptime in seconds.
	 * @return uptime
	 */
	public long getUptime() { return uptime; }

	/**
	 * Sets the OS uptime in seconds.
	 * @param uptime uptime in seconds
	 */
	public void setUptime(long uptime) { this.uptime = uptime; }
}
