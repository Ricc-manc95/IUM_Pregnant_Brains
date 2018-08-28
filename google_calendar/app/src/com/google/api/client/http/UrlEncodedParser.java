// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.ArrayValueMap;
import com.google.api.client.util.Charsets;
import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.ObjectParser;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import com.google.common.base.Throwables;
import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;

// Referenced classes of package com.google.api.client.http:
//            HttpMediaType

public final class UrlEncodedParser
    implements ObjectParser
{

    public static final String MEDIA_TYPE;

    public static void parse(String s, Object obj)
    {
        if (s == null)
        {
            return;
        }
        Object obj1;
        StringReader stringreader;
        ClassInfo classinfo;
        java.util.List list;
        stringreader = new StringReader(s);
        obj1 = obj.getClass();
        classinfo = ClassInfo.of(((Class) (obj1)));
        list = Arrays.asList(new Type[] {
            obj1
        });
        if (!com/google/api/client/util/GenericData.isAssignableFrom(((Class) (obj1)))) goto _L2; else goto _L1
_L1:
        s = (GenericData)obj;
_L11:
        if (!java/util/Map.isAssignableFrom(((Class) (obj1)))) goto _L4; else goto _L3
_L3:
        obj1 = (Map)obj;
_L12:
        Object obj2;
        Object obj3;
        ArrayValueMap arrayvaluemap;
        arrayvaluemap = new ArrayValueMap(obj);
        obj3 = new StringWriter();
        obj2 = new StringWriter();
        boolean flag = true;
_L10:
        int i;
        try
        {
            i = stringreader.read();
        }
        // Misplaced declaration of an exception variable
        catch (String s)
        {
            throw Throwables.propagate(s);
        }
        i;
        JVM INSTR lookupswitch 3: default 576
    //                   -1: 171
    //                   38: 171
    //                   61: 616;
           goto _L5 _L6 _L6 _L7
_L5:
        if (!flag) goto _L9; else goto _L8
_L8:
        ((StringWriter) (obj3)).write(i);
          goto _L10
_L2:
        s = null;
          goto _L11
_L4:
        obj1 = null;
          goto _L12
_L6:
        Object obj4 = CharEscapers.decodeUri(((StringWriter) (obj3)).toString());
        if (((String) (obj4)).length() == 0) goto _L14; else goto _L13
_L13:
        String s1;
        FieldInfo fieldinfo;
        s1 = CharEscapers.decodeUri(((StringWriter) (obj2)).toString());
        fieldinfo = classinfo.getFieldInfo(((String) (obj4)));
        if (fieldinfo == null) goto _L16; else goto _L15
_L15:
        obj4 = Data.resolveWildcardTypeOrTypeVariable(list, fieldinfo.field.getGenericType());
        if ((obj4 instanceof GenericArrayType) || (obj4 instanceof Class) && ((Class)obj4).isArray())
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L18; else goto _L17
_L17:
        if (!(obj4 instanceof GenericArrayType)) goto _L20; else goto _L19
_L19:
        obj2 = ((GenericArrayType)obj4).getGenericComponentType();
_L22:
        obj2 = Types.getRawArrayComponentType(list, ((Type) (obj2)));
        arrayvaluemap.put(fieldinfo.field, ((Class) (obj2)), Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(list, ((Type) (obj2))), s1));
_L14:
        flag = true;
        obj3 = new StringWriter();
        obj2 = new StringWriter();
        if (i != -1) goto _L10; else goto _L21
_L21:
        arrayvaluemap.setValues();
        return;
_L20:
        obj2 = ((Class)obj4).getComponentType();
          goto _L22
_L18:
        obj2 = Types.getRawArrayComponentType(list, ((Type) (obj4)));
        if (((Class) (obj2)).isAssignableFrom(java/lang/Iterable) || java/lang/Iterable.isAssignableFrom(((Class) (obj2))))
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag) goto _L24; else goto _L23
_L23:
        obj3 = (Collection)FieldInfo.getFieldValue(fieldinfo.field, obj);
        obj2 = obj3;
        if (obj3 != null) goto _L26; else goto _L25
_L25:
        obj2 = Data.newCollectionInstance(((Type) (obj4)));
        FieldInfo.setFieldValue(fieldinfo.field, obj, obj2);
          goto _L26
_L27:
        ((Collection) (obj2)).add(Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(list, ((Type) (obj3))), s1));
          goto _L14
_L34:
        obj3 = Types.getActualParameterAtPosition(((Type) (obj4)), java/lang/Iterable, 0);
          goto _L27
_L24:
        obj2 = Data.parsePrimitiveValue(Data.resolveWildcardTypeOrTypeVariable(list, ((Type) (obj4))), s1);
        FieldInfo.setFieldValue(fieldinfo.field, obj, obj2);
          goto _L14
_L16:
        if (obj1 == null) goto _L14; else goto _L28
_L28:
        obj3 = (ArrayList)((Map) (obj1)).get(obj4);
        obj2 = obj3;
        if (obj3 != null) goto _L30; else goto _L29
_L29:
        obj2 = new ArrayList();
        if (s == null) goto _L32; else goto _L31
_L31:
        s.set(((String) (obj4)), obj2);
_L30:
        ((ArrayList) (obj2)).add(s1);
          goto _L14
_L32:
        ((Map) (obj1)).put(obj4, obj2);
          goto _L30
_L36:
        ((StringWriter) (obj2)).write(i);
          goto _L10
_L9:
        ((StringWriter) (obj2)).write(i);
          goto _L10
_L26:
        if (obj4 != java/lang/Object) goto _L34; else goto _L33
_L33:
        obj3 = null;
          goto _L27
_L7:
        if (!flag) goto _L36; else goto _L35
_L35:
        flag = false;
          goto _L10
    }

    public final Object parseAndClose(InputStream inputstream, Charset charset, Class class1)
        throws IOException
    {
        throw new NoSuchMethodError();
    }

    static 
    {
        HttpMediaType httpmediatype = new HttpMediaType("application/x-www-form-urlencoded");
        Object obj = Charsets.UTF_8;
        if (obj == null)
        {
            obj = null;
        } else
        {
            obj = ((Charset) (obj)).name();
        }
        httpmediatype.setParameter("charset", ((String) (obj)));
        MEDIA_TYPE = httpmediatype.build();
    }
}
