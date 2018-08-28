// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentHostCallback;
import com.google.android.apps.calendar.api.util.attendee.AttendeeUtils;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.event.EventModifications;
import com.google.android.calendar.api.event.attachment.AttachmentModifications;
import com.google.android.calendar.api.event.attendee.AttendeeModifications;
import com.google.android.calendar.utils.flow.Flow;
import com.google.android.calendar.utils.fragment.FragmentUtils;
import com.google.common.collect.Collections2;
import com.google.common.collect.ImmutableList;
import java.util.Collection;
import java.util.List;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventSaveFlow, DrivePermissionChecker

public final class arg._cls3
    implements Consumer
{

    private final boolean arg$1;
    private final List arg$2;
    private final EventModifications arg$3;

    public final void accept(Object obj)
    {
        EventSaveFlow eventsaveflow;
        boolean flag;
        boolean flag2 = true;
        boolean flag3 = arg$1;
        Object obj1 = arg$2;
        EventModifications eventmodifications = arg$3;
        eventsaveflow = (EventSaveFlow)obj;
        eventsaveflow.showConfirmationToast = flag3;
        eventsaveflow.saveScopes = ((List) (obj1));
        eventsaveflow.event = eventmodifications;
        obj = eventsaveflow.event;
        obj1 = Collections2.filter(((EventModifications) (obj)).getAttendees(), ..instance);
        boolean flag1;
        boolean flag4;
        if (AttendeeUtils.getCurrentAttendee(((com.google.android.calendar.api.event.Event) (obj))) == null)
        {
            if (!((Collection) (obj1)).isEmpty())
            {
                flag = true;
            } else
            {
                flag = false;
            }
        } else
        if (((Collection) (obj1)).size() > 1)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!((EventModifications) (obj)).getAttachments().isEmpty())
        {
            flag1 = true;
        } else
        {
            flag1 = false;
        }
        flag3 = ((EventModifications) (obj)).getAttachmentModifications().isModified();
        flag4 = ((EventModifications) (obj)).getAttendeeModifications().isModified();
        if (!flag || !flag1)
        {
            break MISSING_BLOCK_LABEL_236;
        }
        flag = flag2;
        if (!flag3)
        {
            if (!flag4)
            {
                break MISSING_BLOCK_LABEL_236;
            }
            flag = flag2;
        }
_L1:
        if (flag)
        {
            obj1 = new (eventsaveflow.event);
            if (((Fragment) (eventsaveflow)).mHost == null)
            {
                obj = null;
            } else
            {
                obj = (FragmentActivity)((Fragment) (eventsaveflow)).mHost.mActivity;
            }
            obj = (Flow)FragmentUtils.attachFragment(((android.app.Activity) (obj)), ((Fragment) (eventsaveflow)).mFragmentManager, com/google/android/calendar/newapi/screen/event/DrivePermissionChecker, eventsaveflow, null);
            if (obj != null)
            {
                ((Consumer) (obj1)).accept(obj);
            }
            return;
        } else
        {
            eventsaveflow.saveWithPermissionsFixed();
            return;
        }
        flag = false;
          goto _L1
    }

    public (boolean flag, List list, EventModifications eventmodifications)
    {
        arg$1 = flag;
        arg$2 = list;
        arg$3 = eventmodifications;
    }
}
