
public class Facility {
	private String facility_name;
	private int count;
	
	public Facility() {
		facility_name = " ";
		count = 0;
	}
	
	public Facility (String name, int count_input) {
		facility_name = name;
		count = count_input;
	}
	
	public Facility (Facility facility_input) {
		facility_name = facility_input.GetFacilityName();
		count = facility_input.GetCount();
	}
	
	public String GetFacilityName() {
		return facility_name;
	}
	public int GetCount() {
		return count;
	}
	public void SetFacilityName(String facility_name_input) {
		facility_name = facility_name_input;
	}
	public void SetCount(int count_input) {
		count = count_input;
	}
}
