package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGearRight extends CommandGroup {

    public AutonomousGearRight() {
       addSequential(new DriveStraightCommand(4));
       addSequential(new TurnCommand(240));
       addSequential(new DriveStraightCommand(2));
//       addSequential(new StartVisionCommand());
    }
}
