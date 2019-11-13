package calendar;

public class month {
	String[] dayNames = {"Minggu", "Senin", "Selasa", "Rabu", "Kamis", "Jumat", "Sabtu"};
	private String name;
	private int dayNumber;
	private int startingDay;
	private day[] dayArr;
	
	public month(String name, int dayNumber, int startingDay) {
		this.name = name;
		this.dayNumber = dayNumber;
		this.startingDay = startingDay;
		this.dayArr = new day[dayNumber];
		for(int i=0 ; i<dayNumber ; i++) {
			dayArr[i] = new day(dayNames[startingDay], i+1);
			if(startingDay == 6) {
				startingDay = 0;
			} else {
				startingDay++;
			}
		}
	}
	
	public String getName() {
		return this.name;
	}

	public day getDay(int date) {
		return this.dayArr[date-1];
	}
	
	public int getDayNumber() {
		return this.dayNumber;
	}
	
	public int getStartingDay() {
		return this.startingDay;
	}
}
