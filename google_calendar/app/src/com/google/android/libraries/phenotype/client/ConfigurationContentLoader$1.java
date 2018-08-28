// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.phenotype.client;

import android.database.ContentObserver;
import android.os.Handler;

// Referenced classes of package com.google.android.libraries.phenotype.client:
//            ConfigurationContentLoader

final class this._cls0 extends ContentObserver
{

    private final ConfigurationContentLoader this$0;

    public final void onChange(boolean flag)
    {
        ConfigurationContentLoader configurationcontentloader = ConfigurationContentLoader.this;
        synchronized (configurationcontentloader.cacheLock)
        {
            configurationcontentloader.cachedFlags = null;
        }
        notifyConfigurationUpdatedListeners();
        return;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    (Handler handler)
    {
        this$0 = ConfigurationContentLoader.this;
        super(null);
    }
}
