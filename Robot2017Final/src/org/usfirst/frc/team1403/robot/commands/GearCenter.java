package org.usfirst.frc.team1403.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class GearCenter extends CommandGroup {

    public GearCenter() {
        // Add Commands here:
    //	addSequential(new RetractGearPusher());
    	addSequential(new DriveTimeGyro(0.3), 5.5);
    	addSequential(new PushGearOutInAuto());
        addSequential(new DriveWithTime(1, -0.4));
    //    addSequential(new RetractGearPusher());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commansds at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    }
}
