package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveDriveKinematics;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.kauailabs.navx.frc.*;

import com.revrobotics.*;

public class SwerveDrive extends SubsystemBase{

  private SwerveModuleState[] moduleState;
  private ChassisSpeeds speeds;
  private SwerveDriveKinematics kinematics;
  public SwerveModule m_frontRightLocation;
  public SwerveModule m_frontLeftLocation;
  public SwerveModule m_backLeftLocation;
  public SwerveModule m_backRightLocation;
  private final double MAX_SPEED;
  private final double MAX_RADIANS;
  public AHRS gyro;
  


  public SwerveDrive(double distanceFromOrigin) {

    // (Y,X) format
    Translation2d frontRightLocation = new Translation2d(distanceFromOrigin, distanceFromOrigin);
    Translation2d frontLeftLocation = new Translation2d(-distanceFromOrigin, distanceFromOrigin);
    Translation2d backLeftLocation = new Translation2d(-distanceFromOrigin, -distanceFromOrigin);
    Translation2d backRightLocation = new Translation2d(distanceFromOrigin, -distanceFromOrigin);

    kinematics = new SwerveDriveKinematics(frontRightLocation, frontLeftLocation, backLeftLocation, backRightLocation);

    MAX_SPEED = 2;//4
    MAX_RADIANS = 2;//5

    moduleState = new SwerveModuleState[4];
    m_frontRightLocation = new SwerveModule(1, 2,
        0);
    m_frontLeftLocation = new SwerveModule(3, 4,
        1);
    //m_backLeftLocation = new SwerveModule(Constants.backLeftTurn, Constants.backLeftDrive, Constants.backLeftEncoder);
    m_backLeftLocation = new SwerveModule(5, 6,
        2);
    m_backRightLocation = new SwerveModule(7, 8,
        3);
    
    m_frontRightLocation.reset();
    m_frontLeftLocation.reset();
    m_backLeftLocation.reset();
    m_backRightLocation.reset();

    try {
      gyro = new AHRS(SPI.Port.kMXP); 
      gyro.reset();
    } catch (RuntimeException ex ) {
        System.out.println("--------------");
        System.out.println("NavX not plugged in");
        System.out.println("--------------");
    }

  }

  public void updatePeriodic(double translateY, double translateX, double yaw) {

    // speeds = ChassisSpeeds.fromFieldRelativeSpeeds(translateY * MAX_SPEED, translateX * MAX_SPEED * -1,
    //     yaw * MAX_RADIANS, new Rotation2d(Math.toRadians(getGyroAngle())));
    speeds = new ChassisSpeeds(translateY * MAX_SPEED, translateX *
    MAX_SPEED * -1, yaw * MAX_RADIANS);

    moduleState = kinematics.toSwerveModuleStates(speeds);

    var optimized1 = SwerveModuleState.optimize(moduleState[0], m_frontRightLocation.currentAngle);
    var optimized2 = SwerveModuleState.optimize(moduleState[1], m_frontLeftLocation.currentAngle);
    var optimized3 = SwerveModuleState.optimize(moduleState[2], m_backLeftLocation.currentAngle);
    var optimized4 = SwerveModuleState.optimize(moduleState[3], m_backRightLocation.currentAngle);


    m_frontRightLocation.setModule(optimized1.angle, optimized1.speedMetersPerSecond);
    m_frontLeftLocation.setModule(optimized2.angle, optimized2.speedMetersPerSecond);
    m_backLeftLocation.setModule(optimized3.angle, optimized3.speedMetersPerSecond);
    m_backRightLocation.setModule(optimized4.angle, optimized4.speedMetersPerSecond);

    // m_frontRightLocation.setModule(moduleState[0].angle, moduleState[0].speedMetersPerSecond);
    // m_frontLeftLocation.setModule(moduleState[1].angle, moduleState[1].speedMetersPerSecond);
    // m_backLeftLocation.setModule(moduleState[2].angle, moduleState[2].speedMetersPerSecond);
    // m_backRightLocation.setModule(optimized4.angle, optimized4.speedMetersPerSecond);

    

  }

  public double getDriveDistance(){
    return m_frontRightLocation.getDrive();
  }

  public void resetDrive(){
    m_frontRightLocation.driveRest();
    m_frontLeftLocation.driveRest();
    m_backLeftLocation.driveRest();
    m_backRightLocation.driveRest();
  }

  public double getGyroAngle(){
    double angle = gyro.getAngle();
    return (angle >360) ? angle % 360 : angle;
  }

  public void stopAll(){
    m_frontRightLocation.stop();
    m_frontLeftLocation.stop();
    m_backLeftLocation.stop();
    m_backRightLocation.stop();
  }

  public void setPID(double p, double i, double d){
    m_frontLeftLocation.setPID(p, i, d);
    m_frontRightLocation.setPID(p, i, d);
    m_backLeftLocation.setPID(p, i, d);
    m_backRightLocation.setPID(p, i, d);
  }
}
