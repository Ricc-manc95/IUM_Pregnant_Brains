// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.datetimepicker.time;

import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import java.util.ArrayList;

// Referenced classes of package com.android.datetimepicker.time:
//            TimePickerBaseDialog, RadialPickerLayout

final class this._cls0
    implements android.view.g.KeyboardListener
{

    private final TimePickerBaseDialog this$0;

    public final boolean onKey(View view, int i, KeyEvent keyevent)
    {
        if (keyevent.getAction() != 1)
        {
            break MISSING_BLOCK_LABEL_394;
        }
        keyevent = TimePickerBaseDialog.this;
        if (i == 111 || i == 4)
        {
            ((TimePickerBaseDialog) (keyevent)).picker.dismiss();
            return true;
        }
        if (i != 61) goto _L2; else goto _L1
_L1:
        if (((TimePickerBaseDialog) (keyevent)).inKbMode)
        {
            if (keyevent.isTypedTimeFullyLegal())
            {
                keyevent.finishKbMode(true);
            }
            return true;
        }
          goto _L3
_L2:
        if (i == 66)
        {
            if (((TimePickerBaseDialog) (keyevent)).inKbMode)
            {
                if (!keyevent.isTypedTimeFullyLegal())
                {
                    return true;
                }
                keyevent.finishKbMode(false);
            }
            if (((TimePickerBaseDialog) (keyevent)).callback != null)
            {
                ((TimePickerBaseDialog) (keyevent)).callback._mth51666RRD5TGMSP3IDTKM8BR4C5Q6AT39DLIN0QB3DDIN4BRKD5MMABQIC5I6IOBCA1KM6QR5E9662UBFELQ3MIA955B0____0(((TimePickerBaseDialog) (keyevent)).timePicker.currentHoursOfDay, ((TimePickerBaseDialog) (keyevent)).timePicker.currentMinutes);
            }
            ((TimePickerBaseDialog) (keyevent)).picker.dismiss();
            return true;
        }
        if (i != 67) goto _L5; else goto _L4
_L4:
        if (((TimePickerBaseDialog) (keyevent)).inKbMode && !((TimePickerBaseDialog) (keyevent)).typedTimes.isEmpty())
        {
            i = keyevent.deleteLastTypedKey();
            RadialPickerLayout radialpickerlayout;
            if (i == keyevent.getAmOrPmKeyCode(0))
            {
                view = ((TimePickerBaseDialog) (keyevent)).amText;
            } else
            if (i == keyevent.getAmOrPmKeyCode(1))
            {
                view = ((TimePickerBaseDialog) (keyevent)).pmText;
            } else
            {
                view = String.format("%d", new Object[] {
                    Integer.valueOf(TimePickerBaseDialog.getValFromKeyCode(i))
                });
            }
            radialpickerlayout = ((TimePickerBaseDialog) (keyevent)).timePicker;
            view = String.format(((TimePickerBaseDialog) (keyevent)).deletedKeyFormat, new Object[] {
                view
            });
            if (radialpickerlayout != null && view != null)
            {
                radialpickerlayout.announceForAccessibility(view);
            }
            keyevent.updateDisplay(true);
        }
_L3:
        return false;
_L5:
        if (i != 7 && i != 8 && i != 9 && i != 10 && i != 11 && i != 12 && i != 13 && i != 14 && i != 15 && i != 16 && (((TimePickerBaseDialog) (keyevent)).is24HourMode || i != keyevent.getAmOrPmKeyCode(0) && i != keyevent.getAmOrPmKeyCode(1))) goto _L3; else goto _L6
_L6:
        if (!((TimePickerBaseDialog) (keyevent)).inKbMode)
        {
            if (((TimePickerBaseDialog) (keyevent)).timePicker == null)
            {
                Log.e("TimePickerDialog", "Unable to initiate keyboard mode, TimePicker was null.");
                return true;
            } else
            {
                ((TimePickerBaseDialog) (keyevent)).typedTimes.clear();
                keyevent.tryStartingKbMode(i);
                return true;
            }
        }
        if (keyevent.addKeyIfLegal(i))
        {
            keyevent.updateDisplay(false);
        }
        return true;
        return false;
    }

    ()
    {
        this$0 = TimePickerBaseDialog.this;
        super();
    }
}
