// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.animation;

import android.util.Property;

public final class Properties
{

    public static final Property STATUS_BAR_COLOR = new _cls4(java/lang/Integer, "statusBarColor");
    public static final Property VIEW_ALPHA = new _cls1(java/lang/Float, "alpha");
    public static final Property VIEW_LEFT = new _cls2(java/lang/Integer, "left");
    public static final Property VIEW_RIGHT = new _cls3(java/lang/Integer, "right");


    private class _cls1 extends Property
    {

        public final Object get(Object obj)
        {
            return Float.valueOf(((View)obj).getAlpha());
        }

        public final void set(Object obj, Object obj1)
        {
            ((View)obj).setAlpha(((Float)obj1).floatValue());
        }

        _cls1(Class class1, String s)
        {
            super(class1, s);
        }
    }


    private class _cls2 extends Property
    {

        public final Object get(Object obj)
        {
            return Integer.valueOf(((View)obj).getLeft());
        }

        public final void set(Object obj, Object obj1)
        {
            ((View)obj).setLeft(((Integer)obj1).intValue());
        }

        _cls2(Class class1, String s)
        {
            super(class1, s);
        }
    }


    private class _cls3 extends Property
    {

        public final Object get(Object obj)
        {
            return Integer.valueOf(((View)obj).getRight());
        }

        public final void set(Object obj, Object obj1)
        {
            ((View)obj).setRight(((Integer)obj1).intValue());
        }

        _cls3(Class class1, String s)
        {
            super(class1, s);
        }
    }


    private class _cls4 extends Property
    {

        public final Object get(Object obj)
        {
            return Integer.valueOf(((Window)obj).getStatusBarColor());
        }

        public final void set(Object obj, Object obj1)
        {
            ((Window)obj).setStatusBarColor(((Integer)obj1).intValue());
        }

        _cls4(Class class1, String s)
        {
            super(class1, s);
        }
    }

}
