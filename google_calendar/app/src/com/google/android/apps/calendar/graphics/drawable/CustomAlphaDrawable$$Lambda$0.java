// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.graphics.drawable;

import android.graphics.drawable.Drawable;

// Referenced classes of package com.google.android.apps.calendar.graphics.drawable:
//            CustomAlphaDrawable

final class arg._cls1
    implements Runnable
{

    private final CustomAlphaDrawable arg$1;

    public final void run()
    {
        CustomAlphaDrawable customalphadrawable = arg$1;
        customalphadrawable.actualAlpha = (customalphadrawable.alpha * customalphadrawable.customAlpha) / 255;
        customalphadrawable.wrappedDrawable.setAlpha(customalphadrawable.actualAlpha);
    }

    (CustomAlphaDrawable customalphadrawable)
    {
        arg$1 = customalphadrawable;
    }
}
