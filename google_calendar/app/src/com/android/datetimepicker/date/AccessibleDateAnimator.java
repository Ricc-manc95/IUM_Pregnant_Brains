// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.date;

import android.content.Context;
import android.text.format.DateUtils;
import android.util.AttributeSet;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ViewAnimator;
import java.util.List;

public class AccessibleDateAnimator extends ViewAnimator
{

    private long dateMillis;

    public AccessibleDateAnimator(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
    {
        if (accessibilityevent.getEventType() == 32)
        {
            accessibilityevent.getText().clear();
            String s = DateUtils.formatDateTime(getContext(), dateMillis, 22);
            accessibilityevent.getText().add(s);
            return true;
        } else
        {
            return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
        }
    }

    public void setDateMillis(long l)
    {
        dateMillis = l;
    }
}
