// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final class DriveConstants {
        public static final int DRIVETRAIN_FRONT_LEFT_DRIVE_MOTOR = 5; // CAN
        public static final int DRIVETRAIN_FRONT_LEFT_ANGLE_MOTOR = 6; // CAN
        public static final int DRIVETRAIN_FRONT_LEFT_ANGLE_ENCODER = 2; // Analog

        public static final int DRIVETRAIN_FRONT_RIGHT_DRIVE_MOTOR = 1; // CAN
        public static final int DRIVETRAIN_FRONT_RIGHT_ANGLE_MOTOR = 2; // CAN
        public static final int DRIVETRAIN_FRONT_RIGHT_ANGLE_ENCODER = 0; // Analog

        public static final int DRIVETRAIN_BACK_LEFT_ANGLE_ENCODER = 3; // Analog
        public static final int DRIVETRAIN_BACK_LEFT_ANGLE_MOTOR = 8; // CAN
        public static final int DRIVETRAIN_BACK_LEFT_DRIVE_MOTOR = 7; // CAN

        public static final int DRIVETRAIN_BACK_RIGHT_DRIVE_MOTOR = 3; // CAN
        public static final int DRIVETRAIN_BACK_RIGHT_ANGLE_MOTOR = 4; // CAN
        public static final int DRIVETRAIN_BACK_RIGHT_ANGLE_ENCODER = 1; // Analog
    }

    public static final class ArmConstants {
        // Solenoids
        public static final int BICEP_EXTENDER_1 = 0;
        public static final int BICEP_EXTENDER_2 = 1;
        public static final int WRIST_EXTENDER_1 = 4;
        public static final int WRIST_EXTENDER_2 = 5;
        public static final int FOREARM_EXTENDER_1 = 2;
        public static final int FOREARM_EXTENDER_2 = 3;
    }

    public static final class  LeadScrewConstants {
        // Motor
        public static final int FOREARM_MOTOR = 9;
        // Sensors
        public static final int SENSOR_1 = 1;
        public static final int SENSOR_2 = 2;
        public static final int SENSOR_TOP = 9;
        public static final int SENSOR_BOTTOM = 0;
        // Speeds
        public static final double UP_SPEED = 0.5;
        public static final double DOWN_SPEED = -0.5;
    }
    public static final class KickstandConstants {
        // solenoids
        public static final int KICKER_EXTENDER_1 = 8;
        public static final int KICKER_EXTENDER_2 = 9;
    }

    // public static final class DefibulatorConstants {
    //     // Solenoids
    //     public static final int DEFIBULATOR_EXTENDER_1 = 7;
    //     public static final int DEFIBULATOR_EXTENDER_2 = 8;
    // }

    public static final class DropConstants {
        // solenoids
        public static final int DROP_EXTENDER_1 = 10;
        public static final int DROP_EXTENDER_2 = 11;
    }

    public static final class ClawConstants {
        public static final int CLAW_MOTOR = 11;//can

        public static final int CLAW_EXTENDER_1 = 6;
        public static final int CLAW_EXTENDER_2 = 7;
    }

    public static final class OIConstants {
        public static final int PRIMARY_JOYSTICK = 0;
        public static final int SECONDARY_JOYSTICK = 1;
        public static final int AUX_JOYSTICK = 2;
    }
}
