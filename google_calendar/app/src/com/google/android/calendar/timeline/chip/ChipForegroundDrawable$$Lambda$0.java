// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip;

import com.android.bitmap.drawable.BasicBitmapDrawable;
import com.google.android.calendar.common.drawable.DefaultableBitmapDrawable;

final class ner
    implements com.google.android.calendar.common.drawable.r
{

    public static final com.google.android.calendar.common.drawable.r $instance = new <init>();

    public final void onEmptyBitmapSet(DefaultableBitmapDrawable defaultablebitmapdrawable)
    {
        ((BasicBitmapDrawable)defaultablebitmapdrawable).unbind();
    }


    private ner()
    {
    }
}
