// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.Limelight;

public class TurretAutoAim extends SubsystemBase {
  private Limelight limelight;
  private SwerveInput swerveInput;
  private Hood hood;
  private AimCalculator aimCalculator;

  private PIDController yawPIDController;

  /** Creates a new AutoAimHood. */
  public TurretAutoAim() {
    limelight = RobotContainer.limelight;
    swerveInput = RobotContainer.swerveInput;
    hood = RobotContainer.hood;
    aimCalculator = RobotContainer.aimCalculator;

    yawPIDController = new PIDController(1.5, 0, 0.8);
    yawPIDController.setTolerance(2, 1);

  }

  public void aimHood() {
    hood.setHoodAngle(aimCalculator.turretHoodAngle());
  }

  public void aimYaw() {
    swerveInput.SwerveYawInput(limelight.getXCrosshairOffset(), aimCalculator.turretYawAngle());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
