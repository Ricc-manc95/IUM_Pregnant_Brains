// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;


// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip

final class arg._cls1
    implements Runnable
{

    private final Chip arg$1;

    public final void run()
    {
        Chip chip = arg$1;
        chip.actionListener.onChipPrimaryAction(chip);
        chip.scheduledPrimaryAction = null;
    }

    Listener(Chip chip)
    {
        arg$1 = chip;
    }
}
