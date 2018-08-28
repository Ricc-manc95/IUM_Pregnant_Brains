// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import com.google.android.apps.calendar.util.Optionals;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.timeline.chip.image.BackgroundImageViewModel;
import com.google.common.base.Optional;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip

final class arg._cls2
    implements Consumer
{

    private final Chip arg$1;
    private final BackgroundImageViewModel arg$2;

    public final void accept(Object obj)
    {
        Chip chip = arg$1;
        BackgroundImageViewModel backgroundimageviewmodel = arg$2;
        Optionals.ifPresent((Optional)obj, new <init>(chip, backgroundimageviewmodel));
    }

    dImageViewModel(Chip chip, BackgroundImageViewModel backgroundimageviewmodel)
    {
        arg$1 = chip;
        arg$2 = backgroundimageviewmodel;
    }
}
