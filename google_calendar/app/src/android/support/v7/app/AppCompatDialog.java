// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.TypedValue;
import android.view.View;

// Referenced classes of package android.support.v7.app:
//            AppCompatCallback, AppCompatDelegateImpl, AppCompatDelegate

public class AppCompatDialog extends Dialog
    implements AppCompatCallback
{

    public AppCompatDelegate mDelegate;

    public AppCompatDialog(Context context, int i)
    {
        int j = i;
        if (i == 0)
        {
            TypedValue typedvalue = new TypedValue();
            context.getTheme().resolveAttribute(0x7f010097, typedvalue, true);
            j = typedvalue.resourceId;
        }
        super(context, j);
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.onCreate(null);
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.applyDayNight();
    }

    public void addContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.addContentView(view, layoutparams);
    }

    public View findViewById(int i)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        return mDelegate.findViewById(i);
    }

    public void invalidateOptionsMenu()
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.invalidateOptionsMenu();
    }

    public void onCreate(Bundle bundle)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.installViewFactory();
        super.onCreate(bundle);
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.onCreate(bundle);
    }

    protected void onStop()
    {
        super.onStop();
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.onStop();
    }

    public final void onSupportActionModeFinished$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKTIILG_0()
    {
    }

    public final void onSupportActionModeStarted$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKTIILG_0()
    {
    }

    public final ActionMode onWindowStartingSupportActionMode$51662RJ4E9NMIP1FEDQN0S3FE9Q2UTHN5TR6IPBN5T0M6T39DTN4QRR4CKI46OBCDHH62ORB7CKKOOBECHP6UQB45TPNAS3GDTP78BRM6SNNCQB5ESNK2ORKD5NMSJBFCHIJM___0()
    {
        return null;
    }

    public void setContentView(int i)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.setContentView(i);
    }

    public void setContentView(View view)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.setContentView(view);
    }

    public void setContentView(View view, android.view.ViewGroup.LayoutParams layoutparams)
    {
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.setContentView(view, layoutparams);
    }

    public void setTitle(int i)
    {
        super.setTitle(i);
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.setTitle(getContext().getString(i));
    }

    public void setTitle(CharSequence charsequence)
    {
        super.setTitle(charsequence);
        if (mDelegate == null)
        {
            mDelegate = new AppCompatDelegateImpl(getContext(), getWindow(), this);
        }
        mDelegate.setTitle(charsequence);
    }
}
