package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousGearRight extends CommandGroup {

    public AutonomousGearRight() {
       addSequential(new DriveStraightCommand(62));
       addSequential(new TurnCommand(240));
       addSequential(new AutoGearDriveCommand(12));
    }
}
