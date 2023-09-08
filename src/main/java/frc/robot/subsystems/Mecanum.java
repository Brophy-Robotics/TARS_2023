package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADIS16448_IMU;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import static frc.robot.Constants.*;

public class Mecanum extends SubsystemBase {

    private TalonSRX[] mecanum_motors = new TalonSRX[4];
    // private ADIS16448_IMU gyro;

    public Mecanum() {
        mecanum_motors[0] = new TalonSRX(mecanum_phoenix_ids[0]); // right front
        mecanum_motors[1] = new TalonSRX(mecanum_phoenix_ids[1]); // right back
        mecanum_motors[2] = new TalonSRX(mecanum_phoenix_ids[2]); // left back
        mecanum_motors[3] = new TalonSRX(mecanum_phoenix_ids[3]); // left front
        // gyro = new ADIS16448_IMU();

        Timer.delay(1.0);

        mecanum_motors[0].setInverted(false);
        mecanum_motors[1].setInverted(true);
        mecanum_motors[2].setInverted(true);
        mecanum_motors[3].setInverted(true);

        for (int i = 0; i < 4; i++) {
            mecanum_motors[i].setSelectedSensorPosition(0); // I think 4096 counts per revolution but I'm not sure
            // idrc
        }
    }

    public double getAngle() { // positive --> clockwise
        return 0;
    }

    public void drive(double strafe, double forward, double turn) {

        /* Calculations */

        double offset = vectorToAngle(strafe, forward);
        double distance = Math.sqrt(strafe * strafe + forward * forward);

        if (Math.abs(distance) < 0.1) distance = 0;
        if (Math.abs(turn) < 0.1) turn = 0;

        double[] power = new double[4];
        for (int i = 0; i < 4; i++) {
            power[i] = turn * ((i > 1) ? 1 : -1) + distance * (Math.cos(offset * Math.PI / 180.0) + Math.sin(offset * Math.PI / 180.0) * (i % 2 == 1 ? 1 : -1));
        }
        double maximum = Math.max(1, Math.max(Math.max(Math.abs(power[0]), Math.abs(power[1])), Math.max(Math.abs(power[2]), Math.abs(power[3]))));
        for (int i = 0; i < 4; i++) {
            mecanum_motors[i].set(TalonSRXControlMode.PercentOutput, power[i] / maximum);
        }
    }
    
}
