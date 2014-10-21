
public class main {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		long time = System.currentTimeMillis();
		
		(new Developer(time,1,2)).start();
		(new Developer(time,1,3)).start();
		(new Developer(time,1,4)).start();
	
	}

}
