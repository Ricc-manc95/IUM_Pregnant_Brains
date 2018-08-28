// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.event.image;

import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.google.android.apps.calendar.util.concurrent.CalendarExecutor;
import com.google.android.calendar.api.event.location.Address;
import com.google.android.calendar.api.event.location.EventLocation;
import com.google.android.calendar.api.event.location.GeoCoordinates;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.android.calendar.volley.VolleyQueueHolder;
import com.google.common.util.concurrent.AbstractTransformFuture;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.TimeoutFuture;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PlacePageOrMapUrl
{

    private static final String TAG = com/google/android/calendar/event/image/PlacePageOrMapUrl.getSimpleName();
    public static final Map attributionsMap = new HashMap();
    public final String placePageImageUrl;
    public final String staticMapUrl;

    public PlacePageOrMapUrl()
    {
        placePageImageUrl = null;
        staticMapUrl = null;
    }

    private PlacePageOrMapUrl(String s, String s1)
    {
        placePageImageUrl = s;
        staticMapUrl = s1;
    }

    public static ListenableFuture getPlacePageOrMapsUrl(EventLocation eventlocation, int i, int j)
    {
        String s1 = null;
        if (eventlocation == null)
        {
            eventlocation = new PlacePageOrMapUrl();
            if (eventlocation == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(eventlocation);
            }
        }
        String s2 = eventlocation.mapsClusterId;
        Object obj = eventlocation.geo;
        String s;
        if (obj != null)
        {
            s = Double.toString(((GeoCoordinates) (obj)).latitude);
            obj = Double.toString(((GeoCoordinates) (obj)).longitude);
        } else
        {
            obj = null;
            s = null;
        }
        if (eventlocation.address != null)
        {
            s1 = eventlocation.address.formattedAddress;
        }
        return getPlacePageOrMapsUrl(s2, s, ((String) (obj)), s1, i, j);
    }

    public static ListenableFuture getPlacePageOrMapsUrl(String s, String s1, String s2, String s3, int i, int j)
    {
        if (TextUtils.isEmpty(s))
        {
            s = new PlacePageOrMapUrl();
            if (s == null)
            {
                return com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                return new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(s);
            }
        }
        Object obj = "https://maps.googleapis.com/maps/api/place/details/json?reference=[REFERENCE_ID]&sensor=true&key=AIzaSyDwzOp5nlDuTO2MtXeMek6aD5e6rQs49Mk".replace("[REFERENCE_ID]", s);
        LogUtils.v(TAG, "place details url: %s", new Object[] {
            obj
        });
        long l = TimeUnit.DAYS.toMillis(30L);
        class .Lambda._cls0
            implements Function
        {

            private final String arg$1;
            private final String arg$2;
            private final String arg$3;
            private final int arg$4;
            private final int arg$5;

            public final Object apply(Object obj1)
            {
                return PlacePageOrMapUrl.lambda$getPlacePageOrMapsUrl$0$PlacePageOrMapUrl(arg$1, arg$2, arg$3, arg$4, arg$5, (String)obj1);
            }

            .Lambda._cls0(String s, String s1, String s2, int i, int j)
            {
                arg$1 = s;
                arg$2 = s1;
                arg$3 = s2;
                arg$4 = i;
                arg$5 = j;
            }
        }

        CalendarExecutor calendarexecutor;
        TimeoutFuture timeoutfuture;
        com.google.common.util.concurrent.TimeoutFuture.Fire fire;
        if (obj == null)
        {
            if (true)
            {
                s = com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture.NULL;
            } else
            {
                s = new com.google.common.util.concurrent.ImmediateFuture.ImmediateSuccessfulFuture(null);
            }
        } else
        {
            s = new com.google.android.calendar.volley.VolleyRequests.VolleyRequestFuture();
            obj = new com.google.android.calendar.volley.VolleyRequests._cls1(((String) (obj)), s, s, l);
            boolean flag;
            if (((com.google.android.calendar.volley.VolleyRequests.VolleyRequestFuture) (s)).request == null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Already started"));
            }
            s.request = ((Request) (obj));
            ((com.google.android.calendar.volley.VolleyRequests.VolleyRequestFuture) (s)).request.mTag = "calendar_volley_request";
            obj = VolleyQueueHolder.requestQueue;
            if (obj == null)
            {
                throw new NullPointerException(String.valueOf("VolleyQueueHolder#initialize(...) must be called first"));
            }
            ((RequestQueue)obj).add(((com.google.android.calendar.volley.VolleyRequests.VolleyRequestFuture) (s)).request);
        }
        obj = TimeUnit.SECONDS;
        calendarexecutor = CalendarExecutor.BACKGROUND;
        timeoutfuture = new TimeoutFuture(s);
        fire = new com.google.common.util.concurrent.TimeoutFuture.Fire(timeoutfuture);
        timeoutfuture.timer = calendarexecutor.schedule(fire, 5L, ((TimeUnit) (obj)));
        s.addListener(fire, com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
        return AbstractTransformFuture.create(timeoutfuture, new .Lambda._cls0(s1, s2, s3, i, j), com.google.common.util.concurrent.MoreExecutors.DirectExecutor.INSTANCE);
    }

    private static String getUrlFromJson(String s, int i, int j)
    {
        int k = 1;
        Object obj = (new JSONObject(s)).getJSONObject("result");
        if (!((JSONObject) (obj)).has("photos"))
        {
            return null;
        }
        s = ((JSONObject) (obj)).getJSONArray("photos");
        if (s == null) goto _L2; else goto _L1
_L1:
        if (s.length() != 0) goto _L3; else goto _L2
_L3:
        LogUtils.v(TAG, "name: %s; photos: %s", new Object[] {
            ((JSONObject) (obj)).getString("name"), s
        });
        if (s.length() >= 10)
        {
            k = Math.min(s.length(), 3);
        }
          goto _L4
_L11:
        int l;
        if (l >= k)
        {
            break MISSING_BLOCK_LABEL_391;
        }
        float f;
        Object obj1;
        obj = s.getJSONObject(l);
        if (((JSONObject) (obj)).getInt("width") < 650 || ((JSONObject) (obj)).getInt("height") < 300)
        {
            break MISSING_BLOCK_LABEL_405;
        }
        k = ((JSONObject) (obj)).getInt("width");
        l = ((JSONObject) (obj)).getInt("height");
        LogUtils.v(TAG, "photo: %s", new Object[] {
            obj
        });
        obj1 = "https://maps.googleapis.com/maps/api/place/photo?photoreference=[PHOTO_REFERENCE]&[DIMENSION_RESTRICTIONS]&sensor=true&key=AIzaSyDwzOp5nlDuTO2MtXeMek6aD5e6rQs49Mk".replace("[PHOTO_REFERENCE]", (String)((JSONObject) (obj)).get("photo_reference"));
        f = (float)k / (float)l;
        if ((float)i / (float)j <= f) goto _L6; else goto _L5
_L5:
        i = Math.min(k, i);
        s = (new StringBuilder(20)).append("maxwidth=").append(i).toString();
_L9:
        s = ((String) (obj1)).replace("[DIMENSION_RESTRICTIONS]", s);
        obj = ((JSONObject) (obj)).getJSONArray("html_attributions");
        if (obj == null)
        {
            break MISSING_BLOCK_LABEL_393;
        }
        if (((JSONArray) (obj)).length() <= 0)
        {
            break MISSING_BLOCK_LABEL_393;
        }
        obj1 = new ArrayList();
        i = 0;
_L8:
        if (i >= ((JSONArray) (obj)).length())
        {
            break; /* Loop/switch isn't completed */
        }
        ((List) (obj1)).add(((JSONArray) (obj)).getString(i));
        i++;
        if (true) goto _L8; else goto _L7
_L6:
        i = Math.min(l, j);
        s = (new StringBuilder(21)).append("maxheight=").append(i).toString();
          goto _L9
_L7:
        attributionsMap.put(s, obj1);
        return s;
        s;
        LogUtils.e(TAG, s, "Parsing the Places API Details response failed.", new Object[0]);
        return null;
        attributionsMap.remove(s);
        return s;
        l++;
        continue; /* Loop/switch isn't completed */
_L2:
        return null;
_L4:
        l = 0;
        if (true) goto _L11; else goto _L10
_L10:
    }

    static final PlacePageOrMapUrl lambda$getPlacePageOrMapsUrl$0$PlacePageOrMapUrl(String s, String s1, String s2, int i, int j, String s3)
    {
        s3 = getUrlFromJson(s3, i, j);
        if (!TextUtils.isEmpty(s3))
        {
            LogUtils.d(TAG, "place page image url: %s", new Object[] {
                s3
            });
            return new PlacePageOrMapUrl(s3, null);
        }
        if (s != null && s1 != null)
        {
            s = TimelyUtils.getStaticMapsUrl(s, s1, s2, i, j);
            if (!TextUtils.isEmpty(s))
            {
                LogUtils.d(TAG, "static maps image url: %s", new Object[] {
                    s
                });
                return new PlacePageOrMapUrl(null, s);
            }
        }
        return new PlacePageOrMapUrl();
    }

}
