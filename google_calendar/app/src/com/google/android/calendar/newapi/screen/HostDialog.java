// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.calendar.common.view.overlay.OverlayFragment;
import com.google.android.calendar.event.DetailsDialogFragment;
import com.google.android.calendar.event.OnInfoChangedListener;

// Referenced classes of package com.google.android.calendar.newapi.screen:
//            HostedFragment

public final class HostDialog extends DetailsDialogFragment
{

    private boolean visible;

    public HostDialog()
    {
        visible = false;
    }

    private final void doNavigateAway()
    {
        if (!((HostedFragment)getChildFragmentManager().findFragmentById(0x7f10013c)).onNavigateAway())
        {
            boolean flag;
            if (getChildFragmentManager().getBackStackEntryCount() > 0)
            {
                getChildFragmentManager().popBackStack();
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag && !notifyActivity(true))
            {
                super.dismiss();
            }
        }
    }

    private final boolean notifyActivity(boolean flag)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        if (obj instanceof OnInfoChangedListener)
        {
            obj = (OnInfoChangedListener)obj;
            if (flag)
            {
                ((OnInfoChangedListener) (obj)).onInfoBack(this, false);
            } else
            {
                ((OnInfoChangedListener) (obj)).onInfoCancel(this, false);
            }
            return true;
        } else
        {
            return false;
        }
    }

    public static void showWithChildFragment(Activity activity, FragmentManager fragmentmanager, HostedFragment hostedfragment)
    {
        showWithChildFragment(activity, fragmentmanager, hostedfragment, null);
    }

    public static void showWithChildFragment(Activity activity, FragmentManager fragmentmanager, HostedFragment hostedfragment, String s)
    {
        boolean flag;
        if (activity == null || activity.isDestroyed() || activity.isFinishing())
        {
            flag = false;
        } else
        if (fragmentmanager != null && !fragmentmanager.isDestroyed())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return;
        }
        FragmentTransaction fragmenttransaction = fragmentmanager.beginTransaction();
        fragmenttransaction.addToBackStack("HostDialog");
        activity = new HostDialog();
        activity.show(fragmenttransaction, "HostDialog");
        fragmentmanager.executePendingTransactions();
        activity.getChildFragmentManager().beginTransaction().replace(0x7f10013c, hostedfragment, s).commit();
        activity.getChildFragmentManager().executePendingTransactions();
        fragmentmanager = ((Fragment) (activity)).mView;
        if (fragmentmanager != null)
        {
            fragmentmanager.findViewById(0x7f10013c).setVisibility(0);
            return;
        } else
        {
            activity.visible = true;
            return;
        }
    }

    public final void dismiss()
    {
        boolean flag;
        if (getChildFragmentManager().getBackStackEntryCount() > 0)
        {
            getChildFragmentManager().popBackStack();
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && !notifyActivity(false))
        {
            super.dismiss();
        }
    }

    public final int getContentViewId()
    {
        return 0;
    }

    public final com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground getLoadingBackground()
    {
        return getTallBackground();
    }

    public final com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground getShortBackground()
    {
        return getTallBackground();
    }

    public final com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground getTallBackground()
    {
        if (requireContext().getResources().getBoolean(0x7f0c0013))
        {
            return com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground.FullDocked;
        } else
        {
            return com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayBackground.BottomDockedMatched;
        }
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return new _cls1(bundle);
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f05007b, viewgroup, false);
    }

    protected final void onDialogBackPressed()
    {
        doNavigateAway();
    }

    protected final void onTouchOutside()
    {
        doNavigateAway();
    }

    public final void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        if (bundle != null || visible)
        {
            view.findViewById(0x7f10013c).setVisibility(0);
        }
    }

    private class _cls1 extends com.google.android.calendar.common.view.overlay.OverlayFragment.OverlayDialog
    {

        private final HostDialog this$0;

        public final boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityevent)
        {
            if (accessibilityevent.getEventType() == 32)
            {
                accessibilityevent.getText().add(requireContext().getResources().getString(0x7f1301ba));
                return true;
            } else
            {
                return super.dispatchPopulateAccessibilityEvent(accessibilityevent);
            }
        }

        _cls1(Context context)
        {
            this$0 = HostDialog.this;
            super(HostDialog.this, context);
        }
    }

}
