package frc.robot.subsystems;

import frc.robot.Constants.KickstandConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class KickstandSubsystem extends SubsystemBase {
  private DoubleSolenoid kickerExtender = new DoubleSolenoid(12, PneumaticsModuleType.CTREPCM,
      KickstandConstants.KICKER_EXTENDER_1, KickstandConstants.KICKER_EXTENDER_2);

  private boolean extendKicker = false;

  public KickstandSubsystem() {
  }

  public void ToggleExtendKicker() {
    if (extendKicker == false) {
      extendKicker = true;
      kickerExtender.set(DoubleSolenoid.Value.kReverse);
    } else {
      extendKicker = false;
      kickerExtender.set(DoubleSolenoid.Value.kForward);
    }
  }
}