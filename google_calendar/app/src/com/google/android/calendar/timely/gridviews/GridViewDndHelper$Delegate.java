// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.gridviews;

import android.view.View;
import com.google.android.calendar.timely.TimelineItem;
import com.google.android.calendar.timely.dnd.DragChipFactory;

public interface 
{

    public abstract boolean startDnd(View view, DragChipFactory dragchipfactory, TimelineItem timelineitem, int i);
}
