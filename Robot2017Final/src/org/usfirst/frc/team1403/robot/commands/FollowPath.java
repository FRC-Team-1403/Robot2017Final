package org.usfirst.frc.team1403.robot.commands;

import org.usfirst.frc.team1403.robot.Robot;
import org.usfirst.frc.team1403.robot.RobotMap;
import org.usfirst.frc.team1403.robot.TrajectoryDriveController;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import trajectoryLib.trajectory.Path;

/**
 *
 */
public class FollowPath extends Command {

	TrajectoryDriveController driveController;
	Path path;
	
    public FollowPath(Path path) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.path = path;
        //kV, kA, kP, kTurn
        //1.0/RobotMap.maxVelocity
            }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driveController = new TrajectoryDriveController(1.0/RobotMap.maxVelocity, SmartDashboard.getNumber("kA", 0), SmartDashboard.getNumber("kP", 0), 0);

    	Robot.driveTrain.resetEncoders();
    	Robot.driveTrain.gyro.reset();
    	driveController.loadProfile(path.getLeftWheelTrajectory(), path.getRightWheelTrajectory(), 1.0, 0);
    	driveController.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	driveController.update();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return driveController.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	/*SmartDashboard.putNumber("Left Error", 0);
    	SmartDashboard.putNumber("Right Error", 0);
    	SmartDashboard.putNumber("Left Velocity Error", 0);
    	SmartDashboard.putNumber("Right Velocity Error", 0);*/
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
