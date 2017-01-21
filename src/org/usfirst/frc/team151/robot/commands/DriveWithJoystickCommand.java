package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team151.robot.Robot;

/**
 *
 */
public class DriveWithJoystickCommand extends Command {
	public DriveWithJoystickCommand() {
		// Use requires() here to declare subsystem dependencies 
		requires(Robot.mecanumDriveSubsystem);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		Robot.mecanumDriveSubsystem.drive(Robot.oi.getJoystick());
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.mecanumDriveSubsystem.drive(0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		end();
	}
}
