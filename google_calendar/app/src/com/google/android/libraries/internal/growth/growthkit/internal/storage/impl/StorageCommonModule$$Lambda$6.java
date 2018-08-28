// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import com.google.android.libraries.internal.growth.growthkit.internal.common.Clock;
import com.google.common.util.concurrent.ListeningExecutorService;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteVisualElementEventsStore, SqliteVisualElementEventsStoreFactory, GrowthDbHelper

final class arg._cls1
    implements com.google.android.libraries.internal.growth.growthkit.internal.common.bda._cls6
{

    private final SqliteVisualElementEventsStoreFactory arg$1;

    public final Object createForAccount(String s)
    {
        SqliteVisualElementEventsStoreFactory sqlitevisualelementeventsstorefactory = arg$1;
        return new SqliteVisualElementEventsStore((ListeningExecutorService)SqliteVisualElementEventsStoreFactory.checkNotNull((ListeningExecutorService)sqlitevisualelementeventsstorefactory.executorProvider.get(), 1), (GrowthDbHelper)SqliteVisualElementEventsStoreFactory.checkNotNull((GrowthDbHelper)sqlitevisualelementeventsstorefactory.dbHelperProvider.get(), 2), s, (Clock)SqliteVisualElementEventsStoreFactory.checkNotNull((Clock)sqlitevisualelementeventsstorefactory.clockProvider.get(), 4));
    }

    Factory(SqliteVisualElementEventsStoreFactory sqlitevisualelementeventsstorefactory)
    {
        arg$1 = sqlitevisualelementeventsstorefactory;
    }
}
