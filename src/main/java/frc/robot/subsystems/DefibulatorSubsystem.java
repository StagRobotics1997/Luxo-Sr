package frc.robot.subsystems;
import frc.robot.Constants.DefibulatorConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DefibulatorSubsystem extends SubsystemBase {
    private DoubleSolenoid DefibulatorExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, DefibulatorConstants.DEFIBULATOR_EXTENDER_1,DefibulatorConstants.DEFIBULATOR_EXTENDER_2);

    private boolean extendKicker = false;

    public DefibulatorSubsystem(){
    }
       
  public CommandBase ToggleExtendDefibulator(){
    if (extendKicker == false){
        extendKicker = true;
        return this.runOnce(() ->DefibulatorExtender.set(DoubleSolenoid.Value.kReverse));
    }
    extendKicker = false;
    return this.runOnce(() -> DefibulatorExtender.set(DoubleSolenoid.Value.kForward));
  }
}
