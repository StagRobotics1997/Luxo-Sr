package frc.robot.subsystems;
import frc.robot.Constants.ArmConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ArmSubsystem extends SubsystemBase {
    private DoubleSolenoid bicepExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ArmConstants.BICEP_EXTENDER_1, ArmConstants.BICEP_EXTENDER_2);
    private DoubleSolenoid forearmExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ArmConstants.FOREARM_EXTENDER_1, ArmConstants.FOREARM_EXTENDER_2);
    private DoubleSolenoid wristExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM,ArmConstants.WRIST_EXTENDER_1, ArmConstants.WRIST_EXTENDER_2);
    private boolean extendBicep = false;
    private boolean extendWrist= false;
    private boolean extendForearm= false;
    private boolean onforearmmotor= false;
    private Spark forearmMotor = new Spark(ArmConstants.FOREARM_MOTOR);
    
    public ArmSubsystem(){
    }
       
  public void ToggleExtendBicep(){
    if (extendBicep == false){
        bicepExtender.set(DoubleSolenoid.Value.kReverse);
        extendBicep = true;
    }else if(extendBicep= true){
        bicepExtender.set(DoubleSolenoid.Value.kForward);
        extendBicep = false;
    }
  }  
  
  public void ToggleExtendFront(){
    if (extendWrist == false){
        wristExtender.set(DoubleSolenoid.Value.kReverse);
        extendWrist = true;
    }else if(extendWrist = true){
       wristExtender.set(DoubleSolenoid.Value.kForward);
        extendWrist = false;
    }
  } 

  public void ToggleExtendForearm(){
    if (extendForearm == false){
        forearmExtender.set(DoubleSolenoid.Value.kReverse);
        extendForearm = true;
    }else if(extendForearm = true){
        forearmExtender.set(DoubleSolenoid.Value.kForward);
        extendForearm = false;
    }
  } 
  public void toggleforearmMotor(double speed){
    if (onforearmmotor == false){
      forearmMotorForward();
      onforearmmotor = true;
    }else{
      forearmMotorOff();
      onforearmmotor = false;
    }
  }
     
  public void forearmMotorForward() {
    forearmMotor.set(0.5);
  }
    
  public void forearmMotorBackward() {
   forearmMotor.set(-0.5);
  }

        
  public CommandBase forearmMotoron(){
    return this.runOnce(() -> forearmMotor.set(0.5));
   }
 
        
  public CommandBase forearmMotorOff(){
    return this.runOnce(() -> forearmMotor.set(0.0));
  }
      
 

  public CommandBase bicepIn(){
    return this.runOnce(() -> bicepExtender.set(DoubleSolenoid.Value.kReverse));
  }

  public CommandBase bicepout(){
    return this.runOnce(() -> bicepExtender.set(DoubleSolenoid.Value.kForward));
  }

  public CommandBase foreArmIn(){
    return this.runOnce(() -> forearmExtender.set(DoubleSolenoid.Value.kReverse));
  }

  public CommandBase foreArmout(){
    return this.runOnce(() -> forearmExtender.set(DoubleSolenoid.Value.kForward));
  }
  public CommandBase wristIn(){
    return this.runOnce(() -> wristExtender.set(DoubleSolenoid.Value.kReverse));
  }

  public CommandBase wristout(){
    return this.runOnce(() -> wristExtender.set(DoubleSolenoid.Value.kForward));
  }
}