package frc.robot.subsystems;

import frc.robot.Constants.ClawConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {
  private DoubleSolenoid ClawExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ClawConstants.CLAW_EXTENDER_1,
      ClawConstants.CLAW_EXTENDER_2);
  private boolean extendClaw = false;
  private boolean onClawmotor = false;
  private VictorSPX ClawMotor = new VictorSPX(ClawConstants.CLAW_MOTOR);
  public final DigitalInput clawLimitSwich = new DigitalInput(ClawConstants.CLAW_LIMITSWICH);

  public ClawSubsystem() {
  }

  public void ToggleExtendClaw() {
    if (extendClaw == false) {
      ClawExtender.set(DoubleSolenoid.Value.kReverse);
      extendClaw = true;
    } else if (extendClaw = true) {
      ClawExtender.set(DoubleSolenoid.Value.kForward);
      extendClaw = false;
    }
  }

  public void toggleClawMotor() {
    if (onClawmotor == false) {
      ClawMotorBackward();
      onClawmotor = true;
    } else {
      ClawMotorOff();
      onClawmotor = false;
    }
    //return this.runOnce(() -> onClawmotor = onClawmotor);
  }
  // public CommandBase ToggleExtendDefibulator(){
  //   if (clawOpen){
  //       extendKicker = true;
  //       return this.runOnce(() ->DefibulatorExtender.set(DoubleSolenoid.Value.kReverse));
  //   }
  //   extendKicker = false;
  //   return this.runOnce(() -> DefibulatorExtender.set(DoubleSolenoid.Value.kForward));
  // }
  public void ClawMotorForward() {
    ClawMotor.set(VictorSPXControlMode.PercentOutput, 0.85);
  }

  public void ClawMotorBackward() {
    ClawMotor.set(VictorSPXControlMode.PercentOutput, -0.85);
  }

 

  


  public void grab(){
    ClawMotorOff();
    CloseClaw();
  }

  public void StartClaw(){
    ClawMotorOn();
    OpenClaw();
  }

  public void ClawMotorOn() {
     ClawMotor.set(VictorSPXControlMode.PercentOutput, -0.8);
  }

  public void ClawMotorOff() {
   ClawMotor.set(VictorSPXControlMode.PercentOutput, 0.0);
  }
  public void CloseClaw() {
    ClawExtender.set(DoubleSolenoid.Value.kReverse);
  }

  public void OpenClaw() {
    ClawExtender.set(DoubleSolenoid.Value.kForward);
  }
}
