package team028;

import battlecode.common.*;
import static battlecode.common.GameConstants.*;

public class RobotPlayer implements Runnable {

   private final RobotController myRC;
   private BasePlayer robot;

   public RobotPlayer(RobotController rc) {
	   
      myRC = rc;
      
      if (myRC.getRobotType()==RobotType.ARCHON) {
    	  robot = new ArchonPlayer(myRC);
	  }
      else if (myRC.getRobotType()==RobotType.WORKER) {
    	  robot = new WorkerPlayer(myRC);
      }
      else if (myRC.getRobotType()==RobotType.SCOUT) {
    	  robot = new ScoutPlayer(myRC);
      }
      else if (myRC.getRobotType()==RobotType.CANNON) {
    	  robot = new CannonPlayer(myRC);
      }
      else if (myRC.getRobotType()==RobotType.CHANNELER) {
    	  robot = new ChannelerPlayer(myRC);
      }
      else {
    	  System.out.println("Robot was not given a type");
      }
   }

   public void run() {
	   while(true){
	         try{
	        	 /*** beginning of main loop ***/
	        	 
	        	 robot.update();

	        	 myRC.yield();
	        	 
	        	 /*** end of main loop ***/
	         }
	         catch(Exception e) {
	             System.out.println("caught exception:");
	             e.printStackTrace();
	         }
	   }
	   
   }
}