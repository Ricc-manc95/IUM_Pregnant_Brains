// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.material.featurehighlight;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.View;

// Referenced classes of package com.google.android.libraries.material.featurehighlight:
//            FeatureHighlightFragment, FeatureHighlightView, FeatureHighlightCallbackProvider, FeatureHighlightCallback, 
//            ViewFinder, FeatureHighlightContent

final class this._cls0
    implements Runnable
{

    private final FeatureHighlightFragment this$0;

    public final void run()
    {
        if (featureHighlightView == null) goto _L2; else goto _L1
_L1:
        FeatureHighlightFragment featurehighlightfragment1;
        featureHighlightView.bringToFront();
        FeatureHighlightFragment featurehighlightfragment = FeatureHighlightFragment.this;
        if (FeatureHighlightFragment.isBelowKitKat())
        {
            View view = (View)featureHighlightView.getParent();
            view.requestLayout();
            view.invalidate();
        }
        featurehighlightfragment1 = FeatureHighlightFragment.this;
        if (featurehighlightfragment1.featureHighlightViewInitialized) goto _L2; else goto _L3
_L3:
        Object obj3;
        featurehighlightfragment1.featureHighlightViewInitialized = true;
        FeatureHighlightCallback featurehighlightcallback;
        if (featurehighlightfragment1.featureHighlightCallbackProvider != null)
        {
            featurehighlightcallback = featurehighlightfragment1.featureHighlightCallbackProvider.getFeatureHighlightCallback(featurehighlightfragment1.callbackId);
        } else
        {
            featurehighlightcallback = null;
        }
        if (featurehighlightcallback != null)
        {
            featurehighlightcallback.onAttached$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
        }
        if (((Fragment) (featurehighlightfragment1)).mHost == null)
        {
            obj3 = null;
        } else
        {
            obj3 = (FragmentActivity)((Fragment) (featurehighlightfragment1)).mHost.mActivity;
        }
        if (obj3 == null)
        {
            obj3 = null;
        } else
        {
            ViewFinder viewfinder = featurehighlightfragment1.viewFinder;
            Object obj;
            if (featurehighlightfragment1.confiningViewId == -1)
            {
                obj = null;
            } else
            {
                if (((Fragment) (featurehighlightfragment1)).mHost == null)
                {
                    obj = null;
                } else
                {
                    obj = (FragmentActivity)((Fragment) (featurehighlightfragment1)).mHost.mActivity;
                }
                if (obj == null)
                {
                    obj = null;
                } else
                {
                    obj = ((Activity) (obj)).findViewById(featurehighlightfragment1.confiningViewId);
                }
            }
            obj3 = viewfinder.find(((Activity) (obj3)), ((View) (obj)));
        }
_L8:
        if (obj3 != null) goto _L5; else goto _L4
_L4:
        if (featurehighlightcallback != null)
        {
            featurehighlightcallback.onViewNotFound$5166KOBMC4NMOOBECSNL6T3ID5N6EEP9AO______0();
        }
        featurehighlightfragment1.removeFragment();
_L2:
        return;
_L5:
        Object obj2 = featurehighlightfragment1.featureHighlightView;
        Object obj1;
        if (featurehighlightfragment1.confiningViewId == -1)
        {
            obj1 = null;
        } else
        {
            if (((Fragment) (featurehighlightfragment1)).mHost == null)
            {
                obj1 = null;
            } else
            {
                obj1 = (FragmentActivity)((Fragment) (featurehighlightfragment1)).mHost.mActivity;
            }
            if (obj1 == null)
            {
                obj1 = null;
            } else
            {
                obj1 = ((Activity) (obj1)).findViewById(featurehighlightfragment1.confiningViewId);
            }
        }
        obj2.confiningView = ((View) (obj1));
        obj1 = featurehighlightfragment1.featureHighlightView;
        obj2 = new <init>(featurehighlightfragment1);
        ((FeatureHighlightView) (obj1)).content.setCallback(((ctionCallback) (obj2)));
        obj1.callback = ((ctionCallback) (obj2));
        if (featurehighlightfragment1.showState != 1) goto _L2; else goto _L6
_L6:
        if (featurehighlightfragment1.isBeingRestored)
        {
            obj1 = featurehighlightfragment1.featureHighlightView;
            ((FeatureHighlightView) (obj1)).setupForTarget(((View) (obj3)));
            ((FeatureHighlightView) (obj1)).addOnLayoutChangeListener(new t>(((FeatureHighlightView) (obj1)), null));
            ((FeatureHighlightView) (obj1)).requestLayout();
            return;
        }
        FeatureHighlightView featurehighlightview = featurehighlightfragment1.featureHighlightView;
        ctionCallback ctioncallback = new <init>(featurehighlightfragment1);
        featurehighlightview.setupForTarget(((View) (obj3)));
        featurehighlightview.addOnLayoutChangeListener(new t>(featurehighlightview, ctioncallback));
        featurehighlightview.requestLayout();
        return;
        if (true) goto _L8; else goto _L7
_L7:
    }

    vider()
    {
        this$0 = FeatureHighlightFragment.this;
        super();
    }
}
