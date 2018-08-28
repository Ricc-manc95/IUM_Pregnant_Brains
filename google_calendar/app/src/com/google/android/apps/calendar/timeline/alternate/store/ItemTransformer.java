// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.store;

import com.google.android.apps.calendar.timeline.alternate.util.TimeUtils;
import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import java.util.HashMap;
import java.util.Map;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.store:
//            VersionedItem

public final class ItemTransformer
{

    public final ItemAdapter adapter;
    private final Map itemCache = new HashMap();
    public final TimeUtils timeUtils;

    ItemTransformer(ItemAdapter itemadapter, TimeUtils timeutils)
    {
        adapter = itemadapter;
        timeUtils = timeutils;
    }

    final VersionedItem updateVersionedItem(Object obj)
    {
        Object obj1 = adapter.getKey(obj);
        VersionedItem versioneditem = (VersionedItem)itemCache.get(obj1);
        if (versioneditem != null && versioneditem.getItem().equals(obj))
        {
            return versioneditem;
        }
        obj = (new AutoValue_VersionedItem.Builder()).setItem(obj);
        int i;
        if (versioneditem == null)
        {
            i = 0;
        } else
        {
            i = versioneditem.getVersion() + 1;
        }
        obj = ((VersionedItem.Builder) (obj)).setVersion(i).build();
        itemCache.put(obj1, obj);
        return ((VersionedItem) (obj));
    }
}
