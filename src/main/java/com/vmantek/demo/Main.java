package com.vmantek.demo;

import com.vmantek.jpos.deployer.ResourceDeployer;
import com.vmantek.jpos.deployer.simple.SimplePropertyResolver;
import com.vmantek.jpos.deployer.spi.PropertyResolver;
import org.jpos.q2.Q2;

import java.io.File;
import java.nio.file.Files;

public class Main
{
    public static void main(String... args) throws Exception
    {
        File tmpDir = Files.createTempDirectory("jpos-Q2").toAbsolutePath().toFile();

        SimplePropertyResolver resolver = new SimplePropertyResolver(tmpDir);
        resolver.addConfigFile("config/config.properties");
        resolver.initialize();

        ResourceDeployer deployer = ResourceDeployer.newInstance(resolver, tmpDir);
        deployer.installRuntimeResources();
        deployer.startConfigMonitoring();

        System.setProperty("DB_PROPERTIES",
                           new File(tmpDir,"cfg/db.properties").getAbsolutePath());

        String[] xargs = new String[]{"-r","-d", new File(tmpDir, "deploy").getAbsolutePath()};
        Q2 q2 = new Q2(xargs);
        q2.start();
    }
}
