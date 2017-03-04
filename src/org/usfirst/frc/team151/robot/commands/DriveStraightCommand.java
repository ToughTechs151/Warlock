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
public class DriveStraightCommand extends Command {
	
	PIDController driverPid = null;
	double distance = 0;

    public DriveStraightCommand(double distanceToTravel) {
    	System.out.println("Entering DriveStraightCommand constructor");
    	requires(Robot.mecanumDriveSubsystem);
    	driverPid = new PIDController(0.5, 0.0, 0.0, new PIDSource() {
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
//				Robot.mecanumDriveSubsystem.drive(Robot.primaryDriverOi);
				Robot.mecanumDriveSubsystem.drive(0.0, 0.25 * d, 0.0);
			}
		});
    	distance = distanceToTravel;
//    	driverPid.reset();
    	System.out.println("Finishing DriveStraightCommand constructor");
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		driverPid.setSetpoint(distance);
		System.out.print("Distance for setpoint: " + distance);
		driverPid.setAbsoluteTolerance(0.5);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	driverPid.reset();
    	Robot.mecanumDriveSubsystem.gyro.reset();
    	Robot.mecanumDriveSubsystem.leftRearEncoder.reset();
//    	Robot.mecanumDriveSubsystem.rightFrontEncoder.reset();
		driverPid.enable();
//		distance = Robot.gearVision.getDistance();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	System.out.println("Finish is returning " + driverPid.onTarget());
    	return driverPid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
 //   	driverPid.disable();
    	Robot.mecanumDriveSubsystem.drive(0, 0, 0);
    	Robot.mecanumDriveSubsystem.gyro.reset();
    	Robot.mecanumDriveSubsystem.leftRearEncoder.reset();
//    	Robot.mecanumDriveSubsystem.rightFrontEncoder.reset();    
    	driverPid.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
