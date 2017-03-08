package org.usfirst.frc.team151.robot;

import org.usfirst.frc.team151.robot.commands.ReverseAgitatorCommand;
import org.usfirst.frc.team151.robot.commands.ShootBallsCommand;
import org.usfirst.frc.team151.robot.commands.StartAgitatorCommand;
import org.usfirst.frc.team151.robot.commands.StartShooterCommandGroup;
import org.usfirst.frc.team151.robot.commands.StopAgitatorCommand;
import org.usfirst.frc.team151.robot.commands.StopShootBallsCommand;
import org.usfirst.frc.team151.robot.commands.StopShooterCommandGroup;

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
		y = new JoystickButton(joystick, 4);
		rightBumper = new JoystickButton(joystick, 6);
		rightTrigger = new JoystickButton(joystick, 8);
		leftBumper = new JoystickButton(joystick, 5);
		leftTrigger = new JoystickButton(joystick, 7);
		
//		y.whenPressed(new ClimbRopeCommand());
		rightTrigger.whenPressed(new StartShooterCommandGroup());
		rightBumper.whenPressed(new StopShooterCommandGroup());
		leftBumper.whenPressed(new ReverseAgitatorCommand());
		leftBumper.whenReleased(new StopAgitatorCommand());
	}

}
