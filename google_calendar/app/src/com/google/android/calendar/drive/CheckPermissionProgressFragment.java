// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.drive;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManagerImpl;
import android.support.v4.content.Loader;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.drive:
//            CheckPermissionsLoader, CheckPermissionsResults

public final class CheckPermissionProgressFragment extends DialogFragment
    implements android.support.v4.app.LoaderManager.LoaderCallbacks
{

    public String accountName;
    private final Handler handler = new Handler();
    public int numFiles;

    public CheckPermissionProgressFragment()
    {
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        Object obj;
        Bundle bundle1;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        obj = new ProgressDialog(((Context) (obj)));
        ((ProgressDialog) (obj)).setIndeterminate(true);
        ((ProgressDialog) (obj)).setMessage(requireContext().getResources().getString(0x7f13043a));
        bundle1 = getArguments();
        numFiles = bundle1.getStringArrayList("fileIds").size();
        accountName = bundle1.getString("account");
        if (bundle != null)
        {
            (new LoaderManagerImpl(this, ((ViewModelStoreOwner)this).getViewModelStore())).initLoader(0, getArguments(), this);
            return ((Dialog) (obj));
        } else
        {
            (new LoaderManagerImpl(this, ((ViewModelStoreOwner)this).getViewModelStore())).restartLoader(0, getArguments(), this);
            return ((Dialog) (obj));
        }
    }

    public final Loader onCreateLoader$514KOOBECHP6UQB45TNN6BQ2ELN68R357CKKOOBECHP6UQB45TPNAS3GDTP78BRM6GNM6RREEHIMST1F9HNM2P35E8TG____0(Bundle bundle)
    {
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        return new CheckPermissionsLoader(((Context) (obj)), bundle);
    }

    public final void onLoadFinished(Loader loader, Object obj)
    {
        loader = (CheckPermissionsResults)obj;
        handler.post(new _cls1(this, loader));
    }

    public final void onLoaderReset$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5THMURJKCLN78BQCDTGM8PBI7CKLC___0()
    {
    }

    private class _cls1 extends FragmentRunnable
    {

        private final CheckPermissionProgressFragment this$0;
        private final CheckPermissionsResults val$data;

        public final void go()
        {
            dismiss();
            Object obj = mTarget;
            if (obj instanceof Listener)
            {
                obj = (Listener)obj;
            } else
            {
                obj = null;
            }
            if (data == null)
            {
                ((Listener) (obj)).onPermissionsCheckFinished();
                return;
            }
            if ("NONE_FIXABLE".equals(data.fixabilitySummary))
            {
                ((Listener) (obj)).showFilesNotSharedDialog(numFiles);
                return;
            } else
            {
                ((Listener) (obj)).showFixPermissionsDialog(null, data.potentialFixes, numFiles, accountName);
                return;
            }
        }

        _cls1(Fragment fragment, CheckPermissionsResults checkpermissionsresults)
        {
            this$0 = CheckPermissionProgressFragment.this;
            data = checkpermissionsresults;
            super(fragment);
        }

        private class Listener
        {

            public abstract void onPermissionsCheckFinished();

            public abstract void showFilesNotSharedDialog(int i);

            public abstract void showFixPermissionsDialog(FixPermissionDialogState fixpermissiondialogstate, ArrayList arraylist, int i, String s);
        }

    }

}
