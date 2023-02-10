package frc.robot.subsystems;

import frc.robot.Constants.LeadScrewConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LeadScrewSubsystem extends SubsystemBase {
  private final Spark motor = new Spark(LeadScrewConstants.FOREARM_MOTOR);
  public final DigitalInput sensor_1 = new DigitalInput(LeadScrewConstants.SENSOR_1);
  public final DigitalInput sensor_2 = new DigitalInput(LeadScrewConstants.SENSOR_2);
  public final DigitalInput sensor_bottom = new DigitalInput(LeadScrewConstants.SENSOR_BOTTOM);
  public final DigitalInput sensor_top = new DigitalInput(LeadScrewConstants.SENSOR_TOP);
  private CommandJoystick m_joystick;

  public enum Position {
    NONE,
    BOTTOM,
    POSITION_1,
    POSITION_2,
    TOP
    MANUAL
  }

  private Position m_lastPosition = Position.NONE;
  private Position m_desiredPosition = Position.NONE;
  private double m_motorSpeed = 0.0;

  public LeadScrewSubsystem() {
    if (!is_sensor_bottom_on()) {
      m_lastPosition = Position.POSITION_1;
    }
    setMotorSpeed(0.0);
  }

  public final void setMotorSpeed(double newSpeed){
    m_motorSpeed = newSpeed;
    motor.set(m_motorSpeed);
  }

  public final boolean is_sensor_1_on() {
    return !sensor_1.get();
  }

  public boolean is_sensor_2_on() {
    return !sensor_2.get();
  }

  public boolean is_sensor_top_on() {
    return !sensor_top.get();
  }

  public boolean is_sensor_bottom_on() {
    return !sensor_bottom.get();
  }

  public Command move_to_top() {
    m_desiredPosition = Position.TOP;
    return processCommand();
  }

  public Command move_to_bottom() {
    m_desiredPosition = Position.BOTTOM;
    return processCommand();
  }

  public Command move_to_position_1() {
    m_desiredPosition = Position.POSITION_1;
    return processCommand();
  }

  public Command move_to_position_2() {
    m_desiredPosition = Position.POSITION_2;
    return processCommand();
  }

  public Command toggle_manual_mode(CommandJoystick joystick) {
    m_auxJoystick = joystick;
    if (m_desiredPosition == Position.MANUAL)
      m_desiredPosition = Position.NONE;
    else
      m_desiredPosition = Position.MANUAL;
  }

  public void stopMotor() {
    m_desiredPosition = Position.NONE;
    setMotorSpeed(0.0);
  }

  public Command motorStartUpCommand() {
    return this.runOnce(() -> setMotorSpeed(LeadScrewConstants.UP_SPEED));
  }

  public Command motorStartDownCommand() {
    return this.runOnce(() -> setMotorSpeed(LeadScrewConstants.UP_SPEED));
  }

  public Command motorOnCommand(double speed) {
    return this.runOnce(() -> setMotorSpeed(speed));
  }

  public Command stopMotorCommand() {
    return this.runOnce(() -> setMotorSpeed(0.0));
  }

  public Command move_to_Position(Position newPosition) {
    m_desiredPosition = newPosition;
    return processCommand();
  }

  public Command processCommand() {
    return this.runOnce(() -> process());
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

    return Position.NONE;
  }

  private boolean is_moving_up() {
    return ((m_motorSpeed > 0 && LeadScrewConstants.UP_SPEED > 0) || (m_motorSpeed < 0 && LeadScrewConstants.UP_SPEED < 0));
  }
  
  private boolean is_moving_down() {
    return ((m_motorSpeed > 0 && LeadScrewConstants.DOWN_SPEED > 0) || (m_motorSpeed < 0 && LeadScrewConstants.DOWN_SPEED < 0));
  }

  public boolean failSafeCheck() {
    // Fail safes - check direction moving and check the appropriate limit switch
    if (is_moving_up() && is_sensor_top_on()) {
      stopMotor();
      return true;
    }
    if (is_moving_down() && is_sensor_bottom_on()) {
      stopMotor();
      return true;
    }
    return false;
  }

  public void teleopPeriodic() {
    failSafeCheck();
    if (m_desiredPosition == Position.MANUAL) { // in manual mode, use joystick to control motor speed
      setMotorSpeed(Utilities.deadband(m_joystick.getRawAxis(2)));
    }
  }

  public boolean reached_destination() {
    Position currentPosition = getPosition(); // store where we are if we know based on the sensors

    if (currentPosition != Position.NONE)
      m_lastPosition = currentPosition; // we got a reading, store it

    return (currentPosition == m_desiredPosition || m_desiredPosition == Position.NONE);
      
  }

  public void process() {

    if (m_desiredPosition == Position.MANUAL) return; // In manual mode, don't use any of this logic 

    if (reached_destination()) { // we're where we want to be
      // if (currentSpeed != 0.0)
        stopMotor(); // stop the motor if it's moving
    }

    if (m_desiredPosition.compareTo(m_lastPosition) > 0) { // do we need to go up or down?
      setMotorSpeed(LeadScrewConstants.UP_SPEED);
    } else {
      setMotorSpeed(LeadScrewConstants.DOWN_SPEED);
    }
  }
}
