package org.usfirst.frc.team151.robot;

import org.usfirst.frc.team151.robot.commands.ClimbRopeCommand;
import org.usfirst.frc.team151.robot.commands.GearDepositCommand;
import org.usfirst.frc.team151.robot.commands.GearRetractCommand;
import org.usfirst.frc.team151.robot.commands.ShootHighGoalCommand;
import org.usfirst.frc.team151.robot.commands.StopRopeCommand;

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
		
		x.whenPressed(new GearRetractCommand());
		b.whenPressed(new GearDepositCommand());
		//TODO right now, both commands in the commandGroup do the same thing
		y.whenPressed(new ClimbRopeCommand());
//		a.whenPressed(new StopRopeCommand());
//		y.whenPressed(new RopeClimberCommandGroup());
		rightTrigger.whenPressed(new ShootHighGoalCommand());
		rightBumper.whenPressed(new ShootHighGoalCommand());
		//TODO figure out which commands to call
		/*
		 * right trigger: shoot
		 * right bumper: toggle shooter motor
		 */
	}
	
	

}
