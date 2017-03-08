package org.usfirst.frc.team1403.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Light extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Spark smotor = new Spark(10);//change later....
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void turnOn(){
    	smotor.set(1);
    	
    }
    public void turnOff(){
    	smotor.set(0);
    }
}

