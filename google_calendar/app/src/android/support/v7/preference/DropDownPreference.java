// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

// Referenced classes of package android.support.v7.preference:
//            ListPreference, PreferenceViewHolder

public class DropDownPreference extends ListPreference
{

    private final ArrayAdapter mAdapter;
    private final Context mContext;
    private final android.widget.AdapterView.OnItemSelectedListener mItemSelectedListener;
    private Spinner mSpinner;

    public DropDownPreference(Context context, AttributeSet attributeset)
    {
        this(context, attributeset, 0x7f01022f);
    }

    private DropDownPreference(Context context, AttributeSet attributeset, int i)
    {
        this(context, attributeset, i, 0);
    }

    private DropDownPreference(Context context, AttributeSet attributeset, int i, int j)
    {
        super(context, attributeset, i, 0);
        mItemSelectedListener = new _cls1();
        mContext = context;
        mAdapter = new ArrayAdapter(mContext, 0x1090009);
        updateEntries();
    }

    private final void updateEntries()
    {
        mAdapter.clear();
        if (super.mEntries != null)
        {
            CharSequence acharsequence[] = super.mEntries;
            int j = acharsequence.length;
            for (int i = 0; i < j; i++)
            {
                CharSequence charsequence = acharsequence[i];
                mAdapter.add(charsequence.toString());
            }

        }
    }

    protected final void notifyChanged()
    {
        super.notifyChanged();
        mAdapter.notifyDataSetChanged();
    }

    public final void onBindViewHolder(PreferenceViewHolder preferenceviewholder)
    {
        Spinner spinner;
        String s;
        CharSequence acharsequence[];
        int i;
        mSpinner = (Spinner)preferenceviewholder.itemView.findViewById(0x7f1002d8);
        mSpinner.setAdapter(mAdapter);
        mSpinner.setOnItemSelectedListener(mItemSelectedListener);
        spinner = mSpinner;
        s = super.mValue;
        acharsequence = super.mEntryValues;
        if (s == null || acharsequence == null)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        i = acharsequence.length - 1;
_L3:
        if (i < 0)
        {
            break MISSING_BLOCK_LABEL_108;
        }
        if (!acharsequence[i].equals(s)) goto _L2; else goto _L1
_L1:
        spinner.setSelection(i);
        super.onBindViewHolder(preferenceviewholder);
        return;
_L2:
        i--;
          goto _L3
        i = -1;
          goto _L1
    }

    protected final void onClick()
    {
        mSpinner.performClick();
    }

    public final void setEntries(CharSequence acharsequence[])
    {
        super.setEntries(acharsequence);
        updateEntries();
    }

    private class _cls1
        implements android.widget.AdapterView.OnItemSelectedListener
    {

        private final DropDownPreference this$0;

        public final void onItemSelected(AdapterView adapterview, View view, int i, long l)
        {
            if (i >= 0)
            {
                adapterview = mEntryValues[i].toString();
                if (!adapterview.equals(mValue))
                {
                    view = DropDownPreference.this;
                    if (((Preference) (view)).mOnChangeListener == null || ((Preference) (view)).mOnChangeListener.onPreferenceChange(view, adapterview))
                    {
                        i = 1;
                    } else
                    {
                        i = 0;
                    }
                    if (i != 0)
                    {
                        setValue(adapterview);
                    }
                }
            }
        }

        public final void onNothingSelected(AdapterView adapterview)
        {
        }

        _cls1()
        {
            this$0 = DropDownPreference.this;
            super();
        }
    }

}
