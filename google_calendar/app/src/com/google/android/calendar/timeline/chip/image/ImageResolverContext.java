// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.chip.image;

import android.content.Context;

public abstract class ImageResolverContext
{

    public ImageResolverContext()
    {
    }

    public abstract Context context();

    public abstract int preferredHeight();

    public abstract int preferredWidth();
}
