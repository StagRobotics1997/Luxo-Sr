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


public class Autonomous3 {
    

    public static Command simpleCommand3(DrivetrainSubsystem drive, DropSubsystem drop, ClawSubsystem claw,
    ArmSubsystem arm, LeadScrewSubsystem leadscrew) {

  return Commands.sequence(
      new StartEndCommand(() -> drive.resetGyroscope(), () -> drive.resetGyroscope(), drive).withTimeout(1),
      PositionCommands.startCommands(arm, leadscrew, drop, claw).withTimeout(2),
      new WaitCommand(1.0),
      new StartEndCommand(() -> drive.stickDrive(.5, .0, .0),
          () -> drive.stickDrive(0.0, .0, .0), drive).withTimeout(2),
          new StartEndCommand(() -> drive.stickDrive(0.0, .0, .0),
          () -> drive.stickDrive(0.0, .0, 180.0), drive).withTimeout(2),
      Commands.runOnce(() -> claw.OpenClaw(), claw));

}
}