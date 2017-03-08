package org.usfirst.frc.team1403.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CenterGearAuto extends CommandGroup {

    public CenterGearAuto() {
        addSequential(new DriveStraight(90/12.0));
        addSequential(new PushGearOutInAuto());
        addSequential(new DriveStraight(-1.0));
    }
}
