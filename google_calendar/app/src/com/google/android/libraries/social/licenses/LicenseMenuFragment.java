// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.social.licenses;

import android.arch.lifecycle.ViewModelStoreOwner;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.support.v4.app.LoaderManager;
import android.support.v4.app.LoaderManagerImpl;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package com.google.android.libraries.social.licenses:
//            LicenseLoader

public final class LicenseMenuFragment extends Fragment
    implements android.support.v4.app.LoaderManager.LoaderCallbacks
{

    public LicenseSelectionListener licenseSelectionListener;
    private ArrayAdapter listAdapter;

    public LicenseMenuFragment()
    {
    }

    public final void onAttach(Context context)
    {
        super.onAttach(context);
        context = super.mParentFragment;
        if (context instanceof LicenseSelectionListener)
        {
            licenseSelectionListener = (LicenseSelectionListener)context;
        } else
        {
            if (super.mHost == null)
            {
                context = null;
            } else
            {
                context = (FragmentActivity)super.mHost.mActivity;
            }
            if (context instanceof LicenseSelectionListener)
            {
                licenseSelectionListener = (LicenseSelectionListener)context;
                return;
            }
        }
    }

    public final Loader onCreateLoader$514KOOBECHP6UQB45TNN6BQ2ELN68R357CKKOOBECHP6UQB45TPNAS3GDTP78BRM6GNM6RREEHIMST1F9HNM2P35E8TG____0(Bundle bundle)
    {
        Object obj = null;
        bundle = null;
        Bundle bundle1 = getArguments();
        if (bundle1 != null && bundle1.getStringArrayList("pluginLicensePaths") != null && !bundle1.getStringArrayList("pluginLicensePaths").isEmpty())
        {
            if (super.mHost != null)
            {
                bundle = (FragmentActivity)super.mHost.mActivity;
            }
            return new LicenseLoader(bundle, bundle1.getStringArrayList("pluginLicensePaths"));
        }
        if (super.mHost == null)
        {
            bundle = obj;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return new LicenseLoader(bundle);
    }

    public final View onCreateView(LayoutInflater layoutinflater, ViewGroup viewgroup, Bundle bundle)
    {
        return layoutinflater.inflate(0x7f0500a1, viewgroup, false);
    }

    public final void onDestroy()
    {
        super.onDestroy();
        Object obj;
        if (super.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)super.mHost.mActivity;
        }
        (new LoaderManagerImpl(((android.arch.lifecycle.LifecycleOwner) (obj)), ((ViewModelStoreOwner)obj).getViewModelStore())).destroyLoader(54321);
    }

    public final void onDetach()
    {
        super.onDetach();
        licenseSelectionListener = null;
    }

    public final void onLoadFinished(Loader loader, Object obj)
    {
        loader = (List)obj;
        listAdapter.clear();
        listAdapter.addAll(loader);
        listAdapter.notifyDataSetChanged();
    }

    public final void onLoaderReset$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5THMURJKCLN78BQCDTGM8PBI7CKLC___0()
    {
        listAdapter.clear();
        listAdapter.notifyDataSetChanged();
    }

    public final void onViewCreated(View view, Bundle bundle)
    {
        super.onViewCreated(view, bundle);
        class .Lambda._cls0
            implements android.widget.AdapterView.OnItemClickListener
        {

            private final LicenseMenuFragment arg$1;

            public final void onItemClick(AdapterView adapterview, View view1, int i, long l)
            {
                view1 = arg$1;
                adapterview = (License)adapterview.getItemAtPosition(i);
                if (((LicenseMenuFragment) (view1)).licenseSelectionListener != null)
                {
                    ((LicenseMenuFragment) (view1)).licenseSelectionListener.onLicenseSelected(adapterview);
                }
            }

            .Lambda._cls0()
            {
                arg$1 = LicenseMenuFragment.this;
            }
        }

        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        listAdapter = new ArrayAdapter(bundle, 0x7f05009e, 0x7f100227, new ArrayList());
        (new LoaderManagerImpl(bundle, ((ViewModelStoreOwner)bundle).getViewModelStore())).initLoader(54321, null, this);
        view = (ListView)view.findViewById(0x7f10022b);
        view.setAdapter(listAdapter);
        view.setOnItemClickListener(new .Lambda._cls0());
    }

    private class LicenseSelectionListener
    {

        public abstract void onLicenseSelected(License license);
    }

}
