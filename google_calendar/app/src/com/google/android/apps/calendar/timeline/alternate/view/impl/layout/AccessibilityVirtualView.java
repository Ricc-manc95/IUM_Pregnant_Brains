// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;


public abstract class AccessibilityVirtualView
{

    public AccessibilityVirtualView()
    {
    }

    public abstract ActionHandler actionHandler();

    public abstract int bottom();

    public abstract Boolean canScrollBackward();

    public abstract Boolean canScrollForward();

    public abstract Runnable clickHandler();

    public abstract CharSequence contentDescription();

    public abstract int id();

    public abstract int left();

    public abstract Integer parentId();

    public abstract int right();

    public abstract int top();

    public abstract Integer traversalAfter();

    public abstract Integer traversalBefore();

    public abstract LayoutItemParams.Type type();

    public abstract int zOrder();
}
