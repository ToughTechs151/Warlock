package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.DriveWithJoystickCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
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
	public void drive(Joystick joystick) {
		robotDrive.mecanumDrive_Cartesian(threshold(joystick.getRawAxis(0)), threshold(joystick.getRawAxis(1)), 
				threshold(joystick.getRawAxis(2)), gyro.getAngle());
		//System.out.println("Joy 0 " + joystick.getRawAxis(0)+ " " + threshold(joystick.getRawAxis(0)));
		//System.out.println("Joy 1 " + joystick.getRawAxis(1)+ " " + threshold(joystick.getRawAxis(0)));
		//System.out.println(joystick.getRawAxis(2));
	}
	
	public double threshold (double rawAxis) {
		if (Math.abs(rawAxis) < 0.04) {
			return 0;
		}
		else {
			return 0.5*rawAxis;
		}
	}
	
	/**
	 * Log information to dashboard
	 */
	public void log() {
		SmartDashboard.putData("Gyro", gyro);
	}
}
