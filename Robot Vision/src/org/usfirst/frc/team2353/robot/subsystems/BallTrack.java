package org.usfirst.frc.team2353.robot.subsystems;

import org.usfirst.frc.team2353.robot.RobotMap;
import org.usfirst.frc.team2353.robot.commands.BallTrackMove;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class BallTrack extends Subsystem {

	public static Servo LeftRight;
	public static Servo UpDown;
	
	public BallTrack() {
		LeftRight = new Servo(RobotMap.servoLR);
		UpDown = new Servo(RobotMap.servoUPDOWN);
	}

    public void initDefaultCommand() {
        setDefaultCommand(new BallTrackMove());
    }
}

