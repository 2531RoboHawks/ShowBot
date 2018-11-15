package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;
import org.usfirst.frc.team2531.robot.RobotMap;

import edu.wpi.first.wpilibj.command.Command;
import frclib.pid.PID;

/**
 *
 */
public class TimeDrive extends Command {
	private double angle;
	private long timeDrive;
	private long currentTime;
	private PID pid = new PID(0.05, 0, 0, 0);
	private double heading;

	public TimeDrive(long time, double head) {
		requires(Robot.drive);
		heading = head;
		timeDrive = time * 1000;
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		pid.setOutputLimits(-0.5, 0.5);
		currentTime = System.currentTimeMillis();
		System.out.println("-> TimeDrive");
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		angle = RobotMap.imu.getAngleZ();
		double outputPower = pid.compute(heading - angle);
		Robot.drive.tankDrive(-.5 + outputPower, -.5 - outputPower);

	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return (System.currentTimeMillis() >= timeDrive + currentTime);
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.drive.stop();
		System.out.println("-! TimeDrive");
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		end();
	}
}
