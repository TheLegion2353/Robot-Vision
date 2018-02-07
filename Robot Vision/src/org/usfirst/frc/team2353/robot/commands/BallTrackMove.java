package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.ArduinoRead;
import org.usfirst.frc.team2353.robot.Robot;
import org.usfirst.frc.team2353.robot.subsystems.CameraLeftRight;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BallTrackMove extends Command {
	
	Timer timer;
	ArduinoRead arduino;
	
	final int maxPixyRightX = 319, maxPixyLeftX = 0;
	
    public BallTrackMove() {
        requires(Robot.ballTrack);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	int x = arduino.getPixyXSerial();
    	x = (x + maxPixyLeftX) / maxPixyRightX;
    	
    	CameraLeftRight.LeftRight.set(x);
    	
    	timer.delay(0.01);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
