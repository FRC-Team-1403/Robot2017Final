package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class Shoot2 extends Command {
	double leftrpm = -3304;
	double rightrpm = 3304;
	double leftvalue = 0;
	double rightvalue = 0;
	double leftcurrentRate = 0;
	double rightcurrentRate = 0;
	double lefttargetRate = (leftrpm/0.24);
	double righttargetRate = (rightrpm/0.24);
    public Shoot2() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.shooter);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.shooter.leftShooter.set(-.65);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	leftcurrentRate = Robot.shooter.leftShooter.getEncVelocity();
    	if(!(Math.abs(leftcurrentRate-lefttargetRate)<200)){
			if((leftcurrentRate-lefttargetRate)>1000){
				leftvalue +=0.01;
				Robot.shooter.leftShooter.set(leftvalue);
			}
			else if((leftcurrentRate-lefttargetRate)<1000){
				leftvalue -=0.01;
				Robot.shooter.leftShooter.set(leftvalue);
			}
			else if((leftcurrentRate-lefttargetRate)>500){
				leftvalue +=0.005;
				Robot.shooter.leftShooter.set(leftvalue);
			}
			else if((leftcurrentRate-lefttargetRate)<500){
				leftvalue -=0.005;
				Robot.shooter.leftShooter.set(leftvalue);
			}
			
			
			
			
    	}
    	
    	rightcurrentRate = Robot.shooter.rightShooter.getEncVelocity();
    	if(!(Math.abs(rightcurrentRate-righttargetRate)<200)){
			if((rightcurrentRate-righttargetRate)>1000){
				rightvalue -=0.01;
				Robot.shooter.rightShooter.set(rightvalue);
			}
			else if((rightcurrentRate-righttargetRate)<1000){
				rightvalue +=0.01;
				Robot.shooter.rightShooter.set(rightvalue);
			}
			else if((rightcurrentRate-righttargetRate)>500){
				rightvalue -=0.005;
				Robot.shooter.rightShooter.set(rightvalue);
			}
			else if((rightcurrentRate-righttargetRate)<500){
				rightvalue +=0.005;
				Robot.shooter.rightShooter.set(rightvalue);
			}
			
			
			
			
    	}
    	
    	SmartDashboard.putNumber(" Left Current Rate", leftcurrentRate);
    	SmartDashboard.putNumber("Right Current Rate", rightcurrentRate);
		//SmartDashboard.putNumber("rpm based on Current Rate", currentRate);
		SmartDashboard.putNumber("Left Target Rate", lefttargetRate);
		SmartDashboard.putNumber("Right Target Rate", righttargetRate);
		SmartDashboard.putNumber("Left set value", leftvalue);
		SmartDashboard.putNumber("Right set value", rightvalue);
		SmartDashboard.putNumber("LEFT rpm", leftcurrentRate*0.24);
		SmartDashboard.putNumber("RIGHT rpm", rightcurrentRate*0.24);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	
    return (Math.abs(leftcurrentRate-lefttargetRate)<200) && (Math.abs(rightcurrentRate-righttargetRate)<200);
    }

    // Called once after isFinished returns true
    protected void end() {
    	SmartDashboard.putBoolean("Left RPM is Good", true);
    	SmartDashboard.putBoolean("Right RPM is Good", true);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.shooter.leftShooter.set(0);
    	Robot.shooter.rightShooter.set(0);
    }
}
