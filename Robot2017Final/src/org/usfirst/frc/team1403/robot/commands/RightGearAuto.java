package org.usfirst.frc.team1403.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class RightGearAuto extends CommandGroup {

    public RightGearAuto() {
        // Add Commands here:
    	addSequential(new MoveToPlaceGear(62.0/12.0));
     //   addSequential(new DriveStraight(57.0/12.0, 0.3));
    	addSequential(new TurnInPlace(-57.0));
    	addSequential(new MoveToPlaceGear(45.0/12.0));
    //	addSequential(new DriveStraight(45.0/12.0, 0.3));
    	addSequential(new VisionAimAssist());
        addSequential(new DriveStraight(96.0/12.0, 0.3), 2);
        addSequential(new PushGearOutInAuto());
        addSequential(new DriveStraight(-2, 0.4));
        addSequential(new TurnInPlace(55.0));
        addSequential(new DriveStraight(10, 1));

        //      addSequential(new Command2());
        // these will run in order.

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
