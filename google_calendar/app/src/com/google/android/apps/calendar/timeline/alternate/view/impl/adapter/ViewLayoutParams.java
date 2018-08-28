// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.adapter;

import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;

public interface ViewLayoutParams
{

    public abstract float getTextScale();

    public abstract ViewMode getViewMode();

    public abstract int getZOrder();

    public abstract boolean hasTextScale();

    public abstract boolean hasZOrder();
}
