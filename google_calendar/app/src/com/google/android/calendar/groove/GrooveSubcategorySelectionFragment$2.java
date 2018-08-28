// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Fade;
import android.view.View;
import com.google.android.calendar.analytics.AnalyticsLogger;
import com.google.android.calendar.analytics.AnalyticsLoggerHolder;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveSubcategorySelectionFragment, CustomGrooveFragment

final class this._cls0
    implements android.view.rySelectionFragment._cls2
{

    private final GrooveSubcategorySelectionFragment this$0;

    public final void onClick(View view)
    {
        Object obj = GrooveSubcategorySelectionFragment.this;
        int i = categoryId;
        view = AnalyticsLoggerHolder.instance;
        if (view == null)
        {
            throw new NullPointerException(String.valueOf("AnalyticsLogger not set"));
        }
        Object obj1 = (AnalyticsLogger)view;
        if (((Fragment) (obj)).mHost == null)
        {
            view = null;
        } else
        {
            view = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        ((AnalyticsLogger) (obj1)).trackView(view, "goal2a_custom");
        view = new Fade();
        view.setDuration(105);
        ((Fragment) (obj)).setExitTransition(view);
        ((Fragment) (obj)).setReenterTransition(null);
        view = ((Fragment) (obj)).mFragmentManager.beginTransaction();
        obj = new CustomGrooveFragment();
        obj1 = new Bundle();
        ((Bundle) (obj1)).putInt("GROOVE_TYPE_KEY", i);
        ((Bundle) (obj1)).putString("CUSTOM_QUESTION", null);
        ((Fragment) (obj)).setArguments(((Bundle) (obj1)));
        obj1 = new Fade();
        ((Fade) (obj1)).setDuration(105);
        ((Fragment) (obj)).setReturnTransition(obj1);
        ((Fragment) (obj)).setExitTransition(null);
        view.replace(0x7f10013c, ((Fragment) (obj)), CustomGrooveFragment.TAG);
        view.addToBackStack(CustomGrooveFragment.TAG).commit();
    }

    ()
    {
        this$0 = GrooveSubcategorySelectionFragment.this;
        super();
    }
}
