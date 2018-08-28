// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen;

import android.app.Activity;
import android.app.Dialog;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.FragmentManager;

public class DiscardChangesDialog extends DialogFragment
{
    public static interface Callback
    {

        public abstract void onDiscard();
    }


    public static final String TAG = com/google/android/calendar/newapi/screen/DiscardChangesDialog.getSimpleName();

    public DiscardChangesDialog()
    {
    }

    public static DiscardChangesDialog newInstance(Fragment fragment, int i)
    {
        Bundle bundle = new Bundle();
        bundle.putInt("ARG_MESSAGE", i);
        DiscardChangesDialog discardchangesdialog = new DiscardChangesDialog();
        discardchangesdialog.setArguments(bundle);
        discardchangesdialog.setTargetFragment(fragment, 0);
        return discardchangesdialog;
    }

    final void dismissDialog()
    {
        if (this == null) goto _L2; else goto _L1
_L1:
        android.support.v4.app.FragmentManagerImpl fragmentmanagerimpl = super.mFragmentManager;
        if (this == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (super.mHost != null && super.mAdded)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag) goto _L5; else goto _L4
_L4:
        flag = false;
_L7:
        if (flag)
        {
            dismiss();
        }
        return;
_L5:
        FragmentActivity fragmentactivity;
        if (super.mHost == null)
        {
            fragmentactivity = null;
        } else
        {
            fragmentactivity = (FragmentActivity)super.mHost.mActivity;
        }
        if (fragmentactivity == null || fragmentactivity.isDestroyed() || fragmentactivity.isFinishing())
        {
            flag = false;
            continue; /* Loop/switch isn't completed */
        }
        if (fragmentmanagerimpl != null && !fragmentmanagerimpl.isDestroyed())
        {
            flag = true;
            continue; /* Loop/switch isn't completed */
        }
_L2:
        flag = false;
        if (true) goto _L7; else goto _L6
_L6:
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        int i = getArguments().getInt("ARG_MESSAGE");
        class .Lambda._cls0
            implements android.content.DialogInterface.OnClickListener
        {

            private final DiscardChangesDialog arg$1;

            public final void onClick(DialogInterface dialoginterface, int j)
            {
                dialoginterface = arg$1;
                ((Callback)((Fragment) (dialoginterface)).mTarget).onDiscard();
                dialoginterface.dismissDialog();
            }

            .Lambda._cls0()
            {
                arg$1 = DiscardChangesDialog.this;
            }
        }

        class .Lambda._cls1
            implements android.content.DialogInterface.OnClickListener
        {

            private final DiscardChangesDialog arg$1;

            public final void onClick(DialogInterface dialoginterface, int j)
            {
                arg$1.dismissDialog();
            }

            .Lambda._cls1()
            {
                arg$1 = DiscardChangesDialog.this;
            }
        }

        Resources resources;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        resources = bundle.getResources();
        return (new android.app.AlertDialog.Builder(bundle)).setMessage(i).setNegativeButton(resources.getString(0x7f130186), new .Lambda._cls0()).setPositiveButton(resources.getString(0x7f130187), new .Lambda._cls1()).create();
    }

}
