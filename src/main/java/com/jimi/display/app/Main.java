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

		// 确保只有一个实例启动
		if (isRunning()) {
			new Alert(AlertType.WARNING, "另一个实例已经在运行中，请勿重复运行！", ButtonType.OK).show();
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
			// 把Stage存入MainController
			taskController = loader.getController();
			taskController.setStage(primaryStage);
			// 显示
			primaryStage.setTitle("生成任务调度单- " + VERSION);
			primaryStage.setScene(new Scene(root));
			//退出程序时结束子线程
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
	 * 程序入口
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
	 * 判断是否已经有一个实例在运行
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
