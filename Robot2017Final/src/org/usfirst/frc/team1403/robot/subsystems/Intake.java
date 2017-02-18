package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.RobotMap;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Intake extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public CANTalon roller;
	
	public Intake() {
		roller = new CANTalon(RobotMap.intakeRoller);
	}
	//TODO figure out intake direction
	public void rollersIn() {
		roller.set(0.5);
	//	Robot.shooter.leftShooter.set(0.1);
	//	Robot.shooter.rightShooter.set(0.1);
	}
	
	public void rollersOut() {
		roller.set(-0.5);
	}
	
	public void stopRollers() {
		roller.set(0);
		Robot.shooter.leftShooter.set(0);
		Robot.shooter.rightShooter.set(0);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

