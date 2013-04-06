/**
 * 
 */
package com.neodem.flyingCar;

/**
 * @author vfumo
 *
 */
public class VehicleId {
	private int id;
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return Integer.toString(id);
	}
	
	/**
	 * 
	 */
	public VehicleId(int id) {
		this.setId(id);
	}

	/**
	 * @param id the id to set
	 */
	private void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

}
