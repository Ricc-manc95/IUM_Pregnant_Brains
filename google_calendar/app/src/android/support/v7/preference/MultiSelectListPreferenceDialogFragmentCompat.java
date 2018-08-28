// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Bundle;
import android.support.v7.preference.internal.AbstractMultiSelectListPreference;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// Referenced classes of package android.support.v7.preference:
//            PreferenceDialogFragmentCompat, Preference

public final class MultiSelectListPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat
{

    private CharSequence mEntries[];
    public CharSequence mEntryValues[];
    public Set mNewValues;
    public boolean mPreferenceChanged;

    public MultiSelectListPreferenceDialogFragmentCompat()
    {
        mNewValues = new HashSet();
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle == null)
        {
            bundle = (AbstractMultiSelectListPreference)getPreference();
            if (bundle.getEntries() == null || bundle.getEntryValues() == null)
            {
                throw new IllegalStateException("MultiSelectListPreference requires an entries array and an entryValues array.");
            } else
            {
                mNewValues.clear();
                mNewValues.addAll(bundle.getValues());
                mPreferenceChanged = false;
                mEntries = bundle.getEntries();
                mEntryValues = bundle.getEntryValues();
                return;
            }
        } else
        {
            mNewValues.clear();
            mNewValues.addAll(bundle.getStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values"));
            mPreferenceChanged = bundle.getBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", false);
            mEntries = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries");
            mEntryValues = bundle.getCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues");
            return;
        }
    }

    public final void onDialogClosed(boolean flag)
    {
        AbstractMultiSelectListPreference abstractmultiselectlistpreference = (AbstractMultiSelectListPreference)getPreference();
        if (flag && mPreferenceChanged)
        {
            Set set = mNewValues;
            boolean flag1;
            if (((Preference) (abstractmultiselectlistpreference)).mOnChangeListener == null || ((Preference) (abstractmultiselectlistpreference)).mOnChangeListener.onPreferenceChange(abstractmultiselectlistpreference, set))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                abstractmultiselectlistpreference.setValues(set);
            }
        }
        mPreferenceChanged = false;
    }

    protected final void onPrepareDialogBuilder(android.support.v7.app.AlertDialog.Builder builder)
    {
        super.onPrepareDialogBuilder(builder);
        int j = mEntryValues.length;
        boolean aflag[] = new boolean[j];
        for (int i = 0; i < j; i++)
        {
            aflag[i] = mNewValues.contains(mEntryValues[i].toString());
        }

        CharSequence acharsequence[] = mEntries;
        _cls1 _lcls1 = new _cls1();
        builder.P.mItems = acharsequence;
        builder.P.mOnCheckboxClickListener = _lcls1;
        builder.P.mCheckedItems = aflag;
        builder.P.mIsMultiChoice = true;
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putStringArrayList("MultiSelectListPreferenceDialogFragmentCompat.values", new ArrayList(mNewValues));
        bundle.putBoolean("MultiSelectListPreferenceDialogFragmentCompat.changed", mPreferenceChanged);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entries", mEntries);
        bundle.putCharSequenceArray("MultiSelectListPreferenceDialogFragmentCompat.entryValues", mEntryValues);
    }

    private class _cls1
        implements android.content.DialogInterface.OnMultiChoiceClickListener
    {

        private final MultiSelectListPreferenceDialogFragmentCompat this$0;

        public final void onClick(DialogInterface dialoginterface, int i, boolean flag)
        {
            if (flag)
            {
                mPreferenceChanged = mPreferenceChanged | mNewValues.add(mEntryValues[i].toString());
                return;
            } else
            {
                mPreferenceChanged = mPreferenceChanged | mNewValues.remove(mEntryValues[i].toString());
                return;
            }
        }

        _cls1()
        {
            this$0 = MultiSelectListPreferenceDialogFragmentCompat.this;
            super();
        }
    }

}
