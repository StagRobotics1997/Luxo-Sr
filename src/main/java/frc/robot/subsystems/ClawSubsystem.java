package frc.robot.subsystems;

import frc.robot.Constants.ClawConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ClawSubsystem extends SubsystemBase {
  private DoubleSolenoid m_clawExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ClawConstants.CLAW_EXTENDER_1,
      ClawConstants.CLAW_EXTENDER_2);
  private boolean m_clawExtended = false;
  private boolean m_motorOn = false;
  private VictorSPX m_motor = new VictorSPX(ClawConstants.CLAW_MOTOR);
  public final DigitalInput clawLimitSwich = new DigitalInput(ClawConstants.CLAW_LIMITSWICH);

  public ClawSubsystem() {
  }

  public void ToggleExtendClaw() {
    if (m_clawExtended == false) {
      m_clawExtender.set(DoubleSolenoid.Value.kReverse);
      m_clawExtended = true;
    } else if (m_clawExtended = true) {
      m_clawExtender.set(DoubleSolenoid.Value.kForward);
      m_clawExtended = false;
    }
  }

  public void toggleClawMotor() {
    if (m_motorOn == false) {
      ClawMotorBackward();
      m_motorOn = true;
    } else {
      ClawMotorOff();
      m_motorOn = false;
    }
  }

  public void ClawMotorForward() {
    m_motor.set(VictorSPXControlMode.PercentOutput, 0.85);
  }

  public void ClawMotorBackward() {
    m_motor.set(VictorSPXControlMode.PercentOutput, -0.85);
  }

  public void grab() {
    ClawMotorOff();
    CloseClaw();
  }

  public void StartClaw() {
    ClawMotorOn();
    OpenClaw();
  }

  public void ClawMotorOn() {
    SmartDashboard.putNumber("Claw Motor", -0.8);
    m_motor.set(VictorSPXControlMode.PercentOutput, -0.8);
  }

  public void ClawMotorOff() {
    SmartDashboard.putNumber("Claw Motor", 0.0);
    m_motor.set(VictorSPXControlMode.PercentOutput, 0.0);
  }

  public void CloseClaw() {
    SmartDashboard.putString("Claw", "Close");
    m_clawExtender.set(DoubleSolenoid.Value.kForward);
  }

  public void OpenClaw() {
    m_clawExtender.set(DoubleSolenoid.Value.kReverse);
  }
}
