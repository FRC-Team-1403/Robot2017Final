package org.usfirst.frc.team1403.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class VoltageAllSteps extends CommandGroup {

    public VoltageAllSteps() {
    	//addSequential(new VoltageShooter());
    	addParallel(new RunFeeders());
    	addSequential(new MaintainShooters());
    	
    }

   
}
