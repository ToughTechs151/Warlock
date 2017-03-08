package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class StartBoilerVisionCommand extends Command {

    public StartBoilerVisionCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.mecanumDriveSubsystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.boilerVision.startVision();
    	Robot.mecanumDriveSubsystem.startLed();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mecanumDriveSubsystem.stopLed();
//    	DriveStraightCommand drive = new DriveStraightCommand(Robot.boilerVision.getDistance());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
