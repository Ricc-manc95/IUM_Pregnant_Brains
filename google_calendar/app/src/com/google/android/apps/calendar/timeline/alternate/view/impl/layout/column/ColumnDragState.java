// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout.column;

import java.util.List;

public abstract class ColumnDragState
{

    public ColumnDragState()
    {
    }

    public abstract long dragTimeFp16();

    public abstract List events();

    public abstract int pagingDirection();

    public abstract List phantoms();

    public abstract boolean scrolling();

    public abstract long startDragTimeFp16();

    abstract Builder toBuilder();
}
