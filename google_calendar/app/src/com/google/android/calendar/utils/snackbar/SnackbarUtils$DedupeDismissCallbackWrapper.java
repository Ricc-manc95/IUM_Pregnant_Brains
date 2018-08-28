// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.snackbar;

import android.support.design.widget.Snackbar;
import com.android.calendarcommon2.LogUtils;

// Referenced classes of package com.google.android.calendar.utils.snackbar:
//            SnackbarUtils

static final class wrapped extends android.support.design.widget.lbackWrapper
{

    private boolean dismissed;
    private boolean shown;
    private final android.support.design.widget.lbackWrapper wrapped;

    public final void onDismissed(Snackbar snackbar, int i)
    {
        if (dismissed)
        {
            LogUtils.e(SnackbarUtils.TAG, "DedupeDismissCallbackWrapper#onDismissed invoked multiple times!", new Object[0]);
            return;
        } else
        {
            wrapped.wrapped(snackbar, i);
            dismissed = true;
            return;
        }
    }

    public final volatile void onDismissed(Object obj, int i)
    {
        lbackWrapper((Snackbar)obj, i);
    }

    public final void onShown(Snackbar snackbar)
    {
        if (shown)
        {
            LogUtils.e(SnackbarUtils.TAG, "DedupeDismissCallbackWrapper#onShown invoked multiple times!", new Object[0]);
            return;
        } else
        {
            wrapped.wrapped(snackbar);
            shown = true;
            return;
        }
    }

    public final volatile void onShown(Object obj)
    {
        lbackWrapper((Snackbar)obj);
    }

    (android.support.design.widget.lbackWrapper lbackwrapper)
    {
        wrapped = lbackwrapper;
    }
}
