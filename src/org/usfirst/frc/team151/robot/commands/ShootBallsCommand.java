package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ShootBallsCommand extends Command {

	private double speed = 0;
	private double lastSpeed = 0;

	public ShootBallsCommand(double finalSpeed) {
		requires(Robot.shooterSubsystem);
		lastSpeed = finalSpeed;
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		Robot.secondaryDriverOi.shooterOn = true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		if (speed < lastSpeed){
			speed += 0.01;
		}
		Robot.shooterSubsystem.shootBallsNoVision(speed);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.secondaryDriverOi.shooterOn = false;
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
