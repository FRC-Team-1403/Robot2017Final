package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CANPowerUp extends Command {

    public CANPowerUp() {
        // Use requires() here to declare subsystem dependencies
      requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.leftShooter.changeControlMode(CANTalon.TalonControlMode.Speed);
    	Robot.shooter.rightShooter.changeControlMode(CANTalon.TalonControlMode.Speed);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooter.leftShooter.set(3304);
    	Robot.shooter.rightShooter.set(-3200);//'member that this is in RPM not ft/s lollllll
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.shooter.leftShooter.set(0);
    	Robot.shooter.rightShooter.set(0);
    	Robot.shooter.leftShooter.changeControlMode(CANTalon.TalonControlMode.Voltage);
    	Robot.shooter.rightShooter.changeControlMode(CANTalon.TalonControlMode.Voltage);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    	Robot.shooter.leftShooter.set(0);
    	Robot.shooter.rightShooter.set(0);
    	Robot.shooter.leftShooter.changeControlMode(CANTalon.TalonControlMode.Voltage);
    	Robot.shooter.rightShooter.changeControlMode(CANTalon.TalonControlMode.Voltage);
    }
}
