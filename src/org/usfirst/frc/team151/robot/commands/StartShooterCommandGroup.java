package org.usfirst.frc.team151.robot.commands;

import org.usfirst.frc.team151.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StartShooterCommandGroup extends CommandGroup {

	public StartShooterCommandGroup() {
		addParallel(new StartBoilerVisionCommand());
		addParallel(new ShootBallsCommand(Robot.boilerVision.getDistanceFromCenter()));
//		addParallel(new StartAgitatorCommand()); TODO UNCOMMENT THIS
	}
}
