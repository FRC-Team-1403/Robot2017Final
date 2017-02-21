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
    	
    	double leftYstick = Robot.oi.ojoy.getRawAxis(1);
    	double motorOutput = Robot.shooter.rightShooter.getOutputVoltage() / Robot.shooter.rightShooter.getBusVoltage();
    	/* prepare line to print */
		System.out.println("\tout:");
		System.out.println(motorOutput);
		System.out.println("\tspd:");
		System.out.println(Robot.shooter.rightShooter.getSpeed());
        
        if(Robot.oi.ojoy.getRawButton(1)){
        	/* Speed mode */
        	double targetSpeed = -3982; /* 1500 RPM in either direction */
        	Robot.shooter.rightShooter.changeControlMode(TalonControlMode.Speed);
        	Robot.shooter.rightShooter.set(targetSpeed); /* 1500 RPM in either direction */

        	/* append more signals to print when in speed mode. */
          System.out.println("\terr:");
            System.out.println(Robot.shooter.rightShooter.getClosedLoopError());
            System.out.println("\ttrg:");
            System.out.println(targetSpeed);
        } else {
        	/* Percent voltage mode */
        	Robot.shooter.rightShooter.changeControlMode(TalonControlMode.PercentVbus);
        	Robot.shooter.rightShooter.set(leftYstick);
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
