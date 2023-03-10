package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous2 extends SequentialCommandGroup {
    public static Command simpleCommand(DrivetrainSubsystem drive, DropSubsystem drop, ClawSubsystem claw,
            ArmSubsystem arm, LeadScrewSubsystem leadscrew) {
        return Commands.sequence(
                Commands.runOnce(() -> drive.resetGyroscope(), drive),
                PositionCommands.startCommands(arm, leadscrew, drop, claw),
                Commands.runOnce(() -> drop.dropin(), drop),
                Commands.runOnce(() -> new WaitCommand(1.0)),
                Commands.waitSeconds(1.0).andThen(
                        new StartEndCommand(() -> drive.stickDrive(1, .0, .0),
                                () -> drive.stickDrive(0.0, .0, .0), drive)));
    }
}