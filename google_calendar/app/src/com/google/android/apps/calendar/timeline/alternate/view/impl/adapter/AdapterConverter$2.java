// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            Packer, AdapterConverter

final class val.placements extends Packer
{

    private final AdapterConverter this$0;
    private final Map val$placements;

    protected final void assignSlot$5166KOBMC4NMOOBECSNKUOJACLHN8EQ9954IILG_0(Object obj, int i)
    {
        val$placements.put(adapter.getKey(obj), Integer.valueOf(i));
    }

    protected final long getEnd(Object obj)
    {
        if (isTimedEvent(obj))
        {
            return (long)adapter.getStartDay(obj) + 1L;
        } else
        {
            return (long)adapter.getEndDay(obj) + 1L;
        }
    }

    protected final long getStart(Object obj)
    {
        return (long)adapter.getStartDay(obj);
    }

    ()
    {
        this$0 = final_adapterconverter;
        val$placements = Map.this;
        super();
    }
}
