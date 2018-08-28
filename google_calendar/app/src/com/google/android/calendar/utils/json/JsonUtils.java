// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.utils.json;

import android.text.TextUtils;
import android.util.Log;
import com.google.api.client.json.GenericJson;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.JsonGenerator;
import com.google.api.client.json.JsonParser;
import com.google.api.client.util.Charsets;
import java.io.ByteArrayOutputStream;

public class JsonUtils
{

    private static final String TAG = com/google/android/calendar/utils/json/JsonUtils.getSimpleName();

    public JsonUtils()
    {
    }

    public static GenericJson fromString(String s, Class class1, GenericJson genericjson)
    {
        if (TextUtils.isEmpty(s))
        {
            return null;
        }
        try
        {
            s = (GenericJson)com.google.api.client.extensions.android.json.AndroidJsonFactory.InstanceHolder.INSTANCE.createJsonParser(s).parse(class1, false, null);
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            Log.wtf(TAG, "Failed to deserialize JSON.", s);
            return null;
        }
        return s;
    }

    public static String toString(GenericJson genericjson)
    {
        if (genericjson == null)
        {
            return null;
        }
        try
        {
            Object obj = com.google.api.client.extensions.android.json.AndroidJsonFactory.InstanceHolder.INSTANCE;
            ByteArrayOutputStream bytearrayoutputstream = new ByteArrayOutputStream();
            obj = ((JsonFactory) (obj)).createJsonGenerator(bytearrayoutputstream, Charsets.UTF_8);
            ((JsonGenerator) (obj)).serialize(false, genericjson);
            ((JsonGenerator) (obj)).flush();
            genericjson = bytearrayoutputstream.toString("UTF-8");
        }
        // Misplaced declaration of an exception variable
        catch (GenericJson genericjson)
        {
            Log.wtf(TAG, "Failed to deserialize JSON.", genericjson);
            return null;
        }
        return genericjson;
    }

}
