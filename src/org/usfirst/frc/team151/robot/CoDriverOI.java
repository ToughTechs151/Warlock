package org.usfirst.frc.team151.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 * This class encapsulates the logic to manage the secondary or Co-driver Joystick controls
 *
 */
public class CoDriverOI extends OI{
 
	public CoDriverOI(int joystickChannel) {
		super(joystickChannel);
		// TODO Auto-generated constructor stub
		x = new JoystickButton(joystick, 1);
		b = new JoystickButton(joystick, 3);
		y = new JoystickButton(joystick, 4);
		rightBumper = new JoystickButton(joystick, 6);
		rightTrigger = new JoystickButton(joystick, 8);
	}
	
	

}
