package frc.robot.subsystems;

import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
  private DoubleSolenoid m_bicepExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,
      ArmConstants.BICEP_EXTENDER_1,
      ArmConstants.BICEP_EXTENDER_2);
  private DoubleSolenoid m_forearmExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,
      ArmConstants.FOREARM_EXTENDER_1,
      ArmConstants.FOREARM_EXTENDER_2);
  private DoubleSolenoid m_wristExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,
      ArmConstants.WRIST_EXTENDER_1,
      ArmConstants.WRIST_EXTENDER_2);

  public ArmSubsystem() {
  }

  public void bicepIn() {
    m_bicepExtender.set(DoubleSolenoid.Value.kReverse);
  }

  public void bicepOut() {
    m_bicepExtender.set(DoubleSolenoid.Value.kForward);
  }

  public DoubleSolenoid.Value bicepValue() {
    return m_bicepExtender.get();
  }

  public void forearmIn() {
    m_forearmExtender.set(DoubleSolenoid.Value.kReverse);
  }

  public void forearmOut() {
    m_forearmExtender.set(DoubleSolenoid.Value.kForward);
  }

  public DoubleSolenoid.Value forearmValue() {
    return m_forearmExtender.get();
  }

  public void wristIn() {
    m_wristExtender.set(DoubleSolenoid.Value.kReverse);
  }

  public void wristOut() {
    m_wristExtender.set(DoubleSolenoid.Value.kForward);
  }

  public DoubleSolenoid.Value wristValue() {
    return m_wristExtender.get();
  }
}