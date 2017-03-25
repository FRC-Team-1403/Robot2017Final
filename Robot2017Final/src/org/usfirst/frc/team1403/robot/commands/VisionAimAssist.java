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
    	
    	double power = Math.abs((Math.abs(diff)-30)/100);
    	
    	if (diff <= -34 && diff != 120) {
    		if (power < 0.05){
    			power = 0.05;
    		}
    		
    		else if (power > 0.8){
    			power = power - 0.4;
    		}
    		Robot.driveTrain.motionMappingSetLeftRightPower(power, -power);
    	}
    		    	
    	else if (diff >= -26 && diff != 120){
    		
    		if (power < 0.05){
    			power = 0.05;
    		}
    		else if (power > 0.8){
    			power = power - 0.4;
    		}
    		
        	Robot.driveTrain.motionMappingSetLeftRightPower(-power, power);
       	}
    	
    	else if (diff == 120){
    		Robot.driveTrain.motionMappingSetLeftRightPower(0.3, -0.3);
    	}
    	
    	
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return diff < -26 && diff > -34;
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
