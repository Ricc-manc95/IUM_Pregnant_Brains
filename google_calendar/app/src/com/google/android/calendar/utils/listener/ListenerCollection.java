// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.listener;

import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.config.feature.FeatureConfig;
import com.google.android.apps.calendar.config.feature.Features;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;

public class ListenerCollection
    implements Iterable
{
    final class IteratorImpl
        implements ReleasableIterator
    {

        private final Collection collection;
        private final Iterator impl;
        private boolean released;
        private final ListenerCollection this$0;

        private final boolean checkNextAndRelease()
        {
            boolean flag = impl.hasNext();
            if (!flag && !released)
            {
                released = true;
                if (collection == listeners)
                {
                    Object obj = ListenerCollection.this;
                    obj.inUseCounter = ((ListenerCollection) (obj)).inUseCounter - 1;
                    obj = Features.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                    }
                    ((FeatureConfig)obj).dogfood_features();
                }
            }
            return flag;
        }

        public final boolean hasNext()
        {
            boolean flag = impl.hasNext();
            if (!flag && !released)
            {
                released = true;
                if (collection == listeners)
                {
                    Object obj = ListenerCollection.this;
                    obj.inUseCounter = ((ListenerCollection) (obj)).inUseCounter - 1;
                    obj = Features.instance;
                    if (obj == null)
                    {
                        throw new NullPointerException(String.valueOf("Need to call Feature.set() first"));
                    }
                    ((FeatureConfig)obj).dogfood_features();
                }
            }
            return flag;
        }

        public final Object next()
        {
            checkNextAndRelease();
            return impl.next();
        }

        public final void remove()
        {
            throw new UnsupportedOperationException();
        }

        IteratorImpl(Collection collection1)
        {
            this$0 = ListenerCollection.this;
            super();
            released = false;
            collection = collection1;
            impl = collection1.iterator();
            inUseCounter = inUseCounter + 1;
        }
    }

    public static interface ReleasableIterator
        extends Iterator
    {
    }


    private static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/listener/ListenerCollection);
    private final int collectionType;
    public int inUseCounter;
    public Collection listeners;

    public ListenerCollection()
    {
        this(0);
    }

    public ListenerCollection(int i)
    {
        inUseCounter = 0;
        collectionType = i;
        collectionType;
        JVM INSTR tableswitch 0 1: default 40
    //                   0 50
    //                   1 64;
           goto _L1 _L2 _L3
_L1:
        throw new IllegalStateException("Unknown collection type");
_L2:
        Object obj = new ArrayList();
_L5:
        listeners = ((Collection) (obj));
        return;
_L3:
        obj = new HashSet();
        if (true) goto _L5; else goto _L4
_L4:
    }

    public final void cloneCollectionIfNeeded()
    {
        Object obj;
        boolean flag;
        if (inUseCounter > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L2; else goto _L1
_L1:
        LogUtils.d(TAG, "Copying listeners", new Object[0]);
        obj = listeners;
        collectionType;
        JVM INSTR tableswitch 0 1: default 56
    //                   0 71
    //                   1 91;
           goto _L3 _L4 _L5
_L3:
        throw new IllegalStateException("Unknown collection type");
_L4:
        obj = new ArrayList(((Collection) (obj)));
_L7:
        listeners = ((Collection) (obj));
        inUseCounter = 0;
_L2:
        return;
_L5:
        obj = new HashSet(((Collection) (obj)));
        if (true) goto _L7; else goto _L6
_L6:
    }

    public Iterator iterator()
    {
        return new IteratorImpl(listeners);
    }

}
