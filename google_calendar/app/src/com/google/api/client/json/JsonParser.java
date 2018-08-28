// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.json;

import com.google.api.client.util.ClassInfo;
import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.GenericData;
import com.google.api.client.util.Types;
import com.google.common.base.Strings;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Referenced classes of package com.google.api.client.json:
//            JsonPolymorphicTypeMap, JsonToken, GenericJson, JsonFactory, 
//            JsonString, CustomizeJsonParser

public abstract class JsonParser
{

    private static WeakHashMap cachedTypemapFields = new WeakHashMap();
    private static final Lock lock = new ReentrantLock();

    public JsonParser()
    {
    }

    private static Field getCachedTypemapFieldFor(Class class1)
    {
        if (class1 == null)
        {
            return null;
        }
        lock.lock();
        if (!cachedTypemapFields.containsKey(class1))
        {
            break MISSING_BLOCK_LABEL_45;
        }
        class1 = (Field)cachedTypemapFields.get(class1);
        lock.unlock();
        return class1;
        Iterator iterator = Collections.unmodifiableCollection(ClassInfo.of(class1).nameToFieldInfoMap.values()).iterator();
        Object obj = null;
_L4:
        if (!iterator.hasNext()) goto _L2; else goto _L1
_L1:
        Field field;
        Object obj1;
        field = ((FieldInfo)iterator.next()).field;
        obj1 = (JsonPolymorphicTypeMap)field.getAnnotation(com/google/api/client/json/JsonPolymorphicTypeMap);
        if (obj1 == null) goto _L4; else goto _L3
_L3:
        boolean flag;
        if (obj == null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (flag)
        {
            break MISSING_BLOCK_LABEL_154;
        }
        throw new IllegalArgumentException(Strings.lenientFormat("Class contains more than one field with @JsonPolymorphicTypeMap annotation: %s", new Object[] {
            class1
        }));
        class1;
        lock.unlock();
        throw class1;
        boolean flag1;
        flag1 = Data.isPrimitive(field.getType());
        obj = field.getType();
        if (flag1)
        {
            break MISSING_BLOCK_LABEL_198;
        }
        throw new IllegalArgumentException(Strings.lenientFormat("Field which has the @JsonPolymorphicTypeMap, %s, is not a supported type: %s", new Object[] {
            class1, obj
        }));
        obj = ((JsonPolymorphicTypeMap) (obj1)).typeDefinitions();
        obj1 = new HashSet();
        int i;
        Object obj2;
        int j;
        boolean flag2;
        if (obj.length > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0)
        {
            break MISSING_BLOCK_LABEL_241;
        }
        throw new IllegalArgumentException(String.valueOf("@JsonPolymorphicTypeMap must have at least one @TypeDef"));
        j = obj.length;
        i = 0;
_L7:
        if (i >= j) goto _L6; else goto _L5
_L5:
        obj2 = obj[i];
        flag2 = ((HashSet) (obj1)).add(((JsonPolymorphicTypeMap.TypeDef) (obj2)).key());
        obj2 = ((JsonPolymorphicTypeMap.TypeDef) (obj2)).key();
        if (flag2)
        {
            break MISSING_BLOCK_LABEL_341;
        }
        throw new IllegalArgumentException(Strings.lenientFormat("Class contains two @TypeDef annotations with identical key: %s", new Object[] {
            obj2
        }));
_L2:
        cachedTypemapFields.put(class1, obj);
        lock.unlock();
        return ((Field) (obj));
_L6:
        obj = field;
          goto _L4
        i++;
          goto _L7
    }

    private final void parseMap(Field field, Map map, Type type, ArrayList arraylist, CustomizeJsonParser customizejsonparser)
        throws IOException
    {
        for (Object obj = startParsingObjectOrArray(); obj == JsonToken.FIELD_NAME; obj = nextToken())
        {
            obj = getText();
            nextToken();
            if (customizejsonparser != null)
            {
                throw new NoSuchMethodError();
            }
            map.put(obj, parseValue(field, type, arraylist, map, customizejsonparser, true));
        }

    }

    private final Object parseValue(Field field, Type type, ArrayList arraylist, Object obj, CustomizeJsonParser customizejsonparser, boolean flag)
        throws IOException
    {
        Object obj1;
        int i;
        int j;
        Type type1 = Data.resolveWildcardTypeOrTypeVariable(arraylist, type);
        if (type1 instanceof Class)
        {
            type = (Class)type1;
        } else
        {
            type = null;
        }
        Object obj2;
        JsonPolymorphicTypeMap.TypeDef atypedef[];
        ClassInfo classinfo;
        Class class1;
        if (type1 instanceof ParameterizedType)
        {
            obj1 = (Class)((ParameterizedType)type1).getRawType();
        } else
        {
            obj1 = type;
        }
        if (obj1 != java/lang/Void) goto _L2; else goto _L1
_L1:
        skipChildren();
        customizejsonparser = null;
_L54:
        return customizejsonparser;
_L2:
        type = getCurrentToken();
        getCurrentToken().ordinal();
        JVM INSTR tableswitch 0 10: default 1853
    //                   0 250
    //                   1 250
    //                   2 483
    //                   3 483
    //                   4 483
    //                   5 1565
    //                   6 1307
    //                   7 1307
    //                   8 1232
    //                   9 1232
    //                   10 1703;
           goto _L3 _L4 _L4 _L5 _L5 _L5 _L6 _L7 _L7 _L8 _L8 _L9
_L3:
        type = String.valueOf(type);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(type).length() + 27)).append("unexpected JSON node type: ").append(type).toString());
_L4:
        if ((type1 instanceof GenericArrayType) || (type1 instanceof Class) && ((Class)type1).isArray())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (type1 == null || i != 0) goto _L11; else goto _L10
_L10:
        if (obj1 == null) goto _L13; else goto _L12
_L12:
        if (((Class) (obj1)).isAssignableFrom(java/util/Collection) || java/util/Collection.isAssignableFrom(((Class) (obj1))))
        {
            j = 1;
        } else
        {
            j = 0;
        }
          goto _L14
_L131:
        if (j == 0)
        {
            try
            {
                throw new IllegalArgumentException(Strings.lenientFormat("expected collection or array type but got %s", new Object[] {
                    type1
                }));
            }
            // Misplaced declaration of an exception variable
            catch (Type type)
            {
                arraylist = new StringBuilder();
                obj = getCurrentName();
                if (obj != null)
                {
                    arraylist.append("key ").append(((String) (obj)));
                }
                if (field != null)
                {
                    if (obj != null)
                    {
                        arraylist.append(", ");
                    }
                    arraylist.append("field ").append(field);
                }
                throw new IllegalArgumentException(arraylist.toString(), type);
            }
        }
        obj = null;
        if (customizejsonparser == null || field == null) goto _L16; else goto _L15
_L15:
        throw new NoSuchMethodError();
_L16:
        if (false) goto _L18; else goto _L17
_L17:
        obj = Data.newCollectionInstance(type1);
_L18:
        obj2 = null;
        if (i == 0) goto _L20; else goto _L19
_L19:
        if (!(type1 instanceof GenericArrayType)) goto _L22; else goto _L21
_L21:
        type = ((GenericArrayType)type1).getGenericComponentType();
_L25:
        type = Data.resolveWildcardTypeOrTypeVariable(arraylist, type);
        parseArray(field, ((Collection) (obj)), type, arraylist, customizejsonparser);
        if (i == 0) goto _L24; else goto _L23
_L23:
        return Types.toArray(((Collection) (obj)), Types.getRawArrayComponentType(arraylist, type));
_L22:
        type = ((Class)type1).getComponentType();
          goto _L25
_L20:
        type = ((Type) (obj2));
        if (obj1 == null) goto _L25; else goto _L26
_L26:
        type = ((Type) (obj2));
        if (!java/lang/Iterable.isAssignableFrom(((Class) (obj1)))) goto _L25; else goto _L27
_L27:
        type = Types.getActualParameterAtPosition(type1, java/lang/Iterable, 0);
          goto _L25
_L132:
        if (i != 0) goto _L29; else goto _L28
_L28:
        throw new IllegalArgumentException(Strings.lenientFormat("expected object or map type but got %s", new Object[] {
            type1
        }));
_L29:
        if (!flag) goto _L31; else goto _L30
_L30:
        obj = getCachedTypemapFieldFor(((Class) (obj1)));
_L133:
        type = null;
        if (obj1 == null || customizejsonparser == null) goto _L33; else goto _L32
_L32:
        throw new NoSuchMethodError();
_L33:
        if (obj1 == null) goto _L35; else goto _L34
_L34:
        if (((Class) (obj1)).isAssignableFrom(java/util/Map) || java/util/Map.isAssignableFrom(((Class) (obj1))))
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L36
_L135:
        if (obj == null) goto _L38; else goto _L37
_L37:
        type = new GenericJson();
_L47:
        j = arraylist.size();
        if (type1 == null) goto _L40; else goto _L39
_L39:
        arraylist.add(type1);
_L40:
        if (i == 0) goto _L42; else goto _L41
_L41:
        if (com/google/api/client/util/GenericData.isAssignableFrom(((Class) (obj1)))) goto _L42; else goto _L43
_L43:
        if (!java/util/Map.isAssignableFrom(((Class) (obj1)))) goto _L45; else goto _L44
_L44:
        obj1 = Types.getActualParameterAtPosition(type1, java/util/Map, 1);
_L139:
        if (obj1 == null) goto _L42; else goto _L46
_L46:
        parseMap(field, (Map)type, ((Type) (obj1)), arraylist, customizejsonparser);
        return type;
_L137:
        type = Data.newMapInstance(((Class) (obj1)));
          goto _L47
_L138:
        type = ((Type) (Types.newInstance(((Class) (obj1)))));
          goto _L47
_L42:
        if (type instanceof GenericJson)
        {
            ((GenericJson)type).jsonFactory = getFactory();
        }
        obj2 = startParsingObjectOrArray();
        class1 = type.getClass();
        classinfo = ClassInfo.of(class1);
        flag = com/google/api/client/util/GenericData.isAssignableFrom(class1);
        obj1 = obj2;
        if (flag) goto _L49; else goto _L48
_L48:
        obj1 = obj2;
        if (!java/util/Map.isAssignableFrom(class1)) goto _L49; else goto _L50
_L50:
        parseMap(null, (Map)type, Types.getActualParameterAtPosition(class1, java/util/Map, 1), arraylist, customizejsonparser);
_L58:
        if (type1 == null) goto _L52; else goto _L51
_L51:
        arraylist.remove(j);
_L52:
        customizejsonparser = type;
        if (obj == null) goto _L54; else goto _L53
_L53:
        customizejsonparser = ((CustomizeJsonParser) (((GenericJson)type).get(((Field) (obj)).getName())));
        if (customizejsonparser != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L56; else goto _L55
_L55:
        throw new IllegalArgumentException(String.valueOf("No value specified for @JsonPolymorphicTypeMap field"));
_L64:
        obj2 = ((FieldInfo) (obj1)).field;
        i = arraylist.size();
        arraylist.add(((Field) (obj2)).getGenericType());
        obj2 = parseValue(((Field) (obj2)), ((FieldInfo) (obj1)).field.getGenericType(), arraylist, type, customizejsonparser, true);
        arraylist.remove(i);
        FieldInfo.setFieldValue(((FieldInfo) (obj1)).field, type, obj2);
_L67:
        obj1 = nextToken();
_L49:
        if (obj1 != JsonToken.FIELD_NAME) goto _L58; else goto _L57
_L57:
        obj2 = getText();
        nextToken();
        if (customizejsonparser == null) goto _L60; else goto _L59
_L59:
        throw new NoSuchMethodError();
_L60:
        obj1 = classinfo.getFieldInfo(((String) (obj2)));
        if (obj1 == null) goto _L62; else goto _L61
_L61:
        if (!Modifier.isFinal(((FieldInfo) (obj1)).field.getModifiers()) || ((FieldInfo) (obj1)).isPrimitive) goto _L64; else goto _L63
_L63:
        throw new IllegalArgumentException("final array/object fields are not supported");
_L62:
        if (!flag) goto _L66; else goto _L65
_L65:
        ((GenericData)type).set(((String) (obj2)), parseValue(null, null, arraylist, type, customizejsonparser, true));
          goto _L67
_L66:
        if (customizejsonparser == null) goto _L69; else goto _L68
_L68:
        throw new NoSuchMethodError();
_L69:
        skipChildren();
          goto _L67
_L56:
        obj1 = customizejsonparser.toString();
        obj = (JsonPolymorphicTypeMap)((Field) (obj)).getAnnotation(com/google/api/client/json/JsonPolymorphicTypeMap);
        customizejsonparser = null;
        atypedef = ((JsonPolymorphicTypeMap) (obj)).typeDefinitions();
        j = atypedef.length;
        i = 0;
_L141:
        obj = customizejsonparser;
        if (i >= j) goto _L71; else goto _L70
_L70:
        obj = atypedef[i];
        if (!((JsonPolymorphicTypeMap.TypeDef) (obj)).key().equals(obj1)) goto _L73; else goto _L72
_L72:
        obj = ((JsonPolymorphicTypeMap.TypeDef) (obj)).ref();
          goto _L71
_L140:
        customizejsonparser = String.valueOf(obj1);
        if (customizejsonparser.length() == 0) goto _L75; else goto _L74
_L74:
        customizejsonparser = "No TypeDef annotation found with key: ".concat(customizejsonparser);
_L78:
        if (i != 0) goto _L77; else goto _L76
_L76:
        throw new IllegalArgumentException(String.valueOf(customizejsonparser));
_L75:
        customizejsonparser = new String("No TypeDef annotation found with key: ");
          goto _L78
_L77:
        customizejsonparser = getFactory();
        type = customizejsonparser.createJsonParser(customizejsonparser.toString(type));
        type.startParsing();
        return type.parseValue(field, ((Type) (obj)), arraylist, null, null, false);
_L8:
        if (type1 == null) goto _L80; else goto _L79
_L79:
        if (obj1 == Boolean.TYPE) goto _L80; else goto _L81
_L81:
        if (obj1 == null) goto _L83; else goto _L82
_L82:
        if (!((Class) (obj1)).isAssignableFrom(java/lang/Boolean)) goto _L83; else goto _L80
_L142:
        if (i != 0) goto _L85; else goto _L84
_L84:
        throw new IllegalArgumentException(Strings.lenientFormat("expected type Boolean or boolean but got %s", new Object[] {
            type1
        }));
_L85:
        if (type == JsonToken.VALUE_TRUE)
        {
            return Boolean.TRUE;
        } else
        {
            return Boolean.FALSE;
        }
_L7:
        if (field == null) goto _L87; else goto _L86
_L86:
        if (field.getAnnotation(com/google/api/client/json/JsonString) != null) goto _L88; else goto _L87
_L143:
        if (i != 0) goto _L90; else goto _L89
_L89:
        throw new IllegalArgumentException(String.valueOf("number type formatted as a JSON number cannot use @JsonString annotation"));
_L90:
        if (obj1 == null) goto _L92; else goto _L91
_L91:
        if (!((Class) (obj1)).isAssignableFrom(java/math/BigDecimal)) goto _L93; else goto _L92
_L92:
        return getDecimalValue();
_L93:
        if (obj1 != java/math/BigInteger) goto _L95; else goto _L94
_L94:
        return getBigIntegerValue();
_L95:
        if (obj1 == java/lang/Double) goto _L97; else goto _L96
_L96:
        if (obj1 != Double.TYPE) goto _L98; else goto _L97
_L97:
        return Double.valueOf(getDoubleValue());
_L98:
        if (obj1 == java/lang/Long) goto _L100; else goto _L99
_L99:
        if (obj1 != Long.TYPE) goto _L101; else goto _L100
_L100:
        return Long.valueOf(getLongValue());
_L101:
        if (obj1 == java/lang/Float) goto _L103; else goto _L102
_L102:
        if (obj1 != Float.TYPE) goto _L104; else goto _L103
_L103:
        return Float.valueOf(getFloatValue());
_L104:
        if (obj1 == java/lang/Integer) goto _L106; else goto _L105
_L105:
        if (obj1 != Integer.TYPE) goto _L107; else goto _L106
_L106:
        return Integer.valueOf(getIntValue());
_L107:
        if (obj1 == java/lang/Short) goto _L109; else goto _L108
_L108:
        if (obj1 != Short.TYPE) goto _L110; else goto _L109
_L109:
        return Short.valueOf(getShortValue());
_L110:
        if (obj1 == java/lang/Byte) goto _L112; else goto _L111
_L111:
        if (obj1 != Byte.TYPE) goto _L113; else goto _L112
_L112:
        return Byte.valueOf(getByteValue());
_L113:
        type = String.valueOf(type1);
        throw new IllegalArgumentException((new StringBuilder(String.valueOf(type).length() + 30)).append("expected numeric type but got ").append(type).toString());
_L6:
        type = getText().trim().toLowerCase(Locale.US);
        if (obj1 == Float.TYPE || obj1 == java/lang/Float) goto _L115; else goto _L114
_L114:
        if (obj1 != Double.TYPE && obj1 != java/lang/Double) goto _L116; else goto _L115
_L115:
        if (type.equals("nan") || type.equals("infinity") || type.equals("-infinity")) goto _L117; else goto _L116
_L116:
        if (obj1 == null) goto _L119; else goto _L118
_L118:
        if (!java/lang/Number.isAssignableFrom(((Class) (obj1)))) goto _L119; else goto _L120
_L120:
        if (field == null) goto _L122; else goto _L121
_L121:
        if (field.getAnnotation(com/google/api/client/json/JsonString) == null) goto _L122; else goto _L119
_L144:
        if (i != 0) goto _L117; else goto _L123
_L123:
        throw new IllegalArgumentException(String.valueOf("number field formatted as a JSON string must use the @JsonString annotation"));
_L117:
        return Data.parsePrimitiveValue(type1, getText());
_L9:
        if (obj1 == null) goto _L125; else goto _L124
_L124:
        if (((Class) (obj1)).isPrimitive()) goto _L126; else goto _L125
_L145:
        if (i != 0) goto _L128; else goto _L127
_L127:
        throw new IllegalArgumentException(String.valueOf("primitive number field but found a JSON null"));
_L128:
        if (obj1 == null)
        {
            break MISSING_BLOCK_LABEL_1835;
        }
        if ((((Class) (obj1)).getModifiers() & 0x600) == 0)
        {
            break MISSING_BLOCK_LABEL_1835;
        }
        if (((Class) (obj1)).isAssignableFrom(java/util/Collection) || java/util/Collection.isAssignableFrom(((Class) (obj1))))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0) goto _L130; else goto _L129
_L129:
        return Data.nullOf(Data.newCollectionInstance(type1).getClass());
_L130:
        if (((Class) (obj1)).isAssignableFrom(java/util/Map) || java/util/Map.isAssignableFrom(((Class) (obj1))))
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_1835;
        }
        return Data.nullOf(Data.newMapInstance(((Class) (obj1))).getClass());
        type = ((Type) (Data.nullOf(Types.getRawArrayComponentType(arraylist, type1))));
        return type;
_L14:
        if (j == 0) goto _L13; else goto _L11
_L11:
        j = 1;
          goto _L131
_L13:
        j = 0;
          goto _L131
_L24:
        return obj;
_L5:
        if ((type1 instanceof GenericArrayType) || (type1 instanceof Class) && ((Class)type1).isArray())
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i == 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L132
_L31:
        obj = null;
          goto _L133
_L36:
        if (i == 0) goto _L35; else goto _L134
_L134:
        i = 1;
          goto _L135
_L35:
        i = 0;
          goto _L135
_L38:
        if (false) goto _L47; else goto _L136
_L136:
        if (i == 0 && obj1 != null) goto _L138; else goto _L137
_L45:
        obj1 = null;
          goto _L139
_L71:
        if (obj != null)
        {
            i = 1;
        } else
        {
            i = 0;
        }
          goto _L140
_L73:
        i++;
          goto _L141
_L80:
        i = 1;
          goto _L142
_L83:
        i = 0;
          goto _L142
_L87:
        i = 1;
          goto _L143
_L88:
        i = 0;
          goto _L143
_L119:
        i = 1;
          goto _L144
_L122:
        i = 0;
          goto _L144
_L125:
        i = 1;
          goto _L145
_L126:
        i = 0;
          goto _L145
    }

    private final JsonToken startParsing()
        throws IOException
    {
        JsonToken jsontoken = getCurrentToken();
        if (jsontoken == null)
        {
            jsontoken = nextToken();
        }
        boolean flag;
        if (jsontoken != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalArgumentException(String.valueOf("no JSON input found"));
        } else
        {
            return jsontoken;
        }
    }

    private final JsonToken startParsingObjectOrArray()
        throws IOException
    {
        JsonToken jsontoken;
        JsonToken jsontoken1;
        jsontoken1 = startParsing();
        jsontoken = jsontoken1;
        jsontoken1.ordinal();
        JVM INSTR tableswitch 0 2: default 36
    //                   0 84
    //                   1 38
    //                   2 40;
           goto _L1 _L2 _L3 _L4
_L1:
        jsontoken = jsontoken1;
_L3:
        return jsontoken;
_L4:
        JsonToken jsontoken2 = nextToken();
        boolean flag;
        if (jsontoken2 == JsonToken.FIELD_NAME || jsontoken2 == JsonToken.END_OBJECT)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        jsontoken = jsontoken2;
        if (flag) goto _L3; else goto _L5
_L5:
        throw new IllegalArgumentException(String.valueOf(jsontoken2));
_L2:
        return nextToken();
    }

    public abstract void close()
        throws IOException;

    public abstract BigInteger getBigIntegerValue()
        throws IOException;

    public abstract byte getByteValue()
        throws IOException;

    public abstract String getCurrentName()
        throws IOException;

    public abstract JsonToken getCurrentToken();

    public abstract BigDecimal getDecimalValue()
        throws IOException;

    public abstract double getDoubleValue()
        throws IOException;

    public abstract JsonFactory getFactory();

    public abstract float getFloatValue()
        throws IOException;

    public abstract int getIntValue()
        throws IOException;

    public abstract long getLongValue()
        throws IOException;

    public abstract short getShortValue()
        throws IOException;

    public abstract String getText()
        throws IOException;

    public abstract JsonToken nextToken()
        throws IOException;

    public final Object parse(Type type, boolean flag, CustomizeJsonParser customizejsonparser)
        throws IOException
    {
        if (!java/lang/Void.equals(type))
        {
            startParsing();
        }
        type = ((Type) (parseValue(null, type, new ArrayList(), null, customizejsonparser, true)));
        if (flag)
        {
            close();
        }
        return type;
        type;
        if (flag)
        {
            close();
        }
        throw type;
    }

    public final Object parseAndClose(Class class1, CustomizeJsonParser customizejsonparser)
        throws IOException
    {
        class1 = ((Class) (parse(class1, false, null)));
        close();
        return class1;
        class1;
        close();
        throw class1;
    }

    public final void parseArray(Field field, Collection collection, Type type, ArrayList arraylist, CustomizeJsonParser customizejsonparser)
        throws IOException
    {
        for (JsonToken jsontoken = startParsingObjectOrArray(); jsontoken != JsonToken.END_ARRAY; jsontoken = nextToken())
        {
            collection.add(parseValue(field, type, arraylist, collection, customizejsonparser, true));
        }

    }

    public abstract JsonParser skipChildren()
        throws IOException;

    public final String skipToKey(Set set)
        throws IOException
    {
        for (Object obj = startParsingObjectOrArray(); obj == JsonToken.FIELD_NAME; obj = nextToken())
        {
            obj = getText();
            nextToken();
            if (set.contains(obj))
            {
                return ((String) (obj));
            }
            skipChildren();
        }

        return null;
    }

}
