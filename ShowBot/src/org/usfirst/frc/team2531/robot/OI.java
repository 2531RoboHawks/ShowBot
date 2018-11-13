package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.Launch;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class OI {
	// joystick definitions
	public static Joystick joystick = new Joystick(0);

	// button definitions
	public JoystickButton act = new JoystickButton(joystick, 1);

	public OI() {
		act.whileHeld(new Launch());// when the button is heldown run the command when button is released interupt
		// the command
	}
}
