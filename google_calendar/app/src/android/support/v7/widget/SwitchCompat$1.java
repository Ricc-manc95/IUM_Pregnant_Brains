// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.util.Property;

// Referenced classes of package android.support.v7.widget:
//            SwitchCompat

final class  extends Property
{

    public final Object get(Object obj)
    {
        return Float.valueOf(((SwitchCompat)obj).mThumbPosition);
    }

    public final void set(Object obj, Object obj1)
    {
        obj = (SwitchCompat)obj;
        obj.mThumbPosition = ((Float)obj1).floatValue();
        ((SwitchCompat) (obj)).invalidate();
    }

    (Class class1, String s)
    {
        super(class1, s);
    }
}
