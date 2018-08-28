// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.dialog;

import android.os.Bundle;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.common.dialog:
//            SingleChoiceTextDialog

public final class SingleParcelableChoiceTextDialog extends SingleChoiceTextDialog
{

    public SingleParcelableChoiceTextDialog()
    {
    }

    protected final ArrayList restoreValuesFromInstanceState(Bundle bundle)
    {
        return bundle.getParcelableArrayList("single_choice_text_values");
    }

    protected final void saveValuesToInstanceState(Bundle bundle, ArrayList arraylist)
    {
        bundle.putParcelableArrayList("single_choice_text_values", arraylist);
    }
}
