// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.common.base.Optional;
import com.google.common.base.Present;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnOnDragListener, ColumnDragState

final class arg._cls1
    implements Consumer
{

    private final ColumnOnDragListener arg$1;

    public final void accept(Object obj)
    {
        obj = arg$1;
        Object obj1 = (Optional)((ColumnOnDragListener) (obj)).stateObservable.get();
        if (((Optional) (obj1)).isPresent())
        {
            obj1 = ((ColumnDragState)((Optional) (obj1)).get()).toBuilder();
            ((ColumnOnDragListener) (obj)).onDragLocationUpdated(((tionUpdated) (obj1)), ((ColumnOnDragListener) (obj)).curX, ((ColumnOnDragListener) (obj)).curY);
            ((ColumnOnDragListener) (obj)).updateDraggedEvents(((gedEvents) (obj1)));
            obj = ((ColumnOnDragListener) (obj)).stateObservable;
            obj1 = ((vable) (obj1)).vable();
            if (obj1 == null)
            {
                throw new NullPointerException();
            }
            ((ObservableReference) (obj)).set(new Present(obj1));
        }
    }

    (ColumnOnDragListener columnondraglistener)
    {
        arg$1 = columnondraglistener;
    }
}
