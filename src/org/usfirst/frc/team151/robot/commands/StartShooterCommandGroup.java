package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartShooterCommandGroup extends CommandGroup {

	public StartShooterCommandGroup() {
//		addSequential(new StartBoilerVisionCommand());
		addSequential(new StartAgitatorCommand());
		addSequential(new ShootBallsCommand(1.0));		
	}
}