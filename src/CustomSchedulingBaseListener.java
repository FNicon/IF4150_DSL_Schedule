import java.util.ArrayList;

import org.antlr.v4.runtime.misc.NotNull;

public class CustomSchedulingBaseListener extends SchedulingBaseListener {
	private ArrayList<Course> courses;
	private ArrayList<Lecturer> lecturers;
	private ArrayList<Room> rooms;
	
	private int courses_counter;
	private int lecturers_counter;
	private int rooms_counter;
	
	private Schedule schedule;
	
	public CustomSchedulingBaseListener() {
		courses_counter = 0;
		lecturers_counter = 0;
		rooms_counter = 0;
		
		courses = new ArrayList<Course>();
		lecturers = new ArrayList<Lecturer>();
		rooms = new ArrayList<Room>();
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitCreate(SchedulingParser.CreateContext ctx) {
		SchedulingParser.EntityContext context = ctx.entity();
		ArrayList<String> currentLecturersName = new ArrayList<>();
		for (int i = 0; i < lecturers.size(); i++) {
			currentLecturersName.add(lecturers.get(i).GetLectureName());
		}
		
		if (context.getChild(0).getChild(0).toString().equals("schedule")) {
			SchedulingParser.Schedule_propertyContext schedule_property_context = context.schedule_property();
			int day_size = Integer.parseInt(
					schedule_property_context.schedule_day().NUM().getText());
			int hour_size = Integer.parseInt(
					schedule_property_context.schedule_hour().NUM().getText());
			int start_time = Integer.parseInt(
					schedule_property_context.schedule_start().NUM().getText());
			schedule = new Schedule(day_size,hour_size, start_time);
			//schedule.PrintSchedule();
		} else if (context.getChild(0).getChild(0).toString().equals("room")) {
			rooms.add(new Room (context.room().ID().getText()));
			
			if (!context.room_property().room_capacity().isEmpty()) {
				rooms.get(rooms_counter).SetCapacity(Integer.parseInt(
						context.room_property().room_capacity().NUM().getText()
						));
			}
			
			ArrayList<Facility> facilities = new ArrayList<>();
			for (int i = 0; i < context.room_property().facility().size(); i++) {
				facilities.add(new Facility(
						context.room_property().facility().get(i).ID().getText(),
						Integer.parseInt(context.room_property().facility().get(i).NUM().getText())
						));
			}
			if (!context.room_property().facility().isEmpty()) {
				rooms.get(rooms_counter).SetFacility(facilities);
			}
			rooms.get(rooms_counter).PrintRoom();
			rooms_counter = rooms_counter + 1;
		} else if (context.getChild(0).getChild(0).toString().equals("course")) {
			if (context.course_property().facility().size() != 0) {
				ArrayList<Facility> facilities = new ArrayList<>();
				for (int i = 0; i < context.course_property().facility().size(); i++) {
					facilities.add(new Facility(
							context.course_property().facility().get(i).ID().getText(),
							Integer.parseInt(context.course_property().facility().get(i).NUM().getText())
							));
				}
				
				ArrayList<Lecturer> lecturers = new ArrayList<>();
				for (int i = 0; i < context.course_property().lecturer().size(); i++) {
					if (!currentLecturersName.contains(context.course_property().lecturer().get(i).getText())) {
						lecturers.add(new Lecturer(context.course_property().lecturer().get(i).getText()));	
					}
				}
				
				courses.add(new Course(Integer.valueOf(context.course_property().course_year().NUM().getText()).intValue(),
						context.course().ID().getText(),
						Integer.valueOf(context.course_property().course_duration().NUM().getText()).intValue(),
						Integer.valueOf(context.course_property().course_capacity().NUM().getText()).intValue(),
						Integer.valueOf(context.course_property().course_class().NUM().getText()).intValue(),
						facilities
						));
			} else {
				ArrayList<Lecturer> lecturers = new ArrayList<>();
				for (int i = 0; i < context.course_property().lecturer().size(); i++) {
					if (!currentLecturersName.contains(context.course_property().lecturer().get(i).getText())) {
						lecturers.add(new Lecturer(context.course_property().lecturer().get(i).getText()));	
					}
				}
				
				courses.add(new Course(Integer.valueOf(context.course_property().course_year().NUM().getText()).intValue(),
						context.course().ID().getText(),
						Integer.valueOf(context.course_property().course_duration().NUM().getText()).intValue(),
						Integer.valueOf(context.course_property().course_capacity().NUM().getText()).intValue(),
						Integer.valueOf(context.course_property().course_class().NUM().getText()).intValue()//,
						//facilities
						));
				//courses.add(new Course());
			}
			
			System.out.println(courses.get(courses.size()-1).toString());
		}/* else if (!context.course().isEmpty()) {
			
		} else if (!context.lecturer().isEmpty()) {
			
		}*/
		//schedule = new Schedule(schedule_name);
	}
	
	@Override public void exitLecturer(SchedulingParser.LecturerContext ctx) {
		String operation = ctx.getParent().getParent().getChild(0).getText();
		if (operation.equals("create")) {
			String lecturerName = ctx.getChild(1).getText();
			
			SchedulingParser.Lecturer_propertyContext lecturerProperties = ctx.lecturer_property();
			
			ArrayList<Course> lecturerCourses = new ArrayList<Course>();
			for (int i = 0; i < lecturerProperties.getChildCount(); i++) {
				String propertyType = lecturerProperties.getChild(i).getChild(0).getText();
				if (propertyType.equals("course")) {
					Course lecturerCourse = new Course();
					lecturerCourse.SetClassName(lecturerProperties.getChild(i).getChild(1).getText());
					lecturerCourses.add(lecturerCourse);
				}
			}
			
			Lecturer addedLecturer = new Lecturer(lecturerName, lecturerCourses, new Schedule());
			lecturers.add(addedLecturer);
			printLecturers();
		}
	}
	
	@Override public void exitAllocate(@NotNull SchedulingParser.AllocateContext ctx) {
		SchedulingParser.RoomContext roomContext = ctx.room();
		SchedulingParser.CourseContext courseContext = ctx.course();
		SchedulingParser.Class_numContext classNUMContext = ctx.class_num();
		SchedulingParser.Allocate_propertyContext allocatePropertyContext = ctx.allocate_property();
		
		String roomName = roomContext.ID().getText();
		String courseName = courseContext.ID().getText();
		int roomIdx = GetRoomIdx(roomName);
		int courseIdx = GetCourseIdx(courseName);
		
		int day = Integer.parseInt(allocatePropertyContext.schedule_day().NUM().getText());
		int hour = Integer.parseInt(allocatePropertyContext.schedule_hour().NUM().getText());
		int courseClass = Integer.parseInt(classNUMContext.NUM().getText());
		
		//System.out.printf("%d %d %d", roomIdx, courseIdx, courseClass);
		if ((roomIdx != -1) && (courseIdx != -1)) {
			if (rooms.get(roomIdx).GetCapacity() >= courses.get(courseIdx).GetReqCapacity()) {
				if (IsFacilitiesFullfilled(rooms.get(roomIdx).GetFacilities(), 
						courses.get(courseIdx).GetReqFacility())) {
					if (courses.get(courseIdx).GetReqFacility().size() > 0) {
						System.out.print(courses.get(courseIdx).GetReqFacility().get(0).GetFacilityName());
						//System.out.print(rooms.get(courseIdx).GetFacilities().get(0).GetFacilityName());
					}
					schedule.Allocate(
							day, hour, courses.get(courseIdx).GetCourseClass().get(courseClass), 
							rooms.get(roomIdx));
					schedule.PrintSchedule();
				} else {
					System.out.printf("room %s facility not fullfilled!\n", rooms.get(roomIdx).GetRoomName());
				}
			} else {
				System.out.printf("room %s capacity overload!\n", rooms.get(roomIdx).GetRoomName());
			}
		} else {
			System.out.print("room or course have not been declared!\n");
		}
	}
	/**
	 * {@inheritDoc}
	 *
	 * <p>The default implementation does nothing.</p>
	 */
	@Override public void exitSet(SchedulingParser.SetContext ctx) {
		SchedulingParser.EntityContext context = ctx.entity();
		
		ArrayList<String> currentLecturersName = new ArrayList<>();
		for (int i = 0; i < lecturers.size(); i++) {
			currentLecturersName.add(lecturers.get(i).GetLectureName());
		}
		
		boolean isPresent = false;
		int idxRoom = 0;
		
		if (context.getChild(0).getChild(0).toString().equals("schedule")) {
			SchedulingParser.Schedule_propertyContext schedule_property_context = context.schedule_property();
			int day_size = Integer.parseInt(
					schedule_property_context.schedule_day().NUM().getText());
			int hour_size = Integer.parseInt(
					schedule_property_context.schedule_hour().NUM().getText());
			int start_time = Integer.parseInt(
					schedule_property_context.schedule_start().NUM().getText());
			schedule = new Schedule(day_size,hour_size, start_time);
			schedule.PrintSchedule();
		} else if (context.getChild(0).getChild(0).toString().equals("room")) {
			if(rooms.isEmpty()) {
				System.out.println("Tidak ada ruangan yang tersimpan");
			} else {				
				for (int i=0;i<rooms.size();i++) {
					if(rooms.get(i).GetRoomName().equals(context.room().ID().getText())) {
						isPresent = true;
						idxRoom = i;
					}
				}
				if (isPresent) {
					System.out.println("Ruangan " + context.room().ID().getText() + " ada");
					rooms.get(idxRoom).SetCapacity(Integer.parseInt(
					context.room_property().room_capacity().NUM().getText()
					));
					
					ArrayList<Facility> facilities = new ArrayList<>();
					for (int i = 0; i < context.room_property().facility().size(); i++) {
						facilities.add(new Facility(
								context.room_property().facility().get(i).ID().getText(),
								Integer.parseInt(context.room_property().facility().get(i).NUM().getText())
								));
					}
					if (!context.room_property().facility().isEmpty()) {
						rooms.get(idxRoom).SetFacility(facilities);
					}
					rooms.get(idxRoom).PrintRoom();
				} else {
					System.out.println("Ruangan " + context.room().ID().getText() + " tidak ada");
				}
			}
		} else if (context.getChild(0).getChild(0).toString().equals("course")) {
			if (context.course_property().facility().size() != 0) {
				ArrayList<Facility> facilities = new ArrayList<>();
				for (int i = 0; i < context.course_property().facility().size(); i++) {
					facilities.add(new Facility(
							context.course_property().facility().get(i).ID().getText(),
							Integer.parseInt(context.course_property().facility().get(i).NUM().getText())
							));
				}
				
				ArrayList<Lecturer> lecturers = new ArrayList<>();
				for (int i = 0; i < context.course_property().lecturer().size(); i++) {
					if (!currentLecturersName.contains(context.course_property().lecturer().get(i).getText())) {
						lecturers.add(new Lecturer(context.course_property().lecturer().get(i).getText()));	
					}
				}
				
				int i = 0;
				String toBeFound = new String(context.course().ID().getText());
				while (i < courses.size() && !toBeFound.equals(courses.get(i).GetClassName())) {
					i++;
				}
				
				if (i >= courses.size()) {
					System.out.println("Course with name " + toBeFound + " not found!");
				} else {
					courses.get(i).SetYear(Integer.valueOf(context.course_property().course_year().NUM().getText()).intValue());
					courses.get(i).SetDuration(Integer.valueOf(context.course_property().course_duration().NUM().getText()).intValue());
					courses.get(i).SetReqCapacity(Integer.valueOf(context.course_property().course_capacity().NUM().getText()).intValue());
					courses.get(i).SetReqFacility(facilities);
					courses.get(i).setCourseClassesLecturers(lecturers);
				}				
			} else {
				ArrayList<Lecturer> lecturers = new ArrayList<>();
				for (int i = 0; i < context.course_property().lecturer().size(); i++) {
					if (!currentLecturersName.contains(context.course_property().lecturer().get(i).getText())) {
						lecturers.add(new Lecturer(context.course_property().lecturer().get(i).getText()));	
					}
				}
				
				int i = 0;
				String toBeFound = new String(context.course().ID().getText());
				while (i < courses.size() && !toBeFound.equals(courses.get(i).GetClassName())) {
					i++;
				}
				
				if (i >= courses.size()) {
					System.out.println("Course with name " + toBeFound + " not found!");
				} else {
					courses.get(i).SetYear(Integer.valueOf(context.course_property().course_year().NUM().getText()).intValue());
					courses.get(i).SetDuration(Integer.valueOf(context.course_property().course_duration().NUM().getText()).intValue());
					courses.get(i).SetReqCapacity(Integer.valueOf(context.course_property().course_capacity().NUM().getText()).intValue());
					//courses.get(i).setFacilities(facilities);
					courses.get(i).setCourseClassesLecturers(lecturers);
				}
			}
			
			System.out.println(courses.get(courses.size()-1).toString());
		}
	}
	
	public int GetCourseIdx(String courseName) {
		int i = 0;
		while((i < courses.size()) && (!courses.get(i).GetClassName().equals(courseName))) {
			i = i + 1;
		}
		if (i < courses.size()) {
			return (i);
		} else {
			return (-1);
		}
	}
	public int GetLecturerIdx(String lectureName) {
		int i = 0;
		while((i < lecturers.size()) && (!lecturers.get(i).GetLectureName().equals(lectureName))) {
			i = i + 1;
		}
		if (i < lecturers.size()) {
			return (i);
		} else {
			return (-1);
		}
	}
	public int GetRoomIdx(String roomName) {
		int i = 0;
		while((i < rooms.size()) && (!rooms.get(i).GetRoomName().equals(roomName))) {
			i = i + 1;
		}
		if (i < rooms.size()) {
			return (i);
		} else {
			return (-1);
		}
	}
	public boolean IsFacilitiesFullfilled(ArrayList<Facility> roomFacilities, 
			ArrayList<Facility> courseFacilities) {
		int i = 0;
		int j = 0;
		boolean isFullfilled = true;
		while (i < courseFacilities.size() && isFullfilled) {
			while (j < roomFacilities.size() && isFullfilled) {
				if (courseFacilities.get(i).GetFacilityName().
						equals(roomFacilities.get(j).GetFacilityName())) {
					if (courseFacilities.get(i).GetCount() 
							> roomFacilities.get(j).GetCount()) {
						isFullfilled = false;
					}
				}
				j = j + 1;
			}
			i = i + 1;
		}
		return isFullfilled;
	}
	
	/**
	 * For debugging purposes
	 */
	public void printLecturers() {
		for (int i = 0; i < lecturers.size(); i++) {
			System.out.println(lecturers.get(i).GetLectureName());
			lecturers.get(i).printCoursesNames();
		}
	}
}
