// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.transition;

import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.Property;
import android.view.View;

final class  extends Property
{

    public final Object get(Object obj)
    {
        return ViewCompat.getClipBounds((View)obj);
    }

    public final void set(Object obj, Object obj1)
    {
        ViewCompat.setClipBounds((View)obj, (Rect)obj1);
    }

    (Class class1, String s)
    {
        super(class1, s);
    }
}
