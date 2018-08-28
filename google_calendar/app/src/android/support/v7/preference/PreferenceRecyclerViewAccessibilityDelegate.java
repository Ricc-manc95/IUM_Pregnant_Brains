// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerViewAccessibilityDelegate;

public final class PreferenceRecyclerViewAccessibilityDelegate extends RecyclerViewAccessibilityDelegate
{

    public final AccessibilityDelegateCompat mDefaultItemDelegate = super.getItemDelegate();
    private final AccessibilityDelegateCompat mItemDelegate = new _cls1();
    public final RecyclerView mRecyclerView;

    public PreferenceRecyclerViewAccessibilityDelegate(RecyclerView recyclerview)
    {
        super(recyclerview);
        mRecyclerView = recyclerview;
    }

    public final AccessibilityDelegateCompat getItemDelegate()
    {
        return mItemDelegate;
    }

    private class _cls1 extends AccessibilityDelegateCompat
    {

        private final PreferenceRecyclerViewAccessibilityDelegate this$0;

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
        {
            mDefaultItemDelegate.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat);
            int i = RecyclerView.getChildAdapterPosition(view);
            view = mRecyclerView.mAdapter;
            if (view instanceof PreferenceGroupAdapter)
            {
                view = (PreferenceGroupAdapter)view;
                if (i < 0 || i >= view.getItemCount())
                {
                    view = null;
                } else
                {
                    view = (Preference)((PreferenceGroupAdapter) (view)).mPreferenceList.get(i);
                }
                if (view != null)
                {
                    view.onInitializeAccessibilityNodeInfo(accessibilitynodeinfocompat);
                    return;
                }
            }
        }

        public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            return mDefaultItemDelegate.performAccessibilityAction(view, i, bundle);
        }

        _cls1()
        {
            this$0 = PreferenceRecyclerViewAccessibilityDelegate.this;
            super();
        }
    }

}
