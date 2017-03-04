//package org.usfirst.frc.team151.robot.subsystems;
//
//import edu.wpi.first.wpilibj.command.Scheduler;
//import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//
//import org.usfirst.frc.team151.robot.commands.StopRopeCommand;
//import org.usfirst.frc.team151.robot.RobotMap;
//
//import edu.wpi.first.wpilibj.DigitalInput;
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Relay;
//import edu.wpi.first.wpilibj.Relay.Value;
//
///**
// *
// */
//public class RopeClimberSubsystem extends Subsystem {
//	
//	Relay diOutRopeClimber = null;
//	DigitalInput ropeLimitSwitch = null;
//
//    // Put methods for controlling this subsystem
//    // here. Call these from Commands.
//
//	public RopeClimberSubsystem() {
//		System.out.println("RopeClimber start initializing");
//		diOutRopeClimber = new Relay(RobotMap.digOutRopeClimber);
//		diOutRopeClimber.set(Value.kOff);
//		ropeLimitSwitch = new DigitalInput(RobotMap.ropeLimitSwitch);
//		System.out.println("RopeClimber initialized");
//	} 
//	
//    public void initDefaultCommand() {
////    	setDefaultCommand(new StopRopeCommand());
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    }
//    
//    public void climbRope(Joystick joystick) {
//    	if(diOutRopeClimber.get() != Value.kForward) {
//    		diOutRopeClimber.set(Value.kForward);
//    		Scheduler.getInstance().add(new StopRopeCommand());
//    	}    	
//    }
//    
//    public void stopRope() { 
//    	if(!ropeLimitSwitch.get()) {
//    		if(diOutRopeClimber.get() == Value.kForward) 
//    			diOutRopeClimber.set(Value.kOff);
//    	}
//    }
//}
