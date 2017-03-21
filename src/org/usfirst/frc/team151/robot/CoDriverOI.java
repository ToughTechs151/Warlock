package org.usfirst.frc.team151.robot;

import org.usfirst.frc.team151.robot.commands.PickUpCommand;
import org.usfirst.frc.team151.robot.commands.ReverseAgitatorCommand;
import org.usfirst.frc.team151.robot.commands.ReversePickupCommand;
import org.usfirst.frc.team151.robot.commands.ShootBallsCommand;
import org.usfirst.frc.team151.robot.commands.StartAgitatorCommand;
//import org.usfirst.frc.team151.robot.commands.StartBoilerVisionCommand;
import org.usfirst.frc.team151.robot.commands.StartShooterCommandGroup;
import org.usfirst.frc.team151.robot.commands.StopAgitatorCommand;
import org.usfirst.frc.team151.robot.commands.StopPickupCommand;
//import org.usfirst.frc.team151.robot.commands.StopBoilerVisionCommand;
import org.usfirst.frc.team151.robot.commands.StopShootBallsCommand;
import org.usfirst.frc.team151.robot.commands.StopShooterCommandGroup;
//import org.usfirst.frc.team151.robot.commands.TurnLedOffCommand;
//import org.usfirst.frc.team151.robot.commands.TurnLedOnCommand;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * 
 * This class encapsulates the logic to manage the secondary or Co-driver Joystick controls
 *
 */
public class CoDriverOI extends OI{
	
	public boolean shooterOn = false;
 
	public CoDriverOI(int joystickChannel) {
		super(joystickChannel);
		// TODO Auto-generated constructor stub
		rightBumper = new JoystickButton(joystick, 6);
		rightTrigger = new JoystickButton(joystick, 8);
		leftBumper = new JoystickButton(joystick, 5);
		leftTrigger = new JoystickButton(joystick, 7);
		x = new JoystickButton(joystick, 1);
		a = new JoystickButton(joystick, 2);
		
//		rightTrigger.whenPressed(new StartShooterCommandGroup());
//		rightBumper.whenPressed(new StopShooterCommandGroup());
		rightBumper.whenPressed(new ShootBallsCommand(Robot.shooterSpeed));
		rightBumper.whenReleased(new StopShootBallsCommand());
		leftTrigger.whenPressed(new StartAgitatorCommand());
		leftTrigger.whenReleased(new StopAgitatorCommand());
		leftBumper.whenPressed(new ReverseAgitatorCommand());
		leftBumper.whenReleased(new StopAgitatorCommand());
		x.whenPressed(new PickUpCommand());
		x.whenReleased(new StopPickupCommand());
		a.whenPressed(new ReversePickupCommand());
		a.whenReleased(new StopPickupCommand());
//		x.whenPressed(new StartBoilerVisionCommand());
//		a.whenPressed(new StopBoilerVisionCommand());
	}
}