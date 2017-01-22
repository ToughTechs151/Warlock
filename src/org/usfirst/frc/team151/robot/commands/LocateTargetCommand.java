package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team151.robot.Robot;
/**
 *
 */
public class LocateTargetCommand extends Command {

    public LocateTargetCommand() {
    	requires(Robot.shooterSubsystem);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterSubsystem.locateTarget(Robot.oi.getJoystick());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	//TODO set motor to 0
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
