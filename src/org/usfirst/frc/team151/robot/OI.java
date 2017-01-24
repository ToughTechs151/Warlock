package org.usfirst.frc.team151.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team151.robot.commands.DriveWithJoystickCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick joystick = null;
	
	public OI(int joystickChannel) {		
		joystick = new Joystick(joystickChannel);
		
        // Create some buttons
        JoystickButton d_up = new JoystickButton(joystick, 5);
        JoystickButton d_right= new JoystickButton(joystick, 6);
        JoystickButton d_down= new JoystickButton(joystick, 7);
        JoystickButton d_left = new JoystickButton(joystick, 8);
        JoystickButton l2 = new JoystickButton(joystick, 9);
        JoystickButton r2 = new JoystickButton(joystick, 10);
        JoystickButton l1 = new JoystickButton(joystick, 11);
        JoystickButton r1 = new JoystickButton(joystick, 12);		
	}
	
    public Joystick getJoystick() {
        return joystick;
    }	
}
