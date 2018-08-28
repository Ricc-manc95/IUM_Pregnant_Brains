// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes;

import android.support.v4.util.SparseArrayCompat;
import com.google.android.libraries.performance.primes.hprof.HprofArrayInstance;
import com.google.android.libraries.performance.primes.hprof.HprofClass;
import com.google.android.libraries.performance.primes.hprof.HprofClassInstance;
import com.google.android.libraries.performance.primes.hprof.HprofObject;
import com.google.android.libraries.performance.primes.hprof.HprofParser;
import com.google.android.libraries.performance.primes.hprof.HprofPrimitiveArrayInstance;
import com.google.android.libraries.performance.primes.hprof.HprofSerializer;
import com.google.android.libraries.performance.primes.hprof.ParseContext;
import com.google.android.libraries.performance.primes.hprof.ParseResult;
import com.google.android.libraries.performance.primes.hprof.collect.IntIntMap;
import com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap;
import com.google.android.libraries.performance.primes.hprof.collect.TrieMap;
import java.io.File;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

// Referenced classes of package com.google.android.libraries.performance.primes:
//            HeapDumpProcessor

final class val.hprofFile
    implements Callable
{

    private final HeapDumpProcessor this$0;
    private final File val$hprofFile;

    public final Object call()
        throws Exception
    {
        HprofSerializer hprofserializer;
        ParseContext parsecontext1;
        HprofParser hprofparser;
        hprofserializer = serializer;
        parsecontext1 = ParseContext.prepareContext(val$hprofFile);
        hprofparser = new HprofParser(parsecontext1, Collections.emptyList(), Arrays.asList(new String[] {
            "java.lang.Class"
        }), Collections.emptyList());
_L13:
        int i;
        if (!hprofparser.buffer.hasRemaining())
        {
            break; /* Loop/switch isn't completed */
        }
        i = hprofparser.buffer.get();
        hprofparser.buffer.getInt();
        if (hprofparser.buffer.getInt(hprofparser.buffer.position()) < 0)
        {
            throw new RuntimeException("Length too large to parse.");
        }
        i;
        JVM INSTR lookupswitch 4: default 148
    //                   1: 171
    //                   2: 236
    //                   12: 536
    //                   28: 536;
           goto _L1 _L2 _L3 _L4 _L4
_L1:
        i = hprofparser.buffer.getInt();
        hprofparser.parseContext.skipBytes(i);
        continue; /* Loop/switch isn't completed */
_L2:
        i = hprofparser.buffer.position();
        int i2 = hprofparser.buffer.getInt();
        int l3 = hprofparser.parseContext.readId();
        hprofparser.stringPositions.putIfAbsent(l3, i);
        hprofparser.parseContext.skipBytes(i2 - hprofparser.parseContext.idSize);
        continue; /* Loop/switch isn't completed */
_L3:
        Object obj;
        HprofClass hprofclass;
        ByteBuffer bytebuffer;
        int j2;
        int i4;
        int l5;
        int j6;
        hprofparser.buffer.getInt();
        hprofparser.buffer.getInt();
        j2 = hprofparser.buffer.position();
        i4 = hprofparser.parseContext.readId();
        hprofparser.buffer.getInt();
        obj = hprofparser.stringPositions;
        i = hprofparser.parseContext.readId();
        i = ((IntIntMap) (obj)).values[((IntIntMap) (obj)).findKeyIndex(i)];
        hprofclass = new HprofClass(j2, i);
        hprofparser.classes.putIfAbsent(i4, hprofclass);
        obj = hprofparser.parseContext;
        l5 = ((ParseContext) (obj)).buffer.getInt(i);
        j6 = ((ParseContext) (obj)).idSize;
        j2 = hprofparser.parseContext.idSize + (i + 4);
        obj = hprofparser.actionsForClass;
        bytebuffer = hprofparser.buffer;
        obj = ((TrieMap) (obj)).head;
        i = j2;
_L10:
        if (i >= (l5 - j6) + j2) goto _L6; else goto _L5
_L5:
        byte byte3 = bytebuffer.get(i);
        if (((com.google.android.libraries.performance.primes.hprof.collect.sent) (obj)).sent == null) goto _L8; else goto _L7
_L7:
        obj = (com.google.android.libraries.performance.primes.hprof.collect.sent)((com.google.android.libraries.performance.primes.hprof.collect.sent) (obj)).sent.get(byte3);
        if (obj != null) goto _L9; else goto _L8
_L8:
        obj = null;
_L11:
        obj = (com.google.android.libraries.performance.primes.hprof.ion)obj;
        if (obj == com.google.android.libraries.performance.primes.hprof.ion.IDENTIFY_OBJECT_CLASS)
        {
            hprofparser.objectClassId = i4;
        } else
        if (obj == com.google.android.libraries.performance.primes.hprof.ion.IDENTIFY_JAVA_LANG_CLASS)
        {
            HprofClass.javaLangClass = hprofclass;
        } else
        if (obj == com.google.android.libraries.performance.primes.hprof.ion.CLASSIFY_REF)
        {
            hprofclass.flags = hprofclass.flags | 2;
        } else
        if (obj != null)
        {
            hprofparser.id2Actions.putIfAbsent(i4, obj);
        }
        continue; /* Loop/switch isn't completed */
_L9:
        i++;
          goto _L10
_L6:
        obj = ((com.google.android.libraries.performance.primes.hprof.collect.sent) (obj)).sent;
          goto _L11
_L4:
        int j = hprofparser.buffer.getInt();
        int i6 = hprofparser.buffer.position() + j;
        do
        {
            if (hprofparser.buffer.position() >= i6)
            {
                break;
            }
            int k = hprofparser.buffer.get() & 0xff;
            IntIntMap intintmap = hprofparser.parseContext.rootTagSizes;
            if (intintmap.values[intintmap.findKeyIndex(k)] != intintmap.emptyValue)
            {
                IntIntMap intintmap1 = hprofparser.parseContext.rootTagSizes;
                int k2 = intintmap1.values[intintmap1.findKeyIndex(k)];
                intintmap1 = hprofparser.rootTagsToExclude;
                if (intintmap1.values[intintmap1.findKeyIndex(k)] != intintmap1.emptyValue)
                {
                    hprofparser.parseContext.skipBytes(k2);
                } else
                {
                    int j4 = hprofparser.parseContext.readId();
                    hprofparser.rootIds.putIfAbsent(j4, k);
                    hprofparser.parseContext.skipBytes(k2 - hprofparser.parseContext.idSize);
                }
            } else
            {
                switch (k)
                {
                default:
                    throw new IllegalArgumentException((new StringBuilder(23)).append("Unknown tag ").append(k).toString());

                case 32: // ' '
                    int l = hprofparser.parseContext.readId();
                    if (l == hprofparser.objectClassId)
                    {
                        Object obj1 = hprofparser.classes;
                        Object obj9 = ((IntObjectMap) (obj1)).values[((IntObjectMap) (obj1)).findKeyIndex(l)];
                        obj1 = obj9;
                        if (obj9 == IntObjectMap.DELETED)
                        {
                            obj1 = null;
                        }
                        HprofClass hprofclass1 = (HprofClass)obj1;
                        ParseContext parsecontext2 = hprofparser.parseContext;
                        obj1 = hprofparser.classes;
                        ByteBuffer bytebuffer1 = parsecontext2.buffer;
                        hprofclass1.position = bytebuffer1.position() - parsecontext2.idSize;
                        bytebuffer1.getInt();
                        l = parsecontext2.readId();
                        obj9 = ((IntObjectMap) (obj1)).values[((IntObjectMap) (obj1)).findKeyIndex(l)];
                        obj1 = obj9;
                        if (obj9 == IntObjectMap.DELETED)
                        {
                            obj1 = null;
                        }
                        hprofclass1.superClass = (HprofClass)obj1;
                        parsecontext2.skipBytes(parsecontext2.idSize * 5);
                        hprofclass1.instanceSize = bytebuffer1.getInt();
                        HprofClass.skipClassConstants(parsecontext2);
                        obj1 = parsecontext2.buffer;
                        short word0 = ((ByteBuffer) (obj1)).getShort();
                        for (l = 0; l < (word0 & 0xffff); l++)
                        {
                            parsecontext2.readId();
                            byte byte1 = ((ByteBuffer) (obj1)).get();
                            int k6 = parsecontext2.typeSizes[byte1];
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
                            parsecontext2.skipBytes(k6);
                        }

                        hprofclass1.staticValueIds = new int[0];
                        hprofclass1.staticFieldNamePositions = new int[0];
                        obj1 = parsecontext2.buffer;
                        word0 = ((ByteBuffer) (obj1)).getShort();
                        hprofclass1.totalOffset = 0;
                        for (l = 0; l < (word0 & 0xffff); l++)
                        {
                            parsecontext2.readId();
                            byte byte2 = ((ByteBuffer) (obj1)).get();
                            int l6 = hprofclass1.totalOffset;
                            int i8 = parsecontext2.typeSizes[byte2];
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
                            hprofclass1.totalOffset = l6 + i8;
                        }

                        hprofclass1.fieldNamePositions = new int[0];
                        hprofclass1.offsets = new int[0];
                    } else
                    {
                        Object obj2 = hprofparser.classes;
                        Object obj10 = ((IntObjectMap) (obj2)).values[((IntObjectMap) (obj2)).findKeyIndex(l)];
                        obj2 = obj10;
                        if (obj10 == IntObjectMap.DELETED)
                        {
                            obj2 = null;
                        }
                        HprofClass hprofclass2 = (HprofClass)obj2;
                        ParseContext parsecontext3 = hprofparser.parseContext;
                        obj2 = hprofparser.classes;
                        IntIntMap intintmap3 = hprofparser.stringPositions;
                        ByteBuffer bytebuffer2 = parsecontext3.buffer;
                        hprofclass2.position = bytebuffer2.position() - parsecontext3.idSize;
                        bytebuffer2.getInt();
                        l = parsecontext3.readId();
                        obj10 = ((IntObjectMap) (obj2)).values[((IntObjectMap) (obj2)).findKeyIndex(l)];
                        obj2 = obj10;
                        if (obj10 == IntObjectMap.DELETED)
                        {
                            obj2 = null;
                        }
                        hprofclass2.superClass = (HprofClass)obj2;
                        parsecontext3.skipBytes(parsecontext3.idSize * 5);
                        hprofclass2.instanceSize = bytebuffer2.getInt();
                        HprofClass.skipClassConstants(parsecontext3);
                        obj2 = parsecontext3.buffer;
                        int i7 = ((ByteBuffer) (obj2)).getShort() & 0xffff;
                        hprofclass2.staticValueIds = new int[i7];
                        hprofclass2.staticFieldNamePositions = new int[i7];
                        int l2 = 0;
                        l = 0;
                        while (l < i7) 
                        {
                            int j8 = parsecontext3.readId();
                            int k9 = ((ByteBuffer) (obj2)).get();
                            int i10 = hprofclass2.staticFieldsSize;
                            int j10 = parsecontext3.typeSizes[k9];
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
                            hprofclass2.staticFieldsSize = i10 + j10;
                            int k4;
                            if (k9 == 2)
                            {
                                k9 = parsecontext3.readId();
                                k4 = l2;
                                if (k9 != 0)
                                {
                                    hprofclass2.staticValueIds[l2] = k9;
                                    hprofclass2.staticFieldNamePositions[l2] = intintmap3.values[intintmap3.findKeyIndex(j8)];
                                    k4 = l2 + 1;
                                }
                            } else
                            {
                                int k8 = parsecontext3.typeSizes[k9];
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
                                parsecontext3.skipBytes(k8);
                                k4 = l2;
                            }
                            l++;
                            l2 = k4;
                        }
                        int l4;
                        if (l2 == i7)
                        {
                            obj2 = hprofclass2.staticValueIds;
                        } else
                        {
                            obj2 = Arrays.copyOf(hprofclass2.staticValueIds, l2);
                        }
                        hprofclass2.staticValueIds = ((int []) (obj2));
                        if (l2 == i7)
                        {
                            obj2 = hprofclass2.staticFieldNamePositions;
                        } else
                        {
                            obj2 = Arrays.copyOf(hprofclass2.staticFieldNamePositions, l2);
                        }
                        hprofclass2.staticFieldNamePositions = ((int []) (obj2));
                        obj2 = parsecontext3.buffer;
                        i7 = ((ByteBuffer) (obj2)).getShort() & 0xffff;
                        hprofclass2.fieldNamePositions = new int[i7];
                        hprofclass2.offsets = new int[i7];
                        hprofclass2.totalOffset = 0;
                        l = 0;
                        for (l4 = 0; l < i7; l4 = l2)
                        {
                            int l8 = parsecontext3.readId();
                            int l9 = ((ByteBuffer) (obj2)).get();
                            l2 = l4;
                            if (l9 == 2)
                            {
                                hprofclass2.fieldNamePositions[l4] = intintmap3.values[intintmap3.findKeyIndex(l8)];
                                hprofclass2.offsets[l4] = hprofclass2.totalOffset;
                                l2 = l4 + 1;
                            }
                            l8 = hprofclass2.totalOffset;
                            l9 = parsecontext3.typeSizes[l9];
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
                            hprofclass2.totalOffset = l8 + l9;
                            l++;
                        }

                        int ai[];
                        if (l4 == i7)
                        {
                            ai = hprofclass2.fieldNamePositions;
                        } else
                        {
                            ai = Arrays.copyOf(hprofclass2.fieldNamePositions, l4);
                        }
                        hprofclass2.fieldNamePositions = ai;
                        if (l4 == i7)
                        {
                            ai = hprofclass2.offsets;
                        } else
                        {
                            ai = Arrays.copyOf(hprofclass2.offsets, l4);
                        }
                        hprofclass2.offsets = ai;
                    }
                    break;

                case 33: // '!'
                    int i1 = hprofparser.buffer.position();
                    int i3 = hprofparser.parseContext.readId();
                    hprofparser.buffer.getInt();
                    int i5 = hprofparser.parseContext.readId();
                    int j7 = hprofparser.buffer.getInt();
                    Object obj3 = hprofparser.classes;
                    Object obj11 = ((IntObjectMap) (obj3)).values[((IntObjectMap) (obj3)).findKeyIndex(i5)];
                    obj3 = obj11;
                    if (obj11 == IntObjectMap.DELETED)
                    {
                        obj3 = null;
                    }
                    Object obj17 = (HprofClass)obj3;
                    obj3 = hprofparser.id2Actions;
                    obj11 = ((IntObjectMap) (obj3)).values[((IntObjectMap) (obj3)).findKeyIndex(i5)];
                    obj3 = obj11;
                    if (obj11 == IntObjectMap.DELETED)
                    {
                        obj3 = null;
                    }
                    obj3 = (com.google.android.libraries.performance.primes.hprof.ion)obj3;
                    if (obj17 != null && obj3 != com.google.android.libraries.performance.primes.hprof.ion.EXCLUDE_INSTANCE)
                    {
                        HprofClassInstance hprofclassinstance = new HprofClassInstance(i1, ((HprofClass) (obj17)));
                        hprofclassinstance.heapName = hprofparser.heapName;
                        hprofparser.instances.putIfAbsent(i3, hprofclassinstance);
                        if (obj3 == com.google.android.libraries.performance.primes.hprof.ion.FIND_INSTANCE)
                        {
                            obj17 = hprofparser.parseContext.readString(((HprofClass) (obj17)).classNamePosition);
                            List list = (List)hprofparser.instancesFound.get(obj17);
                            Object obj4 = list;
                            if (list == null)
                            {
                                obj4 = new ArrayList();
                                hprofparser.instancesFound.put(obj17, obj4);
                            }
                            ((List) (obj4)).add(hprofclassinstance);
                        }
                    }
                    hprofparser.parseContext.skipBytes(j7);
                    break;

                case 34: // '"'
                    int j3 = hprofparser.buffer.position();
                    int j5 = hprofparser.parseContext.readId();
                    hprofparser.buffer.getInt();
                    int k7 = hprofparser.buffer.getInt();
                    int i9 = hprofparser.parseContext.readId();
                    Object obj5 = hprofparser.id2Actions;
                    Object obj12 = ((IntObjectMap) (obj5)).values[((IntObjectMap) (obj5)).findKeyIndex(i9)];
                    obj5 = obj12;
                    if (obj12 == IntObjectMap.DELETED)
                    {
                        obj5 = null;
                    }
                    com.google.android.libraries.performance.primes.hprof.ion ion = (com.google.android.libraries.performance.primes.hprof.ion)obj5;
                    obj5 = hprofparser.classes;
                    obj12 = ((IntObjectMap) (obj5)).values[((IntObjectMap) (obj5)).findKeyIndex(i9)];
                    obj5 = obj12;
                    if (obj12 == IntObjectMap.DELETED)
                    {
                        obj5 = null;
                    }
                    boolean flag;
                    if (obj5 != null && obj5 != IntObjectMap.DELETED)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (flag && ion != com.google.android.libraries.performance.primes.hprof.ion.EXCLUDE_INSTANCE)
                    {
                        Object obj6 = hprofparser.classes;
                        Object obj13 = ((IntObjectMap) (obj6)).values[((IntObjectMap) (obj6)).findKeyIndex(i9)];
                        obj6 = obj13;
                        if (obj13 == IntObjectMap.DELETED)
                        {
                            obj6 = null;
                        }
                        obj6 = new HprofArrayInstance(j3, (HprofClass)obj6);
                        obj6.heapName = hprofparser.heapName;
                        hprofparser.instances.putIfAbsent(j5, obj6);
                    }
                    hprofparser.parseContext.skipBytes(hprofparser.parseContext.idSize * k7);
                    break;

                case 35: // '#'
                case 195: 
                    int k3 = hprofparser.buffer.position();
                    int k5 = hprofparser.parseContext.readId();
                    hprofparser.buffer.getInt();
                    int l7 = hprofparser.buffer.getInt();
                    byte byte0 = hprofparser.buffer.get();
                    Object obj7 = hprofparser.type2Actions;
                    Object obj14 = ((IntObjectMap) (obj7)).values[((IntObjectMap) (obj7)).findKeyIndex(byte0)];
                    obj7 = obj14;
                    if (obj14 == IntObjectMap.DELETED)
                    {
                        obj7 = null;
                    }
                    obj7 = (com.google.android.libraries.performance.primes.hprof.ion)obj7;
                    obj14 = hprofparser.parseContext;
                    int j9 = hprofparser.parseContext.typeSizes[byte0];
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
                    ((ParseContext) (obj14)).skipBytes(l7 * j9);
                    if (obj7 != com.google.android.libraries.performance.primes.hprof.ion.EXCLUDE_INSTANCE)
                    {
                        HprofPrimitiveArrayInstance hprofprimitivearrayinstance = new HprofPrimitiveArrayInstance(k3);
                        hprofprimitivearrayinstance.heapName = hprofparser.heapName;
                        hprofparser.instances.putIfAbsent(k5, hprofprimitivearrayinstance);
                    }
                    break;

                case 254: 
                    hprofparser.buffer.getInt();
                    int j1 = hprofparser.parseContext.readId();
                    ParseContext parsecontext = hprofparser.parseContext;
                    IntIntMap intintmap2 = hprofparser.stringPositions;
                    hprofparser.heapName = parsecontext.readString(intintmap2.values[intintmap2.findKeyIndex(j1)]);
                    break;
                }
            }
        } while (true);
        boolean flag2;
        if (hprofparser.buffer.position() == i6)
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
        Object obj8 = hprofparser.classes;
        for (obj8 = new com.google.android.libraries.performance.primes.hprof.collect.tor(((IntObjectMap) (obj8)).keys, ((IntObjectMap) (obj8)).values); ((com.google.android.libraries.performance.primes.hprof.collect.tor) (obj8)).next(); ((HprofClass)((com.google.android.libraries.performance.primes.hprof.collect.tor) (obj8)).value).resolveSuperClasses()) { }
        ArrayList arraylist = new ArrayList();
        obj8 = hprofparser.rootIds;
        com.google.android.libraries.performance.primes.hprof.collect.  = new com.google.android.libraries.performance.primes.hprof.collect.(((IntIntMap) (obj8)).keys, ((IntIntMap) (obj8)).values, ((IntIntMap) (obj8)).emptyValue);
        do
        {
            int ai1[];
            int k1;
            for (.value = .emptyValue; .value == .emptyValue && .nextIndex < .values.length; .value = ai1[k1])
            {
                ai1 = .values;
                k1 = .nextIndex;
                .nextIndex = k1 + 1;
            }

            if (.nextIndex > 0)
            {
                .key = .keys[.nextIndex - 1];
            }
            if (.value == .emptyValue)
            {
                break;
            }
            int l1 = .key;
            ai1 = hprofparser.classes;
            Object obj15 = ((IntObjectMap) (ai1)).values[ai1.findKeyIndex(l1)];
            ai1 = ((int []) (obj15));
            if (obj15 == IntObjectMap.DELETED)
            {
                ai1 = null;
            }
            obj15 = (HprofObject)ai1;
            ai1 = ((int []) (obj15));
            if (obj15 == null)
            {
                ai1 = hprofparser.instances;
                Object obj16 = ((IntObjectMap) (ai1)).values[ai1.findKeyIndex(l1)];
                ai1 = ((int []) (obj16));
                if (obj16 == IntObjectMap.DELETED)
                {
                    ai1 = null;
                }
                ai1 = (HprofObject)ai1;
                if (ai1 == null)
                {
                    continue;
                }
            }
            ai1.flags = ((HprofObject) (ai1)).flags | 1;
            ai1.rootTag = .value;
            arraylist.add(ai1);
        } while (true);
        hprofparser.stringPositions.init();
        hprofparser.id2Actions.init();
        ai1 = new ParseResult(hprofparser.classes, hprofparser.instances, arraylist, hprofparser.instancesFound);
        HprofSerializer.trim(ai1, parsecontext1);
        return hprofserializer.serialize(ai1, parsecontext1);
    }

    r()
    {
        this$0 = final_heapdumpprocessor;
        val$hprofFile = File.this;
        super();
    }
}
