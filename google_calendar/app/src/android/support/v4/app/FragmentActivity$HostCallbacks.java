// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.support.v4.app:
//            FragmentHostCallback, FragmentActivity, ActivityCompat, Fragment

final class this._cls0 extends FragmentHostCallback
{

    private final FragmentActivity this$0;

    public final void onAttachFragment(Fragment fragment)
    {
        FragmentActivity.this.onAttachFragment(fragment);
    }

    public final void onDump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
        dump(s, null, printwriter, as);
    }

    public final View onFindViewById(int i)
    {
        return findViewById(i);
    }

    public final LayoutInflater onGetLayoutInflater()
    {
        return getLayoutInflater().cloneInContext(FragmentActivity.this);
    }

    public final int onGetWindowAnimations()
    {
        Window window = getWindow();
        if (window == null)
        {
            return 0;
        } else
        {
            return window.getAttributes().owAnimations;
        }
    }

    public final boolean onHasView()
    {
        Window window = getWindow();
        return window != null && window.peekDecorView() != null;
    }

    public final boolean onHasWindowAnimations()
    {
        return getWindow() != null;
    }

    public final void onRequestPermissionsFromFragment(Fragment fragment, String as[], int i)
    {
        FragmentActivity fragmentactivity;
        fragmentactivity = FragmentActivity.this;
        if (i == -1)
        {
            ActivityCompat.requestPermissions(fragmentactivity, as, i);
            return;
        }
        if ((0xffff0000 & i) != 0)
        {
            throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        }
        fragmentactivity.mRequestedPermissionsFromFragment = true;
        ActivityCompat.requestPermissions(fragmentactivity, as, (fragmentactivity.allocateRequestIndex(fragment) + 1 << 16) + (0xffff & i));
        fragmentactivity.mRequestedPermissionsFromFragment = false;
        return;
        fragment;
        fragmentactivity.mRequestedPermissionsFromFragment = false;
        throw fragment;
    }

    public final boolean onShouldSaveFragmentState$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78EP9B8______0()
    {
        return !isFinishing();
    }

    public final boolean onShouldShowRequestPermissionRationale(String s)
    {
        return ActivityCompat.shouldShowRequestPermissionRationale(FragmentActivity.this, s);
    }

    public final void onStartActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
    {
        FragmentActivity fragmentactivity;
        fragmentactivity = FragmentActivity.this;
        fragmentactivity.mStartedActivityFromFragment = true;
        if (i != -1)
        {
            break MISSING_BLOCK_LABEL_33;
        }
        ActivityCompat.startActivityForResult(fragmentactivity, intent, -1, bundle);
        fragmentactivity.mStartedActivityFromFragment = false;
        return;
        if ((0xffff0000 & i) == 0)
        {
            break MISSING_BLOCK_LABEL_59;
        }
        throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
        fragment;
        fragmentactivity.mStartedActivityFromFragment = false;
        throw fragment;
        ActivityCompat.startActivityForResult(fragmentactivity, intent, (fragmentactivity.allocateRequestIndex(fragment) + 1 << 16) + (0xffff & i), bundle);
        fragmentactivity.mStartedActivityFromFragment = false;
        return;
    }

    public final void onSupportInvalidateOptionsMenu()
    {
        supportInvalidateOptionsMenu();
    }

    public ()
    {
        this$0 = FragmentActivity.this;
        super(FragmentActivity.this);
    }
}
