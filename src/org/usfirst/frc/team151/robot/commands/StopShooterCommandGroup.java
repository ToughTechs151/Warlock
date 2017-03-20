package org.usfirst.frc.team151.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class StopShooterCommandGroup extends CommandGroup {
	
	public StopShooterCommandGroup() {
		addSequential(new StopShootBallsCommand());
		addSequential(new StopAgitatorCommand());
//		addSequential(new StopBoilerVisionCommand());
	}
}