// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Bundle;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import java.util.List;

// Referenced classes of package android.support.v7.preference:
//            PreferenceRecyclerViewAccessibilityDelegate, PreferenceGroupAdapter, Preference

final class this._cls0 extends AccessibilityDelegateCompat
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
            if (i < 0 || i >= view.mRecyclerView())
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

    Q()
    {
        this$0 = PreferenceRecyclerViewAccessibilityDelegate.this;
        super();
    }
}
