package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class GearVisionDriveCommand extends Command {

	private boolean hasArrived = false;
	
	public GearVisionDriveCommand() {
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
		double distFromCenter = Robot.gearVision.getDistanceFromCenter();
		double dist = Robot.gearVision.getDistance();
		if(distFromCenter < 0 && dist > 5) {
			//TODO fix y-axis and z-axis rotation power
			Robot.mecanumDriveSubsystem.drive(0, 0, 0.05);
			System.out.println("StartGearVisionCommand execute - turning right");
		} else if(distFromCenter > 0 && dist > 5) {
			Robot.mecanumDriveSubsystem.drive(0, 0, -0.05);
			System.out.println("StartGearVisionCommand execute - turning left");
		} else if(distFromCenter == 0 && dist > 5){
			Robot.mecanumDriveSubsystem.drive(0, 0.25, 0);
			System.out.println("StartGearVisionCommand execute - moving forward");
		} else {
			Robot.mecanumDriveSubsystem.drive(0, 0, 0);
			hasArrived = true;
			System.out.println("StartGearVisionCommand execute - not moving");
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return hasArrived;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.mecanumDriveSubsystem.drive(0, 0, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
