// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20;

import android.content.SharedPreferences;
import android.util.Log;
import com.google.android.libraries.hats20.network.GcsResponse;
import com.google.android.libraries.hats20.storage.HatsDataStore;

// Referenced classes of package com.google.android.libraries.hats20:
//            HatsDownloadRequest, HatsControllerImpl

final class val.hatsDataStore
    implements com.google.android.libraries.hats20.network.stener
{

    private final HatsDownloadRequest val$downloadRequest;
    private final HatsDataStore val$hatsDataStore;

    public final void onError(Exception exception)
    {
        Log.w("HatsLibClient", String.format("Site ID %s failed to download with error: %s", new Object[] {
            val$downloadRequest.siteId, exception.toString()
        }));
        exception = val$hatsDataStore;
        String s = val$downloadRequest.siteId;
        long l = (System.currentTimeMillis() + HatsDataStore.MILLIS_TO_CACHE_FAILED_DOWNLOAD) / 1000L;
        ((HatsDataStore) (exception)).sharedPreferences.edit().putInt(HatsDataStore.getKeyForPrefSuffix(s, "RESPONSE_CODE"), 4).putLong(HatsDataStore.getKeyForPrefSuffix(s, "EXPIRATION_DATE"), l).putString(HatsDataStore.getKeyForPrefSuffix(s, "CONTENT"), "").apply();
    }

    public final void onSuccess(GcsResponse gcsresponse)
    {
        String.format("Site ID %s downloaded with response code: %s", new Object[] {
            val$downloadRequest.siteId, Integer.valueOf(gcsresponse.responseCode)
        });
        HatsDataStore hatsdatastore = val$hatsDataStore;
        int j = gcsresponse.responseCode;
        long l = gcsresponse.expirationDateUnix;
        String s = gcsresponse.surveyJson;
        String s1 = val$downloadRequest.siteId;
        int i = j;
        if (j != 0)
        {
            i = j;
            if (j != 1)
            {
                i = j;
                if (j != 2)
                {
                    i = j;
                    if (j != 3)
                    {
                        i = 5;
                    }
                }
            }
        }
        long l1 = (System.currentTimeMillis() + 0x240c8400L) / 1000L;
        hatsdatastore.sharedPreferences.edit().putInt(HatsDataStore.getKeyForPrefSuffix(s1, "RESPONSE_CODE"), i).putLong(HatsDataStore.getKeyForPrefSuffix(s1, "EXPIRATION_DATE"), Math.min(l1, l)).putString(HatsDataStore.getKeyForPrefSuffix(s1, "CONTENT"), s).apply();
        HatsControllerImpl.sendBroadcast(val$downloadRequest.context, val$downloadRequest.siteId, gcsresponse.responseCode);
    }

    ()
    {
        val$downloadRequest = hatsdownloadrequest;
        val$hatsDataStore = hatsdatastore;
        super();
    }
}
