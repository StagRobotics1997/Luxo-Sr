// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.PS4Controller;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
// import edu.wpi.first.cameraserver.CameraServer;
// import edu.wpi.first.cscore.UsbCamera;
// import edu.wpi.first.math.trajectory.TrapezoidProfile.State;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import frc.robot.commands.Autonomous2;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems
  private final DrivetrainSubsystem m_robotDrive = new DrivetrainSubsystem();
  private final frc.robot.subsystems.ArmSubsystem m_arm = new ArmSubsystem();
  private final frc.robot.subsystems.ClawSubsystem m_claw = new ClawSubsystem();
  private final frc.robot.subsystems.KickstandSubsystem m_kickstand = new KickstandSubsystem();
  private final frc.robot.subsystems.DropSubsystem m_drop = new DropSubsystem();
  private final frc.robot.subsystems.LeadScrewSubsystem m_leadScrew = new LeadScrewSubsystem();

  // The autonomous routines
  // A simple auto routine that drives forward a specified distance, and then
  // stops.
  private final Command m_Autonomous2 = Autonomous2.simple2Command(m_robotDrive, m_drop,
      m_claw, m_arm, m_leadScrew);
  private final Command m_Autonomous3 = Autonomous3.simple3Command(m_robotDrive, m_drop, m_claw, m_arm, m_leadScrew);
  private final Command m_simpleAuto = Autonomous.simpleCommand(m_robotDrive, m_drop, m_claw, m_arm, m_leadScrew);

  // A chooser for autonomous commands
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // The driver's controller
  CommandJoystick m_primaryJoystick = new CommandJoystick(frc.robot.Constants.OIConstants.PRIMARY_JOYSTICK);
  CommandJoystick m_secondaryJoystick = new CommandJoystick(frc.robot.Constants.OIConstants.SECONDARY_JOYSTICK);
  CommandJoystick m_auxJoystick = new CommandJoystick(frc.robot.Constants.OIConstants.AUX_JOYSTICK);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);

    // Add commands to the autonomous command chooser
    m_chooser.setDefaultOption("SimpleAuto", m_simpleAuto);
    m_chooser.addOption("Autonomous2", m_Autonomous2);
    m_chooser.addOption("SimpleAuto", m_simpleAuto);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
    SmartDashboard.putNumber("primary Axis 1 ", m_primaryJoystick.getRawAxis(0));
    SmartDashboard.putNumber("primary Axis 2", m_primaryJoystick.getRawAxis(1));
    SmartDashboard.putNumber("secondary Axis 1", m_secondaryJoystick.getRawAxis(0));
    SmartDashboard.putNumber("secondary Axis 2", m_secondaryJoystick.getRawAxis(1));
    SmartDashboard.putNumber("aux Axis 1", m_auxJoystick.getRawAxis(0));
    SmartDashboard.putNumber("aux Axis 2", m_auxJoystick.getRawAxis(1));
    SmartDashboard.putString("Claw", "null");
    SmartDashboard.putNumber("Claw Motor", 0);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link PS4Controller}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  public void configureButtonBindings() {
    // Configure default command for drive subsystem
    m_robotDrive.setDefaultCommand(
        new RunCommand(
            () -> m_robotDrive.stickDrive(
                -m_primaryJoystick.getRawAxis(1),
                m_primaryJoystick.getRawAxis(0),
                m_secondaryJoystick.getRawAxis(0)),
            m_robotDrive));

    m_primaryJoystick
        .button(7)
        .onTrue(Commands.runOnce(() -> m_robotDrive.resetGyroscope(), m_robotDrive));
    m_primaryJoystick.button(3).onTrue(StartupCommands.startCommands(m_arm, m_leadScrew, m_drop, m_kickstand));
    // m_primaryJoystick.button(4).onTrue(Commands.runOnce(() ->m_robotDrive.turn(),m_robotDrive));
    m_primaryJoystick.button(8).onTrue(Commands.runOnce(() -> m_arm.ToggleExtendBicep(), m_arm));
    m_primaryJoystick.button(9).onTrue(Commands.runOnce(() -> m_arm.bicepout(), m_arm));
    m_primaryJoystick.button(11).onTrue(Commands.runOnce(() -> m_arm.bicepIn(), m_arm));
    m_primaryJoystick.button(12).onTrue(Commands.runOnce(() -> m_arm.forearmOut(), m_arm));

    m_secondaryJoystick.button(1).onTrue(ClawCommands.closeClawCommand(m_claw));
    m_secondaryJoystick.button(3).onTrue(Commands.runOnce(() -> m_robotDrive.turnTo180(), m_robotDrive));
    m_secondaryJoystick.button(5).onTrue(Commands.runOnce(() -> m_robotDrive.turnTo0(), m_robotDrive));
    m_secondaryJoystick.button(7).onTrue(Commands.runOnce(() -> m_arm.wristIn(), m_arm));
    m_secondaryJoystick.button(8).onTrue(Commands.runOnce(() -> m_arm.wristOut(), m_arm));
    m_secondaryJoystick.button(9).onTrue(Commands.runOnce(() -> m_arm.forearmIn(), m_arm));
    m_secondaryJoystick.button(12).onTrue(Commands.runOnce(() -> m_robotDrive.reset(), m_robotDrive));
    m_auxJoystick.button(3).onTrue(PositionCommands.startCommands(m_arm, m_leadScrew, m_drop, m_claw));

    m_auxJoystick.button(1).onTrue(Commands.runOnce(() -> m_claw.ToggleClaw(), m_claw));
    m_auxJoystick.button(2).onTrue(ClawCommands.ClawMotoroffCommand(m_claw));
    m_auxJoystick.button(3).onTrue(StartupCommands.startCommands(m_arm, m_leadScrew, m_drop, m_kickstand));
    m_auxJoystick.button(4).onTrue(PositionCommands.pickupoffloorCommand(m_arm, m_leadScrew));
    m_auxJoystick.button(5).onTrue(PositionCommands.shelfCommand(m_arm, m_leadScrew));
    m_auxJoystick.button(6).onTrue(PositionCommands.HighbarCommand(m_arm, m_leadScrew));
    m_auxJoystick.button(7).onTrue(Commands.runOnce(() -> m_leadScrew.move_to_bottom(), m_leadScrew));
    m_auxJoystick.button(8).onTrue(Commands.runOnce(() -> m_leadScrew.move_to_top(), m_leadScrew));
    m_auxJoystick.button(9).onTrue(Commands.runOnce(() -> m_arm.ToggleExtendFront(), m_arm));
    // m_auxJoystick.button(10).onTrue(KickstandCommands.toggleKickerCommand(m_kickstand));
    m_auxJoystick.button(11).onTrue(Commands.runOnce(() -> m_leadScrew.toggle_manual_mode(m_auxJoystick), m_leadScrew));
    m_auxJoystick.button(12).onTrue(DropCommands.toggleDropCommand(m_drop));

  }

  public void configureSensorTriggers() {
    new Trigger(m_leadScrew::is_sensor_top_on)
        .onTrue(Commands.runOnce(() -> m_leadScrew.process(), m_leadScrew));
    new Trigger(m_leadScrew::is_sensor_bottom_on)
        .onTrue(Commands.runOnce(() -> m_leadScrew.process(), m_leadScrew));
    new Trigger(m_leadScrew::is_sensor_1_on)
        .onTrue(Commands.runOnce(() -> m_leadScrew.process(), m_leadScrew));
    new Trigger(m_leadScrew::is_sensor_2_on)
        .onTrue(Commands.runOnce(() -> m_leadScrew.process(), m_leadScrew));
    new Trigger(m_leadScrew::is_sensor_3_on)
        .onTrue(Commands.runOnce(() -> m_leadScrew.process(), m_leadScrew));
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return m_chooser.getSelected();
  }

}
