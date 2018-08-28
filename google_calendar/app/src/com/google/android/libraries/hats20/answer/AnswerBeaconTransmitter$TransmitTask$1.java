// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.hats20.answer;

import android.util.Log;
import com.google.android.libraries.hats20.cookie.HatsCookieManager;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.hats20.answer:
//            AnswerBeaconTransmitter

final class val.beaconType
    implements com.google.android.libraries.hats20.network.al.beaconType
{

    private final val.beaconType this$1;
    private final String val$beaconType;

    public final void onFailed(Exception exception)
    {
    }

    public final void onSucceeded(int i, String s, Map map)
    {
        if (i == 200)
        {
            s = String.valueOf(val$beaconType);
            if (s.length() != 0)
            {
                "Successfully transmitted answer beacon of type: ".concat(s);
            } else
            {
                new String("Successfully transmitted answer beacon of type: ");
            }
            hatsCookieManager.putCookie(answerUrl, map);
            return;
        } else
        {
            s = val$beaconType;
            Log.e("HatsLibTransmitter", (new StringBuilder(String.valueOf(s).length() + 74)).append("Failed to transmit answer beacon of type: ").append(s).append("; response code was: ").append(i).toString());
            return;
        }
    }

    ()
    {
        this$1 = final_;
        val$beaconType = String.this;
        super();
    }
}
