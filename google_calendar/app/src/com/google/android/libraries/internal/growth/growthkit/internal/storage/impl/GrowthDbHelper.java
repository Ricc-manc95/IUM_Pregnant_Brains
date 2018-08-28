// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import android.content.Context;
import com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper;
import com.google.common.collect.ImmutableList;
import com.google.common.util.concurrent.ListeningExecutorService;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteMessageStore

public final class GrowthDbHelper
{

    private static final ImmutableList UPGRADE_LIST = ImmutableList.of(new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper.StatementStep("CREATE TABLE clearcut_events_table (account TEXT NOT NULL, timestamp_ms INTEGER NOT NULL, log_source INTEGER NOT NULL, event_code INTEGER NOT NULL, package_name TEXT NOT NULL)"), new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper.StatementStep(SqliteMessageStore.buildInitialSchema("promotions")), new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper.StatementStep(SqliteMessageStore.buildInitialSchema("capped_promos")), new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper.StatementStep(SqliteMessageStore.buildInitialSchema("presented_promos")), new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper.StatementStep(SqliteMessageStore.buildInitialSchema("monitored_events_clearcut")), new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper.StatementStep(SqliteMessageStore.buildInitialSchema("monitored_events_visual_element")), new com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite.AsyncSQLiteOpenHelper.StatementStep("CREATE TABLE visual_element_events_table (account TEXT NOT NULL, timestamp_ms INTEGER NOT NULL, node_id INTEGER NOT NULL, node_id_path TEXT NOT NULL, action INTEGER NOT NULL)"));
    public AsyncSQLiteOpenHelper asyncSQLiteOpenHelper;

    public GrowthDbHelper(Context context, ListeningExecutorService listeningexecutorservice)
    {
        asyncSQLiteOpenHelper = new AsyncSQLiteOpenHelper("growthkit.db", context, listeningexecutorservice, UPGRADE_LIST);
    }

}
