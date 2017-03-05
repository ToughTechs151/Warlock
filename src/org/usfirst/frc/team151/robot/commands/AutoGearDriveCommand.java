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
public class AutoGearDriveCommand extends Command {
	private PIDController drivePid = null;
	boolean finish = false;

	public AutoGearDriveCommand(double distance) {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.mecanumDriveSubsystem);
		Robot.gearVision.startVision();
		drivePid = new PIDController(0.05, 0.0, 0.0, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			@Override
			public double pidGet() {
				return Robot.mecanumDriveSubsystem.leftRearEncoder.getDistance();
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
				double distFromCenter = Robot.gearVision.getDistanceFromCenter();
				double z = 0;
				if(distFromCenter < -10) {
					//TODO fix z-axis rotation power
					z = 0.125;
					System.out.println("AutoGearDriveCommand execute - turning right");
				} else if(distFromCenter > 10) {
					z = -0.125;
					System.out.println("AutoGearDriveCommand execute - turning left");
				} else {
					z = 0.0;
					System.out.println("AutoGearDriveCommand execute - not turning");
				}
				Robot.mecanumDriveSubsystem.drive(0.0, d, z);
			}
		});
		drivePid.setAbsoluteTolerance(0.5);
		drivePid.setSetpoint(distance);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return finish;
	}

	// Called once after isFinished returns true
	protected void end() {
		Robot.gearVision.stopVision();
		Robot.mecanumDriveSubsystem.drive(0, 0, 0);
		Robot.mecanumDriveSubsystem.leftRearEncoder.reset();
		Robot.mecanumDriveSubsystem.gyro.reset();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {

	}
}
