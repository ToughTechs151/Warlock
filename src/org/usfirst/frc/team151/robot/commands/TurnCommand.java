package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.interfaces.Gyro;

/**
 *
 */
public class TurnCommand extends Command {
	
	double turnAngle = 0;
	boolean stopTurn = false;
	
    public TurnCommand(double turn) {
    	requires(Robot.mecanumDriveSubsystem);
    	turnAngle = turn;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.mecanumDriveSubsystem.gyro.reset();
    	Robot.mecanumDriveSubsystem.gyro.calibrate();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.mecanumDriveSubsystem.drive(0, 0, 0.25);
    	if (Robot.mecanumDriveSubsystem.gyro.getAngle() == turnAngle) {
    		stopTurn = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return stopTurn;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.mecanumDriveSubsystem.gyro.reset();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
