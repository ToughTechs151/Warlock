package org.usfirst.frc.team151.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//PWM Motors
	public static int leftFrontMotor = 1;
	public static int rightFrontMotor = 2;
	public static int rightRearMotor = 3;
	public static int leftRearMotor = 4;
	public static int shooterMotor = 5;
	public static int relayRopeClimber = 6;
	
	//Pneumatics
	public static int lowGoalSolenoid = 0;

	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
