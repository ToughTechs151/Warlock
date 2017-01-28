package org.usfirst.frc.team151.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team151.robot.commands.ClimbRopeCommand;
import org.usfirst.frc.team151.robot.commands.RopeClimberCommandGroup;

import org.usfirst.frc.team151.robot.RobotMap;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;

/**
 *
 */
public class RopeClimberSubsystem extends Subsystem {
	
	DigitalOutput diOutRopeClimber = null;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public RopeClimberSubsystem() {
		diOutRopeClimber = new DigitalOutput(RobotMap.digOutRopeClimber);
	}
	
    public void initDefaultCommand() {
    	setDefaultCommand(new ClimbRopeCommand());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void climbRope(Joystick joystick) {
    	if(diOutRopeClimber.get() == false) {
    		diOutRopeClimber.set(true);
    	}
    }
    
    public void stopRope(Joystick joystick) {
    	diOutRopeClimber.set(false);
    }
}

