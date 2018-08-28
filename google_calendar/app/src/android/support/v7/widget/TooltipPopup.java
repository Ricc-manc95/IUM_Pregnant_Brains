// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.content.Context;
import android.graphics.Rect;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

final class TooltipPopup
{

    public final View mContentView;
    public final Context mContext;
    public final android.view.WindowManager.LayoutParams mLayoutParams = new android.view.WindowManager.LayoutParams();
    public final TextView mMessageView;
    public final int mTmpAnchorPos[] = new int[2];
    public final int mTmpAppPos[] = new int[2];
    public final Rect mTmpDisplayFrame = new Rect();

    TooltipPopup(Context context)
    {
        mContext = context;
        mContentView = LayoutInflater.from(mContext).inflate(0x7f05001b, null);
        mMessageView = (TextView)mContentView.findViewById(0x7f1000ff);
        mLayoutParams.setTitle(getClass().getSimpleName());
        mLayoutParams.packageName = mContext.getPackageName();
        mLayoutParams.type = 1002;
        mLayoutParams.width = -2;
        mLayoutParams.height = -2;
        mLayoutParams.format = -3;
        mLayoutParams.windowAnimations = 0x7f14000a;
        mLayoutParams.flags = 24;
    }
}
