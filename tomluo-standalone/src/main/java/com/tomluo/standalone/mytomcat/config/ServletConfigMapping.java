package com.tomluo.standalone.mytomcat.config;

import java.util.ArrayList;
import java.util.List;

public class ServletConfigMapping {
    private static List<GpServletConfig> configs  = new ArrayList<GpServletConfig>();

    static {
        configs.add(new GpServletConfig("JamesServlet",
                "/gp/james",
                "com.tomluo.standalone.mytomcat.servlet.JamesServlet"));

        configs.add(new GpServletConfig("KobeServlet",
                "/gp/kobe",
                "com.tomluo.standalone.mytomcat.servlet.KobeServlet"));
    }

    public static List<GpServletConfig> getConfigs(){
        return configs;
    }
}