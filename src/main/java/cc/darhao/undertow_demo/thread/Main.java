package cc.darhao.undertow_demo.thread;

import cc.darhao.undertow_demo.config.DataSourceConfig;
import cc.darhao.undertow_demo.thread.quartz.RecordSaveSchedule;
import cc.darhao.undertow_demo.web.UndertowBoot;
import sun.misc.Signal;
import sun.misc.SignalHandler;

public class Main implements SignalHandler {

	private UndertowBoot undertowBoot = new UndertowBoot();
	private DataSourceConfig dataSourceConfig = new DataSourceConfig();
	
	public static void main(String[] args) {
		Main main = new Main();
		Signal.handle(new Signal("INT"), main);//对应Ctrl+C
		Signal.handle(new Signal("TERM"), main);//对应kill
		//详见->Linux信号：https://www.jianshu.com/p/f445bfeea40a
		main.start();
	}
	

	private void start() {
		//开启数据源插件
		dataSourceConfig.start();
		
		//开启Web服务器
		undertowBoot.start();

		//开启定时器
		RecordSaveSchedule.run();

		//配置Pasta
		//...
		
		//开启Netty
		//...
		
		//打印Log4j2（如果日志写到数据库，一定要在数据源开启后打印）
		//...
	}

	
	@Override
	public void handle(Signal arg0) {
		try {
			//打印Log4j2（如果日志写到数据库，一定要在数据源关闭前打印）
			//...
			
			//关闭定时线程
			RecordSaveSchedule.stop();
			
			//关闭Web服务器
			undertowBoot.stop();
			
			//关闭数据源插件
			dataSourceConfig.shutdown();
			
			System.out.println("程序正常结束");
		} catch (Exception e){
			System.err.println("程序异常结束");
			System.exit(1);
		}
		
	}
}
