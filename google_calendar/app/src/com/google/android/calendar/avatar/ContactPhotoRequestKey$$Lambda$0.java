// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.avatar;

import android.content.Context;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.people.Images;
import com.google.android.gms.people.People;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

// Referenced classes of package com.google.android.calendar.avatar:
//            ContactPhotoRequestKey, ContactInfo, ContactInfoLoader, RecipientAdapterFactory

final class arg._cls1
    implements Callable
{

    private final ContactPhotoRequestKey arg$1;

    public final Object call()
    {
        ContactPhotoRequestKey contactphotorequestkey = arg$1;
        Context context = contactphotorequestkey.context;
        ContactInfo contactinfo = contactphotorequestkey.contactInfo;
        Object obj = contactinfo;
        if (contactinfo.avatarPhotoBytes == null)
        {
            obj = contactinfo;
            int i;
            if (contactinfo.avatarReference == null)
            {
                if (contactinfo.contactId != null && contactinfo.lookupKey != null)
                {
                    obj = contactinfo;
                } else
                {
                    obj = (new ContactInfoLoader(context)).load(contactinfo);
                }
            }
        }
        contactinfo = contactphotorequestkey.context;
        if (((ContactInfo) (obj)).avatarPhotoBytes != null)
        {
            obj = new ByteArrayInputStream(((ContactInfo) (obj)).avatarPhotoBytes);
        } else
        if (((ContactInfo) (obj)).avatarReference != null)
        {
            contactinfo = RecipientAdapterFactory.getGoogleApiClient(contactinfo);
            obj = ((ContactInfo) (obj)).avatarReference;
            obj1 = new com.google.android.gms.people.init>();
            obj1.zbTi = 1;
            obj1.zbTj = 1;
            obj1 = new com.google.android.gms.people.zbTj(((com.google.android.gms.people.zbTj) (obj1)));
            contactinfo = (com.google.android.gms.people.zbTj)People.ImageApi.loadByReference(contactinfo, ((com.google.android.gms.people.model.AvatarReference) (obj)), ((com.google.android.gms.people.zbTj) (obj1))).await(5L, TimeUnit.SECONDS);
            obj = contactinfo.ileDescriptor();
            contactinfo = contactinfo.ileDescriptor();
            int j = ((Status) (contactinfo)).zzaEP;
            if (((Status) (contactinfo)).zzaEP <= 0)
            {
                j = 1;
            } else
            {
                j = 0;
            }
            if (!j || obj == null)
            {
                obj = null;
            } else
            {
                obj = new android.os.utStream(((android.os.ParcelFileDescriptor) (obj)));
            }
        } else
        if (((ContactInfo) (obj)).lookupKey != null && ((ContactInfo) (obj)).contactId != null)
        {
            obj = android.provider.upUri(((ContactInfo) (obj)).contactId.longValue(), ((ContactInfo) (obj)).lookupKey);
            obj = android.provider.tactPhotoInputStream(contactinfo.getApplicationContext().getContentResolver(), ((android.net.Uri) (obj)));
        } else
        {
            obj = null;
        }
        if (obj != null)
        {
            contactinfo = new byte[1024];
            Object obj1 = new ByteArrayOutputStream();
            do
            {
                i = ((InputStream) (obj)).read(contactinfo);
                if (i >= 0)
                {
                    ((ByteArrayOutputStream) (obj1)).write(contactinfo, 0, i);
                } else
                {
                    return ((ByteArrayOutputStream) (obj1)).toByteArray();
                }
            } while (true);
        } else
        {
            return null;
        }
    }

    I(ContactPhotoRequestKey contactphotorequestkey)
    {
        arg$1 = contactphotorequestkey;
    }
}
