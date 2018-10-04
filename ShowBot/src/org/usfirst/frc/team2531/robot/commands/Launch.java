package org.usfirst.frc.team2531.robot.commands;

import org.usfirst.frc.team2531.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class Launch extends Command {

	public Launch() {
		requires(Robot.arm);// requires arm subsystem
	}

	protected void initialize() {
		System.out.println("-> Launch");
	}

	protected void execute() {
		Robot.arm.set(true);// open valve
	}

	protected boolean isFinished() {
		return false;// run infinitely
	}

	protected void end() {
		Robot.arm.set(false);// close valve
		System.out.println("-! Launch");
	}

	protected void interrupted() {
		end();// run end when interrupted
	}
}
