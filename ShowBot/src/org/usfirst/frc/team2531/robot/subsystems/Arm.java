package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	private Solenoid valve = new Solenoid(0);

	public void initDefaultCommand() {

	}

	public void set(boolean on) {
		valve.set(on);
	}
}
