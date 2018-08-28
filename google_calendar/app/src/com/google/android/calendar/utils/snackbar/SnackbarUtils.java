// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.snackbar;

import android.app.Activity;
import android.content.Context;
import android.support.design.snackbar.BaseTransientBottomBar;
import android.support.design.snackbar.SnackbarContentLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.TextView;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.utils.AccessibilityUtils;
import java.util.ArrayList;
import java.util.List;

public class SnackbarUtils
{
    static final class A11yAnnounceCallback extends android.support.design.widget.Snackbar.Callback
    {

        private Context context;
        private CharSequence text;

        public final void onShown(Snackbar snackbar)
        {
            super.onShown(snackbar);
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
            onShown((Snackbar)obj);
        }

        A11yAnnounceCallback(Context context1, CharSequence charsequence)
        {
            context = context1;
            text = charsequence;
        }
    }

    static final class DedupeDismissCallbackWrapper extends android.support.design.widget.Snackbar.Callback
    {

        private boolean dismissed;
        private boolean shown;
        private final android.support.design.widget.Snackbar.Callback wrapped;

        public final void onDismissed(Snackbar snackbar, int i)
        {
            if (dismissed)
            {
                LogUtils.e(SnackbarUtils.TAG, "DedupeDismissCallbackWrapper#onDismissed invoked multiple times!", new Object[0]);
                return;
            } else
            {
                wrapped.onDismissed(snackbar, i);
                dismissed = true;
                return;
            }
        }

        public final volatile void onDismissed(Object obj, int i)
        {
            onDismissed((Snackbar)obj, i);
        }

        public final void onShown(Snackbar snackbar)
        {
            if (shown)
            {
                LogUtils.e(SnackbarUtils.TAG, "DedupeDismissCallbackWrapper#onShown invoked multiple times!", new Object[0]);
                return;
            } else
            {
                wrapped.onShown(snackbar);
                shown = true;
                return;
            }
        }

        public final volatile void onShown(Object obj)
        {
            onShown((Snackbar)obj);
        }

        DedupeDismissCallbackWrapper(android.support.design.widget.Snackbar.Callback callback)
        {
            wrapped = callback;
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/snackbar/SnackbarUtils);

    public SnackbarUtils()
    {
    }

    public static Snackbar showSnackbar(Activity activity, String s, int i, String s1, android.view.View.OnClickListener onclicklistener, android.support.design.widget.Snackbar.Callback callback)
    {
        View view1 = activity.findViewById(0x7f100369);
        View view = view1;
        if (view1 == null)
        {
            view = activity.getWindow().getDecorView().findViewById(0x1020002);
        }
        return showSnackbar(((Context) (activity)), view, s, i, s1, onclicklistener, callback);
    }

    public static Snackbar showSnackbar(Context context, View view, String s, int i, String s1, android.view.View.OnClickListener onclicklistener, android.support.design.widget.Snackbar.Callback callback)
    {
        boolean flag2 = true;
        boolean flag;
        boolean flag1;
        if (onclicklistener == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (s1 == null)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (flag == flag1)
        {
            flag = flag2;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("actionListener and actionString must both be present or both be absent"));
        }
        Snackbar snackbar = Snackbar.make(view, s, i);
        ((BaseTransientBottomBar) (snackbar)).view.setAccessibilityLiveRegion(0);
        view = new A11yAnnounceCallback(view.getContext(), s);
        if (((BaseTransientBottomBar) (snackbar)).callbacks == null)
        {
            snackbar.callbacks = new ArrayList();
        }
        ((BaseTransientBottomBar) (snackbar)).callbacks.add(view);
        if (s1 != null)
        {
            i = ContextCompat.getColor(context, 0x7f0d01d7);
            ((SnackbarContentLayout)snackbar.view.getChildAt(0)).actionView.setTextColor(i);
            context = ((SnackbarContentLayout)snackbar.view.getChildAt(0)).actionView;
            if (TextUtils.isEmpty(s1) || onclicklistener == null)
            {
                context.setVisibility(8);
                context.setOnClickListener(null);
            } else
            {
                context.setVisibility(0);
                context.setText(s1);
                context.setOnClickListener(new android.support.design.widget.Snackbar._cls1(snackbar, onclicklistener));
            }
        }
        if (callback != null)
        {
            context = new DedupeDismissCallbackWrapper(callback);
            if (((BaseTransientBottomBar) (snackbar)).callbacks == null)
            {
                snackbar.callbacks = new ArrayList();
            }
            ((BaseTransientBottomBar) (snackbar)).callbacks.add(context);
        }
        snackbar.show();
        return snackbar;
    }

}
