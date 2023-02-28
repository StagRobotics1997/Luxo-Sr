package frc.robot.subsystems;

import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
  private DoubleSolenoid m_bicepExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ArmConstants.BICEP_EXTENDER_1,
      ArmConstants.BICEP_EXTENDER_2);
  private DoubleSolenoid m_forearmExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,
      ArmConstants.FOREARM_EXTENDER_1, ArmConstants.FOREARM_EXTENDER_2);
  private DoubleSolenoid m_wristExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ArmConstants.WRIST_EXTENDER_1,
      ArmConstants.WRIST_EXTENDER_2);
  private boolean m_bicepExtended = false;
  private boolean m_wristExtended = false;
  private boolean m_forearmExtended = false;

  public ArmSubsystem() {
  }

  public void ToggleExtendBicep() {
    if (m_bicepExtended == false) {
      m_bicepExtender.set(DoubleSolenoid.Value.kReverse);
      m_bicepExtended = true;
    } else if (m_bicepExtended = true) {
      m_bicepExtender.set(DoubleSolenoid.Value.kForward);
      m_bicepExtended = false;
    }
  }

  public void ToggleExtendFront() {
    if (m_wristExtended == false) {
      m_wristExtender.set(DoubleSolenoid.Value.kReverse);
      m_wristExtended = true;
    } else if (m_wristExtended = true) {
      m_wristExtender.set(DoubleSolenoid.Value.kForward);
      m_wristExtended = false;
    }
  }

  public void ToggleExtendForearm() {
    if (m_forearmExtended == false) {
      m_forearmExtender.set(DoubleSolenoid.Value.kReverse);
      m_forearmExtended = true;
    } else if (m_forearmExtended = true) {
      m_forearmExtender.set(DoubleSolenoid.Value.kForward);
      m_forearmExtended = false;
    }
  }

  public void bicepIn() {
    m_bicepExtender.set(DoubleSolenoid.Value.kReverse);
  }

  public void bicepout() {
    m_bicepExtender.set(DoubleSolenoid.Value.kForward);
  }

  public void forearmIn() {
    m_forearmExtender.set(DoubleSolenoid.Value.kReverse);
  }

  public void forearmOut() {
    m_forearmExtender.set(DoubleSolenoid.Value.kForward);
  }

  public void wristIn() {
    m_wristExtender.set(DoubleSolenoid.Value.kForward);
  }

  public void wristOut() {
    m_wristExtender.set(DoubleSolenoid.Value.kReverse);
  }
}