import java.util.ArrayList;

public class CourseClass {
	private int class_id;					//K1,K2,K3
	private int year;
	private String course_name;
	private ArrayList<Lecturer> lecture;
	//private Schedule preference_schedule;
	//private Schedule constraint_schedule;
	
	public CourseClass() {
		class_id = 1;
		year = 1;
		course_name = " ";
		lecture = new ArrayList<Lecturer>();
		//preference_schedule;
		//constraint_schedule= new Schedule();
	}
	
	public CourseClass(int class_id_input, String name_input, int yearInput,
			ArrayList<Lecturer> lecture_input, Schedule preference_schedule_input) {
		class_id = class_id_input;
		year = yearInput;
		course_name = name_input;
		lecture = new ArrayList<Lecturer>();
		for (int i=0;i<lecture_input.size();i++) {
			lecture.add(new Lecturer(lecture_input.get(i)));
		}
	}
	public CourseClass(int classId, ArrayList<Lecturer> lecture_input) {
		class_id = classId;
		year = 1;
		course_name = " ";
		lecture = new ArrayList<Lecturer>();
		for (int i=0;i<lecture_input.size();i++) {
			lecture.add(new Lecturer(lecture_input.get(i)));
		}
		//preference_schedule = new Schedule();
		//constraint_schedule= new Schedule();
	}
	
	public CourseClass(int class_id_input, ArrayList<Lecturer> lecture_input, Schedule preference_schedule_input) {
		class_id = class_id_input;
		year = 1;
		course_name = " ";
		lecture = new ArrayList<Lecturer>();
		for (int i=0;i<lecture_input.size();i++) {
			lecture.add(new Lecturer(lecture_input.get(i)));
		}
		//preference_schedule = new Schedule(preference_schedule_input.GetDaySize(), preference_schedule_input.GetHourSize(), preference_schedule_input.GetStartTime());
	}
	public CourseClass(int classIDInput, String nameInput, int yearInput) {
		class_id = classIDInput;
		year = yearInput;
		course_name = nameInput;
		lecture = new ArrayList<Lecturer>();
	}
	
	public CourseClass(CourseClass course_class_input) {
		class_id = course_class_input.GetClassID();
		year = course_class_input.GetCourseYear();
		course_name = course_class_input.GetCourseName();
		lecture = new ArrayList<Lecturer>();
		for (int i=0;i<course_class_input.GetLecture().size();i++) {
			lecture.add(new Lecturer(course_class_input.GetLecture().get(i)));
		}
		//preference_schedule = new Schedule(course_class_input.GetPreferenceSchedule());
	}
	
	public int GetClassID() {
		return class_id;
	}
	
	public ArrayList<Lecturer> GetLecture() {
		return (lecture);
		/*ArrayList<Lecturer> output = new ArrayList<Lecturer>();
		for (int i=0;i<lecture.size();i++) {
			output.add(new Lecturer(lecture.get(i)));
		}
		return output;*/
	}
	public Lecturer GetLecture(int index) {
		return (lecture.get(index));
	}
	
	/*public Schedule GetPreferenceSchedule() {
		Schedule output = new Schedule(preference_schedule);
		return output;
	}*/
	//public Schedule GetConstraintSchedule() {
	//	return constraint_schedule;
		/*Schedule output = new Schedule(constraint_schedule);
		return output;*/
	//}
	
	public String GetCourseName() {
		return course_name;
	}
	public int GetCourseYear() {
		return year;
	}
	
	public void SetClassID(int class_id_input) {
		class_id = class_id_input;
	}
	
	public void SetLecture(ArrayList<Lecturer> lecture_input) {
		lecture = new ArrayList<Lecturer>();
		for (int i=0;i<lecture_input.size();i++) {
			lecture.add(new Lecturer(lecture_input.get(i)));
		}
	}
	
	/*public void SetPreferenceSchedule(Schedule preference_schedule_input) {
		preference_schedule = new Schedule(preference_schedule_input);
	}*/

	public void PrintCourseClass() {
		//System.out.printf(arg0, arg1)
	}
	
}
