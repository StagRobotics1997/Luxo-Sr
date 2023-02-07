package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.KickstandSubsystem;

public final class KickstandCommands {
 
    public static Command toggleKickstandCommand(KickstandSubsystem kicker) {
        return Commands.sequence(
            kicker.ToggleExtendKicker());
    }
}