package com.monitoring.system_monitoring_dashboard.model;

/**
 * Data Transfer Object representing PC information.
 * Contains manufacturer, model, and motherboard information.
 */
public class PcInfoDTO {
	/** PC manufacturer. */
	private String manufacturer;
	/** PC model. */
	private String model;
	/** Motherboard model. */
	private String motherboard;

	/**
	 * Gets the PC manufacturer.
	 * @return manufacturer
	 */
	public String getManufacturer() { return manufacturer; }

	/**
	 * Sets the PC manufacturer.
	 * @param manufacturer PC manufacturer
	 */
	public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

	/**
	 * Gets the PC model.
	 * @return model
	 */
	public String getModel() { return model; }

	/**
	 * Sets the PC model.
	 * @param model PC model
	 */
	public void setModel(String model) { this.model = model; }

	/**
	 * Gets the motherboard model.
	 * @return motherboard model
	 */
	public String getMotherboard() { return motherboard; }

	/**
	 * Sets the motherboard model.
	 * @param motherboard motherboard model
	 */
	public void setMotherboard(String motherboard) { this.motherboard = motherboard; }
}
