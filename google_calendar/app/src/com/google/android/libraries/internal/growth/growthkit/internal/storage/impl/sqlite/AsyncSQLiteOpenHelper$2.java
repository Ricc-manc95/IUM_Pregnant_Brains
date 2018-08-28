// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite;

import android.app.ActivityManager;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.google.common.base.Function;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.storage.impl.sqlite:
//            AsyncSQLiteOpenHelper, AsyncSQLiteDatabase

final class this._cls0
    implements Function
{

    private final AsyncSQLiteOpenHelper this$0;

    private final AsyncSQLiteDatabase apply(String s)
    {
        int j = 0x30000000;
        File file = context.getDatabasePath(s);
        s = AsyncSQLiteOpenHelper.this;
        SQLiteDatabase sqlitedatabase;
        Iterator iterator;
        boolean flag;
        int i;
        if (!((ActivityManager)((AsyncSQLiteOpenHelper) (s)).context.getSystemService("activity")).isLowRamDevice())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        Exception exception;
        if (flag)
        {
            i = 0x30000000;
        } else
        {
            i = 0x10000000;
        }
        file.getParentFile().mkdirs();
        sqlitedatabase = SQLiteDatabase.openDatabase(file.getPath(), AsyncSQLiteDatabase.getDefaultCursorFactory(), i, new <init>(s));
        if (flag)
        {
            sqlitedatabase.enableWriteAheadLogging();
        }
        sqlitedatabase.setForeignKeyConstraintsEnabled(true);
        iterator = config.pragmaStatements.iterator();
_L5:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        s = String.valueOf((String)iterator.next());
        if (s.length() == 0) goto _L4; else goto _L3
_L3:
        s = "PRAGMA ".concat(s);
_L6:
        sqlitedatabase.execSQL(s);
          goto _L5
        s;
        if (sqlitedatabase != null)
        {
            try
            {
                sqlitedatabase.close();
            }
            // Misplaced declaration of an exception variable
            catch (String s) { }
        }
        file.delete();
        s = AsyncSQLiteOpenHelper.this;
        if (!((ActivityManager)((AsyncSQLiteOpenHelper) (s)).context.getSystemService("activity")).isLowRamDevice())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            i = j;
        } else
        {
            i = 0x10000000;
        }
        file.getParentFile().mkdirs();
        s = SQLiteDatabase.openDatabase(file.getPath(), AsyncSQLiteDatabase.getDefaultCursorFactory(), i, new <init>(s));
        if (flag)
        {
            s.enableWriteAheadLogging();
        }
        upgradeDatabase(s, upgradeStepList);
_L7:
        return new AsyncSQLiteDatabase(s, executor);
_L4:
        s = new String("PRAGMA ");
          goto _L6
_L2:
        upgradeDatabase(sqlitedatabase, upgradeStepList);
        (new File(String.valueOf(sqlitedatabase.getPath()).concat(".bak"))).delete();
        s = sqlitedatabase;
          goto _L7
        exception;
        if (s != null)
        {
            try
            {
                s.close();
            }
            // Misplaced declaration of an exception variable
            catch (String s) { }
        }
        throw new RuntimeException(exception);
          goto _L6
    }

    public final volatile Object apply(Object obj)
    {
        return apply((String)obj);
    }

    nnectionConfig()
    {
        this$0 = AsyncSQLiteOpenHelper.this;
        super();
    }
}
