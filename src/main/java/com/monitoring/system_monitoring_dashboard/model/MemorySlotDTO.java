package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing a memory slot (RAM stick).
 * Contains manufacturer, part number, capacity, and clock speed.
 */
public class MemorySlotDTO {
	/** RAM manufacturer. */
	private String manufacturer;
	/** RAM part number or label. */
	private String partNumber;
	/** RAM capacity in megabytes. */
	private long capacityMB;
	/** RAM clock speed in MHz. */
	private long clockSpeedMHz;

	/**
	 * Gets the RAM manufacturer.
	 * @return manufacturer
	 */
	public String getManufacturer() { return manufacturer; }

	/**
	 * Sets the RAM manufacturer.
	 * @param manufacturer RAM manufacturer
	 */
	public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

	/**
	 * Gets the RAM part number or label.
	 * @return part number
	 */
	public String getPartNumber() { return partNumber; }

	/**
	 * Sets the RAM part number or label.
	 * @param partNumber RAM part number
	 */
	public void setPartNumber(String partNumber) { this.partNumber = partNumber; }

	/**
	 * Gets the RAM capacity in megabytes.
	 * @return capacity in MB
	 */
	public long getCapacityMB() { return capacityMB; }

	/**
	 * Sets the RAM capacity in megabytes.
	 * @param capacityMB RAM capacity in MB
	 */
	public void setCapacityMB(long capacityMB) { this.capacityMB = capacityMB; }

	/**
	 * Gets the RAM clock speed in MHz.
	 * @return clock speed in MHz
	 */
	public long getClockSpeedMHz() { return clockSpeedMHz; }

	/**
	 * Sets the RAM clock speed in MHz.
	 * @param clockSpeedMHz RAM clock speed in MHz
	 */
	public void setClockSpeedMHz(long clockSpeedMHz) { this.clockSpeedMHz = clockSpeedMHz; }
}
