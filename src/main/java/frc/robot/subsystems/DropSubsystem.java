package frc.robot.subsystems;
import frc.robot.Constants.DropConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DropSubsystem extends SubsystemBase {
    private DoubleSolenoid DropExtender = new DoubleSolenoid(12, PneumaticsModuleType.CTREPCM, DropConstants.DROP_EXTENDER_1, DropConstants.DROP_EXTENDER_2);

    private boolean extendDrop = false;

    public DropSubsystem(){
    }
       
  public CommandBase ToggleextendDrop(){
    if (extendDrop == false){
        extendDrop = true;
        return this.runOnce(() ->   DropExtender.set(DoubleSolenoid.Value.kReverse));
    }
    extendDrop = false;
    return this.runOnce(() -> DropExtender.set(DoubleSolenoid.Value.kForward));
  }
}
