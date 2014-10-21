import java.util.Random;


public class Developer extends Thread {
	
	private static int EIGHT_HOURS = 60*8*10;
	private long startTime = 0;
	private long timeWorked = 0;
	
	private Random r = new Random();
	
	int teamNumber;
	int empNumber;
	Status status;
	
	public Developer(long t, int tn, int en){
		startTime = t;
		teamNumber = tn;
		empNumber = en;
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printArrivalMessage(arrivalTime);
		
		
		while(System.currentTimeMillis() <= (lunchStartTime + startTime)){
			try {
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
		
		while(System.currentTimeMillis() <= (leaveTime + startTime)){
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printLeavingMessage(leaveTime);
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
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//(new Developer(1,2)).start();
	}

}
