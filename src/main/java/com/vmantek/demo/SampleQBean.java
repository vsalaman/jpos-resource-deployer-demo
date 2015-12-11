package com.vmantek.demo;

import org.jpos.q2.QBeanSupport;

public class SampleQBean extends QBeanSupport
{
    @Override
    protected void startService() throws Exception
    {
        log.info("Started sample qbean with prop: "+cfg.get("prop"));
    }
}
