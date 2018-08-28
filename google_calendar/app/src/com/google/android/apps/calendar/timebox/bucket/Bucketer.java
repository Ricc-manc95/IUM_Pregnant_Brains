// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timebox.bucket;

import android.util.SparseArray;
import com.google.android.apps.calendar.timebox.TimeRangeEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// Referenced classes of package com.google.android.apps.calendar.timebox.bucket:
//            TypeBucketer

public final class Bucketer
{

    private final BucketerConfig config;
    private final ArrayList items;

    private Bucketer(BucketerConfig bucketerconfig, int i)
    {
        items = new ArrayList(i);
        config = bucketerconfig;
    }

    public static List bucket(BucketerConfig bucketerconfig, List list)
    {
        bucketerconfig = new Bucketer(bucketerconfig, list.size());
        list = list.iterator();
        do
        {
            if (!list.hasNext())
            {
                break;
            }
            Object obj = (TimeRangeEntry)list.next();
            TypeBucketer typebucketer = ((Bucketer) (bucketerconfig)).config.getBucketer(((TimeRangeEntry) (obj)));
            if (typebucketer == null)
            {
                ((Bucketer) (bucketerconfig)).items.add(obj);
            } else
            {
                int i = typebucketer.hashEntry(((TimeRangeEntry) (obj)));
                Object obj1 = typebucketer.items.get(i);
                if (obj1 == null)
                {
                    obj = typebucketer.newBucket(((TimeRangeEntry) (obj)));
                    if (obj != null)
                    {
                        typebucketer.items.put(i, obj);
                    }
                } else
                {
                    typebucketer.addToBucket(obj1, ((TimeRangeEntry) (obj)));
                }
            }
        } while (true);
        list = ((Bucketer) (bucketerconfig)).config.getBucketers();
        bucketerconfig = new ArrayList(((Bucketer) (bucketerconfig)).items);
        ArrayList arraylist;
        for (list = list.iterator(); list.hasNext(); bucketerconfig.addAll(arraylist))
        {
            TypeBucketer typebucketer1 = (TypeBucketer)list.next();
            arraylist = new ArrayList(typebucketer1.items.size());
            for (int j = 0; j < typebucketer1.items.size(); j++)
            {
                TimeRangeEntry timerangeentry = typebucketer1.finalizeBucket(typebucketer1.items.valueAt(j));
                if (timerangeentry != null)
                {
                    arraylist.add(timerangeentry);
                }
            }

        }

        return bucketerconfig;
    }

    private class BucketerConfig
    {

        public abstract TypeBucketer getBucketer(TimeRangeEntry timerangeentry);

        public abstract List getBucketers();
    }

}
