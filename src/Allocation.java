//Allocation for 1 hour
public class Allocation {
	private CourseClass course_class;
	private Room room;
	private Lecturer lecture;
	private int time;
	private int day;
	private boolean isEmpty;
	
	public Allocation() {
		course_class = new CourseClass();
		room = new Room();
		lecture = new Lecturer();
		time = 0;
		day = 0;
		isEmpty = true;
	}
	
	public Allocation(int time_input, int day_input) {
		course_class = new CourseClass();
		room = new Room();
		lecture = new Lecturer();
		time = time_input;
		day = day_input;
		isEmpty = true;
	}

	public Allocation(CourseClass class_input, Room room_input, int time_input,int day_input) {
		course_class = new CourseClass(class_input);
		//System.out.printf("DOSEN %s", class_input.GetLecture(0));
		room = new Room(room_input);
		lecture = new Lecturer();
		time = time_input;
		day = day_input;
		isEmpty = false;
	}
	
	public Allocation(Allocation allocation_input) {
		course_class = new CourseClass(allocation_input.GetCourseClass());
		room = new Room(allocation_input.GetRoom());
		lecture = new Lecturer(allocation_input.GetLecturer());
		time = allocation_input.GetTime();
		day = allocation_input.GetDay();
		isEmpty = allocation_input.GetIsEmpty();
	}
	
	public CourseClass GetCourseClass() {
		return (course_class);
		/*CourseClass output = new CourseClass(course_class);
		return output;*/
	}
	
	public Room GetRoom() {
		return(room);
		/*Room output = new Room(room);
		return output;*/
	}
	public Lecturer GetLecturer() {
		return (lecture);
		/*Lecturer output = new Lecturer(lecture);
		return output;*/
	}
	
	public int GetTime() {
		return time;
	}
	public int GetDay() {
		return day;
	}
	
	public boolean GetIsEmpty() {
		return isEmpty;
	}
	
	public void SetCourseClass(CourseClass course_class_input) {
		course_class = new CourseClass(course_class_input);
	}
	
	public void SetRoom(Room room_input) {
		room = new Room(room_input);
	}
	
	public void SetTime(int time_input) {
		time = time_input;
	}
	
	public void SetIsEmpty(boolean isEmpty_input) {
		isEmpty = isEmpty_input;
	}
	
	public boolean Allocate(CourseClass class_input, Room room_input) {
		if (isEmpty) {
			course_class = new CourseClass(class_input);
			room = new Room(room_input);
			isEmpty = false;
			return (true);
		} else {
			return (false);
		}
	}
	
	public boolean IsEmpty() {
		return (isEmpty);
	}
	
	public void PrintAllocation() {
		//System.out.printf("Course : %s", course_class.GetCourseName());
		System.out.printf(" %d.00 Room %s Course : %s K %d Year : %d |\n", 
				time, room.GetRoomName(), course_class.GetCourseName(),
				course_class.GetClassID(), course_class.GetCourseYear());
	}
}
