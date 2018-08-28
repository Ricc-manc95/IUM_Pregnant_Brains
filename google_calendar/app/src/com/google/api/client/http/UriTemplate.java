// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.api.client.http;

import com.google.api.client.util.Data;
import com.google.api.client.util.FieldInfo;
import com.google.api.client.util.Types;
import com.google.api.client.util.escape.CharEscapers;
import com.google.api.client.util.escape.Escaper;
import com.google.common.base.CharMatcher;
import com.google.common.base.Splitter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.api.client.http:
//            GenericUrl

public final class UriTemplate
{

    public static final Map COMPOSITE_PREFIXES = new HashMap();

    public static String expand(String s, String s1, Object obj, boolean flag)
    {
        String s2;
        Map map1;
        StringBuilder stringbuilder;
        int i;
        if (s1.startsWith("/"))
        {
            s = new GenericUrl(s);
            s.pathParts = GenericUrl.toPathParts(null);
            s = String.valueOf(s.build());
            s1 = String.valueOf(s1);
            int j1;
            if (s1.length() != 0)
            {
                s = s.concat(s1);
            } else
            {
                s = new String(s);
            }
            s2 = s;
        } else
        {
            s2 = s1;
            if (!s1.startsWith("http://"))
            {
                s2 = s1;
                if (!s1.startsWith("https://"))
                {
                    s = String.valueOf(s);
                    s1 = String.valueOf(s1);
                    if (s1.length() != 0)
                    {
                        s2 = s.concat(s1);
                    } else
                    {
                        s2 = new String(s);
                    }
                }
            }
        }
        map1 = getMap(obj);
        stringbuilder = new StringBuilder();
        i = 0;
        j1 = s2.length();
        do
        {
            int j;
label0:
            {
                if (i < j1)
                {
                    j = s2.indexOf('{', i);
                    if (j != -1)
                    {
                        break label0;
                    }
                    if (i != 0);
                    stringbuilder.append(s2.substring(i));
                }
                GenericUrl.addQueryParams(map1.entrySet(), stringbuilder);
                return stringbuilder.toString();
            }
            stringbuilder.append(s2.substring(i, j));
            int k1 = s2.indexOf('}', j + 2);
            s = s2.substring(j + 1, k1);
            obj = (CompositeOutput)COMPOSITE_PREFIXES.get(Character.valueOf(s.charAt(0)));
            if (obj == null)
            {
                obj = CompositeOutput.SIMPLE;
            }
            s1 = CharMatcher.is(',');
            if (s1 == null)
            {
                throw new NullPointerException();
            }
            ListIterator listiterator = (new Splitter(new com.google.common.base.Splitter._cls1(s1))).splitToList(s).listIterator();
            i = 1;
            while (listiterator.hasNext()) 
            {
                s = (String)listiterator.next();
                flag = s.endsWith("*");
                Object obj1;
                int k;
                int l;
                int i1;
                if (listiterator.nextIndex() == 1)
                {
                    if (((CompositeOutput) (obj)).propertyPrefix == null)
                    {
                        k = 0;
                    } else
                    {
                        k = 1;
                    }
                } else
                {
                    k = 0;
                }
                i1 = s.length();
                l = i1;
                if (flag)
                {
                    l = i1 - 1;
                }
                obj1 = s.substring(k, l);
                s1 = ((String) (map1.remove(obj1)));
                if (s1 != null)
                {
                    if (i == 0)
                    {
                        stringbuilder.append(((CompositeOutput) (obj)).explodeJoiner);
                    } else
                    {
                        stringbuilder.append(((CompositeOutput) (obj)).outputPrefix);
                        i = 0;
                    }
                    if (s1 instanceof Iterator)
                    {
                        s = getListPropertyValue(((String) (obj1)), (Iterator)s1, flag, ((CompositeOutput) (obj)));
                    } else
                    if ((s1 instanceof Iterable) || s1.getClass().isArray())
                    {
                        s = getListPropertyValue(((String) (obj1)), Types.iterableOf(s1).iterator(), flag, ((CompositeOutput) (obj)));
                    } else
                    if (s1.getClass().isEnum())
                    {
                        s = s1;
                        if (FieldInfo.of((Enum)s1).name != null)
                        {
                            s = s1;
                            if (((CompositeOutput) (obj)).requiresVarAssignment)
                            {
                                s = String.format("%s=%s", new Object[] {
                                    obj1, s1
                                });
                            }
                            s = s.toString();
                            s = CharEscapers.URI_PATH_ESCAPER.escape(s);
                        }
                    } else
                    if (!Data.isValueOfPrimitiveType(s1))
                    {
                        Map map = getMap(s1);
                        if (map.isEmpty())
                        {
                            s = "";
                        } else
                        {
                            StringBuilder stringbuilder1 = new StringBuilder();
                            Iterator iterator;
                            if (flag)
                            {
                                s1 = ((CompositeOutput) (obj)).explodeJoiner;
                                s = "=";
                            } else
                            {
                                if (((CompositeOutput) (obj)).requiresVarAssignment)
                                {
                                    stringbuilder1.append(CharEscapers.URI_PATH_ESCAPER.escape(((String) (obj1))));
                                    stringbuilder1.append("=");
                                }
                                s = ",";
                                s1 = ",";
                            }
                            iterator = map.entrySet().iterator();
                            do
                            {
                                if (!iterator.hasNext())
                                {
                                    break;
                                }
                                obj1 = (java.util.Map.Entry)iterator.next();
                                String s3 = (String)((java.util.Map.Entry) (obj1)).getKey();
                                if (((CompositeOutput) (obj)).reservedExpansion)
                                {
                                    s3 = CharEscapers.URI_PATH_ESCAPER.escape(s3);
                                } else
                                {
                                    s3 = CharEscapers.URI_ESCAPER.escape(s3);
                                }
                                obj1 = ((java.util.Map.Entry) (obj1)).getValue().toString();
                                if (((CompositeOutput) (obj)).reservedExpansion)
                                {
                                    obj1 = CharEscapers.URI_PATH_ESCAPER.escape(((String) (obj1)));
                                } else
                                {
                                    obj1 = CharEscapers.URI_ESCAPER.escape(((String) (obj1)));
                                }
                                stringbuilder1.append(s3);
                                stringbuilder1.append(s);
                                stringbuilder1.append(((String) (obj1)));
                                if (iterator.hasNext())
                                {
                                    stringbuilder1.append(s1);
                                }
                            } while (true);
                            s = stringbuilder1.toString();
                        }
                    } else
                    {
                        s = s1;
                        if (((CompositeOutput) (obj)).requiresVarAssignment)
                        {
                            s = String.format("%s=%s", new Object[] {
                                obj1, s1
                            });
                        }
                        if (((CompositeOutput) (obj)).reservedExpansion)
                        {
                            s = s.toString();
                            s = CharEscapers.URI_RESERVED_ESCAPER.escape(s);
                        } else
                        {
                            s = s.toString();
                            s = CharEscapers.URI_PATH_ESCAPER.escape(s);
                        }
                    }
                    stringbuilder.append(s);
                }
            }
            i = k1 + 1;
        } while (true);
    }

    private static String getListPropertyValue(String s, Iterator iterator, boolean flag, CompositeOutput compositeoutput)
    {
        StringBuilder stringbuilder;
        if (!iterator.hasNext())
        {
            return "";
        }
        stringbuilder = new StringBuilder();
        if (!flag) goto _L2; else goto _L1
_L1:
        String s1 = compositeoutput.explodeJoiner;
_L4:
        if (!iterator.hasNext())
        {
            break; /* Loop/switch isn't completed */
        }
        if (flag && compositeoutput.requiresVarAssignment)
        {
            stringbuilder.append(CharEscapers.URI_PATH_ESCAPER.escape(s));
            stringbuilder.append("=");
        }
        String s2 = iterator.next().toString();
        if (compositeoutput.reservedExpansion)
        {
            s2 = CharEscapers.URI_PATH_ESCAPER.escape(s2);
        } else
        {
            s2 = CharEscapers.URI_ESCAPER.escape(s2);
        }
        stringbuilder.append(s2);
        if (iterator.hasNext())
        {
            stringbuilder.append(s1);
        }
        continue; /* Loop/switch isn't completed */
_L2:
        s2 = ",";
        s1 = s2;
        if (compositeoutput.requiresVarAssignment)
        {
            stringbuilder.append(CharEscapers.URI_PATH_ESCAPER.escape(s));
            stringbuilder.append("=");
            s1 = s2;
        }
        if (true) goto _L4; else goto _L3
_L3:
        return stringbuilder.toString();
    }

    private static Map getMap(Object obj)
    {
        LinkedHashMap linkedhashmap = new LinkedHashMap();
        obj = Data.mapOf(obj).entrySet().iterator();
        do
        {
            if (!((Iterator) (obj)).hasNext())
            {
                break;
            }
            java.util.Map.Entry entry = (java.util.Map.Entry)((Iterator) (obj)).next();
            Object obj1 = entry.getValue();
            if (obj1 != null && !Data.isNull(obj1))
            {
                linkedhashmap.put((String)entry.getKey(), obj1);
            }
        } while (true);
        return linkedhashmap;
    }

    static 
    {
        CompositeOutput.values();
    }

    private class CompositeOutput extends Enum
    {

        private static final CompositeOutput $VALUES[];
        private static final CompositeOutput AMP;
        private static final CompositeOutput DOT;
        private static final CompositeOutput FORWARD_SLASH;
        private static final CompositeOutput HASH;
        private static final CompositeOutput PLUS;
        private static final CompositeOutput QUERY;
        private static final CompositeOutput SEMI_COLON;
        public static final CompositeOutput SIMPLE;
        public final String explodeJoiner;
        public final String outputPrefix;
        public final Character propertyPrefix;
        public final boolean requiresVarAssignment;
        public final boolean reservedExpansion;

        public static CompositeOutput[] values()
        {
            return (CompositeOutput[])$VALUES.clone();
        }

        static 
        {
            PLUS = new CompositeOutput("PLUS", 0, Character.valueOf('+'), "", ",", false, true);
            HASH = new CompositeOutput("HASH", 1, Character.valueOf('#'), "#", ",", false, true);
            DOT = new CompositeOutput("DOT", 2, Character.valueOf('.'), ".", ".", false, false);
            FORWARD_SLASH = new CompositeOutput("FORWARD_SLASH", 3, Character.valueOf('/'), "/", "/", false, false);
            SEMI_COLON = new CompositeOutput("SEMI_COLON", 4, Character.valueOf(';'), ";", ";", true, false);
            QUERY = new CompositeOutput("QUERY", 5, Character.valueOf('?'), "?", "&", true, false);
            AMP = new CompositeOutput("AMP", 6, Character.valueOf('&'), "&", "&", true, false);
            SIMPLE = new CompositeOutput("SIMPLE", 7, null, "", ",", false, false);
            $VALUES = (new CompositeOutput[] {
                PLUS, HASH, DOT, FORWARD_SLASH, SEMI_COLON, QUERY, AMP, SIMPLE
            });
        }

        private CompositeOutput(String s, int i, Character character, String s1, String s2, boolean flag, boolean flag1)
        {
            super(s, i);
            propertyPrefix = character;
            if (s1 == null)
            {
                throw new NullPointerException();
            }
            outputPrefix = (String)s1;
            if (s2 == null)
            {
                throw new NullPointerException();
            }
            explodeJoiner = (String)s2;
            requiresVarAssignment = flag;
            reservedExpansion = flag1;
            if (character != null)
            {
                UriTemplate.COMPOSITE_PREFIXES.put(character, this);
            }
        }
    }

}
