//package org.usfirst.frc.team151.robot.subsystems;
//
//import org.usfirst.frc.team151.robot.RobotMap;
//
//import edu.wpi.first.wpilibj.Relay;
//import edu.wpi.first.wpilibj.Relay.Value;
//import edu.wpi.first.wpilibj.command.Subsystem;
//
///**
// *
// */
//public class AgitatorSubsystem extends Subsystem {
//	
//	Relay agitator = null;
//
//    // Put methods for controlling this subsystem
//    // here. Call these from Commands.
//	public AgitatorSubsystem() {
//		System.out.println("Agitator");
//		agitator = new Relay(RobotMap.shooterAgitator);
//	}
//
//    public void initDefaultCommand() {
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    }
//    
//    public void startAgitator() {
//    	agitator.set(Value.kForward);
//    }
//    
//    public void stopAgitator() {
//    	agitator.set(Value.kOff);
//    }
//    
//    public void reverseAgitator() {
//    	agitator.set(Value.kReverse);
//    }
//}
//
