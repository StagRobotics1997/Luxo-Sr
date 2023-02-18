package frc.robot.subsystems;

import frc.robot.Constants.ClawConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {
  private DoubleSolenoid m_clawExtender = new DoubleSolenoid(
      PneumaticsModuleType.CTREPCM,
      ClawConstants.CLAW_EXTENDER_1,
      ClawConstants.CLAW_EXTENDER_2);
  // private boolean extendClaw = false;
  private boolean m_motorOn = false;
  private VictorSPX m_motor = new VictorSPX(ClawConstants.CLAW_MOTOR);

  public ClawSubsystem() {
  }

  public void toggleClawMotor() {
    if (m_motorOn == false) {
      MotorOn();
    } else {
      MotorOff();
    }
  }

  public void MotorOn() {
    m_motor.set(VictorSPXControlMode.Velocity, 0.5);
    m_motorOn = true;
  }

  public void MotorOff() {
    m_motor.set(VictorSPXControlMode.Velocity, 0);
    m_motorOn = false;
  }

  public void CloseClaw() {
    m_clawExtender.set(DoubleSolenoid.Value.kReverse);
  }

  public void OpenClaw() {
    m_clawExtender.set(DoubleSolenoid.Value.kForward);
  }
}
