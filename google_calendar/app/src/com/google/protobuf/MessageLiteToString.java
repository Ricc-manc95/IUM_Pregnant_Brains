// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.protobuf;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

// Referenced classes of package com.google.protobuf:
//            ByteString, GeneratedMessageLite, MessageLite, FieldSet, 
//            UnknownFieldSetLite

final class MessageLiteToString
{

    private static final String camelCaseToSnakeCase(String s)
    {
        StringBuilder stringbuilder = new StringBuilder();
        for (int i = 0; i < s.length(); i++)
        {
            char c = s.charAt(i);
            if (Character.isUpperCase(c))
            {
                stringbuilder.append("_");
            }
            stringbuilder.append(Character.toLowerCase(c));
        }

        return stringbuilder.toString();
    }

    static final void printField(StringBuilder stringbuilder, int i, String s, Object obj)
    {
        boolean flag1;
        boolean flag2;
        boolean flag3;
label0:
        {
label1:
            {
label2:
                {
label3:
                    {
label4:
                        {
label5:
                            {
label6:
                                {
label7:
                                    {
label8:
                                        {
label9:
                                            {
label10:
                                                {
label11:
                                                    {
                                                        flag1 = false;
                                                        flag2 = false;
                                                        flag3 = false;
                                                        boolean flag = false;
                                                        if (obj instanceof List)
                                                        {
                                                            for (obj = ((List)obj).iterator(); ((Iterator) (obj)).hasNext(); printField(stringbuilder, i, s, ((Iterator) (obj)).next())) { }
                                                            break label1;
                                                        }
                                                        if (obj instanceof Map)
                                                        {
                                                            for (obj = ((Map)obj).entrySet().iterator(); ((Iterator) (obj)).hasNext(); printField(stringbuilder, i, s, (java.util.Map.Entry)((Iterator) (obj)).next())) { }
                                                            break label1;
                                                        }
                                                        stringbuilder.append('\n');
                                                        for (int j = 0; j < i; j++)
                                                        {
                                                            stringbuilder.append(' ');
                                                        }

                                                        stringbuilder.append(s);
                                                        if (!(obj instanceof String))
                                                        {
                                                            break label0;
                                                        }
                                                        stringbuilder = stringbuilder.append(": \"");
                                                        s = new TextFormatEscaper._cls1(ByteString.copyFromUtf8((String)obj));
                                                        obj = new StringBuilder(s.size());
                                                        i = ((flag) ? 1 : 0);
label12:
                                                        do
                                                        {
                                                            {
                                                                if (i >= s.size())
                                                                {
                                                                    break label2;
                                                                }
                                                                byte byte0 = s.byteAt(i);
                                                                switch (byte0)
                                                                {
                                                                default:
                                                                    if (byte0 >= 32 && byte0 <= 126)
                                                                    {
                                                                        ((StringBuilder) (obj)).append((char)byte0);
                                                                    } else
                                                                    {
                                                                        ((StringBuilder) (obj)).append('\\');
                                                                        ((StringBuilder) (obj)).append((char)((byte0 >>> 6 & 3) + 48));
                                                                        ((StringBuilder) (obj)).append((char)((byte0 >>> 3 & 7) + 48));
                                                                        ((StringBuilder) (obj)).append((char)((byte0 & 7) + 48));
                                                                    }
                                                                    break;

                                                                case 7: // '\007'
                                                                    break label12;

                                                                case 8: // '\b'
                                                                    break label11;

                                                                case 9: // '\t'
                                                                    break label7;

                                                                case 10: // '\n'
                                                                    break label9;

                                                                case 11: // '\013'
                                                                    break label6;

                                                                case 12: // '\f'
                                                                    break label10;

                                                                case 13: // '\r'
                                                                    break label8;

                                                                case 34: // '"'
                                                                    break label3;

                                                                case 39: // '\''
                                                                    break label4;

                                                                case 92: // '\\'
                                                                    break label5;
                                                                }
                                                            }
                                                            i++;
                                                        } while (true);
                                                        ((StringBuilder) (obj)).append("\\a");
                                                        break MISSING_BLOCK_LABEL_318;
                                                    }
                                                    ((StringBuilder) (obj)).append("\\b");
                                                    break MISSING_BLOCK_LABEL_318;
                                                }
                                                ((StringBuilder) (obj)).append("\\f");
                                                break MISSING_BLOCK_LABEL_318;
                                            }
                                            ((StringBuilder) (obj)).append("\\n");
                                            break MISSING_BLOCK_LABEL_318;
                                        }
                                        ((StringBuilder) (obj)).append("\\r");
                                        break MISSING_BLOCK_LABEL_318;
                                    }
                                    ((StringBuilder) (obj)).append("\\t");
                                    break MISSING_BLOCK_LABEL_318;
                                }
                                ((StringBuilder) (obj)).append("\\v");
                                break MISSING_BLOCK_LABEL_318;
                            }
                            ((StringBuilder) (obj)).append("\\\\");
                            break MISSING_BLOCK_LABEL_318;
                        }
                        ((StringBuilder) (obj)).append("\\'");
                        break MISSING_BLOCK_LABEL_318;
                    }
                    ((StringBuilder) (obj)).append("\\\"");
                    break MISSING_BLOCK_LABEL_318;
                }
                stringbuilder.append(((StringBuilder) (obj)).toString()).append('"');
            }
            return;
        }
label13:
        {
label14:
            {
label15:
                {
label16:
                    {
label17:
                        {
label18:
                            {
label19:
                                {
label20:
                                    {
label21:
                                        {
label22:
                                            {
label23:
                                                {
                                                    if (!(obj instanceof ByteString))
                                                    {
                                                        break label13;
                                                    }
                                                    stringbuilder = stringbuilder.append(": \"");
                                                    s = new TextFormatEscaper._cls1((ByteString)obj);
                                                    obj = new StringBuilder(s.size());
                                                    i = ((flag1) ? 1 : 0);
label24:
                                                    do
                                                    {
                                                        {
                                                            if (i >= s.size())
                                                            {
                                                                break label14;
                                                            }
                                                            byte byte1 = s.byteAt(i);
                                                            switch (byte1)
                                                            {
                                                            default:
                                                                if (byte1 >= 32 && byte1 <= 126)
                                                                {
                                                                    ((StringBuilder) (obj)).append((char)byte1);
                                                                } else
                                                                {
                                                                    ((StringBuilder) (obj)).append('\\');
                                                                    ((StringBuilder) (obj)).append((char)((byte1 >>> 6 & 3) + 48));
                                                                    ((StringBuilder) (obj)).append((char)((byte1 >>> 3 & 7) + 48));
                                                                    ((StringBuilder) (obj)).append((char)((byte1 & 7) + 48));
                                                                }
                                                                break;

                                                            case 7: // '\007'
                                                                break label24;

                                                            case 8: // '\b'
                                                                break label23;

                                                            case 9: // '\t'
                                                                break label19;

                                                            case 10: // '\n'
                                                                break label21;

                                                            case 11: // '\013'
                                                                break label18;

                                                            case 12: // '\f'
                                                                break label22;

                                                            case 13: // '\r'
                                                                break label20;

                                                            case 34: // '"'
                                                                break label15;

                                                            case 39: // '\''
                                                                break label16;

                                                            case 92: // '\\'
                                                                break label17;
                                                            }
                                                        }
                                                        i++;
                                                    } while (true);
                                                    ((StringBuilder) (obj)).append("\\a");
                                                    break MISSING_BLOCK_LABEL_674;
                                                }
                                                ((StringBuilder) (obj)).append("\\b");
                                                break MISSING_BLOCK_LABEL_674;
                                            }
                                            ((StringBuilder) (obj)).append("\\f");
                                            break MISSING_BLOCK_LABEL_674;
                                        }
                                        ((StringBuilder) (obj)).append("\\n");
                                        break MISSING_BLOCK_LABEL_674;
                                    }
                                    ((StringBuilder) (obj)).append("\\r");
                                    break MISSING_BLOCK_LABEL_674;
                                }
                                ((StringBuilder) (obj)).append("\\t");
                                break MISSING_BLOCK_LABEL_674;
                            }
                            ((StringBuilder) (obj)).append("\\v");
                            break MISSING_BLOCK_LABEL_674;
                        }
                        ((StringBuilder) (obj)).append("\\\\");
                        break MISSING_BLOCK_LABEL_674;
                    }
                    ((StringBuilder) (obj)).append("\\'");
                    break MISSING_BLOCK_LABEL_674;
                }
                ((StringBuilder) (obj)).append("\\\"");
                break MISSING_BLOCK_LABEL_674;
            }
            stringbuilder.append(((StringBuilder) (obj)).toString()).append('"');
            return;
        }
        if (obj instanceof GeneratedMessageLite)
        {
            stringbuilder.append(" {");
            reflectivePrintWithIndent((GeneratedMessageLite)obj, stringbuilder, i + 2);
            stringbuilder.append("\n");
            for (int k = ((flag2) ? 1 : 0); k < i; k++)
            {
                stringbuilder.append(' ');
            }

            stringbuilder.append("}");
            return;
        }
        if (obj instanceof java.util.Map.Entry)
        {
            stringbuilder.append(" {");
            s = (java.util.Map.Entry)obj;
            printField(stringbuilder, i + 2, "key", s.getKey());
            printField(stringbuilder, i + 2, "value", s.getValue());
            stringbuilder.append("\n");
            for (int l = ((flag3) ? 1 : 0); l < i; l++)
            {
                stringbuilder.append(' ');
            }

            stringbuilder.append("}");
            return;
        } else
        {
            stringbuilder.append(": ").append(obj.toString());
            return;
        }
    }

    static void reflectivePrintWithIndent(MessageLite messagelite, StringBuilder stringbuilder, int i)
    {
        HashMap hashmap = new HashMap();
        HashMap hashmap1 = new HashMap();
        TreeSet treeset = new TreeSet();
        Method amethod[] = messagelite.getClass().getDeclaredMethods();
        int i1 = amethod.length;
        for (int j = 0; j < i1; j++)
        {
            Method method = amethod[j];
            hashmap1.put(method.getName(), method);
            if (method.getParameterTypes().length != 0)
            {
                continue;
            }
            hashmap.put(method.getName(), method);
            if (method.getName().startsWith("get"))
            {
                treeset.add(method.getName());
            }
        }

        Iterator iterator1 = treeset.iterator();
        do
        {
            if (iterator1.hasNext())
            {
                Object obj = (String)iterator1.next();
                String s3 = ((String) (obj)).replaceFirst("get", "");
                if (s3.endsWith("List") && !s3.endsWith("OrBuilderList") && !s3.equals("List"))
                {
                    String s = String.valueOf(s3.substring(0, 1).toLowerCase());
                    Object obj2 = String.valueOf(s3.substring(1, s3.length() - 4));
                    if (((String) (obj2)).length() != 0)
                    {
                        s = s.concat(((String) (obj2)));
                    } else
                    {
                        s = new String(s);
                    }
                    obj2 = (Method)hashmap.get(obj);
                    if (obj2 != null && ((Method) (obj2)).getReturnType().equals(java/util/List))
                    {
                        printField(stringbuilder, i, camelCaseToSnakeCase(s), GeneratedMessageLite.invokeOrDie(((Method) (obj2)), messagelite, new Object[0]));
                        continue;
                    }
                }
                if (s3.endsWith("Map") && !s3.equals("Map"))
                {
                    String s1 = String.valueOf(s3.substring(0, 1).toLowerCase());
                    String s4 = String.valueOf(s3.substring(1, s3.length() - 3));
                    if (s4.length() != 0)
                    {
                        s1 = s1.concat(s4);
                    } else
                    {
                        s1 = new String(s1);
                    }
                    obj = (Method)hashmap.get(obj);
                    if (obj != null && ((Method) (obj)).getReturnType().equals(java/util/Map) && !((Method) (obj)).isAnnotationPresent(java/lang/Deprecated) && Modifier.isPublic(((Method) (obj)).getModifiers()))
                    {
                        printField(stringbuilder, i, camelCaseToSnakeCase(s1), GeneratedMessageLite.invokeOrDie(((Method) (obj)), messagelite, new Object[0]));
                        continue;
                    }
                }
                String s2 = String.valueOf(s3);
                if (s2.length() != 0)
                {
                    s2 = "set".concat(s2);
                } else
                {
                    s2 = new String("set");
                }
                if ((Method)hashmap1.get(s2) == null)
                {
                    continue;
                }
                if (s3.endsWith("Bytes"))
                {
                    s2 = String.valueOf(s3.substring(0, s3.length() - 5));
                    Method method1;
                    if (s2.length() != 0)
                    {
                        s2 = "get".concat(s2);
                    } else
                    {
                        s2 = new String("get");
                    }
                    if (hashmap.containsKey(s2))
                    {
                        continue;
                    }
                }
                s2 = String.valueOf(s3.substring(0, 1).toLowerCase());
                obj = String.valueOf(s3.substring(1));
                if (((String) (obj)).length() != 0)
                {
                    s2 = s2.concat(((String) (obj)));
                } else
                {
                    s2 = new String(s2);
                }
                obj = String.valueOf(s3);
                if (((String) (obj)).length() != 0)
                {
                    obj = "get".concat(((String) (obj)));
                } else
                {
                    obj = new String("get");
                }
                method1 = (Method)hashmap.get(obj);
                obj = String.valueOf(s3);
                if (((String) (obj)).length() != 0)
                {
                    obj = "has".concat(((String) (obj)));
                } else
                {
                    obj = new String("has");
                }
                obj = (Method)hashmap.get(obj);
                if (method1 != null)
                {
                    Object obj1 = GeneratedMessageLite.invokeOrDie(method1, messagelite, new Object[0]);
                    boolean flag;
                    if (obj == null)
                    {
                        if (obj1 instanceof Boolean)
                        {
                            if (!((Boolean)obj1).booleanValue())
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                        } else
                        if (obj1 instanceof Integer)
                        {
                            if (((Integer)obj1).intValue() == 0)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                        } else
                        if (obj1 instanceof Float)
                        {
                            if (((Float)obj1).floatValue() == 0.0F)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                        } else
                        if (obj1 instanceof Double)
                        {
                            if (((Double)obj1).doubleValue() == 0.0D)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                        } else
                        if (obj1 instanceof String)
                        {
                            flag = obj1.equals("");
                        } else
                        if (obj1 instanceof ByteString)
                        {
                            flag = obj1.equals(ByteString.EMPTY);
                        } else
                        if (obj1 instanceof MessageLite)
                        {
                            if (obj1 == ((MessageLite)obj1).getDefaultInstanceForType())
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                        } else
                        if (obj1 instanceof Enum)
                        {
                            if (((Enum)obj1).ordinal() == 0)
                            {
                                flag = true;
                            } else
                            {
                                flag = false;
                            }
                        } else
                        {
                            flag = false;
                        }
                        if (!flag)
                        {
                            flag = true;
                        } else
                        {
                            flag = false;
                        }
                    } else
                    {
                        flag = ((Boolean)GeneratedMessageLite.invokeOrDie(((Method) (obj)), messagelite, new Object[0])).booleanValue();
                    }
                    if (flag)
                    {
                        printField(stringbuilder, i, camelCaseToSnakeCase(s2), obj1);
                    }
                }
            } else
            {
                if (messagelite instanceof GeneratedMessageLite.ExtendableMessage)
                {
                    java.util.Map.Entry entry;
                    int k;
                    for (Iterator iterator = ((GeneratedMessageLite.ExtendableMessage)messagelite).extensions.iterator(); iterator.hasNext(); printField(stringbuilder, i, (new StringBuilder(13)).append("[").append(k).append("]").toString(), entry.getValue()))
                    {
                        entry = (java.util.Map.Entry)iterator.next();
                        k = ((GeneratedMessageLite.ExtensionDescriptor)entry.getKey()).number;
                    }

                }
                if (((GeneratedMessageLite)messagelite).unknownFields != null)
                {
                    messagelite = ((GeneratedMessageLite)messagelite).unknownFields;
                    for (int l = 0; l < ((UnknownFieldSetLite) (messagelite)).count; l++)
                    {
                        printField(stringbuilder, i, String.valueOf(((UnknownFieldSetLite) (messagelite)).tags[l] >>> 3), ((UnknownFieldSetLite) (messagelite)).objects[l]);
                    }

                }
                return;
            }
        } while (true);
    }
}
