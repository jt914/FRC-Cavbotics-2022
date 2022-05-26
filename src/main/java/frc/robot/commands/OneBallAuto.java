package frc.robot.commands;

import java.lang.invoke.ConstantCallSite;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

import frc.robot.subsystems.*;

public class OneBallAuto extends CommandBase {
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

    public OneBallAuto(Limelight a, Hood h, InnerIndex i, Intake in, OuterIndex o, Shooter s, SwerveDrive swerve){
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
        addRequirements(autoaim, hood, innerIndex, intake, outerIndex, shooter, swerveDrive);
        swerveDrive.m_frontRightLocation.reset();
        swerveDrive.m_frontLeftLocation.reset();
        swerveDrive.m_backLeftLocation.reset();
        swerveDrive.m_backRightLocation.reset();
    }

    @Override
    public void initialize(){
        hood.hoodReset();
        intake.retract();
        //SmartDashboard.putNumber("Auto works", 1);
        //System.out.println("Wotks 2");
    }

    @Override
    public void execute(){
        
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("OneBallAuto").setBoolean(true);

        switch(step){

            //shoots

            case 0:
                if (!Constants.intakeStatus){
                    intake.extend();
                    Constants.intakeStatus = true;
                }
                intake.spinIntake();
                outerIndex.spin();

                if (Math.abs(swerveDrive.getDriveDistance()) > 44 ){
                    swerveDrive.stopAll();
                    intake.retract();
                    Constants.intakeStatus = false;
                    step = 1;
                    System.out.println("Drove 4 feet");
                } else{
                    swerveDrive.updatePeriodic(0.018, 0.4, 0);
                }
        
            break;

            case 1:
                if (Math.abs(swerveDrive.getGyroAngle()) < 153 ){
                    swerveDrive.updatePeriodic(0, 0, 0.8);
                } else{
                    intake.stopIntake();
                    swerveDrive.stopAll();
                    swerveDrive.resetDrive();
                    outerIndex.stop();
                    step = 2;
                }   
            break;

            case 2:   
                hood.setHoodAngle(35);
                shooter.set(4);
                if(shooter.getRPM() > 1900) {
                    outerIndex.spin();
                    innerIndex.spin();
                    swerveDrive.resetDrive();
                    step = 3;
                }        
            break;

            default:
            step = 3;
            break;
        }
    }

    @Override
    public void end(boolean interrupted){
        outerIndex.stop();
        innerIndex.stop();
        shooter.set(0);
        swerveDrive.resetDrive();
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("OneBallAuto").setBoolean(false);


    }

    @Override
    public boolean isFinished(){
        return (step >= 3);
    }

    
    
}
