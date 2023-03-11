package frc.robot.subsystems;

import frc.robot.Constants.LeadScrewConstants;

import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.button.CommandJoystick;
import frc.robot.Utilities;

public class LeadScrewSubsystem extends SubsystemBase {
  private final VictorSPX m_motor_1 = new VictorSPX(LeadScrewConstants.MOTOR_1);
  private final VictorSPX m_motor_2 = new VictorSPX(LeadScrewConstants.MOTOR_2);
  public final DigitalInput sensor_1 = new DigitalInput(LeadScrewConstants.SENSOR_1);
  public final DigitalInput sensor_2 = new DigitalInput(LeadScrewConstants.SENSOR_2);
  public final DigitalInput sensor_3 = new DigitalInput(LeadScrewConstants.SENSOR_3);
  public final DigitalInput sensor_bottom = new DigitalInput(LeadScrewConstants.SENSOR_BOTTOM);
  public final DigitalInput sensor_top = new DigitalInput(LeadScrewConstants.SENSOR_TOP);
  private CommandJoystick m_joystick;

  public enum Position {
    NONE,
    BOTTOM,
    POSITION_1,
    POSITION_2,
    POSITION_3,
    TOP,
    MANUAL
  }

  private Position m_lastPosition = Position.NONE;
  private Position m_desiredPosition = Position.NONE;
  private double m_motorSpeed = 0.0;

  public LeadScrewSubsystem() {
    if (!is_sensor_bottom_on()) {
      m_lastPosition = Position.BOTTOM;
    }
    setMotorSpeed(0.0);
  }

  public final void setMotorSpeed(double newSpeed) {
    m_motorSpeed = newSpeed;
    m_motor_1.set(VictorSPXControlMode.PercentOutput, m_motorSpeed);
    m_motor_2.set(VictorSPXControlMode.PercentOutput, m_motorSpeed);
  }

  public final boolean is_sensor_1_on() {
    return !sensor_1.get();
  }

  public boolean is_sensor_2_on() {
    return !sensor_2.get();
  }

  public boolean is_sensor_3_on() {
    return !sensor_3.get();
  }

  public boolean is_sensor_top_on() {
    return !sensor_top.get();
  }

  public boolean is_sensor_bottom_on() {
    return !sensor_bottom.get();
  }

  public void move_to_top() {
    m_desiredPosition = Position.TOP;
    process();
  }

  public void move_to_bottom() {
    m_desiredPosition = Position.BOTTOM;
    process();
  }

  public void move_to_position_1() {
    m_desiredPosition = Position.POSITION_1;
    process();
  }

  public void move_to_position_2() {
    m_desiredPosition = Position.POSITION_2;
    process();
  }

  public void move_to_position_3() {
    m_desiredPosition = Position.POSITION_3;
    process();
  }

  public void toggle_manual_mode(CommandJoystick joystick) {
    m_joystick = joystick;
    if (m_desiredPosition == Position.MANUAL)
      m_desiredPosition = Position.NONE;
    else
      m_desiredPosition = Position.MANUAL;
    setMotorSpeed((0.0));
  }

  public void stopMotor() {
    m_desiredPosition = Position.NONE;
    setMotorSpeed(0.0);
  }

  public void stopMotor2(boolean interrupted) {
    m_desiredPosition = Position.NONE;
    setMotorSpeed(0.0);
  }

  public void moveToPosition(Position newPosition) {
    m_desiredPosition = newPosition;
    process();
  }

  public Position getPosition() {
    // check sensors to determine if we know for sure where we are
    if (is_sensor_bottom_on())
      return Position.BOTTOM;

    if (is_sensor_top_on())
      return Position.TOP;

    if (is_sensor_1_on())
      return Position.POSITION_1;

    if (is_sensor_2_on())
      return Position.POSITION_2;

    if (is_sensor_3_on())
      return Position.POSITION_3;

    return Position.NONE;
  }

  private boolean is_moving_up() {
    return ((m_motorSpeed > 0 && LeadScrewConstants.UP_SPEED > 0)
        || (m_motorSpeed < 0 && LeadScrewConstants.UP_SPEED < 0));
  }

  private boolean is_moving_down() {
    return ((m_motorSpeed > 0 && LeadScrewConstants.DOWN_SPEED > 0)
        || (m_motorSpeed < 0 && LeadScrewConstants.DOWN_SPEED < 0));
  }

  public boolean failSafeCheck() {
    // Fail safes - check direction moving and check the appropriate limit switch
    if (is_moving_up() && is_sensor_top_on()) {
      SmartDashboard.putString("Reached failsafe", "Top");
      stopMotor();
      return true;
    }
    if (is_moving_down() && is_sensor_bottom_on()) {
      SmartDashboard.putString("Reached failsafe", "Bottom");
      stopMotor();
      return true;
    }
    return false;
  }

  public void periodic() {
    SmartDashboard.putString("Desired Destination", m_desiredPosition.toString());
    SmartDashboard.putBoolean("Sensor1", sensor_1.get());
    SmartDashboard.putBoolean("Sensor2", sensor_2.get());
    SmartDashboard.putBoolean("Sensor3", sensor_3.get());
    SmartDashboard.putBoolean("SensorTop", sensor_top.get());
    SmartDashboard.putBoolean("SensorBottom", sensor_bottom.get());
    failSafeCheck();
    if (m_desiredPosition == Position.MANUAL) { // in manual mode, use joystick to control motor speed
      setMotorSpeed(Utilities.deadband(-m_joystick.getRawAxis(1) * .5));
    }
    // else {
    // if( m_desiredPosition == Position.NONE) setMotorSpeed(0.0);
    // }
  }

  public boolean reached_destination() {
    Position currentPosition = getPosition(); // store where we are if we know based on the sensors
    SmartDashboard.putString("Detected position", currentPosition.toString());

    if (currentPosition != Position.NONE)
      m_lastPosition = currentPosition; // we got a reading, store it

    return (currentPosition == m_desiredPosition); // || m_desiredPosition == Position.NONE);

  }

  public void process() {
    SmartDashboard.putString("step", "0");

    if (m_desiredPosition == Position.MANUAL)
      return; // In manual mode, don't use any of this logic
    boolean arrived = reached_destination();
    SmartDashboard.putBoolean("Reached Destination", arrived);
    if (arrived) { // we're where we want to be
      // if (currentSpeed != 0.0)
      m_desiredPosition = Position.NONE; // stop the motor if it's moving
    }
    if (m_desiredPosition == Position.NONE) {
      SmartDashboard.putString("step", "1");
      stopMotor();
      return;
    }
    if (m_desiredPosition.compareTo(m_lastPosition) > 0) { // do we need to go up or down?
      SmartDashboard.putString("step", "2");
      setMotorSpeed(LeadScrewConstants.UP_SPEED);
    } else {
      SmartDashboard.putString("step", "3");
      setMotorSpeed(LeadScrewConstants.DOWN_SPEED);
    }
  }
}