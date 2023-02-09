package frc.robot.subsystems;

import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.*;

public class ArmSubsystem extends SubsystemBase {
  private DoubleSolenoid bicepExtender = new DoubleSolenoid(PneumaticsModuleType.REVPH, ArmConstants.BICEP_EXTENDER_1,
      ArmConstants.BICEP_EXTENDER_2);
  private DoubleSolenoid forearmExtender = new DoubleSolenoid(PneumaticsModuleType.REVPH,
      ArmConstants.FOREARM_EXTENDER_1, ArmConstants.FOREARM_EXTENDER_2);
  private DoubleSolenoid wristExtender = new DoubleSolenoid(PneumaticsModuleType.REVPH, ArmConstants.WRIST_EXTENDER_1,
      ArmConstants.WRIST_EXTENDER_2);
  private boolean extendBicep = false;
  private boolean extendWrist = false;
  private boolean extendForearm = false;
  private boolean onforearmmotor = false;
  private final Spark forearmMotor = new Spark(ArmConstants.FOREARM_MOTOR);
  private final DigitalInput m_sensor_1 = new DigitalInput(ArmConstants.SENSOR_1);
  private final DigitalInput m_sensor_2 = new DigitalInput(ArmConstants.SENSOR_2);
  private int currentPosition = 0;

  public ArmSubsystem() {
  }

  public void ToggleExtendBicep() {
    if (extendBicep == false) {
      bicepExtender.set(DoubleSolenoid.Value.kReverse);
      extendBicep = true;
    } else if (extendBicep = true) {
      bicepExtender.set(DoubleSolenoid.Value.kForward);
      extendBicep = false;
    }
  }

  public void ToggleExtendFront() {
    if (extendWrist == false) {
      wristExtender.set(DoubleSolenoid.Value.kReverse);
      extendWrist = true;
    } else if (extendWrist = true) {
      wristExtender.set(DoubleSolenoid.Value.kForward);
      extendWrist = false;
    }
  }

  public void ToggleExtendForearm() {
    if (extendForearm == false) {
      forearmExtender.set(DoubleSolenoid.Value.kReverse);
      extendForearm = true;
    } else if (extendForearm = true) {
      forearmExtender.set(DoubleSolenoid.Value.kForward);
      extendForearm = false;
    }
  }

  public void toggleforearmMotor(double speed) {
    if (onforearmmotor == false) {
      forearmMotorForward();
      onforearmmotor = true;
    } else {
      forearmMotorOff();
      onforearmmotor = false;
    }
  }

  public void forearmMotorForward() {
    setMotorSpeed(.5);
    //forearmMotor.set(0.5);
  }

  public void forearmMotorBackward() {
    forearmMotor.set(-0.5);
  }

  public CommandBase forearmMotoron() {
    return this.runOnce(() -> setMotorSpeed(-.5));
  }

  public CommandBase forearmMotorOff() {
    return this.runOnce(() -> forearmMotor.set(0.0));
  }

  public CommandBase bicepIn() {
    return this.runOnce(() -> bicepExtender.set(DoubleSolenoid.Value.kReverse));
  }

  public CommandBase bicepout() {
    return this.runOnce(() -> bicepExtender.set(DoubleSolenoid.Value.kForward));
  }

  public CommandBase foreArmIn() {
    return this.runOnce(() -> forearmExtender.set(DoubleSolenoid.Value.kReverse));
  }

  public CommandBase foreArmout() {
    return this.runOnce(() -> forearmExtender.set(DoubleSolenoid.Value.kForward));
  }

  public CommandBase wristIn() {
    return this.runOnce(() -> wristExtender.set(DoubleSolenoid.Value.kReverse));
  }

  public CommandBase wristout() {
    return this.runOnce(() -> wristExtender.set(DoubleSolenoid.Value.kForward));
  }

  public boolean atDesiredPosition(int desiredPosition, boolean directionUp) {
    int direction = 0;

    if (directionUp)
      direction = +1;
    else
      direction = -1;

    if (currentPosition == 3 && directionUp)
      return true;

    if (currentPosition == 0 && !directionUp)
      return true;

    if (m_sensor_1.get()) {
      currentPosition = currentPosition + direction;
      if (currentPosition == desiredPosition) {
        return true;
      }
    }
    return false;
  }

  public void setMotorSpeed(double speed) {
    if (speed > 0.0) {
      if (!m_sensor_1.get()) {
        forearmMotor.set(0.0); // Moving up and top limit is tripped so stop
        SmartDashboard.putString("Lead Screw", "Off");
      } else {
        forearmMotor.set(speed); // Moving up, top limit is not tripped so continue at speed
        SmartDashboard.putString("Lead Screw", "On");
      }
    } else {
      if (!m_sensor_2.get()) {
        forearmMotor.set(0.0); // Moving down and bottom limit is tripped so stop
        SmartDashboard.putString("Lead Screw", "Off");
      } else {
        forearmMotor.set(speed); // Moving down, bottom limit is not tripped so continue at speed
        SmartDashboard.putString("Lead Screw", "On");
      }
    }
  }
}