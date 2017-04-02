package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.commands.MoveCatcherWithJoystick;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class GearCatcher extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public Servo catcher = new Servo(0);
	public Servo catcher2 = new Servo(1);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MoveCatcherWithJoystick()); hello world
    }
    public void barUp(){
    	catcher.set(0.63);
    	catcher2.set(0.25);
    	
    }
   // public void 
   
    public void barDown(){
    	catcher.set(0);
    	catcher2.set(1);
    	
    	
    }
    public void useJoystick(){
    	SmartDashboard.putNumber("stick value", Robot.oi.ojoy.getRawAxis(5));
    	catcher.set(Robot.oi.ojoy.getRawAxis(5));
    	catcher2.set(Robot.oi.ojoy.getRawAxis(5));
    }
}

