package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.TurnLedOnCommand;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class LedSubsystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	Relay ledRelay = null;
	
	public LedSubsystem() {
		ledRelay = new Relay(RobotMap.ledLights);
	}

    public void initDefaultCommand() {
    	setDefaultCommand(new TurnLedOnCommand());
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void turnOnLed() {
    	ledRelay.set(Value.kForward);
    }
    
    public void turnOffLed() {
    	ledRelay.set(Value.kOff);
    }   
    
    public void log() {
    	System.out.println("LED in ledSubsystem " + Robot.ledSubsystem.ledRelay.get());
    }
}

