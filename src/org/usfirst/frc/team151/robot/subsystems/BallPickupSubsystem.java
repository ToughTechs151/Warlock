package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.PickUpCommand;
import org.usfirst.frc.team151.robot.commands.StopPickupCommand;

import edu.wpi.first.wpilibj.Joystick;
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
		spinBallPickup = new Talon(RobotMap.spinBallPickup);
		
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void pickupBalls() {
    	spinBallPickup.set(-1.0);
    }
    
    public void stopPickupBalls() {
    	spinBallPickup.set(0.0);
    }
    
    public void reversePickupBalls() {
    	spinBallPickup.set(1.0);
    }
}
