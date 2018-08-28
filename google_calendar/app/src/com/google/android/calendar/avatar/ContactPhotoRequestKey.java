// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.avatar;

import android.content.Context;
import com.android.bitmap.RequestKey;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.event.image.BitmapCallbacks;
import com.google.common.util.concurrent.FluentFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;
import java.io.InputStream;

// Referenced classes of package com.google.android.calendar.avatar:
//            ContactInfo

public final class ContactPhotoRequestKey
    implements RequestKey
{

    public final ContactInfo contactInfo;
    public final Context context;
    private ListenableFuture future;

    public ContactPhotoRequestKey(Context context1, ContactInfo contactinfo)
    {
        if (contactinfo == null)
        {
            throw new NullPointerException();
        } else
        {
            contactInfo = (ContactInfo)contactinfo;
            context = context1.getApplicationContext();
            return;
        }
    }

    public final com.android.bitmap.RequestKey.Cancelable createFileDescriptorFactoryOrByteArrayAsync$51666RRD5TGMSP3IDTKM8BR2D5Q6QOBG5T96ASBLCLPN8IR5F4TKOORFDKNM2RJ4E9NMIP1FC9KN8RB1E0NL4PBHELIN6T2BCLSI8GR1DHM64OB3DCTIIJ33DTMIUOBECHP6UQB45TH6IT3DC5O2UKJ5E5QMASRK9DINI923C5N66PBCC5H6OP9R0(com.android.bitmap.RequestKey.Callback callback)
    {
        class .Lambda._cls0
            implements Callable
        {

            private final ContactPhotoRequestKey arg$1;

            public final Object call()
            {
                ContactPhotoRequestKey contactphotorequestkey = arg$1;
                Context context1 = contactphotorequestkey.context;
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
                            obj = (new ContactInfoLoader(context1)).load(contactinfo);
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
                    obj1 = new com.google.android.gms.people.Images.LoadImageOptions.Builder();
                    obj1.zzbTi = 1;
                    obj1.zzbTj = 1;
                    obj1 = new com.google.android.gms.people.Images.LoadImageOptions(((com.google.android.gms.people.Images.LoadImageOptions.Builder) (obj1)));
                    contactinfo = (com.google.android.gms.people.Images.LoadImageResult)People.ImageApi.loadByReference(contactinfo, ((com.google.android.gms.people.model.AvatarReference) (obj)), ((com.google.android.gms.people.Images.LoadImageOptions) (obj1))).await(5L, TimeUnit.SECONDS);
                    obj = contactinfo.getParcelFileDescriptor();
                    contactinfo = contactinfo.getStatus();
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
                        obj = new android.os.ParcelFileDescriptor.AutoCloseInputStream(((android.os.ParcelFileDescriptor) (obj)));
                    }
                } else
                if (((ContactInfo) (obj)).lookupKey != null && ((ContactInfo) (obj)).contactId != null)
                {
                    obj = android.provider.ContactsContract.Contacts.getLookupUri(((ContactInfo) (obj)).contactId.longValue(), ((ContactInfo) (obj)).lookupKey);
                    obj = android.provider.ContactsContract.Contacts.openContactPhotoInputStream(contactinfo.getApplicationContext().getContentResolver(), ((android.net.Uri) (obj)));
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

            .Lambda._cls0()
            {
                arg$1 = ContactPhotoRequestKey.this;
            }
        }

        if (future == null || future.isCancelled())
        {
            future = (FluentFuture)CalendarExecutor.DISK.submit(new .Lambda._cls0());
        }
        return BitmapCallbacks.listen(this, callback, future);
    }

    public final InputStream createInputStream()
        throws IOException
    {
        return null;
    }

    public final boolean equals(Object obj)
    {
        if (obj == null || !(obj instanceof ContactPhotoRequestKey))
        {
            return false;
        } else
        {
            obj = (ContactPhotoRequestKey)obj;
            return contactInfo.equals(((ContactPhotoRequestKey) (obj)).contactInfo);
        }
    }

    public final boolean hasOrientationExif()
        throws IOException
    {
        return false;
    }

    public final int hashCode()
    {
        return contactInfo.hashCode();
    }
}
