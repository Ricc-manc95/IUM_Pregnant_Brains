// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import com.google.android.apps.calendar.util.concurrent.ObservableReference;
import com.google.common.base.Optional;
import com.google.common.base.Present;
import com.google.common.util.concurrent.ListenableFuture;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column:
//            ColumnOnDragListener, ColumnViewportController, ColumnDragState, ColumnViewport

final class pageFuture
    implements com.google.android.calendar.timeline.dnd.r.Delegate
{

    private ListenableFuture pageFuture;
    private final ColumnOnDragListener this$0;

    public final void onPage(int i)
    {
        Object obj;
        if (dragMode == com.google.android.apps.calendar.timeline.alternate.view.api.e.MOVE)
        {
            if (((Optional) (obj = (Optional)stateObservable.get())).isPresent() && pageFuture.isDone())
            {
                Object obj1 = viewportController;
                byte byte0;
                if (((Boolean)isRtl.get()).booleanValue())
                {
                    byte0 = -1;
                } else
                {
                    byte0 = 1;
                }
                pageFuture = ((ColumnViewportController) (obj1)).animateDragPage(byte0 * i);
                obj1 = stateObservable;
                obj = ((ColumnDragState)((Optional) (obj)).get()).toBuilder().pagingDirection(i).build();
                if (obj == null)
                {
                    throw new NullPointerException();
                } else
                {
                    ((ObservableReference) (obj1)).set(new Present(obj));
                    return;
                }
            }
        }
    }

    public final void onPageModeEnd()
    {
        Object obj;
        if (dragMode == com.google.android.apps.calendar.timeline.alternate.view.api.e.MOVE)
        {
            if (((Optional) (obj = (Optional)stateObservable.get())).isPresent())
            {
                ObservableReference observablereference = stateObservable;
                obj = ((ColumnDragState)((Optional) (obj)).get()).toBuilder().pagingDirection(0).build();
                if (obj == null)
                {
                    throw new NullPointerException();
                } else
                {
                    observablereference.set(new Present(obj));
                    return;
                }
            }
        }
    }

    public final void onPageModeStart()
    {
    }

    public final void onScroll(int i)
    {
        ColumnViewportController columnviewportcontroller = viewportController;
        i = -i;
        ColumnViewport columnviewport = columnviewportcontroller.viewport;
        int j = columnviewportcontroller.viewport.gridTopMsOfDay;
        columnviewport.setGridTopMsOfDay(columnviewportcontroller.viewport.gridMsPerVerticalPixel * i + j);
    }

    public final void onScrollModeEnd()
    {
        Object obj = (Optional)stateObservable.get();
        if (((Optional) (obj)).isPresent())
        {
            ObservableReference observablereference = stateObservable;
            obj = ((ColumnDragState)((Optional) (obj)).get()).toBuilder().scrolling(false).build();
            if (obj == null)
            {
                throw new NullPointerException();
            }
            observablereference.set(new Present(obj));
        }
    }

    public final void onScrollModeStart()
    {
        Object obj = (Optional)stateObservable.get();
        if (((Optional) (obj)).isPresent())
        {
            ObservableReference observablereference = stateObservable;
            obj = ((ColumnDragState)((Optional) (obj)).get()).toBuilder().scrolling(true).build();
            if (obj == null)
            {
                throw new NullPointerException();
            }
            observablereference.set(new Present(obj));
        }
    }

    ()
    {
        this$0 = ColumnOnDragListener.this;
        super();
        columnondraglistener = ((ColumnOnDragListener) (new Object()));
        if (ColumnOnDragListener.this == null)
        {
            columnondraglistener = com.google.common.util.concurrent.teSuccessfulFuture.NULL;
        } else
        {
            columnondraglistener = new com.google.common.util.concurrent.teSuccessfulFuture(ColumnOnDragListener.this);
        }
        pageFuture = ColumnOnDragListener.this;
    }
}
