// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.preference;

import android.content.DialogInterface;

// Referenced classes of package android.support.v7.preference:
//            ListPreferenceDialogFragmentCompat, PreferenceDialogFragmentCompat

final class this._cls0
    implements android.content.gmentCompat._cls1
{

    private final ListPreferenceDialogFragmentCompat this$0;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        mClickedDialogEntryIndex = i;
        PreferenceDialogFragmentCompat.this.onClick(dialoginterface, -1);
        dialoginterface.dismiss();
    }

    ()
    {
        this$0 = ListPreferenceDialogFragmentCompat.this;
        super();
    }
}
