package frc.robot.commands;

import java.lang.invoke.ConstantCallSite;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

import frc.robot.subsystems.*;

public class Auto5 extends CommandBase {

    private Limelight autoaim;
    private Hood hood;
    private InnerIndex innerIndex;
    private Intake intake;
    private OuterIndex outerIndex;
    private Shooter shooter;
    private SwerveDrive swerveDrive;
    private Timer timer;
    private double power;
    private int step;
    private double startTime;

    public Auto5(Limelight a, Hood h, InnerIndex i, Intake in, OuterIndex o, Shooter s, SwerveDrive swerve) {
        autoaim = a;
        hood = h;
        innerIndex = i;
        intake = in;
        outerIndex = o;
        shooter = s;
        swerveDrive = swerve;
        timer = new Timer();
        power = 0;
        step = 0;
        startTime = 0;
        addRequirements(autoaim, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
        swerveDrive.m_frontRightLocation.reset();
        swerveDrive.m_frontLeftLocation.reset();
        swerveDrive.m_backLeftLocation.reset();
        swerveDrive.m_backRightLocation.reset();
    }

    @Override
    public void initialize() {
        hood.hoodReset();
        intake.extend();
        swerveDrive.gyro.reset();
    }

    @Override
    public void execute() {

        // fix this
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto2").setBoolean(true);

        switch (step) {
            case 0:
                hood.setHoodAngle(33);
                shooter.set(3.67);
                if (shooter.getRPM() > 1800) {
                    startTime = System.currentTimeMillis();
                    outerIndex.spin();
                    innerIndex.spin();
                    swerveDrive.resetDrive();
                    step = 1;
                }
                System.out.println("step " + step + " done");
                break;

            case 1:
                while (Math.abs(startTime - System.currentTimeMillis()) < 1500) {
                    continue;
                }
                shooter.set(0);
                outerIndex.stop();
                innerIndex.stop();
                step = 2;
                System.out.println("step " + step + " done");

                break;
            case 2:
                if (Math.abs(swerveDrive.getGyroAngle()) < 34) {
                    swerveDrive.updatePeriodic(0, 0, 0.15);
                } else {
                    swerveDrive.stopAll();
                    swerveDrive.resetDrive();
                    step = 3;
                }
                System.out.println("step " + step + " done");

                break;
            case 3:
                if (!Constants.intakeStatus) {
                    intake.extend();
                    Constants.intakeStatus = true;
                }

                intake.spinIntake();
                outerIndex.spin();

                if (Math.abs(swerveDrive.getDriveDistance()) > 100) {
                    swerveDrive.stopAll();
                    intake.retract();
                    Constants.intakeStatus = false;
                    step = 4;
                } else {
                    swerveDrive.updatePeriodic(-0.014, 0.4, 0);

                }

                System.out.println("step " + step + " done");
                break;
            case 4:
                if (Math.abs(swerveDrive.getGyroAngle()) < 70) {
                    swerveDrive.updatePeriodic(0, 0, 0.15);
                } else {
                    swerveDrive.stopAll();
                    swerveDrive.resetDrive();
                    step = 5;
                }
                System.out.println("step " + step + " done");

                break;
            case 5:
                hood.setHoodAngle(33);
                shooter.set(3.67);
                if (shooter.getRPM() > 1800) {
                    startTime = System.currentTimeMillis();
                    outerIndex.spin();
                    innerIndex.spin();
                    swerveDrive.resetDrive();
                    step = 6;
                }
                System.out.println("step " + step + " done");
                break;

            // limelight target get
            // translate y reverse until reaches {x} distance
            // do a 180
            // wait for certain time for balls to be fed
            // turn 180
            // drive forward {y} distance
            // shoot

        }

    }

    @Override
    public void end(boolean interrupted) {
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto2").setBoolean(false);
        shooter.set(0);

    }

    @Override
    public boolean isFinished() {
        // return (step >= 9);
        return step >= 10;
    }

}