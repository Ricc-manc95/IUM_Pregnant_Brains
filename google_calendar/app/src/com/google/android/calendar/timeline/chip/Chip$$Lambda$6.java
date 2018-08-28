// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import com.android.bitmap.RequestKey;
import com.google.android.apps.calendar.util.function.Consumer;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipForegroundDrawable

final class arg._cls1
    implements Consumer
{

    private final ChipForegroundDrawable arg$1;

    public final void accept(Object obj)
    {
        arg$1.setBadge((RequestKey)obj);
    }

    rawable(ChipForegroundDrawable chipforegrounddrawable)
    {
        arg$1 = chipforegrounddrawable;
    }
}
