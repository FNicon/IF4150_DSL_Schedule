import java.util.ArrayList;

public class Lecturer {
	private String lecture_name;
	private ArrayList<Course> course;
	//private Schedule preference_schedule;
	
	public Lecturer() {
		lecture_name = " ";
		course = new ArrayList<Course>();
		//preference_schedule = new Schedule();
	}
	
	public Lecturer (String name) {
		lecture_name = name;
		course = new ArrayList<Course>();
		//preference_schedule = new Schedule();
	}
	
	public Lecturer (String name, ArrayList<Course> course_input) {
		lecture_name = name;		
		course = new ArrayList<Course>();
		for (int i = 0; i< course_input.size(); i++) {
			course.add(new Course(course_input.get(i)));
		}
		//preference_schedule = new Schedule();
	}
	
	public Lecturer (String name, Schedule preference_schedule_input) {
		lecture_name = name;
		course = new ArrayList<Course>();
		//preference_schedule = new Schedule(
		//		preference_schedule_input.GetDaySize(),preference_schedule_input.GetHourSize(),
		//		preference_schedule_input.GetStartTime());
	}
	
	public Lecturer (String name, ArrayList<Course> course_input, Schedule preference_schedule_input) {
		lecture_name = name;		
		course = new ArrayList<Course>();
		for (int i = 0; i < course_input.size(); i++) {
			course.add(new Course(course_input.get(i)));
		}
		//preference_schedule = new Schedule(preference_schedule_input);
	}
	
	public Lecturer(Lecturer lecturer_input) {
		lecture_name = lecturer_input.GetLectureName();
		course = new ArrayList<Course>();
			for (int i = 0; i < lecturer_input.GetCourse().size();i++) {
				course.add(lecturer_input.GetCourse().get(i));
			}
		//preference_schedule = new Schedule(lecturer_input.GetPreferenceSchedule());
	}
	
	public String GetLectureName() {
		return lecture_name;
	}
	
	public ArrayList<Course> GetCourse() {
		return course; 
	}
	
	//public Schedule GetPreferenceSchedule() {
		//return preference_schedule;
		/*Schedule output = new Schedule(preference_schedule);
		return output;*/
	//}
	
	public void SetLectureName(String lecture_name_input) {
		lecture_name = lecture_name_input;
	}
	
	public void SetCourse(ArrayList<Course> course_input) {
		course = new ArrayList<Course>();
		for (int i = 0; i< course_input.size(); i++) {
			course.add(new Course(course_input.get(i)));
		}
	}
	
	//public void SetPreferenceSchedule(Schedule preference_schedule_input) {
	//	preference_schedule = new Schedule(preference_schedule_input);		
	//}
	
	public void addCourse(Course input) {
		course.add(input);
	}
	
	/**
	 * For debugging purposes
	 */
	public void printCoursesNames() {
		for (int i = 0; i < course.size(); i++) {
			System.out.println(course.get(i).GetClassName());
		}
	}
}
