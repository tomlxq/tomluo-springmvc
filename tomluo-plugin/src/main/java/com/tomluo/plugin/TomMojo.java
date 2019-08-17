package com.tomluo.plugin;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 在打包时执行插件功能
 *
 * @author TomLuo
 * @date 2019/8/13
 */
@Mojo(name = "tom-plugin", defaultPhase = LifecyclePhase.PACKAGE)
public class TomMojo extends AbstractMojo {
    @Parameter(defaultValue = ".jsp,.html")
    private String includes;
    @Parameter(property = "project.basedir")
    private String basedir;
    @Parameter(property = "project.build.directory")
    private String buildDirectory;
    @Parameter(property = "project.build.sourceDirectory")
    private String sourceDirectory;
    @Parameter(property = "args")
    private String args;
    @Parameter
    private List<Integer> myIntegers;
    @Parameter
    private Double myDouble;
    @Parameter
    private Date myDate;


    @Override
    public void execute() throws MojoExecutionException, MojoFailureException {
        System.out.println(this.getClass().getName() + " plugin");
        System.out.println(sourceDirectory);
        Collection<File> files = FileUtils.listFiles(new File(sourceDirectory), new String[]{"java"}, true);
        System.out.println("一共有java文件：" + files.size());
        Collection<File> jspFiles = FileUtils.listFiles(new File(sourceDirectory), new String[]{"jsp"}, true);
        jspFiles.forEach(f -> {
            try {
                String s = FileUtils.readFileToString(f, StandardCharsets.UTF_8);
                Pattern p = Pattern.compile("/*.[^.js].*/");
                Matcher m = p.matcher(s);
                StringBuffer sb = new StringBuffer();
                while (m.find()) {
                    m.appendReplacement(sb, ".js?"+ RandomUtils.nextInt());
                }
                m.appendTail(sb);
                System.out.println(sb.toString());
            } catch (IOException e) {
                e.printStackTrace();
            }


        });

        System.out.println("basedir :" + basedir);
        System.out.println("buildDirectory :" + buildDirectory);
        System.out.println("args :" + args);
        System.out.println("myIntegers :" + myIntegers);
        System.out.println("myDouble :" + myDouble);
        System.out.println("myDate :" + myDate);
    }
}
