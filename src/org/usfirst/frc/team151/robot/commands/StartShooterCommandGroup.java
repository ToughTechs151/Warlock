package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartShooterCommandGroup extends CommandGroup {

	public StartShooterCommandGroup() {
		addParallel(new StartBoilerVisionCommand());
		addParallel(new ShootBallsCommand());
	}
}
