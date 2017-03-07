package org.usfirst.frc.team1403.robot.subsystems;


import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.commands.CalibrationCommand;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.Encoder;
//import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class FlyWheel extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon leftShooter;
	public CANTalon rightShooter;
	
	public FlyWheel() {
		leftShooter = new CANTalon(RobotMap.leftFlyWheel);
		rightShooter = new CANTalon(RobotMap.rightFlyWheel);
		leftShooter.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
		rightShooter.reverseSensor(true);

		rightShooter.setFeedbackDevice(CANTalon.FeedbackDevice.QuadEncoder);
	}
	
	public void stop() {
		leftShooter.set(0);
		rightShooter.set(0);
	}
	
	//TODO make sure getAnalogInVelocity works
	public double getLeftRPM() {
		return leftShooter.getSpeed();
	}
	
	public double getRightRPM() {
		return rightShooter.getSpeed();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CalibrationCommand());
    }
    
    
}

