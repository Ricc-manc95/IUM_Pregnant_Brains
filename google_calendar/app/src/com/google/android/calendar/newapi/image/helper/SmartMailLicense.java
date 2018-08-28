// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.image.helper;

import android.text.TextUtils;
import com.android.calendarcommon2.LogUtils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SmartMailLicense
{

    private static final String TAG = com/google/android/calendar/newapi/image/helper/SmartMailLicense.getSimpleName();
    private final JSONObject authorJson;
    private final JSONObject json;
    private final String language;
    private final JSONObject licenseJson;

    private SmartMailLicense(JSONObject jsonobject, String s)
        throws JSONException
    {
        Object obj;
        obj = null;
        super();
        json = jsonobject;
        licenseJson = jsonobject.getJSONArray("license").getJSONObject(0);
        language = s;
        if (licenseJson.has("author")) goto _L2; else goto _L1
_L1:
        jsonobject = obj;
_L4:
        authorJson = jsonobject;
        return;
_L2:
        s = licenseJson.getJSONArray("author");
        jsonobject = obj;
        if (s != null)
        {
            jsonobject = obj;
            if (s.length() != 0)
            {
                jsonobject = s.getJSONObject(0);
            }
        }
        if (true) goto _L4; else goto _L3
_L3:
    }

    private static String inputStreamToString(InputStream inputstream)
        throws IOException
    {
        BufferedReader bufferedreader;
        StringBuilder stringbuilder;
        stringbuilder = new StringBuilder();
        bufferedreader = new BufferedReader(new InputStreamReader(inputstream));
_L3:
        inputstream = bufferedreader.readLine();
        if (inputstream == null) goto _L2; else goto _L1
_L1:
        stringbuilder.append(inputstream);
          goto _L3
        inputstream;
_L5:
        if (bufferedreader != null)
        {
            bufferedreader.close();
        }
        throw inputstream;
_L2:
        inputstream = stringbuilder.toString();
        bufferedreader.close();
        return inputstream;
        inputstream;
        bufferedreader = null;
        if (true) goto _L5; else goto _L4
_L4:
    }

    public static SmartMailLicense loadLicense(String s, String s1)
    {
        s = new JSONObject(inputStreamToString((new URL(s)).openStream()));
        if (!s.has("license"))
        {
            return null;
        }
        JSONArray jsonarray = s.getJSONArray("license");
        if (jsonarray == null)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        if (jsonarray.length() == 0)
        {
            break MISSING_BLOCK_LABEL_78;
        }
        s = new SmartMailLicense(s, s1);
        return s;
        s;
        LogUtils.e(TAG, s, "Failed to load license", new Object[0]);
        return null;
    }

    final String getAuthorField(String s)
    {
        if (authorJson == null || !authorJson.has(s))
        {
            return null;
        }
        String s1;
        try
        {
            s1 = authorJson.getString(s);
        }
        catch (JSONException jsonexception)
        {
            LogUtils.i(TAG, jsonexception, "Could not read author field '%s' from JSON.", new Object[] {
                s
            });
            return null;
        }
        return s1;
    }

    public final String getLicenseAttribution()
    {
        if (licenseJson.has("attribution")) goto _L2; else goto _L1
_L1:
        return null;
_L2:
        Object obj;
        JSONObject jsonobject;
        int i;
        try
        {
            obj = licenseJson.getJSONArray("attribution");
        }
        catch (JSONException jsonexception)
        {
            LogUtils.i(TAG, jsonexception, "Could not read license attribution from JSON.", new Object[0]);
            return null;
        }
        i = 0;
        if (i >= ((JSONArray) (obj)).length())
        {
            continue;
        }
        jsonobject = ((JSONArray) (obj)).getJSONObject(i);
        if (!language.equals(jsonobject.getString("language")) || TextUtils.isEmpty(jsonobject.getString("text")))
        {
            break MISSING_BLOCK_LABEL_77;
        }
        obj = jsonobject.getString("text");
        return ((String) (obj));
label0:
        {
            for (i++; true;)
            {
                break MISSING_BLOCK_LABEL_26;
            }

            break label0;
        }
        if (true) goto _L1; else goto _L3
_L3:
    }

    public final String getName()
    {
        String s;
        try
        {
            s = licenseJson.getString("name");
        }
        catch (JSONException jsonexception)
        {
            LogUtils.i(TAG, jsonexception, "Could not read license['name'] from JSON.", new Object[0]);
            return null;
        }
        return s;
    }

    public final String getReferrerUrl()
    {
        String s;
        try
        {
            s = json.getString("referrer_url");
        }
        catch (JSONException jsonexception)
        {
            LogUtils.i(TAG, jsonexception, "Could not read referrer_url from JSON.", new Object[0]);
            return null;
        }
        return s;
    }

    public final Set getRequirements()
    {
        HashSet hashset = new HashSet();
        if (licenseJson.has("requirement")) goto _L2; else goto _L1
_L1:
        return hashset;
_L2:
        JSONArray jsonarray;
        int i;
        try
        {
            jsonarray = licenseJson.getJSONArray("requirement");
        }
        catch (JSONException jsonexception)
        {
            LogUtils.i(TAG, jsonexception, "Could not read requirments from JSON.", new Object[0]);
            return hashset;
        }
        i = 0;
        if (i >= jsonarray.length())
        {
            continue; /* Loop/switch isn't completed */
        }
        hashset.add(Integer.valueOf(jsonarray.getInt(i)));
        i++;
        if (true) goto _L4; else goto _L3
_L3:
        break MISSING_BLOCK_LABEL_60;
_L4:
        break MISSING_BLOCK_LABEL_34;
        if (true) goto _L1; else goto _L5
_L5:
    }

    public final String getUri()
    {
        String s;
        try
        {
            s = licenseJson.getString("uri");
        }
        catch (JSONException jsonexception)
        {
            LogUtils.i(TAG, jsonexception, "Could not read license['uri'] from JSON.", new Object[0]);
            return null;
        }
        return s;
    }

}
