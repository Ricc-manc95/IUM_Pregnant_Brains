// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timeline.dnd;

import android.graphics.Rect;

final class DragAreaEdges
{

    public final Rect boundaries;
    public final float edgeSize;

    DragAreaEdges(Rect rect, int i)
    {
        boundaries = rect;
        edgeSize = i;
    }
}
