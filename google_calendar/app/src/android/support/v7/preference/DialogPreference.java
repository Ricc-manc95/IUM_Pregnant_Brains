// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.util.TypedValue;

// Referenced classes of package android.support.v7.preference:
//            Preference, PreferenceManager

public class DialogPreference extends Preference
{
    public static interface TargetFragment
    {

        public abstract Preference findPreference(CharSequence charsequence);
    }


    public Drawable mDialogIcon;
    public int mDialogLayoutResId;
    public CharSequence mDialogMessage;
    public CharSequence mDialogTitle;
    public CharSequence mNegativeButtonText;
    public CharSequence mPositiveButtonText;

    public DialogPreference(Context context, AttributeSet attributeset)
    {
        int i = 0x7f01022c;
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x7f01022c, typedvalue, true);
        if (typedvalue.resourceId == 0)
        {
            i = 0x1010091;
        }
        this(context, attributeset, i);
    }

    private DialogPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    public DialogPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, j);
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.DialogPreference, i, j);
        i = R.styleable.DialogPreference_dialogTitle;
        j = R.styleable.DialogPreference_android_dialogTitle;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mDialogTitle = context;
        if (mDialogTitle == null)
        {
            mDialogTitle = super.mTitle;
        }
        i = R.styleable.DialogPreference_dialogMessage;
        j = R.styleable.DialogPreference_android_dialogMessage;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mDialogMessage = context;
        i = R.styleable.DialogPreference_dialogIcon;
        j = R.styleable.DialogPreference_android_dialogIcon;
        attributeset = typedarray.getDrawable(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getDrawable(j);
        }
        mDialogIcon = context;
        i = R.styleable.DialogPreference_positiveButtonText;
        j = R.styleable.DialogPreference_android_positiveButtonText;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mPositiveButtonText = context;
        i = R.styleable.DialogPreference_negativeButtonText;
        j = R.styleable.DialogPreference_android_negativeButtonText;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mNegativeButtonText = context;
        mDialogLayoutResId = typedarray.getResourceId(R.styleable.DialogPreference_dialogLayout, typedarray.getResourceId(R.styleable.DialogPreference_android_dialogLayout, 0));
        typedarray.recycle();
    }

    protected void onClick()
    {
        PreferenceManager preferencemanager = super.mPreferenceManager;
        if (preferencemanager.mOnDisplayPreferenceDialogListener != null)
        {
            preferencemanager.mOnDisplayPreferenceDialogListener.onDisplayPreferenceDialog(this);
        }
    }
}
