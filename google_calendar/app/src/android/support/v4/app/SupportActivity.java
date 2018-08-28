// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleOwner;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.ReportFragment;
import android.os.Bundle;
import android.support.v4.util.SimpleArrayMap;

public class SupportActivity extends Activity
    implements LifecycleOwner
{

    private LifecycleRegistry mLifecycleRegistry;

    public SupportActivity()
    {
        new SimpleArrayMap();
        mLifecycleRegistry = new LifecycleRegistry(this);
    }

    public Lifecycle getLifecycle()
    {
        return mLifecycleRegistry;
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        bundle = getFragmentManager();
        if (bundle.findFragmentByTag("android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag") == null)
        {
            bundle.beginTransaction().add(new ReportFragment(), "android.arch.lifecycle.LifecycleDispatcher.report_fragment_tag").commit();
            bundle.executePendingTransactions();
        }
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        mLifecycleRegistry.moveToState(android.arch.lifecycle.Lifecycle.State.CREATED);
        super.onSaveInstanceState(bundle);
    }
}
