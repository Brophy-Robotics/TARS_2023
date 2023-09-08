// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static final int[] mecanum_phoenix_ids = {
        8, 4, 6, 3
    }; // right front, right back, left back, left front

    public static final double normalize_angle(double degrees) {
        if (degrees < 0) return ((degrees - 180) % 360 + 180);
        return ((degrees + 180) % 360 - 180);
    }

    public static final double getCorrection(double error, double p, double e, double min_power, double max_power) {
        double correction = error * p;
        if ((Math.abs(correction) < max_power) && (error * p != 0)) {
            correction *= Math.pow(Math.abs(error * p) / max_power, e - 1);
        }
        if (Math.abs(correction) < min_power) return 0;
        return correction;
    }

    public static final double vectorToAngle(double x, double y) {
        if (y == 0) {
            if (x < 0) {
                return -90;
            } else {
                return 90;
            }
        }
        return Math.atan(x / y) * 180.0 / Math.PI + (y > 0 ? 0 : 180) * (x > 0 ? 1 : -1);
    }

    public static final double trigger_deadzone = 0.1;
    
    public static final double normalize_joystick(double val) {
        if (Math.abs(val) < trigger_deadzone) {
            return 0;
        } else if (val > 0) {
            return Math.pow((val - trigger_deadzone) / (1 - trigger_deadzone), 1.2);
        } else {
            return -Math.pow((val + trigger_deadzone) / (trigger_deadzone - 1), 1.2);
        }
    }

}
