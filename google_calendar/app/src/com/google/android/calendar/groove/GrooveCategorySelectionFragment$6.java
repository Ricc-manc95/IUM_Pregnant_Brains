// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveCategorySelectionFragment, GrooveCategoryView

final class val.categoryView
    implements android.view.rySelectionFragment._cls6
{

    private final GrooveCategorySelectionFragment this$0;
    private final int val$categoryType;
    private final GrooveCategoryView val$categoryView;

    public final void onClick(View view)
    {
        view = GrooveCategorySelectionFragment.this;
        if (((Fragment) (view)).mHost == null)
        {
            view = null;
        } else
        {
            view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
        }
        if (view instanceof stener)
        {
            view = GrooveCategorySelectionFragment.this;
            if (((Fragment) (view)).mHost == null)
            {
                view = null;
            } else
            {
                view = (FragmentActivity)((Fragment) (view)).mHost.mActivity;
            }
            ((stener)view).onCategorySelectionComplete(val$categoryType, val$categoryView);
        }
    }

    stener()
    {
        this$0 = final_groovecategoryselectionfragment;
        val$categoryType = i;
        val$categoryView = GrooveCategoryView.this;
        super();
    }
}
