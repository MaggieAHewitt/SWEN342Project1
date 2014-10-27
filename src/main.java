
public class main {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		final int DEVS = 3;
		long time = System.currentTimeMillis();
		
		Manager item = new Manager(time, DEVS);
		item.start();
		(new Developer(item,time,1,2)).start();
		(new Developer(item,time,1,3)).start();
		(new Developer(item,time,1,4)).start();
	
	}

}
