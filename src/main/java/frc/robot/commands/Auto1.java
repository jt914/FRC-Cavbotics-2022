package frc.robot.commands;

import java.lang.invoke.ConstantCallSite;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

import frc.robot.subsystems.*;

public class Auto1 extends CommandBase {
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

    public Auto1(Limelight a, Hood h, InnerIndex i, Intake in, OuterIndex o, Shooter s, SwerveDrive swerve){
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
        
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto1").setBoolean(true);

        
        switch(step){
            
            case 0:
            hood.setHoodAngle(27);
                shooter.set(3);
                double startTime = System.currentTimeMillis();
                while (Math.abs(startTime - System.currentTimeMillis()) < 3000){
                    continue;
                }
                outerIndex.spin();
                innerIndex.spin();
                startTime = System.currentTimeMillis();
                while (Math.abs(startTime - System.currentTimeMillis()) < 1500){
                    continue;
                }
                outerIndex.stop();
                innerIndex.stop();
                shooter.set(0);
                step = 9;
            break;        

            default:
            step = 9;
            break;
        }
    }

    @Override
    public void end(boolean interrupted){
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto1").setBoolean(false);
        shooter.set(0);
        // outerIndex.stop();
        // innerIndex.stop();

    }

    @Override
    public boolean isFinished(){
        return (step >= 9);
    }

    
    
}