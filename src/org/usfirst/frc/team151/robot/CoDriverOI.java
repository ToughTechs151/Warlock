package org.usfirst.frc.team151.robot;

import org.usfirst.frc.team151.robot.commands.ClimbRopeCommand;
import org.usfirst.frc.team151.robot.commands.GearDepositCommand;
import org.usfirst.frc.team151.robot.commands.GearRetractCommand;
import org.usfirst.frc.team151.robot.commands.ReverseAgitatorCommand;
import org.usfirst.frc.team151.robot.commands.ShootBallsCommand;
import org.usfirst.frc.team151.robot.commands.StartAgitatorCommand;
import org.usfirst.frc.team151.robot.commands.StopAgitatorCommand;
import org.usfirst.frc.team151.robot.commands.StopRopeCommand;
import org.usfirst.frc.team151.robot.commands.StopShootBallsCommand;

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
//		a = new JoystickButton(joystick, 2);
		rightBumper = new JoystickButton(joystick, 6);
		rightTrigger = new JoystickButton(joystick, 8);
		leftBumper = new JoystickButton(joystick, 5);
		leftTrigger = new JoystickButton(joystick, 7);
		
		x.whenPressed(new GearRetractCommand());
		b.whenPressed(new GearDepositCommand());
		y.whenPressed(new ClimbRopeCommand());
//		a.whenPressed(new StopRopeCommand());
		rightTrigger.whenPressed(new ShootBallsCommand());
		rightBumper.whenPressed(new StopShootBallsCommand());
		leftTrigger.whenPressed(new StartAgitatorCommand());
		leftTrigger.whenReleased(new StopAgitatorCommand());
		leftBumper.whenPressed(new ReverseAgitatorCommand());
		leftBumper.whenReleased(new StopAgitatorCommand());
//		rightTrigger.whenReleased(new StopShootBallsCommand());
	}
	
	

}
