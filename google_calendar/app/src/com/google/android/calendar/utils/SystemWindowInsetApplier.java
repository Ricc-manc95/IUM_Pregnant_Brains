// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils;

import android.support.v4.view.OnApplyWindowInsetsListener;
import android.support.v4.view.WindowInsetsCompat;
import android.view.View;
import android.view.WindowInsets;
import com.android.calendarcommon2.LogUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class SystemWindowInsetApplier
    implements OnApplyWindowInsetsListener
{
    public static interface CustomInsetHandler
    {

        public abstract void onInsetsChanged$51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0(View view, int i);
    }

    public static final class InsetSensitiveViewRegistration
    {

        public final int applicationStyle;
        public final int oldBottomSpacing;
        public final int oldLeftSpacing;
        public final int oldRightSpacing;
        public final int oldTopSpacing;
        public final int sensitivity;
        public final View view;

        InsetSensitiveViewRegistration(View view1, int i, int j)
        {
            if (j == 1 && !(view1.getLayoutParams() instanceof android.view.ViewGroup.MarginLayoutParams))
            {
                throw new IllegalArgumentException(String.format("%s cannot work with view %s: LayoutParams are of type %s which doesn't extend MarginLayoutParams.", new Object[] {
                    getClass().getSimpleName(), view1, view1.getLayoutParams().getClass().getSimpleName()
                }));
            }
            view = view1;
            sensitivity = i;
            applicationStyle = j;
            if (j == 1)
            {
                view1 = (android.view.ViewGroup.MarginLayoutParams)view.getLayoutParams();
                oldLeftSpacing = ((android.view.ViewGroup.MarginLayoutParams) (view1)).leftMargin;
                oldTopSpacing = ((android.view.ViewGroup.MarginLayoutParams) (view1)).topMargin;
                oldRightSpacing = ((android.view.ViewGroup.MarginLayoutParams) (view1)).rightMargin;
                oldBottomSpacing = ((android.view.ViewGroup.MarginLayoutParams) (view1)).bottomMargin;
                return;
            } else
            {
                oldLeftSpacing = view.getPaddingLeft();
                oldTopSpacing = view.getPaddingTop();
                oldRightSpacing = view.getPaddingRight();
                oldBottomSpacing = view.getPaddingBottom();
                return;
            }
        }
    }


    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/utils/SystemWindowInsetApplier);
    private final boolean consumesInsets;
    public final Map customHandledViews;
    public final Map views;

    public SystemWindowInsetApplier()
    {
        this(false);
    }

    public SystemWindowInsetApplier(boolean flag)
    {
        views = new HashMap();
        customHandledViews = new HashMap();
        consumesInsets = flag;
    }

    public static void applyMarginToView(InsetSensitiveViewRegistration insetsensitiveviewregistration, int i, int j, int k, int l)
    {
        android.view.ViewGroup.MarginLayoutParams marginlayoutparams = (android.view.ViewGroup.MarginLayoutParams)insetsensitiveviewregistration.view.getLayoutParams();
        if ((insetsensitiveviewregistration.sensitivity & 1) > 0)
        {
            marginlayoutparams.leftMargin = i;
        }
        if ((insetsensitiveviewregistration.sensitivity & 2) > 0)
        {
            marginlayoutparams.topMargin = j;
        }
        if ((insetsensitiveviewregistration.sensitivity & 4) > 0)
        {
            marginlayoutparams.rightMargin = k;
        }
        if ((insetsensitiveviewregistration.sensitivity & 8) > 0)
        {
            marginlayoutparams.bottomMargin = l;
        }
        insetsensitiveviewregistration.view.setLayoutParams(marginlayoutparams);
    }

    public static void applyPaddingToView(InsetSensitiveViewRegistration insetsensitiveviewregistration, int i, int j, int k, int l)
    {
        View view = insetsensitiveviewregistration.view;
        if ((insetsensitiveviewregistration.sensitivity & 1) <= 0)
        {
            i = insetsensitiveviewregistration.view.getPaddingLeft();
        }
        if ((insetsensitiveviewregistration.sensitivity & 2) <= 0)
        {
            j = insetsensitiveviewregistration.view.getPaddingTop();
        }
        if ((insetsensitiveviewregistration.sensitivity & 4) <= 0)
        {
            k = insetsensitiveviewregistration.view.getPaddingRight();
        }
        if ((insetsensitiveviewregistration.sensitivity & 8) <= 0)
        {
            l = insetsensitiveviewregistration.view.getPaddingBottom();
        }
        view.setPadding(i, j, k, l);
    }

    public final void addView(View view, int i, int j)
    {
        if (view == null)
        {
            throw new NullPointerException();
        }
        if (views.containsKey(view))
        {
            LogUtils.w(TAG, "Received addView for view %s, which was already present. Ignoring.", new Object[] {
                view
            });
            return;
        }
        InsetSensitiveViewRegistration insetsensitiveviewregistration;
        try
        {
            insetsensitiveviewregistration = new InsetSensitiveViewRegistration(view, i, j);
        }
        // Misplaced declaration of an exception variable
        catch (View view)
        {
            LogUtils.wtf(TAG, view, "Failed to create a view registration", new Object[0]);
            return;
        }
        views.put(view, insetsensitiveviewregistration);
    }

    public final WindowInsetsCompat onApplyWindowInsets(View view, WindowInsetsCompat windowinsetscompat)
    {
        int i = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetLeft();
        int j = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetTop();
        int k = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetRight();
        int l = ((WindowInsets)windowinsetscompat.mInsets).getSystemWindowInsetBottom();
        for (view = views.values().iterator(); view.hasNext();)
        {
            InsetSensitiveViewRegistration insetsensitiveviewregistration = (InsetSensitiveViewRegistration)view.next();
            if (insetsensitiveviewregistration.applicationStyle == 1)
            {
                applyMarginToView(insetsensitiveviewregistration, i, j, k, l);
            } else
            {
                applyPaddingToView(insetsensitiveviewregistration, i, j, k, l);
            }
        }

        java.util.Map.Entry entry;
        for (view = customHandledViews.entrySet().iterator(); view.hasNext(); ((CustomInsetHandler)entry.getValue())._mth51662RJ4E9NMIP1FEPKMATPFAPKMATPR954KII99AO______0((View)entry.getKey(), j))
        {
            entry = (java.util.Map.Entry)view.next();
        }

        view = windowinsetscompat;
        if (consumesInsets)
        {
            view = new WindowInsetsCompat(((WindowInsets)windowinsetscompat.mInsets).consumeSystemWindowInsets());
        }
        return view;
    }

}
