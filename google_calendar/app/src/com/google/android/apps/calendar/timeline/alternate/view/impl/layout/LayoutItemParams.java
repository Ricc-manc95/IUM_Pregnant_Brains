// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.timeline.alternate.view.impl.layout;

import com.google.android.apps.calendar.timeline.alternate.view.api.ViewMode;
import com.google.android.apps.calendar.timeline.alternate.view.impl.util.Rect;

public final class LayoutItemParams
{

    public boolean animate;
    public long animationDurationMs;
    public boolean canScrollBackward;
    public boolean canScrollForward;
    public final Rect clipRect = new Rect(0, 0, 0, 0);
    public String contentDescription;
    public float elevation;
    public boolean hasAnimationDuration;
    public boolean hasClip;
    public boolean hasElevation;
    public boolean hasParentId;
    public boolean hasTextScale;
    public boolean hasVirtualRect;
    public boolean hasZOrder;
    public int parentId;
    public int position;
    public final Rect rect = new Rect();
    public float textScale;
    public int traversalAfter;
    public int traversalBefore;
    public Type type;
    public ViewMode viewMode;
    public AccessibilityVirtualView.ActionHandler virtualActionHandler;
    public final Rect virtualRect = new Rect(0, 0, 0, 0);
    public int zOrder;

    public LayoutItemParams()
    {
        traversalBefore = -1;
        traversalAfter = -1;
        type = Type.REGULAR;
    }

    public final LayoutItemParams setClip(int i, int j, int k, int l)
    {
        hasClip = true;
        Rect rect1 = clipRect;
        rect1.left = i;
        rect1.top = j;
        rect1.right = k;
        rect1.bottom = l;
        return this;
    }

    public final LayoutItemParams setClip(Rect rect1)
    {
        if (rect1 == null)
        {
            hasClip = false;
            return this;
        } else
        {
            hasClip = true;
            setClip(rect1.left, rect1.top, rect1.right, rect1.bottom);
            return this;
        }
    }

    public final LayoutItemParams setRect(int i, int j, int k, int l)
    {
        Rect rect1 = rect;
        rect1.left = i;
        rect1.top = j;
        rect1.right = k;
        rect1.bottom = l;
        return this;
    }

    public final void setVirtualRect(int i, int j, int k, int l)
    {
        Rect rect1 = virtualRect;
        rect1.left = i;
        rect1.top = j;
        rect1.right = k;
        rect1.bottom = l;
        hasVirtualRect = true;
    }

    private class Type extends Enum
    {

        private static final Type $VALUES[];
        public static final Type DISPLAY_ONLY;
        public static final Type REGULAR;
        public static final Type VIRTUAL_ONLY;

        public static Type[] values()
        {
            return (Type[])$VALUES.clone();
        }

        static 
        {
            REGULAR = new Type("REGULAR", 0);
            VIRTUAL_ONLY = new Type("VIRTUAL_ONLY", 1);
            DISPLAY_ONLY = new Type("DISPLAY_ONLY", 2);
            $VALUES = (new Type[] {
                REGULAR, VIRTUAL_ONLY, DISPLAY_ONLY
            });
        }

        private Type(String s, int i)
        {
            super(s, i);
        }
    }

}
