package com.jimi.display.app;


import cc.darhao.dautils.api.ResourcesUtil;
import com.jimi.display.controller.TaskController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Configurator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Main extends Application {

	private TaskController taskController;

	private static final String VERSION = "1.0.0";

	private static final String FILE_NAME = "Hik_Display-" + VERSION + ".jar";

	@Override
	public void start(Stage primaryStage) throws Exception {

		// ȷ��ֻ��һ��ʵ������
		if (isRunning()) {
			new Alert(AlertType.WARNING, "��һ��ʵ���Ѿ��������У������ظ����У�", ButtonType.OK).show();
			new Thread(() -> {
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.exit(0);
			}).start();
		} else {
			FXMLLoader loader = new FXMLLoader(ResourcesUtil.getResourceURL("fxml/genAgvSchedulingTask.fxml"));
			Parent root = loader.load();
			// ��Stage����MainController
			taskController = loader.getController();
			taskController.setStage(primaryStage);
			// ��ʾ
			primaryStage.setTitle("����������ȵ�- " + VERSION);
			primaryStage.setScene(new Scene(root));
			//�˳�����ʱ�������߳�
			primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
				@Override
				public void handle(WindowEvent e) {
					Platform.exit();
					System.exit(0);
				}
			});
			primaryStage.show();
		}
	}

	/**
	 * �������
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		ConfigurationSource source;
		try {
			source = new ConfigurationSource(ResourcesUtil.getResourceAsStream("log4j/log4j.xml"));
			Configurator.initialize(null, source);
		} catch (IOException e) {
			e.printStackTrace();
		}
		launch(args);
	}


	/**
	 * �ж��Ƿ��Ѿ���һ��ʵ��������
	 * 
	 * @return
	 * @throws IOException
	 */
	public static boolean isRunning() throws IOException {
		String line = null;
		InputStream in = Runtime.getRuntime().exec("jps").getInputStream();
		BufferedReader b = new BufferedReader(new InputStreamReader(in));
		int count = 0;
		while ((line = b.readLine()) != null) {
			if (line.contains(FILE_NAME)) {
				count++;
				if (count > 1) {
					return true;
				}
			}
		}
		return false;
	}


	public static String getVersion() {
		return VERSION;
	}



}
