package frc.robot.subsystems;


import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class OuterIndex extends SubsystemBase{

    private CANSparkMax outerIndex;

    public OuterIndex(){
        outerIndex = new CANSparkMax(Constants.outerIndexId, MotorType.kBrushless);
        outerIndex.enableVoltageCompensation(12);
    }

    public void spin(){
        outerIndex.set(-0.45);
    }

    public void reverse(){
        outerIndex.set(0.35);
    }

    public void stop(){
        outerIndex.setVoltage(0);
    }

    
}
