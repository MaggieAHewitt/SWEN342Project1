import java.util.ArrayList;

public class Manager extends Thread{
	
	private static final long ARRIVAL_TIME = 0;
	private static final long DEPARTURE_TIME = 9*60*10;
	private long startTime;
	private ArrayList<Integer> waiting;
	Status status;
	
	public Manager(long time) {
		waiting = new ArrayList<Integer>();
		startTime = time;
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

		while (System.currentTimeMillis() <= morningMeetingStart + startTime) {
			openForQuestions();
		}

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

		while (System.currentTimeMillis() <= startLunchtime + startTime ) {
			openForQuestions();
		}
		printLunchMessage(System.currentTimeMillis());

		while (System.currentTimeMillis() < endLunchtime + startTime ) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printBackToWorkMessage(System.currentTimeMillis());

		while (System.currentTimeMillis() <= afternoonMeetingStart + startTime ) {
			openForQuestions();
		}
		printAfternoonMeetingMessage(System.currentTimeMillis());

		while (System.currentTimeMillis() <= groupMeetingStart + startTime ) {
			openForQuestions();
		}

		while (System.currentTimeMillis() < DEPARTURE_TIME + startTime ) {
			openForQuestions();
		}

		printDepartureMessage(DEPARTURE_TIME);
	}
	
	public synchronized void queue(int i) throws InterruptedException {
		waiting.add(i);
		System.out.println(waiting);
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
				System.out.println("Answering question!");
				Thread.sleep(10*10);
				waiting.remove(0);
				//notify dev to go back to work
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