// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar;

import android.content.Context;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.android.ex.chips.RecipientEntry;
import com.google.android.calendar.avatar.ContactInfo;
import com.google.android.calendar.avatar.ContactInfoLoader;
import com.google.android.calendar.avatar.RecipientAdapterFactory;
import com.google.android.gms.chips.GmsRecipientEntry;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataBuffer;
import com.google.android.gms.people.Autocomplete;
import com.google.android.gms.people.People;
import com.google.android.gms.people.model.AutocompleteEntry;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

public final class ContactNameResolver
{

    public static ArrayList loadDisplayNames(Context context, List list)
    {
        SimpleArrayMap simplearraymap;
        ArrayList arraylist1;
        ContactInfoLoader contactinfoloader;
        Iterator iterator;
        simplearraymap = new SimpleArrayMap();
        ArrayList arraylist = new ArrayList();
        com.google.android.calendar.avatar.ContactInfo.Builder builder = ContactInfo.newBuilder();
        for (int i = 0; i < list.size(); i++)
        {
            builder.primaryEmail = (String)list.get(i);
            arraylist.add(new ContactInfo(builder));
        }

        contactinfoloader = new ContactInfoLoader(context);
        arraylist1 = new ArrayList(arraylist.size());
        iterator = arraylist.iterator();
_L7:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        ContactInfo contactinfo = (ContactInfo)iterator.next();
        if (contactinfo.primaryEmail == null) goto _L4; else goto _L3
_L3:
        if (TextUtils.isEmpty(contactinfo.sourceAccountName)) goto _L6; else goto _L5
_L5:
        Object obj;
        Object obj1;
        context = contactinfo.primaryEmail;
        obj1 = contactinfo.sourceAccountName;
        if (context == null)
        {
            throw new NullPointerException();
        }
        obj = RecipientAdapterFactory.getGoogleApiClient(contactinfoloader.context);
        if (TextUtils.isEmpty(((CharSequence) (obj1))) || TextUtils.isEmpty(context))
        {
            context = null;
        } else
        {
label0:
            {
                if (((GoogleApiClient) (obj)).isConnected())
                {
                    break label0;
                }
                ((GoogleApiClient) (obj)).blockingConnect(5L, TimeUnit.SECONDS);
                if (((GoogleApiClient) (obj)).isConnected())
                {
                    break label0;
                }
                context = null;
            }
        }
_L8:
        if (context == null)
        {
            context = contactinfo;
        } else
        {
            obj = ContactInfo.newBuilder(contactinfo);
            obj.name = ((RecipientEntry) (context)).displayName;
            obj.lookupKey = ((RecipientEntry) (context)).lookupKey;
            obj.contactId = Long.valueOf(((RecipientEntry) (context)).contactId);
            obj.avatarReference = ((GmsRecipientEntry) (context)).mAvatarReference;
            obj.avatarPhotoBytes = context.getPhotoBytes();
            context = new ContactInfo(((com.google.android.calendar.avatar.ContactInfo.Builder) (obj)));
        }
_L9:
        arraylist1.add(context);
          goto _L7
        com.google.android.gms.people.Autocomplete.AutocompleteOptions.Builder builder1 = new com.google.android.gms.people.Autocomplete.AutocompleteOptions.Builder();
        builder1.mAccount = ((String) (obj1));
        builder1.zzbSL = true;
        builder1.zzbSJ = 1;
        builder1.zzbSK = 1;
        obj1 = new com.google.android.gms.people.Autocomplete.AutocompleteOptions(builder1);
        obj = (com.google.android.gms.people.Autocomplete.AutocompleteResult)People.AutocompleteApi.loadAutocompleteList(((GoogleApiClient) (obj)), context, ((com.google.android.gms.people.Autocomplete.AutocompleteOptions) (obj1))).await(5L, TimeUnit.SECONDS);
        context = ((com.google.android.gms.people.Autocomplete.AutocompleteResult) (obj)).getStatus();
        int j = ((Status) (context)).zzaEP;
        obj1 = ((com.google.android.gms.people.Autocomplete.AutocompleteResult) (obj)).getAutocompleteEntries();
        if (obj1 == null || ((DataBuffer) (obj1)).getCount() <= 0)
        {
            break MISSING_BLOCK_LABEL_611;
        }
        if (((Status) (context)).zzaEP <= 0)
        {
            j = 1;
        } else
        {
            j = 0;
        }
        if (!j)
        {
            break MISSING_BLOCK_LABEL_611;
        }
        obj = new GmsRecipientEntry(android.support.v4.content.ModernAsyncTask.Status.LOOKUP$9HHMUR9FCTNMUPRCCKNM2RJ4E9NMIP1FCTMN6BR3D1KN0SPF8TMN6KJ5CDKN0QB5DPQ4ARJKE9SI8KJ5CDKN0QB5DPQ46SJ5C5Q6IRREALPMAGR1EDIJM___0, (AutocompleteEntry)((DataBuffer) (obj1)).get(0), 0);
_L10:
        context = ((Context) (obj));
        if (obj1 != null)
        {
            ((DataBuffer) (obj1)).release();
            context = ((Context) (obj));
        }
          goto _L8
_L6:
        context = contactinfoloader.loadSingleByEmailFromCP2(contactinfo);
          goto _L9
_L4:
        LogUtils.e(ContactInfoLoader.TAG, "Not enough information to load %s", new Object[] {
            contactinfo
        });
        context = contactinfo;
          goto _L9
_L2:
        context = new SimpleArrayMap();
        ContactInfo contactinfo1;
        for (obj = arraylist1.iterator(); ((Iterator) (obj)).hasNext(); context.put(contactinfo1.primaryEmail, contactinfo1.name))
        {
            contactinfo1 = (ContactInfo)((Iterator) (obj)).next();
        }

        simplearraymap.putAll(context);
        context = new ArrayList();
        for (list = list.iterator(); list.hasNext(); context.add((String)simplearraymap.get((String)list.next()))) { }
        return context;
        obj = null;
          goto _L10
    }
}
