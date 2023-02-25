package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Utilities;
import frc.robot.drivers.Gyroscope;
import frc.robot.drivers.SwerveModule;
import frc.robot.drivers.Mk2SwerveModuleBuilder;
import frc.robot.drivers.NavX;
import frc.robot.math.Vector2;
import frc.robot.Constants.DriveConstants;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel;

public class DrivetrainSubsystem extends SubsystemBase {
  private static final double TRACKWIDTH = 22.25;
  private static final double WHEELBASE = 24.44;

  // The offsets are in Radians now. Copy the array from the dashbaord to assign
  // new values
  private double[] OFFSETS = {3.6486, 1.2838, 2.8949, 5.9312 };
  // private double[] OFFSETS = { 0.00, 0.00, 0.00, 0.00 };

  private static DrivetrainSubsystem instance;

  private SwerveModule frontLeftModule ;
  private SwerveModule frontRightModule;
  private SwerveModule backLeftModule;
  private SwerveModule backRightModule;
  
  private int m_counter = 0; // counter used to throttle the updates to the dashboard

  private final SwerveDriveKinematics kinematics = new SwerveDriveKinematics(
      new Translation2d(TRACKWIDTH / 2.0, WHEELBASE / 2.0),
      new Translation2d(TRACKWIDTH / 2.0, -WHEELBASE / 2.0),
      new Translation2d(-TRACKWIDTH / 2.0, WHEELBASE / 2.0),
      new Translation2d(-TRACKWIDTH / 2.0, -WHEELBASE / 2.0));

  private final Gyroscope gyroscope = new NavX(I2C.Port.kOnboard);

  public DrivetrainSubsystem() {
//     Timer.delay(1.0);
    gyroscope.calibrate();
    gyroscope.setInverted(true); // You might not need to invert the gyro
//     Timer.delay(1.0);
     frontLeftModule = new Mk2SwerveModuleBuilder(
      new Vector2(TRACKWIDTH / 2.0, WHEELBASE / 2.0))
      .angleEncoder(new AnalogInput(DriveConstants.DRIVETRAIN_FRONT_LEFT_ANGLE_ENCODER),
             -OFFSETS[0])
      .angleMotor(new CANSparkMax(DriveConstants.DRIVETRAIN_FRONT_LEFT_ANGLE_MOTOR,
          CANSparkMaxLowLevel.MotorType.kBrushless),
          Mk2SwerveModuleBuilder.MotorType.NEO)
      .driveMotor(new CANSparkMax(DriveConstants.DRIVETRAIN_FRONT_LEFT_DRIVE_MOTOR,
          CANSparkMaxLowLevel.MotorType.kBrushless),
          Mk2SwerveModuleBuilder.MotorType.NEO)
      .build();
   frontRightModule = new Mk2SwerveModuleBuilder(
      new Vector2(TRACKWIDTH / 2.0, -WHEELBASE / 2.0))
      .angleEncoder(new AnalogInput(DriveConstants.DRIVETRAIN_FRONT_RIGHT_ANGLE_ENCODER),
           -OFFSETS[1])
      .angleMotor(new CANSparkMax(DriveConstants.DRIVETRAIN_FRONT_RIGHT_ANGLE_MOTOR,
          CANSparkMaxLowLevel.MotorType.kBrushless),
          Mk2SwerveModuleBuilder.MotorType.NEO)
      .driveMotor(new CANSparkMax(DriveConstants.DRIVETRAIN_FRONT_RIGHT_DRIVE_MOTOR,
          CANSparkMaxLowLevel.MotorType.kBrushless),
          Mk2SwerveModuleBuilder.MotorType.NEO)
      .build();
   backLeftModule = new Mk2SwerveModuleBuilder(
      new Vector2(-TRACKWIDTH / 2.0, WHEELBASE / 2.0))
      .angleEncoder(new AnalogInput(DriveConstants.DRIVETRAIN_BACK_LEFT_ANGLE_ENCODER),
           -OFFSETS[2])
      .angleMotor(new CANSparkMax(DriveConstants.DRIVETRAIN_BACK_LEFT_ANGLE_MOTOR,
          CANSparkMaxLowLevel.MotorType.kBrushless),
          Mk2SwerveModuleBuilder.MotorType.NEO)
      .driveMotor(new CANSparkMax(DriveConstants.DRIVETRAIN_BACK_LEFT_DRIVE_MOTOR,
          CANSparkMaxLowLevel.MotorType.kBrushless),
          Mk2SwerveModuleBuilder.MotorType.NEO)
      .build();
  backRightModule = new Mk2SwerveModuleBuilder(
      new Vector2(-TRACKWIDTH / 2.0, -WHEELBASE / 2.0))
      .angleEncoder(new AnalogInput(DriveConstants.DRIVETRAIN_BACK_RIGHT_ANGLE_ENCODER),
          -OFFSETS[3])
      .angleMotor(new CANSparkMax(DriveConstants.DRIVETRAIN_BACK_RIGHT_ANGLE_MOTOR,
          CANSparkMaxLowLevel.MotorType.kBrushless),
          Mk2SwerveModuleBuilder.MotorType.NEO)
      .driveMotor(new CANSparkMax(DriveConstants.DRIVETRAIN_BACK_RIGHT_DRIVE_MOTOR,
          CANSparkMaxLowLevel.MotorType.kBrushless),
          Mk2SwerveModuleBuilder.MotorType.NEO)
      .build();
    frontLeftModule.setName("Front Left");
    frontRightModule.setName("Front Right");
    backLeftModule.setName("Back Left");
    backRightModule.setName("Back Right");

  
    // frontLeftModule.resetKinematics();
    // frontRightModule.resetKinematics();
    // backLeftModule.resetKinematics();
    // backRightModule.resetKinematics();
  }

  public static DrivetrainSubsystem getInstance() {
    if (instance == null) {
      instance = new DrivetrainSubsystem();
    }

    return instance;
  }

  @Override
  public void periodic() {
    frontLeftModule.updateSensors();
    frontRightModule.updateSensors();
    backLeftModule.updateSensors();
    backRightModule.updateSensors();
   //  SmartDashboard.putString("offsets", frontLeftModule.getCurrentAngle().toString + "," )
    SmartDashboard.putNumber("Front Left Module Angle", Math.toDegrees(frontLeftModule.getCurrentAngle()));
    SmartDashboard.putNumber("Front Right Module Angle",
        Math.toDegrees(frontRightModule.getCurrentAngle()));
    SmartDashboard.putNumber("Back Left Module Angle", Math.toDegrees(backLeftModule.getCurrentAngle()));
    SmartDashboard.putNumber("Back Right Module Angle", Math.toDegrees(backRightModule.getCurrentAngle()));
    SmartDashboard.putNumber("funniness", -Math.toRadians(0.0));
    SmartDashboard.putNumber("Gyroscope Angle", gyroscope.getAngle().toDegrees());
    SmartDashboard.putNumber("Gyroscope Raw degrees", gyroscope.getUnadjustedAngle().toDegrees());
    m_counter++;
    if (m_counter > 100) {
      SmartDashboard.putString("offsets", String.format("%.4f, %.4f, %.4f, %.4f",
          frontLeftModule.getCurrentAngle(),
          frontRightModule.getCurrentAngle(),
          backLeftModule.getCurrentAngle(),
          backRightModule.getCurrentAngle()));
      m_counter = 0;
    }
    frontLeftModule.updateState(TimedRobot.kDefaultPeriod);
    frontRightModule.updateState(TimedRobot.kDefaultPeriod);
    backLeftModule.updateState(TimedRobot.kDefaultPeriod);
    backRightModule.updateState(TimedRobot.kDefaultPeriod);
  }

  public void reset() {
  }

  public void drive(Translation2d translation, double rotation, boolean fieldOriented) {
    rotation *= 2.0 / Math.hypot(WHEELBASE, TRACKWIDTH);
    ChassisSpeeds speeds;
    if (fieldOriented) {
      speeds = ChassisSpeeds.fromFieldRelativeSpeeds(translation.getX(), translation.getY(), rotation,
          Rotation2d.fromDegrees(gyroscope.getAngle().toDegrees()));
    } else {
      speeds = new ChassisSpeeds(translation.getX(), translation.getY(), rotation);
    }

    SwerveModuleState[] states = kinematics.toSwerveModuleStates(speeds);
    frontLeftModule.setTargetVelocity(states[0].speedMetersPerSecond, states[0].angle.getRadians());
    frontRightModule.setTargetVelocity(states[1].speedMetersPerSecond, states[1].angle.getRadians());
    backLeftModule.setTargetVelocity(states[2].speedMetersPerSecond, states[2].angle.getRadians());
    backRightModule.setTargetVelocity(states[3].speedMetersPerSecond, states[3].angle.getRadians());
  }

  public void stickDrive(double forward, double strafe, double rotation) {
    // double forward = -RobotgetOI.getPrimaryJoystick().getRawAxis(1);
    // forward = Utilities.deadband(-forward * 0.85);
    // // // Square the forward stick
    // forward = Math.copySign(Math.pow(forward, 3.0), forward);

    // //double strafe = -Robot.getOi().getPrimaryJoystick().getRawAxis(0);
    // strafe = Utilities.deadband(-strafe * 0.85);
    // // Square the strafe stick
    // strafe = Math.copySign(Math.pow(strafe, 3.0), strafe);

    // //double rotation = -Robot.getOi().getPrimaryJoystick().getRawAxis(4);
    // rotation = Utilities.deadband(-rotation *0.85);
    // // Square the rotation stick
    // rotation = Math.copySign(Math.pow(rotation, 3.0), rotation);

    forward = Utilities.joystickCubicScaledDeadband(forward);
    strafe = Utilities.joystickCubicScaledDeadband(-strafe);
    rotation = Utilities.joystickCubicScaledDeadband(-rotation);

    drive(new Translation2d(forward, strafe), rotation, true);
    // drive(new Translation2d(forward, 0), 0, false);

  }

  public CommandBase resetGyroscope() {
    return new InstantCommand(() -> gyroscope.setAdjustmentAngle(gyroscope.getUnadjustedAngle()));
  }

}
