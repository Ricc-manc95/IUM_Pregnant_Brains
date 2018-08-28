// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.graphics.Color;
import com.google.android.calendar.utils.ColorUtils;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipViewModel

public final class GhostChipUtils
{

    private static int modifyColor(int i, boolean flag)
    {
        if (flag)
        {
            i = ColorUtils.blend(i, -1, 0.4F);
            return Color.argb((int)((float)Color.alpha(i) * 0.8F), Color.red(i), Color.green(i), Color.blue(i));
        } else
        {
            return Color.argb((int)((float)Color.alpha(i) * 0.8F), Color.red(i), Color.green(i), Color.blue(i));
        }
    }

    public static ChipViewModel toGhostChip(ChipViewModel chipviewmodel, boolean flag)
    {
        int i = modifyColor(chipviewmodel.getBackgroundColor(), flag);
        int j = modifyColor(chipviewmodel.getForegroundColor(), flag);
        int k = modifyColor(chipviewmodel.getBorderColor(), flag);
        return chipviewmodel.toBuilder().setBackgroundColor(i).setForegroundColor(j).setBorderColor(k).setOuterBorderColor(0).build();
    }
}
