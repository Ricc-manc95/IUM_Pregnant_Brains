// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.image;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;

// Referenced classes of package com.google.android.apps.calendar.image:
//            Image

public final class arg._cls1
    implements Image
{

    private final int arg$1;

    public final Drawable createDrawable(Resources resources)
    {
        return resources.getDrawable(arg$1);
    }

    public (int i)
    {
        arg$1 = i;
    }
}
