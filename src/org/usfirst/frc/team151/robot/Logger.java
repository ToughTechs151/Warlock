package org.usfirst.frc.team151.robot;

public class Logger {
	
	public Logger() {
		/*
		 * Convenient way to keep track of all log methods in one class
		 */
	}
	
	public void log() {
		Robot.mecanumDriveSubsystem.log();
		Robot.agitatorSubsystem.log();
		Robot.ballPickupSubsystem.log();
		Robot.shooterSubsystem.log();
	}

}
