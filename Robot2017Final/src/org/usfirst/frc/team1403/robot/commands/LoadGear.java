package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class LoadGear extends CommandGroup {

    public LoadGear() {
        addSequential(new TilterOut());
        addSequential(new RetractGearPusher());
        
 
    }
}
