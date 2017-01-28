package org.usfirst.frc.team151.robot;

import org.usfirst.frc.team151.robot.commands.ClimbRopeCommand;
import org.usfirst.frc.team151.robot.commands.GearDepositCommand;
import org.usfirst.frc.team151.robot.commands.GearRetractCommand;
import org.usfirst.frc.team151.robot.commands.ShootHighGoalCommand;
import org.usfirst.frc.team151.robot.commands.StopRopeCommand;

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
		x = new JoystickButton(joystick, 1);
		a = new JoystickButton(joystick, 2);
		b = new JoystickButton(joystick, 3);
		y = new JoystickButton(joystick, 4);
		leftBumper = new JoystickButton(joystick, 5);
		rightBumper = new JoystickButton(joystick, 6);
		leftTrigger = new JoystickButton(joystick, 7);
		rightTrigger = new JoystickButton(joystick, 8);
		back = new JoystickButton(joystick, 9);
		start = new JoystickButton(joystick, 10);
		leftJoyDown = new JoystickButton(joystick, 11);
		rightJoyDown = new JoystickButton(joystick, 12);	
		if(joystickChannel == 0) {
//			leftTrigger.whenPressed(new SpinPinCommand());
			//TODO left trigger: rolling pin spins
		} else if(joystickChannel == 1) {
			x.whenPressed(new GearRetractCommand());
			b.whenPressed(new GearDepositCommand());
			//TODO right now, both commands in the commandGroup do the same thing
			y.whenPressed(new ClimbRopeCommand());
			a.whenPressed(new StopRopeCommand());
//			y.whenPressed(new RopeClimberCommandGroup());
			rightTrigger.whenPressed(new ShootHighGoalCommand());
			rightBumper.whenPressed(new ShootHighGoalCommand());
			//TODO figure out which commands to call
			/*
			 * right trigger: shoot
			 * right bumper: toggle shooter motor
			 */
		}
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
