// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.analytics.tracking.android;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.CursorWindow;
import android.database.sqlite.SQLiteCursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.analytics.internal.Command;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.analytics.tracking.android:
//            AnalyticsStore, SimpleNetworkDispatcher, Hit, AnalyticsStoreStateListener, 
//            Log, Dispatcher, GAServiceManager, Clock, 
//            HitBuilder

final class PersistentAnalyticsStore
    implements AnalyticsStore
{

    public static final String CREATE_HITS_TABLE = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL, '%s' TEXT NOT NULL, '%s' INTEGER);", new Object[] {
        "hits2", "hit_id", "hit_time", "hit_url", "hit_string", "hit_app_id"
    });
    public Clock mClock;
    public final Context mContext;
    public final String mDatabaseName;
    private final AnalyticsDatabaseHelper mDbHelper;
    private volatile Dispatcher mDispatcher;
    private long mLastDeleteStaleHitsTime;
    private final AnalyticsStoreStateListener mListener;

    PersistentAnalyticsStore(AnalyticsStoreStateListener analyticsstorestatelistener, Context context)
    {
        this(analyticsstorestatelistener, context, "google_analytics_v2.db");
    }

    private PersistentAnalyticsStore(AnalyticsStoreStateListener analyticsstorestatelistener, Context context, String s)
    {
        mContext = context.getApplicationContext();
        mDatabaseName = s;
        mListener = analyticsstorestatelistener;
        mClock = new _cls1();
        mDbHelper = new AnalyticsDatabaseHelper(mContext, mDatabaseName);
        mDispatcher = new SimpleNetworkDispatcher(new _cls2(), mContext);
        mLastDeleteStaleHitsTime = 0L;
    }

    private final void deleteHits(Collection collection)
    {
        boolean flag;
        flag = false;
        if (collection == null)
        {
            throw new NullPointerException("hits cannot be null");
        }
        if (!collection.isEmpty()) goto _L2; else goto _L1
_L1:
        Object obj;
        return;
_L2:
        if ((obj = getWritableDatabase("Error opening database for deleteHit")) == null) goto _L1; else goto _L3
_L3:
        String as[];
        String s;
        as = new String[collection.size()];
        s = String.format("HIT_ID in (%s)", new Object[] {
            TextUtils.join(",", Collections.nCopies(as.length, "?"))
        });
        Iterator iterator = collection.iterator();
        for (int i = 0; iterator.hasNext(); i++)
        {
            as[i] = Long.toString(((Hit)iterator.next()).mHitId);
        }

        ((SQLiteDatabase) (obj)).delete("hits2", s, as);
        obj = mListener;
        if (getNumStoredHits() == 0)
        {
            flag = true;
        }
        try
        {
            ((AnalyticsStoreStateListener) (obj)).reportStoreIsEmpty(flag);
            return;
        }
        catch (SQLiteException sqliteexception)
        {
            Log.w((new StringBuilder("Error deleting hit ")).append(collection).toString());
        }
        return;
    }

    private final int getNumStoredHits()
    {
        Cursor cursor;
        Object obj;
        Object obj1;
        int i;
        int j;
        obj = null;
        cursor = null;
        i = 0;
        j = 0;
        obj1 = getWritableDatabase("Error opening database for requestNumHitsPending");
        if (obj1 != null) goto _L2; else goto _L1
_L1:
        return j;
_L2:
        obj1 = ((SQLiteDatabase) (obj1)).rawQuery("SELECT COUNT(*) from hits2", null);
        cursor = ((Cursor) (obj1));
        obj = obj1;
        if (!((Cursor) (obj1)).moveToFirst())
        {
            break MISSING_BLOCK_LABEL_63;
        }
        cursor = ((Cursor) (obj1));
        obj = obj1;
        long l = ((Cursor) (obj1)).getLong(0);
        i = (int)l;
        j = i;
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
            return i;
        }
        if (true) goto _L1; else goto _L3
_L3:
        obj;
        obj = cursor;
        Log.w("Error getting numStoredHits");
        if (cursor == null) goto _L1; else goto _L4
_L4:
        cursor.close();
        return 0;
        Exception exception;
        exception;
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw exception;
    }

    private final SQLiteDatabase getWritableDatabase(String s)
    {
        SQLiteDatabase sqlitedatabase;
        try
        {
            sqlitedatabase = mDbHelper.getWritableDatabase();
        }
        catch (SQLiteException sqliteexception)
        {
            Log.w(s);
            return null;
        }
        return sqlitedatabase;
    }

    private final List peekHits(int i)
    {
        Object obj2 = getWritableDatabase("Error opening database for peekHits");
        if (obj2 != null) goto _L2; else goto _L1
_L1:
        Object obj1 = new ArrayList();
_L9:
        return ((List) (obj1));
_L2:
        obj1 = null;
        new ArrayList();
        Object obj;
        obj = String.format("%s ASC, %s ASC", new Object[] {
            "hit_url", "hit_id"
        });
        String s = Integer.toString(i);
        obj = ((SQLiteDatabase) (obj2)).query("hits2", new String[] {
            "hit_id", "hit_time", "hit_url"
        }, null, null, null, null, ((String) (obj)), s);
        ArrayList arraylist = new ArrayList();
        boolean flag;
        if (((Cursor) (obj)).moveToFirst())
        {
            do
            {
                obj1 = new Hit(null, ((Cursor) (obj)).getLong(0), ((Cursor) (obj)).getLong(1));
                obj1.mHitUrl = ((Cursor) (obj)).getString(2);
                arraylist.add(obj1);
                flag = ((Cursor) (obj)).moveToNext();
            } while (flag);
        }
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        obj1 = obj;
        Object obj3 = String.format("%s ASC", new Object[] {
            "hit_id"
        });
        obj1 = obj;
        String s1 = Integer.toString(i);
        obj1 = obj;
        obj2 = ((SQLiteDatabase) (obj2)).query("hits2", new String[] {
            "hit_id", "hit_string"
        }, null, null, null, null, ((String) (obj3)), s1);
        if (!((Cursor) (obj2)).moveToFirst()) goto _L4; else goto _L3
_L3:
        i = 0;
_L18:
        if (!(obj2 instanceof SQLiteCursor)) goto _L6; else goto _L5
_L5:
        if (((SQLiteCursor)obj2).getWindow().getNumRows() <= 0) goto _L8; else goto _L7
_L7:
        ((Hit)arraylist.get(i)).mHitString = ((Cursor) (obj2)).getString(1);
_L11:
        boolean flag1 = ((Cursor) (obj2)).moveToNext();
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_666;
        }
_L4:
        obj1 = arraylist;
        if (obj2 != null)
        {
            ((Cursor) (obj2)).close();
            return arraylist;
        }
          goto _L9
        obj1;
        obj = null;
_L17:
        Log.w((new StringBuilder("error in peekHits fetching hitIds: ")).append(((SQLiteException) (obj1)).getMessage()).toString());
        obj2 = new ArrayList();
        obj1 = obj2;
        if (obj == null) goto _L9; else goto _L10
_L10:
        ((Cursor) (obj)).close();
        return ((List) (obj2));
        obj;
_L16:
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
        throw obj;
_L8:
        Log.w((new StringBuilder("hitString for hitId ")).append(((Hit)arraylist.get(i)).mHitId).append(" too large.  Hit will be deleted.").toString());
          goto _L11
        obj1;
        obj = obj2;
        obj2 = obj1;
_L15:
        obj1 = obj;
        Log.w((new StringBuilder("error in peekHits fetching hitString: ")).append(((SQLiteException) (obj2)).getMessage()).toString());
        obj1 = obj;
        obj2 = new ArrayList();
        i = 0;
        obj1 = obj;
        arraylist = (ArrayList)arraylist;
        obj1 = obj;
        int l = arraylist.size();
        int j = 0;
_L13:
        if (j >= l)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = obj;
        obj3 = arraylist.get(j);
        int k;
        k = j + 1;
        obj1 = obj;
        obj3 = (Hit)obj3;
        j = i;
        obj1 = obj;
        if (TextUtils.isEmpty(((Hit) (obj3)).mHitString))
        {
            if (i != 0)
            {
                break; /* Loop/switch isn't completed */
            }
            j = 1;
        }
        obj1 = obj;
        ((List) (obj2)).add(obj3);
        i = j;
        j = k;
        if (true) goto _L13; else goto _L12
        obj;
_L14:
        if (obj1 != null)
        {
            ((Cursor) (obj1)).close();
        }
        throw obj;
_L6:
        ((Hit)arraylist.get(i)).mHitString = ((Cursor) (obj2)).getString(1);
          goto _L11
        obj;
        obj1 = obj2;
          goto _L14
_L12:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        return ((List) (obj2));
        obj2;
          goto _L15
        Exception exception;
        exception;
        obj1 = obj;
        obj = exception;
          goto _L16
        exception;
        obj1 = obj;
        obj = exception;
          goto _L16
        obj1;
          goto _L17
        i++;
          goto _L18
    }

    public final void clearHits(long l)
    {
        boolean flag = true;
        Object obj = getWritableDatabase("Error opening database for clearHits");
        if (obj != null)
        {
            if (0L == 0L)
            {
                ((SQLiteDatabase) (obj)).delete("hits2", null, null);
            } else
            {
                ((SQLiteDatabase) (obj)).delete("hits2", "hit_app_id = ?", new String[] {
                    Long.valueOf(0L).toString()
                });
            }
            obj = mListener;
            if (getNumStoredHits() != 0)
            {
                flag = false;
            }
            ((AnalyticsStoreStateListener) (obj)).reportStoreIsEmpty(flag);
        }
    }

    public final void dispatch()
    {
        if (mDispatcher.okToDispatch())
        {
            List list = peekHits(40);
            if (list.isEmpty())
            {
                mListener.reportStoreIsEmpty(true);
                return;
            }
            int i = mDispatcher.dispatchHits(list);
            (new StringBuilder("sent ")).append(i).append(" of ").append(list.size()).append(" hits").toString();
            deleteHits(list.subList(0, Math.min(i, list.size())));
            if (i == list.size() && getNumStoredHits() > 0)
            {
                if (GAServiceManager.instance == null)
                {
                    GAServiceManager.instance = new GAServiceManager();
                }
                GAServiceManager.instance.dispatch();
                return;
            }
        }
    }

    public final void putHit(Map map, long l, String s, Collection collection)
    {
        Object obj;
        Object obj1;
        boolean flag = true;
        long l1 = mClock.currentTimeMillis();
        if (l1 > mLastDeleteStaleHitsTime + 0x5265c00L)
        {
            mLastDeleteStaleHitsTime = l1;
            obj = getWritableDatabase("Error opening database for deleteStaleHits");
            if (obj != null)
            {
                ((SQLiteDatabase) (obj)).delete("hits2", "HIT_TIME < ?", new String[] {
                    Long.toString(mClock.currentTimeMillis() - 0x9a7ec800L)
                });
                obj = mListener;
                if (getNumStoredHits() != 0)
                {
                    flag = false;
                }
                ((AnalyticsStoreStateListener) (obj)).reportStoreIsEmpty(flag);
            }
        }
        obj = collection.iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            collection = (Command)((Iterator) (obj)).next();
            if (!((Command) (collection)).id.equals("appendVersion"))
            {
                continue;
            }
            obj1 = ((Command) (collection)).value;
            obj = ((Command) (collection)).urlParam;
            Iterator iterator;
            java.util.Map.Entry entry;
            int i;
            if (obj1 == null)
            {
                collection = "";
            } else
            {
                collection = (new StringBuilder()).append(((String) (obj1))).toString();
            }
            if (obj != null)
            {
                map.put(obj, collection);
            }
            break;
        } while (true);
        i = (getNumStoredHits() - 2000) + 1;
        if (i > 0)
        {
            collection = peekHits(i);
            (new StringBuilder("Store full, deleting ")).append(collection.size()).append(" hits to make room").toString();
            deleteHits(collection);
        }
        collection = getWritableDatabase("Error opening database for putHit");
        if (collection == null) goto _L2; else goto _L1
_L1:
        obj = new ContentValues();
        obj1 = new ArrayList(map.size());
        for (iterator = map.entrySet().iterator(); iterator.hasNext(); ((List) (obj1)).add((new StringBuilder()).append((String)entry.getKey()).append("=").append(HitBuilder.encode((String)entry.getValue())).toString()))
        {
            entry = (java.util.Map.Entry)iterator.next();
        }

        ((ContentValues) (obj)).put("hit_string", TextUtils.join("&", ((Iterable) (obj1))));
        ((ContentValues) (obj)).put("hit_time", Long.valueOf(l));
        if (!map.containsKey("AppUID")) goto _L4; else goto _L3
_L3:
        l = Long.parseLong((String)map.get("AppUID"));
_L6:
        ((ContentValues) (obj)).put("hit_app_id", Long.valueOf(l));
        map = s;
        if (s == null)
        {
            map = "http://www.google-analytics.com/collect";
        }
        if (map.length() != 0)
        {
            break; /* Loop/switch isn't completed */
        }
        Log.w("empty path: not sending hit");
_L2:
        return;
        map;
_L4:
        l = 0L;
        if (true) goto _L6; else goto _L5
_L5:
        ((ContentValues) (obj)).put("hit_url", map);
        try
        {
            collection.insert("hits2", null, ((ContentValues) (obj)));
            mListener.reportStoreIsEmpty(false);
            return;
        }
        // Misplaced declaration of an exception variable
        catch (Map map)
        {
            Log.w("Error storing hit");
        }
        return;
    }


    private class _cls1
        implements Clock
    {

        public final long currentTimeMillis()
        {
            return System.currentTimeMillis();
        }

        _cls1()
        {
        }
    }


    private class AnalyticsDatabaseHelper extends SQLiteOpenHelper
    {

        private boolean mBadDatabase;
        private long mLastDatabaseCheckTime;
        private final PersistentAnalyticsStore this$0;

        private static boolean tablePresent(String s, SQLiteDatabase sqlitedatabase)
        {
            Object obj = null;
            sqlitedatabase = sqlitedatabase.query("SQLITE_MASTER", new String[] {
                "name"
            }, "name=?", new String[] {
                s
            }, null, null, null);
            boolean flag = sqlitedatabase.moveToFirst();
            if (sqlitedatabase != null)
            {
                sqlitedatabase.close();
            }
            return flag;
            sqlitedatabase;
            sqlitedatabase = null;
_L4:
            Log.w((new StringBuilder("error querying for table ")).append(s).toString());
            if (sqlitedatabase != null)
            {
                sqlitedatabase.close();
            }
            return false;
            s;
            sqlitedatabase = obj;
_L2:
            if (sqlitedatabase != null)
            {
                sqlitedatabase.close();
            }
            throw s;
            s;
            continue; /* Loop/switch isn't completed */
            s;
            if (true) goto _L2; else goto _L1
_L1:
            SQLiteException sqliteexception;
            sqliteexception;
            if (true) goto _L4; else goto _L3
_L3:
        }

        public final SQLiteDatabase getWritableDatabase()
        {
            SQLiteDatabase sqlitedatabase;
            if (mBadDatabase && mLastDatabaseCheckTime + 0x36ee80L > mClock.currentTimeMillis())
            {
                throw new SQLiteException("Database creation failed");
            }
            sqlitedatabase = null;
            mBadDatabase = true;
            mLastDatabaseCheckTime = mClock.currentTimeMillis();
            SQLiteDatabase sqlitedatabase1 = super.getWritableDatabase();
            sqlitedatabase = sqlitedatabase1;
_L2:
            SQLiteDatabase sqlitedatabase2 = sqlitedatabase;
            if (sqlitedatabase == null)
            {
                sqlitedatabase2 = super.getWritableDatabase();
            }
            mBadDatabase = false;
            return sqlitedatabase2;
            SQLiteException sqliteexception;
            sqliteexception;
            mContext.getDatabasePath(mDatabaseName).delete();
            if (true) goto _L2; else goto _L1
_L1:
        }

        public final void onCreate(SQLiteDatabase sqlitedatabase)
        {
            sqlitedatabase = sqlitedatabase.getPath();
            if (FutureApis.version() < 9)
            {
                return;
            } else
            {
                sqlitedatabase = new File(sqlitedatabase);
                sqlitedatabase.setReadable(false, false);
                sqlitedatabase.setWritable(false, false);
                sqlitedatabase.setReadable(true, true);
                sqlitedatabase.setWritable(true, true);
                return;
            }
        }

        public final void onOpen(SQLiteDatabase sqlitedatabase)
        {
            boolean flag1 = false;
            if (tablePresent("hits2", sqlitedatabase)) goto _L2; else goto _L1
_L1:
            sqlitedatabase.execSQL(PersistentAnalyticsStore.CREATE_HITS_TABLE);
_L6:
            return;
_L2:
            Cursor cursor;
            HashSet hashset;
            cursor = sqlitedatabase.rawQuery("SELECT * FROM hits2 WHERE 0", null);
            hashset = new HashSet();
            String as[] = cursor.getColumnNames();
            int i = 0;
_L4:
            if (i >= as.length)
            {
                break; /* Loop/switch isn't completed */
            }
            hashset.add(as[i]);
            i++;
            if (true) goto _L4; else goto _L3
_L3:
            cursor.close();
            if (!hashset.remove("hit_id") || !hashset.remove("hit_url") || !hashset.remove("hit_string") || !hashset.remove("hit_time"))
            {
                throw new SQLiteException("Database column missing");
            }
            break MISSING_BLOCK_LABEL_145;
            sqlitedatabase;
            cursor.close();
            throw sqlitedatabase;
            boolean flag = flag1;
            if (!hashset.remove("hit_app_id"))
            {
                flag = true;
            }
            if (!hashset.isEmpty())
            {
                throw new SQLiteException("Database has extra columns");
            }
            if (flag)
            {
                sqlitedatabase.execSQL("ALTER TABLE hits2 ADD COLUMN hit_app_id");
                return;
            }
            if (true) goto _L6; else goto _L5
_L5:
        }

        public final void onUpgrade(SQLiteDatabase sqlitedatabase, int i, int j)
        {
        }

        AnalyticsDatabaseHelper(Context context, String s)
        {
            this$0 = PersistentAnalyticsStore.this;
            super(context, s, null, 1);
            mLastDatabaseCheckTime = 0L;
        }
    }


    private class _cls2
        implements HttpClientFactory
    {

        public final HttpClient newInstance()
        {
            return new DefaultHttpClient();
        }

        _cls2()
        {
        }
    }

}
