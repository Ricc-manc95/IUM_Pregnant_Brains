// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.android.libraries.hats20.inject.HatsModule;

// Referenced classes of package com.google.android.libraries.hats20:
//            PromptDialogDelegate, HatsController, DimensionConfigurationHelper

public final class PromptDialogFragment extends DialogFragment
    implements PromptDialogDelegate.DialogFragmentInterface
{

    private final PromptDialogDelegate _flddelegate = new PromptDialogDelegate(this);

    public PromptDialogFragment()
    {
    }

    public final Activity getActivity()
    {
        if (super.mHost == null)
        {
            return null;
        } else
        {
            return (FragmentActivity)super.mHost.mActivity;
        }
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return _flddelegate.onCreateView$51662RJ4E9NMIP1FEPKMATPF9HGNIRRLEH4MSPJCC5Q6ASHR9HGMSP3IDTKM8BRMD5INEBQMD5INEHRIDTQN0EQCC5N68SJFD5I2URRJ5T17ARJ4DHIJMAACC5N68SJFD5I2UTJ9CLRIULJ9CLRJM___0(layoutinflater, viewgroup);
    }

    public final void onDestroy()
    {
        if (!_flddelegate.isStartingSurvey)
        {
            HatsModule.get().getHatsController().markSurveyFinished();
        }
        super.onDestroy();
    }

    public final void onPause()
    {
        super.onPause();
        _flddelegate.areDimensionsValid = false;
    }

    public final void onResume()
    {
        PromptDialogDelegate promptdialogdelegate = _flddelegate;
        if (!promptdialogdelegate.areDimensionsValid)
        {
            promptdialogdelegate.configurationHelper.configureDimensions();
        }
        promptdialogdelegate.areDimensionsValid = true;
        super.onResume();
    }

    public final void onStart()
    {
        super.onStart();
        PromptDialogDelegate promptdialogdelegate = _flddelegate;
        if (!promptdialogdelegate.areDimensionsValid)
        {
            promptdialogdelegate.configurationHelper.configureDimensions();
        }
        promptdialogdelegate.areDimensionsValid = true;
    }
}
