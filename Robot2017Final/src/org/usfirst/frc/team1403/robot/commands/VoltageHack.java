package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *uses voltage compensation mode
 */
public class VoltageHack extends Command {

    public VoltageHack() {
        // Use requires() here to declare subsystem dependencies
       requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.leftShooter.configNominalOutputVoltage(+12.0,-12.0);
    	Robot.shooter.rightShooter.configNominalOutputVoltage(+12.0,-12.0);

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.leftShooter.set(0.75);
    	Robot.shooter.rightShooter.set(0.80);
    	//single balls left.735right.8
    	
    	//for pulling voltage values from smartdashboard
    	//Robot.shooter.leftShooter.set(SmartDashboard.getNumber("Left Shooter Voltage", 0));
    	//Robot.shooter.rightShooter.set(SmartDashboard.getNumber("Right Shooter Voltage", 0));
		
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.leftShooter.set(0);
    	Robot.shooter.rightShooter.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.leftShooter.set(0);
    	Robot.shooter.rightShooter.set(0);
    }
}
