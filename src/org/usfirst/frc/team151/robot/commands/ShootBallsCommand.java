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

//	PIDController shooterPid = null;
//	private double rpm = 0;
	private double speed = 0;
	private double lastSpeed = 0;

	public ShootBallsCommand(double finalSpeed) {
		requires(Robot.shooterSubsystem);
		lastSpeed = finalSpeed;
		//TODO add PID after testing
		/*
    	shooterPid = new PIDController(0.5, 0.01, 0.0, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kRate;

			@Override
			public double pidGet() {
				return Robot.shooterSubsystem.getCurrentWheelRpm();
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
				Robot.shooterSubsystem.shootBalls();
			}
		});
    	shooterPid.reset();
    	System.out.println("Finishing DriveStraightCommand constructor");
		shooterPid.setSetpoint(rpm);
		shooterPid.setAbsoluteTolerance(0.5);

		 */
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
//		shooterPid.reset();
		Robot.shooterSubsystem.wheelEncoder.reset();
		Robot.secondaryDriverOi.shooterOn = true;
//		shooterPid.enable();
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
		Robot.shooterSubsystem.wheelEncoder.reset();
		Robot.secondaryDriverOi.shooterOn = false;
//		Robot.shooterSubsystem.stopShootBalls();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
