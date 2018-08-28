// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.DialogInterface;
import java.util.Set;

// Referenced classes of package android.support.v7.preference:
//            MultiSelectListPreferenceDialogFragmentCompat

final class this._cls0
    implements android.content.gmentCompat._cls1
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

    ()
    {
        this$0 = MultiSelectListPreferenceDialogFragmentCompat.this;
        super();
    }
}
