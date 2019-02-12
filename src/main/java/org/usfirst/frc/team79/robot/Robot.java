/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team79.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.usfirst.frc.team79.robot.util.ArcadeUtil;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot{
	/* Intializing talons which control the speed of the robot. There are four talons each which are plugged into
	 * a specific port on the roboRio. */

	WPI_TalonSRX frontLeft = new WPI_TalonSRX(1);
	WPI_TalonSRX frontRight = new WPI_TalonSRX(2);
	WPI_TalonSRX backLeft = new WPI_TalonSRX(3);
	WPI_TalonSRX backRight = new WPI_TalonSRX(4);
	Joystick joy = new Joystick(0); 
	ArcadeUtil drive =  new ArcadeUtil(frontLeft, frontRight); 
	UsbCamera camera;
	UsbCamera camera2;
	// boolean prevTrigger = false;
	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		frontLeft.configFactoryDefault();
		frontRight.configFactoryDefault();
		backLeft.configFactoryDefault();
		backRight.configFactoryDefault();
		backLeft.follow(frontLeft); //Sets the back talons to follow whatever the front talons do
		backRight.follow(frontRight); 

		camera = CameraServer.getInstance().startAutomaticCapture(0);
		camera.setResolution(160, 120);
		
		camera2 = CameraServer.getInstance().startAutomaticCapture(1);
		camera2.setResolution(160, 120);

	
		frontLeft.setInverted(false);
		backLeft.setInverted(InvertType.FollowMaster);;
		frontRight.setInverted(true);
		backRight.setInverted(InvertType.FollowMaster);;

		drive.setRightSideInverted(false);
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void autonomousInit() {
	
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		
	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {
		double forward = 0.9 * joy.getY();	// Sign this so forward is positive
		double turn = 0.8 * joy.getZ(); // Sign this so right is positive
		drive.arcadeDrive(forward, turn);

	}
	

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}
