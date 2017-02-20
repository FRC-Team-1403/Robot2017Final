package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class LeftFinalTurn extends Command {

    public LeftFinalTurn() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.neededAngle = Robot.turn;
    	Robot.currentAngle = Robot.angle;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Gyro angle " + Robot.angle);
    	
    	if (Robot.angle > 0){
    	System.out.println("Robot is Moving");
    	Robot.driveTrain.setLeftRightPower(0, 0.2);
    	}   	
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.angle <= 0;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setLeftRightPower(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
