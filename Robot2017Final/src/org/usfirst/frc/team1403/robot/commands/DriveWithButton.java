package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveWithButton extends Command {

    public DriveWithButton() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("hi");
    	//negative because the pixel xy plane starts from the top - Alex... yeah just ignore this
    	SmartDashboard.putNumber("Left Encoder Value", Robot.driveTrain.getLeftPosition());
    	SmartDashboard.putNumber("Right Encoder Value", Robot.driveTrain.getRightPosition());
    	SmartDashboard.putNumber("Left Encoder Velocity", Robot.driveTrain.getLeftVelocity());
    	SmartDashboard.putNumber("Right Encoder Velocity", Robot.driveTrain.getRightVelocity());
    	Robot.driveTrain.setLeftRightPower(SmartDashboard.getNumber("Left Power", 0), SmartDashboard.getNumber("Right Power", 0));
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.setLeftRightPower(0,0);
    }
}
