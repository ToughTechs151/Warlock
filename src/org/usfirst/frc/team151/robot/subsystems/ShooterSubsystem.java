package org.usfirst.frc.team151.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import org.usfirst.frc.team151.robot.RobotMap;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

/**
 *
 */
public class ShooterSubsystem extends Subsystem {
	private SpeedController wheelSpinner = null;
	
	public ShooterSubsystem() {
		wheelSpinner = new Talon(RobotMap.shooterMotor);
	}


    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	//setDefaultCommand(new )
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
	
    
    
	public void locateTarget(Joystick joystick) {
		//TODO put locateTarget code here
	}
	
	public void shootHighGoal(Joystick joystick) {
		//TODO put shootHighGoal code here
	}
}

