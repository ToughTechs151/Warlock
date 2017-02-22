package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ClimbRopeCommand extends Command {

	private boolean isClimbFinished = false;

	public ClimbRopeCommand() {
		requires(Robot.ropeClimberSubsystem);
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		Robot.ropeClimberSubsystem.climbRope(Robot.secondaryDriverOi.getJoystick());
		isClimbFinished = true;
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return isClimbFinished;
	}

	// Called once after isFinished returns true
	protected void end() {
		//TODO add end code here
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
