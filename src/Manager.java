public class Manager extends Thread{
	
	public Manager() {
		private static ARRIVAL_TIME = 0;
		private static DEPARTURE_TIME = 8*60*10;
	}

	public void run() {
		printArrivalMessage(ARRIVAL_TIME);
		
		//Other day things!

		printDepartureMessage(DEPARTURE_TIME);
	}
	
	private long Lunch() {
		int lunchtime = 4*60*10;
	}
	
	private long AnswerQuestion() {
		int timeSpent = 10*10;
	}
	
	private long AttendMorningMeeting() {
		int timeSpent = 15*10;
	}

	private long AttendLongMeeting() {
		int 60*10;
	}
	
	private String getTime(long time){
		String zero = "";
		long hours = time/600;
		long min = (time-(hours*600))/10;
		hours += 8;
		if(hours > 12){
			hours -= 12;
		}
		if(min < 10){
			zero = "0";
		}
		return hours + ":" + zero + min;
	}

	private void printArrivalMessage(long time){
		System.out.println(getTime(time) + "  Manager has arrived!");
	}
	
	private void printLunchMessage(long time) {
		System.out.println(getTime(time) + "Manager goes to Lunch!");
	}
	
	private void printAnswerQMessage(long time) {
		System.out.println(getTime(time) + "Manager's answering a question!");
	}
	
	private void printDepartureMessage(long time) {
		System.out.println(getTime(time) + "Manager gracefully leaves for the day!");
	}

} 