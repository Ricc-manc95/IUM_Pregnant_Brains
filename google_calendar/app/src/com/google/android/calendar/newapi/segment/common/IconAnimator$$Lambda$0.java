// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.common;

import android.widget.ImageView;

final class arg._cls1
    implements Runnable
{

    private final ImageView arg$1;

    public final void run()
    {
        arg$1.clearColorFilter();
    }

    (ImageView imageview)
    {
        arg$1 = imageview;
    }
}
