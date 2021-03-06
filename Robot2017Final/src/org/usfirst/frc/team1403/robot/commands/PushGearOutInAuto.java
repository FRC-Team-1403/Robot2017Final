package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Command;

/**
 *pushes the gear out then waits for 1 second before going to the next command
 */
public class PushGearOutInAuto extends Command {

    public PushGearOutInAuto() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.gearPusher);
        setTimeout(1);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.catcher.barDown();//up is down, down is up lolll
    	Robot.gearPusher.push();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println(isTimedOut());
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
