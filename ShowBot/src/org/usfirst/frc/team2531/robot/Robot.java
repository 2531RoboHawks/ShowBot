package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.commands.SquareDrive;
import org.usfirst.frc.team2531.robot.commands.TimeDrive;
import org.usfirst.frc.team2531.robot.commands.Turn2Angle;
import org.usfirst.frc.team2531.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
	}

	@Override
	public void disabledInit() {
		System.out.println("#Disabled");// print status
		RobotMap.imu.reset();// reset imu
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
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
		auto.addObject("Forward 2 sec", new TimeDrive(1, 0));
		auto.addObject("Drive in a Square", new SquareDrive());
		SmartDashboard.putData(auto);

	}

	public void updateSmartDash() {
		SmartDashboard.putNumber("Angle X", RobotMap.imu.getAngleX());
		SmartDashboard.putNumber("Angle Y", RobotMap.imu.getAngleY());
		SmartDashboard.putNumber("Angle Z", RobotMap.imu.getAngleZ());
	}
}
