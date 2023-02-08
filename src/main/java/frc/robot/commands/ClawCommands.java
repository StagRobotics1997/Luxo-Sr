package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.ClawSubsystem;
public final class ClawCommands {
    public static Command ClawstartCommands(ClawSubsystem claw) {
        return Commands.sequence(
            claw.Clawclosed(),
            claw.ClawMotorOff());
    }
}