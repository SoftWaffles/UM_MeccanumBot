// In src/main/java/frc/robot/Constants.java
package frc.robot;

public final class Constants {

    public static final class DriveConstants {
        // CAN IDs for the four Spark MAX motors
        // YOU MUST ADJUST THESE TO MATCH YOUR ROBOT'S CONFIGURATION
        public static final int kFrontLeftMotorCanId = 1;
        public static final int kBackLeftMotorCanId = 2;
        public static final int kFrontRightMotorCanId = 3;
        public static final int kBackRightMotorCanId = 4;

        // Motor Inversion Settings
        // Inversion must be correct for the robot to drive straight.
        // Typically, one side (e.g., the right) must be inverted relative to the other.
        public static final boolean kFrontLeftInverted = false;
        public static final boolean kBackLeftInverted = false;
        public static final boolean kFrontRightInverted = true; 
        public static final boolean kBackRightInverted = true;  
    }

    public static final class OIConstants {
        // Driver Controller Port
        public static final int kDriverControllerPort = 0;
    }
}