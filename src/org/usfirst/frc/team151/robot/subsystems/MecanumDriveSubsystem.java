package org.usfirst.frc.team151.robot.subsystems;

import org.usfirst.frc.team151.robot.OI;
import org.usfirst.frc.team151.robot.Robot;
import org.usfirst.frc.team151.robot.RobotMap;
import org.usfirst.frc.team151.robot.commands.DriveWithJoystickCommand;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
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
	private SpeedController leftRearSpeedController = null;
	private RobotDrive robotDrive = null;
	
	private Encoder leftFrontEncoder = null; //TODO
	public Encoder rightFrontEncoder = null; //TODO
	private Encoder rightRearEncoder = null;
	public Encoder leftRearEncoder = null;
	
	public static ADXRS450_Gyro gyro = null;
	
	public static final double normalMultiplier = 0.5;
	public static final double creepMultiplier = 0.25;
	public static final double turboMultiplier = 1.0;
	
	public static final double totalDistance = 72.0;
	/*
	 * TRY CALCULATING DISTANCEPERPULSE WITHOUT ANY MATH OPERATIONS
	 * PUT IN THE VALUE THAT RESULTS :
	 * 2 * PI * R/12 / #OFPULSES FOR DISTANCEPERPULSE IN FEET
	 */
//	public static final double distancePerPulse = 0.0130899694;
//	public static final double distancePerPulse = 0.0065449847;
	public static final double distancePerPulse = 0.0523598776;

	public MecanumDriveSubsystem() {
		System.out.println("Entering MecanumDriveSubsystem constructor");
		leftFrontSpeedController = new Talon(RobotMap.leftFrontMotor);        
		rightFrontSpeedController = new Talon(RobotMap.rightFrontMotor);
		rightRearSpeedController = new Talon(RobotMap.rightRearMotor);
		leftRearSpeedController = new Talon(RobotMap.leftRearMotor);
		
		
//		leftFrontEncoder = new Encoder(0, 1); //check values
		rightFrontEncoder = new Encoder(RobotMap.rightFrontB, RobotMap.rightFrontA, false, Encoder.EncodingType.k4X);
//		rightRearEncoder = new Encoder(4, 5);
		leftRearEncoder = new Encoder(RobotMap.leftRearB, RobotMap.leftRearA, false, Encoder.EncodingType.k4X);
		
//		leftFrontEncoder.setDistancePerPulse(distancePerPulse);
		rightFrontEncoder.setDistancePerPulse(distancePerPulse);
//		rightRearEncoder.setDistancePerPulse(distancePerPulse);
		leftRearEncoder.setDistancePerPulse(distancePerPulse);
		
		leftRearEncoder.setReverseDirection(true);
		
//		leftFrontEncoder.reset();
		rightFrontEncoder.reset();
//		rightRearEncoder.reset();
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
		System.out.println("in x y rotation drive method");
		robotDrive.mecanumDrive_Cartesian(x, -y, rotation, gyro.getAngle());
//		System.out.println("leftRear encoder rate: " + leftRearEncoder.getRate() + 
//				"\t\tleftRear encoder distance: " + leftRearEncoder.getDistance());
		System.out.println("rightFront encoder rate: " + rightFrontEncoder.getRate() + 
				"\t\trightFront encoder distance: " + rightFrontEncoder.getDistance());
		System.out.println("rightFrontEncoder distance: " + rightFrontEncoder.get());
//		System.out.println("leftRear encoder direction: " + leftRearEncoder.getDirection() +
//				"\t\trightFront encoder direction: " + rightFrontEncoder.getDirection());
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
		System.out.println("count: " + rightFrontEncoder.get());
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
	
	public double getDistanceTraveled() {
//		return (leftFrontEncoder.getDistance() + rightFrontEncoder.getDistance() + 
//				leftRearEncoder.getDistance() + rightRearEncoder.getDistance())/4;
	//	return leftRearEncoder.getDistance(); //TODO maybe uncomment
		return rightFrontEncoder.getDistance();
	}
	
	public double getDistanceRemaining() {
		return (totalDistance - getDistanceTraveled());
	}
	
	public int getCountEncoders() {  //The current count
		return ((leftFrontEncoder.get() + rightFrontEncoder.get() + leftRearEncoder.get() +  rightRearEncoder.get())/4);
	}
	
	public double getRawDistanceTraveled() { //The count without compensation for decoding scale factor.
		return ((leftFrontEncoder.getRaw() + rightFrontEncoder.getRaw() + leftRearEncoder.get() + rightRearEncoder.get())/4);
	}
	
	@SuppressWarnings("deprecation")
	public double getTimeTraveled () { //The current period of the counter in seconds
		return ((leftFrontEncoder.getPeriod() + rightFrontEncoder.getPeriod() + leftRearEncoder.getPeriod() + rightRearEncoder.getPeriod())/4);
	}
	
	public double getRateOfEncoders() { // The current rate of the counter in units/sec
		return ((leftFrontEncoder.getRate() + rightFrontEncoder.getRate() + leftRearEncoder.getRate() + rightRearEncoder.getRate())/4);
	}
	
	public void resetAll() {
		leftRearEncoder.reset();
		rightRearEncoder.reset();
		rightFrontEncoder.reset();
		leftRearEncoder.reset();
		gyro.reset();
	}
}
