import java.util.ArrayList;

public class Course {
	private int year;								//year1,year2,year3,year4
	private String class_name;						//Rekayasa Perangkat Lunak Spesifik Domain
	private int duration;							//1 hour,2 hours, 3 hours, 4 hours
	private int req_capacity;						//50
	private ArrayList<Facility> req_facility;		//Papan
	private ArrayList<CourseClass> course_class;	//K1,K2,K3
	
	public Course() {
		year = 0;
		class_name = " ";
		duration = 0;
		req_capacity = 0;
		req_facility = new ArrayList<Facility>();
		course_class = new ArrayList<CourseClass>();
	}
	public Course(int year_input, String class_name_input, int duration_input, 
			int req_capacity_input, int class_count, ArrayList<Facility> req_facility_input) {
		year = year_input;
		class_name = class_name_input;
		duration = duration_input;
		req_capacity = req_capacity_input;
		req_facility = new ArrayList<Facility>();
		for (int i = 0; i < req_facility_input.size(); i++) {
			req_facility.add(new Facility(req_facility_input.get(i)));
		}
		course_class = new ArrayList<CourseClass>();
		for (int i =0;i<class_count;i++) {
			course_class.add(new CourseClass(i + 1, class_name, year));
		}
	}
	
	public Course(int year_input, String class_name_input, int duration_input, 
			int req_capacity_input, int class_count) {
		year = year_input;
		class_name = class_name_input;
		duration = duration_input;
		req_capacity = req_capacity_input;
		req_facility = new ArrayList<Facility>();
		course_class = new ArrayList<CourseClass>();
		for (int i =0;i<class_count;i++) {
			course_class.add(new CourseClass(i + 1, class_name, year));
		}
	}
	
	public Course(int year_input, String class_name_input, int duration_input, 
			int req_capacity_input, ArrayList<Facility> req_facility_input, 
			ArrayList<CourseClass> course_class_input) {
		year = year_input;
		class_name = class_name_input;
		duration = duration_input;
		req_capacity = req_capacity_input;
		req_facility = new ArrayList<Facility>();
		for (int i = 0; i < req_facility_input.size(); i++) {
			req_facility.add(new Facility(req_facility_input.get(i)));
		}
		course_class = new ArrayList<CourseClass>();
		for (int i =0;i<course_class_input.size();i++) {
			course_class.add(new CourseClass(course_class_input.get(i)));
		}
	}
	
	public Course(Course course_input) {
		year = course_input.GetYear();
		class_name = course_input.GetClassName();
		duration = course_input.GetDuration();
		req_capacity = course_input.GetReqCapacity();
		req_facility = new ArrayList<Facility>();
		for (int i = 0; i < course_input.GetReqFacility().size(); i++) {
			req_facility.add(new Facility(course_input.GetReqFacility().get(i)));
		}
		course_class = new ArrayList<CourseClass>();
		for (int i=0;i<course_input.GetCourseClass().size();i++) {
			course_class.add(new CourseClass(course_input.GetCourseClass().get(i)));
		}
	}

	public int GetYear() { 
		return year;
	}
	
	public String GetClassName() {
		return class_name;
	}
	
	public int GetDuration() {
		return duration;
	}
	
	public int GetReqCapacity() {
		return req_capacity;
	}
	
	public ArrayList<Facility> GetReqFacility() {
		return req_facility;
		/*ArrayList<Facility> req_facility_output = new ArrayList<Facility>();
		for (int i = 0; i < req_facility.size(); i++) {
			req_facility_output.add(new Facility(req_facility.get(i)));
		}
		return req_facility_output;*/
	}
	
	public ArrayList<CourseClass> GetCourseClass() {
		return course_class;
/*		ArrayList<CourseClass> output = new ArrayList<CourseClass>();
		for (int i=0;i<course_class.size();i++) {
			output.add(new CourseClass(course_class.get(i)));
		}
		return output;*/
	}
	
	public void SetYear(int year_input) {
		year = year_input;
	}
	
	public void SetClassName(String class_name_input) {
		class_name = class_name_input;
	}
	
	public void SetDuration (int duration_input) {
		duration = duration_input;
	}
	
	public void SetReqCapacity(int req_capacity_input) {
		req_capacity = req_capacity_input;
	}
	
	public void SetReqFacility(ArrayList<Facility> req_facility_input) {
		req_facility = new ArrayList<Facility>();
		for (int i=0;i<req_facility_input.size();i++) {
			req_facility.add(new Facility(req_facility_input.get(i)));
		}
	}
	
	public void SetCourseClass(ArrayList<CourseClass> course_class_input) {
		course_class = new ArrayList<CourseClass>();
		for (int i=0;i<course_class_input.size();i++) {
			course_class.add(new CourseClass(course_class_input.get(i)));
		}
	}	
	
	public String toString() {
		return "Course " + this.class_name + ", year " + this.year + ", " 
	+ this.course_class.size() + " classes, " + this.course_class.get(0).GetLecture().size() 
	+ " lecturers, " + this.duration + " minute session.";
	}
	
	public void setCourseClassesLecturers(ArrayList<Lecturer> lecturers) {
		for (int i = 0; i < course_class.size(); i++) {
			course_class.get(i).SetLecture(lecturers);
		}
	}
}
