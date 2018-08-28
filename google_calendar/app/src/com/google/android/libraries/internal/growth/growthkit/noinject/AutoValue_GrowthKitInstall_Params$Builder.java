// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.noinject;

import android.content.Context;
import com.google.common.util.concurrent.ListeningExecutorService;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.noinject:
//            AutoValue_GrowthKitInstall_Params

public final class 
    implements 
{

    private String apiKey;
    private Context context;
    private ListeningExecutorService executorService;

    public final  build()
    {
        String s2 = "";
        if (context == null)
        {
            s2 = String.valueOf("").concat(" context");
        }
        String s = s2;
        if (apiKey == null)
        {
            s = String.valueOf(s2).concat(" apiKey");
        }
        s2 = s;
        if (executorService == null)
        {
            s2 = String.valueOf(s).concat(" executorService");
        }
        if (!s2.isEmpty())
        {
            String s1 = String.valueOf(s2);
            if (s1.length() != 0)
            {
                s1 = "Missing required properties:".concat(s1);
            } else
            {
                s1 = new String("Missing required properties:");
            }
            throw new IllegalStateException(s1);
        } else
        {
            return new AutoValue_GrowthKitInstall_Params(context, apiKey, executorService, null);
        }
    }

    public final executorService setApiKey(String s)
    {
        apiKey = s;
        return this;
    }

    public final apiKey setContext(Context context1)
    {
        if (context1 == null)
        {
            throw new NullPointerException("Null context");
        } else
        {
            context = context1;
            return this;
        }
    }

    public final context setExecutorService(ListeningExecutorService listeningexecutorservice)
    {
        if (listeningexecutorservice == null)
        {
            throw new NullPointerException("Null executorService");
        } else
        {
            executorService = listeningexecutorservice;
            return this;
        }
    }

    public ()
    {
    }
}
