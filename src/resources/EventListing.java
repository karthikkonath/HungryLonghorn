package resources;

public class EventListing {

	private int id;
	private String description;
	private String place;
	private String time;

	// Must have no-argument constructor
	public EventListing() {

	}

	public EventListing(String description, String place, String time) {
		this.description = description;
		this.place = place;
		this.time = time;
	}

	public void setDescription(String desc) {
		this.description = desc;
	}

	public String getDescription() {
		return this.description;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getPlace() {
		return this.place;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getTime() {
		return this.time;
	}
	
	@Override
	public String toString() {
		return new StringBuffer(" Description : ").append(this.description)
				.append(" Place : ").append(this.place)
				.append(" Time : ").append(this.time).toString();
	}
}
