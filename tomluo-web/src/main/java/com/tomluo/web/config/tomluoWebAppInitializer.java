package com.tomluo.web.config;

import com.tomluo.dal.config.DataSourceConfig;
import com.tomluo.dal.config.MybatisConfig;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import java.util.EnumSet;

/**
 * Created by James
 * on 16/8/16.
 * Description: web startup
 */
public class tomluoWebAppInitializer implements WebApplicationInitializer {
    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        AnnotationConfigWebApplicationContext rootContext = new AnnotationConfigWebApplicationContext();
        rootContext.register(DataSourceConfig.class, MybatisConfig.class);
        servletContext.addListener(new ContextLoaderListener(rootContext));

        //servlets & contorllers
        AnnotationConfigWebApplicationContext webContext = new AnnotationConfigWebApplicationContext();
        webContext.register(WebConfig.class, WebConfigEx.class, ServiceConfigEx.class);

        //filters
        FilterRegistration.Dynamic encodingFilterRegistration =
                servletContext.addFilter("encodingFilter", new CharacterEncodingFilter());
        encodingFilterRegistration.setInitParameter("encoding", "UTF-8");
        encodingFilterRegistration.setInitParameter("forceEncoding", "true");
        encodingFilterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,
                DispatcherType.FORWARD), true, "/*");


//        FilterRegistration.Dynamic monitorFilterRegistration =
//                servletContext.addFilter("monitorFilter", new MonitorFilter());
//        monitorFilterRegistration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST,
//                DispatcherType.FORWARD), true, "/*");

        ServletRegistration.Dynamic registration = servletContext.addServlet("dispatcher", new DispatcherServlet(webContext));
        registration.setLoadOnStartup(1);
        registration.addMapping("/");

    }
}
