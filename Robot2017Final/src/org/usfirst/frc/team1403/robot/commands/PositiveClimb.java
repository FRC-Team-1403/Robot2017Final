package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *runs the climber off of operator's left joystick
 */
public class PositiveClimb extends Command {

    public PositiveClimb() {
        // Use requires() here to declare subsystem dependencies
          requires(Robot.climb);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.climb.climbwithJoystick();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.climb.climbstop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.climb.climbstop();
    }
}
