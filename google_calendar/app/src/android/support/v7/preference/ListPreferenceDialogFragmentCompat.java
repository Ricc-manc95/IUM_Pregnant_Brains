// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Bundle;

// Referenced classes of package android.support.v7.preference:
//            PreferenceDialogFragmentCompat, ListPreference, Preference

public final class ListPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat
{

    public int mClickedDialogEntryIndex;
    private CharSequence mEntries[];
    private CharSequence mEntryValues[];

    public ListPreferenceDialogFragmentCompat()
    {
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle == null)
        {
            bundle = (ListPreference)getPreference();
            if (((ListPreference) (bundle)).mEntries == null || ((ListPreference) (bundle)).mEntryValues == null)
            {
                throw new IllegalStateException("ListPreference requires an entries array and an entryValues array.");
            } else
            {
                mClickedDialogEntryIndex = bundle.findIndexOfValue(((ListPreference) (bundle)).mValue);
                mEntries = ((ListPreference) (bundle)).mEntries;
                mEntryValues = ((ListPreference) (bundle)).mEntryValues;
                return;
            }
        } else
        {
            mClickedDialogEntryIndex = bundle.getInt("ListPreferenceDialogFragment.index", 0);
            mEntries = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entries");
            mEntryValues = bundle.getCharSequenceArray("ListPreferenceDialogFragment.entryValues");
            return;
        }
    }

    public final void onDialogClosed(boolean flag)
    {
        ListPreference listpreference = (ListPreference)getPreference();
        if (flag && mClickedDialogEntryIndex >= 0)
        {
            String s = mEntryValues[mClickedDialogEntryIndex].toString();
            boolean flag1;
            if (((Preference) (listpreference)).mOnChangeListener == null || ((Preference) (listpreference)).mOnChangeListener.onPreferenceChange(listpreference, s))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                listpreference.setValue(s);
            }
        }
    }

    protected final void onPrepareDialogBuilder(android.support.v7.app.AlertDialog.Builder builder)
    {
        super.onPrepareDialogBuilder(builder);
        CharSequence acharsequence[] = mEntries;
        int i = mClickedDialogEntryIndex;
        _cls1 _lcls1 = new _cls1();
        builder.P.mItems = acharsequence;
        builder.P.mOnClickListener = _lcls1;
        builder.P.mCheckedItem = i;
        builder.P.mIsSingleChoice = true;
        builder.P.mPositiveButtonText = null;
        builder.P.mPositiveButtonListener = null;
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putInt("ListPreferenceDialogFragment.index", mClickedDialogEntryIndex);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entries", mEntries);
        bundle.putCharSequenceArray("ListPreferenceDialogFragment.entryValues", mEntryValues);
    }

    private class _cls1
        implements android.content.DialogInterface.OnClickListener
    {

        private final ListPreferenceDialogFragmentCompat this$0;

        public final void onClick(DialogInterface dialoginterface, int i)
        {
            mClickedDialogEntryIndex = i;
            PreferenceDialogFragmentCompat.this.onClick(dialoginterface, -1);
            dialoginterface.dismiss();
        }

        _cls1()
        {
            this$0 = ListPreferenceDialogFragmentCompat.this;
            super();
        }
    }

}
