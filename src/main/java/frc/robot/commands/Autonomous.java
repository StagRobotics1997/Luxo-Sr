package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.PositionCommands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;

import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj.Timer;

public final class Autonomous {

  public static Command simpleCommand(DrivetrainSubsystem drive, DropSubsystem drop, ClawSubsystem claw,
      ArmSubsystem arm, LeadScrewSubsystem leadscrew) {

    return new ParallelCommandGroup(

        new ParallelCommandGroup(
            PositionCommands.position1Command(leadscrew),
            new WaitCommand(1))
            .andThen(
                new ParallelCommandGroup(
                    Commands.runOnce(() -> drop.dropin(), drop),
                    new WaitCommand(1)))
            .andThen(
                Commands.runOnce(() -> claw.CloseClaw(), claw))
            .andThen(
                new StartEndCommand(() -> drive.stickDrive(-.8, .0, .0),
                    () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(2.0)));

  }
}