// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.widget;

import android.content.Context;
import android.util.AttributeSet;

public class FloatingActionButton extends android.support.design.floatingactionbutton.FloatingActionButton
{
    public static class Behavior extends android.support.design.floatingactionbutton.BaseBehavior
    {

        public Behavior()
        {
        }

        public Behavior(Context context, AttributeSet attributeset)
        {
            super(context, attributeset);
        }
    }


    public FloatingActionButton(Context context)
    {
        super(context);
    }

    public FloatingActionButton(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public FloatingActionButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
    }
}
