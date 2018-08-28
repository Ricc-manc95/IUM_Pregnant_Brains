// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.snackbar;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import com.google.android.calendar.utils.AccessibilityUtils;

// Referenced classes of package com.google.android.calendar.utils.snackbar:
//            SnackbarUtils

static final class text extends android.support.design.widget.unceCallback
{

    private Context context;
    private CharSequence text;

    public final void onShown(Snackbar snackbar)
    {
        super.unceCallback(snackbar);
        CharSequence charsequence = text;
        if (AccessibilityUtils.isAccessibilityEnabled(context))
        {
            AccessibilityManager accessibilitymanager = (AccessibilityManager)context.getSystemService("accessibility");
            AccessibilityEvent accessibilityevent = AccessibilityEvent.obtain(32);
            accessibilityevent.setContentDescription(charsequence);
            accessibilityevent.setClassName(snackbar.getClass().getName());
            accessibilityevent.setPackageName(context.getPackageName());
            accessibilityevent.setEnabled(true);
            accessibilitymanager.sendAccessibilityEvent(accessibilityevent);
        }
    }

    public final volatile void onShown(Object obj)
    {
        unceCallback((Snackbar)obj);
    }

    (Context context1, CharSequence charsequence)
    {
        context = context1;
        text = charsequence;
    }
}
