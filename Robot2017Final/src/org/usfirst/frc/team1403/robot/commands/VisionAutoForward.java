package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class VisionAutoForward extends Command {
	private double placementDistance;
	private double speed;

    public VisionAutoForward(double pD, double s) {
    	placementDistance = pD;
    	speed = s;
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.leftEncoder.reset();
    	Robot.driveTrain.rightEncoder.reset();

    }

    protected void execute() {
		
		Robot.driveTrain.motor4.set(speed);
	   	Robot.driveTrain.motor5.set(speed);
	   	Robot.driveTrain.motor6.set(-speed);
	   	Robot.driveTrain.motor7.set(-speed);
	   	System.out.println("placementDistance (same as hypotenuse) = " + placementDistance);
	   	System.out.println("left encoder = " + Robot.driveTrain.getLeftPosition());
    	System.out.println("right encoder = " + Robot.driveTrain.getRightPosition());
   // 	System.out.println("HYPOTNUSE = " + Robot.hypotenuse);
    	System.out.println(Robot.irs);
	}

   	


    protected boolean isFinished() {
	//		return (Robot.driveTrain.coder1.get() >= (Robot.hypotenuse*Robot.inchRotation) || Robot.driveTrain.coder2.get() >= (Robot.hypotenuse*Robot.inchRotation)); //coder1 currently not working
    	return (Math.abs(Robot.driveTrain.getLeftPosition())*12 >= placementDistance || Math.abs(Robot.driveTrain.getRightPosition())*12 >= placementDistance || Robot.irs >= 3600);
    
    }
    

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.motor4.set(0);
	   	Robot.driveTrain.motor5.set(0);
	   	Robot.driveTrain.motor6.set(0);
	   	Robot.driveTrain.motor7.set(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
