// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.groove;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.RippleDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;
import android.view.ViewTreeObserver;
import com.google.android.calendar.Utils;

// Referenced classes of package com.google.android.calendar.groove:
//            GrooveCategorySelectionFragment, GrooveUtils, GrooveCategoryView

final class val.categoryView
    implements android.view.
{

    private final GrooveCategorySelectionFragment this$0;
    private final com.google.android.calendar.groove.category.oryView val$category;
    private final GrooveCategoryView val$categoryView;

    public final boolean onPreDraw()
    {
        int i = mView.getWidth();
        if (GrooveCategorySelectionFragment.isTablet)
        {
            i /= 2;
        }
        int j = (int)(((float)i / 16F) * 9F);
        Object obj = GrooveCategorySelectionFragment.this;
        Object obj1;
        if (((Fragment) (obj)).mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)((Fragment) (obj)).mHost.mActivity;
        }
        obj1 = GrooveCategorySelectionFragment.this;
        if (((Fragment) (obj1)).mHost == null)
        {
            obj1 = null;
        } else
        {
            obj1 = (FragmentActivity)((Fragment) (obj1)).mHost.mActivity;
        }
        obj = Utils.getRtlAdjustedImage(((Context) (obj)), GrooveUtils.decodeScaledBitmapFromResource(((Context) (obj1)), val$category.ndDrawableResId, i, j));
        val$categoryView.setBackground(new RippleDrawable(ColorStateList.valueOf(requireContext().getResources().getColor(0x7f0d0087)), new BitmapDrawable(requireContext().getResources(), ((android.graphics.Bitmap) (obj))), null));
        val$categoryView.getViewTreeObserver().removeOnPreDrawListener(this);
        return false;
    }

    ()
    {
        this$0 = final_groovecategoryselectionfragment;
        val$category = ;
        val$categoryView = GrooveCategoryView.this;
        super();
    }
}
