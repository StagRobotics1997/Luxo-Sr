package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.ClawSubsystem;

public final class ClawCommands {
   

    public static Command closeClawCommand(ClawSubsystem claw) {
        return Commands.runOnce(() -> claw.CloseClaw(), claw);
    }

    public static Command StartClawCommand(ClawSubsystem claw) {
        return Commands.runOnce(() -> claw.StartClaw(), claw);
    }

    public static Command openClawCommand(ClawSubsystem claw) {
        return Commands.runOnce(()-> claw.OpenClaw(),claw);
    }

    public static Command ClawMotoronCommand(ClawSubsystem claw) {
        return Commands.runOnce(()-> claw.ClawMotorOn(),claw);
    }

    public static Command ClawMotoroffCommand(ClawSubsystem claw) {
        return Commands.runOnce(()-> claw.ClawMotorOff(),claw);
    }

    // public static Command toggleClawMotorCommand(ClawSubsystem claw) {
    // return Commands.sequence(
    // claw.toggleClawMotor());
    // }
}
