package org.usfirst.frc.team1403.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	
	//Robot Map is the place for all the ports and constants
	//All are static
	//We're supposed to construct the objects here but... it doesn't work? LOLIDK
	
	//motion mapping constants
	//public static double feetPerTick = Math.PI/3072;
	public static double feetPerTick = 0.0010047574;
	public static double wheelBaseWidthInFeet = 21.125;
	public static double maxVelocity = 12.5;
	public static double maxAcceleration = 5;
	public static double maxJerk = 11.2;
	
	//drive train ports
	/*public static int leftEncoder1 = 0;
	public static int leftEncoder2 = 0;
	public static int rightEncoder1 = 0;
	public static int rightEncoder2 = 0;*/
	public static int gyro = 0;
	
	//shooter ports
	public static int leftFlyWheel = 9;
	public static int rightFlyWheel = 10;
	/*public static int leftShooterEncoder1 = 0;
	public static int leftShooterEncoder2 = 0;
	public static int rightShooterEncoder1 = 0;
	public static int rightShooterEncoder2 = 0;*/
	
	public static double leftshooterRPM = -3304;// lol
	public static double rightshooterRPM = 3304;
	
	public static double rotationsPerTick = 256;
	
	//feeder ports
	public static int leftFeeder = 3;
	public static int rightFeeder = 2;
	//public static double feederTickConstant = 0;
	
	//intake ports
	public static int intakeRoller = 0;
	
	//gear pusher ports
	public static int gearPusher1 = 2;
	public static int gearPusher2 = 1;
	public static int gearTilter1 = 3;
	public static int gearTilter2 = 4;
	// we will figure this out later i gues....
	
	//climber ports
	public static int climber = 8;
	
}
