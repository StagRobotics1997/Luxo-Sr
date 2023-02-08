package frc.robot.subsystems;
import frc.robot.Constants.ClawConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {
  private DoubleSolenoid ClawExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ClawConstants.CLAW_EXTENDER_1, ClawConstants.CLAW_EXTENDER_2);
  private boolean extendClaw= false;
  private boolean onClawmotor= false;
  private Spark ClawMotor = new Spark(ClawConstants.CLAW_MOTOR);
  
  public ClawSubsystem(){
  }

  public void ToggleExtendClaw(){
    if (extendClaw == false){
        ClawExtender.set(DoubleSolenoid.Value.kReverse);
        extendClaw = true;
    }else if(extendClaw= true){
        ClawExtender.set(DoubleSolenoid.Value.kForward);
        extendClaw = false;
    }
  }  

  public void toggleClawMotor(double speed){
    if (onClawmotor == false){
      ClawMotorForward();
      onClawmotor = true;
    }else{
      ClawMotorOff();
      onClawmotor = false;
    }
  }

  public void ClawMotorForward() {
    ClawMotor.set(0.5);
  }
    
  public void ClawMotorBackward() {
    ClawMotor.set(-0.5);
  }

        
  public CommandBase ClawMotoron(){
    return this.runOnce(() -> ClawMotor.set(0.5));
   }
 
        
  public CommandBase ClawMotorOff(){
    return this.runOnce(() -> ClawMotor.set(0.0));
  }
      
 

  public CommandBase Clawclosed(){
    return this.runOnce(() -> ClawExtender.set(DoubleSolenoid.Value.kReverse));
  }

  public CommandBase Clawopen(){
    return this.runOnce(() -> ClawExtender.set(DoubleSolenoid.Value.kForward));
  }
}
     