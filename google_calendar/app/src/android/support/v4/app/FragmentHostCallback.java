// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import java.io.FileDescriptor;
import java.io.PrintWriter;

// Referenced classes of package android.support.v4.app:
//            FragmentContainer, FragmentManagerImpl, FragmentActivity, Fragment

public class FragmentHostCallback extends FragmentContainer
{

    public final Activity mActivity;
    public final Context mContext;
    public final FragmentManagerImpl mFragmentManager;
    public final Handler mHandler;
    private final int mWindowAnimations;

    private FragmentHostCallback(Activity activity, Context context, Handler handler, int i)
    {
        mFragmentManager = new FragmentManagerImpl();
        mActivity = activity;
        if (context == null)
        {
            throw new NullPointerException(String.valueOf("context == null"));
        }
        mContext = (Context)context;
        if (handler == null)
        {
            throw new NullPointerException(String.valueOf("handler == null"));
        } else
        {
            mHandler = (Handler)handler;
            mWindowAnimations = 0;
            return;
        }
    }

    FragmentHostCallback(FragmentActivity fragmentactivity)
    {
        this(((Activity) (fragmentactivity)), ((Context) (fragmentactivity)), fragmentactivity.mHandler, 0);
    }

    void onAttachFragment(Fragment fragment)
    {
    }

    public void onDump(String s, FileDescriptor filedescriptor, PrintWriter printwriter, String as[])
    {
    }

    public View onFindViewById(int i)
    {
        return null;
    }

    public LayoutInflater onGetLayoutInflater()
    {
        return LayoutInflater.from(mContext);
    }

    public int onGetWindowAnimations()
    {
        return mWindowAnimations;
    }

    public boolean onHasView()
    {
        return true;
    }

    public boolean onHasWindowAnimations()
    {
        return true;
    }

    public void onRequestPermissionsFromFragment(Fragment fragment, String as[], int i)
    {
    }

    public boolean onShouldSaveFragmentState$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHK5TGN0S1F8PP62PRDCLN78EP9B8______0()
    {
        return true;
    }

    public boolean onShouldShowRequestPermissionRationale(String s)
    {
        return false;
    }

    public void onStartActivityFromFragment(Fragment fragment, Intent intent, int i, Bundle bundle)
    {
        if (i != -1)
        {
            throw new IllegalStateException("Starting activity with a requestCode requires a FragmentActivity host");
        } else
        {
            mContext.startActivity(intent);
            return;
        }
    }

    public void onSupportInvalidateOptionsMenu()
    {
    }
}
