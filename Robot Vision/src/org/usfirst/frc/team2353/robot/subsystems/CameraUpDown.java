package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.RobotMap;
import org.usfirst.frc.team2353.robot.commands.CameraMoveUpDown;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class CameraUpDown extends Subsystem {
	
	public static Servo UpDown;
	
	public CameraUpDown() {
		UpDown = new Servo(RobotMap.servoUPDOWN);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new CameraMoveUpDown());
    }
}

