package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.RobotMap;

import org.usfirst.frc.team1403.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    public CANTalon motor4;
    public CANTalon motor5; 
    public CANTalon motor6; 
    public CANTalon motor7;
    
    public Encoder leftEncoder, rightEncoder;
    
    public AnalogGyro gyro;
    
    //used to change setLeftRightPower
    private boolean isReversed;
    
    public DriveTrain()
    {
    	motor4 = new CANTalon(4);
    	motor5 = new CANTalon(5);
    	motor6 = new CANTalon(6);
    	motor7 = new CANTalon(7);
    	isReversed = false;
    	
    	//the encoders and gyro use the ports in robotmap
    	//leftEncoder = new Encoder(RobotMap.leftEncoder1, RobotMap.leftEncoder2);
    	//rightEncoder = new Encoder(RobotMap.rightEncoder1, RobotMap.rightEncoder2);
    	gyro = new AnalogGyro(RobotMap.gyro);
    	
    	//Set the distance per pulse as the feet per tick ratio in order to use feet for all motion mapping calculations
    	//leftEncoder.setDistancePerPulse(RobotMap.feetPerTick);
    	//rightEncoder.setDistancePerPulse(RobotMap.feetPerTick);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveWithJoystick());
        
    }
    
    //edits the setLeftRightPower method to treat the side of the robot with the gear holder as the front
    public void makeGearHolderFront() {
    	isReversed = false;
    }
    
    //edits the setLeftRightPower method to treat the side of the robot with the intake as the front
    public void makeIntakeFront() {
    	isReversed = true;
    }
    
  //don't set the motors directly; always use this method because it sets the motors differently based off of which side of the robot is considered the front
    public void setLeftRightPower(double leftPower, double rightPower)
    {
	   if(!isReversed){
		   motor4.set(-leftPower);
		   motor5.set(-leftPower);
		   motor6.set(rightPower);
		   motor7.set(rightPower);
	   }
	   else {
		   motor6.set(-leftPower);
		   motor7.set(-leftPower);
		   motor4.set(rightPower);
		   motor5.set(rightPower);
	   }
	   
    }
    
    //third parameter adjusts the power by a multiplier
    //useful for half speed button, mutliplier from SmartDashboard, etc.
    //don't make a multiplier negative, use the makeGearHolderFront() and makeIntakeFront() methods
    public void setLeftRightPower(double leftPower, double rightPower, double multiplier)
    {
	   if(!isReversed){
		   motor4.set(-leftPower*multiplier);
		   motor5.set(-leftPower*multiplier);
		   motor6.set(rightPower*multiplier);
		   motor7.set(rightPower*multiplier);
	   }
	   else {
		   motor6.set(-leftPower*multiplier);
		   motor7.set(-leftPower*multiplier);
		   motor4.set(rightPower*multiplier);
		   motor5.set(rightPower*multiplier);
	   }
	   
    }
    
    //encoder values all in feet for motion mapping
    
    public double getLeftPosition() {
    	//return leftEncoder.getDistance();
    	return -1 * motor5.getEncPosition() * RobotMap.feetPerTick;
    }
    
    public double getRightPosition() {
    	//return rightEncoder.getDistance();
    	return motor7.getEncPosition() * RobotMap.feetPerTick;
    }
    
    public double getLeftVelocity() {
    	//return leftEncoder.getRate();
    	return motor5.getEncVelocity() * RobotMap.feetPerTick * -1 * 10;
    }
    
    public double getRightVelocity() {
    	//return rightEncoder.getRate();
    	return motor7.getEncVelocity() * RobotMap.feetPerTick * 10;
    }
    
    public void resetEncoders() {
    	//leftEncoder.reset();
    	//rightEncoder.reset();
    	motor5.setEncPosition(0);
    	motor7.setEncPosition(0);
    }
    
    public double getAngleInRadians() {
    	return gyro.getAngle() * Math.PI/180;
    }
    public void toggle(){
    	isReversed = !isReversed;
    	
    }
    
}

