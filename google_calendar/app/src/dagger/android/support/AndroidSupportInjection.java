// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package dagger.android.support;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import dagger.android.AndroidInjector;
import dagger.internal.Preconditions;

// Referenced classes of package dagger.android.support:
//            HasSupportFragmentInjector

public final class AndroidSupportInjection
{

    public static void inject(Fragment fragment)
    {
        Object obj;
        if (fragment == null)
        {
            throw new NullPointerException("fragment");
        }
        obj = fragment;
_L4:
        Fragment fragment1 = ((Fragment) (obj)).mParentFragment;
        if (fragment1 == null) goto _L2; else goto _L1
_L1:
        obj = fragment1;
        if (!(fragment1 instanceof HasSupportFragmentInjector)) goto _L4; else goto _L3
_L3:
        obj = (HasSupportFragmentInjector)fragment1;
_L6:
        AndroidInjector androidinjector = ((HasSupportFragmentInjector) (obj)).supportFragmentInjector();
        Preconditions.checkNotNull(androidinjector, "%s.supportFragmentInjector() returned null", obj.getClass());
        androidinjector.inject(fragment);
        return;
_L2:
        if (fragment.mHost == null)
        {
            obj = null;
        } else
        {
            obj = (FragmentActivity)fragment.mHost.mActivity;
        }
        if (obj instanceof HasSupportFragmentInjector)
        {
            obj = (HasSupportFragmentInjector)obj;
        } else
        if (((Activity) (obj)).getApplication() instanceof HasSupportFragmentInjector)
        {
            obj = (HasSupportFragmentInjector)((Activity) (obj)).getApplication();
        } else
        {
            throw new IllegalArgumentException(String.format("No injector was found for %s", new Object[] {
                fragment.getClass().getCanonicalName()
            }));
        }
        if (true) goto _L6; else goto _L5
_L5:
    }
}
