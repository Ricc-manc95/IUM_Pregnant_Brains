// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.text.TextUtils;
import com.google.android.calendar.event.image.cache.UnknownUrlVolleyRequestKey;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.IOException;

// Referenced classes of package com.google.android.calendar.event.image:
//            BitmapCallbacks, PlacePageOrMapUrl

public final class PlacePageOrMapRequestKey extends UnknownUrlVolleyRequestKey
{

    private final String address;
    private final int height;
    private final String latitude;
    private final String longitude;
    private final String mapsClusterId;
    private final int width;

    public PlacePageOrMapRequestKey(String s, String s1, String s2, String s3, int i, int j)
    {
        mapsClusterId = s;
        latitude = s1;
        longitude = s2;
        address = s3;
        width = i;
        height = j;
    }

    public final com.android.bitmap.RequestKey.Cancelable createFileDescriptorFactoryOrByteArrayAsync$51666RRD5TGMSP3IDTKM8BR2D5Q6QOBG5T96ASBLCLPN8IR5F4TKOORFDKNM2RJ4E9NMIP1FC9KN8RB1E0NL4PBHELIN6T2BCLSI8GR1DHM64OB3DCTIIJ33DTMIUOBECHP6UQB45TH6IT3DC5O2UKJ5E5QMASRK9DINI923C5N66PBCC5H6OP9R0(com.android.bitmap.RequestKey.Callback callback)
    {
        return BitmapCallbacks.listen(this, callback, new com.google.android.calendar.event.image.cache.UnknownUrlVolleyRequestKey.FetchFuture(this));
    }

    public final boolean equals(Object obj)
    {
        if (obj != null && (obj instanceof PlacePageOrMapRequestKey))
        {
            if (width == ((PlacePageOrMapRequestKey) (obj = (PlacePageOrMapRequestKey)obj)).width && height == ((PlacePageOrMapRequestKey) (obj)).height && TextUtils.equals(mapsClusterId, ((PlacePageOrMapRequestKey) (obj)).mapsClusterId) && TextUtils.equals(latitude, ((PlacePageOrMapRequestKey) (obj)).latitude) && TextUtils.equals(longitude, ((PlacePageOrMapRequestKey) (obj)).longitude))
            {
                return true;
            }
        }
        return false;
    }

    protected final ListenableFuture getUrlAsync()
    {
        class .Lambda._cls0
            implements Function
        {

            public static final Function $instance = new .Lambda._cls0();

            public final Object apply(Object obj)
            {
                obj = (PlacePageOrMapUrl)obj;
                if (((PlacePageOrMapUrl) (obj)).placePageImageUrl != null)
                {
                    return ((PlacePageOrMapUrl) (obj)).placePageImageUrl;
                } else
                {
                    return ((PlacePageOrMapUrl) (obj)).staticMapUrl;
                }
            }


            private .Lambda._cls0()
            {
            }
        }

        return AbstractTransformFuture.create(PlacePageOrMapUrl.getPlacePageOrMapsUrl(mapsClusterId, latitude, longitude, address, width, height), .Lambda._cls0..instance, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    public final boolean hasOrientationExif()
        throws IOException
    {
        return false;
    }

    public final int hashCode()
    {
        int l = 0;
        int i;
        int j;
        int k;
        if (mapsClusterId != null)
        {
            i = mapsClusterId.hashCode();
        } else
        {
            i = 0;
        }
        if (latitude != null)
        {
            j = latitude.hashCode();
        } else
        {
            j = 0;
        }
        if (longitude != null)
        {
            k = longitude.hashCode();
        } else
        {
            k = 0;
        }
        if (address != null)
        {
            l = address.hashCode();
        }
        return (((k + (j + i * 31) * 31) * 31 + l) * 31 + width) * 31 + height;
    }
}
