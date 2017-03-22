package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *runs the feeders to send one ball into the shooters
 */
public class LoadBall extends Command {

	Timer timer;
    public LoadBall() {
        // Use requires() here to declare subsystem dependencies
    	timer = new Timer();
        requires(Robot.feeder);
        setTimeout(.6);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer.start();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(timer.get()>.3) {
    		Robot.feeder.startBoth();
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.feeder.stopBoth();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.feeder.stopBoth();
    }
}
