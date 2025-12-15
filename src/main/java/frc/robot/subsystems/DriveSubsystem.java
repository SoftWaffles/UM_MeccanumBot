// In src/main/java/frc/robot/subsystems/DriveSubsystem.java
package frc.robot.subsystems;

// Use the SparkMax class and MotorType from the new 'spark' package
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkBase.PersistMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Configs;
import frc.robot.Constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    
    // Motor Controllers declared using the "MAX" naming convention
    // Using SparkMax as it is the base class for CANSparkMax in the new library
    private final SparkMax m_frontLeftMax;
    private final SparkMax m_backLeftMax;
    private final SparkMax m_frontRightMax;
    private final SparkMax m_backRightMax;

    /** Creates a new DriveSubsystem. */
    public DriveSubsystem() {
        // Initialize and configure all four Spark MAX motors
        m_frontLeftMax = initializeSparkMax(
            DriveConstants.kFrontLeftMotorCanId, 
            DriveConstants.kFrontLeftInverted
        );
        m_backLeftMax = initializeSparkMax(
            DriveConstants.kBackLeftMotorCanId, 
            DriveConstants.kBackLeftInverted
        );
        m_frontRightMax = initializeSparkMax(
            DriveConstants.kFrontRightMotorCanId, 
            DriveConstants.kFrontRightInverted
        );
        m_backRightMax = initializeSparkMax(
            DriveConstants.kBackRightMotorCanId, 
            DriveConstants.kBackRightInverted
        );
    }

    /**
     * Helper method to initialize and configure a Spark MAX motor.
     */
    private SparkMax initializeSparkMax(int canId, boolean inverted) {
        // Use SparkMax for NEO/SparkMAX configuration
        SparkMax sparkMax = new SparkMax(canId, MotorType.kBrushless);

        // Apply basic configuration
        sparkMax.configure(
        Configs.MecanumDrive.driveMotorConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
        
        return sparkMax;
    }


    /**
     * Drives the robot using Mecanum kinematics based on controller inputs.
     * @param y Forward/Backward speed [-1.0, 1.0]. 
     * @param x Strafe speed (left/right) [-1.0, 1.0].
     * @param rx Rotation speed (turn) [-1.0, 1.0].
     */
    public void driveMecanum(double y, double x, double rx) {
        x = x * 1.1; // Counteract imperfect strafing
        // Denominator ensures no motor power exceeds 1.0
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1.0);
        
        double frontLeftPower = (y + x + rx) / denominator;
        double backLeftPower = (y - x + rx) / denominator;
        double frontRightPower = (y - x - rx) / denominator;
        double backRightPower = (y + x - rx) / denominator;

        // Set the motor powers
        m_frontLeftMax.set(frontLeftPower);
        m_backLeftMax.set(backLeftPower);
        m_frontRightMax.set(frontRightPower);
        m_backRightMax.set(backRightPower);
    }

    /** Stops the drive motors. */
    public void stop() {
        m_frontLeftMax.set(0);
        m_backLeftMax.set(0);
        m_frontRightMax.set(0);
        m_backRightMax.set(0);
    }
}