import java.util.ArrayList;


public class ConfrenceRoom extends Thread{

	private static int COMPLETE_TEAM = 4;
	
	ArrayList<Developer> waiting;
	
	public ConfrenceRoom(){
		this.waiting = new ArrayList<Developer>();
	}
	
	public void run(){
		openForMeeting();
	}
	
	public synchronized void queue(Developer t) throws InterruptedException {
		waiting.add(t);
	}
	
	private void openForMeeting() {
		if (waiting.isEmpty()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		} else {
			try{
				while(!teamReady()){
					Thread.sleep(10);
				}
				int team = getTeamId();
				printMeetingStartMessage(team);
				Thread.sleep(150);
				printMeetingEndMessage(team);
				teamFinish(team);
			
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized Boolean teamReady(){
		Developer next = waiting.get(0); 
		int count = 1;
		for(Developer d: waiting){
			if(next.teamNumber == d.teamNumber){
				count +=1;
			}
		}
		if(count == COMPLETE_TEAM){
			return true;
		}
		else{
			return false;
		}
	}
	private synchronized void teamFinish(int id){
		for(Developer d: waiting){
			if(d.teamNumber == id){
				waiting.remove(d);
			}
		}
	}
	private synchronized int getTeamId(){
		return waiting.get(0).teamNumber;
	}
	
	private synchronized void printMeetingStartMessage(int id){
		System.out.println("Team " + id + " meeting has started");
	}
	private synchronized void printMeetingEndMessage(int id){
		System.out.println("Team " + id + " meeting has ended");
	}
	/**
	 * @param args
	 */


}
