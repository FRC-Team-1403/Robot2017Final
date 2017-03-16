package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.commands.PositiveClimb;

import com.ctre.CANTalon;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Climber extends Subsystem {

    //simple climber with one talon, EZ
	//no additional comments needed
	
	public CANTalon climber = new CANTalon(RobotMap.climber);
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

