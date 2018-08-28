// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timely.store;

import android.accounts.Account;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.util.LruCache;
import android.util.Pair;

// Referenced classes of package com.google.android.apps.calendar.timely.store:
//            TimelyStore

final class this._cls0 extends LruCache
{

    private final TimelyStore this$0;

    private final String create(Pair pair)
    {
        Object obj;
        Object obj1;
        Object obj2;
        obj = null;
        obj2 = (Account)pair.first;
        obj1 = (String)pair.second;
        SQLiteDatabase sqlitedatabase = database;
        String s = ((Account) (obj2)).name;
        obj2 = ((Account) (obj2)).type;
        obj1 = sqlitedatabase.query("calendar_settings", new String[] {
            "conference_properties"
        }, "calendar_sync_id = ? AND account_name = ? AND account_type = ?", new String[] {
            obj1, s, obj2
        }, null, null, null);
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_124;
        }
        if (!((Cursor) (obj1)).moveToFirst())
        {
            break MISSING_BLOCK_LABEL_124;
        }
        obj = TimelyStore.this;
        pair = TimelyStore.parseConferenceType(pair, ((Cursor) (obj1)).getString(0));
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
        return pair;
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
        return null;
        pair;
_L2:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw pair;
        pair;
        obj = obj1;
        if (true) goto _L2; else goto _L1
_L1:
    }

    protected final volatile Object create(Object obj)
    {
        return create((Pair)obj);
    }

    public (int i)
    {
        this$0 = TimelyStore.this;
        super(10000);
    }
}
