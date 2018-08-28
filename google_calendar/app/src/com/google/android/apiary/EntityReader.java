// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apiary;

import android.content.ContentValues;
import android.content.Entity;
import android.content.EntityIterator;
import android.database.DatabaseUtils;
import android.os.Process;
import android.os.RemoteException;
import android.util.Log;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

// Referenced classes of package com.google.android.apiary:
//            ParseException, ItemAndEntityHandler

public final class EntityReader
    implements Runnable
{

    private final EntityItem entityEndMarker;
    private final BlockingQueue entityQueue;
    private final Object entryEndMarker;
    private final BlockingQueue eventQueue;
    public volatile boolean forcedClosed;
    private final ItemAndEntityHandler handler;
    private final String idQueryColumn;
    private final String logTag;
    private final AtomicInteger numRemoteExceptions = new AtomicInteger(0);
    private final String selection;
    public volatile Thread thread;

    public EntityReader(String s, BlockingQueue blockingqueue, Object obj, BlockingQueue blockingqueue1, EntityItem entityitem, ItemAndEntityHandler itemandentityhandler, String s1, 
            String s2)
    {
        logTag = s;
        eventQueue = blockingqueue;
        entityQueue = blockingqueue1;
        entryEndMarker = obj;
        entityEndMarker = entityitem;
        handler = itemandentityhandler;
        selection = s2;
        forcedClosed = false;
        idQueryColumn = s1;
    }

    private final void readBatch(List list)
        throws InterruptedException
    {
        Object obj;
        Object obj1;
        obj1 = (new StringBuilder(idQueryColumn)).append(" in (");
        obj = "";
        Object obj3;
        for (Iterator iterator = list.iterator(); iterator.hasNext(); DatabaseUtils.appendEscapedSQLString(((StringBuilder) (obj1)), handler.itemToSourceId(obj3)))
        {
            obj3 = iterator.next();
            ((StringBuilder) (obj1)).append(((String) (obj)));
            obj = ",";
        }

        ((StringBuilder) (obj1)).append(")");
        if (selection != null)
        {
            ((StringBuilder) (obj1)).append(" AND ").append(selection);
        }
        obj = new HashMap();
        obj1 = handler.newEntityIterator(((StringBuilder) (obj1)).toString(), null, -1);
_L1:
        boolean flag;
        if (forcedClosed || !((EntityIterator) (obj1)).hasNext())
        {
            break MISSING_BLOCK_LABEL_214;
        }
        flag = forcedClosed;
        if (flag)
        {
            Object obj2;
            Entity entity;
            try
            {
                ((EntityIterator) (obj1)).close();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (List list)
            {
                numRemoteExceptions.incrementAndGet();
                return;
            }
            // Misplaced declaration of an exception variable
            catch (List list)
            {
                Log.wtf(logTag, "Error while reading batch", list);
            }
            break MISSING_BLOCK_LABEL_302;
        }
        obj2 = (Entity)((EntityIterator) (obj1)).next();
        ((Map) (obj)).put(((Entity) (obj2)).getEntityValues().getAsString(idQueryColumn), obj2);
          goto _L1
        list;
        ((EntityIterator) (obj1)).close();
        throw list;
        for (list = list.iterator(); list.hasNext(); entityQueue.put(new EntityItem(obj2, entity)))
        {
            obj2 = list.next();
            entity = (Entity)((Map) (obj)).get(handler.itemToSourceId(obj2));
        }

        ((EntityIterator) (obj1)).close();
        return;
    }

    public final void run()
    {
        thread = Thread.currentThread();
        Process.setThreadPriority(10);
        ArrayList arraylist = new ArrayList();
_L9:
        if (forcedClosed) goto _L2; else goto _L1
_L1:
        Object obj1 = eventQueue.take();
        if (obj1 != entryEndMarker) goto _L4; else goto _L3
_L3:
        if (arraylist.isEmpty()) goto _L6; else goto _L5
_L5:
        readBatch(arraylist);
        if (forcedClosed) goto _L2; else goto _L7
_L7:
        arraylist.clear();
_L6:
        entityQueue.put(entityEndMarker);
_L2:
        thread = null;
        if (forcedClosed)
        {
            boolean flag = forcedClosed;
            (new StringBuilder(49)).append("EntityReader thread ended: mForcedClosed is ").append(flag);
        }
_L12:
        return;
_L4:
        arraylist.add(obj1);
        if (arraylist.size() < 10) goto _L9; else goto _L8
_L8:
        readBatch(arraylist);
        if (forcedClosed) goto _L2; else goto _L10
_L10:
        arraylist.clear();
          goto _L9
        Object obj;
        obj;
        thread = null;
        if (!forcedClosed) goto _L12; else goto _L11
_L11:
        boolean flag1 = forcedClosed;
        (new StringBuilder(49)).append("EntityReader thread ended: mForcedClosed is ").append(flag1);
        return;
        obj;
        thread = null;
        if (forcedClosed)
        {
            boolean flag2 = forcedClosed;
            (new StringBuilder(49)).append("EntityReader thread ended: mForcedClosed is ").append(flag2);
        }
        throw obj;
          goto _L9
    }

    private class EntityItem
    {

        public final Entity entity;
        public final Object entry;

        public EntityItem(Object obj, Entity entity1)
        {
            entry = obj;
            entity = entity1;
        }
    }

}
