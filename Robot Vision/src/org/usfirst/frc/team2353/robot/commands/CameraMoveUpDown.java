package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.OI;
import org.usfirst.frc.team2353.robot.Robot;
import org.usfirst.frc.team2353.robot.subsystems.CameraUpDown;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CameraMoveUpDown extends Command {
	
	Timer timer;
	
    public CameraMoveUpDown() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.cameraUpDown);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.getXboxLeftYAxis() < 0) {
    		double targetPos = CameraUpDown.UpDown.get() + 0.05;
    		
    		if(targetPos <= 1) {
    			CameraUpDown.UpDown.set(targetPos);
        		
        		while(Math.abs(targetPos - CameraUpDown.UpDown.get()) > 0.025) {
        			timer.delay(5 / 1000);
        		}
    		}
    	}
    	else if(OI.getXboxLeftYAxis() > 0) {
    		double targetPos = CameraUpDown.UpDown.get() - 0.05;
    		
    		CameraUpDown.UpDown.set(targetPos);
    		
    		if(targetPos >= 0) {
    			CameraUpDown.UpDown.set(targetPos);
        		
        		while(Math.abs(targetPos - CameraUpDown.UpDown.get()) > 0.025) {
        			timer.delay(5 / 1000);
        		}
    		}
    	}
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
