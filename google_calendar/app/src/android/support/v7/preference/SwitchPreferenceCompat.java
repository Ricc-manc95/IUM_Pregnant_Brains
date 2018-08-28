// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.widget.SwitchCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;

// Referenced classes of package android.support.v7.preference:
//            TwoStatePreference, Preference, PreferenceViewHolder

public class SwitchPreferenceCompat extends TwoStatePreference
{
    final class Listener
        implements android.widget.CompoundButton.OnCheckedChangeListener
    {

        private final SwitchPreferenceCompat this$0;

        public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            boolean flag2 = true;
            SwitchPreferenceCompat switchpreferencecompat = SwitchPreferenceCompat.this;
            boolean flag1;
            if (((Preference) (switchpreferencecompat)).mOnChangeListener == null || ((Preference) (switchpreferencecompat)).mOnChangeListener.onPreferenceChange(switchpreferencecompat, Boolean.valueOf(flag)))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (!flag1)
            {
                if (!flag)
                {
                    flag = flag2;
                } else
                {
                    flag = false;
                }
                compoundbutton.setChecked(flag);
                return;
            } else
            {
                setChecked(flag);
                return;
            }
        }

        Listener()
        {
            this$0 = SwitchPreferenceCompat.this;
            super();
        }
    }


    private final Listener mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;

    public SwitchPreferenceCompat(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f010237);
    }

    private SwitchPreferenceCompat(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    private SwitchPreferenceCompat(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, 0);
        mListener = new Listener();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.SwitchPreferenceCompat, i, 0);
        i = R.styleable.SwitchPreferenceCompat_summaryOn;
        j = R.styleable.SwitchPreferenceCompat_android_summaryOn;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        super.mSummaryOn = context;
        if (super.mChecked)
        {
            notifyChanged();
        }
        i = R.styleable.SwitchPreferenceCompat_summaryOff;
        j = R.styleable.SwitchPreferenceCompat_android_summaryOff;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        super.mSummaryOff = context;
        if (!super.mChecked)
        {
            notifyChanged();
        }
        i = R.styleable.SwitchPreferenceCompat_switchTextOn;
        j = R.styleable.SwitchPreferenceCompat_android_switchTextOn;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mSwitchOn = context;
        notifyChanged();
        i = R.styleable.SwitchPreferenceCompat_switchTextOff;
        j = R.styleable.SwitchPreferenceCompat_android_switchTextOff;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mSwitchOff = context;
        notifyChanged();
        super.mDisableDependentsState = typedarray.getBoolean(R.styleable.SwitchPreferenceCompat_disableDependentsState, typedarray.getBoolean(R.styleable.SwitchPreferenceCompat_android_disableDependentsState, false));
        typedarray.recycle();
    }

    private final void syncSwitchView(View view)
    {
        if (view instanceof SwitchCompat)
        {
            ((SwitchCompat)view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable)
        {
            ((Checkable)view).setChecked(mChecked);
        }
        if (view instanceof SwitchCompat)
        {
            view = (SwitchCompat)view;
            view.mTextOn = mSwitchOn;
            view.requestLayout();
            view.mTextOff = mSwitchOff;
            view.requestLayout();
            view.setOnCheckedChangeListener(mListener);
        }
    }

    public final void onBindViewHolder(PreferenceViewHolder preferenceviewholder)
    {
        super.onBindViewHolder(preferenceviewholder);
        syncSwitchView(preferenceviewholder.findViewById(0x7f1002dd));
        syncSummaryView(preferenceviewholder.findViewById(0x1020010));
    }

    protected final void performClick(View view)
    {
        super.performClick(view);
        if (((AccessibilityManager)super.mContext.getSystemService("accessibility")).isEnabled())
        {
            syncSwitchView(view.findViewById(0x7f1002dd));
            syncSummaryView(view.findViewById(0x1020010));
        }
    }
}
