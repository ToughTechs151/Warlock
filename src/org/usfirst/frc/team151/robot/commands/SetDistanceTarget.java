package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *@author Julia Zhang
 * For testing the PID Controllers
 */
public class SetDistanceTarget extends Command {
	
	private PIDController pid = null;

    public SetDistanceTarget(double distance) {
    	requires(Robot.mecanumDriveSubsystem);
    	pid = new PIDController(0.1, 0.01, 0.0, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			@Override
			public double pidGet() {
				return Robot.mecanumDriveSubsystem.getDistanceRemaining();
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
				Robot.mecanumDriveSubsystem.drive(Robot.primaryDriverOi);
			}
		});
		pid.setAbsoluteTolerance(0.01);
		pid.setSetpoint(distance);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.mecanumDriveSubsystem.reset();
    	pid.reset();
    	pid.enable();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return pid.onTarget();
    }

    // Called once after isFinished returns true
    protected void end() {
    	pid.disable();
    	Robot.mecanumDriveSubsystem.drive(0, 0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
