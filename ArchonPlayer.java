package team028;

import battlecode.common.*;
import static battlecode.common.GameConstants.*;

public class ArchonPlayer extends BasePlayer {

   private final RobotController myRC;
   
   private MapLocation targetLoc;
   private State myState;
   private boolean fluxDepositFindingOwned;
   private boolean fluxDepositFindingUnowned;
   
   public ArchonPlayer(RobotController rc) {
	   super(rc);
	   setState(State.IDLE);
	   myRC = rc;
   }

   public void update() {
	   
	   // sensing loops and messaging goes here
	   
	   // code to set state based on status of game

	   
	   
	   try {
		   
		   
		   
		   if (Clock.getRoundNum() == 300){
			   myRC.spawn(RobotType.SCOUT);
		   }
		   else{
			   navigateTowardsUnownedFluxDeposit();
		   }
		   
		   while(myRC.isMovementActive()||myRC.isAttackActive()) {
		  		 myRC.yield();
		   }
		   
		   switch (myState) {
		   	case MOVE: move();
		   	// more states go here
		   	case IDLE: myRC.yield();
		   	default: myRC.yield();
		   }
	   }
	   catch(GameActionException e){
		   GameActionExceptionType type = e.getType();
		   if (type == GameActionExceptionType.BAD_DIRECTION){
			   setState(State.IDLE);
		   }
		   // need to handle all appropriate exceptions
	   }
	   // change this to specific game action exceptions
	   catch(Exception e) {
           System.out.println("caught a non-GameAction Exception");
           e.printStackTrace();
       }
   }
   
	
   public void move() throws GameActionException {
	   
	   Direction nextStep = Direction.NONE;
	   
	   if (fluxDepositFindingUnowned){
		   nextStep = myRC.senseDirectionToUnownedFluxDeposit();
	   }
	   else if (fluxDepositFindingOwned){
		   nextStep = myRC.senseDirectionToOwnedFluxDeposit();
	   }
	   else {
		   // gets the direction from our current location to our target location
		   nextStep = myRC.getLocation().directionTo(getTargetLoc());
	   }
	   
	   // if we're not in the direction of the target, change direction to face target
	   // ** might throw an exception
	   if (nextStep != myRC.getDirection()){
		   myRC.setDirection(nextStep);
	   }
	   else {
		   myRC.moveForward();
	   }
   }
   
   public void createMessage(){
	   ;
   }
   
   public void interpretMessage(Message m){
	   ;
   }
   
   // STATE CHANGE METHODS
   
   public void navigateTowardsTarget(MapLocation newTarget){
		// Sets final navigation destination
		setTargetLoc(newTarget);
		// Sets state to MOVE
		setState(State.MOVE);
		// turn off FluxDeposit finding
		fluxDepositFindingOwned = false;
		fluxDepositFindingUnowned = false;
	}
 
   public void navigateTowardsUnownedFluxDeposit(){
	   fluxDepositFindingUnowned = true;
	   fluxDepositFindingOwned = false;
	   setState(State.MOVE);
   }
   
   public void navigateTowardsOwnedFluxDeposit(){
	   fluxDepositFindingUnowned = false;
	   fluxDepositFindingOwned = true;
	   setState(State.MOVE);
   }
   
   // MUTATOR METHODS
   
   private void setTargetLoc(MapLocation loc){
	   targetLoc = loc;
   }
   private void setState(State s){
	   myState = s;
   }
   
   // ACCESSOR METHODS
   
   private MapLocation getTargetLoc(){
	   return targetLoc;
   }
   private State getState(){
	   return myState;
   }
}
