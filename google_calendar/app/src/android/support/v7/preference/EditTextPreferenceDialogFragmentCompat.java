// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;

// Referenced classes of package android.support.v7.preference:
//            PreferenceDialogFragmentCompat, EditTextPreference, Preference

public final class EditTextPreferenceDialogFragmentCompat extends PreferenceDialogFragmentCompat
{

    private EditText mEditText;
    private CharSequence mText;

    public EditTextPreferenceDialogFragmentCompat()
    {
    }

    protected final boolean needInputMethod()
    {
        return true;
    }

    protected final void onBindDialogView(View view)
    {
        super.onBindDialogView(view);
        mEditText = (EditText)view.findViewById(0x1020003);
        mEditText.requestFocus();
        if (mEditText == null)
        {
            throw new IllegalStateException("Dialog view must contain an EditText with id @android:id/edit");
        } else
        {
            mEditText.setText(mText);
            mEditText.setSelection(mEditText.getText().length());
            return;
        }
    }

    public final void onCreate(Bundle bundle)
    {
        super.onCreate(bundle);
        if (bundle == null)
        {
            mText = ((EditTextPreference)getPreference()).mText;
            return;
        } else
        {
            mText = bundle.getCharSequence("EditTextPreferenceDialogFragment.text");
            return;
        }
    }

    public final void onDialogClosed(boolean flag)
    {
        if (flag)
        {
            String s = mEditText.getText().toString();
            EditTextPreference edittextpreference = (EditTextPreference)getPreference();
            boolean flag1;
            if (((Preference) (edittextpreference)).mOnChangeListener == null || ((Preference) (edittextpreference)).mOnChangeListener.onPreferenceChange(edittextpreference, s))
            {
                flag1 = true;
            } else
            {
                flag1 = false;
            }
            if (flag1)
            {
                EditTextPreference edittextpreference1 = (EditTextPreference)getPreference();
                flag = edittextpreference1.shouldDisableDependents();
                edittextpreference1.mText = s;
                edittextpreference1.persistString(s);
                boolean flag2 = edittextpreference1.shouldDisableDependents();
                if (flag2 != flag)
                {
                    edittextpreference1.notifyDependencyChange(flag2);
                }
            }
        }
    }

    public final void onSaveInstanceState(Bundle bundle)
    {
        super.onSaveInstanceState(bundle);
        bundle.putCharSequence("EditTextPreferenceDialogFragment.text", mText);
    }
}
