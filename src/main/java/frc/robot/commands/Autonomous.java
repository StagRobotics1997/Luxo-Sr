package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.*;
import frc.robot.commands.PositionCommands;

import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj.Timer;

public final class Autonomous {

  
  public static Command simpleCommand(DrivetrainSubsystem drive, DropSubsystem drop, ClawSubsystem claw, ArmSubsystem arm, LeadScrewSubsystem leadscrew) {

    return Commands.sequence(
      new StartEndCommand(() -> drive.resetGyroscope(),()->drive.resetGyroscope(),drive).withTimeout(1),
      PositionCommands.startCommands(arm ,leadscrew, drop, claw),
      
        //Commands.runOnce(() -> drive.resetGyroscope(), drive),
        Commands.waitSeconds(1),
        // new StartEndCommand(() -> drop.dropin(), () -> drop.dropin(), drop).withTimeout(1.0),
        //Commands.waitSeconds(1),
        // new StartEndCommand(() -> claw.OpenClaw(), () -> claw.CloseClaw(), claw).withTimeout(2.0),
        // Commands.waitSeconds(2),
      
        new StartEndCommand(() -> drive.stickDrive(-.5, .0, .0),
            () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(2),
            Commands.runOnce(() -> claw.OpenClaw(), claw));

    // new StartEndCommand(() -> drive.stickDrive(.25, .0, .0),
    // () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(3),
    // // Commands.runOnce(()-> Timer.delay(3.0)),
    // new StartEndCommand(() -> drive.stickDrive(.0, .25, .0),
    // () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(3),
    // // Commands.runOnce(()-> Timer.delay(3.0)),
    // new StartEndCommand(() -> drive.stickDrive(-.25, .0, .0),
    // () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(3),
    // // Commands.runOnce(()-> Timer.delay(3.0)),
    // new StartEndCommand(() -> drive.stickDrive(.0, -.25, .0),
    // () -> drive.stickDrive(0.0, 0, .0), drive).withTimeout(3));

  }
}