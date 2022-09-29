package frc.robot.commands;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Limelight;
import frc.robot.subsystems.SwerveDrive;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Hood;
import frc.robot.subsystems.InnerIndex;
import frc.robot.subsystems.OuterIndex;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ShootTesting extends CommandBase {
  private static SwerveDrive swerveDrive;
  private static XboxController remote;
  private static Shooter shooter;
  private static Hood hood;
  private static InnerIndex inner;
  private static OuterIndex outer;
  private static Limelight light;
  private double p = 0.004;
  private double i = 0;
  private double d = 0.00001;

  public ShootTesting() {
    light = RobotContainer.limelight;
    swerveDrive = RobotContainer.swerveDrive;
    remote = RobotContainer.swerveController;
    hood = RobotContainer.hood;
    inner = RobotContainer.innerIndex;
    this.shooter = RobotContainer.shooter;
    outer = RobotContainer.outerIndex;
    this.shooter = RobotContainer.shooter;
    addRequirements(swerveDrive);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
    // swerveDrive.setPID(p, i, d);
    double mode = NetworkTableInstance.getDefault().getTable("/datatable").getEntry("shooterMode").getDouble(0);
    // NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(0);

    shooter.set(3.5);
    hood.adjustAngle(15);

    double midStart = System.currentTimeMillis();
    System.out.println("loop 2");
    inner.spin();
    outer.spin();

    while (Math.abs(midStart - System.currentTimeMillis()) < 1500) {
      continue;
    }
    System.out.println("Finished loop 2");
    shooter.set(0);
    inner.stop();
    outer.stop();
    RobotContainer.status = 1;
  }

  @Override
  public void end(boolean interrupted) {
    // NetworkTableInstance.getDefault().getTable("/limelight-sam").getEntry("ledMode").setDouble(1);
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("SwerveCommand").setBoolean(false);
    // swerveDrive.stopAll();
    shooter.set(0);
    inner.stop();
    outer.stop();
  }

  @Override
  public boolean isFinished() {
    if (RobotContainer.status == 1) {
      RobotContainer.status = 0;
      return true;
    }
    return false;
  }
}
