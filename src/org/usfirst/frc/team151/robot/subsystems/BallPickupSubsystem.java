package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
 
/**
 *
 */
public class BallPickupSubsystem extends Subsystem {
	
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	private SpeedController spinBallPickup = null;
	
	public BallPickupSubsystem() {
		System.out.println("Starting BallPickupSubsystem");
		spinBallPickup = new Talon(RobotMap.spinBallPickup);
		System.out.println("Ending BallPickupSubsystem");
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void pickupBalls() {
    	spinBallPickup.set(-0.6);
    }
    
    public void stopPickupBalls() {
    	spinBallPickup.set(0.0);
    }
    
    public void reversePickupBalls() {
    	spinBallPickup.set(0.6);
    }
    
    public void log() {
    	System.out.println("Ball Pick-up Relay: " + spinBallPickup.get());
    }
}
