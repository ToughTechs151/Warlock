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
	private Encoder wheelEncoder = null;
	
	private static int channel1 = 0;
	private static int channel2 = 1;
	
	private boolean isStopped = false;
	
	private static final int BOILER_HEIGHT = 97;
	private static final double CIRCUMFERENCE = Math.PI * 8;
	
	//Map of ranges to initial velocities
	private static final double V72 = 269.1;
	private static final double V84 = 277.5;
	private static final double V96 = 287.2;
	private static final double V108 = 297.5;
	private static final double V120 = 307.9;
	private static final double V132 = 318.3;
	private static final double V144 = 328.6;
	private static final double V156 = 338.7;
	private static final double V168 = 348.6;
	private static final double V180 = 358.3;
	
	//pulses 1440 pulses per rev
	private double distancePerPulse = 6 * Math.PI / 1440; //TODO need to change this, in inches
	
	private static final double MOTOR_MAX_RPM = 5000; //TODO change this(?)
	
	private double[] velocityCalc = {
		V72, V84, V96, V108, V120, V132, V144, V156, V168, V180	
	};
	
	public ShooterSubsystem() {
		wheelSpinner = new Talon(RobotMap.shooterMotor);
		wheelEncoder = new Encoder(channel1, channel2);
		wheelEncoder.reset();
		wheelEncoder.setDistancePerPulse(distancePerPulse);
	}


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//setDefaultCommand(new )
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	
    }
	
    public double getEncoderSpeed() {
    	return wheelEncoder.getRate();
    }
    
    public void stopShootBalls() {
    	wheelSpinner.set(0.0);
    	isStopped = true;
    }
    
    public void shootBalls() {
    	wheelSpinner.set(1.0); //TODO change speed (do math), use algorithm to set speed
    }
    
    public void shootBalls(double speed) {
    	wheelSpinner.set(speed);
    }
    
    public double getWheelRpm() {
    	double range = Math.sqrt(Math.pow(Robot.boilerVision.getDistance(), 2) * Math.pow(BOILER_HEIGHT,  2));
    	return getInitialVelocity(range) * 60 * 4 / CIRCUMFERENCE / MOTOR_MAX_RPM;
    }
    
    public double getInitialVelocity(double range) {
    	int index = (int) Math.floor(range/12) - 6;
    	double temp = velocityCalc[index];
    	if(range != temp) {
    		return (velocityCalc[index + 1] - velocityCalc[index]) / 12 + velocityCalc[index];
    	} else {
    		return velocityCalc[index];
    	}
    }
    
    public double getDistanceGoal() {
    	return 120; //TODO change this according to visual information
    }
    
    public void reset() {
 //   	wheelSpinner.set(0);
    	wheelEncoder.reset();
    }
    
    public boolean isStopped() {
    	return isStopped;
    }
}
