// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.StringUtils;
import com.google.api.client.util.Types;
import com.google.common.base.Strings;
import java.io.IOException;
import java.io.Writer;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

// Referenced classes of package com.google.api.client.http:
//            LowLevelHttpRequest

public final class HttpHeaders extends GenericData
{

    public List accept;
    public List acceptEncoding;
    public List age;
    public List authenticate;
    public List authorization;
    public List cacheControl;
    public List contentEncoding;
    public List contentLength;
    public List contentMD5;
    public List contentRange;
    public List contentType;
    public List cookie;
    public List date;
    public List etag;
    public List expires;
    public List ifMatch;
    public List ifModifiedSince;
    public List ifNoneMatch;
    public List ifRange;
    public List ifUnmodifiedSince;
    public List lastModified;
    public List location;
    public List mimeVersion;
    public List range;
    public List retryAfter;
    public List userAgent;

    public HttpHeaders()
    {
        super(EnumSet.of(com.google.api.client.util.GenericData.Flags.IGNORE_CASE));
        acceptEncoding = new ArrayList(Collections.singleton("gzip"));
    }

    private static void addHeader(Logger logger, StringBuilder stringbuilder, StringBuilder stringbuilder1, LowLevelHttpRequest lowlevelhttprequest, String s, Object obj, Writer writer)
        throws IOException
    {
        if (obj != null && !Data.isNull(obj))
        {
            if (obj instanceof Enum)
            {
                obj = FieldInfo.of((Enum)obj).name;
            } else
            {
                obj = obj.toString();
            }
            if (("Authorization".equalsIgnoreCase(s) || "Cookie".equalsIgnoreCase(s)) && (logger == null || !logger.isLoggable(Level.ALL)))
            {
                logger = "<Not Logged>";
            } else
            {
                logger = ((Logger) (obj));
            }
            if (stringbuilder != null)
            {
                stringbuilder.append(s).append(": ");
                stringbuilder.append(logger);
                stringbuilder.append(StringUtils.LINE_SEPARATOR);
            }
            if (stringbuilder1 != null)
            {
                stringbuilder1.append(" -H '").append(s).append(": ").append(logger).append("'");
            }
            if (lowlevelhttprequest != null)
            {
                lowlevelhttprequest.addHeader(s, ((String) (obj)));
            }
            if (writer != null)
            {
                writer.write(s);
                writer.write(": ");
                writer.write(((String) (obj)));
                writer.write("\r\n");
                return;
            }
        }
    }

    static void serializeHeaders(HttpHeaders httpheaders, StringBuilder stringbuilder, StringBuilder stringbuilder1, Logger logger, LowLevelHttpRequest lowlevelhttprequest, Writer writer)
        throws IOException
    {
        HashSet hashset = new HashSet();
        Iterator iterator = httpheaders.entrySet().iterator();
        do
        {
            if (!iterator.hasNext())
            {
                break;
            }
            Object obj = (java.util.Map.Entry)iterator.next();
            String s = (String)((java.util.Map.Entry) (obj)).getKey();
            if (!hashset.add(s))
            {
                throw new IllegalArgumentException(Strings.lenientFormat("multiple headers of the same name (headers are case insensitive): %s", new Object[] {
                    s
                }));
            }
            obj = ((java.util.Map.Entry) (obj)).getValue();
            if (obj != null)
            {
                Object obj1 = ((GenericData) (httpheaders)).classInfo.getFieldInfo(s);
                if (obj1 != null)
                {
                    s = ((FieldInfo) (obj1)).name;
                }
                obj1 = obj.getClass();
                if ((obj instanceof Iterable) || ((Class) (obj1)).isArray())
                {
                    obj = Types.iterableOf(obj).iterator();
                    while (((Iterator) (obj)).hasNext()) 
                    {
                        addHeader(logger, stringbuilder, stringbuilder1, lowlevelhttprequest, s, ((Iterator) (obj)).next(), writer);
                    }
                } else
                {
                    addHeader(logger, stringbuilder, stringbuilder1, lowlevelhttprequest, s, obj, writer);
                }
            }
        } while (true);
        if (writer != null)
        {
            writer.flush();
        }
    }

    public final volatile GenericData clone()
    {
        return (HttpHeaders)clone();
    }

    public final Object clone()
        throws CloneNotSupportedException
    {
        return (HttpHeaders)super.clone();
    }

    final void parseHeader(String s, String s1, ParseHeaderState parseheaderstate)
    {
        Object obj = parseheaderstate.context;
        Object obj1 = parseheaderstate.classInfo;
        ArrayValueMap arrayvaluemap = parseheaderstate.arrayValueMap;
        parseheaderstate = parseheaderstate.logger;
        if (parseheaderstate != null)
        {
            parseheaderstate.append((new StringBuilder(String.valueOf(s).length() + 2 + String.valueOf(s1).length())).append(s).append(": ").append(s1).toString()).append(StringUtils.LINE_SEPARATOR);
        }
        obj1 = ((ClassInfo) (obj1)).getFieldInfo(s);
        if (obj1 != null)
        {
            Type type = Data.resolveWildcardTypeOrTypeVariable(((List) (obj)), ((FieldInfo) (obj1)).field.getGenericType());
            boolean flag;
            if ((type instanceof GenericArrayType) || (type instanceof Class) && ((Class)type).isArray())
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                if (type instanceof GenericArrayType)
                {
                    s = ((GenericArrayType)type).getGenericComponentType();
                } else
                {
                    s = ((Class)type).getComponentType();
                }
                s = Types.getRawArrayComponentType(((List) (obj)), s);
                arrayvaluemap.put(((FieldInfo) (obj1)).field, s, Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(((List) (obj)), s), s1));
                return;
            }
            s = Types.getRawArrayComponentType(((List) (obj)), type);
            if (s.isAssignableFrom(java/lang/Iterable) || java/lang/Iterable.isAssignableFrom(s))
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (flag)
            {
                parseheaderstate = (Collection)FieldInfo.getFieldValue(((FieldInfo) (obj1)).field, this);
                s = parseheaderstate;
                if (parseheaderstate == null)
                {
                    s = Data.newCollectionInstance(type);
                    FieldInfo.setFieldValue(((FieldInfo) (obj1)).field, this, s);
                }
                if (type == java/lang/Object)
                {
                    parseheaderstate = null;
                } else
                {
                    parseheaderstate = Types.getActualParameterAtPosition(type, java/lang/Iterable, 0);
                }
                s.add(Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(((List) (obj)), parseheaderstate), s1));
                return;
            } else
            {
                s = ((String) (Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(((List) (obj)), type), s1)));
                FieldInfo.setFieldValue(((FieldInfo) (obj1)).field, this, s);
                return;
            }
        }
        obj = (ArrayList)get(s);
        parseheaderstate = ((ParseHeaderState) (obj));
        if (obj == null)
        {
            parseheaderstate = new ArrayList();
            s = (HttpHeaders)set(s, parseheaderstate);
        }
        parseheaderstate.add(s1);
    }

    public final GenericData set(String s, Object obj)
    {
        return (HttpHeaders)super.set(s, obj);
    }

    public final HttpHeaders setAuthorization(String s)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(s);
            s = arraylist;
        }
        authorization = s;
        return this;
    }

    public final HttpHeaders setIfMatch(String s)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(s);
            s = arraylist;
        }
        ifMatch = s;
        return this;
    }

    public final HttpHeaders setUserAgent(String s)
    {
        if (s == null)
        {
            s = null;
        } else
        {
            ArrayList arraylist = new ArrayList();
            arraylist.add(s);
            s = arraylist;
        }
        userAgent = s;
        return this;
    }

    private class ParseHeaderState
    {

        public final ArrayValueMap arrayValueMap;
        public final ClassInfo classInfo;
        public final List context;
        public final StringBuilder logger;

        public ParseHeaderState(StringBuilder stringbuilder)
        {
            Class class1 = getClass();
            context = Arrays.asList(new Type[] {
                class1
            });
            classInfo = ClassInfo.of(class1, true);
            logger = stringbuilder;
            arrayValueMap = new ArrayValueMap(HttpHeaders.this);
        }
    }

}
