package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import frc.robot.subsystems.ArmSubsystem;
import frc.robot.subsystems.ClawSubsystem;
import frc.robot.subsystems.DropSubsystem;
import frc.robot.subsystems.LeadScrewSubsystem;
import edu.wpi.first.wpilibj.Timer;

//this is atest
public final class PositionCommands {
  public static Command startCommands(ArmSubsystem arm, LeadScrewSubsystem leadScrew, DropSubsystem drop,
      ClawSubsystem claw) {
    return Commands.sequence(
        // Commands.runOnce(() -> arm.wristIn(), arm),
        Commands.runOnce(() -> arm.bicepIn(), arm),
        Commands.runOnce(() -> arm.forearmIn(), arm),
        Commands.runOnce(() -> leadScrew.move_to_position_1(), leadScrew),
        Commands.runOnce(() -> arm.wristOut(), arm),
        Commands.runOnce(() -> drop.dropin(), drop),
        Commands.runOnce(() -> claw.CloseClaw(), claw));
  }

  public static Command pickupoffloorCommand(ArmSubsystem arm, LeadScrewSubsystem leadScrew) {

    return Commands.sequence(
        Commands.runOnce(() -> arm.wristIn(), arm),
        Commands.runOnce(() -> arm.bicepIn(), arm),
        Commands.runOnce(() -> arm.forearmIn(), arm),
        Commands.runOnce(() -> leadScrew.move_to_position_1(), leadScrew));
  }

  public static Command shelfCommand(ArmSubsystem arm, LeadScrewSubsystem leadScrew) {
    return Commands.sequence(
        Commands.runOnce(() -> arm.forearmOut(), arm),
        Commands.runOnce(() -> arm.wristOut(), arm),
        Commands.runOnce(() -> arm.bicepout(), arm),
        Commands.runOnce(() -> leadScrew.move_to_position_2(), leadScrew));

  }

  public static Command HighbarCommand(ArmSubsystem arm, LeadScrewSubsystem leadScrew) {
    return Commands.sequence(
        Commands.runOnce(() -> arm.forearmOut(), arm),
        Commands.runOnce(() -> arm.wristOut(), arm),
        Commands.runOnce(() -> arm.bicepout(), arm),
        // Commands.runOnce(() -> Timer.delay(.5)),
        Commands.runOnce(() -> leadScrew.move_to_position_3(), leadScrew));
  }

  public static Command position1Command(LeadScrewSubsystem leadScrew) {
    return new FunctionalCommand(
        leadScrew::stopMotor,
        leadScrew::move_to_position_1,
        leadScrew::stopMotor2,
        leadScrew::is_sensor_1_on,
        leadScrew);
  }
  public static Command position2Command(LeadScrewSubsystem leadScrew) {
    return new FunctionalCommand(
        leadScrew::stopMotor,
        leadScrew::move_to_position_2,
        leadScrew::stopMotor2,
        leadScrew::is_sensor_2_on,
        leadScrew);
  }
}