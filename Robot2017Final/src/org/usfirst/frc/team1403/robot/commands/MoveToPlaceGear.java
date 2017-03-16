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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(Math.abs(Robot.driveTrain.getLeftPosition())<distance*.75){
    		
    		Robot.driveTrain.setLeftRightPower(0.5, 0.5);
    	}
    	else if(Math.abs(Robot.driveTrain.getLeftPosition())<distance*.9){
    		
    		Robot.driveTrain.setLeftRightPower(0.25, 0.25);
    	}
    	else{
    		
    		Robot.driveTrain.setLeftRightPower(0.1, 0.1);
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (Math.abs(Robot.driveTrain.getLeftPosition())>distance);
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
