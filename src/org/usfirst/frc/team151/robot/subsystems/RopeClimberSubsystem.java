package org.usfirst.frc.team151.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team151.robot.commands.ClimbRopeCommand;
import org.usfirst.frc.team151.robot.commands.RopeClimberCommandGroup;

import org.usfirst.frc.team151.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 */
public class RopeClimberSubsystem extends Subsystem {
	
	Relay relayRopeClimber = null;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public RopeClimberSubsystem() {
		relayRopeClimber = new Relay(RobotMap.relayRopeClimber);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ClimbRopeCommand());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void reverseRope(Joystick joystick) {
    	relayRopeClimber.set(Relay.Value.kReverse);
    }
    
    public void climbRope(Joystick joystick) {
    	relayRopeClimber.set(Relay.Value.kForward);
    }
    
    public void stopOnRope(Joystick joystick) {
    	relayRopeClimber.set(Relay.Value.kOff);
    }
    
//    public void ropeClimber(Joystick joystick) {
//    	//when certain button is pressed
//    	if () { //making robot climb up
//    		relayRopeClimber.set(Relay.Value.kForward);
//    	}
//    	else if() { //reversing robot if rope gets caught
//        	relayRopeClimber.set(Relay.Value.kReverse);
//    	}
//    	else {
//    		relayRopeClimber.set(Relay.Value.kOff);
//    	}
//    }
}

