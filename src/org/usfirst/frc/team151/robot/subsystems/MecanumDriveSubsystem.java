package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.OI;
import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.DriveWithJoystickCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
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
	private SpeedController leftRearSpeedController = null;
	private RobotDrive robotDrive = null;
	
	public Encoder leftRearEncoder = null;
	
	public static ADXRS450_Gyro gyro = null;
	
	public static final double normalMultiplier = 0.5;
	public static final double creepMultiplier = 0.25;
	public static final double turboMultiplier = 1.0;
	
	/*
	 * TRY CALCULATING DISTANCEPERPULSE WITHOUT ANY MATH OPERATIONS
	 * PUT IN THE VALUE THAT RESULTS :
	 * 2 * PI * R/12 / #OFPULSES FOR DISTANCEPERPULSE IN FEET
	 */
//	public static final double DISTANCE_PER_PULSE = Math.PI * 6 * 2 / 360;
	public static final double DISTANCE_PER_PULSE = 0.10471975667;

	public MecanumDriveSubsystem() {
		System.out.println("Entering MecanumDriveSubsystem constructor");
		leftFrontSpeedController = new Talon(RobotMap.leftFrontMotor);        
		rightFrontSpeedController = new Talon(RobotMap.rightFrontMotor);
		rightRearSpeedController = new Talon(RobotMap.rightRearMotor);
		leftRearSpeedController = new Talon(RobotMap.leftRearMotor);
		
		leftRearEncoder = new Encoder(RobotMap.leftRearA, RobotMap.leftRearB);
		leftRearEncoder.setDistancePerPulse(DISTANCE_PER_PULSE);
		leftRearEncoder.setReverseDirection(true);
		leftRearEncoder.reset();
		
		gyro = new ADXRS450_Gyro();
		gyro.calibrate();
		gyro.reset();
		
		robotDrive = new RobotDrive(leftFrontSpeedController, leftRearSpeedController, rightFrontSpeedController,
				rightRearSpeedController);
		robotDrive.setInvertedMotor(MotorType.kFrontRight, true);
		robotDrive.setInvertedMotor(MotorType.kRearRight, true);
		System.out.println("Mecanum done instantiating");
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
//		robotDrive.mecanumDrive_Cartesian(x, y, rotation, gyro.getAngle()); TODO TEST THIS
	}
	
	/**
	 * drive the robot using RobotDrive Cartesian
	 * @param joystick
	 */
	public void drive(OI oi) {
		System.out.println("About to call threshold method");
		double x = threshold( oi, 0); //0 is x-axis
		double y = threshold(oi, 1); //1 is y-axis
		double z = threshold(oi, 2); //2 is z-axis 
		robotDrive.mecanumDrive_Cartesian(x, y, z, 0);
	}
	
	public double threshold (OI oi, int axis) {
		JoystickButton rbumper = oi.getJoystickButton(6);
		JoystickButton rtrigger = oi.getJoystickButton(8);
		double rawAxis = oi.getJoystick().getRawAxis(axis);
		/*
		 * Check all of this (the deadzone part)
		 */
		if (rawAxis >= 0.03) { //adds a deadzone
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
		else {
			return 0;
		}
	}
	
	/**
	 * Log information to dashboard
	 */
	public void log() {
		System.out.println("Mecanum leftRear encoder rate: " + leftRearEncoder.getRate() + 
				"\t\tMecanum leftRear encoder distance: " + leftRearEncoder.getDistance());
		System.out.println("Mecanum leftRear encoder pulses: " + leftRearEncoder.get());
	}
	
	public double getDistanceTraveled() {
		return (leftRearEncoder.getDistance());
	}
	
	public void resetAll() {
		leftRearEncoder.reset();
		gyro.reset();
	}
}
