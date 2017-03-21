package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutoStraightCenterCommandGroup extends CommandGroup {

    public AutoStraightCenterCommandGroup() {
    	
    	addSequential(new DriveStraightCommand(48));
    	addSequential(new TurnCommand(45));
    	addSequential(new DriveStraightCommand(60));
    	addSequential(new TurnCommand(315));
    	addSequential(new DriveStraightCommand(24));
    }
}
