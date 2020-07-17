package cc.darhao.undertow_demo.web;

import cc.darhao.pasta.Pasta;
import cc.darhao.undertow_demo.config.DataSourceConfig;
import cc.darhao.undertow_demo.constant.SystemProperties;
import cc.darhao.undertow_demo.controller.ChatController;
import cc.darhao.undertow_demo.thread.quartz.RecordSaveSchedule;
import cc.darhao.undertow_demo.util.PropUtil;
import cc.darhao.undertow_demo.web.ws.ChatSocket;
import cc.darhao.undertow_demo.web.ws.handler.ChatHandler;
import cc.darhao.undertow_demo.web.ws.handler.LoginHandler;
import com.jfinal.config.*;
import com.jfinal.server.undertow.UndertowServer;
import com.jfinal.template.Engine;

public class UndertowBoot extends JFinalConfig {

    private UndertowServer undertowServer;
    private DataSourceConfig dataSourceConfig = new DataSourceConfig();

    public void start() {
        //配置Pasta
        Pasta.bindRoute("login", LoginHandler.class);
        Pasta.bindRoute("chat", ChatHandler.class);
        //配置web服务器
        undertowServer = UndertowServer.create(UndertowBoot.class);
        int port = PropUtil.getInt(SystemProperties.FILE_NAME, SystemProperties.UNDERTOW_LISTEN_PORT);
        undertowServer.setPort(port);
        //Tips: Dev模式能提供代码热部署功能，不用重启就可以更新。但会有一个监控线程在运行，建议上线关闭该模式
        undertowServer.setDevMode(false);
        //配置websocket（可选）
        undertowServer.configWeb( builder ->{
            builder.addWebSocketEndpoint(ChatSocket.class);
        } );
        undertowServer.start();
    }

    @Override
    public void onStop(){
        //关闭定时线程
        RecordSaveSchedule.stop();
        //关闭数据源插件
        dataSourceConfig.shutdown();
    }

    @Override
    public void configConstant(Constants me) {}

    @Override
    public void configRoute(Routes me) {
        me.add("/chat", ChatController.class);
    }

    @Override
    public void configEngine(Engine me) {}

    @Override
    public void configPlugin(Plugins me) {}

    @Override
    public void configInterceptor(Interceptors me) {}

    @Override
    public void configHandler(Handlers me) {}
}
