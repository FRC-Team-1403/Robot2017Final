package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class VoltageShooter extends Command {

    public VoltageShooter() {
        // Use requires() here to declare subsystem dependencies
         requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.leftShooter.set(0.8);
    	Robot.shooter.rightShooter.set(-0.8);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Math.abs(0.8-Robot.shooter.leftShooter.get())>0.05&&Math.abs(0.8-Robot.shooter.rightShooter.get())>0.05;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooter.leftShooter.set(0);
    	Robot.shooter.rightShooter.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
