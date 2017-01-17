package org.usfirst.frc.team151.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;

import org.usfirst.frc.team151.robot.commands.DriveWithJoystickCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick joystick = new Joystick(0);
	
    public Joystick getJoystick() {
        return joystick;
    }	
}
