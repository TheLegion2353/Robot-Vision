package org.usfirst.frc.team2353.robot;

import java.util.Timer;
import java.util.TimerTask;

import edu.wpi.first.wpilibj.SerialPort;

public class ArduinoRead {
	static SerialPort serialPort;
	int BaudRate = 9600;
	int databits = 8;
	
	int distance = -1;
	int pixyX = -1;
	
	//Initialize the serial port. To connect the Arduino, just plug the USB into the RoboRio USB port. Make sure the Arduino is on the same baud rate. 
	public ArduinoRead() {
		serialPort = new SerialPort(BaudRate, SerialPort.Port.kUSB, databits);
	}
	
	public int getUltrasonicSerial() {
		serialPort.writeString("1");

		Timer timer2 = new Timer();

		TimerTask task = new TimerTask() {
			public void run() {
				if(serialPort.getBytesReceived() != 0) {
					distance = UltrasonicSerialRead();
					timer2.cancel();
				}
			}
		};

		timer2.schedule(task, 50);
		
		serialPort.flush();
		
		return distance;
	}

	private int UltrasonicSerialRead() {
		distance = -1;
		
		int startIndex = -1;
		int endIndex = -1;

		String in = "";
		
		in = serialPort.readString();

		startIndex = in.indexOf('.');
		endIndex = 0;

		for (int i = startIndex + 1; i < in.length(); i++) {
			if (in.charAt(i) == '.') {
				endIndex = i;
				break;
			}
		}

		// System.out.println(in + " " + startIndex + " " + endIndex);

		if (startIndex != endIndex && startIndex != -1 && startIndex < endIndex && (startIndex + 1) != endIndex) {
			in = in.substring(startIndex + 1, endIndex);

			distance = Integer.parseInt(in);
		} else {
			distance = -1;
		}
		
		return distance;
	}
	
	public int getPixyXSerial() {
		serialPort.writeString("2");

		Timer timer2 = new Timer();

		TimerTask task = new TimerTask() {
			public void run() {
				if(serialPort.getBytesReceived() != 0) {
					pixyX = PixyXSerialRead();
					timer2.cancel();
				}
			}
		};

		timer2.schedule(task, 50);
		
		serialPort.flush();
		
		return pixyX;
	}

	private int PixyXSerialRead() {
		pixyX = -1;
		
		int startIndex = -1;
		int endIndex = -1;

		String in = "";
		
		in = serialPort.readString();

		startIndex = in.indexOf('/');
		endIndex = 0;

		for (int i = startIndex + 1; i < in.length(); i++) {
			if (in.charAt(i) == '/') {
				endIndex = i;
				break;
			}
		}

		// System.out.println(in + " " + startIndex + " " + endIndex);

		if (startIndex != endIndex && startIndex != -1 && startIndex < endIndex && (startIndex + 1) != endIndex) {
			in = in.substring(startIndex + 1, endIndex);

			pixyX = Integer.parseInt(in);
		} else {
			pixyX = -1;
		}
		
		return pixyX;
	}
}
