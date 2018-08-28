// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.screen.event;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import com.google.android.apps.calendar.util.function.Consumer;
import com.google.android.calendar.api.CalendarApi;
import com.google.android.calendar.api.event.Event;
import com.google.android.calendar.api.event.EventPermissions;
import com.google.android.calendar.api.event.EventPermissionsFactory;
import com.google.android.calendar.event.scope.ScopeSelectionDialog;
import com.google.android.calendar.newapi.overflow.DeletionConfirmationDialog;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.RandomAccess;

// Referenced classes of package com.google.android.calendar.newapi.screen.event:
//            EventDeleteFlow

public final class arg._cls2
    implements Consumer
{

    private final Event arg$1;
    private final boolean arg$2;

    public final void accept(Object obj)
    {
        Event event = arg$1;
        boolean flag = arg$2;
        EventDeleteFlow eventdeleteflow = (EventDeleteFlow)obj;
        eventdeleteflow.event = event;
        eventdeleteflow.prompt = flag;
        obj = CalendarApi.EventPermissionsFactory.create(event);
        if (((EventPermissions) (obj)).getAllowedModificationScopes().size() > 1)
        {
            com.google.android.calendar.event.scope.r r = (new com.google.android.calendar.event.scope.nfig.Builder()).additionalArguments(new Bundle());
            obj = new ArrayList(((EventPermissions) (obj)).getAllowedModificationScopes());
            Collections.sort(((List) (obj)));
            com.google.common.base.Function function = com.google.android.calendar.event.scope.tance;
            if (obj instanceof RandomAccess)
            {
                obj = new com.google.common.collect.(((List) (obj)), function);
            } else
            {
                obj = new com.google.common.collect.init>(((List) (obj)), function);
            }
            obj = ScopeSelectionDialog.newInstance(eventdeleteflow, r.scopes(((List) (obj))).title(0x7f130161).positiveButton(0x7f130160).build());
        } else
        if (eventdeleteflow.prompt)
        {
            obj = DeletionConfirmationDialog.newInstance(eventdeleteflow, 0x7f13015d);
        } else
        {
            eventdeleteflow.onScopeSelected(0, null);
            return;
        }
        if (!((Fragment) (eventdeleteflow)).mFragmentManager.isDestroyed())
        {
            ((DialogFragment) (obj)).show(((Fragment) (eventdeleteflow)).mFragmentManager, null);
        }
    }

    public lder(Event event, boolean flag)
    {
        arg$1 = event;
        arg$2 = flag;
    }
}
