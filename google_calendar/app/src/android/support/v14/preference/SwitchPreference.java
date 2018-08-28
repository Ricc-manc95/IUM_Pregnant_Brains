// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v14.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceViewHolder;
import android.support.v7.preference.TwoStatePreference;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;
import android.widget.Switch;

public class SwitchPreference extends TwoStatePreference
{
    final class Listener
        implements android.widget.CompoundButton.OnCheckedChangeListener
    {

        private final SwitchPreference this$0;

        public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            boolean flag2 = true;
            SwitchPreference switchpreference = SwitchPreference.this;
            boolean flag1;
            if (((Preference) (switchpreference)).mOnChangeListener == null || ((Preference) (switchpreference)).mOnChangeListener.onPreferenceChange(switchpreference, Boolean.valueOf(flag)))
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
            this$0 = SwitchPreference.this;
            super();
        }
    }


    private final Listener mListener;
    private CharSequence mSwitchOff;
    private CharSequence mSwitchOn;

    public SwitchPreference(Context context)
    {
        this(context, null);
    }

    public SwitchPreference(Context context, AttributeSet attributeset)
    {
        int i = 0x7f010236;
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x7f010236, typedvalue, true);
        if (typedvalue.resourceId == 0)
        {
            i = 0x101036d;
        }
        this(context, attributeset, i);
    }

    private SwitchPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    private SwitchPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, 0);
        mListener = new Listener();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, android.support.v7.preference.R.styleable.SwitchPreference, i, 0);
        i = android.support.v7.preference.R.styleable.SwitchPreference_summaryOn;
        j = android.support.v7.preference.R.styleable.SwitchPreference_android_summaryOn;
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
        i = android.support.v7.preference.R.styleable.SwitchPreference_summaryOff;
        j = android.support.v7.preference.R.styleable.SwitchPreference_android_summaryOff;
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
        i = android.support.v7.preference.R.styleable.SwitchPreference_switchTextOn;
        j = android.support.v7.preference.R.styleable.SwitchPreference_android_switchTextOn;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mSwitchOn = context;
        notifyChanged();
        i = android.support.v7.preference.R.styleable.SwitchPreference_switchTextOff;
        j = android.support.v7.preference.R.styleable.SwitchPreference_android_switchTextOff;
        attributeset = typedarray.getString(i);
        context = attributeset;
        if (attributeset == null)
        {
            context = typedarray.getString(j);
        }
        mSwitchOff = context;
        notifyChanged();
        super.mDisableDependentsState = typedarray.getBoolean(android.support.v7.preference.R.styleable.SwitchPreference_disableDependentsState, typedarray.getBoolean(android.support.v7.preference.R.styleable.SwitchPreference_android_disableDependentsState, false));
        typedarray.recycle();
    }

    private final void syncSwitchView(View view)
    {
        if (view instanceof Switch)
        {
            ((Switch)view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable)
        {
            ((Checkable)view).setChecked(mChecked);
        }
        if (view instanceof Switch)
        {
            view = (Switch)view;
            view.setTextOn(mSwitchOn);
            view.setTextOff(mSwitchOff);
            view.setOnCheckedChangeListener(mListener);
        }
    }

    public final void onBindViewHolder(PreferenceViewHolder preferenceviewholder)
    {
        super.onBindViewHolder(preferenceviewholder);
        syncSwitchView(preferenceviewholder.findViewById(0x1020040));
        syncSummaryView(preferenceviewholder.findViewById(0x1020010));
    }

    protected final void performClick(View view)
    {
        super.performClick(view);
        if (((AccessibilityManager)super.mContext.getSystemService("accessibility")).isEnabled())
        {
            syncSwitchView(view.findViewById(0x1020040));
            syncSummaryView(view.findViewById(0x1020010));
        }
    }
}
