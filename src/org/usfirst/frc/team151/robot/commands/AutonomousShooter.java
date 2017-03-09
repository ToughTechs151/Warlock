package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousShooter extends CommandGroup {

    public AutonomousShooter() {
    	addSequential(new DriveStraightCommand(6));
    	addSequential(new TurnCommand(300));
    	addSequential(new StartShooterCommandGroup());
    }
}
