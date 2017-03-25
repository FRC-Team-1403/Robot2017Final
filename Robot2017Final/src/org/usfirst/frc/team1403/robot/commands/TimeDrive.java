package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class TimeDrive extends Command {

	private double seconds;
	
    public TimeDrive(double seconds) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.seconds = seconds;
        setTimeout(seconds);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.setLeftRightPower(0, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
