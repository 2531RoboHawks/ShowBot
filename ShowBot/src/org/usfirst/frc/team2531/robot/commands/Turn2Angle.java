package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class Turn2Angle extends Command {
	private PID pid = new PID(0.05, 0, 0, 0);
	private double angle;

	public Turn2Angle(double degrees) {
		requires(Robot.drive);
		angle = degrees;
		pid.setOutputLimits(-0.5, 0.5);
		pid.setOnTargetCount(10);
		pid.setOnTargetOffset(2);

		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("-> Turn2Angle"); // Corissa was here
		pid.setSetpoint(angle);
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		double outputPower = pid.compute(RobotMap.imu.getAngleZ());
		Robot.drive.tankDrive(-outputPower, outputPower);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return pid.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		RobotMap.imu.reset();
		Robot.drive.stop();
		System.out.println("-! Turn2Angle");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();

	}
}
