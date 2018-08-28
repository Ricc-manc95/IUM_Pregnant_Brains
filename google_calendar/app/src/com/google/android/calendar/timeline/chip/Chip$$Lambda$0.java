// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.view.KeyEvent;
import android.view.View;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip

final class arg._cls1
    implements android.view.ner
{

    private final Chip arg$1;

    public final boolean onKey(View view, int i, KeyEvent keyevent)
    {
        view = arg$1;
        return keyevent.getAction() == 1 && i == 66 && view.performClick();
    }

    (Chip chip)
    {
        arg$1 = chip;
    }
}
