package org.usfirst.frc.team1403.robot;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import raspVision.raspInit;
import trajectoryLib.trajectory.Path;
import trajectoryLib.trajectory.PathGenerator;
import trajectoryLib.trajectory.TrajectoryGenerator;
import trajectoryLib.trajectory.WaypointSequence;
import trajectoryLib.trajectory.WaypointSequence.Waypoint;

import org.usfirst.frc.team1403.robot.commands.AutoShoot;
import org.usfirst.frc.team1403.robot.commands.AutoShootBlue;
import org.usfirst.frc.team1403.robot.commands.AutoShootRed;
import org.usfirst.frc.team1403.robot.commands.CenterGearAuto;
import org.usfirst.frc.team1403.robot.commands.ClassicAuto;
import org.usfirst.frc.team1403.robot.commands.DriveWithJoystick;
import org.usfirst.frc.team1403.robot.commands.FollowPath;
import org.usfirst.frc.team1403.robot.commands.GearAutoRight;
import org.usfirst.frc.team1403.robot.commands.GearCenter;
import org.usfirst.frc.team1403.robot.commands.LeftGearAuto;
import org.usfirst.frc.team1403.robot.commands.RightGearAuto;
import org.usfirst.frc.team1403.robot.subsystems.Climber;
import org.usfirst.frc.team1403.robot.subsystems.DriveTrain;
import org.usfirst.frc.team1403.robot.subsystems.Feeder;
import org.usfirst.frc.team1403.robot.subsystems.FlyWheel;
import org.usfirst.frc.team1403.robot.subsystems.GearCatcher;
import org.usfirst.frc.team1403.robot.subsystems.GearPusher;
import org.usfirst.frc.team1403.robot.subsystems.Intake;
import org.usfirst.frc.team1403.robot.subsystems.Light;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	//declare variables for subsystems
	public static DriveTrain driveTrain;
	public static Intake intake;
	public static GearPusher gearPusher;
	public static FlyWheel shooter;
	public static Feeder feeder;
	public static GearCatcher catcher;
	public static Climber climb;
	public static Light light;
	public static OI oi;
	public String autoselector;
	int chooserint;
	public static double x,h,irs,bottomLeg,dC,rS,hpN,totalInchHeight,nA,curve;
	public static double currentAngle,totalInchWidth,diffConversion,w,hd2,coor;
	public static double hypotenuse,subtracted,autoGyro,neededAngle;
	public static double angle,leftC,inv,turn,autodist,inchRotation;
	//public static raspInit raspinit;
	
	//declare variables for motion mapping paths
	//public static Path straightTestPath, startToGearLeft, gearToAutoLineLeft, startToGearRight, gearToAutoLineRight;
	
	Command autonomousCommand;
	SendableChooser<Command> chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		chooser = new SendableChooser<Command>();
		//construct subsystems
		CameraServer.getInstance().startAutomaticCapture();
		light = new Light();
		driveTrain = new DriveTrain();
		intake = new Intake();
		gearPusher = new GearPusher();
		shooter = new FlyWheel();
		feeder = new Feeder();
		climb = new Climber();
		catcher = new GearCatcher();
		//initialize editable SmartDashboard numbers
		SmartDashboard.putNumber("Left Power", 0);
		SmartDashboard.putNumber("Right Power", 0);
    	SmartDashboard.putNumber("Right Encoder Velocity", 0);
    	SmartDashboard.putNumber("kV", 0);
		SmartDashboard.putNumber("kA", 0);
		SmartDashboard.putNumber("kP", 0);
		SmartDashboard.putNumber("kTurn", 0);
		SmartDashboard.putNumber("Left Shooter Voltage", 0);
		SmartDashboard.putNumber("Right Shooter Voltage", 0);
		//for motion mapping
		TrajectoryGenerator.Config config = new TrajectoryGenerator.Config();
		config.max_vel = RobotMap.maxVelocity * .5;
		config.max_acc = RobotMap.maxAcceleration;
		config.max_jerk = RobotMap.maxJerk; //TODO pick a value
		config.dt = .02;
	//	Robot.driveTrain.gyro.calibrate();
		
		  //Robot.shooter.rightShooter.reverseSensor(true);
		 // Robot.shooter.rightShooter.reverseOutput(false);
		/*WaypointSequence straightTestSequence = new WaypointSequence(5);
		straightTestSequence.addWaypoint(new Waypoint(0, 0, 0));
		straightTestSequence.addWaypoint(new Waypoint(1, 0, 0));
		straightTestSequence.addWaypoint(new Waypoint(6.427, 4.88, Math.PI/3));
		straightTestPath = PathGenerator.makePath(straightTestSequence, config, RobotMap.wheelBaseWidthInFeet, "Straight Test");*/
		
		//generate auto paths
		//this runs when the code is first deployed or the RoboRIO first turns on
		/*
		WaypointSequence startToGearSequenceLeft = new WaypointSequence(5);
		startToGearSequenceLeft.addWaypoint(new Waypoint(0, 0, 0));
		startToGearSequenceLeft.addWaypoint(new Waypoint(4.927, -2.282, -Math.PI/3));
		startToGearLeft = PathGenerator.makePath(startToGearSequenceLeft, config, RobotMap.wheelBaseWidthInFeet, "Start To Gear");
		
		WaypointSequence gearToAutoLineSequenceLeft = new WaypointSequence(5);
		gearToAutoLineSequenceLeft.addWaypoint(new Waypoint(0, 0, 0));
		gearToAutoLineSequenceLeft.addWaypoint(new Waypoint(3.33333, -2.291667, -Math.PI/3));
		gearToAutoLineSequenceLeft.addWaypoint(new Waypoint(6.66666, -4.58333, -Math.PI/3));
		gearToAutoLineLeft = PathGenerator.makePath(gearToAutoLineSequenceLeft, config, RobotMap.wheelBaseWidthInFeet, "Straight Test");
		
		WaypointSequence startToGearSequenceRight = new WaypointSequence(5);
		startToGearSequenceRight.addWaypoint(new Waypoint(0, 0, 0));
		startToGearSequenceRight.addWaypoint(new Waypoint(4.927, 2.282, Math.PI/3));
		startToGearRight = PathGenerator.makePath(startToGearSequenceRight, config, RobotMap.wheelBaseWidthInFeet, "Start To Gear");
		
		WaypointSequence gearToAutoLineSequenceRight = new WaypointSequence(5);
		gearToAutoLineSequenceRight.addWaypoint(new Waypoint(0, 0, 0));
		gearToAutoLineSequenceRight.addWaypoint(new Waypoint(3.33333, 2.291667, Math.PI/3));
		gearToAutoLineSequenceRight.addWaypoint(new Waypoint(6.66666, 4.58333, Math.PI/3));
		gearToAutoLineRight = PathGenerator.makePath(gearToAutoLineSequenceRight, config, RobotMap.wheelBaseWidthInFeet, "Straight Test");
		*/
		
		
		
		chooser.addDefault("line", new ClassicAuto());
		chooser.addObject("left", new LeftGearAuto());
		chooser.addObject("center", new CenterGearAuto());
		chooser.addObject("shoot red", new AutoShootRed());
		chooser.addObject("shoot blue", new AutoShootBlue());
		chooser.addObject("Gear Center", new GearCenter());
	//	chooser.addObject(", object);
		
		SmartDashboard.putData("Auto mode", chooser);
		
		//LAST FOR A REASON.......... DO NOT CHANGE!!!!!!!!!!!!!!!!!!!!!!!!
		oi = new OI();
		//I REPEAT... DO NOT CHANGE THIS AND DO NOT ADD ANYTHING AFTER THIS....
		//only for pi
	//	Timer.delay(5);
	//	rasp_init = new raspInit("ssh pi@raspberrypi.local");
		
		//oi has to be constructed last because it contains references to other subsystems
		//the code will have null pointers if oi is constructed before the other subsystems
	//	autonomous
		
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * 
	 * 
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {
		chooserint=0;
		//raspInit.release(raspinit.p);
		Robot.gearPusher.retract();
	}

	@Override
	public void disabledPeriodic() {
		if(Robot.oi.ojoy.getButtonPressedOneShot(1)){
			//if(Robot.oi.ojoy.(1))
			chooserint++;
			SmartDashboard.putNumber("chooserint", chooserint%7);
		}
		
		switch(chooserint%7){
		case 0: SmartDashboard.putString("Auto Selector", "Line");break;
		case 1: SmartDashboard.putString("Auto Selector", "Left");break;
		case 2: SmartDashboard.putString("Auto Selector", "Center");break;
		case 3: SmartDashboard.putString("Auto Selector", "Shoot Red");break;
		case 4: SmartDashboard.putString("Auto Selector", "Shoot Blue");break;
		case 5: SmartDashboard.putString("Auto Selector", "Gear Center");break;
		case 6: SmartDashboard.putString("Auto Selector", "Right");break;
		}
		
		Scheduler.getInstance().run();
	}
	@Override
	public void autonomousInit() {
		
		
		
		//rasp_init = new raspInit("ssh pi@raspberrypi.local");
		//intialize all vision variables
		driveTrain.gyro.reset();
		turn = Math.atan(Math.abs(autodist/diffConversion))*180/Math.PI;
		neededAngle = 90-turn;
		inchRotation = (3.93/(8*Math.PI));
		//Robot.driveTrain.leftEncoder.reset();
		//Robot.driveTrain.rightEncoder.reset();
		x = SmartDashboard.getNumber("difference", 321);
		w = SmartDashboard.getNumber("width", 321);
		h = SmartDashboard.getNumber("height", 241);
		totalInchHeight = 1200/h; //conversion of Robot.x to inches from starting autonomous position
		totalInchWidth = 640/w;
		autodist = 50; //distance of vertical leg in right triangle
		diffConversion = (Math.abs((x*totalInchWidth)/320))-6.5;
		dC = (Math.abs((x*totalInchWidth)/320))+2;
		hypotenuse = Math.sqrt((Math.pow(diffConversion, 2))+(Math.pow(autodist, 2)));
		hpN = Math.sqrt((Math.pow(dC, 2))+(Math.pow(autodist, 2)));
		curve = Math.atan(Math.abs(autodist/dC))*180/Math.PI;
		nA = 90-curve;

		// schedule the autonomous command (example)
		//TODO motion mapping command group?
		//autonomousCommand = new CenterGearAuto();
		//autoselector = SmartDashboard.getString("auto", "center");
		/*if(autoselector.equals("line")){
		autonomousCommand = new ClassicAuto();
		}
		else if(autoselector.equals("center")){
			autonomousCommand = new CenterGearAuto();
						}
		else if(autoselector.equals("left")){
			autonomousCommand = new LeftGearAuto();
			}
		else {
			System.out.println("Houston we have a problem");
		}*/
	//	autonomousCommand = new LeftGearAuto();
		
		
		autonomousCommand = new ClassicAuto();
		
		switch(chooserint%7){
		case 0: autonomousCommand = new ClassicAuto();break;
		case 1: autonomousCommand = new LeftGearAuto();break;
		case 2: autonomousCommand = new CenterGearAuto();break;
		case 3: autonomousCommand = new AutoShootRed();break;
		case 4: autonomousCommand = new AutoShootBlue();break;
		case 5: autonomousCommand = new GearCenter();break;
		case 6: autonomousCommand = new RightGearAuto();break;
		}
		
		if (autonomousCommand != null)
			
			autonomousCommand.start();
		
		
		
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		//this auto generated line is what constantly runs all scheduled commands throughout the game
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("GYRO GYRO GYRO .... AAA111", Robot.driveTrain.gyro.getAngle());
		
		//update vision variables as autonomous runs
		leftC = SmartDashboard.getNumber("x value", 321);
		angle = Robot.driveTrain.gyro.getAngle();
		irs = Robot.driveTrain.infra.getValue();
		inv = 60.374*Math.pow(Robot.driveTrain.infra.getValue()/1000, -1.1068);
		autoGyro = driveTrain.gyro.getAngle();
		subtracted = Math.abs(neededAngle) - Math.abs(angle);
		rS = Math.abs(angle-nA);
		
		SmartDashboard.putNumber("Left Position from Encoder", Robot.driveTrain.getLeftPosition());
    	SmartDashboard.putNumber("Right Position from Encoder", Robot.driveTrain.getRightPosition());
    	SmartDashboard.putNumber("Left Velocity from Encoder", Robot.driveTrain.getLeftVelocity());
    	SmartDashboard.putNumber("Right Velocity from Encoder", Robot.driveTrain.getRightVelocity());
    	SmartDashboard.putNumber("Left Output", -Robot.oi.djoy.getRawAxis(1));
    	SmartDashboard.putNumber("Right Output", -Robot.oi.djoy.getRawAxis(5));
		
	}

	@Override
	public void teleopInit() {
		
		if (autonomousCommand != null)
			autonomousCommand.cancel();
		Robot.driveTrain.gyro.reset();
		Robot.driveTrain.resetEncoders();
		
	//	Robot.driveTrain.leftEncoder.reset();
	//	Robot.driveTrain.leftEncoder.reset();
		Robot.driveTrain.isReversed = false;
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		//this auto generated line is what constantly runs all scheduled commands throughout the game
		Scheduler.getInstance().run();
		
		SmartDashboard.putNumber("GYRO DEGREES", Robot.driveTrain.gyro.getAngle());
		
		//display the RPM of the shooters on the smart dashboard
		SmartDashboard.putNumber("LeftRPM", Robot.shooter.getLeftRPM()/(6*Math.PI));
		SmartDashboard.putNumber("RightRPM", Robot.shooter.getRightRPM()/(6*Math.PI));
		
		SmartDashboard.putNumber("Raw Left", Robot.driveTrain.motor5.getAnalogInPosition());
		SmartDashboard.putNumber("Raw Right", Robot.driveTrain.motor7.getAnalogInPosition());
	/*	x = SmartDashboard.getNumber("difference", 321);
		w = SmartDashboard.getNumber("width", 321);
		h = SmartDashboard.getNumber("height", 241);
		leftC = SmartDashboard.getNumber("x value", 321);
	//	hd2 = SmartDashboard.getNumber("horizontal distance", 321);
		angle = Robot.driveTrain.gyro.getAngle();
		irs = Robot.driveTrain.infra.getValue();
		inv = 60.374*Math.pow(Robot.driveTrain.infra.getValue()/1000, -1.1068);
		//IR units to cm
	    	SmartDashboard.putNumber("testX", x);
	    totalInchHeight = 1200/h; //conversion of Robot.x to inches from starting autonomous position
	    totalInchWidth = 640/w;
	    diffConversion = (x*totalInchWidth)/320;
	//  System.out.println(diffConversion);
	//  System.out.println("Robot.turn = " + turn);
	    System.out.println("Infrared value = " + irs);
	    autodist = 70; //distance in inches
	    turn = Math.atan(Math.abs(autodist/diffConversion))*180/Math.PI;
	    inchRotation = (256/(6*Math.PI)); //equals inch using encoder
	    hypotenuse = Math.sqrt((Math.pow(diffConversion, 2))+(Math.pow(autodist, 2)));*/
		
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override 
	public void testPeriodic() {
		LiveWindow.run();
	}
}

