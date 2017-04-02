package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *drives straight for the distance in the constructor, in feet
 */
public class DriveStraight extends Command {

	public double distance;
	public double speed;
	
    public DriveStraight(double distance, double speed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.distance = distance;
        this.speed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.gyro.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	System.out.println("Drive straight");
    	double gyroPower = Robot.driveTrain.gyro.getAngle()*0.01;
    	if(distance>0.0) {
    		Robot.driveTrain.motionMappingSetLeftRightPower(speed-gyroPower, speed+gyroPower);
    	}
    	else {
    		Robot.driveTrain.motionMappingSetLeftRightPower(-(speed+gyroPower), -(speed-gyroPower));
    	}
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    
    	return  Math.abs(Robot.driveTrain.getLeftPosition()) >= Math.abs(distance) || Math.abs(Robot.driveTrain.getRightPosition()) >= Math.abs(distance);
    
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
