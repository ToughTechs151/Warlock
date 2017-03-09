package org.usfirst.frc.team151.robot;

import org.usfirst.frc.team151.robot.subsystems.BallPickupSubsystem;
import org.usfirst.frc.team151.robot.subsystems.MecanumDriveSubsystem;
import org.usfirst.frc.team151.robot.subsystems.ShooterSubsystem;
import org.usfirst.frc.team151.robot.commands.AutonomousGearCenter;
import org.usfirst.frc.team151.robot.commands.AutonomousGearLeft;
import org.usfirst.frc.team151.robot.commands.AutonomousGearRight;
import org.usfirst.frc.team151.robot.commands.DriveStraightCommand;
import org.usfirst.frc.team151.robot.commands.StartShooterCommandGroup;
import org.usfirst.frc.team151.robot.subsystems.AgitatorSubsystem;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Preferences;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

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
		AutoGearLeft,
		AutoGearRight,
		AutoShooter,
		AutoTestingStraight
	}

	public static final MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final AgitatorSubsystem agitatorSubsystem = new AgitatorSubsystem();
	public static final BallPickupSubsystem ballPickupSubsystem = new BallPickupSubsystem();

	//Initialize cameras in roboInit()!!!!!!
	public static GearVision gearVision = null;
	public static BoilerVision boilerVision = null;
	public static DriverOI primaryDriverOi = null;
	public static CoDriverOI secondaryDriverOi = null;
	
	Preferences preference = null;
	
	public static double distanceToTravel;

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
		distanceToTravel = preference.getDouble("DistanceToTravel", 12.0);
		
		autoChooser.addDefault("AutoGearCenter", AutoModes.AutoGearCenter);
		autoChooser.addDefault("AutoGearLeft", AutoModes.AutoGearLeft);
		autoChooser.addDefault("AutoGearRight", AutoModes.AutoGearRight);
		autoChooser.addObject("AutoShooter", AutoModes.AutoShooter);

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
		case AutoGearLeft:
			autonomousCommand = new AutonomousGearLeft();
			break;
		case AutoGearCenter:
			autonomousCommand = new AutonomousGearCenter();
			break;
		case AutoTestingStraight:
			autonomousCommand = new DriveStraightCommand(distanceToTravel);
			break;
		case AutoGearRight:
			autonomousCommand = new AutonomousGearRight();
			break;
		case AutoShooter:
			autonomousCommand = new StartShooterCommandGroup();
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
			//TODO check if vision is on and if so, turn off
		}
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
		SmartDashboard.putNumber("Gyro value", mecanumDriveSubsystem.gyro.getAngle());
		mecanumDriveSubsystem.gyro.startLiveWindowMode();
	}

	/**
	 * This function is called periodically during test mode
	 */
	@Override
	public void testPeriodic() {
		LiveWindow.run();
	}
}
