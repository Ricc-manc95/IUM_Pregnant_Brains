// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.phenotype.client;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

// Referenced classes of package com.google.android.libraries.phenotype.client:
//            PhenotypeFlag, ConfigurationUpdatedListener

public final class ConfigurationContentLoader
{

    private static final String COLUMNS[] = {
        "key", "value"
    };
    public static final ConcurrentHashMap LOADERS_BY_URI = new ConcurrentHashMap();
    public final Object cacheLock = new Object();
    public volatile Map cachedFlags;
    private final List listeners = new ArrayList();
    private final Object listenersLock = new Object();
    public final ContentObserver observer = new _cls1(null);
    public final ContentResolver resolver;
    public final Uri uri;

    ConfigurationContentLoader(ContentResolver contentresolver, Uri uri1)
    {
        resolver = contentresolver;
        uri = uri1;
    }

    private final Map readFlagsFromContentProvider()
    {
        Object obj;
        Object obj1;
        obj1 = new HashMap();
        obj = resolver.query(uri, COLUMNS, null, null, null);
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_89;
        }
        for (; ((Cursor) (obj)).moveToNext(); ((Map) (obj1)).put(((Cursor) (obj)).getString(0), ((Cursor) (obj)).getString(1))) { }
        break MISSING_BLOCK_LABEL_83;
        obj1;
        try
        {
            ((Cursor) (obj)).close();
            throw obj1;
        }
        // Misplaced declaration of an exception variable
        catch (Object obj) { }
        catch (SQLiteException sqliteexception) { }
        Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
        return null;
        ((Cursor) (obj)).close();
        return ((Map) (obj1));
    }

    public final Map getFlags()
    {
        Object obj;
        Map map;
        Map map1;
        if (PhenotypeFlag.getGservicesBoolean("gms:phenotype:phenotype_flag:debug_disable_caching", false))
        {
            map = readFlagsFromContentProvider();
        } else
        {
            map = cachedFlags;
        }
        map1 = map;
        if (map != null)
        {
            break MISSING_BLOCK_LABEL_52;
        }
        obj = cacheLock;
        obj;
        JVM INSTR monitorenter ;
        map1 = cachedFlags;
        map = map1;
        if (map1 != null)
        {
            break MISSING_BLOCK_LABEL_48;
        }
        map = readFlagsFromContentProvider();
        cachedFlags = map;
        obj;
        JVM INSTR monitorexit ;
        map1 = map;
        Exception exception;
        if (map1 != null)
        {
            return map1;
        } else
        {
            return Collections.emptyMap();
        }
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    final void notifyConfigurationUpdatedListeners()
    {
        Object obj = listenersLock;
        obj;
        JVM INSTR monitorenter ;
        for (Iterator iterator = listeners.iterator(); iterator.hasNext(); ((ConfigurationUpdatedListener)iterator.next()).onConfigurationUpdated()) { }
        break MISSING_BLOCK_LABEL_48;
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        JVM INSTR monitorexit ;
    }


    private class _cls1 extends ContentObserver
    {

        private final ConfigurationContentLoader this$0;

        public final void onChange(boolean flag)
        {
            ConfigurationContentLoader configurationcontentloader = ConfigurationContentLoader.this;
            synchronized (configurationcontentloader.cacheLock)
            {
                configurationcontentloader.cachedFlags = null;
            }
            notifyConfigurationUpdatedListeners();
            return;
            exception;
            obj;
            JVM INSTR monitorexit ;
            throw exception;
        }

        _cls1(Handler handler)
        {
            this$0 = ConfigurationContentLoader.this;
            super(null);
        }
    }

}
