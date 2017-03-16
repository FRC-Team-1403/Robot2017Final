package org.usfirst.frc.team1403.robot.subsystems;

import org.usfirst.frc.team1403.robot.RobotMap;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class GearPusher extends Subsystem {

	//The gear holder is controlled by two solenoids
	//pusher pushes the gear out
	//tilter orients the gear holder to be straight when placing a gear and tilted when receiving a gear
	public DoubleSolenoid pusher;
	public DoubleSolenoid tilter;
	
	public GearPusher() {
		pusher = new DoubleSolenoid(RobotMap.gearPusher2, RobotMap.gearPusher1);
		tilter = new DoubleSolenoid(RobotMap.gearTilter1, RobotMap.gearTilter2);
	}
	
	public void push() {
		pusher.set(DoubleSolenoid.Value.kForward);
	}
	
	public void retract() {
		pusher.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void off() {
		pusher.set(DoubleSolenoid.Value.kOff);
	}
	
	public void tilterOut() {
		tilter.set(DoubleSolenoid.Value.kForward);
	}
	
	public void tilterStraight() {
		tilter.set(DoubleSolenoid.Value.kReverse);
	}
	
	public void tilterOff() {
		tilter.set(DoubleSolenoid.Value.kOff);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

