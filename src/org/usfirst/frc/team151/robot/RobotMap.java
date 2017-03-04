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
	
	//Pneumatics-Analog Channel
	public static int lowGoalSolenoid = 0; 
	public static int GearSol = 1; // Solenoid Number on PCM Module
	public static int ropeLimitSwitch = 9;

	
	//Relay ports
//	public static int digOutRopeClimber = 0; //Relay port
//	public static int GearPort = 1; // Relay Port Number for Gear Control
	public static int shooterAgitator = 0;
	
	
	public static int primaryJoystick = 0;
	public static int secondaryJoystick = 1;
	
	
	public static int leftRearA = 0;
	public static int leftRearB = 1;
	public static int leftFrontA = 4;
	public static int leftFrontB = 5;
	public static int rightFrontA = 7;
	public static int rightFrontB = 8;
//	public static int rightRear1 = 6;
//	public static int rightRear2 = 7;
	
	//public static SPI.Port SPI_port_Gyro = new SPI.Port(0);
	// If you are using multiple modules, make sure to define both the port
	// number and the module. For example you with a rangefinder:
	// public static int rangefinderPort = 1;
	// public static int rangefinderModule = 1;
}
