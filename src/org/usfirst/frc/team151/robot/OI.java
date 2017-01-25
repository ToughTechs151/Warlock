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
	
	private JoystickButton x = new JoystickButton(joystick, 1);
	private JoystickButton a = new JoystickButton(joystick, 2);
	private JoystickButton b = new JoystickButton(joystick, 3);
	private JoystickButton y = new JoystickButton(joystick, 4);
	private JoystickButton leftBumper = new JoystickButton(joystick, 5);
	private  JoystickButton rightBumper = new JoystickButton(joystick, 6);
	private JoystickButton leftTrigger = new JoystickButton(joystick, 7);
	private JoystickButton rightTrigger = new JoystickButton(joystick, 8);
	private JoystickButton back = new JoystickButton(joystick, 9);
	private JoystickButton start = new JoystickButton(joystick, 10);
	private JoystickButton leftJoyDown = new JoystickButton(joystick, 11);
	private JoystickButton rightJoyDown = new JoystickButton(joystick, 12);		
	
	public OI(int joystickChannel) {		
		joystick = new Joystick(joystickChannel);	
	}
	
    public Joystick getJoystick() {
        return joystick;
    }	
    
    public JoystickButton getJoystickButton(int button) {
    	switch(button) {
    		case 1: return x;
    		case 2: return a;
    		case 3: return b;
    		case 4: return y;
    		case 5: return leftBumper;
    		case 6 : return rightBumper; 
    		case 7: return leftTrigger;
    		case 8 : return rightTrigger;
    		case 9: return back;
    		case 10: return start;
    		case 11: return leftJoyDown;
    		case 12: return rightJoyDown;
    	}
		return null;
    }
}
