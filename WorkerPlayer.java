package team028;

import battlecode.common.*;
import static battlecode.common.GameConstants.*;

public class WorkerPlayer extends BasePlayer {

   private final RobotController myRC;
   
   private MapLocation targetLoc;
   private Direction targetDir;
   private State myState;
   
   public WorkerPlayer(RobotController rc) {
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
	   
	   for (Direction dir : Direction.values()){
		   // assign heuristic to direction
		   // if its less than the minimum variable and we haven't "turned" here, then set it as minimum
	   }
	   
	   // if not facing the current direction, then move and store this as a turning point
	   // otherwise, move forward
	   
   }
   
   public void createMessage() {
   	
   }

   public void interpretMessage(Message m) {
   	
   }

   public void navigateTowardsTarget(MapLocation m) {
   	
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