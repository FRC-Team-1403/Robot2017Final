package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionAimAssist extends Command {
	
	private double diff;
	
    public VisionAimAssist() {
    
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

    	diff = SmartDashboard.getNumber("difference", 321);
    	SmartDashboard.putBoolean("Aligned", false);
    	
    	if (diff <= -75) {
    		Robot.driveTrain.setLeftRightPower(Math.abs((Math.abs(diff)-80)/100)*0.5, -Math.abs(Math.abs(diff)-70)/100);
    	}
    		    	
    	else if (diff >= -85){
        	Robot.driveTrain.setLeftRightPower((-Math.abs(Math.abs(diff)-80)/100)*0.5, Math.abs(Math.abs(diff)-70)/100);
       	}
    	
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return diff < -75 && diff > -85;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setLeftRightPower(0, 0);
    	SmartDashboard.putBoolean("Aligned", true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.setLeftRightPower(0, 0);
    }
}
