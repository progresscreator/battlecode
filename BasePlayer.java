package team028;

import battlecode.common.*;
import static battlecode.common.GameConstants.*;

abstract public class BasePlayer {

   public enum State {IDLE, MOVE};
   
   public BasePlayer(RobotController rc) {}

   abstract public void update();
   
   abstract public void move() throws GameActionException;
   
   abstract public void createMessage();

   abstract public void interpretMessage(Message m);
   
   abstract public void navigateTowardsTarget(MapLocation m);
   
   public double moveCostFunction(RobotController robot, Direction d, MapLocation abstarget){
	   RobotType type = robot.getRobotType();
	   MapLocation targetloc = robot.getLocation().add(d);
	   TerrainTile tile = robot.senseTerrainTile(targetloc);
	   double tileheight = 0;
	   if (tile.getType() != TerrainTile.TerrainType.OFF_MAP 
			   && tile.getType() != TerrainTile.TerrainType.VOID){
		   
		   if (type == RobotType.WORKER && robot.getNumBlocks() >= GameConstants.WORKER_MAX_BLOCKS){
			   TerrainTile currenttile = robot.senseTerrainTile(robot.getLocation());
			   if (Math.abs(currenttile.getHeight()- tile.getHeight()) > GameConstants.WORKER_MAX_HEIGHT_DELTA){
				   return Double.MAX_VALUE;
			   }
		   }
		   
		   tileheight = 4 * tile.getHeight();
	   }
	   else {
		   return Double.MAX_VALUE;
	   }
	   
	   double heuristic = 0;
	   if (d.isDiagonal()){
		   heuristic = tileheight + type.moveDelayDiagonal();
	   }
	   else {
		   heuristic = tileheight + type.moveDelayOrthogonal();
	   }
	   
	   heuristic += robot.getLocation().add(d).distanceSquaredTo(abstarget);
	   
	   return heuristic;
   }
   
   // NODE FOR MOVING
   protected class TurningPoint{
	   private Direction dir;
	   private MapLocation loc;
	   
	   private TurningPoint(Direction d, MapLocation mloc){
		   dir = d;
		   loc = mloc;
	   }
	   public Direction getDirection(){
		   return dir;
	   }
	   public MapLocation getLocation(){
		   return loc;
	   }
   }
   
}


