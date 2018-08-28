// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.base.UserActionUtil;
import com.google.android.libraries.material.featurehighlight.FeatureHighlightCallback;

// Referenced classes of package com.google.android.libraries.internal.growth.growthkit.internal.ui.impl.taptarget:
//            FeatureHighlightController, FeatureHighlightFragment

final class reportedAction extends FeatureHighlightCallback
{

    private boolean reportedAction;
    private final FeatureHighlightController this$0;

    public final void onAttached$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0()
    {
        com.google.android.libraries.internal.growth.growthkit.internal.common.Logger logger = FeatureHighlightController.logger;
    }

    public final void onDetached$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0()
    {
        com.google.android.libraries.internal.growth.growthkit.internal.common.Logger logger = FeatureHighlightController.logger;
        if (!reportedAction && !featureHighlightFragment.savedState)
        {
            userActionUtil.persistUserChoice(promoContext, com.google.identity.growth.proto..DISMISSED);
            reportedAction = true;
        }
        if (featureHighlightFragment != null)
        {
            FeatureHighlightFragment featurehighlightfragment = featureHighlightFragment;
            Object obj;
            if (((Fragment) (featurehighlightfragment)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (featurehighlightfragment)).mHost.mActivity;
            }
            if (obj != null)
            {
                if (((Fragment) (featurehighlightfragment)).mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (featurehighlightfragment)).mHost.mActivity;
                }
                if (!((FragmentActivity) (obj)).isFinishing())
                {
                    boolean flag;
                    if (((Fragment) (featurehighlightfragment)).mHost != null && ((Fragment) (featurehighlightfragment)).mAdded)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag && !((Fragment) (featurehighlightfragment)).mRemoving)
                    {
                        if ((obj = ((Fragment) (featurehighlightfragment)).mFragmentManager) != null)
                        {
                            ((FragmentManager) (obj)).beginTransaction().remove(featurehighlightfragment).commitAllowingStateLoss();
                            return;
                        }
                    }
                }
            }
        }
    }

    public final void onDismiss$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0()
    {
        com.google.android.libraries.internal.growth.growthkit.internal.common.Logger logger = FeatureHighlightController.logger;
        userActionUtil.persistUserChoice(promoContext, com.google.identity.growth.proto..DISMISSED);
        reportedAction = true;
    }

    public final void onShow$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0()
    {
        com.google.android.libraries.internal.growth.growthkit.internal.common.Logger logger = FeatureHighlightController.logger;
    }

    public final void onTaskComplete$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0()
    {
        com.google.android.libraries.internal.growth.growthkit.internal.common.Logger logger = FeatureHighlightController.logger;
        userActionUtil.persistUserChoice(promoContext, com.google.identity.growth.proto..POSITIVE_RESPONSE);
        reportedAction = true;
    }

    public final void onViewNotFound$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0()
    {
        com.google.android.libraries.internal.growth.growthkit.internal.common.Logger logger = FeatureHighlightController.logger;
    }

    ()
    {
        this$0 = FeatureHighlightController.this;
        super();
        reportedAction = false;
    }
}
