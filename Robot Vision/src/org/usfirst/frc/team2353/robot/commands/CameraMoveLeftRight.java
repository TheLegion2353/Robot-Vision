package org.usfirst.frc.team2353.robot.commands;

import org.usfirst.frc.team2353.robot.OI;
import org.usfirst.frc.team2353.robot.Robot;
import org.usfirst.frc.team2353.robot.subsystems.CameraLeftRight;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CameraMoveLeftRight extends Command {
	
	Timer timer;
	
    public CameraMoveLeftRight() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.cameraLeftRight);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	timer = new Timer();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(OI.getXboxLeftXAxis() < 0) {
    		double targetPos = CameraLeftRight.LeftRight.get() + 0.05;
    		
    		if(targetPos <= 1) {
    			CameraLeftRight.LeftRight.set(targetPos);
        		
        		while(Math.abs(targetPos - CameraLeftRight.LeftRight.get()) > 0.025) {
        			timer.delay(5 / 1000);
        		}
    		}
    	}
    	else if(OI.getXboxLeftXAxis() > 0) {
    		double targetPos = CameraLeftRight.LeftRight.get() - 0.05;
    		
    		CameraLeftRight.LeftRight.set(targetPos);
    		
    		if(targetPos >= 0) {
    			CameraLeftRight.LeftRight.set(targetPos);
        		
        		while(Math.abs(targetPos - CameraLeftRight.LeftRight.get()) > 0.025) {
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
    	end();
    }
}
