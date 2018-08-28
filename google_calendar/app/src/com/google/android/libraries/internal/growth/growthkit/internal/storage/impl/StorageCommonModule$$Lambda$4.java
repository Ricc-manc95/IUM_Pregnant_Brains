// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.common.util.concurrent.ListeningExecutorService;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteClearcutEventsStore, SqliteClearcutEventsStoreFactory, GrowthDbHelper

final class arg._cls1
    implements com.google.android.libraries.internal.growth.growthkit.internal.common.bda._cls4
{

    private final SqliteClearcutEventsStoreFactory arg$1;

    public final Object createForAccount(String s)
    {
        SqliteClearcutEventsStoreFactory sqliteclearcuteventsstorefactory = arg$1;
        return new SqliteClearcutEventsStore((ListeningExecutorService)SqliteClearcutEventsStoreFactory.checkNotNull((ListeningExecutorService)sqliteclearcuteventsstorefactory.executorProvider.get(), 1), (GrowthDbHelper)SqliteClearcutEventsStoreFactory.checkNotNull((GrowthDbHelper)sqliteclearcuteventsstorefactory.dbHelperProvider.get(), 2), s);
    }

    ry(SqliteClearcutEventsStoreFactory sqliteclearcuteventsstorefactory)
    {
        arg$1 = sqliteclearcuteventsstorefactory;
    }
}
