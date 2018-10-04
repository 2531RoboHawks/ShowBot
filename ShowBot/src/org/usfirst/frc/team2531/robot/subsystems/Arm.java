package org.usfirst.frc.team2531.robot.subsystems;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {

	private Solenoid valveOn = new Solenoid(0);
	private Solenoid valveOff = new Solenoid(1);

	public void initDefaultCommand() {

	}

	public void set(boolean on) {
		// set states
		valveOn.set(on);
		valveOff.set(!on);
	}
}
