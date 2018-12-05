package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.Square;
import org.usfirst.frc.team2531.robot.commands.TimeDrive;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
import org.usfirst.frc.team2531.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import net.java.games.input.Component;
import net.java.games.input.Controller;
import net.java.games.input.ControllerEnvironment;

public class Robot extends TimedRobot {
	// subsystem declarations for access
	public static Drive drive = new Drive();
	// OI class declarations
	public static OI oi;

	private static SendableChooser<Command> auto;
	private static Command autocommand;

	@Override
	public void robotInit() {
		oi = new OI();// initialize OI class for control
		RobotMap.imu.calibrate();
		initSmartDash();
//		printJinput();
	}

	@Override
	public void disabledInit() {
		System.out.println("#Disabled");// print status
		RobotMap.imu.reset();// reset imu
		RobotMap.heading = 0;// reset heading
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDash();
	}

	@Override
	public void autonomousInit() {
		System.out.println("#Autonomous");// print status
		autocommand = auto.getSelected();
		if (autocommand != null) {
			autocommand.start();
		}
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDash();
	}

	@Override
	public void teleopInit() {
		System.out.println("#Teleop");// print status
		if (autocommand != null) {
			autocommand.cancel();
		}
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		updateSmartDash();
	}

	@Override
	public void testPeriodic() {
	}

	public void initSmartDash() {
		SmartDashboard.putNumber("Angle X", RobotMap.imu.getAngleX());
		SmartDashboard.putNumber("Angle Y", RobotMap.imu.getAngleY());
		SmartDashboard.putNumber("Angle Z", RobotMap.imu.getAngleZ());
		auto = new SendableChooser<Command>();
		auto.addDefault("None", null);
		auto.addObject("Turn 90", new Turn2Angle(90));
		auto.addObject("Square", new Square());
		auto.addObject("Drive 2sec", new TimeDrive(2000, 0.5));
		SmartDashboard.putData(auto);

	}

	public void updateSmartDash() {
		SmartDashboard.putNumber("Angle X", RobotMap.imu.getAngleX());
		SmartDashboard.putNumber("Angle Y", RobotMap.imu.getAngleY());
		SmartDashboard.putNumber("Angle Z", RobotMap.imu.getAngleZ());
	}

	public void printJinput() {
		Controller[] controllers = ControllerEnvironment.getDefaultEnvironment().getControllers();
		for (int i = 0; i < controllers.length; i++) {
			Controller c = controllers[i];
			System.out.println("Found Controller " + c.getName() + " on port " + c.getPortNumber());
			for (int j = 0; j < c.getComponents().length; j++) {
				Component com = c.getComponents()[j];
				System.out.println(j + " - " + com.getName());
			}
		}

	}
}
