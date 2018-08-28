// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnOnDragListener

final class arg._cls1
    implements Consumer
{

    private final ColumnOnDragListener arg$1;

    public final void accept(Object obj)
    {
        ColumnOnDragListener columnondraglistener = arg$1;
        obj = (com.google.android.apps.calendar.timeline.alternate.store.action)obj;
        if (columnondraglistener.storeBlockingFuture != null)
        {
            ((com.google.android.apps.calendar.timeline.alternate.store.action) (obj)).blockUpdates(columnondraglistener.storeBlockingFuture);
        }
    }

    (ColumnOnDragListener columnondraglistener)
    {
        arg$1 = columnondraglistener;
    }
}
