// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.calendarlist;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;

final class  extends android.view.upItemAccessibilityDelegate
{

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
    {
        super.deInfo(view, accessibilitynodeinfo);
        accessibilitynodeinfo.setEnabled(true);
    }

    ()
    {
    }
}
