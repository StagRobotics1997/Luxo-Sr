package frc.robot.subsystems;
import frc.robot.Constants.KickstandConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class KickstandSubsystem extends SubsystemBase {
    private DoubleSolenoid kickerExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, KickstandConstants.KICKER_EXTENDER_1, KickstandConstants.KICKER_EXTENDER_2);

    private boolean extendKicker = false;

    public KickstandSubsystem(){
    }
       
  public CommandBase ToggleExtendKicker(){
    if (extendKicker == false){
        extendKicker = true;
        return this.runOnce(() ->   kickerExtender.set(DoubleSolenoid.Value.kReverse));
    }
    extendKicker = false;
    return this.runOnce(() -> kickerExtender.set(DoubleSolenoid.Value.kForward));
  }
}