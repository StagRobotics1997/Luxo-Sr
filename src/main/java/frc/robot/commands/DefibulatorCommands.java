package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.DefibulatorSubsystem;

public final class DefibulatorCommands {
 
    public static Command toggledefibulatorCommand(DefibulatorSubsystem Defibulator) {
        return Commands.sequence(
            Defibulator.ToggleExtendDefibulator());
    }
}
