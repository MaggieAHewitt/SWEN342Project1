import java.util.ArrayList;
import java.util.Random;


public class Employee extends Thread {
	
	private static int EIGHT_HOURS = 60*8*10;
	private int questionChance;
	private long startTime = 0;
	private long timeWorked = 0;
	
	private Random r = new Random();
	
	int teamNumber;
	int empNumber;
	Status status;
	Manager man;
	Employee lead;
	boolean answered;
	ArrayList<Employee> waiting;
	
	public Employee(Manager man, long t, int tn, int en, int qc){
		startTime = t;
		teamNumber = tn;
		empNumber = en;
		this.man = man;
		answered = false;
		questionChance = qc;
		waiting = new ArrayList<Employee>();
	}
	
	public Employee(Manager man, Employee l, long t, int tn, int en, int qc){
		startTime = t;
		teamNumber = tn;
		empNumber = en;
		this.man = man;
		this.lead = l;
		answered = false;
		questionChance = qc;
	}
	
	public void run(){
		long arrivalTime = getArrivalTime();
		long lunchStartTime = getLunchStartTime();
		long lunchEndTime = getLunchEndTime(lunchStartTime);
		long leaveTime = getLeavingTime(arrivalTime, lunchStartTime, lunchEndTime);
		
		
		while(System.currentTimeMillis() <= (arrivalTime + startTime)){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		printArrivalMessage(arrivalTime);
		
		if(isLead()){
			man.arrived(this);
		}
	
		while(System.currentTimeMillis() <= (lunchStartTime + startTime)){
			try {
				if(isLead()){
					openForQuestions();
				}
				if(!isLead() && askQuestion()){
					printAskingQuestionMessage(System.currentTimeMillis() - startTime);
					lead.queue(this);
					while(!answered) {
						Thread.sleep(10);
					}
					answered = false;
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printLunchStartMessage(lunchStartTime);
		
		while(System.currentTimeMillis() <= (lunchEndTime + startTime)){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printLunchEndMessage(lunchEndTime);
		
		while(System.currentTimeMillis() <= (8*60*10 + startTime)){
			try {
				if(isLead()){
					openForQuestions();
				}
				if(!isLead() && askQuestion()){
					printAskingQuestionMessage(System.currentTimeMillis() - startTime);
					lead.queue(this);
					while(!answered) {
						Thread.sleep(10);
					}
					answered = false;
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		man.groupMeetingArrival(this);
		while(!answered) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		while(System.currentTimeMillis() <= (leaveTime + startTime)){
			try {
				if(isLead()){
					openForQuestions();
				}
				if(!isLead() && askQuestion()){
					printAskingQuestionMessage(System.currentTimeMillis() - startTime);
					lead.queue(this);
					while(!answered) {
						Thread.sleep(10);
					}
					answered = false;
				}
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printLeavingMessage(leaveTime);
	}
	
	//Make the employee wait until called
	public synchronized void done() {
		answered = true;
	}
	
	private long getArrivalTime(){
		int latestArrival = (30*10);
		return r.nextInt(latestArrival+1);
	}
	private long getLunchStartTime(){
		int earliestLunchTime = (3*60*10);
		int latestLunchTime = (5*60*10);
		return r.nextInt((latestLunchTime-earliestLunchTime)+1)+earliestLunchTime;
	}
	private long getLunchEndTime(long startTime){
		int earliestEndTime = (int)(startTime + (30*10) );
		int latestEndTime   = (int)(startTime + (60*10) );
		return r.nextInt((latestEndTime-earliestEndTime)+1)+earliestEndTime;
	}
	private long getLeavingTime(long arrivalTime, long lunchStartTime, long lunchEndTime){
		long time = 8*60*10;
		time += arrivalTime;
		time += (lunchEndTime - lunchStartTime);
		return time;
	}
	
	private Boolean askQuestion(){
		if(r.nextInt(100) < questionChance){
			return true;
		}
		return false;
	}
	private synchronized Boolean canAnswer(){
		if(r.nextInt(100) < 50){
			return true;
		}
		return false;
	}
	
	private synchronized Boolean isLead(){
		if(empNumber == 1){
			return true;
		}
		return false;
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
		System.out.println("Developer " + teamNumber + "" + empNumber + " arrived at " + getTime(time));
	}
	private void printLunchStartMessage(long time){
		System.out.println("Developer " + teamNumber + "" + empNumber + " left for lunch at " + getTime(time));
	}
	private void printLunchEndMessage(long time){
		System.out.println("Developer " + teamNumber + "" + empNumber + " returned from lunch at " + getTime(time));
	}
	private void printLeavingMessage(long time){
		System.out.println("Developer " + teamNumber + "" + empNumber + " left the office at " + getTime(time));
	}
	private void printAskingQuestionMessage(long time){
		System.out.println("Developer " + teamNumber + "" + empNumber + " asked a question at " + getTime(time));
	}
	private void printAnsweredQuestionMessage(long time){
		System.out.println("Developer " + teamNumber + "" + empNumber + " is finished asking questions at " + getTime(time));
	}
	private void printAnswerQMessage(long time, int i) {
		System.out.println(getTime(time) + "  Team lead " + teamNumber + " answered Developer " + i + "'s question!");
	}
	
	public synchronized void queue(Employee t) throws InterruptedException {
		waiting.add(t);
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
				if(canAnswer()){
					printAnswerQMessage(System.currentTimeMillis() - startTime, waiting.get(0).empNumber);
					Thread.sleep(10*10);
					waiting.get(0).done();
					waiting.remove(0);
				}else{
					printAskingQuestionMessage(System.currentTimeMillis() - startTime);
					man.queue(this);
					while(!answered) {
						Thread.sleep(10);
					}
					answered = false;
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//(new Developer(1,2)).start();
	}

}
