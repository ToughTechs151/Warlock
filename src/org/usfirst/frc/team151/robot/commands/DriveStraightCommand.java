package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveStraightCommand extends Command {
	
	PIDController driverPid = null;
	double distance = 0;
	boolean isDrive = false;
	
	double write = 0;

    public DriveStraightCommand(double distanceToTravel) {
    	System.out.println("Entering DriveStraightCommand constructor");
    	requires(Robot.mecanumDriveSubsystem);
    	driverPid = new PIDController(0.5, 0.01, 0.0, new PIDSource() {
			PIDSourceType m_sourceType = PIDSourceType.kDisplacement;

			@Override
			public double pidGet() {
				return Robot.mecanumDriveSubsystem.getDistanceRemaining();
			}

			@Override
			public void setPIDSourceType(PIDSourceType pidSource) {
				m_sourceType = pidSource;
			}

			@Override
			public PIDSourceType getPIDSourceType() {
				return m_sourceType;
			}
		}, new PIDOutput() {
			@Override
			public void pidWrite(double d) {
//				Robot.mecanumDriveSubsystem.drive(Robot.primaryDriverOi);
				write = d;
			}
		});
    	distance = distanceToTravel;
//    	driverPid.reset();
    	System.out.println("Finishing DriveStraightCommand constructor");
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.mecanumDriveSubsystem.leftRearEncoder.reset();
    	Robot.mecanumDriveSubsystem.rightFrontEncoder.reset();
    	driverPid.reset();
		driverPid.enable();
		driverPid.setSetpoint(distance);
		driverPid.setAbsoluteTolerance(0.01);
//		distance = Robot.gearVision.getDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//    	System.out.println(driverPid.get());
//    	Robot.mecanumDriveSubsystem.leftRearEncoder.setReverseDirection(true);
//    	if(driverPid.getError() > 0.01) {
//    		Robot.mecanumDriveSubsystem.drive(0, 0.25, 0);
//    	}
 //   	System.out.print(driverPid.getSetpoint() + "\t\t\t");
  //  	System.out.println(driverPid.get());
//    	System.out.println("LeftRear ticks: " + Robot.mecanumDriveSubsystem.leftRearEncoder.get() + 
//    			"RightFront ticks: " + Robot.mecanumDriveSubsystem.rightFrontEncoder.get());
  //  	System.out.println("RightFront ticks: " + Robot.mecanumDriveSubsystem.rightFrontEncoder.get());
    	if (//Robot.mecanumDriveSubsystem.leftRearEncoder.getDistance() < distance && 
    			-Robot.mecanumDriveSubsystem.rightFrontEncoder.getDistance() < distance) {
    		Robot.mecanumDriveSubsystem.drive(0, 0.25, 0);
    	}
    	else {
    		Robot.mecanumDriveSubsystem.drive(0, 0, 0);
    		isDrive = true;
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return isDrive;
    }

    // Called once after isFinished returns true
    protected void end() {
 //   	driverPid.disable();
    	Robot.mecanumDriveSubsystem.leftRearEncoder.reset();
    	Robot.mecanumDriveSubsystem.rightFrontEncoder.reset();
    	Robot.mecanumDriveSubsystem.drive(0, 0, 0);    	
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
