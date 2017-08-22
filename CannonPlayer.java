package team028;

import battlecode.common.*;
import static battlecode.common.GameConstants.*;

public class CannonPlayer extends BasePlayer {

   private final RobotController myRC;
   
   private MapLocation targetLoc;
   private Direction targetDir;
   private State myState;
   
   public CannonPlayer(RobotController rc) {
	   super(rc);
	   myRC = rc;
   }

   public void update() {
	   try {
		   switch (myState) {
		   	case MOVE: move();
		   	// more states go here
		   	case IDLE: myRC.yield();
		   	default: myRC.yield();
		   }
	   }
	   // change this to specific game action exceptions
	   catch(Exception e) {
           System.out.println("caught exception:");
           e.printStackTrace();
       }
   }
   
   public void move() throws GameActionException {
	   
   }
   
   // MUTATOR METHODS
   
   private void setTargetLoc(MapLocation loc){
	   targetLoc = loc;
	   // set targetDir to new location from the robot's current location
	   targetDir = myRC.getLocation().directionTo(loc);
   }
   private void setTargetDir(Direction dir){
	   // will be used when trying to get around an obstacle but keeping the same target
	   targetDir = dir;
   }
   private void setState(State s){
	   myState = s;
   }
   
   // ACCESSOR METHODS
   
   private MapLocation getTargetLoc(){
	   return targetLoc;
   }
   private Direction getTargetDir(){
	   return targetDir;
   }
   private State getState(){
	   return myState;
   }
}