// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import edu.wpi.first.math.MathUtil;

public class SwerveInput extends SubsystemBase {
  private XboxController swerveController;
  private SwerveDrive swerveDrive;
  private PIDController forwardPIDController;
  private PIDController strafePIDController;
  private PIDController yawPIDController;

  private int controllerSwitcher;

  /** Creates a new SwerveInput. */
  public SwerveInput() {
    swerveController = RobotContainer.swerveController;
    swerveDrive = RobotContainer.swerveDrive;

    /* 0: Taranis QX7, 1: Xbox */
    controllerSwitcher = 0;

    forwardPIDController = new PIDController(0, 0, 0);
    strafePIDController = new PIDController(0, 0, 0);
    yawPIDController = new PIDController(0, 0, 0);
  }

  public void SwerveJoystick() {
    if (controllerSwitcher == 0) {
      /* Taranis QX7 Controller Settings */
      if (Math.abs(swerveController.getRawAxis(0)) > 0.05 || Math.abs(swerveController.getRawAxis(1)) > 0.05 || Math.abs(swerveController.getRawAxis(2)) > 0.05) {
        swerveDrive.updatePeriodic(swerveController.getRawAxis(0) * Constants.maxVelocityMultiplier, swerveController.getRawAxis(1) * Constants.maxVelocityMultiplier, swerveController.getRawAxis(2) * Constants.radiansPerSecondMultiplier);
      }
    }
    else if (controllerSwitcher == 1) {
      /* Xbox Controller Settings */
      if (Math.abs(swerveController.getRightY()) > 0.05 || Math.abs(swerveController.getRightX()) > 0.05 || Math.abs(swerveController.getLeftX()) > 0.05) {
        swerveDrive.updatePeriodic(swerveController.getRightY() * Constants.maxVelocityMultiplier, swerveController.getRightX() * Constants.maxVelocityMultiplier, swerveController.getLeftX() * Constants.radiansPerSecondMultiplier);
      }
    }
    else {
      controllerSwitcher = 0;
    }
  }

  public void SwerveYawInput(double yawMeasurement, double yawSetpoint) {
    double yawPID = MathUtil.clamp(yawPIDController.calculate(yawMeasurement, yawSetpoint), -1, 1);
    if (Math.abs(swerveController.getRawAxis(0)) > 0.05 || Math.abs(swerveController.getRawAxis(1)) > 0.05) {
      SmartDashboard.putNumber("Swerve Input Yaw", yawPID);
      swerveDrive.updatePeriodic(swerveController.getRawAxis(0) * Constants.maxVelocityMultiplier, swerveController.getRawAxis(1) * Constants.maxVelocityMultiplier, yawPID * Constants.radiansPerSecondMultiplier);
    }
  }

  public void SwerveAllInput(double forwardMeasurement, double forwardSetpoint, double strafeMeasurement, double strafeSetpoint, double yawMeasurement, double yawSetpoint) {
    double forwardPID = MathUtil.clamp(forwardPIDController.calculate(forwardMeasurement, forwardSetpoint), -1, 1);
    double strafePID = MathUtil.clamp(strafePIDController.calculate(strafeMeasurement, strafeSetpoint), -1, 1);
    double yawPID = MathUtil.clamp(yawPIDController.calculate(yawMeasurement, yawSetpoint), -1, 1);

    swerveDrive.updatePeriodic(forwardPID * Constants.maxVelocityMultiplier, strafePID * Constants.maxVelocityMultiplier, yawPID * Constants.radiansPerSecondMultiplier);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
