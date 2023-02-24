package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj2.command.StartEndCommand;

public final class Autonomous {

    public static Command simpleCommand(DrivetrainSubsystem drive) {
        return Commands.sequence(
                new StartEndCommand(() -> drive.stickDrive(.25, .0, .0),
                        () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(3),
                new StartEndCommand(() -> drive.stickDrive(.0, .25, .0),
                        () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(3),
                new StartEndCommand(() -> drive.stickDrive(-.25, .0, .0),
                        () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(3),
                new StartEndCommand(() -> drive.stickDrive(.0, -.25, .0),
                        () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(3));
    }
}