package com.vmantek.demo;

import com.vmantek.jpos.deployer.ResourceDeployer;
import com.vmantek.jpos.deployer.simple.SimplePropertyResolver;
import org.jpos.q2.Q2;

import java.io.File;

public class Main
{
    public static void main(String... args) throws Exception
    {
        File tmpDir = TempDeployTarget.create();

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
