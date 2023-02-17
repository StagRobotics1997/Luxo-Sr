package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.LeadScrewSubsystem;

//this is atest
public final class ArmCommands {
    public static Command startCommands(ArmSubsystem arm, LeadScrewSubsystem leadScrew) {
        return Commands.parallel(
                Commands.runOnce(() -> leadScrew.move_to_bottom(), leadScrew),
                Commands.sequence(
                        arm.bicepIn(),
                        arm.forearmIn(),
                        arm.wristOut()));
    }

    public static Command pickupoffloorCommand(ArmSubsystem arm, LeadScrewSubsystem leadScrew) {
        return Commands.parallel(
                Commands.runOnce(() -> leadScrew.move_to_bottom(), leadScrew),
                Commands.sequence(
                        arm.bicepIn(),
                        arm.forearmIn(),
                        arm.wristOut()));
    }

    public static Command midbarCommand(ArmSubsystem arm, LeadScrewSubsystem leadScrew) {
        return Commands.parallel(
                Commands.runOnce(() -> leadScrew.move_to_position_2(), leadScrew),
                Commands.sequence(
                        arm.bicepIn(),
                        arm.forearmOut(),
                        arm.wristOut()));
    }

    public static Command shelfCommand(ArmSubsystem arm, LeadScrewSubsystem leadScrew) {
        return Commands.parallel(
                Commands.runOnce(() -> leadScrew.move_to_position_2(), leadScrew),
                Commands.sequence(
                        arm.bicepIn(),
                        arm.forearmOut(),
                        arm.wristOut()));
    }

    public static Command HighbarCommand(ArmSubsystem arm, LeadScrewSubsystem leadScrew) {
        return Commands.parallel(
                Commands.runOnce(() -> leadScrew.move_to_top(), leadScrew),
                Commands.sequence(
                        arm.bicepout(),
                        arm.forearmOut(),
                        arm.wristOut()));
    }

}
