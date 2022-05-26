package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class InnerIndex extends SubsystemBase {

    private CANSparkMax innerIndex;

    public InnerIndex() {
        innerIndex = new CANSparkMax(Constants.innerIndexId, MotorType.kBrushless);
        innerIndex.enableVoltageCompensation(12);

    }

    public void spin() {
        innerIndex.set(-0.30);
    }

    public void reverse(){
        innerIndex.set(0.30);
    }

    public void stop() {
        innerIndex.set(0);
    }

}
