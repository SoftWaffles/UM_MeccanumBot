// In src/main/java/frc/robot/Configs.java
package frc.robot;

import com.revrobotics.spark.config.ClosedLoopConfig.FeedbackSensor;
import com.revrobotics.spark.config.SparkMaxConfig;

/**
 * The Configs class provides static configuration objects for all Spark MAX/Flex
 * motor controllers on the robot. This allows the configuration to be done once
 * and applied easily to the motor controllers.
 */
public final class Configs {

    /**
     * Configuration for a single Spark MAX driving a standard motor.
     * This is an open-loop configuration for basic speed control.
     */
    public static final class MecanumDrive {
        // We only need one config object since all four drive motors have the same settings.
        public static final SparkMaxConfig driveMotorConfig = new SparkMaxConfig();

        static {
            // Apply basic settings common to all four drive motors
            double drivingFactor = 104E-3 * Math.PI / 30.0;
            driveMotorConfig
                .encoder
                    .positionConversionFactor(drivingFactor) // meters
                    .velocityConversionFactor(drivingFactor / 60.0); // meters per second
            driveMotorConfig
                .closedLoop
                    .feedbackSensor(FeedbackSensor.kPrimaryEncoder)
                    // These are example gains you may need to adjust them for your own robot!
                    .pid(0.04, 0, 0)
                    .outputRange(-1, 1);
            // You can add more configurations here if needed, 
            // but for simple drive, IdleMode and CurrentLimit are often sufficient.
        }
    }

    // You would add other subsystem configurations (e.g., Shooter, Climber) here
    // using similar static inner classes.

}