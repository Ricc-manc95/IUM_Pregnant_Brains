// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.common.dialog;

import android.os.Bundle;
import java.util.ArrayList;

// Referenced classes of package com.google.android.calendar.common.dialog:
//            SingleChoiceTextDialog

public final class SingleIntegerChoiceTextDialog extends SingleChoiceTextDialog
{

    public SingleIntegerChoiceTextDialog()
    {
    }

    protected final ArrayList restoreValuesFromInstanceState(Bundle bundle)
    {
        return bundle.getIntegerArrayList("single_choice_text_values");
    }

    protected final void saveValuesToInstanceState(Bundle bundle, ArrayList arraylist)
    {
        bundle.putIntegerArrayList("single_choice_text_values", arraylist);
    }
}
