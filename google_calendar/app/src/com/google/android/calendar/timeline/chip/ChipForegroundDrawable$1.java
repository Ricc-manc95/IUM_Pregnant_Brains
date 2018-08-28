// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import android.graphics.drawable.Drawable;
import com.android.bitmap.drawable.BasicBitmapDrawable;

// Referenced classes of package com.google.android.calendar.timeline.chip:
//            ChipForegroundDrawable

final class this._cls0
    implements android.graphics.drawable.roundDrawable._cls1
{

    private final ChipForegroundDrawable this$0;

    public final void invalidateDrawable(Drawable drawable)
    {
        if (drawable == badgeDrawable)
        {
            drawable = ChipForegroundDrawable.this;
            boolean flag;
            if (((BasicBitmapDrawable) (((ChipForegroundDrawable) (drawable)).badgeDrawable)).mCurrKey != null && ((ChipForegroundDrawable) (drawable)).badgeDrawable.getBitmap() != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                drawable = ChipForegroundDrawable.this;
                drawable.valid = false;
                drawable.invalidateSelf();
                return;
            }
        }
        invalidateSelf();
    }

    public final void scheduleDrawable(Drawable drawable, Runnable runnable, long l)
    {
        scheduleSelf(runnable, l);
    }

    public final void unscheduleDrawable(Drawable drawable, Runnable runnable)
    {
        unscheduleSelf(runnable);
    }

    ()
    {
        this$0 = ChipForegroundDrawable.this;
        super();
    }
}
