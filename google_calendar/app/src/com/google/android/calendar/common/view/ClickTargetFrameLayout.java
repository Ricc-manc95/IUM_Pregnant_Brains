// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.view;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class ClickTargetFrameLayout extends FrameLayout
{

    public ClickTargetFrameLayout(Context context)
    {
        super(context);
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    public ClickTargetFrameLayout(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
        ViewCompat.setImportantForAccessibility(this, 1);
    }

    public CharSequence getAccessibilityClassName()
    {
        return android/widget/Button.getName();
    }

    public CharSequence getContentDescription()
    {
        CharSequence charsequence;
        if (android.os.Build.VERSION.SDK_INT >= 23)
        {
            charsequence = super.getContentDescription();
        } else
        {
            Object obj = super.getContentDescription();
            charsequence = ((CharSequence) (obj));
            if (!TextUtils.isEmpty(((CharSequence) (obj))))
            {
                String s = String.valueOf(obj);
                obj = getContext().getString(0x7f1300e7);
                return (new StringBuilder(String.valueOf(s).length() + 1 + String.valueOf(obj).length())).append(s).append(" ").append(((String) (obj))).toString();
            }
        }
        return charsequence;
    }

    public void onViewAdded(View view)
    {
        super.onViewAdded(view);
        ViewCompat.setImportantForAccessibility(view, 4);
    }

    public boolean requestFocus(int i, Rect rect)
    {
        if (getChildCount() == 0)
        {
            return false;
        } else
        {
            getChildAt(0).requestFocus();
            return true;
        }
    }

    public void setOnClickListener(android.view.View.OnClickListener onclicklistener)
    {
        if (getChildCount() == 0)
        {
            return;
        } else
        {
            super.setOnClickListener(onclicklistener);
            class .Lambda._cls0
                implements android.view.View.OnClickListener
            {

                private final ClickTargetFrameLayout arg$1;

                public final void onClick(View view)
                {
                    arg$1.callOnClick();
                }

            .Lambda._cls0()
            {
                arg$1 = ClickTargetFrameLayout.this;
            }
            }

            getChildAt(0).setOnClickListener(new .Lambda._cls0());
            return;
        }
    }
}
