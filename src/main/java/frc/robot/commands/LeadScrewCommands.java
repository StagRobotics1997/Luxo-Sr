package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.LeadScrewSubsystem;

public final class LeadScrewCommands {

  public static Command startCommands(LeadScrewSubsystem leadScrew) {}

  public static Command move_to_bottom(LeadScrewSubsystem leadScrew) {
    return run(leadScrew.move_to_top, leadScrew)
      .until(leadScrew.is_sensor_bottom_on())
      .withTimeout(10.0)
      .finallyDo(leadScrew.stopMotorCommand());
  }

  public static Command move_to_top(LeadScrewSubsystem leadScrew) {
    return run(leadScrew.move_to_bottom, leadScrew)
      .until(leadScrew.is_sensor_top_on())
      .withTimeout(10.0)
      .finallyDo(leadScrew.stopMotorCommand());
  }

  public static Command move_to_position_1(LeadScrewSubsystem leadScrew) {
    return run(leadScrew.move_to_position_1, leadScrew)
      .until(leadScrew.reached_destination() || leadScrew.failSafeCheck())
      .withTimeout(10.0)
      .finallyDo(leadScrew.stopMotorCommand());
  }

  public static Command move_to_position_2(LeadScrewSubsystem leadScrew) {
    return run(leadScrew.move_to_position_2, leadScrew)
      .until(leadScrew.reached_destination() || leadScrew.failSafeCheck())
      .withTimeout(10.0)
      .finallyDo(leadScrew.stopMotorCommand());
  }
}
