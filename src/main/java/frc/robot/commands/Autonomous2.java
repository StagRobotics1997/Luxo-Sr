package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.Timer;

public class Autonomous2 extends SequentialCommandGroup {
  public static Command simple2Command(DrivetrainSubsystem drive, DropSubsystem drop, ClawSubsystem claw,
      ArmSubsystem arm, LeadScrewSubsystem leadscrew) {
    // return Commands.sequence(
    return new ParallelCommandGroup(
        Commands.runOnce(() -> arm.bicepIn(), arm),
        new WaitCommand(2))
        .andThen(
            new ParallelCommandGroup(
                Commands.runOnce(() -> arm.forearmIn(), arm),
                new WaitCommand(2)))
        .andThen(
            new ParallelCommandGroup(
                PositionCommands.position1Command(leadscrew),
                new WaitCommand(3)))
        .andThen(
            // Commands.runOnce(() -> arm.wristIn(), arm),
            new ParallelCommandGroup(
                Commands.runOnce(() -> drop.dropin(), drop),
                new WaitCommand(3)))
        .andThen(
            // Commands.runOnce(() -> drop.dropout(), drop),
            // Commands.runOnce(() -> drop.dropin(), drop),
            // Commands.runOnce(() -> drop.dropout(), drop),
            Commands.runOnce(() -> claw.CloseClaw(), claw))
        .andThen(
            // // Commands.runOnce(() -> drive.resetGyroscope(), drive).withTimeout(2.0),
            // PositionCommands.startCommands(arm, leadscrew, drop, claw).withTimeout(2.0),
            // // Commands.runOnce(() -> drop.dropin(), drop).withTimeout(2.0),
            // // new WaitCommand(1.0).withTimeout(1.0),
            // // Commands.runOnce(() -> drive.stickDrive(-.5, .0, .0)).withTimeout(2.0),
            new WaitCommand(2.0))
        .andThen(
            // Commands.print("************************** WMA 2
            // *************************"),
            // Commands.runOnce(() -> drive.stickDrive(-0, .0, .0)).withTimeout(2.0)
            new StartEndCommand(() -> drive.stickDrive(-.5, .0, .0),
                () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(2.0));
    // new StartEndCommand(() -> drive.drive(new Translation2d(-.5, 0.0), .0, true),
    // () -> drive.drive(new Translation2d(0.0, 0.0), .0, true)).withTimeout(2.0)
    // );
  }
}