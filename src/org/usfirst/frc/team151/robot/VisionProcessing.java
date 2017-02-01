package org.usfirst.frc.team151.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;

public class VisionProcessing {
	
	private CameraServer cameraServer = null;

	Thread visionThread;
	
	public VisionProcessing() {
//		cameraServer = CameraServer.getInstance();
//		cameraServer.startAutomaticCapture();
		visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
			UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
			// Set the resolution
			camera1.setResolution(640, 480); 
			camera2.setResolution(640, 480);

			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream = CameraServer.getInstance().putVideo("Rectangle", 640, 480);

			Mat mat = new Mat();

			while (!Thread.interrupted()) {
				// Tell the CvSink to grab a frame from the camera and put it
				// in the source mat.  If there is an error notify the output.
				if (cvSink.grabFrame(mat) == 0) {
					outputStream.notifyError(cvSink.getError());
					continue;
				}
				
//				 Put a rectangle on the image
				Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
						new Scalar(255, 255, 255), 5);
//				Imgproc.circle(mat, new Point(200, 200), 50, new Scalar(0, 255, 0, 0), 15);
				// Give the output stream a new image to display
				outputStream.putFrame(mat);
			}
		});
		visionThread.setDaemon(true);
		visionThread.start();
	}
}
