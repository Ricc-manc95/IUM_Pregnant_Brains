// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl;

import android.database.Cursor;
import com.google.common.base.Function;
import com.google.protobuf.MessageLite;
import com.google.protobuf.contrib.android.ProtoParsers;
import java.util.HashMap;
import java.util.Map;
import javax.inject.Provider;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl:
//            SqliteMessageStore

final class arg._cls1
    implements Function
{

    private final SqliteMessageStore arg$1;

    public final Object apply(Object obj)
    {
        SqliteMessageStore sqlitemessagestore = arg$1;
        obj = (Cursor)obj;
        HashMap hashmap = new HashMap(((Cursor) (obj)).getCount());
        for (; ((Cursor) (obj)).moveToNext(); hashmap.put(((Cursor) (obj)).getString(((Cursor) (obj)).getColumnIndexOrThrow("key")), ProtoParsers.mergeFrom(((Cursor) (obj)).getBlob(((Cursor) (obj)).getColumnIndexOrThrow("value")), (MessageLite)sqlitemessagestore.defaultInstance.get()))) { }
        return hashmap;
    }

    (SqliteMessageStore sqlitemessagestore)
    {
        arg$1 = sqlitemessagestore;
    }
}
