package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.OI;
import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Drive3Axis extends Command {

	public Drive3Axis() {
		requires(Robot.drive);// requires drive so no other command can use drive and alocates the subsystem
	}

	@Override
	protected void initialize() {
		System.out.println("-> Drive");// print status
	}

	@Override
	protected void execute() {
		double left = OI.joystick.getRawAxis(1) - OI.joystick.getRawAxis(3);// value for left motors
		double right = OI.joystick.getRawAxis(1) + OI.joystick.getRawAxis(3);// value for right motors
		left /= 3;
		right /= 3;
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
		System.out.println("-! Drive");// print status
	}

	@Override
	protected void interrupted() {
		end();// when robot is interrupted run end to stop the motors
	}
}
