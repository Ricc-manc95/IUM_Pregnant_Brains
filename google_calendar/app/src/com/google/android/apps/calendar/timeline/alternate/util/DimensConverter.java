// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.util;


public interface DimensConverter
{

    public abstract float dpToPx(float f);

    public abstract int getPixelOffset(float f);

    public abstract int getPixelSize(float f);

    public abstract float spToPx(float f);
}
