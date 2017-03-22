package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *sends balls from feeders into shooters
 *runs until the button is released
 */
public class RunFeeders extends Command {
	Timer timer = new Timer();
    public RunFeeders() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.feeder);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get() > 1) {
    		Robot.feeder.startBoth();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.feeder.stopBoth();
    	Robot.shooter.leftShooter.set(0);
    	Robot.shooter.rightShooter.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.feeder.stopBoth();
    	Robot.shooter.leftShooter.set(0);
    	Robot.shooter.rightShooter.set(0);
    }
}
