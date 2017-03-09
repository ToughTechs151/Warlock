package org.usfirst.frc.team151.robot;
 
/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//PWM Motors
	public static int rightRearMotor = 0;
	public static int leftRearMotor = 1;
	public static int leftFrontMotor = 2;
	public static int rightFrontMotor = 3;
	public static int shooterMotor = 4;
	public static int spinBallPickup = 5;
		
	//Relay ports
	public static int agitatorRelay = 0;
	public static int ledLights = 1;
	
	//OI Ports
	public static int primaryJoystick = 0;
	public static int secondaryJoystick = 1;
	
	//Encoder ports
	public static int leftRearA = 1;
	public static int leftRearB = 0;
	public static int rightFrontA = 3;
	public static int rightFrontB = 2;
	public static int shooterEncoderA = 4;
	public static int shooterEncoderB = 5;
	
	//Vision HSV Limits
	static double[] hsvThresholdHue = {40.0, 120.0};
	static double[] hsvThresholdSaturation = {0.0, 40.0};
	static double[] hsvThresholdValue = {235.0, 255.0};
	
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
