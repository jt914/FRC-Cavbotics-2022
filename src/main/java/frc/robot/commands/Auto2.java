package frc.robot.commands;
import java.lang.invoke.ConstantCallSite;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;

import frc.robot.subsystems.*;

public class Auto2 extends CommandBase {

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

    public Auto2(Limelight a, Hood h, InnerIndex i, Intake in, OuterIndex o, Shooter s, SwerveDrive swerve){
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
    public void initialize(){
        hood.hoodReset();
        intake.extend();
        swerveDrive.gyro.reset();
    }

    @Override
    public void execute(){
        
        NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto2").setBoolean(true);


        switch(step){
            case 0:
                if (!Constants.intakeStatus){
                    intake.extend();
                    Constants.intakeStatus = true;
                }
               
                intake.spinIntake();
                outerIndex.spin();

                if (Math.abs(swerveDrive.getDriveDistance()) > 100 ){
                    swerveDrive.stopAll();
                    intake.retract();
                    Constants.intakeStatus = false;
                    step = 1;
                    System.out.println("Drove 4 feet");
                } else{
                    System.out.println(Math.abs(swerveDrive.getDriveDistance()));
                    swerveDrive.updatePeriodic(-0.014, 0.4, 0);
                    
                }
        
            break;

            case 1:
            //134
                if (Math.abs(swerveDrive.getGyroAngle()) < 151 ){
                    swerveDrive.updatePeriodic(0, 0, 0.15);
                } else{
                    intake.stopIntake();
                    swerveDrive.stopAll();
                    outerIndex.stop();
                    step = 2;
                }   
            break;

            case 2:   
                hood.setHoodAngle(33);
                shooter.set(3.67);
                if(shooter.getRPM() > 1800) {
                    startTime = System.currentTimeMillis();
                    outerIndex.spin();
                    innerIndex.spin();
                    swerveDrive.resetDrive();
                    step = 3;
                }        
            break;

         
            case 3:
                    //swerveDrive.updatePeriodic(0, 0, 0);
                    while (Math.abs(startTime - System.currentTimeMillis()) < 1500){
                        continue;
                    }
                    shooter.set(0);
                    outerIndex.stop();
                    innerIndex.stop();
                    step = 9;
               
            break;

            case 4:
                if (Math.abs(swerveDrive.getDriveDistance()) < 5){
                    swerveDrive.updatePeriodic(0, 0.08, 0);
                } else{
                    swerveDrive.stopAll();
                    hood.setHoodAngle(29);
                    shooter.set(5);
                    if(shooter.getRPM() > 2100) {
                        outerIndex.spin();
                        innerIndex.spin();
                        shooter.set(0);
                        step = 5;
                    }
                }
            break;

            case 5:
                // outerIndex.stop();
                intake.stopIntake();
                // innerIndex.stop();
                step = 9;
            break;

            //turn around again
            case 6:
                
            break;

            //autoaim again 

            case 7:
                
            break;


            //shoot again

            case 8:

            break;

            default:
            step = 9;
            break;
        }
    }

    @Override
    public void end(boolean interrupted){
    NetworkTableInstance.getDefault().getTable("/datatable").getEntry("Auto2").setBoolean(false);
    shooter.set(0);

    }

    @Override
    public boolean isFinished(){
        //return (step >= 9);
        return step >= 10;
    }

    
    
}