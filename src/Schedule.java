import java.util.ArrayList;

public class Schedule {
	private ArrayList<ArrayList<ScheduleHour>> schedule_days;
	private int day_size;
	private int hour_size;
	private int start_time;
	
	public Schedule() {
		day_size = 5;
		hour_size = 11;
		start_time = 7;
		
		schedule_days = new ArrayList<ArrayList<ScheduleHour>>();
		for (int d = 0; d < day_size; d++) {
			schedule_days.add(new ArrayList<ScheduleHour>());
			for (int h = 0; h < hour_size; h++) {
				schedule_days.get(d).add(new ScheduleHour(start_time + h, d));
			}
		}
	}
	
	public Schedule(int day, int hour, int start) {
		day_size = day;
		hour_size = hour;
		start_time = start;
		
		schedule_days = new ArrayList<ArrayList<ScheduleHour>>();
		for (int d = 0; d < day_size; d++) {
			schedule_days.add(new ArrayList<ScheduleHour>());
			for (int h = 0; h < hour_size; h++) {
				schedule_days.get(d).add(new ScheduleHour(start_time + h, d));
			}
		}
	}
	
	public Schedule(Schedule schedule_input) {
		day_size = schedule_input.GetDaySize();
		hour_size = schedule_input.GetHourSize();
		start_time = schedule_input.GetStartTime();
		
		schedule_days = new ArrayList<ArrayList<ScheduleHour>>();
		for (int d = 0; d < day_size; d++) {
			schedule_days.add(new ArrayList<ScheduleHour>());
			for (int h = 0; h < hour_size; h++) {
				schedule_days.get(d).add(new ScheduleHour(start_time + h, d));
			}
		}
	}
	
	public void PrintSchedule() {
		for (int d = 0; d < day_size; d++) {
			System.out.printf("Day : %d \n", d);
			for (int h = 0; h < hour_size; h++) {
				System.out.print("===============\n");
				schedule_days.get(d).get(h).PrintHour();
			}
		}
	}
	
	public void Allocate(int day, int hour, CourseClass classInput, Room roomInput) {
		schedule_days.get(day).get(hour).AddSchedule(classInput, roomInput);
	}
	
	public int GetDaySize() {
		return day_size;
	}
	
	public int GetHourSize() {
		return hour_size;
	}
	
	public int GetStartTime() {
		return start_time;
	}
	
	public void SetDaySize(int day_size_input) {
		day_size = day_size_input;
	}
	
	public void SetHourSize(int hour_size_input) {
		hour_size = hour_size_input;
	}
	
	public void SetStartTime(int start_time_input) {
		start_time = start_time_input;
	}
}
