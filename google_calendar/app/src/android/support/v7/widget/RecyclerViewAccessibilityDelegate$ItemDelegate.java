// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.widget;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.List;

// Referenced classes of package android.support.v7.widget:
//            RecyclerViewAccessibilityDelegate, RecyclerView, ChildHelper

public final class mRecyclerViewDelegate extends AccessibilityDelegateCompat
{

    private final RecyclerViewAccessibilityDelegate mRecyclerViewDelegate;

    public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        super.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
        if (!mRecyclerViewDelegate.mRecyclerView.hasPendingAdapterUpdates() && mRecyclerViewDelegate.mRecyclerView.mLayout != null)
        {
            mRecyclerViewDelegate mrecyclerviewdelegate = mRecyclerViewDelegate.mRecyclerView.mLayout;
            Object obj1 = RecyclerView.getChildViewHolderInt(view);
            if (obj1 != null)
            {
                int i;
                if (((() (obj1)). & 8) != 0)
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i == 0)
                {
                    ChildHelper childhelper = mrecyclerviewdelegate.;
                    obj1 = (() (obj1)).;
                    if (!childhelper.mHiddenViews.contains(obj1))
                    {
                        Object obj = mrecyclerviewdelegate..mRecycler;
                        obj = mrecyclerviewdelegate..mState;
                        int j;
                        if (mrecyclerviewdelegate.())
                        {
                            i = (view);
                        } else
                        {
                            i = 0;
                        }
                        if (mrecyclerviewdelegate.y())
                        {
                            j = y(view);
                        } else
                        {
                            j = 0;
                        }
                        view = new android.support.v4.view.accessibility.ompat(android.view.accessibility.(i, 1, j, 1, false, false));
                        accessibilitynodeinfocompat = accessibilitynodeinfocompat.mInfo;
                        if (view == null)
                        {
                            view = null;
                        } else
                        {
                            view = (android.view.accessibility.)((android.support.v4.view.accessibility.ompat)view).mInfo;
                        }
                        accessibilitynodeinfocompat.setCollectionItemInfo(view);
                    }
                }
            }
        }
    }

    public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        boolean flag1 = false;
        boolean flag;
        if (super.performAccessibilityAction(view, i, bundle))
        {
            flag = true;
        } else
        {
            flag = flag1;
            if (!mRecyclerViewDelegate.mRecyclerView.hasPendingAdapterUpdates())
            {
                flag = flag1;
                if (mRecyclerViewDelegate.mRecyclerView.mLayout != null)
                {
                    view = mRecyclerViewDelegate.mRecyclerView.mLayout;
                    bundle = (() (view))..mRecycler;
                    view = (() (view))..mState;
                    return false;
                }
            }
        }
        return flag;
    }

    public ionItemInfoCompat(RecyclerViewAccessibilityDelegate recyclerviewaccessibilitydelegate)
    {
        mRecyclerViewDelegate = recyclerviewaccessibilitydelegate;
    }
}
