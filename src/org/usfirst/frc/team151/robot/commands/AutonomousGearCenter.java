package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGearCenter extends CommandGroup {

    public AutonomousGearCenter() {
    	//TODO check values
    	addSequential(new DriveStraightCommand(12));
    	addSequential(new AutoGearDriveCommand(54));
    }
}
