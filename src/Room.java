import java.util.ArrayList;

public class Room {
	private String room_name;
	private int capacity;
	private ArrayList<Facility> facilities;
	//private Schedule used_schedule;
	
	public Room() {
		room_name = " ";
		capacity = 0;
		facilities = new ArrayList<Facility>();
	}
	
	public Room(String name) {
		room_name = name;
		capacity = 0;
		facilities = new ArrayList<Facility>();
	}
	public Room(String name, int capacity_input) {
		room_name = name;
		capacity = capacity_input;
		facilities = new ArrayList<Facility>();
	}
	public Room(String name, ArrayList<Facility> facility_input) {
		room_name = name;
		facilities = new ArrayList<Facility>();
		for (int i = 0; i <facility_input.size(); i++) {
			facilities.add(new Facility(facility_input.get(i)));
		}
	}
	
	public Room (Room room_input) {
		room_name = room_input.GetRoomName();
		capacity = room_input.GetCapacity();
		facilities = new ArrayList<Facility>();
		for (int i =0;i<room_input.GetFacilities().size();i++) {
			facilities.add(new Facility(room_input.GetFacilities().get(i)));
		}
	}
	
	public String GetRoomName () {
		return room_name;
	}
	
	public int GetCapacity() {
		return capacity;
	}
	
	public ArrayList<Facility> GetFacilities() {
		return facilities;
		/*ArrayList<Facility> output = new ArrayList<Facility>();
		for (int i=0;i<facilities.size();i++) {
			output.add(new Facility(facilities.get(i)));
		}
		return output;*/
	}
	
	public void SetRoomName(String room_name_input) {
		room_name = room_name_input;
	}
	
	public void SetCapacityName(int capacity_input) {
		capacity = capacity_input;
	}
	
	public void SetFacility(ArrayList<Facility> facility_input) {
		facilities = new ArrayList<Facility>();
		for (int i = 0; i <facility_input.size(); i++) {
			facilities.add(new Facility(facility_input.get(i)));
		}
	}
	public void SetCapacity(int capacity_input) {
		capacity = capacity_input;
	}
	
	public void PrintRoom() {
		System.out.printf("Room %s Capacity %d\n", room_name, capacity);
	}
}
