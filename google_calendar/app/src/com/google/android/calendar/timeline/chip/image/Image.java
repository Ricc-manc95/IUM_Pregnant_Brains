// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip.image;

import com.android.bitmap.ReusableBitmap;
import com.google.android.apps.calendar.graphics.RtlMirroring;

public abstract class Image
{

    public Image()
    {
    }

    public abstract ReusableBitmap reusableBitmap();

    public abstract RtlMirroring rtlMirroring();
}
