package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.CANTalon.TalonControlMode;

import edu.wpi.first.wpilibj.Joystick.AxisType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CalibrationCommand extends Command {

    public CalibrationCommand() {
        // Use requires() here to declare subsystem dependencies
   requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double leftYstick = Robot.oi.ojoy.getAxis(AxisType.kY);
    	double motorOutput = Robot.shooter.leftShooter.getOutputVoltage() / Robot.shooter.leftShooter.getBusVoltage();
    	/* prepare line to print */
		System.out.println("\tout:");
		System.out.println(motorOutput);
		System.out.println("\tspd:");
		System.out.println(Robot.shooter.leftShooter.getSpeed());
        
        if(Robot.oi.ojoy.getRawButton(1)){
        	/* Speed mode */
        	double targetSpeed = leftYstick * 3704; /* 1500 RPM in either direction */
        	Robot.shooter.leftShooter.changeControlMode(TalonControlMode.Speed);
        	Robot.shooter.leftShooter.set(targetSpeed); /* 1500 RPM in either direction */

        	/* append more signals to print when in speed mode. */
          System.out.println("\terr:");
            System.out.println(Robot.shooter.leftShooter.getClosedLoopError());
            System.out.println("\ttrg:");
            System.out.println(targetSpeed);
        } else {
        	/* Percent voltage mode */
        	Robot.shooter.leftShooter.changeControlMode(TalonControlMode.PercentVbus);
        	Robot.shooter.leftShooter.set(leftYstick);
        }

        
       
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
