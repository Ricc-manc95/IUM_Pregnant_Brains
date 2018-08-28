// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view;

import android.content.Context;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.view.accessibility.AccessibilityEvent;

public final class NinjaLinearLayoutManager extends LinearLayoutManager
{

    public NinjaLinearLayoutManager(Context context, int i, boolean flag)
    {
        super(1, false);
    }

    public final void onInitializeAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
    }

    public final void onInitializeAccessibilityNodeInfo(android.support.v7.widget.RecyclerView.Recycler recycler, android.support.v7.widget.RecyclerView.State state, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
    }
}
