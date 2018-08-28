// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Logger;
import com.google.android.libraries.internal.growth.growthkit.lifecycle.GrowthKitStartupListener;
import java.util.Iterator;
import java.util.Set;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.lifecycle.impl:
//            GrowthKitStartupImpl

final class arg._cls1
    implements Runnable
{

    private final GrowthKitStartupImpl arg$1;

    public final void run()
    {
        Object obj = arg$1;
        if (!((Boolean)((GrowthKitStartupImpl) (obj)).enableFlagProvider.get()).booleanValue())
        {
            obj = GrowthKitStartupImpl.logger;
            obj = ((Object) (new Object[0]));
            if (obj != null && obj.length > 0)
            {
                String.format("GrowthKit is disabled by Phenotype flag.", ((Object []) (obj)));
            }
        } else
        {
            obj = ((Set)((GrowthKitStartupImpl) (obj)).listeners.get()).iterator();
            while (((Iterator) (obj)).hasNext()) 
            {
                GrowthKitStartupListener growthkitstartuplistener = (GrowthKitStartupListener)((Iterator) (obj)).next();
                try
                {
                    growthkitstartuplistener.onApplicationStartup$51662RJ4E9NMIP1FCDNMST35DPQ2UGRFDPQ6AU3K7CKLC___0();
                }
                catch (Exception exception)
                {
                    GrowthKitStartupImpl.logger.e(exception, "Failed startup listener: %s", new Object[] {
                        growthkitstartuplistener
                    });
                }
            }
        }
    }

    (GrowthKitStartupImpl growthkitstartupimpl)
    {
        arg$1 = growthkitstartupimpl;
    }
}
