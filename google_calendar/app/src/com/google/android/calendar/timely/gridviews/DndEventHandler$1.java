// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.view.View;
import java.util.List;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            DndEventHandler

final class this._cls0
    implements android.view.ChangeListener
{

    private final DndEventHandler this$0;

    public final void onViewAttachedToWindow(View view)
    {
        activateTarget((dTarget)view);
    }

    public final void onViewDetachedFromWindow(View view)
    {
        DndEventHandler dndeventhandler = DndEventHandler.this;
        dTarget dtarget = (dTarget)view;
        dndeventhandler.dndTargets.remove(dtarget);
        if (dndeventhandler.isDragInProgress)
        {
            dndeventhandler._flddelegate.onTargetDeactivated(dtarget);
        }
        view.removeOnAttachStateChangeListener(targetDetachListener);
        view.removeOnLayoutChangeListener(targetLayoutListener);
    }

    legate()
    {
        this$0 = DndEventHandler.this;
        super();
    }
}
