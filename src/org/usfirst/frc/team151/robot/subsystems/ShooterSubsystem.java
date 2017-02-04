package org.usfirst.frc.team151.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

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
	
	//pulses 1440 pulses per rev
	private double distancePerPulse = 0.01963194; //TODO need to change this, in inches
	
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
	
    public void getEncoderSpeed() {
    	wheelEncoder.getRate();
    }
    
    public void stopShootBalls() {
    	wheelSpinner.set(0.0);
    }
    
    public void shootBalls() {
    	wheelSpinner.set(1.0); //TODO change speed (do math), use algorithm to set speed
    }
}
