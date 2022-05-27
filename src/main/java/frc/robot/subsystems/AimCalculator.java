// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Gyro;
import frc.robot.subsystems.Limelight;

public class AimCalculator extends SubsystemBase {
  public Gyro gyro;
  public Limelight limelight;
  
  /** Creates a new MovingAimCalculator. */
  public AimCalculator() {
    gyro = RobotContainer.gyro;
    limelight = RobotContainer.limelight;

  }   

  public double turretYawAngle() {
    double yawAngle = Math.atan2(limelight.getTargetDistance() + gyro.getHubRelativeVelocityY(), gyro.getHubRelativeVelocityX());
    SmartDashboard.putNumber("Turret Yaw Angle", yawAngle);
    return yawAngle;
  }

  public double turretHoodAngle() {
    double movingHubDistance = Math.sqrt(Math.pow(limelight.getTargetDistance() + gyro.getHubRelativeVelocityY(), 2) + Math.pow(gyro.getHubRelativeVelocityX(), 2));
    double movingHoodAngle = movingHubDistance * Constants.distanceToHoodAngle;
    SmartDashboard.putNumber("Turret Hub Distance", movingHubDistance);
    SmartDashboard.putNumber("Turret Hood Angle", movingHoodAngle);
    return movingHoodAngle;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
