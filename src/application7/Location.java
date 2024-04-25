package application7;

import application6.SQueue;

public class Location implements Comparable<Location> {
	private SQueue<Martyr> martyrs = new SQueue<>();
	private String locationName;


	public Location(String locationName) {
		this.locationName = locationName;
	}


	public SQueue<Martyr> getMartyrs() {
		return martyrs;
	}
	public void insertMartyr(Martyr martyr) {
		this.martyrs.enqueue(martyr);
	}


	public String getLocationName() {
		return locationName;
	}


	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}


	@Override
	public String toString() {
		return "Location [locationName=" + locationName + ",martyrs=" + martyrs +"]\n";
	}


	@Override
	public int compareTo(Location o) {
		return this.locationName.compareToIgnoreCase(o.getLocationName());
	}

}

