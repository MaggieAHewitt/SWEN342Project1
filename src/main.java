
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
		
		Employee lead1 = new Employee(item,time,1,1,questionChance);
		Employee lead2 = new Employee(item,time,2,1,questionChance);
		Employee lead3 = new Employee(item,time,3,1,questionChance);
		
		item.start();
		lead1.start();
		lead2.start();
		lead3.start();
		
		
		(new Employee(item,lead1,time,1,2,questionChance)).start();
		(new Employee(item,lead1,time,1,3,questionChance)).start();
		(new Employee(item,lead1,time,1,4,questionChance)).start();
		(new Employee(item,lead1,time,2,2,questionChance)).start();
		(new Employee(item,lead1,time,2,3,questionChance)).start();
		(new Employee(item,lead1,time,2,4,questionChance)).start();
		(new Employee(item,lead1,time,3,2,questionChance)).start();
		(new Employee(item,lead1,time,3,3,questionChance)).start();
		(new Employee(item,lead1,time,3,4,questionChance)).start();
	}

}
