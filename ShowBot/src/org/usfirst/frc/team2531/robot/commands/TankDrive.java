package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TankDrive extends Command {

	public TankDrive() {
		requires(Robot.drive);// requires drive so no other command can use drive and alocates the subsystem
	}

	@Override
	protected void initialize() {
		System.out.println("-> TankDrive");// print status
	}

	@Override
	protected void execute() {
		double left = Robot.oi.joystick.getRawAxis(1) - Robot.oi.joystick.getRawAxis(3);// value for left motors
		double right = Robot.oi.joystick.getRawAxis(1) + Robot.oi.joystick.getRawAxis(3);// value for right motors
		if (!OI.joystick.getRawButton(1)) {
			left /= 2;
			right /= 2;
		}
		if (Math.abs(left) > 0.1 || Math.abs(right) > 0.1) {// check to see if joystick is at a usable value
			Robot.drive.tankDrive(left, right);// yes, move
		} else {
			Robot.drive.stop();// no, stop motors
		}
	}

	@Override
	protected boolean isFinished() {
		return false;// run this command infinitely
	}

	@Override
	protected void end() {
		Robot.drive.stop();// stop motors
		System.out.println("-! TankDrive");// print status
	}

	@Override
	protected void interrupted() {
		end();// when robot is interrupted run end to stop the motors
	}
}
