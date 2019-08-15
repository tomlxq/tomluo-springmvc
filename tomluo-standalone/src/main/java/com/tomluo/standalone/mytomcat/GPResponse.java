package com.tomluo.standalone.mytomcat;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by James on 2017-05-27.
 * From 咕泡学院出品
 * 老师咨询 QQ 2904270631
 */
public class GPResponse {
    private OutputStream outputStream;

    public GPResponse(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public void write(String content){
        try {
            outputStream.write(content.getBytes());
        } catch (IOException e) {
            //TODO
            e.printStackTrace();
        }
    }
}
