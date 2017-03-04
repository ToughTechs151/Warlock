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
	
	PIDController shooterPid = null;
	private double rpm = 0;

    public ShootBallsCommand(double idealRpm) {
    	requires(Robot.shooterSubsystem);
    	rpm = idealRpm;
    	shooterPid = new PIDController(0.5, 0.01, 0.0, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kRate;

			@Override
			public double pidGet() {
				return Robot.mecanumDriveSubsystem.getDistanceTraveled();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
		}, new PIDOutput() {
			@Override
			public void pidWrite(double d) {
//				Robot.mecanumDriveSubsystem.drive(Robot.primaryDriverOi);
				Robot.shooterSubsystem.shootBalls(rpm);
			}
		});
//    	driverPid.reset();
    	System.out.println("Finishing DriveStraightCommand constructor");
		shooterPid.setSetpoint(rpm);
		shooterPid.setAbsoluteTolerance(0.5);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	shooterPid.reset();
    	Robot.shooterSubsystem.wheelEncoder.reset();
		shooterPid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.shooterSubsystem.shootBalls();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return shooterPid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.shooterSubsystem.wheelEncoder.reset();
    	Robot.shooterSubsystem.stopShootBalls();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
