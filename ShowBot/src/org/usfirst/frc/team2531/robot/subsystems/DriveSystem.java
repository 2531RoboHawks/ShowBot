package org.usfirst.frc.team2531.robot.subsystems;

import org.usfirst.frc.team2531.robot.commands.Drive3Axis;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;

public class DriveSystem extends Subsystem {

	// motor speed controller declarations
	private Spark left_motor1 = new Spark(0);
	private Spark left_motor2 = new Spark(1);
	private Spark right_motor1 = new Spark(2);
	private Spark right_motor2 = new Spark(3);

	public void initDefaultCommand() {
		setDefaultCommand(new Drive3Axis());// set default command so we can drive all the time
	}

	public void tankDrive(double left, double right) {
		// set motor power for left and right sides
		left_motor1.set(left);
		left_motor2.set(left);
		right_motor1.set(right);
		right_motor2.set(right);
	}

	public void arcadeDrive(double power, double steering) {
		// set motor power for left and right sides
		left_motor1.set(power - steering);
		left_motor2.set(power - steering);
		right_motor1.set(power + steering);
		right_motor2.set(power + steering);
	}

	public void stop() {
		tankDrive(0, 0);// sets the left and right power to 0 so the robot stops
	}

}
