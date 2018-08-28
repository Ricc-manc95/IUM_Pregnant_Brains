// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class UnPressableLinearLayout extends LinearLayout
{

    public UnPressableLinearLayout(Context context)
    {
        this(context, null);
    }

    public UnPressableLinearLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    protected void dispatchSetPressed(boolean flag)
    {
    }
}
