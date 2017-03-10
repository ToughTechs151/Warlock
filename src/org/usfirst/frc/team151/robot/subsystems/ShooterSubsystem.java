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
	public Encoder wheelEncoder = null;

	private boolean isStopped = false;

	private static final int BOILER_HEIGHT = 97;
	private static final double CIRCUMFERENCE = Math.PI * 8;

	//Map of ranges to initial velocities
	private static final double V84 = 0.85; //TODO NEED TO TEST THIS
	private static final double V120 = 1.0;

	//pulses 1440 pulses per rev
	private static final double DISTANCE_PER_PULSE = 8 * Math.PI / 360; //TODO need to change this, in inches	
	private static final double WHEEL_CIRCUMFERENCE = Math.PI * 6; //TODO check wheel diameter

	public ShooterSubsystem() {
		wheelSpinner = new Talon(RobotMap.shooterMotor);
		wheelEncoder = new Encoder(RobotMap.shooterEncoderA, RobotMap.shooterEncoderB);
		wheelEncoder.reset();
		wheelEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
	}


	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {

	}

	public double getEncoderSpeed() {
		return wheelEncoder.getRate();
	}

	public void stopShootBalls() {
		wheelSpinner.set(0.0);
		isStopped = true;
	}

	public void shootBalls() {
		if (Math.abs(Robot.boilerVision.getDistance() - 120) < 5) {
			wheelSpinner.set(V120);
		} else if (Math.abs(Robot.boilerVision.getDistance() - 84) < 5) {
			wheelSpinner.set(V84);
		} else {
			wheelSpinner.set(0);
		}
	}

	public double getCurrentWheelRpm() {
		return wheelEncoder.getRate() * 60 * (1/WHEEL_CIRCUMFERENCE);
	}

	public void reset() {
		wheelSpinner.set(0);
		wheelEncoder.reset();
	}

	public boolean isStopped() {
		return isStopped;
	}
	
	public void log() {
		System.out.println("Shooter Encoder Rate: " + wheelEncoder.getRate());
		System.out.println("Shooter Motor Output: " + wheelSpinner.get());
	}
}
