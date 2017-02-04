package org.usfirst.frc.team151.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.command.Subsystem;
import org.usfirst.frc.team151.robot.RobotMap;


/**
 *
 */
public class GearSubsystem extends Subsystem {
	private Relay gearDevice = null;

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public GearSubsystem() {
		// gearDevice = new Solenoid(RobotMap.GearSol);
//		 gearDevice = new Relay(RobotMap.GearPort);
	}
	
	public void gearDeposit(Joystick joystick) {
		//if joystick button is pressed, set true
//		gearDevice.set(Value.kForward);
	}

	public void gearRetract(Joystick joystick) {
		//if joystick button is pressed, set true
//		gearDevice.set(Value.kOff);
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}