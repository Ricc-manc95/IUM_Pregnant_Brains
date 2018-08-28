// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.rooms.ui;

import android.support.v4.app.Fragment;
import com.google.android.calendar.common.dialog.SingleChoiceDialogListener;
import com.google.android.calendar.timely.rooms.data.AutoValue_RoomBookingFilterParams;
import com.google.android.calendar.timely.rooms.data.RoomBookingFilterParams;

// Referenced classes of package com.google.android.calendar.timely.rooms.ui:
//            FiltersViewController

public static class  extends Fragment
    implements SingleChoiceDialogListener
{

    public FiltersViewController outer;

    public final void onDialogItemChosen(Object obj, int i)
    {
        obj = (Integer)obj;
        if (outer != null)
        {
            FiltersViewController filtersviewcontroller = outer;
            i = ((Integer) (obj)).intValue();
            filtersviewcontroller.params = new AutoValue_RoomBookingFilterParams(filtersviewcontroller.params.showOnlyAvailable(), Integer.valueOf(i));
            filtersviewcontroller.updateUi();
        }
    }

    public ()
    {
    }
}
