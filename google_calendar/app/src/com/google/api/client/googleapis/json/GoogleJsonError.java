// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.googleapis.json;

import com.google.api.client.json.GenericJson;
import com.google.api.client.util.Data;
import com.google.api.client.util.GenericData;
import java.util.List;

public class GoogleJsonError extends GenericJson
{
    public static class ErrorInfo extends GenericJson
    {

        public String domain;
        public String location;
        public String locationType;
        public String message;
        public String reason;

        public final volatile GenericJson clone()
        {
            return (ErrorInfo)clone();
        }

        public final volatile GenericData clone()
        {
            return (ErrorInfo)clone();
        }

        public Object clone()
            throws CloneNotSupportedException
        {
            return (ErrorInfo)super.clone();
        }

        public final volatile GenericJson set(String s, Object obj)
        {
            return (ErrorInfo)set(s, obj);
        }

        public final GenericData set(String s, Object obj)
        {
            return (ErrorInfo)super.set(s, obj);
        }

        public ErrorInfo()
        {
        }
    }


    public int code;
    public List errors;
    public String message;

    public GoogleJsonError()
    {
    }

    public final volatile GenericJson clone()
    {
        return (GoogleJsonError)clone();
    }

    public final volatile GenericData clone()
    {
        return (GoogleJsonError)clone();
    }

    public Object clone()
        throws CloneNotSupportedException
    {
        return (GoogleJsonError)super.clone();
    }

    public final volatile GenericJson set(String s, Object obj)
    {
        return (GoogleJsonError)set(s, obj);
    }

    public final GenericData set(String s, Object obj)
    {
        return (GoogleJsonError)super.set(s, obj);
    }

    static 
    {
        Data.nullOf(com/google/api/client/googleapis/json/GoogleJsonError$ErrorInfo);
    }
}
