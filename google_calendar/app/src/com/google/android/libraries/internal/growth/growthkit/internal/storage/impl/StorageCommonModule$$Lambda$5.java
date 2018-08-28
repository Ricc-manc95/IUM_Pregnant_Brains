// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.common.util.concurrent.ListeningExecutorService;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteMessageStore, GrowthDbHelper

final class arg._cls2
    implements com.google.android.libraries.internal.growth.growthkit.internal.storage.ry
{

    private final ListeningExecutorService arg$1;
    private final GrowthDbHelper arg$2;

    public final Object createForAccount(String s)
    {
        return new SqliteMessageStore(arg$1, arg$2, "monitored_events_visual_element", s, .instance);
    }

    (ListeningExecutorService listeningexecutorservice, GrowthDbHelper growthdbhelper)
    {
        arg$1 = listeningexecutorservice;
        arg$2 = growthdbhelper;
    }
}
