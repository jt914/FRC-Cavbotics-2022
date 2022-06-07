// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.SwerveDrive;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.util.net.PortForwarder;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the
 * name of this class or
 * the package after creating this project, you must also update the
 * build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command m_autonomousCommand;

  private RobotContainer m_robotContainer;

  private static SwerveDrive swerveDrive;

  private static XboxController remote;
  private Limelight light;

  /**
   * This function is run when the robot is first started up and should be used
   * for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
    RobotContainer.swerveDrive.gyro.reset();
    light = RobotContainer.limelight;

    // visit 172.22.11.2:5801
    PortForwarder.add(5800, "limelight.local", 5800);
    PortForwarder.add(5801, "limelight.local", 5801);
    PortForwarder.add(5802, "limelight.local", 5802);
    PortForwarder.add(5803, "limelight.local", 5803);
    PortForwarder.add(5804, "limelight.local", 5804);
    PortForwarder.add(5805, "limelight.local", 5805);
    // swerveDrive.gyro.setAngleAdjustment(90);
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    // intake = new Intake();
    // shooter = new Shooter();
    // innerIndex = new InnerIndex();
    // outerIndex = new OuterIndex();
    // climber = new Climber();
    // hood = new Hood();

    // // autoAim = new AutoAim();
    // swerveDrive = new SwerveDrive(1.5);
    // controller = new XboxController(0);
    // swerveController = new XboxController(1);
    // m_RobotContainer = new RobotContainer();

    // CameraServer.startAutomaticCapture();
    // NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);

    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Killswitch").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto1").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto2").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("DeclineHoodCommand").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("DoNothingCommand").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("ExtendClimberCommand").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("HoodCommand").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("InnerIndexCommand").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("IntakeCommand").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("KickOutBallsCommand").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("OneBallAuto").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("OuterIndexCommand").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("RaiseHoodCommand").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("RetractClimberCode").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("ShootCommand").setBoolean(false);
    // NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("SwerveCommand").setBoolean(false);
    // NetworkTableInstance.getDefault().getTable("/datatable").getEntry("routine").setDouble(1);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("shooterMode").setDouble(0);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("batteryVoltage")
        .setDouble(RobotController.getBatteryVoltage());
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("robotMode").setBoolean(false);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("distance").setDouble(0);

    SmartDashboard.putNumber("Distance", RobotContainer.limelight.getXDistance());
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items
   * like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("batteryVoltage")
        .setDouble(RobotController.getBatteryVoltage());
    // System.out.println(RobotContainer.swerveDrive.getGyroAngle());
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {
  }

  @Override
  public void disabledPeriodic() {
  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    RobotContainer.swerveDrive.gyro.reset();
    System.out.println("swerve reset ran in Robot");

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
  }

  @Override
  public void teleopInit() {

    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("robotMode").setBoolean(true);
    swerveDrive = RobotContainer.swerveDrive;
    remote = RobotContainer.swerveController;
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("distance").setDouble(light.getXDistance());

    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {
  }

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {
  //can test whether code or electrical through here
  //put same init methods 
  
  }
  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {
  }
}
