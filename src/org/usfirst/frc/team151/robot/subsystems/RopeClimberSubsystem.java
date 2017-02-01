package org.usfirst.frc.team151.robot.subsystems;

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

/**
 *
 */
public class RopeClimberSubsystem extends Subsystem {
	
	DigitalOutput diOutRopeClimber = null;
	DigitalInput ropeLimitSwitch = null;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	public RopeClimberSubsystem() {
		diOutRopeClimber = new DigitalOutput(RobotMap.digOutRopeClimber);
		diOutRopeClimber.set(false);
		ropeLimitSwitch = new DigitalInput(RobotMap.ropeLimitSwitch);
	} 
	
    public void initDefaultCommand() {
    	setDefaultCommand(new StopRopeCommand());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void climbRope(Joystick joystick) {
    	if(!diOutRopeClimber.get()) {
    		diOutRopeClimber.set(true);
    	}    	
    }
    
    public void stopRope() {
    	if(!ropeLimitSwitch.get()) {
    		if(diOutRopeClimber.get())
    			diOutRopeClimber.set(false);
    	}
    }
}
