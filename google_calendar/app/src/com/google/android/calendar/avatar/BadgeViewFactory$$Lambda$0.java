// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.avatar;

import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import com.google.android.apps.calendar.util.function.Consumer;

public final class arg._cls1
    implements Consumer
{

    private final ImageView arg$1;

    public final void accept(Object obj)
    {
        arg$1.setImageDrawable((Drawable)obj);
    }

    public (ImageView imageview)
    {
        arg$1 = imageview;
    }
}
