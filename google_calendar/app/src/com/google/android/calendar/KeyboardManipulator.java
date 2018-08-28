// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.app.Activity;
import android.os.SystemClock;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public final class KeyboardManipulator
    implements android.view.ViewTreeObserver.OnGlobalLayoutListener, Runnable
{

    private Activity activity;
    private EditText editText;
    public long showPendingSince;
    private ViewTreeObserver viewTreeObserver;

    public KeyboardManipulator(Activity activity1, EditText edittext)
    {
        showPendingSince = -1L;
        activity = activity1;
        editText = edittext;
    }

    private final void cleanupCallbacks()
    {
        if (viewTreeObserver != null && viewTreeObserver.isAlive())
        {
            viewTreeObserver.removeOnGlobalLayoutListener(this);
            viewTreeObserver = null;
        }
        editText.removeCallbacks(this);
    }

    public final void onGlobalLayout()
    {
        cleanupCallbacks();
        boolean flag;
        if (SystemClock.uptimeMillis() - showPendingSince < 1000L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            editText.post(this);
        }
    }

    public final void requestHide()
    {
        showPendingSince = -1L;
        cleanupCallbacks();
        ((InputMethodManager)activity.getSystemService("input_method")).hideSoftInputFromWindow(editText.getWindowToken(), 0);
    }

    public final void run()
    {
        showIfNecessary();
    }

    public final void showIfNecessary()
    {
        cleanupCallbacks();
        boolean flag;
        if (SystemClock.uptimeMillis() - showPendingSince < 1000L)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return;
        }
        if (!((InputMethodManager)activity.getSystemService("input_method")).showSoftInput(editText, 0))
        {
            viewTreeObserver = editText.getViewTreeObserver();
            viewTreeObserver.addOnGlobalLayoutListener(this);
            return;
        } else
        {
            showPendingSince = -1L;
            return;
        }
    }
}
