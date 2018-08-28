// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.util.Property;

final class mBounds extends Property
{

    private Rect mBounds;

    public final Object get(Object obj)
    {
        ((Drawable)obj).copyBounds(mBounds);
        return new PointF(mBounds.left, mBounds.top);
    }

    public final void set(Object obj, Object obj1)
    {
        obj = (Drawable)obj;
        obj1 = (PointF)obj1;
        ((Drawable) (obj)).copyBounds(mBounds);
        mBounds.offsetTo(Math.round(((PointF) (obj1)).x), Math.round(((PointF) (obj1)).y));
        ((Drawable) (obj)).setBounds(mBounds);
    }

    (Class class1, String s)
    {
        super(class1, s);
        mBounds = new Rect();
    }
}
