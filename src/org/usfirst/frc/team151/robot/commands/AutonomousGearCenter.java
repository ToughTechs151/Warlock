package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGearCenter extends CommandGroup {

    public AutonomousGearCenter() {
    	addSequential(new DriveStraightCommand(4));
    	addSequential(new GearVisionDriveCommand());
    }
}
