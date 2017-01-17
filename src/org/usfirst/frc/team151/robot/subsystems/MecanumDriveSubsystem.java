package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.DriveWithJoystickCommand;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class MecanumDriveSubsystem extends Subsystem {
	private SpeedController leftFrontSpeedController = null;
	private SpeedController rightFrontSpeedController = null;
	private SpeedController rightRearSpeedController = null;
	private SpeedController leftReartSpeedController = null;
	private RobotDrive robotDrive = null;

	public MecanumDriveSubsystem() {
		leftFrontSpeedController = new Talon(RobotMap.leftFrontMotor);
		rightFrontSpeedController = new Talon(RobotMap.rightFrontMotor);
		rightRearSpeedController = new Talon(RobotMap.rightRearMotor);
		leftReartSpeedController = new Talon(RobotMap.leftRearMotor);
		
		robotDrive = new RobotDrive(leftFrontSpeedController, leftReartSpeedController, rightFrontSpeedController, rightRearSpeedController);
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		setDefaultCommand(new DriveWithJoystickCommand());
	}
	
	/**
	 * drive the robot using RobotDrive Cartesian
	 * @param x
	 * @param y
	 * @param rotation
	 */
	public void drive(double x, double y, double rotation) {
		robotDrive.mecanumDrive_Cartesian(x, y, rotation, 0);
	}
	
	/**
	 * drive the robot using RobotDrive Cartesian
	 * @param joystick
	 */
	public void drive(Joystick joystick) {
		robotDrive.mecanumDrive_Cartesian(joystick.getX(), joystick.getY(), joystick.getZ(), 0);
	}
}
