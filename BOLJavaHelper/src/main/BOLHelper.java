package main;

import java.time.ZonedDateTime;


public class BOLHelper {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		 
		System.out.println("Press a key to send bug..");
		System.in.read();

        //First time setup instance
        //Replace with your username and password!
        //BOLSingleton ben = BOLSingleton.getInstance("YOUR@USERNAME", "YOURPASSWORD");
		//In Java prepare a BOLBug instance
		BOLBug bug = new BOLBug("BOLHelper", "1.0", "LAVORO", "Microsoft Windows NT", "10");
        BOLSingleton ben = BOLSingleton.getInstance("demo@demo.com", "cicci0CICCI0_", bug);

        //It's a singleton, next times you can obtain without login e pwd.
        BOLSingleton ben2 = BOLSingleton.getInstance();
        int c = 0;
        try
        {
            int g = 5 / c;
        }
        catch(Exception ex)
        {
        	// You can pass routine name and/or a token, if needed
        	BOLSingleton.bug.setRoutine("main");
        	BOLSingleton.bug.setToken("666");
        	System.out.println("Sending bug at " + ZonedDateTime.now());
            ben2.Send(ex); // <= the only code really needed in 'catch'
            System.out.println("Bug sent at " + ZonedDateTime.now());
        }
        finally
        {
        	System.out.println("Done!");
        }
	}
	
}
