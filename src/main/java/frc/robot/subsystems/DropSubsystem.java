package frc.robot.subsystems;

import frc.robot.Constants.DropConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DropSubsystem extends SubsystemBase {
  private DoubleSolenoid DropExtender = new DoubleSolenoid(12, PneumaticsModuleType.CTREPCM,
      DropConstants.DROP_EXTENDER_1, DropConstants.DROP_EXTENDER_2);

  private boolean m_dropExtended = false;

  public DropSubsystem() {
  }

  public void ToggleDrop() {
    if (m_dropExtended == false) {
      m_dropExtended = true;
      DropExtender.set(DoubleSolenoid.Value.kReverse);
    } else {
      m_dropExtended = false;
      DropExtender.set(DoubleSolenoid.Value.kForward);
    }
  }
}
