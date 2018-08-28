// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.PointF;
import android.util.Property;

final class ewBounds extends Property
{

    public final volatile Object get(Object obj)
    {
        return null;
    }

    public final void set(Object obj, Object obj1)
    {
        obj = (ewBounds)obj;
        obj1 = (PointF)obj1;
        obj.mLeft = Math.round(((PointF) (obj1)).x);
        obj.mTop = Math.round(((PointF) (obj1)).y);
        obj.mTopLeftCalls = ((ewBounds) (obj)).mTopLeftCalls + 1;
        if (((ewBounds) (obj)).mTopLeftCalls == ((ewBounds) (obj)).mBottomRightCalls)
        {
            ((ewBounds) (obj)).setLeftTopRightBottom();
        }
    }

    ewBounds(Class class1, String s)
    {
        super(class1, s);
    }
}
