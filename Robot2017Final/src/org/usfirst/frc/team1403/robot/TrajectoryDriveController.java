package org.usfirst.frc.team1403.robot;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import trajectoryLib.trajectory.Trajectory;
import trajectoryLib.trajectory.TrajectoryFollower;
import trajectoryLib.util.ChezyMath;
/**
 * TrajectoryDriveController.java
 * This controller drives the robot along a specified trajectory.
 * @author Tom Bottiglieri
 */
public class TrajectoryDriveController {

	
	
  public TrajectoryDriveController() {
    init();
  }
  
  //a constructor for configuring the trajectory followers
  public TrajectoryDriveController(double kV, double kA, double kP, double kTurn) {
	  followerLeft.configure(kP, 0, 0, kV, kA);
	  followerRight.configure(kP, 0, 0, kV, kA);
	  this.kTurn = kTurn;
  }
  
  Trajectory trajectory;
  TrajectoryFollower followerLeft = new TrajectoryFollower();
  TrajectoryFollower followerRight = new TrajectoryFollower();
  double direction;
  double heading;
  public double kTurn = 0;
  boolean enabled = false;

  public boolean onTarget() {
    return followerLeft.isFinishedTrajectory(); 
  }

  private void init() {
    followerLeft.configure(0, 0, 0, 1.0/1.0, 1.0/10.0);
    followerRight.configure(0, 0, 0, 1.0/1.0, 1.0/10.0);
  }

  public void loadProfile(Trajectory leftProfile, Trajectory rightProfile, double direction, double heading) {
    reset();
    followerLeft.setTrajectory(leftProfile);
    followerRight.setTrajectory(rightProfile);
    this.direction = direction;
    this.heading = heading;
  }
  
  public void loadProfileNoReset(Trajectory leftProfile, Trajectory rightProfile) {
    followerLeft.setTrajectory(leftProfile);
    followerRight.setTrajectory(rightProfile);
  }

  public void reset() {
    followerLeft.reset();
    followerRight.reset();
    Robot.driveTrain.resetEncoders();
    
  }
  
  public int getNumSegments() {
	  	//originally had followerLeft.getNumSegments in 254's code but that give us error
	    return getNumSegments();
  }
  
  public int getFollowerCurrentSegment() {
    return followerLeft.getCurrentSegment();
  }
  
  //sets the motors based on the current state of the path
  public void update() {
    if (!enabled) {
      return ;
    }

    if (onTarget()) {
      Robot.driveTrain.setLeftRightPower(0.0, 0.0);
    } else  {
      double distanceL = direction * Robot.driveTrain.getLeftPosition();
      double distanceR = direction * Robot.driveTrain.getRightPosition();

      double speedLeft = direction * followerLeft.calculate(distanceL);
      double speedRight = direction * followerRight.calculate(distanceR);
      
      double goalHeading = followerLeft.getHeading();
      double observedHeading = Robot.driveTrain.getAngleInRadians();

      double angleDiffRads = ChezyMath.getDifferenceInAngleRadians(observedHeading, goalHeading);
      double angleDiff = Math.toDegrees(angleDiffRads);

      double turn = kTurn * angleDiff;
     Robot.driveTrain.motionMappingSetLeftRightPower(speedLeft + turn, speedRight - turn);
      
      SmartDashboard.putNumber("Left Velocity from algorithm", followerLeft.segmentVel);
      SmartDashboard.putNumber("Right Velocity from algorithm", followerRight.segmentVel);
      
      SmartDashboard.putNumber("Left Position from algorithm", followerLeft.segmentPos);
      SmartDashboard.putNumber("Right Position from algorithm", followerRight.segmentPos);
      
      SmartDashboard.putNumber("Left Output", speedLeft);
      SmartDashboard.putNumber("Right Output", speedRight);
      
      SmartDashboard.putNumber("Left Position from Encoder", Robot.driveTrain.getLeftPosition());
  	  SmartDashboard.putNumber("Right Position from Encoder", Robot.driveTrain.getRightPosition());
  	  
  	  SmartDashboard.putNumber("Left Velocity from Encoder", Robot.driveTrain.getLeftVelocity());
  	  SmartDashboard.putNumber("Right Velocity from Encoder", Robot.driveTrain.getRightVelocity());
      
      SmartDashboard.putNumber("Left Error", followerLeft.error);
      SmartDashboard.putNumber("Right Error", followerRight.error);
      
      SmartDashboard.putNumber("Left Velocity Error", followerLeft.segmentVel - Robot.driveTrain.getLeftVelocity());
      SmartDashboard.putNumber("Right Velocity Error", followerRight.segmentVel - Robot.driveTrain.getRightVelocity());
      
    }
  }

  public void setTrajectory(Trajectory t) {
    this.trajectory = t;
  }

  public double getGoal() {
    return 0;
  }

  public void enable() {
	    enabled = true;
	  }
	  
	  public void disable() {
	    enabled = false;
	  }

	  public boolean enabled() {
	    return enabled;
	  }
}