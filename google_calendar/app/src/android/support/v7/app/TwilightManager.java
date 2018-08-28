// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package android.support.v7.app;

import android.content.Context;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.content.PermissionChecker;
import java.util.Calendar;

// Referenced classes of package android.support.v7.app:
//            TwilightCalculator

final class TwilightManager
{

    public static TwilightManager sInstance;
    private final Context mContext;
    private final LocationManager mLocationManager;
    private final TwilightState mTwilightState = new TwilightState();

    TwilightManager(Context context, LocationManager locationmanager)
    {
        mContext = context;
        mLocationManager = locationmanager;
    }

    private final Location getLastKnownLocationForProvider(String s)
    {
        if (!mLocationManager.isProviderEnabled(s))
        {
            break MISSING_BLOCK_LABEL_23;
        }
        s = mLocationManager.getLastKnownLocation(s);
        return s;
        s;
        return null;
    }

    final boolean isNight()
    {
        TwilightState twilightstate = mTwilightState;
        boolean flag;
        if (mTwilightState.nextUpdate > System.currentTimeMillis())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            return twilightstate.isNight;
        }
        Object obj = null;
        Object obj1 = null;
        if (PermissionChecker.checkSelfPermission(mContext, "android.permission.ACCESS_COARSE_LOCATION") == 0)
        {
            obj = getLastKnownLocationForProvider("network");
        }
        if (PermissionChecker.checkSelfPermission(mContext, "android.permission.ACCESS_FINE_LOCATION") == 0)
        {
            obj1 = getLastKnownLocationForProvider("gps");
        }
        TwilightCalculator twilightcalculator;
        if (obj1 != null && obj != null)
        {
            if (((Location) (obj1)).getTime() > ((Location) (obj)).getTime())
            {
                obj = obj1;
            }
            break MISSING_BLOCK_LABEL_102;
        } else
        {
            if (obj1 != null)
            {
                obj = obj1;
            }
            continue;
        }
        do
        {
            if (obj != null)
            {
                obj1 = mTwilightState;
                long l = System.currentTimeMillis();
                if (TwilightCalculator.sInstance == null)
                {
                    TwilightCalculator.sInstance = new TwilightCalculator();
                }
                twilightcalculator = TwilightCalculator.sInstance;
                twilightcalculator.calculateTwilight(l - 0x5265c00L, ((Location) (obj)).getLatitude(), ((Location) (obj)).getLongitude());
                twilightcalculator.calculateTwilight(l, ((Location) (obj)).getLatitude(), ((Location) (obj)).getLongitude());
                long l1;
                long l2;
                long l3;
                boolean flag1;
                if (twilightcalculator.state == 1)
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                l1 = twilightcalculator.sunrise;
                l2 = twilightcalculator.sunset;
                twilightcalculator.calculateTwilight(0x5265c00L + l, ((Location) (obj)).getLatitude(), ((Location) (obj)).getLongitude());
                l3 = twilightcalculator.sunrise;
                if (l1 == -1L || l2 == -1L)
                {
                    l = 0x2932e00L + l;
                } else
                {
                    if (l > l2)
                    {
                        l = l3 + 0L;
                    } else
                    if (l > l1)
                    {
                        l = 0L + l2;
                    } else
                    {
                        l = 0L + l1;
                    }
                    l += 60000L;
                }
                obj1.isNight = flag1;
                obj1.nextUpdate = l;
                return twilightstate.isNight;
            }
            int i = Calendar.getInstance().get(11);
            if (i < 6 || i >= 22)
            {
                return true;
            }
            return false;
        } while (true);
    }

    private class TwilightState
    {

        public boolean isNight;
        public long nextUpdate;

        TwilightState()
        {
        }
    }

}
