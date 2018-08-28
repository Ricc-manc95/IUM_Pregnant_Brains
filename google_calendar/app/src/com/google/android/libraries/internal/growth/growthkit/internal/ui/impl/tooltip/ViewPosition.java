// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.tooltip;

import android.graphics.Rect;

public final class ViewPosition
{

    public final Rect clipRect = new Rect();
    public final Rect viewRect = new Rect();

    public ViewPosition()
    {
        viewRect.set(0, 0, 0, 0);
        clipRect.set(0, 0, 0x3fffffff, 0x3fffffff);
    }

    public final boolean equals(Object obj)
    {
        if (this != obj)
        {
            if (obj == null || getClass() != obj.getClass())
            {
                return false;
            }
            obj = (ViewPosition)obj;
            if (!viewRect.equals(((ViewPosition) (obj)).viewRect) || !clipRect.equals(((ViewPosition) (obj)).clipRect))
            {
                return false;
            }
        }
        return true;
    }

    public final int hashCode()
    {
        return viewRect.hashCode() * 31 + clipRect.hashCode();
    }

    public final boolean isVisible()
    {
        boolean flag1 = false;
        int i = Math.max(viewRect.left, clipRect.left);
        boolean flag = flag1;
        if (Math.max(Math.min(viewRect.right, clipRect.right) - i, 0) > 0)
        {
            int j = Math.max(viewRect.top, clipRect.top);
            flag = flag1;
            if (Math.max(Math.min(viewRect.bottom, clipRect.bottom) - j, 0) > 0)
            {
                flag = true;
            }
        }
        return flag;
    }
}
