package org.usfirst.frc.team151.robot;

import org.usfirst.frc.team151.robot.subsystems.BallPickupSubsystem;
import org.usfirst.frc.team151.robot.subsystems.LedSubsystem;
import org.usfirst.frc.team151.robot.subsystems.MecanumDriveSubsystem;
import org.usfirst.frc.team151.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team151.robot.commands.AutoStraightCenterCommandGroup;
import org.usfirst.frc.team151.robot.commands.AutonomousGearCenter;
//import org.usfirst.frc.team151.robot.commands.AutonomousGearLeft;
//import org.usfirst.frc.team151.robot.commands.AutonomousGearRight;
import org.usfirst.frc.team151.robot.commands.DriveStraightCommand;
import org.usfirst.frc.team151.robot.subsystems.AgitatorSubsystem;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
/*
 * TEST THE GYRO IN THE DRIVESTRAIGHT METHOD TO SEE IF THAT IS CAUSING ROBOT
 * TO VEER SIDEWAYS IN AUTONOMOUS
 */
/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {
	
	public enum AutoModes {
		AutoGearCenter,
//		AutoGearLeft,
//		AutoGearRight,
		AutoStraight,
		AutoStraightCenter
	}

	public static final MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final AgitatorSubsystem agitatorSubsystem = new AgitatorSubsystem();
	public static final BallPickupSubsystem ballPickupSubsystem = new BallPickupSubsystem();
	public static final LedSubsystem ledSubsystem = new LedSubsystem();
	
	Logger logger = new Logger();

	//Initialize cameras in roboInit()!!!!!!
	public static GearVision gearVision = null;
	public static BoilerVision boilerVision = null;
	public static DriverOI primaryDriverOi = null;
	public static CoDriverOI secondaryDriverOi = null;
	
	Preferences preference = null;
	
	public static double distanceToTravel;
	public static double shooterSpeed;
	public static double autoGearInitialDistance;
	public static double autoGearFinalDistance;
	public static boolean startLogger;

	private SendableChooser <AutoModes> autoChooser = new SendableChooser<AutoModes>();
	private Command autonomousCommand;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		boilerVision = new BoilerVision(0);
		gearVision = new GearVision(1);

		primaryDriverOi = new DriverOI(RobotMap.primaryJoystick);
		secondaryDriverOi = new CoDriverOI(RobotMap.secondaryJoystick);
		
		preference = Preferences.getInstance();
		distanceToTravel = preference.getDouble("AutoDistanceToTravelStraight", 72.0);
		shooterSpeed = preference.getDouble("ShooterSpeed", 0.9);
		autoGearInitialDistance = preference.getDouble("AutoGearInitialDistance", 12);
		autoGearFinalDistance = preference.getDouble("AutoGearFinalDistance", 54);
		startLogger = preference.getBoolean("TurnOnLogger", false);
		
		autoChooser.addDefault("AutoGearCenter", AutoModes.AutoGearCenter);
//		autoChooser.addDefault("AutoGearLeft", AutoModes.AutoGearLeft);
//		autoChooser.addDefault("AutoGearRight", AutoModes.AutoGearRight);
		autoChooser.addObject("AutoStraight", AutoModes.AutoStraight);
		autoChooser.addObject("AutoStraightCenter", AutoModes.AutoStraightCenter);

		SmartDashboard.putData("Autonomous mode", autoChooser);
		SmartDashboard.putData("Mecanum Drive", mecanumDriveSubsystem);
	}
	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}
 
	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {
		AutoModes autoSelected = (AutoModes)autoChooser.getSelected();
		switch(autoSelected)  {
//		case AutoGearLeft:
//			autonomousCommand = new AutonomousGearLeft();
//			break;
		case AutoGearCenter:
			autonomousCommand = new AutonomousGearCenter();
			break;
		case AutoStraight:
			autonomousCommand = new DriveStraightCommand(distanceToTravel);
			break;
//		case AutoGearRight:
//			autonomousCommand = new AutonomousGearRight();
//			break;
		case AutoStraightCenter:
			autonomousCommand = new AutoStraightCenterCommandGroup();
		}

		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	@Override
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	@Override
	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null) {
			autonomousCommand.cancel();
			agitatorSubsystem.stopAgitator();
			shooterSubsystem.stopShootBalls();
			ledSubsystem.turnOnLed();
			//TODO check if vision is on and if so, turn off
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		if (startLogger) {
			logger.log(); //TODO make sure I did this right???
		}
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
