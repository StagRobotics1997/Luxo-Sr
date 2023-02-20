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
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.*;
import frc.robot.commands.*;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.button.Trigger;

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
  // private final frc.robot.commands.ArmCommands armCommands = new ArmCommands();
  private final frc.robot.subsystems.ArmSubsystem m_arm = new ArmSubsystem();
  public static UsbCamera m_camera_0;
  private final frc.robot.subsystems.ClawSubsystem m_claw = new ClawSubsystem();
  private final frc.robot.subsystems.KickstandSubsystem m_kickstand = new KickstandSubsystem();
  private final frc.robot.subsystems.DropSubsystem m_drop = new DropSubsystem();
  private final frc.robot.subsystems.LeadScrewSubsystem m_leadScrew = new LeadScrewSubsystem();

  // private final frc.robot.commands.ArmCommands armCommands = new ArmCommands();
  // private final frc.robot.subsystems.KickstandSubsystem kicker= new
  // KickstandSubsystem();

  // Retained command handles

  // The autonomous routines
  // A simple auto routine that drives forward a specified distance, and then
  // stops.
  // private final Command m_simpleAuto = Autos.simpleAuto(m_robotDrive);
  // private final Command m_complexAuto = Autos.complexAuto(m_robotDrive,
  // m_shooter);
  // A complex auto routine that drives forward, drops a hatch, and then drives
  // backward.

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
    // Configure the button bindings
    configureButtonBindings();

    m_camera_0 = CameraServer.startAutomaticCapture(0);
    NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    // Configure default commands
    // Set the default drive command to split-stick arcade drive
    m_robotDrive.setDefaultCommand(
        // A split-stick arcade command, with forward/backward controlled by the left
        // hand, and turning controlled by the right.
        new RunCommand(
            () -> m_robotDrive.stickDrive(
                m_primaryJoystick.getRawAxis(1),
                m_primaryJoystick.getRawAxis(0),
                m_secondaryJoystick.getRawAxis(0)),
            m_robotDrive));

    // Add commands to the autonomous command chooser
    // m_chooser.setDefaultOption("Simple Auto", m_simpleAuto);
    // m_chooser.addOption("Complex Auto", m_complexAuto);

    // Put the chooser on the dashboard
    Shuffleboard.getTab("Autonomous").add(m_chooser);
    SmartDashboard.putNumber("primary Axis 1 ", m_primaryJoystick.getRawAxis(0));
    SmartDashboard.putNumber("primary Axis 2", m_primaryJoystick.getRawAxis(1));
    SmartDashboard.putNumber("secondary Axis 1", m_secondaryJoystick.getRawAxis(0));
    SmartDashboard.putNumber("secondary Axis 2", m_secondaryJoystick.getRawAxis(1));
    SmartDashboard.putNumber("aux Axis 1", m_auxJoystick.getRawAxis(0));
    SmartDashboard.putNumber("aux Axis 2", m_auxJoystick.getRawAxis(1));
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
  private void configureButtonBindings() {
    m_primaryJoystick
        .button(1)
        .onTrue(m_robotDrive.resetGyroscope());

    // m_auxJoystick
    // .button(7)
    // .onTrue((ArmCommands.startCommands(arm)));

    // m_auxJoystick
    // .button(8)
    // .onTrue((ArmCommands.pickupoffloorCommand(arm)));

    // m_auxJoystick
    // .button(9)
    // .onTrue((ArmCommands.midbarCommand(arm)));

    // m_auxJoystick
    // .button(10)
    // .onTrue(ArmCommands.shelfCommand(arm));

    // m_auxJoystick
    // .button(11)
    // .onTrue(ArmCommands.HighbarCommand(arm));

    // m_auxJoystick
    // .button(12)
    // .onTrue(ClawCommands.ClawstartCommands(claw));

    m_auxJoystick.button(1).onTrue(ClawCommands.closeClawCommand(m_claw)).onFalse(ClawCommands.openClawCommand(m_claw));
    m_auxJoystick.button(3).onTrue(ArmCommands.startCommands(m_arm, m_leadScrew));
    m_auxJoystick.button(4).onTrue(ArmCommands.pickupoffloorCommand(m_arm, m_leadScrew));
    m_auxJoystick.button(5).onTrue(ArmCommands.midbarCommand(m_arm, m_leadScrew));
    m_auxJoystick.button(6).onTrue(ArmCommands.HighbarCommand(m_arm, m_leadScrew));
    m_auxJoystick.button(7).onTrue(Commands.runOnce(() -> m_leadScrew.move_to_bottom(), m_leadScrew));
    m_auxJoystick.button(8).onTrue(Commands.runOnce(() -> m_leadScrew.move_to_top(), m_leadScrew));
      // m_auxJoystick.button(9).onTrue(ClawCommands.toggleClawMotorCommand(m_claw));
      m_auxJoystick.button(2).onTrue(ClawCommands.ClawMotoronCommand(m_claw)).onFalse(ClawCommands.ClawMotoroffCommand(m_claw));
    // m_auxJoystick.button(2).onTrue(Commands.runOnce(() -> m_claw.toggleClawMotor(), m_claw));
    m_auxJoystick.button(10).onTrue(KickstandCommands.toggleKickerCommand(m_kickstand));
    m_auxJoystick.button(11).onTrue(Commands.runOnce(() -> m_leadScrew.toggle_manual_mode(m_auxJoystick), m_leadScrew));
    m_auxJoystick.button(12).onTrue(DropCommands.toggleDropCommand(m_drop));
    m_primaryJoystick.button(8).onTrue(Commands.runOnce(() -> m_arm.ToggleExtendBicep(), m_arm));
    m_secondaryJoystick.button(5).onTrue(Commands.runOnce(() -> m_arm.ToggleExtendForearm(), m_arm));
    m_auxJoystick.button(9).onTrue(Commands.runOnce(() -> m_arm.ToggleExtendFront(), m_arm));
    m_primaryJoystick.button(11).onTrue(m_arm.bicepIn());
    m_primaryJoystick.button(9).onTrue(m_arm.bicepout());
    m_secondaryJoystick.button(9).onTrue(m_arm.forearmIn());
    m_primaryJoystick.button(12).onTrue(m_arm.forearmOut());
    m_secondaryJoystick.button(7).onTrue(Commands.runOnce(() -> m_drop. dropin(), m_drop));
    m_secondaryJoystick.button(8).onTrue(Commands.runOnce(() -> m_drop. dropout(), m_drop));
  }

  public void configureTriggers() {
    Trigger sensorTopTrigger = new Trigger(m_leadScrew.sensor_top::get);
    sensorTopTrigger
        .onTrue(m_leadScrew.processCommand())
        .onFalse(m_leadScrew.processCommand());
    Trigger sensorBottomTrigger = new Trigger(m_leadScrew.sensor_bottom::get);
    sensorBottomTrigger
        .onTrue(m_leadScrew.processCommand())
        .onFalse(m_leadScrew.processCommand());
    Trigger sensor1Trigger = new Trigger(m_leadScrew.sensor_1::get);
    sensor1Trigger
        .onTrue(m_leadScrew.processCommand())
        .onFalse(m_leadScrew.processCommand());
    Trigger sensor2Trigger = new Trigger(m_leadScrew.sensor_2::get);
    sensor2Trigger
        .onTrue(m_leadScrew.processCommand())
        .onFalse(m_leadScrew.processCommand());
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
