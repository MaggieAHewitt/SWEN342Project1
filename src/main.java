
public class main {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		final int DEVS = 3;
		final int questionChance = 4;
		long time = System.currentTimeMillis();
		
		Manager item = new Manager(time, DEVS);
		
		Developer lead1 = new Developer(item,time,1,1,questionChance);
		Developer lead2 = new Developer(item,time,2,1,questionChance);
		Developer lead3 = new Developer(item,time,3,1,questionChance);
		
		item.start();
		lead1.start();
		lead2.start();
		lead3.start();
		
		
		(new Developer(item,lead1,time,1,2,questionChance)).start();
		(new Developer(item,lead1,time,1,3,questionChance)).start();
		(new Developer(item,lead1,time,1,4,questionChance)).start();
		(new Developer(item,lead1,time,2,2,questionChance)).start();
		(new Developer(item,lead1,time,2,3,questionChance)).start();
		(new Developer(item,lead1,time,2,4,questionChance)).start();
		(new Developer(item,lead1,time,3,2,questionChance)).start();
		(new Developer(item,lead1,time,3,3,questionChance)).start();
		(new Developer(item,lead1,time,3,4,questionChance)).start();
	}

}
