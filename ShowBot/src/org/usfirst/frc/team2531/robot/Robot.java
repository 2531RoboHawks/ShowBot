package org.usfirst.frc.team2531.robot;

import org.usfirst.frc.team2531.robot.subsystems.Drive;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

public class Robot extends TimedRobot {
	// subsystem declarations for access
	public static Drive drive = new Drive();
	// OI class declarations
	public static OI oi;

	@Override
	public void robotInit() {
		oi = new OI();// initialize OI class for control
	}

	@Override
	public void disabledInit() {
		System.out.println("#Disabled");// print status
	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void autonomousInit() {
		System.out.println("#Autonomous");// print status
	}

	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		System.out.println("#Teleop");// print status
	}

	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void testPeriodic() {
	}
}
