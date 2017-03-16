package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *drives straight for the distance in the constructor, in feet
 */
public class DriveStraight extends Command {

	public double distance;
	
    public DriveStraight(double distance) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.distance = distance;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(distance>0) {
    		Robot.driveTrain.setLeftRightPower(0.3, 0.3);
    	}
    	else {
    		Robot.driveTrain.setLeftRightPower(-0.3, -0.3);
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        if (distance>0) {
        	return Robot.driveTrain.getLeftPosition() > distance && Robot.driveTrain.getRightPosition() > distance;
        }
        return Robot.driveTrain.getLeftPosition() < distance && Robot.driveTrain.getRightPosition() < distance;

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
