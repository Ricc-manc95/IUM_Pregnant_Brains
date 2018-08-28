// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

// Referenced classes of package android.support.v7.preference:
//            DialogPreference

public abstract class PreferenceDialogFragmentCompat extends DialogFragment
    implements android.content.DialogInterface.OnClickListener
{

    private BitmapDrawable mDialogIcon;
    private int mDialogLayoutRes;
    private CharSequence mDialogMessage;
    private CharSequence mDialogTitle;
    private CharSequence mNegativeButtonText;
    private CharSequence mPositiveButtonText;
    private DialogPreference mPreference;
    private int mWhichButtonClicked;

    public PreferenceDialogFragmentCompat()
    {
    }

    public final DialogPreference getPreference()
    {
        if (mPreference == null)
        {
            String s = getArguments().getString("key");
            mPreference = (DialogPreference)((DialogPreference.TargetFragment)super.mTarget).findPreference(s);
        }
        return mPreference;
    }

    protected boolean needInputMethod()
    {
        return false;
    }

    public void onBindDialogView(View view)
    {
        view = view.findViewById(0x102000b);
        if (view != null)
        {
            CharSequence charsequence = mDialogMessage;
            byte byte0 = 8;
            if (!TextUtils.isEmpty(charsequence))
            {
                if (view instanceof TextView)
                {
                    ((TextView)view).setText(charsequence);
                }
                byte0 = 0;
            }
            if (view.getVisibility() != byte0)
            {
                view.setVisibility(byte0);
            }
        }
    }

    public void onClick(DialogInterface dialoginterface, int i)
    {
        mWhichButtonClicked = i;
    }

    public void onCreate(Bundle bundle)
    {
        Object obj;
        String s;
        super.onCreate(bundle);
        obj = super.mTarget;
        if (!(obj instanceof DialogPreference.TargetFragment))
        {
            throw new IllegalStateException("Target fragment must implement TargetFragment interface");
        }
        obj = (DialogPreference.TargetFragment)obj;
        s = getArguments().getString("key");
        if (bundle != null) goto _L2; else goto _L1
_L1:
        mPreference = (DialogPreference)((DialogPreference.TargetFragment) (obj)).findPreference(s);
        mDialogTitle = mPreference.mDialogTitle;
        mPositiveButtonText = mPreference.mPositiveButtonText;
        mNegativeButtonText = mPreference.mNegativeButtonText;
        mDialogMessage = mPreference.mDialogMessage;
        mDialogLayoutRes = mPreference.mDialogLayoutResId;
        bundle = mPreference.mDialogIcon;
        if (bundle != null && !(bundle instanceof BitmapDrawable)) goto _L4; else goto _L3
_L3:
        mDialogIcon = (BitmapDrawable)bundle;
_L6:
        return;
_L4:
        Bitmap bitmap = Bitmap.createBitmap(bundle.getIntrinsicWidth(), bundle.getIntrinsicHeight(), android.graphics.Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        bundle.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        bundle.draw(canvas);
        mDialogIcon = new BitmapDrawable(requireContext().getResources(), bitmap);
        return;
_L2:
        mDialogTitle = bundle.getCharSequence("PreferenceDialogFragment.title");
        mPositiveButtonText = bundle.getCharSequence("PreferenceDialogFragment.positiveText");
        mNegativeButtonText = bundle.getCharSequence("PreferenceDialogFragment.negativeText");
        mDialogMessage = bundle.getCharSequence("PreferenceDialogFragment.message");
        mDialogLayoutRes = bundle.getInt("PreferenceDialogFragment.layout", 0);
        bundle = (Bitmap)bundle.getParcelable("PreferenceDialogFragment.icon");
        if (bundle != null)
        {
            mDialogIcon = new BitmapDrawable(requireContext().getResources(), bundle);
            return;
        }
        if (true) goto _L6; else goto _L5
_L5:
    }

    public final Dialog onCreateDialog(Bundle bundle)
    {
        Object obj = null;
        android.support.v7.app.AlertDialog.Builder builder;
        Object obj1;
        int i;
        if (super.mHost == null)
        {
            bundle = null;
        } else
        {
            bundle = (FragmentActivity)super.mHost.mActivity;
        }
        mWhichButtonClicked = -2;
        builder = new android.support.v7.app.AlertDialog.Builder(bundle);
        obj1 = mDialogTitle;
        builder.P.mTitle = ((CharSequence) (obj1));
        obj1 = mDialogIcon;
        builder.P.mIcon = ((Drawable) (obj1));
        obj1 = mPositiveButtonText;
        builder.P.mPositiveButtonText = ((CharSequence) (obj1));
        builder.P.mPositiveButtonListener = this;
        obj1 = mNegativeButtonText;
        builder.P.mNegativeButtonText = ((CharSequence) (obj1));
        builder.P.mNegativeButtonListener = this;
        i = mDialogLayoutRes;
        if (i == 0)
        {
            bundle = obj;
        } else
        {
            bundle = LayoutInflater.from(bundle).inflate(i, null);
        }
        if (bundle != null)
        {
            onBindDialogView(bundle);
            builder.P.mView = bundle;
            builder.P.mViewLayoutResId = 0;
            builder.P.mViewSpacingSpecified = false;
        } else
        {
            bundle = mDialogMessage;
            builder.P.mMessage = bundle;
        }
        onPrepareDialogBuilder(builder);
        bundle = builder.create();
        if (needInputMethod())
        {
            bundle.getWindow().setSoftInputMode(5);
        }
        return bundle;
    }

    public abstract void onDialogClosed(boolean flag);

    public void onDismiss(DialogInterface dialoginterface)
    {
        super.onDismiss(dialoginterface);
        boolean flag;
        if (mWhichButtonClicked == -1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        onDialogClosed(flag);
    }

    protected void onPrepareDialogBuilder(android.support.v7.app.AlertDialog.Builder builder)
    {
    }

    public void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("PreferenceDialogFragment.title", mDialogTitle);
        bundle.putCharSequence("PreferenceDialogFragment.positiveText", mPositiveButtonText);
        bundle.putCharSequence("PreferenceDialogFragment.negativeText", mNegativeButtonText);
        bundle.putCharSequence("PreferenceDialogFragment.message", mDialogMessage);
        bundle.putInt("PreferenceDialogFragment.layout", mDialogLayoutRes);
        if (mDialogIcon != null)
        {
            bundle.putParcelable("PreferenceDialogFragment.icon", mDialogIcon.getBitmap());
        }
    }
}
