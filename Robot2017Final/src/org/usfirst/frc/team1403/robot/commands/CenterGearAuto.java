package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *Moves forward until it gets to the lift
 *push gear out and wait 1 second
 *drive backward 2 feet
 */
public class CenterGearAuto extends CommandGroup {

    public CenterGearAuto() {
    	//Robot.driveTrain.resetEncoders();
      addSequential(new MoveToPlaceGear(57.3/12.0));
        addSequential(new PushGearOutInAuto());
        addSequential(new DriveStraight(-2));

    }
}
