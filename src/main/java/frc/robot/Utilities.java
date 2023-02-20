package frc.robot;

public class Utilities {
    public static double deadband(double input) {
        return deadband(input, .1);
    }

    public static double deadband(double input, double buffer) {
        if (Math.abs(input) < buffer) return 0;
        return input;
    }

    public static double Cubic(double input, double weight) {
        return weight * Math.pow(input, 3.0) + (1.0 - weight) * input;
    }

    public static double joystickCubicScaledDeadband(double input) {
        double deadbandCutoff = 0.1;
        double weight = 0.2;
         
        if(Math.abs(input) < deadbandCutoff) {
            return 0.0;
        } else {
            return (Cubic(input, weight) - (Math.abs(input)/input) * Cubic(deadbandCutoff, weight)) / (1.0 - Cubic(deadbandCutoff, weight));
        }
    }
}