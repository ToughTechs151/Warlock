package org.usfirst.frc.team151.robot;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class BaseVision {

	UsbCamera usbCamera = null;
	
	public BaseVision(int channel) {
		usbCamera = CameraServer.getInstance().startAutomaticCapture(channel);
	}
	
	public CvSink getVideoFrame() {
		return CameraServer.getInstance().getVideo();
	}
 /*
  * A method like this called startVision will be in each class and start the vision 
  * for each separate class, and should be distinct for each class
		public void startVision() {
		//TODO INSERT CODE FOR EACH SEPARATE VISION CLASS
		}
	*/
}
