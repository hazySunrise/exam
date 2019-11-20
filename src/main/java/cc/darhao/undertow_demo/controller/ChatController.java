package cc.darhao.undertow_demo.controller;

import cc.darhao.undertow_demo.constant.SystemProperties;
import com.jfinal.core.Controller;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

public class ChatController extends Controller {

    public void list(){
        File dir = new File(SystemProperties.CHAT_RECORD_SAVE_PATH);
        List<String> files = null ;
        if (dir.exists()) {
            // 获取目录下的所有的 .txt 文件
            String[] names = dir.list(new FilterByTxt());
            files = Arrays.asList(names);
        }
            renderJson(files);
    }

    public void downLoad(String fileName){
        File file = new File( SystemProperties.CHAT_RECORD_SAVE_PATH + fileName);
        if (file.exists()) {
            renderFile(file);
        } else {
            renderJson("文件不存在");
        }
    }


    private class FilterByTxt implements FilenameFilter {
        @Override
        public boolean accept(File dir, String name) {
            return name.endsWith(".txt");
        }
    }

}
