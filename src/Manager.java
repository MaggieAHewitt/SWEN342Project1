import java.util.ArrayList;

public class Manager extends Thread{
	
	private static final long ARRIVAL_TIME = 0;
	private static final long DEPARTURE_TIME = 9*60*10;
	private long startTime;
	int Devs;
	private ArrayList<Employee> waiting;
	private ArrayList<Employee> leadDevs;
	private ArrayList<Employee> allDevs;
	Status status;
	
	public Manager(long time, int i) {
		Devs = i;
		waiting = new ArrayList<Employee>();
		leadDevs = new ArrayList<Employee>();
		startTime = time;
		allDevs = new ArrayList<Employee>();
	}

	public void run() {
		int startLunchtime = 4*60*10;
		int endLunchtime = 5*60*10;
		int morningMeetingStart = 2*60*10;
		int morningMeetingEnd = 3*60*10;
		int afternoonMeetingStart = 6*60*10;
		int afternoonMeetingEnd = 7*60*10;
		int groupMeetingStart = 8*60*10;

		printArrivalMessage(ARRIVAL_TIME);
		//Work until meeting happens

		while (leadDevs.size() < 3) {
			openForQuestions();
		}
		printStandupMeetingMessage(System.currentTimeMillis() - startTime);
		
		try {
			Thread.sleep(30*10);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}
		printBackToWorkMessage(System.currentTimeMillis() - startTime);
		
		while (System.currentTimeMillis() <= morningMeetingStart + startTime){
			openForQuestions();
		}
		printMorningMeetingMessage(System.currentTimeMillis() - startTime);
		
		//At 11AM Meeting
		while (System.currentTimeMillis() <= morningMeetingEnd + startTime ) {
			this.status = Status.WORKING;
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printBackToWorkMessage(System.currentTimeMillis() - startTime);

		while (System.currentTimeMillis() <= startLunchtime + startTime ) {
			openForQuestions();
		}
		printLunchMessage(System.currentTimeMillis() - startTime);

		while (System.currentTimeMillis() < endLunchtime + startTime ) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printBackToWorkMessage(System.currentTimeMillis() - startTime);

		while (System.currentTimeMillis() <= afternoonMeetingStart + startTime ) {
			openForQuestions();
		}
		printAfternoonMeetingMessage(System.currentTimeMillis() - startTime);

		while (System.currentTimeMillis() <= groupMeetingStart + startTime || allDevs.size() < Devs) {
			openForQuestions();
		}
		
		printStatusMeetingMessage(System.currentTimeMillis() - startTime);
		try {
			Thread.sleep(30*10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (allDevs.size() > 0) {
			allDevs.get(0).done();
			allDevs.remove(0);
		}
		printBackToWorkMessage(System.currentTimeMillis() - startTime);

		while (System.currentTimeMillis() < DEPARTURE_TIME + startTime ) {
			openForQuestions();
		}

		printDepartureMessage(System.currentTimeMillis() - startTime);
	}
	
	public synchronized void queue(Employee t) throws InterruptedException {
		waiting.add(t);
	}
	
	public synchronized void arrived(Employee i) {
		leadDevs.add(i);
	}
	
	public synchronized void groupMeetingArrival(Employee d) {
		allDevs.add(d);
	}
	
	private void openForQuestions() {
		if (waiting.isEmpty()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			try{
				printAnswerQMessage(System.currentTimeMillis() - startTime, waiting.get(0).empNumber);
				Thread.sleep(10*10);
				waiting.get(0).done();
				waiting.remove(0);
				
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
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
	
	private void printStandupMeetingMessage(long time) {
		System.out.println(getTime(time) + "  Manager meets for standup meeting!");
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

	private void printAnswerQMessage(long time, int i) {
		System.out.println(getTime(time) + "  Manager's answering Developer " + i + "'s question!");
	}
	
	private void printDepartureMessage(long time) {
		System.out.println(getTime(time) + "  Manager gracefully leaves for the day!");
	}

} 