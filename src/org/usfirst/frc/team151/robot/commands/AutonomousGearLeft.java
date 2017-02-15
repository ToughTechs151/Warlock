package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGearLeft extends CommandGroup {

    public AutonomousGearLeft() {
    	addSequential(new DriveStraightCommand(4));
    	addSequential(new TurnCommand(120));
    	addSequential(new DriveStraightCommand(2));
//    	addSequential(new StartVisionCommand); TODO add when StartVisionCommand is made
    }
}
