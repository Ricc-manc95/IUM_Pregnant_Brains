// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.avatar;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.text.TextUtils;
import android.text.util.Rfc822Token;
import android.text.util.Rfc822Tokenizer;
import com.android.calendarcommon2.LogUtils;
import com.android.ex.chips.RecipientEntry;
import com.google.android.calendar.utils.permission.AndroidPermissionUtils;
import com.google.android.gms.chips.GmsRecipientEntry;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.people.Autocomplete;
import com.google.android.gms.people.People;
import com.google.android.gms.people.model.AutocompleteEntry;
import com.google.common.base.Function;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.avatar:
//            ContactInfo, RecipientAdapterFactory

public class ContactInfoLoader
{

    private static final String CONTACTS_LOOKUP_PROJECTION[] = {
        "display_name", "lookup", "photo_id", "contact_id"
    };
    public static final String TAG = LogUtils.getLogTag(com/google/android/calendar/avatar/ContactInfoLoader);
    private final ContentResolver contentResolver;
    public final Context context;
    private final boolean hasContactsPermissions;

    public ContactInfoLoader(Context context1)
    {
        contentResolver = context1.getContentResolver();
        hasContactsPermissions = AndroidPermissionUtils.hasContactsPermissions(context1);
        context = context1;
    }

    static final ContactInfo lambda$loadSingleByEmailFromCP2$0$ContactInfoLoader(ContactInfo contactinfo, Cursor cursor)
    {
        boolean flag = true;
        contactinfo = ContactInfo.newBuilder(contactinfo);
        contactinfo.name = cursor.getString(0);
        contactinfo.lookupKey = cursor.getString(1);
        contactinfo.contactId = Long.valueOf(cursor.getLong(3));
        if (cursor.getLong(2) <= 0L)
        {
            flag = false;
        }
        contactinfo.hasPhoto = Boolean.valueOf(flag);
        return new ContactInfo(contactinfo);
    }

    private static transient Object processSingleCursorItem(Cursor cursor, Function function, Object obj, String s, Object aobj[])
    {
        boolean flag = true;
        if (cursor.getCount() != 1)
        {
            flag = false;
        }
        if (!flag)
        {
            try
            {
                throw new IllegalStateException(String.valueOf("Expecting a single row"));
            }
            // Misplaced declaration of an exception variable
            catch (Cursor cursor)
            {
                LogUtils.e(TAG, cursor, s, aobj);
            }
            return obj;
        }
        if (!cursor.moveToFirst())
        {
            throw new IllegalStateException();
        }
        cursor = ((Cursor) (function.apply(cursor)));
        return cursor;
    }

    public final ContactInfo load(ContactInfo contactinfo)
    {
        if (contactinfo.primaryEmail == null) goto _L2; else goto _L1
_L1:
        if (TextUtils.isEmpty(contactinfo.sourceAccountName)) goto _L4; else goto _L3
_L3:
        Object obj;
        Object obj1;
        Object obj2;
        obj = contactinfo.primaryEmail;
        obj2 = contactinfo.sourceAccountName;
        if (obj == null)
        {
            throw new NullPointerException();
        }
        obj1 = RecipientAdapterFactory.getGoogleApiClient(context);
        if (TextUtils.isEmpty(((CharSequence) (obj2))) || TextUtils.isEmpty(((CharSequence) (obj))))
        {
            obj = null;
        } else
        {
label0:
            {
                if (((GoogleApiClient) (obj1)).isConnected())
                {
                    break label0;
                }
                ((GoogleApiClient) (obj1)).blockingConnect(5L, TimeUnit.SECONDS);
                if (((GoogleApiClient) (obj1)).isConnected())
                {
                    break label0;
                }
                obj = null;
            }
        }
_L5:
        com.google.android.gms.people.Autocomplete.AutocompleteOptions.Builder builder;
        int i;
        if (obj == null)
        {
            return contactinfo;
        } else
        {
            contactinfo = ContactInfo.newBuilder(contactinfo);
            contactinfo.name = ((RecipientEntry) (obj)).displayName;
            contactinfo.lookupKey = ((RecipientEntry) (obj)).lookupKey;
            contactinfo.contactId = Long.valueOf(((RecipientEntry) (obj)).contactId);
            contactinfo.avatarReference = ((GmsRecipientEntry) (obj)).mAvatarReference;
            contactinfo.avatarPhotoBytes = ((RecipientEntry) (obj)).getPhotoBytes();
            return new ContactInfo(contactinfo);
        }
        builder = new com.google.android.gms.people.Autocomplete.AutocompleteOptions.Builder();
        builder.mAccount = ((String) (obj2));
        builder.zzbSL = true;
        builder.zzbSJ = 1;
        builder.zzbSK = 1;
        obj2 = new com.google.android.gms.people.Autocomplete.AutocompleteOptions(builder);
        obj1 = (com.google.android.gms.people.Autocomplete.AutocompleteResult)People.AutocompleteApi.loadAutocompleteList(((GoogleApiClient) (obj1)), ((String) (obj)), ((com.google.android.gms.people.Autocomplete.AutocompleteOptions) (obj2))).await(5L, TimeUnit.SECONDS);
        obj = ((com.google.android.gms.people.Autocomplete.AutocompleteResult) (obj1)).getStatus();
        i = ((Status) (obj)).zzaEP;
        obj2 = ((com.google.android.gms.people.Autocomplete.AutocompleteResult) (obj1)).getAutocompleteEntries();
        if (obj2 == null || ((DataBuffer) (obj2)).getCount() <= 0)
        {
            break MISSING_BLOCK_LABEL_353;
        }
        if (((Status) (obj)).zzaEP <= 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (!i)
        {
            break MISSING_BLOCK_LABEL_353;
        }
        obj1 = new GmsRecipientEntry(android.support.v4.content.ModernAsyncTask.Status.LOOKUP$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCTMN6BR3D1KN0SPF8TMN6KJ5CDKN0QB5DPQ4ARJKE9SI8KJ5CDKN0QB5DPQ46SJ5C5Q6IRREALPMAGR1EDIJM___0, (AutocompleteEntry)((DataBuffer) (obj2)).get(0), 0);
_L6:
        obj = obj1;
        if (obj2 != null)
        {
            ((DataBuffer) (obj2)).release();
            obj = obj1;
        }
          goto _L5
_L4:
        return loadSingleByEmailFromCP2(contactinfo);
_L2:
        LogUtils.e(TAG, "Not enough information to load %s", new Object[] {
            contactinfo
        });
        return contactinfo;
        obj1 = null;
          goto _L6
    }

    public final ContactInfo loadSingleByEmailFromCP2(ContactInfo contactinfo)
    {
        if (hasContactsPermissions) goto _L2; else goto _L1
_L1:
        LogUtils.d(TAG, "No contacts permission granted.  Cannot lookup contact by email.", new Object[0]);
_L4:
        return contactinfo;
_L2:
        String s1;
        Object obj1;
        s1 = contactinfo.primaryEmail;
        if (s1 == null)
        {
            throw new NullPointerException();
        }
        String s = Uri.encode(s1);
        Rfc822Token arfc822token[] = Rfc822Tokenizer.tokenize(s);
        boolean flag;
        if (arfc822token.length != 0 && arfc822token[0].getAddress() != null && arfc822token[0].getAddress().trim().length() > 0)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            LogUtils.d(TAG, "No contact found for %s", new Object[] {
                s
            });
            return contactinfo;
        }
        obj1 = Uri.withAppendedPath(android.provider.ContactsContract.CommonDataKinds.Email.CONTENT_LOOKUP_URI, s);
        Object obj = contentResolver.query(((Uri) (obj1)), CONTACTS_LOOKUP_PROJECTION, null, null, "photo_id DESC LIMIT 1");
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_157;
        }
        if (((Cursor) (obj)).getCount() != 0)
        {
            break MISSING_BLOCK_LABEL_187;
        }
        LogUtils.d(TAG, "No contact found for %s", new Object[] {
            s1
        });
        if (obj == null) goto _L4; else goto _L3
_L3:
        ((Cursor) (obj)).close();
        return contactinfo;
        class .Lambda._cls0
            implements Function
        {

            private final ContactInfo arg$1;

            public final Object apply(Object obj2)
            {
                return ContactInfoLoader.lambda$loadSingleByEmailFromCP2$0$ContactInfoLoader(arg$1, (Cursor)obj2);
            }

            .Lambda._cls0(ContactInfo contactinfo)
            {
                arg$1 = contactinfo;
            }
        }

        obj1 = (ContactInfo)processSingleCursorItem(((Cursor) (obj)), new .Lambda._cls0(contactinfo), contactinfo, "Failed to load contact for %s", new Object[] {
            obj1
        });
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        return ((ContactInfo) (obj1));
        obj;
        obj = null;
_L8:
        LogUtils.d(TAG, "No contact found for %s", new Object[] {
            s1
        });
        if (obj == null) goto _L4; else goto _L5
_L5:
        ((Cursor) (obj)).close();
        return contactinfo;
        contactinfo;
        obj = null;
_L7:
        if (obj != null)
        {
            ((Cursor) (obj)).close();
        }
        throw contactinfo;
        contactinfo;
        continue; /* Loop/switch isn't completed */
        contactinfo;
        if (true) goto _L7; else goto _L6
_L6:
        IllegalArgumentException illegalargumentexception;
        illegalargumentexception;
          goto _L8
    }

}
