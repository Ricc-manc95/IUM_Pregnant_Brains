// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import android.support.v4.util.ArrayMap;
import android.support.v4.util.SparseArrayCompat;
import com.google.android.libraries.performance.primes.hprof.collect.IntIntMap;
import com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap;
import com.google.android.libraries.performance.primes.hprof.collect.TrieMap;
import java.lang.ref.Reference;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            ParseContext, HprofClass, HprofClassInstance, HprofArrayInstance, 
//            HprofPrimitiveArrayInstance, HprofObject, ParseResult

public final class HprofParser
{

    private static final Map PRIMITIVE_ARRAY_2_TYPES;
    public final TrieMap actionsForClass = new TrieMap();
    public final ByteBuffer buffer;
    public final IntObjectMap classes = new IntObjectMap();
    public String heapName;
    public final IntObjectMap id2Actions = new IntObjectMap();
    public final IntObjectMap instances = new IntObjectMap();
    public final Map instancesFound = new ArrayMap();
    public int objectClassId;
    public final ParseContext parseContext;
    public final IntIntMap rootIds = new IntIntMap();
    public final IntIntMap rootTagsToExclude = new IntIntMap();
    public final IntIntMap stringPositions = new IntIntMap();
    public final IntObjectMap type2Actions = new IntObjectMap();

    public HprofParser(ParseContext parsecontext, Iterable iterable, Iterable iterable1, Iterable iterable2)
    {
        heapName = "";
        parseContext = parsecontext;
        buffer = parsecontext.buffer;
        actionsForClass.putIfAbsent(java/lang/ref/Reference.getName(), ParseAction.CLASSIFY_REF);
        actionsForClass.putIfAbsent(java/lang/Object.getName(), ParseAction.IDENTIFY_OBJECT_CLASS);
        actionsForClass.putIfAbsent(java/lang/Class.getName(), ParseAction.IDENTIFY_JAVA_LANG_CLASS);
        if (iterable1 != null)
        {
            parsecontext = iterable1.iterator();
            do
            {
                if (!parsecontext.hasNext())
                {
                    break;
                }
                iterable1 = (String)parsecontext.next();
                actionsForClass.putIfAbsent(iterable1, ParseAction.EXCLUDE_INSTANCE);
                if (PRIMITIVE_ARRAY_2_TYPES.containsKey(iterable1))
                {
                    type2Actions.putIfAbsent(((Integer)PRIMITIVE_ARRAY_2_TYPES.get(iterable1)).intValue(), ParseAction.EXCLUDE_INSTANCE);
                }
            } while (true);
        }
        if (iterable2 != null)
        {
            for (parsecontext = iterable2.iterator(); parsecontext.hasNext(); actionsForClass.putIfAbsent(iterable1, ParseAction.FIND_INSTANCE))
            {
                iterable1 = (String)parsecontext.next();
            }

        }
        if (iterable != null)
        {
            int i;
            for (parsecontext = iterable.iterator(); parsecontext.hasNext(); rootTagsToExclude.putIfAbsent(i, 0))
            {
                i = ((Integer)parsecontext.next()).intValue();
            }

        }
    }

    static ParseResult parseBuffer(ParseContext parsecontext, Iterable iterable, Iterable iterable1, Iterable iterable2)
    {
        iterable1 = new HprofParser(parsecontext, iterable, iterable1, iterable2);
_L13:
        int i;
        if (!((HprofParser) (iterable1)).buffer.hasRemaining())
        {
            break; /* Loop/switch isn't completed */
        }
        i = ((HprofParser) (iterable1)).buffer.get();
        ((HprofParser) (iterable1)).buffer.getInt();
        if (((HprofParser) (iterable1)).buffer.getInt(((HprofParser) (iterable1)).buffer.position()) < 0)
        {
            throw new RuntimeException("Length too large to parse.");
        }
        i;
        JVM INSTR lookupswitch 4: default 112
    //                   1: 133
    //                   2: 192
    //                   12: 476
    //                   28: 476;
           goto _L1 _L2 _L3 _L4 _L4
_L1:
        i = ((HprofParser) (iterable1)).buffer.getInt();
        ((HprofParser) (iterable1)).parseContext.skipBytes(i);
        continue; /* Loop/switch isn't completed */
_L2:
        i = ((HprofParser) (iterable1)).buffer.position();
        int i2 = ((HprofParser) (iterable1)).buffer.getInt();
        int l3 = ((HprofParser) (iterable1)).parseContext.readId();
        ((HprofParser) (iterable1)).stringPositions.putIfAbsent(l3, i);
        ((HprofParser) (iterable1)).parseContext.skipBytes(i2 - ((HprofParser) (iterable1)).parseContext.idSize);
        continue; /* Loop/switch isn't completed */
_L3:
        int j2;
        int i4;
        int l5;
        int j6;
        ((HprofParser) (iterable1)).buffer.getInt();
        ((HprofParser) (iterable1)).buffer.getInt();
        j2 = ((HprofParser) (iterable1)).buffer.position();
        i4 = ((HprofParser) (iterable1)).parseContext.readId();
        ((HprofParser) (iterable1)).buffer.getInt();
        parsecontext = ((HprofParser) (iterable1)).stringPositions;
        i = ((HprofParser) (iterable1)).parseContext.readId();
        i = ((IntIntMap) (parsecontext)).values[parsecontext.findKeyIndex(i)];
        iterable = new HprofClass(j2, i);
        ((HprofParser) (iterable1)).classes.putIfAbsent(i4, iterable);
        parsecontext = ((HprofParser) (iterable1)).parseContext;
        l5 = parsecontext.buffer.getInt(i);
        j6 = parsecontext.idSize;
        j2 = ((HprofParser) (iterable1)).parseContext.idSize + (i + 4);
        parsecontext = ((HprofParser) (iterable1)).actionsForClass;
        iterable2 = ((HprofParser) (iterable1)).buffer;
        parsecontext = ((TrieMap) (parsecontext)).head;
        i = j2;
_L10:
        if (i >= (l5 - j6) + j2) goto _L6; else goto _L5
_L5:
        byte byte3 = iterable2.get(i);
        if (((com.google.android.libraries.performance.primes.hprof.collect.TrieMap.Edge) (parsecontext)).edges == null) goto _L8; else goto _L7
_L7:
        parsecontext = (com.google.android.libraries.performance.primes.hprof.collect.TrieMap.Edge)((com.google.android.libraries.performance.primes.hprof.collect.TrieMap.Edge) (parsecontext)).edges.get(byte3);
        if (parsecontext != null) goto _L9; else goto _L8
_L8:
        parsecontext = null;
_L11:
        parsecontext = (ParseAction)parsecontext;
        if (parsecontext == ParseAction.IDENTIFY_OBJECT_CLASS)
        {
            iterable1.objectClassId = i4;
        } else
        if (parsecontext == ParseAction.IDENTIFY_JAVA_LANG_CLASS)
        {
            HprofClass.javaLangClass = iterable;
        } else
        if (parsecontext == ParseAction.CLASSIFY_REF)
        {
            iterable.flags = ((HprofClass) (iterable)).flags | 2;
        } else
        if (parsecontext != null)
        {
            ((HprofParser) (iterable1)).id2Actions.putIfAbsent(i4, parsecontext);
        }
        continue; /* Loop/switch isn't completed */
_L9:
        i++;
          goto _L10
_L6:
        parsecontext = ((ParseContext) (((com.google.android.libraries.performance.primes.hprof.collect.TrieMap.Edge) (parsecontext)).value));
          goto _L11
_L4:
        int j = ((HprofParser) (iterable1)).buffer.getInt();
        int i6 = ((HprofParser) (iterable1)).buffer.position() + j;
        do
        {
            if (((HprofParser) (iterable1)).buffer.position() >= i6)
            {
                break;
            }
            int k = ((HprofParser) (iterable1)).buffer.get() & 0xff;
            parsecontext = ((HprofParser) (iterable1)).parseContext.rootTagSizes;
            if (((IntIntMap) (parsecontext)).values[parsecontext.findKeyIndex(k)] != ((IntIntMap) (parsecontext)).emptyValue)
            {
                parsecontext = ((HprofParser) (iterable1)).parseContext.rootTagSizes;
                int k2 = ((IntIntMap) (parsecontext)).values[parsecontext.findKeyIndex(k)];
                parsecontext = ((HprofParser) (iterable1)).rootTagsToExclude;
                if (((IntIntMap) (parsecontext)).values[parsecontext.findKeyIndex(k)] != ((IntIntMap) (parsecontext)).emptyValue)
                {
                    ((HprofParser) (iterable1)).parseContext.skipBytes(k2);
                } else
                {
                    int j4 = ((HprofParser) (iterable1)).parseContext.readId();
                    ((HprofParser) (iterable1)).rootIds.putIfAbsent(j4, k);
                    ((HprofParser) (iterable1)).parseContext.skipBytes(k2 - ((HprofParser) (iterable1)).parseContext.idSize);
                }
            } else
            {
                switch (k)
                {
                default:
                    throw new IllegalArgumentException((new StringBuilder(23)).append("Unknown tag ").append(k).toString());

                case 32: // ' '
                    int l = ((HprofParser) (iterable1)).parseContext.readId();
                    if (l == ((HprofParser) (iterable1)).objectClassId)
                    {
                        parsecontext = ((HprofParser) (iterable1)).classes;
                        iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(l)]));
                        parsecontext = iterable;
                        if (iterable == IntObjectMap.DELETED)
                        {
                            parsecontext = null;
                        }
                        iterable2 = (HprofClass)parsecontext;
                        ParseContext parsecontext1 = ((HprofParser) (iterable1)).parseContext;
                        parsecontext = ((HprofParser) (iterable1)).classes;
                        ByteBuffer bytebuffer = parsecontext1.buffer;
                        iterable2.position = bytebuffer.position() - parsecontext1.idSize;
                        bytebuffer.getInt();
                        l = parsecontext1.readId();
                        iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(l)]));
                        parsecontext = iterable;
                        if (iterable == IntObjectMap.DELETED)
                        {
                            parsecontext = null;
                        }
                        iterable2.superClass = (HprofClass)parsecontext;
                        parsecontext1.skipBytes(parsecontext1.idSize * 5);
                        iterable2.instanceSize = bytebuffer.getInt();
                        HprofClass.skipClassConstants(parsecontext1);
                        parsecontext = parsecontext1.buffer;
                        short word0 = parsecontext.getShort();
                        for (l = 0; l < (word0 & 0xffff); l++)
                        {
                            parsecontext1.readId();
                            byte byte1 = parsecontext.get();
                            int k6 = parsecontext1.typeSizes[byte1];
                            boolean flag3;
                            if (k6 > 0)
                            {
                                flag3 = true;
                            } else
                            {
                                flag3 = false;
                            }
                            if (!flag3)
                            {
                                throw new IllegalStateException();
                            }
                            parsecontext1.skipBytes(k6);
                        }

                        iterable2.staticValueIds = new int[0];
                        iterable2.staticFieldNamePositions = new int[0];
                        parsecontext = parsecontext1.buffer;
                        word0 = parsecontext.getShort();
                        iterable2.totalOffset = 0;
                        for (l = 0; l < (word0 & 0xffff); l++)
                        {
                            parsecontext1.readId();
                            byte byte2 = parsecontext.get();
                            int l6 = ((HprofClass) (iterable2)).totalOffset;
                            int i8 = parsecontext1.typeSizes[byte2];
                            boolean flag4;
                            if (i8 > 0)
                            {
                                flag4 = true;
                            } else
                            {
                                flag4 = false;
                            }
                            if (!flag4)
                            {
                                throw new IllegalStateException();
                            }
                            iterable2.totalOffset = l6 + i8;
                        }

                        iterable2.fieldNamePositions = new int[0];
                        iterable2.offsets = new int[0];
                    } else
                    {
                        parsecontext = ((HprofParser) (iterable1)).classes;
                        iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(l)]));
                        parsecontext = iterable;
                        if (iterable == IntObjectMap.DELETED)
                        {
                            parsecontext = null;
                        }
                        iterable2 = (HprofClass)parsecontext;
                        ParseContext parsecontext2 = ((HprofParser) (iterable1)).parseContext;
                        parsecontext = ((HprofParser) (iterable1)).classes;
                        IntIntMap intintmap = ((HprofParser) (iterable1)).stringPositions;
                        ByteBuffer bytebuffer1 = parsecontext2.buffer;
                        iterable2.position = bytebuffer1.position() - parsecontext2.idSize;
                        bytebuffer1.getInt();
                        l = parsecontext2.readId();
                        iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(l)]));
                        parsecontext = iterable;
                        if (iterable == IntObjectMap.DELETED)
                        {
                            parsecontext = null;
                        }
                        iterable2.superClass = (HprofClass)parsecontext;
                        parsecontext2.skipBytes(parsecontext2.idSize * 5);
                        iterable2.instanceSize = bytebuffer1.getInt();
                        HprofClass.skipClassConstants(parsecontext2);
                        parsecontext = parsecontext2.buffer;
                        int i7 = parsecontext.getShort() & 0xffff;
                        iterable2.staticValueIds = new int[i7];
                        iterable2.staticFieldNamePositions = new int[i7];
                        int l2 = 0;
                        l = 0;
                        while (l < i7) 
                        {
                            int j8 = parsecontext2.readId();
                            int k9 = parsecontext.get();
                            int i10 = ((HprofClass) (iterable2)).staticFieldsSize;
                            int j10 = parsecontext2.typeSizes[k9];
                            boolean flag5;
                            if (j10 > 0)
                            {
                                flag5 = true;
                            } else
                            {
                                flag5 = false;
                            }
                            if (!flag5)
                            {
                                throw new IllegalStateException();
                            }
                            iterable2.staticFieldsSize = i10 + j10;
                            int k4;
                            if (k9 == 2)
                            {
                                k9 = parsecontext2.readId();
                                k4 = l2;
                                if (k9 != 0)
                                {
                                    ((HprofClass) (iterable2)).staticValueIds[l2] = k9;
                                    ((HprofClass) (iterable2)).staticFieldNamePositions[l2] = intintmap.values[intintmap.findKeyIndex(j8)];
                                    k4 = l2 + 1;
                                }
                            } else
                            {
                                int k8 = parsecontext2.typeSizes[k9];
                                if (k8 > 0)
                                {
                                    k4 = 1;
                                } else
                                {
                                    k4 = 0;
                                }
                                if (k4 == 0)
                                {
                                    throw new IllegalStateException();
                                }
                                parsecontext2.skipBytes(k8);
                                k4 = l2;
                            }
                            l++;
                            l2 = k4;
                        }
                        int l4;
                        if (l2 == i7)
                        {
                            parsecontext = ((HprofClass) (iterable2)).staticValueIds;
                        } else
                        {
                            parsecontext = Arrays.copyOf(((HprofClass) (iterable2)).staticValueIds, l2);
                        }
                        iterable2.staticValueIds = parsecontext;
                        if (l2 == i7)
                        {
                            parsecontext = ((HprofClass) (iterable2)).staticFieldNamePositions;
                        } else
                        {
                            parsecontext = Arrays.copyOf(((HprofClass) (iterable2)).staticFieldNamePositions, l2);
                        }
                        iterable2.staticFieldNamePositions = parsecontext;
                        parsecontext = parsecontext2.buffer;
                        i7 = parsecontext.getShort() & 0xffff;
                        iterable2.fieldNamePositions = new int[i7];
                        iterable2.offsets = new int[i7];
                        iterable2.totalOffset = 0;
                        l = 0;
                        for (l4 = 0; l < i7; l4 = l2)
                        {
                            int l8 = parsecontext2.readId();
                            int l9 = parsecontext.get();
                            l2 = l4;
                            if (l9 == 2)
                            {
                                ((HprofClass) (iterable2)).fieldNamePositions[l4] = intintmap.values[intintmap.findKeyIndex(l8)];
                                ((HprofClass) (iterable2)).offsets[l4] = ((HprofClass) (iterable2)).totalOffset;
                                l2 = l4 + 1;
                            }
                            l8 = ((HprofClass) (iterable2)).totalOffset;
                            l9 = parsecontext2.typeSizes[l9];
                            if (l9 > 0)
                            {
                                l4 = 1;
                            } else
                            {
                                l4 = 0;
                            }
                            if (l4 == 0)
                            {
                                throw new IllegalStateException();
                            }
                            iterable2.totalOffset = l8 + l9;
                            l++;
                        }

                        if (l4 == i7)
                        {
                            parsecontext = ((HprofClass) (iterable2)).fieldNamePositions;
                        } else
                        {
                            parsecontext = Arrays.copyOf(((HprofClass) (iterable2)).fieldNamePositions, l4);
                        }
                        iterable2.fieldNamePositions = parsecontext;
                        if (l4 == i7)
                        {
                            parsecontext = ((HprofClass) (iterable2)).offsets;
                        } else
                        {
                            parsecontext = Arrays.copyOf(((HprofClass) (iterable2)).offsets, l4);
                        }
                        iterable2.offsets = parsecontext;
                    }
                    break;

                case 33: // '!'
                    int i1 = ((HprofParser) (iterable1)).buffer.position();
                    int i3 = ((HprofParser) (iterable1)).parseContext.readId();
                    ((HprofParser) (iterable1)).buffer.getInt();
                    int i5 = ((HprofParser) (iterable1)).parseContext.readId();
                    int j7 = ((HprofParser) (iterable1)).buffer.getInt();
                    parsecontext = ((HprofParser) (iterable1)).classes;
                    iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(i5)]));
                    parsecontext = iterable;
                    if (iterable == IntObjectMap.DELETED)
                    {
                        parsecontext = null;
                    }
                    iterable2 = (HprofClass)parsecontext;
                    parsecontext = ((HprofParser) (iterable1)).id2Actions;
                    iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(i5)]));
                    parsecontext = iterable;
                    if (iterable == IntObjectMap.DELETED)
                    {
                        parsecontext = null;
                    }
                    parsecontext = (ParseAction)parsecontext;
                    if (iterable2 != null && parsecontext != ParseAction.EXCLUDE_INSTANCE)
                    {
                        HprofClassInstance hprofclassinstance = new HprofClassInstance(i1, iterable2);
                        hprofclassinstance.heapName = ((HprofParser) (iterable1)).heapName;
                        ((HprofParser) (iterable1)).instances.putIfAbsent(i3, hprofclassinstance);
                        if (parsecontext == ParseAction.FIND_INSTANCE)
                        {
                            iterable2 = ((HprofParser) (iterable1)).parseContext.readString(((HprofClass) (iterable2)).classNamePosition);
                            iterable = (List)((HprofParser) (iterable1)).instancesFound.get(iterable2);
                            parsecontext = iterable;
                            if (iterable == null)
                            {
                                parsecontext = new ArrayList();
                                ((HprofParser) (iterable1)).instancesFound.put(iterable2, parsecontext);
                            }
                            parsecontext.add(hprofclassinstance);
                        }
                    }
                    ((HprofParser) (iterable1)).parseContext.skipBytes(j7);
                    break;

                case 34: // '"'
                    int j3 = ((HprofParser) (iterable1)).buffer.position();
                    int j5 = ((HprofParser) (iterable1)).parseContext.readId();
                    ((HprofParser) (iterable1)).buffer.getInt();
                    int k7 = ((HprofParser) (iterable1)).buffer.getInt();
                    int i9 = ((HprofParser) (iterable1)).parseContext.readId();
                    parsecontext = ((HprofParser) (iterable1)).id2Actions;
                    iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(i9)]));
                    parsecontext = iterable;
                    if (iterable == IntObjectMap.DELETED)
                    {
                        parsecontext = null;
                    }
                    iterable2 = (ParseAction)parsecontext;
                    parsecontext = ((HprofParser) (iterable1)).classes;
                    iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(i9)]));
                    parsecontext = iterable;
                    if (iterable == IntObjectMap.DELETED)
                    {
                        parsecontext = null;
                    }
                    boolean flag;
                    if (parsecontext != null && parsecontext != IntObjectMap.DELETED)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag && iterable2 != ParseAction.EXCLUDE_INSTANCE)
                    {
                        parsecontext = ((HprofParser) (iterable1)).classes;
                        iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(i9)]));
                        parsecontext = iterable;
                        if (iterable == IntObjectMap.DELETED)
                        {
                            parsecontext = null;
                        }
                        parsecontext = new HprofArrayInstance(j3, (HprofClass)parsecontext);
                        parsecontext.heapName = ((HprofParser) (iterable1)).heapName;
                        ((HprofParser) (iterable1)).instances.putIfAbsent(j5, parsecontext);
                    }
                    ((HprofParser) (iterable1)).parseContext.skipBytes(((HprofParser) (iterable1)).parseContext.idSize * k7);
                    break;

                case 35: // '#'
                case 195: 
                    int k3 = ((HprofParser) (iterable1)).buffer.position();
                    int k5 = ((HprofParser) (iterable1)).parseContext.readId();
                    ((HprofParser) (iterable1)).buffer.getInt();
                    int l7 = ((HprofParser) (iterable1)).buffer.getInt();
                    byte byte0 = ((HprofParser) (iterable1)).buffer.get();
                    parsecontext = ((HprofParser) (iterable1)).type2Actions;
                    iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(byte0)]));
                    parsecontext = iterable;
                    if (iterable == IntObjectMap.DELETED)
                    {
                        parsecontext = null;
                    }
                    parsecontext = (ParseAction)parsecontext;
                    iterable = ((HprofParser) (iterable1)).parseContext;
                    int j9 = ((HprofParser) (iterable1)).parseContext.typeSizes[byte0];
                    boolean flag1;
                    if (j9 > 0)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    if (!flag1)
                    {
                        throw new IllegalStateException();
                    }
                    iterable.skipBytes(l7 * j9);
                    if (parsecontext != ParseAction.EXCLUDE_INSTANCE)
                    {
                        parsecontext = new HprofPrimitiveArrayInstance(k3);
                        parsecontext.heapName = ((HprofParser) (iterable1)).heapName;
                        ((HprofParser) (iterable1)).instances.putIfAbsent(k5, parsecontext);
                    }
                    break;

                case 254: 
                    ((HprofParser) (iterable1)).buffer.getInt();
                    int j1 = ((HprofParser) (iterable1)).parseContext.readId();
                    parsecontext = ((HprofParser) (iterable1)).parseContext;
                    iterable = ((HprofParser) (iterable1)).stringPositions;
                    iterable1.heapName = parsecontext.readString(((IntIntMap) (iterable)).values[iterable.findKeyIndex(j1)]);
                    break;
                }
            }
        } while (true);
        boolean flag2;
        if (((HprofParser) (iterable1)).buffer.position() == i6)
        {
            flag2 = true;
        } else
        {
            flag2 = false;
        }
        if (!flag2)
        {
            throw new IllegalStateException();
        }
        if (true) goto _L13; else goto _L12
_L12:
        parsecontext = ((HprofParser) (iterable1)).classes;
        for (parsecontext = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (parsecontext)).keys, ((IntObjectMap) (parsecontext)).values); parsecontext.next(); ((HprofClass)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).resolveSuperClasses()) { }
        iterable2 = new ArrayList();
        parsecontext = ((HprofParser) (iterable1)).rootIds;
        com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator enumerator = new com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator(((IntIntMap) (parsecontext)).keys, ((IntIntMap) (parsecontext)).values, ((IntIntMap) (parsecontext)).emptyValue);
        do
        {
            int k1;
            for (enumerator.value = enumerator.emptyValue; enumerator.value == enumerator.emptyValue && enumerator.nextIndex < enumerator.values.length; enumerator.value = parsecontext[k1])
            {
                parsecontext = enumerator.values;
                k1 = enumerator.nextIndex;
                enumerator.nextIndex = k1 + 1;
            }

            if (enumerator.nextIndex > 0)
            {
                enumerator.key = enumerator.keys[enumerator.nextIndex - 1];
            }
            if (enumerator.value == enumerator.emptyValue)
            {
                break;
            }
            int l1 = enumerator.key;
            parsecontext = ((HprofParser) (iterable1)).classes;
            iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(l1)]));
            parsecontext = iterable;
            if (iterable == IntObjectMap.DELETED)
            {
                parsecontext = null;
            }
            iterable = (HprofObject)parsecontext;
            parsecontext = iterable;
            if (iterable == null)
            {
                parsecontext = ((HprofParser) (iterable1)).instances;
                iterable = ((Iterable) (((IntObjectMap) (parsecontext)).values[parsecontext.findKeyIndex(l1)]));
                parsecontext = iterable;
                if (iterable == IntObjectMap.DELETED)
                {
                    parsecontext = null;
                }
                parsecontext = (HprofObject)parsecontext;
                if (parsecontext == null)
                {
                    continue;
                }
            }
            parsecontext.flags = ((HprofObject) (parsecontext)).flags | 1;
            parsecontext.rootTag = enumerator.value;
            iterable2.add(parsecontext);
        } while (true);
        ((HprofParser) (iterable1)).stringPositions.init();
        ((HprofParser) (iterable1)).id2Actions.init();
        return new ParseResult(((HprofParser) (iterable1)).classes, ((HprofParser) (iterable1)).instances, iterable2, ((HprofParser) (iterable1)).instancesFound);
    }

    static 
    {
        ArrayMap arraymap = new ArrayMap();
        PRIMITIVE_ARRAY_2_TYPES = arraymap;
        arraymap.put("boolean[]", Integer.valueOf(4));
        PRIMITIVE_ARRAY_2_TYPES.put("char[]", Integer.valueOf(5));
        PRIMITIVE_ARRAY_2_TYPES.put("float[]", Integer.valueOf(6));
        PRIMITIVE_ARRAY_2_TYPES.put("double[]", Integer.valueOf(7));
        PRIMITIVE_ARRAY_2_TYPES.put("byte[]", Integer.valueOf(8));
        PRIMITIVE_ARRAY_2_TYPES.put("short[]", Integer.valueOf(9));
        PRIMITIVE_ARRAY_2_TYPES.put("int[]", Integer.valueOf(10));
        PRIMITIVE_ARRAY_2_TYPES.put("long[]", Integer.valueOf(11));
    }

    private class ParseAction extends Enum
    {

        private static final ParseAction $VALUES[];
        public static final ParseAction CLASSIFY_REF;
        public static final ParseAction EXCLUDE_INSTANCE;
        public static final ParseAction FIND_INSTANCE;
        public static final ParseAction IDENTIFY_JAVA_LANG_CLASS;
        public static final ParseAction IDENTIFY_OBJECT_CLASS;

        public static ParseAction[] values()
        {
            return (ParseAction[])$VALUES.clone();
        }

        static 
        {
            EXCLUDE_INSTANCE = new ParseAction("EXCLUDE_INSTANCE", 0);
            FIND_INSTANCE = new ParseAction("FIND_INSTANCE", 1);
            CLASSIFY_REF = new ParseAction("CLASSIFY_REF", 2);
            IDENTIFY_OBJECT_CLASS = new ParseAction("IDENTIFY_OBJECT_CLASS", 3);
            IDENTIFY_JAVA_LANG_CLASS = new ParseAction("IDENTIFY_JAVA_LANG_CLASS", 4);
            $VALUES = (new ParseAction[] {
                EXCLUDE_INSTANCE, FIND_INSTANCE, CLASSIFY_REF, IDENTIFY_OBJECT_CLASS, IDENTIFY_JAVA_LANG_CLASS
            });
        }

        private ParseAction(String s, int i)
        {
            super(s, i);
        }
    }

}
