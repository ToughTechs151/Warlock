//package org.usfirst.frc.team151.robot.subsystems;
//
//import edu.wpi.first.wpilibj.command.Subsystem;
//
//import org.usfirst.frc.team151.robot.RobotMap;
//import org.usfirst.frc.team151.robot.commands.DumpLowGoalCommand;
//
//import edu.wpi.first.wpilibj.Joystick;
//import edu.wpi.first.wpilibj.Solenoid;
//
///**
// *
// */
//public class LowGoalDumperSubsystem extends Subsystem {
//	
//	Solenoid lowGoalDumperSolenoid = null;
//
//    // Put methods for controlling this subsystem
//    // here. Call these from Commands.
//	
//	public LowGoalDumperSubsystem() {
//		lowGoalDumperSolenoid = new Solenoid(RobotMap.lowGoalSolenoid);
//	}
//
//    public void initDefaultCommand() {
//    	setDefaultCommand(new DumpLowGoalCommand());
//        // Set the default command for a subsystem here.
//        //setDefaultCommand(new MySpecialCommand());
//    }
//    
//    public void dumpLowGoal(Joystick joystick) {
//    	//TODO insert code for method to dump balls into low goal
//    }
//}
//
