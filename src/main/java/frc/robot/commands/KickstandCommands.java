package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.KickstandSubsystem;

public final class KickstandCommands {
    public static Command toggleKickerCommand(KickstandSubsystem kicker) {
        return Commands.sequence(
           Commands.runOnce(() ->kicker.ToggleExtendKicker(),kicker));
    }
}
