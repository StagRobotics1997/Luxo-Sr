package frc.robot.subsystems;

import frc.robot.Constants.DropConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DropSubsystem extends SubsystemBase {
  private DoubleSolenoid DropExtender = new DoubleSolenoid(12, PneumaticsModuleType.CTREPCM,
      DropConstants.DROP_EXTENDER_1, DropConstants.DROP_EXTENDER_2);

  private boolean extendDrop = false;

  public DropSubsystem() {
  }

  public void ToggleextendDrop() {
    if (extendDrop == false) {
      extendDrop = true;
      DropExtender.set(DoubleSolenoid.Value.kReverse);
    } else {
      extendDrop = false;
      DropExtender.set(DoubleSolenoid.Value.kForward);
    }
  }

  public void dropin() {
    extendDrop = true;
    DropExtender.set(DoubleSolenoid.Value.kReverse);
  }

  public void dropout() {
    extendDrop = false;
    DropExtender.set(DoubleSolenoid.Value.kForward);
  }
}
