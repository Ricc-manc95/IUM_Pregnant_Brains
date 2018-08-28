// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.apps.xplat.logging;


// Referenced classes of package com.google.apps.xplat.logging:
//            LoggerBackendDiscovery, LoggerBackend

public final class LoggerBackendConfig
{

    public static volatile LoggerBackend configuredBackend;
    public static final Object lock = new Object();

    public static LoggerBackend getBackend()
    {
        if (configuredBackend == null)
        {
            synchronized (lock)
            {
                if (configuredBackend == null)
                {
                    configuredBackend = LoggerBackendDiscovery.discoverBackend();
                }
            }
        }
        return configuredBackend;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

}
