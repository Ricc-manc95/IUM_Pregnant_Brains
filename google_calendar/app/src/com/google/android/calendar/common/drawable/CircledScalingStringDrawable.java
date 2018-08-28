// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.drawable;

import android.content.Context;
import android.graphics.Rect;

// Referenced classes of package com.google.android.calendar.common.drawable:
//            CircledStringDrawable

public final class CircledScalingStringDrawable extends CircledStringDrawable
{

    private final float textFillRatio = 0.6666667F;

    public CircledScalingStringDrawable(Context context, String s, int i, int j, float f)
    {
        super(s, i, j);
    }

    protected final void onBoundsChange(Rect rect)
    {
        super.textSize = textFillRatio * (float)Math.min(rect.width(), rect.height());
        invalidateSelf();
    }
}
