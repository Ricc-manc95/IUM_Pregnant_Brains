// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.util.AttributeSet;

// Referenced classes of package android.support.v7.widget:
//            Toolbar

public static final class mMargin extends android.support.v7.app.s
{

    public int mViewType;

    public (int i, int j)
    {
        super(-2, -2);
        mViewType = 0;
        gravity = 0x800013;
    }

    public gravity(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        mViewType = 0;
    }

    public mViewType(android.support.v7.app.s s)
    {
        super(s);
        mViewType = 0;
    }

    public mViewType(mViewType mviewtype)
    {
        super(mviewtype);
        mViewType = 0;
        mViewType = mviewtype.mViewType;
    }

    public mViewType(android.view.s s)
    {
        super(s);
        mViewType = 0;
    }

    public mViewType(android.view.tParams tparams)
    {
        super(tparams);
        mViewType = 0;
        leftMargin = tparams.leftMargin;
        topMargin = tparams.topMargin;
        rightMargin = tparams.rightMargin;
        bottomMargin = tparams.bottomMargin;
    }
}