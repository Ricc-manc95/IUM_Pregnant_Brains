// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.conference;

import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.calendar.api.calendarlist.CalendarListEntry;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.conference.ConferenceDataModifications;
import com.google.android.calendar.api.event.conference.ConferenceDataUtils;
import com.google.android.calendar.api.event.conference.ConferencePermissions;
import com.google.android.calendar.api.settings.GoogleSettings;
import com.google.android.calendar.common.view.NinjaSwitch;
import com.google.android.calendar.newapi.model.PermissionHolder;
import com.google.android.calendar.newapi.model.edit.EventModificationsHolder;
import com.google.android.calendar.newapi.model.edit.SettingsHolder;
import com.google.android.calendar.newapi.segment.common.EditSegmentController;
import com.google.android.calendar.newapi.segment.common.SegmentController;
import com.google.android.calendar.newapi.segment.conference.thirdparty.utils.ThirdPartyConferenceUtils;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.segment.conference:
//            ConferenceEditSegment

public class ConferenceEditSegmentController extends EditSegmentController
{

    private int conferenceType;
    private boolean isAllDayEvent;

    public ConferenceEditSegmentController()
    {
        conferenceType = 0;
    }

    private final boolean canUpdateConferenceInfo()
    {
        return !((PermissionHolder)(EventModificationsHolder)super.model).getPermissions().getConferencePermissions().isReadOnly();
    }

    private static int getConferenceType(Event event)
    {
        int i = ConferenceDataUtils.getConferenceType(event.getConferenceData());
        if (i != 0)
        {
            return i;
        }
        event = event.getCalendarListEntry().getAllowedConferenceTypes();
        if (event == null || event.isEmpty())
        {
            return 0;
        } else
        {
            return ((Integer)event.get(0)).intValue();
        }
    }

    private final boolean hasConference()
    {
        return !ConferenceDataUtils.isEmptyConference(((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications());
    }

    private final boolean hasConferenceByDefault()
    {
        if (conferenceType == 3 && ((EventModificationsHolder)super.model).getEventModifications().isNewEvent() && ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent())
        {
            return false;
        }
        com.google.android.calendar.api.settings.Settings settings = ((SettingsHolder)(EventModificationsHolder)super.model).getSettings();
        return (settings instanceof GoogleSettings) && ((GoogleSettings)settings).autoAddHangoutsEnabled();
    }

    private final void updateUi()
    {
        boolean flag;
        if (!canUpdateConferenceInfo())
        {
            break MISSING_BLOCK_LABEL_79;
        }
        View view;
        if (AttendeeUtils.hasGuests(((EventModificationsHolder)super.model).getEventModifications()) && conferenceType != 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag && !hasConference())
        {
            break MISSING_BLOCK_LABEL_79;
        }
        flag = true;
_L1:
        view = super.view;
        if (view != null)
        {
            int i;
            if (flag)
            {
                i = 0;
            } else
            {
                i = 8;
            }
            view.setVisibility(i);
        }
        if (!flag)
        {
            return;
        }
        break MISSING_BLOCK_LABEL_91;
        flag = false;
          goto _L1
        Object obj = (ConferenceEditSegment)super.view;
        boolean flag2 = hasConference();
        obj = ((ConferenceEditSegment) (obj)).conferenceToggle;
        obj.stealth = true;
        ((NinjaSwitch) (obj)).setChecked(flag2);
        obj.stealth = false;
        obj = (ConferenceEditSegment)super.view;
        boolean flag1;
        if (conferenceType == 3)
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        if (ThirdPartyConferenceUtils.isThirdPartyConferenceData(((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications()))
        {
            TextTileView texttileview = ((ConferenceEditSegment) (obj)).tile;
            texttileview.setPrimaryText(new CharSequence[] {
                texttileview.getResources().getString(0x7f130318, new Object[0])
            });
            ((ConferenceEditSegment) (obj)).tile.setSecondaryText(new CharSequence[] {
                null
            });
            return;
        }
        TextTileView texttileview1 = ((ConferenceEditSegment) (obj)).tile;
        int j;
        if (flag1)
        {
            j = 0x7f13008f;
        } else
        {
            j = 0x7f130092;
        }
        texttileview1.setPrimaryText(new CharSequence[] {
            texttileview1.getResources().getString(j, new Object[0])
        });
        texttileview1 = ((ConferenceEditSegment) (obj)).tile;
        if (flag1)
        {
            obj = ((ConferenceEditSegment) (obj)).getContext().getString(0x7f13008e);
        } else
        {
            obj = null;
        }
        texttileview1.setSecondaryText(new CharSequence[] {
            obj
        });
        return;
    }

    public final View createView(LayoutInflater layoutinflater)
    {
        layoutinflater = (ConferenceEditSegment)layoutinflater.inflate(0x7f0500c6, null);
        class .Lambda._cls0
            implements ConferenceEditSegment.Listener
        {

            private final ConferenceEditSegmentController arg$1;

            public final void onConferenceToggled(boolean flag)
            {
                arg$1.enableConference(flag);
            }

            .Lambda._cls0()
            {
                arg$1 = ConferenceEditSegmentController.this;
            }
        }

        layoutinflater.mListener = new .Lambda._cls0();
        return layoutinflater;
    }

    final void enableConference(boolean flag)
    {
        ((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications().clear();
        if (flag)
        {
            ConferenceDataUtils.createNewConference(((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications(), conferenceType);
        }
    }

    public final void onAttendeesChanged()
    {
        boolean flag1;
        boolean flag2;
        flag1 = true;
        boolean flag;
        if (((ConferenceEditSegment)super.view).getVisibility() == 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!AttendeeUtils.hasGuests(((EventModificationsHolder)super.model).getEventModifications()) || conferenceType == 0)
        {
            flag1 = false;
        }
        flag2 = canUpdateConferenceInfo();
        if (!flag2 || !flag1 || flag) goto _L2; else goto _L1
_L1:
        flag2 = hasConferenceByDefault();
        ((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications().clear();
        if (flag2)
        {
            ConferenceDataUtils.createNewConference(((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications(), conferenceType);
        }
_L4:
        updateUi();
        return;
_L2:
        if (flag2 && !flag1)
        {
            ((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications().clear();
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final void onCalendarChanged(boolean flag, boolean flag1)
    {
        boolean flag3;
        flag3 = true;
        conferenceType = getConferenceType(((EventModificationsHolder)super.model).getEventModifications());
        if (!flag1) goto _L2; else goto _L1
_L1:
        boolean flag2;
        if (!canUpdateConferenceInfo())
        {
            break MISSING_BLOCK_LABEL_138;
        }
        if (AttendeeUtils.hasGuests(((EventModificationsHolder)super.model).getEventModifications()) && conferenceType != 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_138;
        }
        flag2 = true;
_L3:
        if (flag2 && hasConferenceByDefault())
        {
            flag2 = flag3;
        } else
        {
            flag2 = false;
        }
        ((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications().clear();
        if (flag2)
        {
            ConferenceDataUtils.createNewConference(((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications(), conferenceType);
        }
_L2:
        updateUi();
        return;
        flag2 = false;
          goto _L3
    }

    protected final void onInitialize()
    {
        conferenceType = getConferenceType(((EventModificationsHolder)super.model).getEventModifications());
        isAllDayEvent = ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent();
        updateUi();
    }

    public final void onTimeRelatedFieldChanged(boolean flag, boolean flag1)
    {
        boolean flag3 = true;
        if (!((EventModificationsHolder)super.model).getEventModifications().isNewEvent() || isAllDayEvent == ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent()) goto _L2; else goto _L1
_L1:
        boolean flag2;
        isAllDayEvent = ((EventModificationsHolder)super.model).getEventModifications().isAllDayEvent();
        if (!canUpdateConferenceInfo())
        {
            break MISSING_BLOCK_LABEL_180;
        }
        if (AttendeeUtils.hasGuests(((EventModificationsHolder)super.model).getEventModifications()) && conferenceType != 0)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2)
        {
            break MISSING_BLOCK_LABEL_180;
        }
        flag2 = true;
_L3:
        if (flag2 && hasConferenceByDefault())
        {
            flag2 = flag3;
        } else
        {
            flag2 = false;
        }
        ((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications().clear();
        if (flag2)
        {
            ConferenceDataUtils.createNewConference(((EventModificationsHolder)super.model).getEventModifications().getConferenceDataModifications(), conferenceType);
        }
        updateUi();
_L2:
        return;
        flag2 = false;
          goto _L3
    }
}
