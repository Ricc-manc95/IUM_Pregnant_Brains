// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import com.google.android.apps.calendar.graphics.drawable.CustomAlphaDrawable;
import com.google.android.apps.calendar.graphics.drawable.VisibilityDrawable;
import com.google.android.apps.calendar.util.validator.DirectValidator;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            Chip, ChipBackgroundImage

final class arg._cls1
    implements Runnable
{

    private final Chip arg$1;

    public final void run()
    {
        Object obj1 = arg$1;
        Object obj;
        DirectValidator directvalidator;
        int i;
        boolean flag;
        if (((Chip) (obj1)).backgroundImage.reusableBitmap != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0 && !((Chip) (obj1)).isFocused)
        {
            i = 255 - Math.round(((Chip) (obj1)).backgroundImageAlpha * 255F);
        } else
        {
            i = 255;
        }
        obj = ((Chip) (obj1)).solidBackgroundCustomAlphaDrawable;
        obj.customAlpha = i;
        ((CustomAlphaDrawable) (obj)).actualAlphaValidator.isValid = false;
        ((CustomAlphaDrawable) (obj)).invalidateSelf();
        obj = ((Chip) (obj1)).backgroundImageVisibilityDrawable;
        obj1 = ((Chip) (obj1)).solidBackgroundCustomAlphaDrawable;
        directvalidator = ((CustomAlphaDrawable) (obj1)).actualAlphaValidator;
        if (!directvalidator.isValid)
        {
            directvalidator.validateRunnable.run();
            directvalidator.isValid = true;
        }
        if (((CustomAlphaDrawable) (obj1)).actualAlpha == 255)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        obj.isHidden = flag;
    }

    lityDrawable(Chip chip)
    {
        arg$1 = chip;
    }
}
