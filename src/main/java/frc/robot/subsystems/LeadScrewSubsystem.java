package frc.robot.subsystems;

import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class LeadScrewSubsystem extends SubsystemBase {
  private final double UP_SPEED = 0.1;
  private final double DOWN_SPEED = -0.1;
  private final Spark motor = new Spark(ArmConstants.FOREARM_MOTOR);
  private final DigitalInput sensor_1 = new DigitalInput(ArmConstants.SENSOR_1);
  private final DigitalInput sensor_2 = new DigitalInput(ArmConstants.SENSOR_2);
  private final DigitalInput sensor_bottom = new DigitalInput(ArmConstants.SENSOR_BOTTOM);
  private final DigitalInput sensor_top = new DigitalInput(ArmConstants.SENSOR_TOP);
  public final enum Position{
    NONE,
    BOTTOM,
    POSITION_1,
    POSITION_2,
    TOP
  }
  private int lastPosition = Position.NONE;
  private int desiredPosition = Position.NONE;

  public LeadScrewSubsystem() {
    if (!sensor_bottom.get()) {
      lastPosition = Position.POSITION_1;
    }
  }
  
  public void move_to_Position(Position) {
    desiredPosition = Position;
  }

  public Position getPosition() {
    // check sensors to determine if we know for sure where we are
    if (sensor_bottom.get()) return Position.BOTTOM;
    if (sensor_1.get()) return Position.POSITION_1;
    if (sensor_1.get()) return Position.POSITION_2;
    if (sensor_top.get()) return Position.TOP;
    return Position.NONE;
  }

  public CommandBase process() {
    //Fail safes - check direction moving and the appropriate limit switch
    double currentSpeed = motor.get();
    if ((currentSpeed > 0 && UP_SPEED > 0 || currentSpeed< 0 && UP_SPEED < 0) && sensor_top.get()) motor.set(0);
    if ((currentSpeed > 0 && DOWN_SPEED > 0 || currentSpeed< 0 && DOWN_SPEED < 0) && sensor_bottom.get()) motor.set(0);

    currentPosition = getPosition() // temp variable to store where we are if we can determine it based on the sensors

    if (currentPosition != Position.NONE) lastPosition = currentPosition;  // we got a reading, store it

    if (currentPosition == desiredPosition || desiredPosition = Position.NONE) { // we're where we want to be
      if (currentSpeed != 0.0) motor.set(0.0); // stop the motor if it's moving
      return;
    }
    
    if (desiredPosition > lastPosition) { //do we need to go up or down?
      motor.set(UP_SPEED);
    } else {
      motor.set(DOWN_SPEED);
    }
  }
}