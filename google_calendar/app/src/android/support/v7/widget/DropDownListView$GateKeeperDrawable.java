// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v7.graphics.drawable.DrawableWrapper;

final class mEnabled extends DrawableWrapper
{

    public boolean mEnabled;

    public final void draw(Canvas canvas)
    {
        if (mEnabled)
        {
            super.draw(canvas);
        }
    }

    public final void setHotspot(float f, float f1)
    {
        if (mEnabled)
        {
            super.setHotspot(f, f1);
        }
    }

    public final void setHotspotBounds(int i, int j, int k, int l)
    {
        if (mEnabled)
        {
            super.setHotspotBounds(i, j, k, l);
        }
    }

    public final boolean setState(int ai[])
    {
        if (mEnabled)
        {
            return super.setState(ai);
        } else
        {
            return false;
        }
    }

    public final boolean setVisible(boolean flag, boolean flag1)
    {
        if (mEnabled)
        {
            return super.setVisible(flag, flag1);
        } else
        {
            return false;
        }
    }

    (Drawable drawable)
    {
        super(drawable);
        mEnabled = true;
    }
}
