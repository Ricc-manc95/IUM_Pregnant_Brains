// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter:
//            AllDayMoreViewHolder

final class arg._cls1
    implements Consumer
{

    private final AllDayMoreViewHolder arg$1;

    public final void accept(Object obj)
    {
        arg$1.update();
    }

    (AllDayMoreViewHolder alldaymoreviewholder)
    {
        arg$1 = alldaymoreviewholder;
    }
}
