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
	
	//Relay relayRopeClimber = null;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public RopeClimberSubsystem() {
		//relayRopeClimber = new Relay(RobotMap.relayRopeClimber);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ClimbRopeCommand());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void locateAttachtoRope(Joystick joystick) {
    	//TODO insert locating and attaching to rope code here
    }
    
    public void climbRope(Joystick joystick) {
    	//TODO insert locating and attaching to rope code here
    }
}

