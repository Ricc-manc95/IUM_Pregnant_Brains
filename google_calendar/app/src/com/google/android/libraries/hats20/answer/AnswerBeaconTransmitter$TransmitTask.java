// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.answer;

import android.net.Uri;
import android.support.v4.util.ArrayMap;
import android.support.v4.util.SimpleArrayMap;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.libraries.hats20.Constants;
import com.google.android.libraries.hats20.cookie.HatsCookieManager;
import com.google.android.libraries.hats20.inject.HatsModule;
import com.google.android.libraries.hats20.network.GcsConnection;

// Referenced classes of package com.google.android.libraries.hats20.answer:
//            AnswerBeaconTransmitter

public final class uri
    implements Runnable
{

    public final AnswerBeaconTransmitter this$0;
    private final Uri uri;

    public final void run()
    {
        try
        {
            if (!answerUrl.equals("/"))
            {
                final String beaconType = uri.getQueryParameter("t");
                byte abyte0[] = uri.getEncodedQuery().getBytes(Constants.UTF_8);
                ArrayMap arraymap = new ArrayMap();
                arraymap.put("Content-Type", "application/x-www-form-urlencoded");
                arraymap.put("Content-Length", Integer.toString(abyte0.length));
                arraymap.put("charset", "utf-8");
                arraymap.put("Connection", "close");
                arraymap.put("User-Agent", HatsModule.get().getUserAgent());
                String s = hatsCookieManager.getCookie(answerUrl);
                if (!TextUtils.isEmpty(s))
                {
                    arraymap.put("Cookie", s);
                }
                class _cls1
                    implements com.google.android.libraries.hats20.network.GcsConnection.Callbacks
                {

                    private final AnswerBeaconTransmitter.TransmitTask this$1;
                    private final String val$beaconType;

                    public final void onFailed(Exception exception1)
                    {
                    }

                    public final void onSucceeded(int i, String s1, Map map)
                    {
                        if (i == 200)
                        {
                            s1 = String.valueOf(beaconType);
                            if (s1.length() != 0)
                            {
                                "Successfully transmitted answer beacon of type: ".concat(s1);
                            } else
                            {
                                new String("Successfully transmitted answer beacon of type: ");
                            }
                            hatsCookieManager.putCookie(answerUrl, map);
                            return;
                        } else
                        {
                            s1 = beaconType;
                            Log.e("HatsLibTransmitter", (new StringBuilder(String.valueOf(s1).length() + 74)).append("Failed to transmit answer beacon of type: ").append(s1).append("; response code was: ").append(i).toString());
                            return;
                        }
                    }

            _cls1()
            {
                this$1 = AnswerBeaconTransmitter.TransmitTask.this;
                beaconType = s;
                super();
            }
                }

                HatsModule.get().getGcsConnection().send(answerUrl, abyte0, arraymap, new _cls1());
            }
            return;
        }
        catch (Exception exception)
        {
            Log.e("HatsLibTransmitter", "Transmission of answer beacon failed.", exception);
        }
    }

    public _cls1(Uri uri1)
    {
        this$0 = AnswerBeaconTransmitter.this;
        super();
        uri = uri1;
    }
}
