package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.commands.PositiveClimb;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	public CANTalon climber = new CANTalon(11);
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new PositiveClimb());
    }
    
    
    public void climbwithJoystick(){
    	climber.set(Robot.oi.ojoy.getRawAxis(1));
    }
  
    public void climbstop(){
    	climber.set(0);
    }
}

