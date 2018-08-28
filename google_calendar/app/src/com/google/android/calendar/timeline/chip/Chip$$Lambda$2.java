// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.view.View;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip

final class arg._cls1
    implements android.view.tener
{

    private final Chip arg$1;

    public final void onClick(View view)
    {
        view = arg$1;
        ((Chip) (view)).actionListener.onChipPrimaryAction(view);
    }

    Listener(Chip chip)
    {
        arg$1 = chip;
    }
}
