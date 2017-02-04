package org.usfirst.frc.team151.robot.subsystems;

import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team151.robot.commands.ClimbRopeCommand;
import org.usfirst.frc.team151.robot.commands.RopeClimberCommandGroup;
import org.usfirst.frc.team151.robot.commands.StopRopeCommand;
import org.usfirst.frc.team151.robot.RobotMap;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 *
 */
public class RopeClimberSubsystem extends Subsystem {
	
	Relay diOutRopeClimber = null;
	DigitalInput ropeLimitSwitch = null;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public RopeClimberSubsystem() {
		diOutRopeClimber = new Relay(RobotMap.digOutRopeClimber);
		diOutRopeClimber.set(Value.kOff);
		ropeLimitSwitch = new DigitalInput(RobotMap.ropeLimitSwitch);
	} 
	
    public void initDefaultCommand() {
//    	setDefaultCommand(new StopRopeCommand());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void climbRope(Joystick joystick) {
    	System.out.println("current output is " + diOutRopeClimber.get());
    	if(diOutRopeClimber.get() != Value.kForward) {
    		diOutRopeClimber.set(Value.kForward);
    		System.out.println("current output is now " + diOutRopeClimber.get());
    		Scheduler.getInstance().add(new StopRopeCommand());
    	}    	
    }
    
    public void stopRope() { 
    	if(!ropeLimitSwitch.get()) {
    		if(diOutRopeClimber.get() == Value.kForward) 
    			diOutRopeClimber.set(Value.kOff);
    	}
    }
}
