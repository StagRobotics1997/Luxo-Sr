package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.ArmSubsystem;
public final class ArmCommands {
    public static Command startCommands(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepIn(),
            arm.foreArmIn(),
            arm.wristout());
    }
    public static Command pickupoffloorCommand(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepIn(),
            arm.foreArmIn(),
            arm.wristout());
    }
    
    public static Command midbarCommand(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepIn(),
            arm.foreArmout(),
            arm.wristout());
    }
    public static Command shelfCommand(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepIn(),
            arm.foreArmout(),
            arm.wristout());
    }
    public static Command HighbarCommand(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepout(),
            arm.foreArmout(),
            arm.wristout());
    }
    
}

