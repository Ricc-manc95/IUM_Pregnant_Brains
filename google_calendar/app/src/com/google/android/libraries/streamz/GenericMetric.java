// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.streamz;

import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.streamz:
//            CellFieldTuple, MetricConfigProvider, CellValue, IncrementListener, 
//            Field

public abstract class GenericMetric
{

    private Map cellMap;
    public final Field fields[];
    private final Object lock;
    private final MetricConfigProvider metricConfigProvider;
    public final String name;
    private int updates;

    transient GenericMetric(String s, MetricConfigProvider metricconfigprovider, Field afield[])
    {
        byte byte0;
        if (afield.length > 0)
        {
            byte0 = 10;
        } else
        {
            byte0 = 1;
        }
        if (s == null)
        {
            throw new NullPointerException();
        }
        name = (String)s;
        fields = afield;
        cellMap = new HashMap(byte0);
        if (afield.length == 0)
        {
            cellMap.put(CellFieldTuple.NO_FIELDS_TUPLE, newCellValue());
        }
        updates = 0;
        if (metricconfigprovider == null)
        {
            throw new NullPointerException();
        } else
        {
            metricConfigProvider = (MetricConfigProvider)metricconfigprovider;
            lock = new Object();
            return;
        }
    }

    public final void doRecord(Object obj, CellFieldTuple cellfieldtuple)
    {
        Object obj1 = lock;
        obj1;
        JVM INSTR monitorenter ;
        CellValue cellvalue1 = (CellValue)cellMap.get(cellfieldtuple);
        CellValue cellvalue;
        cellvalue = cellvalue1;
        if (cellvalue1 != null)
        {
            break MISSING_BLOCK_LABEL_49;
        }
        cellvalue = newCellValue();
        cellMap.put(cellfieldtuple, cellvalue);
        cellvalue.record(obj);
        updates = updates + 1;
        obj1;
        JVM INSTR monitorexit ;
        obj = metricConfigProvider.getIncrementListener();
        if (obj != null)
        {
            ((IncrementListener) (obj)).incremented();
        }
        return;
        obj;
        obj1;
        JVM INSTR monitorexit ;
        throw obj;
    }

    final MetricSnapshot getMetricSnapshotAndClear()
    {
        HashMap hashmap;
        MetricSnapshot metricsnapshot;
        byte byte0;
        if (fields.length > 0)
        {
            byte0 = 10;
        } else
        {
            byte0 = 1;
        }
        hashmap = new HashMap(byte0);
        metricsnapshot = new MetricSnapshot(name, fields);
        synchronized (lock)
        {
            metricsnapshot.cellMap = cellMap;
            metricsnapshot.updates = updates;
            cellMap = hashmap;
            updates = 0;
        }
        return metricsnapshot;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
    }

    abstract CellValue newCellValue();

    private class MetricSnapshot
    {

        public Map cellMap;
        public final Field fields[];
        public final String name;
        public int updates;

        transient MetricSnapshot(String s, Field afield[])
        {
            if (s == null)
            {
                throw new NullPointerException();
            } else
            {
                name = (String)s;
                fields = afield;
                return;
            }
        }
    }

}
