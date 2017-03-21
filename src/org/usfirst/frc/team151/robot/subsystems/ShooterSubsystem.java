package org.usfirst.frc.team151.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	private SpeedController wheelSpinner = null;

	private boolean isStopped = false;

	public ShooterSubsystem() {
		wheelSpinner = new Talon(RobotMap.shooterMotor);
	}

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

	}

	public void stopShootBalls() {
		wheelSpinner.set(0.0);
		isStopped = true;
	}

	public void shootBallsNoVision(double x) {
		wheelSpinner.set(x);
	}

	public void reset() {
		wheelSpinner.set(0);
	}

	public boolean isStopped() {
		return isStopped;
	}
	
	public void log() {
		System.out.println("Shooter Motor Output: " + wheelSpinner.get());
	}
}
