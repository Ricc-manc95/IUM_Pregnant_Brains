// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Checkable;
import android.widget.CompoundButton;

// Referenced classes of package android.support.v7.preference:
//            TwoStatePreference, Preference, PreferenceViewHolder

public class CheckBoxPreference extends TwoStatePreference
{
    final class Listener
        implements android.widget.CompoundButton.OnCheckedChangeListener
    {

        private final CheckBoxPreference this$0;

        public final void onCheckedChanged(CompoundButton compoundbutton, boolean flag)
        {
            boolean flag2 = true;
            CheckBoxPreference checkboxpreference = CheckBoxPreference.this;
            boolean flag1;
            if (((Preference) (checkboxpreference)).mOnChangeListener == null || ((Preference) (checkboxpreference)).mOnChangeListener.onPreferenceChange(checkboxpreference, Boolean.valueOf(flag)))
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
            this$0 = CheckBoxPreference.this;
            super();
        }
    }


    private final Listener mListener;

    public CheckBoxPreference(Context context, AttributeSet attributeset)
    {
        int i = 0x7f01022a;
        TypedValue typedvalue = new TypedValue();
        context.getTheme().resolveAttribute(0x7f01022a, typedvalue, true);
        if (typedvalue.resourceId == 0)
        {
            i = 0x101008f;
        }
        this(context, attributeset, i);
    }

    private CheckBoxPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    private CheckBoxPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, 0);
        mListener = new Listener();
        TypedArray typedarray = context.obtainStyledAttributes(attributeset, R.styleable.CheckBoxPreference, i, 0);
        i = R.styleable.CheckBoxPreference_summaryOn;
        j = R.styleable.CheckBoxPreference_android_summaryOn;
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
        i = R.styleable.CheckBoxPreference_summaryOff;
        j = R.styleable.CheckBoxPreference_android_summaryOff;
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
        super.mDisableDependentsState = typedarray.getBoolean(R.styleable.CheckBoxPreference_disableDependentsState, typedarray.getBoolean(R.styleable.CheckBoxPreference_android_disableDependentsState, false));
        typedarray.recycle();
    }

    private final void syncCheckboxView(View view)
    {
        if (view instanceof CompoundButton)
        {
            ((CompoundButton)view).setOnCheckedChangeListener(null);
        }
        if (view instanceof Checkable)
        {
            ((Checkable)view).setChecked(mChecked);
        }
        if (view instanceof CompoundButton)
        {
            ((CompoundButton)view).setOnCheckedChangeListener(mListener);
        }
    }

    public final void onBindViewHolder(PreferenceViewHolder preferenceviewholder)
    {
        super.onBindViewHolder(preferenceviewholder);
        syncCheckboxView(preferenceviewholder.findViewById(0x1020001));
        syncSummaryView(preferenceviewholder.findViewById(0x1020010));
    }

    protected final void performClick(View view)
    {
        super.performClick(view);
        if (((AccessibilityManager)super.mContext.getSystemService("accessibility")).isEnabled())
        {
            syncCheckboxView(view.findViewById(0x1020001));
            syncSummaryView(view.findViewById(0x1020010));
        }
    }
}
