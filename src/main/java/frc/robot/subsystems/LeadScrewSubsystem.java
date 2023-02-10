package frc.robot.subsystems;

import frc.robot.Constants.LeadScrewConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LeadScrewSubsystem extends SubsystemBase {
  private final Spark motor = new Spark(LeadScrewConstants.FOREARM_MOTOR);
  public final DigitalInput sensor_1 = new DigitalInput(LeadScrewConstants.SENSOR_1);
  public final DigitalInput sensor_2 = new DigitalInput(LeadScrewConstants.SENSOR_2);
  public final DigitalInput sensor_bottom = new DigitalInput(LeadScrewConstants.SENSOR_BOTTOM);
  public final DigitalInput sensor_top = new DigitalInput(LeadScrewConstants.SENSOR_TOP);

  public enum Position {
    NONE,
    BOTTOM,
    POSITION_1,
    POSITION_2,
    TOP
  }

  private Position lastPosition = Position.NONE;
  private Position desiredPosition = Position.NONE;

  public LeadScrewSubsystem() {
    if (!sensor_bottom.get()) {
      lastPosition = Position.POSITION_1;
    }
  }
  public void init() {
  }

  public final boolean getSensor1(){
    return !sensor_1.get();
  } 
  public boolean getSensor2(){
    return !sensor_2.get();
  } 
  public boolean getSensorTop(){
    return sensor_top.get();
  } 
  public boolean getSensorBottom(){
    return sensor_bottom.get();
  }
  
  public Command move_to_top() {
    desiredPosition = Position.TOP;
    return new InstantCommand(() -> process());
  }
  public Command move_to_bottom() {
    desiredPosition = Position.BOTTOM;
    return new InstantCommand(() -> process());
  }
  public Command move_to_position_1() {
    desiredPosition = Position.POSITION_1;
    return new InstantCommand(() -> process());
  }
  public Command move_to_position_2() {
    desiredPosition = Position.POSITION_2;
    return new InstantCommand(() -> process());
  }
  public void stopMotor() {
    // return this.runOnce(() ->  
    motor.set(0.0);
    //);
  }
  public Command startMotor() {
    return this.runOnce(() ->  motor.set(0.5));
  }
  public Command stopMotorCommand() {
    return this.runOnce(() ->  motor.set(0.0));
  }
  public Command move_to_Position(Position newPosition) {
    return new InstantCommand(() -> desiredPosition = newPosition);
    // return null;
  }

  public Position getPosition() {
    // check sensors to determine if we know for sure where we are
    if (sensor_bottom.get())
      return Position.BOTTOM;

    if (sensor_1.get())
      return Position.POSITION_1;

    if (sensor_2.get())
      return Position.POSITION_2;

    if (sensor_top.get())
      return Position.TOP;

    return Position.NONE;
  }

  public void process() {
    // Fail safes - check direction moving and check the appropriate limit switch
    double currentSpeed = motor.get();
    // if ((currentSpeed > 0 && LeadScrewConstants.UP_SPEED > 0 || currentSpeed < 0 && LeadScrewConstants.UP_SPEED < 0)
    //     && sensor_top.get()) {
    //   motor.set(0);
    // }
    // if ((currentSpeed > 0 && LeadScrewConstants.DOWN_SPEED > 0 || currentSpeed < 0 && LeadScrewConstants.DOWN_SPEED < 0)
    //     && sensor_bottom.get()) {
    //   motor.set(0);
    // }

    Position currentPosition = getPosition(); // temp variable to store where we are if we know based on the sensors

    if (currentPosition != Position.NONE)
      lastPosition = currentPosition; // we got a reading, store it

    if (currentPosition == desiredPosition || desiredPosition == Position.NONE) { // we're where we want to be
      if (currentSpeed != 0.0)
        motor.set(0.0); // stop the motor if it's moving
    }

    if (desiredPosition.compareTo(lastPosition) > 0) { // do we need to go up or down?
      motor.set(LeadScrewConstants.UP_SPEED);
    } else {
      motor.set(LeadScrewConstants.DOWN_SPEED);
    }
  }
}
