package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.DropSubsystem;
import frc.robot.subsystems.KickstandSubsystem;
import frc.robot.subsystems.LeadScrewSubsystem;

//this is atest
public final class StartupCommands {
        public static Command startCommands(ArmSubsystem arm, LeadScrewSubsystem leadScrew, DropSubsystem drop,
                        KickstandSubsystem kick) {
                return Commands.sequence(
                                Commands.runOnce(() -> arm.bicepIn(), arm),
                                Commands.runOnce(() -> arm.forearmIn(), arm),
                                Commands.runOnce(() -> arm.wristIn(), arm),
                                Commands.runOnce(() -> drop.dropout(), drop),
                                Commands.runOnce(() -> kick.kickerin(), kick),
                                Commands.runOnce(() -> leadScrew.move_to_bottom(), leadScrew));

        }

}
