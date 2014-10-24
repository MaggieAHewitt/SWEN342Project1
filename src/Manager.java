public class Manager extends Thread{
	
	public Manager() {
		private static ARRIVAL_TIME = 0;
		private static DEPARTURE_TIME = 9*60*10;
		Status status;
	}

	public void run() {
		int startLunchtime = 4*60*10;
		int endLunchtime = 5*60*10;
		int questionTime = 10*10;
		int morningMeetingStart = 2*60*10;
		int morningMeetingEnd = 3*60*10;
		int afternoonMeetingStart = 6*60*10;
		int afternoonMeetingEnd = 7*60*10;
		int groupMeetingStart = 8*60*10;

		printArrivalMessage(ARRIVAL_TIME);
		
		//Work until meeting happens

		while (System.currentTimeMillis() <= morningMeetingStart + ARRIVAL_TIME) {
			//check for question to be answered
			//work for 10ms
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		//At 11AM Meeting
		while (System.currentTimeMillis() <= morningMeetingEnd + ARRIVAL_TIME) {
			try {
				Thread.sleep(10);
			} catch (InterruptedExcetion e) {
				e.printStackTrace();
			}
		}

		while (System.currentTimeMillis() <= Lunch + ARRIVAL_TIME) {
			//check for question to be answered
			//else work for 10ms
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printLunchMessage(System.currentTimeMillis());

		while (System.currentTimeMillis() < endLunchtime + ARRIVAL_TIME) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printEndLunchMessage(System.currentTimeMillis);

		while (System.currentTimeMillis() <= afternoonMeetingStart + ARRIVAL_TIME) {
			//Check for question to be answered
			//work for 10ms
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printAfternoonMeetingMessage(System.currentTimeMillis);

		while (System.currentTimeMillis() <= groupMeetingStart + ARRIVAL_TIME) {
			//check for question to be answered
			//check for 4PM (8*60*10)
			//else work for 10ms
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		while (System.currentTimeMillis() < DEPARTURE_TIME + ARRIVAL_TIME) {
			//Check for question to be answered
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		printDepartureMessage(DEPARTURE_TIME);
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
		System.out.println(getTime(time) + "  Manager goes to Lunch!");
	}
	
	private void printMorningMeetingMessage (long time) {
		System.out.println(getTime(time) + "  Manager goes to morning meeting!");
	}

	private void printAfternoonMeetingMessage (long time) {
		System.out.println(getTime(time) + "  Manager goes to Afternoon Meeting!");
	}

	private void printStatusMeetingMessage(long time) {
		System.out.println(getTime(time) + "  Manager goes to Status Meeting");
	}

	private void printBackToWorkMessage (long time) {
		System.out.println(getTime(time) + "  Manager goes back to work!");
	}

	private void printAnswerQMessage(long time) {
		System.out.println(getTime(time) + "  Manager's answering a question!");
	}
	
	private void printDepartureMessage(long time) {
		System.out.println(getTime(time) + "  Manager gracefully leaves for the day!");
	}

} 