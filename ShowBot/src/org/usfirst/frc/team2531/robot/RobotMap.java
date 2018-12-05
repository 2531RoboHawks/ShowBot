package org.usfirst.frc.team2531.robot;

import frclib.sensors.ADIS16448;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class RobotMap {

	public static ADIS16448 imu = new ADIS16448();
	public static double heading;
	public static Controller joy = ControllerEnvironment.getDefaultEnvironment().getControllers()[0];
}
