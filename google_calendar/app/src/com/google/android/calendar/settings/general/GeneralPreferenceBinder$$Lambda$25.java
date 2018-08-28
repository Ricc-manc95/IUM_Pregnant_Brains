// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.settings.general;

import android.content.DialogInterface;
import android.support.v4.app.Fragment;
import android.widget.Toast;
import com.google.android.calendar.settings.SettingsShims;

// Referenced classes of package com.google.android.calendar.settings.general:
//            GeneralPreferenceBinder, GeneralPreferenceViewModel

final class arg._cls1
    implements android.content.lPreferenceBinder..Lambda._cls25
{

    private final GeneralPreferenceBinder arg$1;

    public final void onClick(DialogInterface dialoginterface, int i)
    {
        dialoginterface = arg$1;
        GeneralPreferenceViewModel generalpreferenceviewmodel = ((GeneralPreferenceBinder) (dialoginterface)).viewModel;
        SettingsShims.instance.disableFitIntegration(generalpreferenceviewmodel.context);
        Toast.makeText(((GeneralPreferenceBinder) (dialoginterface)).fragment.getContext(), 0x7f130388, 0).show();
    }

    (GeneralPreferenceBinder generalpreferencebinder)
    {
        arg$1 = generalpreferencebinder;
    }
}
