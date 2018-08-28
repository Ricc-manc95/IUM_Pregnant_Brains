// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.timely.location;

import android.content.Context;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import com.android.calendarcommon2.LogUtils;
import com.google.android.calendar.event.EventLocationResult;
import com.google.android.calendar.timely.SuggestionFetcher;
import com.google.android.calendar.utils.timely.TimelyUtils;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.FusedLocationProviderApi;
import com.google.android.gms.location.LocationServices;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

// Referenced classes of package com.google.android.calendar.timely.location:
//            LocationFetcher, GoogleLocationSettingHelper

final class this._cls0 extends AsyncTask
{

    private final LocationFetcher this$0;

    private final transient List doInBackground(String as[])
    {
        as = as[0];
        LogUtils.v(LocationFetcher.TAG, "Fetch Places Suggestions: %s", new Object[] {
            as
        });
        android.net.  = Uri.parse("https://maps.googleapis.com/maps/api/place/autocomplete/json").buildUpon();
        .TAG("key", "AIzaSyDwzOp5nlDuTO2MtXeMek6aD5e6rQs49Mk").TAG("language", Locale.getDefault().getLanguage()).TAG("sensor", "true").TAG("input", as);
        if (locationClient.isConnected() && GoogleLocationSettingHelper.isGoogleLocationServicesEnabled(context))
        {
            try
            {
                as = LocationServices.FusedLocationApi.getLastLocation(locationClient);
            }
            // Misplaced declaration of an exception variable
            catch (String as[])
            {
                LogUtils.i(LocationFetcher.TAG, as, "exception while fetching location", new Object[0]);
                as = null;
            }
            if (as != null)
            {
                .TAG("location", String.format(Locale.getDefault(), "%f,%f", new Object[] {
                    Double.valueOf(as.getLatitude()), Double.valueOf(as.getLongitude())
                })).TAG("radius", Integer.toString(TimelyUtils.getPlacesRadiusMeters()));
            }
        }
        return getPlaceSuggestions(TimelyUtils.executeJsonRequest(context, .context()));
    }

    private final List getPlaceSuggestions(JSONObject jsonobject)
    {
        ArrayList arraylist = new ArrayList();
        if (jsonobject != null) goto _L2; else goto _L1
_L1:
        return arraylist;
_L10:
        j = jsonarray.length();
        int i;
        if (i >= j || i >= 3) goto _L1; else goto _L3
_L3:
        jsonobject1 = jsonarray.getJSONObject(i);
        obj1 = jsonobject1.getJSONArray("terms");
        s = jsonobject1.getString("description");
        if (((JSONArray) (obj1)).length() > 1) goto _L5; else goto _L4
_L4:
        obj = null;
_L6:
        obj = new EventLocationResult(context, s, ((String) (obj)), jsonobject1.getString("reference"));
        arraylist.add(obj);
        LogUtils.v(LocationFetcher.TAG, "%s", new Object[] {
            obj
        });
        break MISSING_BLOCK_LABEL_406;
_L5:
        if (((JSONArray) (obj1)).length() != 2)
        {
            break MISSING_BLOCK_LABEL_255;
        }
        obj = ((JSONArray) (obj1)).getJSONObject(0);
        obj1 = ((JSONArray) (obj1)).getJSONObject(1);
        obj = ((JSONObject) (obj)).getString("value");
        obj1 = s.substring(((JSONObject) (obj1)).getInt("offset"));
        s = ((String) (obj));
        obj = obj1;
          goto _L6
        obj = ((JSONArray) (obj1)).getJSONObject(1).getString("value");
        obj1 = ((JSONArray) (obj1)).getJSONObject(2);
        s1 = s.substring(0, ((JSONObject) (obj1)).getInt("offset"));
        j = s1.lastIndexOf(((String) (obj)));
        if (j != -1) goto _L8; else goto _L7
_L7:
        j = s1.length();
_L9:
        obj = s.substring(0, j);
        obj1 = s.substring(((JSONObject) (obj1)).getInt("offset"));
        s = ((String) (obj));
        obj = obj1;
          goto _L6
_L8:
        k = ((String) (obj)).length();
        j = k + j;
          goto _L9
        jsonexception;
        LogUtils.e(LocationFetcher.TAG, jsonexception, "Invalid Response: %s", new Object[] {
            jsonarray
        });
        break MISSING_BLOCK_LABEL_406;
_L2:
        {
            Object obj;
            JSONException jsonexception;
            String s;
            Object obj1;
            JSONArray jsonarray;
            JSONObject jsonobject1;
            String s1;
            int j;
            int k;
            try
            {
                jsonarray = jsonobject.getJSONArray("predictions");
                LogUtils.d(LocationFetcher.TAG, "getPlaceData %d results", new Object[] {
                    Integer.valueOf(jsonarray.length())
                });
                if (jsonarray.length() > 0)
                {
                    arraylist.add(new EventLocationResult(context, context.getString(0x7f130346)));
                }
            }
            catch (JSONException jsonexception1)
            {
                LogUtils.e(LocationFetcher.TAG, jsonexception1, "Invalid Response: %s", new Object[] {
                    jsonobject
                });
                return arraylist;
            }
            i = 0;
        }
          goto _L10
        i++;
          goto _L10
    }

    public final volatile Object doInBackground(Object aobj[])
    {
        return doInBackground((String[])aobj);
    }

    protected final void onPostExecute(Object obj)
    {
        obj = (List)obj;
        super.onPostExecute(obj);
        LocationFetcher locationfetcher = LocationFetcher.this;
        if (((SuggestionFetcher) (locationfetcher)).suggestionsListener != null)
        {
            ((SuggestionFetcher) (locationfetcher)).suggestionsListener.onUpdateSuggestions(((List) (obj)));
        }
        fetchSuggestionsTask = null;
    }

    ()
    {
        this$0 = LocationFetcher.this;
        super();
    }
}
