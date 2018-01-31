package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.RobotMap;
import org.usfirst.frc.team2353.robot.commands.CameraMoveLeftRight;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraLeftRight extends Subsystem {

	public static Servo LeftRight;
	
	public CameraLeftRight() {
		LeftRight = new Servo(RobotMap.servoLR);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CameraMoveLeftRight());
    }
}

