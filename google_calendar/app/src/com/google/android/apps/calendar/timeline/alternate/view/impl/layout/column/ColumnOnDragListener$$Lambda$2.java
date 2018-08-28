// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.timeline.alternate.view.api.ItemAdapter;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Function;
import com.google.common.base.Present;
import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnOnDragListener

final class arg._cls5
    implements Consumer
{

    private final ColumnOnDragListener arg$1;
    private final Object arg$2;
    private final long arg$3;
    private final SettableFuture arg$4;
    private final Function arg$5;

    public final void accept(Object obj)
    {
        ColumnOnDragListener columnondraglistener = arg$1;
        Object obj1 = arg$2;
        long l = arg$3;
        SettableFuture settablefuture = arg$4;
        Function function = arg$5;
        obj = (com.google.android.apps.calendar.timeline.alternate.store.action)obj;
        ((com.google.android.apps.calendar.timeline.alternate.store.action) (obj)).removeItem(obj1);
        ((com.google.android.apps.calendar.timeline.alternate.store.action) (obj)).addItem(columnondraglistener.itemAdapter.moveTime(obj1, l));
        obj = Long.valueOf(l);
        if (obj == null)
        {
            throw new NullPointerException();
        } else
        {
            settablefuture.setFuture((ListenableFuture)function.apply(new Present(obj)));
            return;
        }
    }

    (ColumnOnDragListener columnondraglistener, Object obj, long l, SettableFuture settablefuture, Function function)
    {
        arg$1 = columnondraglistener;
        arg$2 = obj;
        arg$3 = l;
        arg$4 = settablefuture;
        arg$5 = function;
    }
}
