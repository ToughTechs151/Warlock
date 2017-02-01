
package org.usfirst.frc.team151.robot;

import java.awt.Color;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team151.robot.commands.DriveWithJoystickCommand;
import org.usfirst.frc.team151.robot.commands.DumpLowGoalCommand;
import org.usfirst.frc.team151.robot.commands.DumpLowGoalCommand;
import org.usfirst.frc.team151.robot.commands.GearRetractCommand;
import org.usfirst.frc.team151.robot.commands.ShootHighGoalCommand;
import org.usfirst.frc.team151.robot.subsystems.BallPickupSubsystem;
import org.usfirst.frc.team151.robot.subsystems.GearSubsystem;
import org.usfirst.frc.team151.robot.subsystems.LowGoalDumperSubsystem;
import org.usfirst.frc.team151.robot.subsystems.MecanumDriveSubsystem;
import org.usfirst.frc.team151.robot.subsystems.RopeClimberSubsystem;
import org.usfirst.frc.team151.robot.subsystems.ShooterSubsystem;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.IterativeRobot;
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
	Thread visionThread;

	public static final MecanumDriveSubsystem mecanumDriveSubsystem = new MecanumDriveSubsystem();
	public static final ShooterSubsystem shooterSubsystem = new ShooterSubsystem();
	public static final RopeClimberSubsystem ropeClimberSubsystem = new RopeClimberSubsystem();
	public static final LowGoalDumperSubsystem lowGoalDumperSubsystem = new LowGoalDumperSubsystem();
	public static final GearSubsystem gearSubsystem = new GearSubsystem();
	public static final BallPickupSubsystem ballPickupSubsystem = new BallPickupSubsystem();
	public static DriverOI primaryDriverOi = null;
	public static CoDriverOI secondaryDriverOi = null;
	
	private CameraServer cameraServer = null;
	
	public enum AutoModes {
		AutoGear,
		AutoHighGoal,
		AutoLowGoal
		}
	
	Command autonomousCommand;
	
	private SendableChooser autoChooser = new SendableChooser();

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		System.out.println("Entering roboInit");
		primaryDriverOi = new DriverOI(RobotMap.primaryJoystick);
		secondaryDriverOi = new CoDriverOI(RobotMap.secondaryJoystick);
		autoChooser.addDefault("Default Auto", new DriveWithJoystickCommand());
		autoChooser.addDefault("AutoGear", AutoModes.AutoGear);
		autoChooser.addObject("AutoHighGoal", AutoModes.AutoHighGoal);
		autoChooser.addObject("AutoLowGoal", AutoModes.AutoLowGoal);
		 //chooser.addObject("My Auto", new MyAutoCommand());
		//SmartDashboard.putData("Auto mode", chooser);
		SmartDashboard.putData(Robot.ropeClimberSubsystem);
		
//		cameraServer = CameraServer.getInstance();
//		cameraServer.startAutomaticCapture();
		visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance();
			camera.startAutomaticCapture();
			// Set the resolution
			camera.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			Mat mat = new Mat();

			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					outputStream.notifyError(cvSink.getError());
					continue;
				}
				
//				 Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
//				Imgproc.circle(mat, new Point(200, 200), 50, new Scalar(0, 255, 0, 0), 15);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		visionThread.setDaemon(true);
		visionThread.start();
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
		autonomousCommand = autoChooser.getSelected();

		AutoModes autoSelected = (AutoModes)autoChooser.getSelected();
		        switch(autoSelected)  {
		        case AutoHighGoal:
		        autonomousCommand = new ShootHighGoalCommand();
		        break;
		        case AutoLowGoal:
		        autonomousCommand = new DumpLowGoalCommand();
		        break;
		        case AutoGear:
		        autonomousCommand = new GearRetractCommand();
		        break;
		        }

		/*

		* String autoSelected = SmartDashboard.getString("Auto Selector",

		* "Default"); switch(autoSelected) { case "My Auto": autonomousCommand

		* = new MyAutoCommand(); break; case "Default Auto": default:

		* autonomousCommand = new ExampleCommand(); break; }

		*/



		// schedule the autonomous command (example)

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
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	@Override
	public void teleopPeriodic() {
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
