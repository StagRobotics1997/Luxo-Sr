package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.LeadScrewSubsystem;
public final class LeadScrewCommands {
    public static Command startCommands(LeadScrewSubsystem leadScrew) {
    }
    public static Command move_to_bottom(LeadScrewSubsystem leadScrew) {
        return Commands
            .run(leadScrew.move_to_top, leadScrew)
            .repeatedly()
            .until(leadScrew.is_sensor_bottom_on());
    }
    
    public static Command move_to_top(LeadScrewSubsystem leadScrew) {
        return Commands
            .run(leadScrew.move_to_bottom, leadScrew)
            .repeatedly()
            .until(leadScrew.is_sensor_top_on());
    }

    public static Command move_to_position_1(LeadScrewSubsystem leadScrew) {
        return Commands
            .run(leadScrew.move_to_position_1, leadScrew)
            .repeatedly()
            .until(leadScrew.reached_destination() || leadScrew.failSafeCheck());
    }

    public static Command move_to_position_2(LeadScrewSubsystem leadScrew) {
        return Commands
            .run(leadScrew.move_to_position_2, leadScrew)
            .repeatedly()
            .until(leadScrew.reached_destination() || leadScrew.failSafeCheck());
    }
}

