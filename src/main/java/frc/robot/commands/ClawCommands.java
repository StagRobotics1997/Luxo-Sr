package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.ClawSubsystem;

public final class ClawCommands {
  public static Command clawStartCommands(ClawSubsystem claw) {
    return Commands.sequence(
        Commands.runOnce(() -> claw.OpenClaw(), claw),
        Commands.runOnce(() -> claw.MotorOff(), claw));
  }

  public static Command closeClawCommand(ClawSubsystem claw) {
    return Commands.runOnce(() -> claw.CloseClaw(), claw);
  }

  public static Command openClawCommand(ClawSubsystem claw) {
    return Commands.runOnce(() -> claw.OpenClaw(), claw);
  }
}