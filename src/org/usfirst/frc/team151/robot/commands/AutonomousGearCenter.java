package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGearCenter extends CommandGroup {

    public AutonomousGearCenter() {
    	//TODO check values
    	addSequential(new DriveStraightCommand(Robot.autoGearInitialDistance));
    	addSequential(new AutoGearDriveCommand(Robot.autoGearFinalDistance));
    }
}
