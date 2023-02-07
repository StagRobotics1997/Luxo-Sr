package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.ArmSubsystem;
//maggie
public final class ArmCommands {
    public static Command startCommands(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepIn(),
            arm.foreArmIn(),
            arm.wristout(),
            arm.forearmMotorOff());
    }
    public static Command pickupoffloorCommand(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepIn(),
            arm.foreArmIn(),
            arm.wristout(),
            arm.forearmMotoron());
    }
    
    public static Command midbarCommand(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepIn(),
            arm.foreArmout(),
            arm.wristout(),
            arm.forearmMotoron());
    }
    public static Command shelfCommand(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepIn(),
            arm.foreArmout(),
            arm.wristout(),
            arm.forearmMotoron());
    }
    public static Command HighbarCommand(ArmSubsystem arm) {
        return Commands.sequence(
            arm.bicepout(),
            arm.foreArmout(),
            arm.wristout(),
            arm.forearmMotoron());
    }
    
}

