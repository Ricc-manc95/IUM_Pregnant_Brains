// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.alerts;

import android.app.PendingIntent;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Rect;
import android.net.Uri;
import com.android.calendarcommon2.LogUtils;
import com.google.android.apps.calendar.timely.store.TimelyStore;
import com.google.android.apps.calendar.timely.store.TimelyStoreUtils;
import com.google.android.calendar.api.event.CpEventKey;
import com.google.android.calendar.api.event.EventKey;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.syncadapters.calendar.timely.contract.TimelyEventData;
import com.google.api.services.calendar.model.ContactReference;
import com.google.api.services.calendar.model.TitleContactAnnotation;
import java.util.List;

// Referenced classes of package com.google.android.calendar.alerts:
//            ContactNotification, NotificationActionTrampoline

public class ContactNotificationImpl
    implements ContactNotification
{

    private static final String EVENT_PROJECTION[] = {
        "calendar_id", "_sync_id"
    };
    private static final String RAW_CONTACTS_PROJECTION[] = {
        "contact_id"
    };
    private Uri contactLink;
    private final Context context;
    private final EventKey eventKey;

    public ContactNotificationImpl(Context context1, EventKey eventkey)
    {
        Exception exception;
        long l;
        exception = null;
        super();
        context = context1;
        eventKey = eventkey;
        if (!(eventkey instanceof CpEventKey))
        {
            break MISSING_BLOCK_LABEL_61;
        }
        l = ((CpEventKey)eventkey).localId();
        boolean flag;
        if (!AndroidPermissionUtils.hasMandatoryPermissions(context))
        {
            break MISSING_BLOCK_LABEL_56;
        }
        flag = AndroidPermissionUtils.hasContactsPermissions(context);
        if (flag)
        {
            break MISSING_BLOCK_LABEL_62;
        }
        contactLink = null;
        return;
        Cursor cursor = context.getContentResolver().query(ContentUris.withAppendedId(android.provider.CalendarContract.Events.CONTENT_URI, l), EVENT_PROJECTION, null, null, null);
        if (cursor == null)
        {
            contactLink = null;
            return;
        }
        flag = cursor.moveToFirst();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_126;
        }
        cursor.close();
        contactLink = null;
        return;
        context1 = TimelyStore.acquire(context);
        context1 = context1.getTimelyEventData(cursor.getString(1), null, cursor.getInt(0), TimelyStoreUtils.loadExtendedProperties(((TimelyStore) (context1)).context.getContentResolver(), l));
        if (context1 != null)
        {
            break MISSING_BLOCK_LABEL_186;
        }
        cursor.close();
        contactLink = null;
        return;
        context1 = ((TimelyEventData) (context1)).titleContactAnnotations;
        if (context1 == null)
        {
            break MISSING_BLOCK_LABEL_226;
        }
        if (context1.size() != 1)
        {
            break MISSING_BLOCK_LABEL_226;
        }
        eventkey = ((TitleContactAnnotation)context1.get(0)).contact.focusId;
        if (eventkey != null)
        {
            break MISSING_BLOCK_LABEL_239;
        }
        cursor.close();
        contactLink = null;
        return;
        context1 = Long.toHexString(((TitleContactAnnotation)context1.get(0)).contact.focusId.longValue());
        eventkey = context.getContentResolver().query(android.provider.ContactsContract.RawContacts.CONTENT_URI, RAW_CONTACTS_PROJECTION, "sourceid=? AND deleted=0", new String[] {
            context1
        }, null);
        if (eventkey != null)
        {
            break MISSING_BLOCK_LABEL_307;
        }
        cursor.close();
        contactLink = null;
        return;
        flag = eventkey.moveToFirst();
        if (flag)
        {
            break MISSING_BLOCK_LABEL_339;
        }
        eventkey.close();
        cursor.close();
        contactLink = null;
        return;
        long l1 = eventkey.getLong(0);
        context1 = Uri.withAppendedPath(android.provider.ContactsContract.Contacts.CONTENT_URI, String.valueOf(l1));
        eventkey.close();
        eventkey = context1;
        cursor.close();
        contactLink = context1;
        return;
        context1;
        eventkey.close();
        throw context1;
        exception;
        context1 = null;
_L4:
        eventkey = context1;
        cursor.close();
        eventkey = context1;
        throw exception;
        context1;
_L2:
        contactLink = eventkey;
        throw context1;
        context1;
        eventkey = exception;
        if (true) goto _L2; else goto _L1
_L1:
        exception;
        if (true) goto _L4; else goto _L3
_L3:
    }

    public final PendingIntent createTrampolineIntent()
    {
        boolean flag;
        if (contactLink != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            return null;
        } else
        {
            Intent intent = (new Intent("com.google.android.calendar.CONTACT")).setClass(context, com/google/android/calendar/alerts/NotificationActionTrampoline);
            Object obj = eventKey;
            Object obj1 = (new StringBuilder(obj.getClass().getSimpleName())).append('|');
            ((EventKey) (obj)).serializeInternal(((StringBuilder) (obj1)));
            intent = intent.putExtra("eventkey", ((StringBuilder) (obj1)).toString());
            obj = context;
            obj1 = eventKey;
            StringBuilder stringbuilder = (new StringBuilder(obj1.getClass().getSimpleName())).append('|');
            ((EventKey) (obj1)).serializeInternal(stringbuilder);
            return PendingIntent.getActivity(((Context) (obj)), stringbuilder.toString().hashCode(), intent, 0x8000000);
        }
    }

    public final int getIconResource()
    {
        return 0x7f020125;
    }

    public final int getLabelResource()
    {
        return 0x7f130138;
    }

    public final boolean isValid()
    {
        return contactLink != null;
    }

    public final void startActivity()
    {
        android.provider.ContactsContract.QuickContact.showQuickContact(context, new Rect(0, 0, 1, 1), contactLink, 3, null);
    }

    static 
    {
        LogUtils.getLogTag(com/google/android/calendar/alerts/ContactNotificationImpl);
    }
}
