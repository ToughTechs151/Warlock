package org.usfirst.frc.team151.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	Joystick joystick = null;	
	//TODO initialize the other buttons in the OI constructor
	private JoystickButton x = new JoystickButton(joystick, 1);
	private JoystickButton a = new JoystickButton(joystick, 2);
	private JoystickButton b = new JoystickButton(joystick, 3);
	private JoystickButton y = new JoystickButton(joystick, 4);
	private JoystickButton leftBumper = null;
	private JoystickButton rightBumper = null;
	private JoystickButton leftTrigger = null;
	private JoystickButton rightTrigger = null;
	private JoystickButton back = null;
	private JoystickButton start = null;
	private JoystickButton leftJoyDown = null;
	private JoystickButton rightJoyDown = null;
	
	public OI(int joystickChannel) {		
		joystick = new Joystick(joystickChannel);
		leftBumper = new JoystickButton(joystick, 5);
		rightBumper = new JoystickButton(joystick, 6);
		leftTrigger = new JoystickButton(joystick, 7);
		rightTrigger = new JoystickButton(joystick, 8);
		back = new JoystickButton(joystick, 9);
		start = new JoystickButton(joystick, 10);
		leftJoyDown = new JoystickButton(joystick, 11);
		rightJoyDown = new JoystickButton(joystick, 12);	
//		y.whenPressed(new ClimbRopeCommand());
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
    		case 6: return rightBumper; 
    		case 7: return leftTrigger;
    		case 8: return rightTrigger;
    		case 9: return back;
    		case 10: return start;
    		case 11: return leftJoyDown;
    		case 12: return rightJoyDown;
    	}
		return null;
    }
}
