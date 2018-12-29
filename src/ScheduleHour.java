import java.util.ArrayList;

//Schedule 1 Hour
public class ScheduleHour {
	private ArrayList<Allocation> allocations;
	private int allocation_counter;
	private int time;
	private int day;
	
	public ScheduleHour(int time_input, int day_input) {
		allocation_counter = 0;
		time = time_input;
		day = day_input;
		allocations = new ArrayList<Allocation>();
		allocations.add(new Allocation(time, day));
	}
	
	public ArrayList<Allocation> GetAllocations() {
		return allocations;
		/*ArrayList<Allocation> output = new ArrayList<Allocation>();
		for (int i=0;i<allocations.size();i++) {
			output.add(allocations.get(i));
		}
		return output;*/
	}
	
	/*public int GetAllocationCounter() {
		return allocation_counter;
	}*/
	
	public int GetTime() {
		return time;
	}
	
	public void SetAllocations(ArrayList<Allocation> allocations_input) {
		allocations = new ArrayList<Allocation>();
		for (int i=0;i<allocations_input.size();i++) {
			allocations.add(new Allocation(allocations_input.get(i)));
		}
	}
	
	/*public void SetAllocationCounter(int allocation_counter_input) {
		allocation_counter = allocation_counter_input;
	}*/
	
	public void SetTime(int time_input) {
		time = time_input;
	}
	
	public void AddSchedule(CourseClass class_input, Room room_input) {
		allocations.set(allocation_counter, new Allocation(class_input, room_input,time, day));
		allocations.add(new Allocation(time, day));
		allocation_counter = allocation_counter + 1;
	}
	
	/*private boolean IsAllowed(CourseClass class_input, Room room_input) {
		int i = 0;
		while ((i < allocations.size())
				&& ((allocations.get(i).GetRoom().GetRoomName() != room_input.GetRoomName()
				&& (
						(allocations.get(i).GetCourseClass().GetClassID() != class_input.GetClassID())
						&& (allocations.get(i).GetCourseClass().GetCourseYear() != class_input.GetCourseYear())
				)))) {
			i = i + 1;
		}
		return (i >=allocations.size());
	}*/
	/*private boolean IsNotAllowed(
			CourseClass checkedClass, Room checkedRoom, CourseClass class_input, Room room_input) {
		return (checkedRoom.GetRoomName() == room_input.GetRoomName()
				&& (
						(checkedClass.GetClassID() == class_input.GetClassID())
						&& (checkedClass.GetCourseYear() == class_input.GetCourseYear())
					)
				);
	}
	private boolean IsClassAllowed(CourseClass checkedClass, CourseClass class_input) {
		return (checkedClass.GetCourseYear() != class_input.GetCourseYear());
	}*/
	
	public boolean IsEmpty(int index) {
		return (allocations.get(index).IsEmpty());
	}
	
	public void PrintHour() {
		for (int i = 0; i < allocations.size();i++) {
			allocations.get(i).PrintAllocation();
		}
	}
}
