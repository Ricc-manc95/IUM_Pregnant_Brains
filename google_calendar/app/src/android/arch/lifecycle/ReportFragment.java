// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.arch.lifecycle;

import android.app.Fragment;
import android.os.Bundle;

// Referenced classes of package android.arch.lifecycle:
//            LifecycleRegistryOwner, LifecycleRegistry, LifecycleOwner

public final class ReportFragment extends Fragment
{

    private ActivityInitializationListener mProcessListener;

    public ReportFragment()
    {
    }

    private final void dispatch(Lifecycle.Event event)
    {
        Object obj = getActivity();
        if (obj instanceof LifecycleRegistryOwner)
        {
            ((LifecycleRegistryOwner)obj).getLifecycle().moveToState(LifecycleRegistry.getStateAfter(event));
        } else
        if (obj instanceof LifecycleOwner)
        {
            obj = ((LifecycleOwner)obj).getLifecycle();
            if (obj instanceof LifecycleRegistry)
            {
                ((LifecycleRegistry)obj).moveToState(LifecycleRegistry.getStateAfter(event));
                return;
            }
        }
    }

    public final void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        if (false)
        {
            throw new NullPointerException();
        } else
        {
            dispatch(Lifecycle.Event.ON_CREATE);
            return;
        }
    }

    public final void onDestroy()
    {
        super.onDestroy();
        dispatch(Lifecycle.Event.ON_DESTROY);
        mProcessListener = null;
    }

    public final void onPause()
    {
        super.onPause();
        dispatch(Lifecycle.Event.ON_PAUSE);
    }

    public final void onResume()
    {
        super.onResume();
        if (false)
        {
            throw new NullPointerException();
        } else
        {
            dispatch(Lifecycle.Event.ON_RESUME);
            return;
        }
    }

    public final void onStart()
    {
        super.onStart();
        if (false)
        {
            throw new NullPointerException();
        } else
        {
            dispatch(Lifecycle.Event.ON_START);
            return;
        }
    }

    public final void onStop()
    {
        super.onStop();
        dispatch(Lifecycle.Event.ON_STOP);
    }
}
