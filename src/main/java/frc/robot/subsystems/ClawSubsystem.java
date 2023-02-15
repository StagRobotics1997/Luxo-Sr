package frc.robot.subsystems;

import frc.robot.Constants.ClawConstants;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import com.ctre.phoenix.motorcontrol.VictorSPXControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ClawSubsystem extends SubsystemBase {
  private DoubleSolenoid ClawExtender = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, ClawConstants.CLAW_EXTENDER_1,
      ClawConstants.CLAW_EXTENDER_2);
  private boolean extendClaw = false;
  private boolean onClawmotor = false;
  private VictorSPX ClawMotor = new VictorSPX(ClawConstants.CLAW_MOTOR);

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

  public CommandBase toggleClawMotor() {
    if (onClawmotor == false) {
      ClawMotorForward();
      onClawmotor = true;
    } else {
      ClawMotorOff();
      onClawmotor = false;
    }
    return null;
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
    ClawMotor.set(VictorSPXControlMode.Velocity, 0.85);
  }

  public void ClawMotorBackward() {
    ClawMotor.set(VictorSPXControlMode.Velocity, -0.85);
  }

  public CommandBase ClawMotoron() {
    return this.runOnce(() -> ClawMotor.set(VictorSPXControlMode.Velocity, 0.5));
  }

  public CommandBase ClawMotorOff() {
    return this.runOnce(() -> ClawMotor.set(VictorSPXControlMode.Velocity, 0.0));
  }

  public CommandBase CloseClaw() {
    return this.runOnce(() -> ClawExtender.set(DoubleSolenoid.Value.kReverse));
  }

  public CommandBase OpenClaw() {
    return this.runOnce(() -> ClawExtender.set(DoubleSolenoid.Value.kForward));
  }
}
