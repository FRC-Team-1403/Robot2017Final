package org.usfirst.frc.team1403.robot;

import org.usfirst.frc.team1403.robot.commands.CANPowerUp;
import org.usfirst.frc.team1403.robot.commands.DriveWithButton;
import org.usfirst.frc.team1403.robot.commands.DriveWithButtonFast;
import org.usfirst.frc.team1403.robot.commands.DriveWithOneJoystick;
import org.usfirst.frc.team1403.robot.commands.FollowPath;
import org.usfirst.frc.team1403.robot.commands.GearAutoLeft;
import org.usfirst.frc.team1403.robot.commands.GearAutoRight;
import org.usfirst.frc.team1403.robot.commands.HumanPlayer;
import org.usfirst.frc.team1403.robot.commands.LetGo;
import org.usfirst.frc.team1403.robot.commands.LoadBall;
import org.usfirst.frc.team1403.robot.commands.PushGearOut;
import org.usfirst.frc.team1403.robot.commands.RetractGearPusher;
import org.usfirst.frc.team1403.robot.commands.RollersIn;
import org.usfirst.frc.team1403.robot.commands.RollersOut;
import org.usfirst.frc.team1403.robot.commands.RunFeeders;
import org.usfirst.frc.team1403.robot.commands.STOPMOTORSSHOOTERS;
import org.usfirst.frc.team1403.robot.commands.Shoot;
import org.usfirst.frc.team1403.robot.commands.VisionAimAssist;
import org.usfirst.frc.team1403.robot.commands.PusherOff;
import org.usfirst.frc.team1403.robot.commands.TilterOff;
import org.usfirst.frc.team1403.robot.commands.TilterOut;
import org.usfirst.frc.team1403.robot.commands.TilterStraight;
import org.usfirst.frc.team1403.robot.commands.Travel;
import org.usfirst.frc.team1403.robot.commands.VoltageAllSteps;

import CougarLibrary.JoystickAnalogButton;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
//TEST TEST TEST TEST TEST

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	//djoy is the driver's configuration at competition
	public Joystick djoy = new Joystick(0);
	public JoystickButton djoyA = new JoystickButton(djoy, 1);
	public JoystickButton djoyB = new JoystickButton(djoy, 2);
	public JoystickButton djoyX = new JoystickButton(djoy, 3);
	public JoystickButton djoyY = new JoystickButton(djoy, 4);
	public JoystickButton djoyL = new JoystickButton(djoy, 5);
	public JoystickButton djoyR = new JoystickButton(djoy, 6);
	public JoystickButton djoyBack = new JoystickButton(djoy, 7);
	public JoystickButton djoyStart = new JoystickButton(djoy, 8);
	public JoystickButton djoyLJB = new JoystickButton(djoy, 9);
	public JoystickButton djoyRJB = new JoystickButton(djoy, 10);
	public JoystickAnalogButton djoyLT = new JoystickAnalogButton(djoy,2,0.5);
	public JoystickAnalogButton djoyRT = new JoystickAnalogButton(djoy,3,0.5);
	
	
	//ojoy is the operator's configuration at competition
	//only bind necessary functionality to ojoy, like command groups
	//do not bind basic functionality that wouldn't be used in a match, do that in tjoy
	public Joystick ojoy = new Joystick(1);
	public JoystickButton ojoyA = new JoystickButton(ojoy, 1);
	public JoystickButton ojoyB = new JoystickButton(ojoy, 2);
	public JoystickButton ojoyX = new JoystickButton(ojoy, 3);
	public JoystickButton ojoyY = new JoystickButton(ojoy, 4);
	public JoystickButton ojoyL = new JoystickButton(ojoy, 5);
	public JoystickButton ojoyR = new JoystickButton(ojoy, 6);
	public JoystickButton ojoyBack = new JoystickButton(ojoy, 7);
	public JoystickButton ojoyStart = new JoystickButton(ojoy, 8);
	public JoystickButton ojoyLJB = new JoystickButton(ojoy, 9);
	public JoystickButton ojoyRJB = new JoystickButton(ojoy, 10);
	public JoystickAnalogButton ojoyLT = new JoystickAnalogButton(ojoy,2,0.5);
	public JoystickAnalogButton ojoyRT = new JoystickAnalogButton(ojoy,3,0.5);

	
	//tjoy is for testing purposes
	//bind all the basic functionality of all the subsystems except the drive train to here
	//if something breaks or needs to be tested, plug a joystick into port 2 in order to access test commands
	//tjoy should not be used during matches
	public Joystick tjoy = new Joystick(2);
	public JoystickButton tjoyA = new JoystickButton(tjoy, 1);
	public JoystickButton tjoyB = new JoystickButton(tjoy, 2);
	public JoystickButton tjoyX = new JoystickButton(tjoy, 3);
	public JoystickButton tjoyY = new JoystickButton(tjoy, 4);
	public JoystickButton tjoyL = new JoystickButton(tjoy, 5);
	public JoystickButton tjoyR = new JoystickButton(tjoy, 6);
	public JoystickButton tjoyBack = new JoystickButton(tjoy, 7);
	public JoystickButton tjoyStart = new JoystickButton(tjoy, 8);
	public JoystickButton tjoyLJB = new JoystickButton(tjoy, 9);
	public JoystickButton tjoyRJB = new JoystickButton(tjoy, 10);
	public JoystickAnalogButton tjoyLT = new JoystickAnalogButton(tjoy,2,0.5);
	public JoystickAnalogButton tjoyRT = new JoystickAnalogButton(tjoy,3,0.5);
	
	
	public OI(){
		//bind buttons to commands
		
		djoyX.whileHeld(new DriveWithButtonFast());
		djoyA.whileHeld(new DriveWithButton());
		djoyLT.whileHeld(new VisionAimAssist());
		
		ojoyRT.whileHeld(new Shoot());
		//ojoyRT.whenReleased(new STOPMOTORSSHOOTERS());
		ojoyY.whileHeld(new RunFeeders());
		ojoyB.whileHeld(new CANPowerUp());
		
		//ojoyA.whileHeld(new RollersOut());
		ojoyR.whenPressed(new LetGo());
		ojoyL.whileHeld(new HumanPlayer());
		ojoyLT.whileHeld(new RollersIn());
		ojoyLJB.whenPressed(new LoadBall());
		ojoyStart.whileHeld(new Travel());
		ojoyBack.whileHeld(new RunFeeders());
		
		djoyA.whenPressed(new FollowPath(Robot.straightTestPath));
		djoyY.whileHeld(new DriveWithOneJoystick());
		
		tjoyY.whenPressed(new PushGearOut());
		tjoyA.whenPressed(new RetractGearPusher());
		tjoyB.whenPressed(new PusherOff());
		tjoyL.whenPressed(new TilterOut());
		tjoyR.whenPressed(new TilterStraight());
		tjoyX.whenPressed(new TilterOff());
		
		tjoyBack.whenPressed(new GearAutoLeft());
		tjoyStart.whenPressed(new GearAutoRight());
	
	}
}
