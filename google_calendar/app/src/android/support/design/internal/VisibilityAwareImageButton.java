// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.design.internal;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageButton;

public class VisibilityAwareImageButton extends ImageButton
{

    public int userSetVisibility;

    public VisibilityAwareImageButton(Context context)
    {
        this(context, null);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0);
    }

    public VisibilityAwareImageButton(Context context, AttributeSet attributeset, int i)
    {
        super(context, attributeset, i);
        userSetVisibility = getVisibility();
    }

    public final void internalSetVisibility(int i, boolean flag)
    {
        super.setVisibility(i);
        if (flag)
        {
            userSetVisibility = i;
        }
    }

    public void setVisibility(int i)
    {
        super.setVisibility(i);
        userSetVisibility = i;
    }
}
