// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.timely.gridviews:
//            TentativeChipGestureListener

final class arg._cls1
    implements Consumer
{

    private final TentativeChipGestureListener arg$1;

    public final void accept(Object obj)
    {
        obj = arg$1;
        ((TentativeChipGestureListener) (obj)).actionListener.tion(((TentativeChipGestureListener) (obj)).chip);
    }

    Y(TentativeChipGestureListener tentativechipgesturelistener)
    {
        arg$1 = tentativechipgesturelistener;
    }
}
