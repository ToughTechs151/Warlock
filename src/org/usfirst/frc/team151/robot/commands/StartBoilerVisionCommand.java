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
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double distFromCenter = Robot.boilerVision.getDistanceFromCenter();
    	if(distFromCenter < 0) {
    		//TODO fix z-axis rotation power
    		Robot.mecanumDriveSubsystem.drive(0, 0, 0.1);
    		System.out.println("StartBoilerVisionCommand execute - turning right");
    	} else if(distFromCenter > 0) {
    		Robot.mecanumDriveSubsystem.drive(0, 0, -0.1);
    		System.out.println("StartBoilerVisionCommand execute - turning left");
    	} else {
    		Robot.mecanumDriveSubsystem.drive(0, 0, 0);
    		System.out.println("StartBoilerVisionCommand execute - not turning");
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.boilerVision.getDistanceFromCenter() == 0;
    }

    // Called once after isFinished returns true
    protected void end() {
//    	DriveStraightCommand drive = new DriveStraightCommand(Robot.boilerVision.getDistance());
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	
    }
}
