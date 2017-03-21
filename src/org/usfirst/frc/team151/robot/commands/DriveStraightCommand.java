package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class DriveStraightCommand extends Command {
	
	private PIDController driverPid = null;

    public DriveStraightCommand(double distanceToTravel) {
    	System.out.println("Entering DriveStraightCommand constructor");
    	requires(Robot.mecanumDriveSubsystem);
    	driverPid = new PIDController(0.008, 0.0, 0.0, new PIDSource() {
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
				Robot.mecanumDriveSubsystem.drive(0.0, d, 0.0);
			}
		});
    	System.out.println("Finishing DriveStraightCommand constructor");
		System.out.print("Distance for setpoint: " + distanceToTravel);
		driverPid.setAbsoluteTolerance(0.5);
		driverPid.setSetpoint(distanceToTravel);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driverPid.reset();
    	Robot.mecanumDriveSubsystem.gyro.reset();
    	Robot.mecanumDriveSubsystem.leftRearEncoder.reset();
		driverPid.enable();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("onTarget is " + driverPid.onTarget());
    	return driverPid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	driverPid.reset();
    	driverPid.disable();
    	Robot.mecanumDriveSubsystem.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
