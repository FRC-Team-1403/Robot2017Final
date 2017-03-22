package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnInPlace extends Command {

	double angle;
    public TurnInPlace(double angle) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.angle = angle;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(angle > 0) {
    		Robot.driveTrain.motionMappingSetLeftRightPower(.4, -.4);
    	}
    	else {
    		Robot.driveTrain.motionMappingSetLeftRightPower(-.4, .4);

    	}
    	
    	//Robot.driveTrain.motionMappingSetLeftRightPower(.4, -.4);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(angle > 0) {
            return Robot.driveTrain.gyro.getAngle() > angle;

    	}
        return Robot.driveTrain.gyro.getAngle() < angle;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.setLeftRightPower(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.setLeftRightPower(0, 0);

    }
}
