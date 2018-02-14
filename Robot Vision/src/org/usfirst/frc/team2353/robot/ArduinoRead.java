package org.usfirst.frc.team2353.robot;

import edu.wpi.first.wpilibj.SerialPort;
import edu.wpi.first.wpilibj.Timer;

public class ArduinoRead {
	static SerialPort serialPort;
	int BaudRate = 9600;
	int databits = 8;
	
	int pixyY = -1;
	int pixyX = -1;
	
	Timer timer;
	
	//Initialize the serial port. To connect the Arduino, just plug the USB into the RoboRio USB port. Make sure the Arduino is on the same baud rate. 
	public ArduinoRead() {
		serialPort = new SerialPort(BaudRate, SerialPort.Port.kUSB, databits);
		timer = new Timer();
	}
	
	public int getPixyYSerial() {
		serialPort.flush();
		
		serialPort.writeString("1");
		
		while(true) {
			if(serialPort.getBytesReceived() != 0) {
				pixyY = PixyYSerialRead();
				break;
			}
			timer.delay(50 / 1000);
		}
		
		serialPort.flush();
		
		return pixyY;
	}

	private int PixyYSerialRead() {
		pixyY = -1;
		
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

		if (startIndex != endIndex && startIndex != -1 && startIndex < endIndex && (startIndex + 1) != endIndex) {
			in = in.substring(startIndex + 1, endIndex);

			try {
				pixyY = Integer.parseInt(in);
			}
			catch (NumberFormatException e) {
				pixyY = -1;
			}
		} else {
			pixyY = -1;
		}
		
		return pixyY;
	}
	
	public int getPixyXSerial() {
		serialPort.flush();
		
		serialPort.writeString("2");

		while(true) {
			if(serialPort.getBytesReceived() != 0) {
				pixyX = PixyXSerialRead();
				break;
			}
			timer.delay(50 / 1000);
		}
		
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

		if (startIndex != endIndex && startIndex != -1 && startIndex < endIndex && (startIndex + 1) != endIndex) {
			
			in = in.substring(startIndex + 1, endIndex);
			
			try {
				pixyX = Integer.parseInt(in);
			}
			catch (NumberFormatException e) {
				pixyX = -1;
			}
			
			//in = in.substring(startIndex + 1, endIndex);
		} else {
			pixyX = -1;
		}
		
		return pixyX;
	}
}
