package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.OI;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.DriveWithJoystickCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.RobotDrive.MotorType;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class MecanumDriveSubsystem extends Subsystem {
	private SpeedController leftFrontSpeedController = null;
	private SpeedController rightFrontSpeedController = null;
	private SpeedController rightRearSpeedController = null;
	private SpeedController leftReartSpeedController = null;
	private RobotDrive robotDrive = null;
	
	public static ADXRS450_Gyro gyro = null;
	
	public static final double normalMultiplier = 0.5;
	public static final double creepMultiplier = 0.25;
	public static final double turboMultiplier = 1.0;

	public MecanumDriveSubsystem() {
		leftFrontSpeedController = new Talon(RobotMap.leftFrontMotor);        
		rightFrontSpeedController = new Talon(RobotMap.rightFrontMotor);
		rightRearSpeedController = new Talon(RobotMap.rightRearMotor);
		leftReartSpeedController = new Talon(RobotMap.leftRearMotor);
		
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
		gyro.reset();
		
		robotDrive = new RobotDrive(leftFrontSpeedController, leftReartSpeedController, rightFrontSpeedController,
				rightRearSpeedController);
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
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
		robotDrive.mecanumDrive_Cartesian(x, y, rotation, gyro.getAngle());
	}
	
	/**
	 * drive the robot using RobotDrive Cartesian
	 * @param joystick
	 */
	public void drive(OI oi) {
		Joystick joystick = oi.getJoystick();
		double x = threshold(joystick.getRawAxis(0), oi); //0 is x-axis
		double y = threshold(joystick.getRawAxis(1), oi); //1 is y-axis
		double z = threshold(joystick.getRawAxis(2), oi); //2 is z-axis 
		robotDrive.mecanumDrive_Cartesian(x, y, z, gyro.getAngle());
	}
	
	public double threshold (double rawAxis, OI oi) {
		JoystickButton rbumper = oi.getJoystickButton(6);
		JoystickButton rtrigger = oi.getJoystickButton(8);
		if (rbumper.get()) { //TODO check if returns true when pressed
			return rawAxis * creepMultiplier;
		}
		else if (rtrigger.get()) {
			return rawAxis * turboMultiplier;
		}
		else {
			return rawAxis * normalMultiplier;
		}
	}
	
	/**
	 * Log information to dashboard
	 */
	public void log() {
		SmartDashboard.putData("Gyro", gyro);
	}
}
