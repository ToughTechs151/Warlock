package org.usfirst.frc.team151.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick joystick = null;
	
	private JoystickButton x = null;
	private JoystickButton a = null;
	private JoystickButton b = null;
	private JoystickButton y = null;
	private JoystickButton leftBumper = null;
	private  JoystickButton rightBumper = null;
	private JoystickButton leftTrigger = null;
	private JoystickButton rightTrigger = null;
	private JoystickButton back = null;
	private JoystickButton start = null;
	private JoystickButton leftJoyDown = null;
	private JoystickButton rightJoyDown = null;	
	
	public OI(int joystickChannel) {		
		joystick = new Joystick(joystickChannel);	
		JoystickButton x = new JoystickButton(joystick, 1);
		JoystickButton a = new JoystickButton(joystick, 2);
		JoystickButton b = new JoystickButton(joystick, 3);
		JoystickButton y = new JoystickButton(joystick, 4);
		JoystickButton leftBumper = new JoystickButton(joystick, 5);
		JoystickButton rightBumper = new JoystickButton(joystick, 6);
		JoystickButton leftTrigger = new JoystickButton(joystick, 7);
		JoystickButton rightTrigger = new JoystickButton(joystick, 8);
		JoystickButton back = new JoystickButton(joystick, 9);
		JoystickButton start = new JoystickButton(joystick, 10);
		JoystickButton leftJoyDown = new JoystickButton(joystick, 11);
		JoystickButton rightJoyDown = new JoystickButton(joystick, 12);		
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
