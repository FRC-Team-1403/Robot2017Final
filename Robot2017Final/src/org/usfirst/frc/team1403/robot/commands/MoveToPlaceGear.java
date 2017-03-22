package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *used in the center gear auto
 *moves the robot from the alliance wall to the lift
 */
public class MoveToPlaceGear extends Command {
	double distance;
    public MoveToPlaceGear(double distance) {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.driveTrain);
         this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.gyro.reset();
    	setTimeout(2);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double angleDiff = Robot.driveTrain.gyro.getAngle();
    	double turn = angleDiff * 1.0/200;
    	if(Math.abs(Robot.driveTrain.getLeftPosition())<distance*.75){
    		//Robot.driveTrain.motionMappingSetLeftRightPower(0.25-turn, 0.25+turn);
    		Robot.driveTrain.motionMappingSetLeftRightPower(0.25, 0.25);
    	}
    	else if(Math.abs(Robot.driveTrain.getLeftPosition())<distance*.9){
    		
    		Robot.driveTrain.motionMappingSetLeftRightPower(0.2, 0.2);
    		//Robot.driveTrain.motionMappingSetLeftRightPower(0.2-turn, 0.2+turn);
    	}
    	else{
    		
    		Robot.driveTrain.motionMappingSetLeftRightPower(0.15, 0.15);
    		//Robot.driveTrain.motionMappingSetLeftRightPower(0.2-turn, 0.2+turn);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println(Math.abs(Robot.driveTrain.getLeftPosition())>distance);
        return (Math.abs(Robot.driveTrain.getLeftPosition())>distance) && isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setLeftRightPower(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.setLeftRightPower(0, 0);
    }
}
