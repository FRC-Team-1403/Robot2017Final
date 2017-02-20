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
	public static double x,h,irs,bottomLeg,dC,rS,hpN,totalInchHeight,nA,curve;
	public static double currentAngle,totalInchWidth,diffConversion,w,hd2,coor;
	public static double hypotenuse,subtracted,autoGyro,neededAngle;
	public static double angle,leftC,inv,turn,autodist,inchRotation;
	
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
	@Override
	public void autonomousInit() {
		//autonomousCommand = chooser.getSelected();
		
		driveTrain.gyro.reset();
		turn = Math.atan(Math.abs(autodist/diffConversion))*180/Math.PI;
		neededAngle = 90-turn;
		inchRotation = (3.93/(8*Math.PI));
		Robot.driveTrain.leftEncoder.reset();
		Robot.driveTrain.rightEncoder.reset();
		x = SmartDashboard.getNumber("difference", 321);
		w = SmartDashboard.getNumber("width", 321);
		h = SmartDashboard.getNumber("height", 241);
		totalInchHeight = 1200/h; //conversion of Robot.x to inches from starting autonomous position
		totalInchWidth = 640/w;
		autodist = 52; //distance in inches
		diffConversion = (Math.abs((x*totalInchWidth)/320))-6.5;
		dC = (Math.abs((x*totalInchWidth)/320))+2;
		hypotenuse = Math.sqrt((Math.pow(diffConversion, 2))+(Math.pow(autodist, 2)));
		hpN = Math.sqrt((Math.pow(dC, 2))+(Math.pow(autodist, 2)));
		curve = Math.atan(Math.abs(autodist/dC))*180/Math.PI;
		nA = 90-curve;

		// schedule the autonomous command (example)
		autonomousCommand = new DriveWithJoystick();
		if (autonomousCommand != null)
			autonomousCommand.start();
		
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		leftC = SmartDashboard.getNumber("x value", 321);
		angle = Robot.driveTrain.gyro.getAngle();
		irs = Robot.driveTrain.infra.getValue();
		inv = 60.374*Math.pow(Robot.driveTrain.infra.getValue()/1000, -1.1068);
		autoGyro = driveTrain.gyro.getAngle();
		subtracted = Math.abs(neededAngle) - Math.abs(angle);
		rS = Math.abs(angle-nA);
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
		Robot.driveTrain.gyro.reset();
		Robot.driveTrain.leftEncoder.reset();
		Robot.driveTrain.leftEncoder.reset();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		
		Scheduler.getInstance().run();
		x = SmartDashboard.getNumber("difference", 321);
		w = SmartDashboard.getNumber("width", 321);
		h = SmartDashboard.getNumber("height", 241);
		leftC = SmartDashboard.getNumber("x value", 321);
	//	hd2 = SmartDashboard.getNumber("horizontal distance", 321);
		angle = Robot.driveTrain.gyro.getAngle();
		irs = Robot.driveTrain.infra.getValue();
		inv = 60.374*Math.pow(Robot.driveTrain.infra.getValue()/1000, -1.1068);
		//IR units to cm
	    	SmartDashboard.putDouble("testX", x);
	    totalInchHeight = 1200/h; //conversion of Robot.x to inches from starting autonomous position
	    totalInchWidth = 640/w;
	    diffConversion = (x*totalInchWidth)/320;
	//  System.out.println(diffConversion);
	//  System.out.println("Robot.turn = " + turn);
	    System.out.println("Infrared value = " + irs);
	    autodist = 70; //distance in inches
	    turn = Math.atan(Math.abs(autodist/diffConversion))*180/Math.PI;
	    inchRotation = (256/(6*Math.PI)); //equals inch using encoder
	    hypotenuse = Math.sqrt((Math.pow(diffConversion, 2))+(Math.pow(autodist, 2)));
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override 
	public void testPeriodic() {
		LiveWindow.run();
	}
}

