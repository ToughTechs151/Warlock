package org.usfirst.frc.team151.robot;

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
		rightBumper = new JoystickButton(joystick, 6);
		leftTrigger = new JoystickButton(joystick, 7);
		rightTrigger = new JoystickButton(joystick, 8);
	}

}
