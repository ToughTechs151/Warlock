package org.usfirst.frc.team151.robot;

import org.usfirst.frc.team151.robot.commands.PickUpCommand;
import org.usfirst.frc.team151.robot.commands.ReversePickupCommand;
import org.usfirst.frc.team151.robot.commands.StartBoilerVisionCommand;
import org.usfirst.frc.team151.robot.commands.StopPickupCommand;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 * This class encapsulates the logics to manage the primary driver Joystick controls
 * 
 */
public class DriverOI extends OI{

	public DriverOI(int joystickChannel) {
		super(joystickChannel);
		// TODO Auto-generated constructor stub
		leftBumper = new JoystickButton(joystick, 5);
		rightBumper = new JoystickButton(joystick, 6);
		leftTrigger = new JoystickButton(joystick, 7);
		rightTrigger = new JoystickButton(joystick, 8); 
		back = new JoystickButton(joystick, 9);
		
		//checks to see if buttons are pressed
		leftTrigger.whenPressed(new PickUpCommand());
		leftTrigger.whenReleased(new StopPickupCommand());
		leftBumper.whenPressed(new ReversePickupCommand());
		leftBumper.whenReleased(new StopPickupCommand());
		
		back.whenPressed(new StartBoilerVisionCommand());
	}

}
