// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.inject;

import android.os.Build;
import com.google.android.libraries.hats20.HatsController;
import com.google.android.libraries.hats20.HatsControllerImpl;
import com.google.android.libraries.hats20.network.GcsConnection;
import com.google.android.libraries.hats20.network.JavanetGcsConnection;
import com.google.android.libraries.hats20.support.espresso.IdleResourceManager;
import java.util.Locale;
import java.util.Random;

// Referenced classes of package com.google.android.libraries.hats20.inject:
//            HatsModule

public final class CommonHatsModule extends HatsModule
{

    private static final String USER_AGENT;
    private volatile GcsConnection gcsConnection;
    private volatile HatsController hatsController;
    private volatile Random hatsLibRandomNumberGenerator;
    private volatile IdleResourceManager idleResourceManager;
    private volatile boolean isTestMode;

    public CommonHatsModule()
    {
        hatsLibRandomNumberGenerator = new Random();
    }

    public final GcsConnection getGcsConnection()
    {
        if (gcsConnection != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (gcsConnection == null)
        {
            gcsConnection = new JavanetGcsConnection();
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return gcsConnection;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final HatsController getHatsController()
    {
        if (hatsController != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (hatsController == null)
        {
            hatsController = new HatsControllerImpl();
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return hatsController;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final Random getHatsLibRandomNumberGenerator()
    {
        return hatsLibRandomNumberGenerator;
    }

    public final IdleResourceManager getIdleResourceManager()
    {
        if (idleResourceManager != null) goto _L2; else goto _L1
_L1:
        this;
        JVM INSTR monitorenter ;
        if (idleResourceManager == null)
        {
            idleResourceManager = new IdleResourceManager();
        }
        this;
        JVM INSTR monitorexit ;
_L2:
        return idleResourceManager;
        Exception exception;
        exception;
        this;
        JVM INSTR monitorexit ;
        throw exception;
    }

    public final String getUserAgent()
    {
        return USER_AGENT;
    }

    public final boolean isTestMode()
    {
        return isTestMode;
    }

    static 
    {
        USER_AGENT = String.format(Locale.US, "Mozilla/5.0; Hats App/v%d (Android %s; SDK %d; %s; %s; %s)", new Object[] {
            Integer.valueOf(2), android.os.Build.VERSION.RELEASE, Integer.valueOf(android.os.Build.VERSION.SDK_INT), Build.ID, Build.MODEL, Build.TAGS
        });
    }
}
