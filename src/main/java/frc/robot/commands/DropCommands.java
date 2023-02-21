package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.DropSubsystem;

public final class DropCommands {

  public static Command toggleDropCommand(DropSubsystem drop) {
    return Commands.sequence(
        Commands.runOnce(() -> drop.ToggleextendDrop(), drop));
  }
}