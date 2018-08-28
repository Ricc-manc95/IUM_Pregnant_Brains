// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.inject;

import com.google.android.libraries.hats20.HatsController;
import com.google.android.libraries.hats20.network.GcsConnection;
import com.google.android.libraries.hats20.support.espresso.IdleResourceManager;
import java.util.Random;

public abstract class HatsModule
{

    public static volatile HatsModule instance = null;

    public HatsModule()
    {
    }

    public static HatsModule get()
    {
        if (instance != null) goto _L2; else goto _L1
_L1:
        com/google/android/libraries/hats20/inject/HatsModule;
        JVM INSTR monitorenter ;
        HatsModule hatsmodule = instance;
        if (hatsmodule != null)
        {
            break MISSING_BLOCK_LABEL_57;
        }
        Class.forName("com.google.android.libraries.hats20.inject.CommonHatsModuleInitializer");
        if (instance == null)
        {
            throw new IllegalStateException("HatsModule is not initialized.");
        }
        break MISSING_BLOCK_LABEL_57;
        Object obj;
        obj;
        com/google/android/libraries/hats20/inject/HatsModule;
        JVM INSTR monitorexit ;
        throw obj;
        obj;
        throw new IllegalStateException("Failed to initialize HatsModule", ((Throwable) (obj)));
        com/google/android/libraries/hats20/inject/HatsModule;
        JVM INSTR monitorexit ;
_L2:
        return instance;
    }

    public abstract GcsConnection getGcsConnection();

    public abstract HatsController getHatsController();

    public abstract Random getHatsLibRandomNumberGenerator();

    public abstract IdleResourceManager getIdleResourceManager();

    public abstract String getUserAgent();

    public abstract boolean isTestMode();

}
