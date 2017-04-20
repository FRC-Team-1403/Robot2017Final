package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.RobotMap;

import org.usfirst.frc.team1403.robot.commands.DriveWithJoystick;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;


public class DriveTrain extends Subsystem {

    //4 drive train motors, two on each side
	//with the gear holder as the front, 4 and 5 are the left and are oriented negatively
	//6 and 7 are the right and are oriented positively
    public CANTalon motor4;
    public CANTalon motor5; 
    public CANTalon motor6; 
    public CANTalon motor7;
    
    //don't construct them because they are not actually their own objects
    //they are wired into the breakout boards on the talons
    public Encoder leftEncoder, rightEncoder;
    
    //gyro
    public AnalogGyro gyro;
    
    //infrared sensor, not currently used
    public AnalogInput infra;
    
    //used to change setLeftRightPower
    public boolean isReversed;
    
    public DriveTrain()
    {
    	motor4 = new CANTalon(4);
    	motor5 = new CANTalon(5);
    	motor6 = new CANTalon(6);
    	motor7 = new CANTalon(1);//original talon 7 was broken, replaced with spare talon 1
    	isReversed = false;
    	
    	//the encoders and gyro use the ports in robotmap
    	//leftEncoder = new Encoder(RobotMap.leftEncoder1, RobotMap.leftEncoder2);
    	//rightEncoder = new Encoder(RobotMap.rightEncoder1, RobotMap.rightEncoder2);
    	gyro = new AnalogGyro(RobotMap.gyro);
    	infra = new AnalogInput(1);
    	
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
    
    //dosen't have deadzones
    public void motionMappingSetLeftRightPower(double leftPower, double rightPower){
    	if(!isReversed){
 		  // if(Math.abs(leftPower)>0.10){
 		   motor4.set(-leftPower);
 		   motor5.set(-leftPower);
 		 
 		
 		   motor6.set(rightPower);
 		   motor7.set(rightPower);
 		
 	   }
 	   else {
 		  
 		   motor4.set(rightPower);
 		   motor5.set(rightPower);
 		
 			  
 		   
 		  
 		   motor6.set(-leftPower);
 		   motor7.set(-leftPower);
 		 
 			
 	   }
    	
    }
  //do not set the motors directly; always use this method because it sets the motors differently based off of which side of the robot is considered the front
    public void setLeftRightPower(double leftPower, double rightPower)
    {
	   if(!isReversed){//forward
		   
		   //if the leftPower is below 10% then don't set the motors at all
		   //called a deadzone
		   //without it the robot is too sensitive to the joystick and will make driving straight hard
		   if(Math.abs(leftPower)>0.10){
		       motor4.set(-leftPower);
		       motor5.set(-leftPower);
		   }
		   else{
			   motor4.set(0);
			   motor5.set(0);
			   
		   }
		   if(Math.abs(rightPower)>0.10){
		       motor6.set(rightPower);
		       motor7.set(rightPower);
		   
		   }
		   else{
			   motor6.set(0);
			   motor7.set(0);
			   
		   }
	   }
	   else {//reverse
		   if(Math.abs(leftPower)>0.10){
		   motor6.set(-leftPower);
		   motor7.set(-leftPower);
		   }
		   else{
			   motor6.set(0);
			   motor7.set(0);
			   
		   }
		   if(Math.abs(rightPower)>0.10){
		   motor4.set(rightPower);
		   motor5.set(rightPower);
		   }//glue
		   else{
			   motor4.set(0);
			   motor5.set(0);
			   
		   }
	   }
	   
    }
    
    //third parameter adjusts the power by a multiplier
    //useful for half speed button, mutliplier from SmartDashboard, etc.
    //don't make a multiplier negative, use the makeGearHolderFront() and makeIntakeFront() methods
    public void setLeftRightPower(double leftPower, double rightPower, double multiplier)
    {
    	this.setLeftRightPower(leftPower*multiplier, rightPower*multiplier);
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
    	//multiplied by 10 because native velocity units are per 100ms, not 1s
    }
    
    public double getRightVelocity() {
    	//return rightEncoder.getRate();
    	return motor7.getEncVelocity() * RobotMap.feetPerTick * 10;
    }
    
    public void resetEncoders() {
    	//leftEncoder.reset();
    	//rightEncoder.reset();
    	motor5.setEncPosition(0);
    	motor5.setPosition(0);
    	motor7.setEncPosition(0);
    	motor7.setPosition(0);
    }
    
    public double getAngleInRadians() {
    	return gyro.getAngle() * Math.PI/180;
    }
    public void toggle(){
    	isReversed = !isReversed;
    }
    
}

