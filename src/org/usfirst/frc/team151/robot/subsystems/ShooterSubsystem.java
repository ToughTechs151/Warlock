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
	private static final double V48 = 0.75; //TODO NEED TO TEST THIS
	private static final double V84 = 0.85; //TODO NEED TO TEST THIS
	private static final double V120 = 1.0;
	
	//pulses 1440 pulses per revbbb
	private static final double DISTANCE_PER_PULSE = 8 * Math.PI / 360; //TODO need to change this, in inches
	
	private static final double MOTOR_MAX_RPM = 5000; //TODO change this(?)
	
	private static final double WHEEL_CIRCUMFERENCE = Math.PI * 6; //TODO check wheel diameter
	
	public ShooterSubsystem() {
		System.out.println("Starting ShooterSubsystem constructor");
		wheelSpinner = new Talon(RobotMap.shooterMotor);
		wheelEncoder = new Encoder(RobotMap.encoderA, RobotMap.encoderB);
		wheelEncoder.reset();
		wheelEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		System.out.println("Shooter initialized");
	}


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//setDefaultCommand(new )
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
	
    public double getEncoderSpeed() {
//    	return wheelEncoder.getRate();
    	return 0;
    }
    
    public void stopShootBalls() {
    	wheelSpinner.set(0.0);
    	isStopped = true;
    }
    
    public void shootBalls() {
    	if (Math.abs(Robot.boilerVision.getDistance() - 120) < 3) {
    		wheelSpinner.set(V120);
    	}
    	else if (Math.abs(Robot.boilerVision.getDistance() - 84) < 3) {
    		wheelSpinner.set(V84);
    	}
    	else if (Math.abs(Robot.boilerVision.getDistance() - 48) < 3) {
    		wheelSpinner.set(V48);
    	}
    	else {
    		wheelSpinner.set(0);
    	}
    }
    
    public double getCurrentWheelRpm() {
    	return wheelEncoder.getRate() * 60 * (1/WHEEL_CIRCUMFERENCE);
    }
    
//    public double getIdealWheelRpm() {
//    	double range = Math.sqrt(Math.pow(Robot.boilerVision.getDistance(), 2) * Math.pow(BOILER_HEIGHT,  2));
//    	return getInitialVelocity(range) * 60 * 4 / CIRCUMFERENCE / MOTOR_MAX_RPM;
//    }
    
//    public double getInitialVelocity(double range) {
//    	int index = (int) Math.floor(range/12) - 6;
//    	double temp = velocityCalc[index];
//    	if(range != temp) {
//    		return (velocityCalc[index + 1] - velocityCalc[index]) / 12 + velocityCalc[index];
//    	} else {
//    		return velocityCalc[index];
//    	}
//    }
    
    public void reset() {
    	wheelSpinner.set(0);
    	wheelEncoder.reset();
    }
    
    public boolean isStopped() {
    	return isStopped;
    }
}
