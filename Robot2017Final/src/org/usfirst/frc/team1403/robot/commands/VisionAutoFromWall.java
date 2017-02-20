package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class VisionAutoFromWall extends Command {

    public VisionAutoFromWall() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {

    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
   // 	Robot.DriveT.robot.drive(0.2, curve);
    	System.out.println("Gyro angle " + Robot.autoGyro);
    	System.out.println("Turn angle " + Robot.turn);
    	System.out.println("neededAngle " + Robot.neededAngle);
    	System.out.println("subtracted = " + Robot.subtracted);
    	
    	if (Robot.subtracted > 0.5){
    	System.out.println("Robot is Moving");
    	Robot.driveTrain.motor4.set(0.2);
    	Robot.driveTrain.motor5.set(0.2);
    	Robot.driveTrain.motor6.set(0.2);
    	Robot.driveTrain.motor7.set(0.2);
    	}   	
    	
    	if (Robot.subtracted < -0.5){
        	System.out.println("other side");
        	Robot.driveTrain.motor4.set(-0.2);
        	Robot.driveTrain.motor5.set(-0.2);
        	Robot.driveTrain.motor6.set(-0.2);
        	Robot.driveTrain.motor7.set(-0.2);
        	} 
    
    }

   protected boolean isFinished() {
        return (Robot.subtracted < 0.5 && Robot.subtracted > -0.5); //&& Math.abs(Robot.neededAngle - Robot.angle) > -0.3;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.driveTrain.motor4.set(0);
    	Robot.driveTrain.motor5.set(0);
    	Robot.driveTrain.motor6.set(0);
    	Robot.driveTrain.motor7.set(0);
    	System.out.println("Finished");
    }

    protected void interrupted() {
    }
}
