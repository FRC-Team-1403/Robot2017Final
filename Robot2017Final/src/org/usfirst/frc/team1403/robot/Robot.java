
package org.usfirst.frc.team1403.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import trajectoryLib.trajectory.Path;
import trajectoryLib.trajectory.PathGenerator;
import trajectoryLib.trajectory.TrajectoryGenerator;
import trajectoryLib.trajectory.WaypointSequence;
import trajectoryLib.trajectory.WaypointSequence.Waypoint;

import org.usfirst.frc.team1403.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team1403.robot.commands.FollowPath;
import org.usfirst.frc.team1403.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1403.robot.subsystems.Feeder;
import org.usfirst.frc.team1403.robot.subsystems.FlyWheel;
import org.usfirst.frc.team1403.robot.subsystems.GearPusher;
import org.usfirst.frc.team1403.robot.subsystems.Intake;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	public static DriveTrain driveTrain;
	public static Intake intake;
	public static GearPusher gearPusher;
	public static FlyWheel shooter;
	public static Feeder feeder;
	public static OI oi;
	
	public static Path straightTestPath;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser = new SendableChooser<>();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		driveTrain = new DriveTrain();
		intake = new Intake();
		gearPusher = new GearPusher();
		shooter = new FlyWheel();
		feeder = new Feeder();
		
		//initialize editable SmartDashboard numbers
		SmartDashboard.putNumber("Left Power", 0);
		SmartDashboard.putNumber("Right Power", 0);
    	SmartDashboard.putNumber("Right Encoder Velocity", 0);
    	SmartDashboard.putNumber("kV", 0);
		SmartDashboard.putNumber("kA", 0);
		SmartDashboard.putNumber("kP", 0);
		SmartDashboard.putNumber("kTurn", 0);
		
		//for motion mapping
		TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
		config.max_vel = RobotMap.maxVelocity * .5;
		config.max_acc = RobotMap.maxAcceleration;
		config.max_jerk = RobotMap.maxJerk; //TODO pick a value
		config.dt = .02;
		
		WaypointSequence straightTestSequence = new WaypointSequence(5);
		straightTestSequence.addWaypoint(new Waypoint(0, 0, 0));
		straightTestSequence.addWaypoint(new Waypoint(1, 0, 0));
		straightTestSequence.addWaypoint(new Waypoint(6.427, 4.88, Math.PI/3));
		straightTestPath = PathGenerator.makePath(straightTestSequence, config, RobotMap.wheelBaseWidthInFeet, "Straight Test");
		
		oi = new OI();
		
		//chooser.addDefault("Default Auto", new ExampleCommand());
		// chooser.addObject("My Auto", new MyAutoCommand());
		SmartDashboard.putData("Auto mode", chooser);
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		//autonomousCommand = chooser.getSelected();
		autonomousCommand = new DriveWithJoystick();
		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */

		// schedule the autonomous command (example)
		if (autonomousCommand != null)
			autonomousCommand.start();
		
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override 
	public void testPeriodic() {
		LiveWindow.run();
	}
}
