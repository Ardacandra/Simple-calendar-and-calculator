package calendar;

public class day {
	private String name;
	private int date;
	private String task;
	
	public day(String name, int date) {
		this.name = name;
		this.date = date;
		this.task = null;
	}
	
	public String getName() {
		return this.name;
	}
	
	public int getDate() {
		return this.date;
	}
	
	public String getTask() {
		return this.task;
	}
	
	public void setTask(String task) {
		this.task = task;
	}
}
