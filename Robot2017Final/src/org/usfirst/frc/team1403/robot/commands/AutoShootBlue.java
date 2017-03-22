package org.usfirst.frc.team1403.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoShootBlue extends CommandGroup {

    public AutoShootBlue() {
        // Add Commands here:
      addParallel(new VoltageHack(), 7);
      addSequential(new RunFeeders(), 7);
     
      addSequential(new DriveStraight(-1));
      addSequential(new TurnInPlace(-45));
      addSequential(new DriveStraight(-6));
      

        // To run multiple commands at the same time,
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
