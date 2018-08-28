// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.fragment.impl;

import android.graphics.Point;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timeline.chip.Chip;

final class arg._cls1
    implements com.google.android.calendar.timeline.chip.emViewFactory..Lambda._cls0
{

    private final Consumer arg$1;

    public final boolean onChipLongPress(Chip chip, Point point)
    {
        arg$1.accept(com.google.android.apps.calendar.timeline.alternate.view.api.da._cls0.arg._fld1);
        return true;
    }

    (Consumer consumer)
    {
        arg$1 = consumer;
    }
}
