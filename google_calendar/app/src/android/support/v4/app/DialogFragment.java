// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v4.app;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;

// Referenced classes of package android.support.v4.app:
//            Fragment, FragmentManager, FragmentTransaction, FragmentHostCallback, 
//            FragmentActivity

public class DialogFragment extends Fragment
    implements android.content.DialogInterface.OnCancelListener, android.content.DialogInterface.OnDismissListener
{

    private int mBackStackId;
    public boolean mCancelable;
    public Dialog mDialog;
    private boolean mDismissed;
    private boolean mShownByMe;
    public boolean mShowsDialog;
    private int mStyle;
    private int mTheme;
    private boolean mViewDestroyed;

    public DialogFragment()
    {
        mStyle = 0;
        mTheme = 0;
        mCancelable = true;
        mShowsDialog = true;
        mBackStackId = -1;
    }

    private final void dismissInternal(boolean flag)
    {
        if (mDismissed)
        {
            return;
        }
        mDismissed = true;
        mShownByMe = false;
        if (mDialog != null)
        {
            mDialog.dismiss();
        }
        mViewDestroyed = true;
        if (mBackStackId >= 0)
        {
            super.mFragmentManager.popBackStack(mBackStackId, 1);
            mBackStackId = -1;
            return;
        }
        FragmentTransaction fragmenttransaction = super.mFragmentManager.beginTransaction();
        fragmenttransaction.remove(this);
        if (flag)
        {
            fragmenttransaction.commitAllowingStateLoss();
            return;
        } else
        {
            fragmenttransaction.commit();
            return;
        }
    }

    public void dismiss()
    {
        dismissInternal(false);
    }

    public void dismissAllowingStateLoss()
    {
        dismissInternal(true);
    }

    public Dialog getDialog()
    {
        return mDialog;
    }

    public boolean getShowsDialog()
    {
        return mShowsDialog;
    }

    public void onActivityCreated(Bundle bundle)
    {
        super.onActivityCreated(bundle);
        if (mShowsDialog)
        {
            Object obj = super.mView;
            if (obj != null)
            {
                if (((View) (obj)).getParent() != null)
                {
                    throw new IllegalStateException("DialogFragment can not be attached to a container view");
                }
                mDialog.setContentView(((View) (obj)));
            }
            if (super.mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)super.mHost.mActivity;
            }
            if (obj != null)
            {
                mDialog.setOwnerActivity(((android.app.Activity) (obj)));
            }
            mDialog.setCancelable(mCancelable);
            mDialog.setOnCancelListener(this);
            mDialog.setOnDismissListener(this);
            if (bundle != null)
            {
                bundle = bundle.getBundle("android:savedDialogState");
                if (bundle != null)
                {
                    mDialog.onRestoreInstanceState(bundle);
                    return;
                }
            }
        }
    }

    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (!mShownByMe)
        {
            mDismissed = false;
        }
    }

    public void onCancel(DialogInterface dialoginterface)
    {
    }

    public void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        boolean flag;
        if (mContainerId == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        mShowsDialog = flag;
        if (bundle != null)
        {
            mStyle = bundle.getInt("android:style", 0);
            mTheme = bundle.getInt("android:theme", 0);
            mCancelable = bundle.getBoolean("android:cancelable", true);
            mShowsDialog = bundle.getBoolean("android:showsDialog", mShowsDialog);
            mBackStackId = bundle.getInt("android:backStackId", -1);
        }
    }

    public Dialog onCreateDialog(Bundle bundle)
    {
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        return new Dialog(bundle, mTheme);
    }

    public void onDestroyView()
    {
        super.onDestroyView();
        if (mDialog != null)
        {
            mViewDestroyed = true;
            mDialog.dismiss();
            mDialog = null;
        }
    }

    public void onDetach()
    {
        super.onDetach();
        if (!mShownByMe && !mDismissed)
        {
            mDismissed = true;
        }
    }

    public void onDismiss(DialogInterface dialoginterface)
    {
        if (!mViewDestroyed)
        {
            dismissInternal(true);
        }
    }

    public final LayoutInflater onGetLayoutInflater(Bundle bundle)
    {
        if (!mShowsDialog)
        {
            return super.onGetLayoutInflater(bundle);
        }
        mDialog = onCreateDialog(bundle);
        if (mDialog == null) goto _L2; else goto _L1
_L1:
        bundle = mDialog;
        mStyle;
        JVM INSTR tableswitch 1 3: default 64
    //                   1 89
    //                   2 89
    //                   3 80;
           goto _L3 _L4 _L4 _L5
_L3:
        return (LayoutInflater)mDialog.getContext().getSystemService("layout_inflater");
_L5:
        bundle.getWindow().addFlags(24);
_L4:
        bundle.requestWindowFeature(1);
        if (true) goto _L3; else goto _L2
_L2:
        return (LayoutInflater)mHost.mContext.getSystemService("layout_inflater");
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        if (mDialog != null)
        {
            Bundle bundle1 = mDialog.onSaveInstanceState();
            if (bundle1 != null)
            {
                bundle.putBundle("android:savedDialogState", bundle1);
            }
        }
        if (mStyle != 0)
        {
            bundle.putInt("android:style", mStyle);
        }
        if (mTheme != 0)
        {
            bundle.putInt("android:theme", mTheme);
        }
        if (!mCancelable)
        {
            bundle.putBoolean("android:cancelable", mCancelable);
        }
        if (!mShowsDialog)
        {
            bundle.putBoolean("android:showsDialog", mShowsDialog);
        }
        if (mBackStackId != -1)
        {
            bundle.putInt("android:backStackId", mBackStackId);
        }
    }

    public void onStart()
    {
        super.onStart();
        if (mDialog != null)
        {
            mViewDestroyed = false;
            mDialog.show();
        }
    }

    public void onStop()
    {
        super.onStop();
        if (mDialog != null)
        {
            mDialog.hide();
        }
    }

    public final int show(FragmentTransaction fragmenttransaction, String s)
    {
        mDismissed = false;
        mShownByMe = true;
        fragmenttransaction.add(this, s);
        mViewDestroyed = false;
        mBackStackId = fragmenttransaction.commit();
        return mBackStackId;
    }

    public void show(FragmentManager fragmentmanager, String s)
    {
        mDismissed = false;
        mShownByMe = true;
        fragmentmanager = fragmentmanager.beginTransaction();
        fragmentmanager.add(this, s);
        fragmentmanager.commit();
    }
}
