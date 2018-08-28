// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.util.Property;
import android.view.View;

final class  extends Property
{

    public final Object get(Object obj)
    {
        return Float.valueOf(((View)obj).getAlpha());
    }

    public final void set(Object obj, Object obj1)
    {
        ((View)obj).setAlpha(((Float)obj1).floatValue());
    }

    (Class class1, String s)
    {
        super(class1, s);
    }
}
