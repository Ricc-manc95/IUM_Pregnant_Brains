// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.view;

import android.os.Bundle;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.view.accessibility.AccessibilityNodeProviderCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;

public class AccessibilityDelegateCompat
{

    public static final android.view.View.AccessibilityDelegate DEFAULT_DELEGATE = new android.view.View.AccessibilityDelegate();
    public final android.view.View.AccessibilityDelegate mBridge = new AccessibilityDelegateAdapter();

    public AccessibilityDelegateCompat()
    {
    }

    public boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        return DEFAULT_DELEGATE.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
    }

    public AccessibilityNodeProviderCompat getAccessibilityNodeProvider(View view)
    {
        view = DEFAULT_DELEGATE.getAccessibilityNodeProvider(view);
        if (view != null)
        {
            return new AccessibilityNodeProviderCompat(view);
        } else
        {
            return null;
        }
    }

    public void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        DEFAULT_DELEGATE.onInitializeAccessibilityEvent(view, accessibilityevent);
    }

    public void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfoCompat accessibilitynodeinfocompat)
    {
        DEFAULT_DELEGATE.onInitializeAccessibilityNodeInfo(view, accessibilitynodeinfocompat.mInfo);
    }

    public void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
    {
        DEFAULT_DELEGATE.onPopulateAccessibilityEvent(view, accessibilityevent);
    }

    public boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
    {
        return DEFAULT_DELEGATE.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
    }

    public boolean performAccessibilityAction(View view, int i, Bundle bundle)
    {
        return DEFAULT_DELEGATE.performAccessibilityAction(view, i, bundle);
    }


    private class AccessibilityDelegateAdapter extends android.view.View.AccessibilityDelegate
    {

        private final AccessibilityDelegateCompat mCompat;

        public final boolean dispatchPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            return mCompat.dispatchPopulateAccessibilityEvent(view, accessibilityevent);
        }

        public final AccessibilityNodeProvider getAccessibilityNodeProvider(View view)
        {
            view = mCompat.getAccessibilityNodeProvider(view);
            if (view != null)
            {
                return (AccessibilityNodeProvider)((AccessibilityNodeProviderCompat) (view)).mProvider;
            } else
            {
                return null;
            }
        }

        public final void onInitializeAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            mCompat.onInitializeAccessibilityEvent(view, accessibilityevent);
        }

        public final void onInitializeAccessibilityNodeInfo(View view, AccessibilityNodeInfo accessibilitynodeinfo)
        {
            mCompat.onInitializeAccessibilityNodeInfo(view, new AccessibilityNodeInfoCompat(accessibilitynodeinfo));
        }

        public final void onPopulateAccessibilityEvent(View view, AccessibilityEvent accessibilityevent)
        {
            mCompat.onPopulateAccessibilityEvent(view, accessibilityevent);
        }

        public final boolean onRequestSendAccessibilityEvent(ViewGroup viewgroup, View view, AccessibilityEvent accessibilityevent)
        {
            return mCompat.onRequestSendAccessibilityEvent(viewgroup, view, accessibilityevent);
        }

        public final boolean performAccessibilityAction(View view, int i, Bundle bundle)
        {
            return mCompat.performAccessibilityAction(view, i, bundle);
        }

        public final void sendAccessibilityEvent(View view, int i)
        {
            AccessibilityDelegateCompat.DEFAULT_DELEGATE.sendAccessibilityEvent(view, i);
        }

        public final void sendAccessibilityEventUnchecked(View view, AccessibilityEvent accessibilityevent)
        {
            AccessibilityDelegateCompat.DEFAULT_DELEGATE.sendAccessibilityEventUnchecked(view, accessibilityevent);
        }

        AccessibilityDelegateAdapter()
        {
            mCompat = AccessibilityDelegateCompat.this;
        }
    }

}
