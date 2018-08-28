// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.hprof;

import com.android.ahat.dominators.DominatorsComputation;
import com.google.android.libraries.performance.primes.hprof.collect.IntIntMap;
import com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap;
import com.google.android.libraries.performance.primes.hprof.collect.MergedEnumerable;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;
import logs.proto.wireless.performance.mobile.nano.ArrayInstance;
import logs.proto.wireless.performance.mobile.nano.ClassInfo;
import logs.proto.wireless.performance.mobile.nano.ClassInstance;
import logs.proto.wireless.performance.mobile.nano.PrimesHeapDump;
import logs.proto.wireless.performance.mobile.nano.PrimitiveArrayInstance;
import logs.proto.wireless.performance.mobile.nano.Root;

// Referenced classes of package com.google.android.libraries.performance.primes.hprof:
//            HprofObject, HprofClass, HprofClassInstance, HprofArrayInstance, 
//            HprofPrimitiveArrayInstance, ParseResult, HprofTraverser, ParseContext, 
//            HprofParser, SuperRoot

public final class HprofSerializer
{

    public HprofSerializer()
    {
    }

    private static int[] findChildrenIds(ParseContext parsecontext, IntIntMap intintmap, IntObjectMap intobjectmap, IntObjectMap intobjectmap1, HprofObject hprofobject, int i, int j, int k)
    {
        int ai[];
        int i1;
        int j1;
        ai = new int[hprofobject.getChildCount(parsecontext)];
        i1 = 0;
        j1 = 0;
_L10:
        if (i1 >= ai.length) goto _L2; else goto _L1
_L1:
        int l;
        int k1;
        k1 = hprofobject.getChildValue(parsecontext, i1);
        l = j1;
        if (k1 == 0) goto _L4; else goto _L3
_L3:
        Object obj;
        Object obj1 = intobjectmap.values[intobjectmap.findKeyIndex(k1)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj = (HprofObject)obj;
        if (obj == null)
        {
            Object obj2 = intobjectmap1.values[intobjectmap1.findKeyIndex(k1)];
            obj = obj2;
            if (obj2 == IntObjectMap.DELETED)
            {
                obj = null;
            }
            obj = (HprofObject)obj;
        }
        k1 = intintmap.values[intintmap.findKeyIndex(k1)];
        l = j1;
        if (obj == null) goto _L4; else goto _L5
_L5:
        l = j1;
        if (k1 == -1) goto _L4; else goto _L6
_L6:
        if (!(obj instanceof HprofClass)) goto _L8; else goto _L7
_L7:
        ai[j1] = k1 + 1;
        l = j1 + 1;
_L4:
        i1++;
        j1 = l;
        continue; /* Loop/switch isn't completed */
_L8:
        if (obj instanceof HprofClassInstance)
        {
            ai[j1] = k1 + i + 1;
            l = j1 + 1;
        } else
        if (obj instanceof HprofArrayInstance)
        {
            ai[j1] = k1 + i + j + 1;
            l = j1 + 1;
        } else
        {
            l = j1;
            if (obj instanceof HprofPrimitiveArrayInstance)
            {
                ai[j1] = k1 + i + j + k + 1;
                l = j1 + 1;
            }
        }
        if (true) goto _L4; else goto _L2
_L2:
        return Arrays.copyOf(ai, j1);
        if (true) goto _L10; else goto _L9
_L9:
    }

    public static void trim(ParseResult parseresult, ParseContext parsecontext)
    {
        HprofTraverser.bfs(parsecontext, parseresult.classInstances, parseresult.classes, HprofTraverser.getRootsQueue(parseresult.roots), new HprofTraverser._cls1());
        parsecontext = parseresult.classInstances;
        parsecontext = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (parsecontext)).keys, ((IntObjectMap) (parsecontext)).values);
        do
        {
            if (!parsecontext.next())
            {
                break;
            }
            if (((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).parent == null)
            {
                if ((((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).flags & 1) == 0)
                {
                    parseresult.classInstances.remove(((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).key);
                }
            } else
            if (((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).heapName.equals("app") && !(((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).parent instanceof HprofClass) && !((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).parent.heapName.equals("app"))
            {
                ((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).rootTag = 255;
                HprofObject hprofobject = (HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value;
                hprofobject.flags = hprofobject.flags | 1;
                parseresult.roots.add((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value);
            }
        } while (true);
        parsecontext = parseresult.classInstances;
        parsecontext = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (parsecontext)).keys, ((IntObjectMap) (parsecontext)).values);
        do
        {
            if (!parsecontext.next())
            {
                break;
            }
            if (!((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).heapName.equals("app"))
            {
                parseresult.classInstances.remove(((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).key);
            }
        } while (true);
        parseresult = parseresult.roots.iterator();
        do
        {
            if (!parseresult.hasNext())
            {
                break;
            }
            parsecontext = (HprofObject)parseresult.next();
            if (((HprofObject) (parsecontext)).heapName != null && !((HprofObject) (parsecontext)).heapName.equals("app"))
            {
                parseresult.remove();
            }
        } while (true);
    }

    public final PrimesHeapDump serialize(ParseResult parseresult, ParseContext parsecontext)
        throws IOException
    {
        ArrayList arraylist;
        IntIntMap intintmap;
        ArrayList arraylist1;
        ArrayList arraylist2;
        ArrayList arraylist3;
        IntObjectMap intobjectmap;
        Object obj2;
        arraylist = new ArrayList(parseresult.classes.size);
        intintmap = new IntIntMap();
        Object obj = parseresult.classes;
        ClassInfo classinfo;
        for (obj = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (obj)).keys, ((IntObjectMap) (obj)).values); ((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).next(); arraylist.add(classinfo))
        {
            HprofClass hprofclass = (HprofClass)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).value;
            intintmap.putIfAbsent(((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).key, arraylist.size());
            classinfo = new ClassInfo();
            classinfo.className = parsecontext.readString(hprofclass.classNamePosition);
            classinfo.instanceSize = Integer.valueOf(hprofclass.instanceSize);
        }

        obj = parseresult.classes;
        for (obj = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (obj)).keys, ((IntObjectMap) (obj)).values); ((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).next();)
        {
            HprofClass hprofclass1 = (HprofClass)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).value;
            int i = ((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).key;
            ClassInfo classinfo1 = (ClassInfo)arraylist.get(intintmap.values[intintmap.findKeyIndex(i)]);
            if (hprofclass1.superClass != null)
            {
                int j = parsecontext.readId(((HprofObject) (hprofclass1.superClass)).position);
                classinfo1.superClass = Integer.valueOf(intintmap.values[intintmap.findKeyIndex(j)] + 1);
            } else
            {
                classinfo1.superClass = Integer.valueOf(0);
            }
        }

        arraylist1 = new ArrayList();
        arraylist2 = new ArrayList();
        arraylist3 = new ArrayList();
        obj = parseresult.classInstances;
        obj = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (obj)).keys, ((IntObjectMap) (obj)).values);
        do
        {
            if (!((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).next())
            {
                break;
            }
            if (((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).value instanceof HprofClassInstance)
            {
                intintmap.putIfAbsent(((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).key, arraylist1.size());
                HprofClassInstance hprofclassinstance = (HprofClassInstance)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).value;
                ClassInstance classinstance = new ClassInstance();
                int k = parsecontext.readId(((HprofObject) (hprofclassinstance.clazz)).position);
                classinstance.clazz = Integer.valueOf(intintmap.values[intintmap.findKeyIndex(k)] + 1);
                arraylist1.add(classinstance);
            } else
            if (((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).value instanceof HprofArrayInstance)
            {
                intintmap.putIfAbsent(((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).key, arraylist2.size());
                HprofArrayInstance hprofarrayinstance = (HprofArrayInstance)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).value;
                ArrayInstance arrayinstance = new ArrayInstance();
                int l = parsecontext.readId(((HprofObject) (hprofarrayinstance.clazz)).position);
                arrayinstance.clazz = Integer.valueOf(intintmap.values[intintmap.findKeyIndex(l)] + 1);
                arraylist2.add(arrayinstance);
            } else
            if (((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).value instanceof HprofPrimitiveArrayInstance)
            {
                intintmap.putIfAbsent(((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).key, arraylist3.size());
                HprofPrimitiveArrayInstance hprofprimitivearrayinstance = (HprofPrimitiveArrayInstance)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).value;
                PrimitiveArrayInstance primitivearrayinstance = new PrimitiveArrayInstance();
                int i1 = hprofprimitivearrayinstance.position;
                int j2 = parsecontext.idSize;
                primitivearrayinstance.type = parsecontext.buffer.get(i1 + j2 + 4 + 4);
                primitivearrayinstance.numElements = Integer.valueOf(hprofprimitivearrayinstance.getChildCount(parsecontext));
                arraylist3.add(primitivearrayinstance);
            }
        } while (true);
        obj = parseresult.classes;
        intobjectmap = parseresult.classInstances;
        obj2 = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(intobjectmap.keys, intobjectmap.values);
        do
        {
            if (!((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).next())
            {
                break;
            }
            HprofObject hprofobject = (HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).value;
            if (!(hprofobject instanceof HprofPrimitiveArrayInstance))
            {
                int ai1[] = findChildrenIds(parsecontext, intintmap, intobjectmap, ((IntObjectMap) (obj)), (HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).value, arraylist.size(), arraylist1.size(), arraylist2.size());
                int j1 = ((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).key;
                j1 = intintmap.values[intintmap.findKeyIndex(j1)];
                if (j1 >= 0)
                {
                    if (hprofobject instanceof HprofClassInstance)
                    {
                        ((ClassInstance)arraylist1.get(j1)).values = ai1;
                    } else
                    if (hprofobject instanceof HprofArrayInstance)
                    {
                        ((ArrayInstance)arraylist2.get(j1)).values = ai1;
                    }
                }
            }
        } while (true);
        for (obj2 = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (obj)).keys, ((IntObjectMap) (obj)).values); ((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).next();)
        {
            int ai[] = findChildrenIds(parsecontext, intintmap, intobjectmap, ((IntObjectMap) (obj)), (HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).value, arraylist.size(), arraylist1.size(), arraylist2.size());
            int k1 = ((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).key;
            ((ClassInfo)arraylist.get(intintmap.values[intintmap.findKeyIndex(k1)])).values = ai;
        }

        intobjectmap = new IntObjectMap();
        obj2 = parseresult.roots.iterator();
_L2:
        HprofObject hprofobject1;
        int l1;
        int k2;
        if (!((Iterator) (obj2)).hasNext())
        {
            break MISSING_BLOCK_LABEL_1195;
        }
        hprofobject1 = (HprofObject)((Iterator) (obj2)).next();
        l1 = parsecontext.readId(hprofobject1.position);
        l1 = intintmap.values[intintmap.findKeyIndex(l1)];
        Object obj1;
        if (hprofobject1 instanceof HprofClass)
        {
            l1++;
        } else
        if (hprofobject1 instanceof HprofClassInstance)
        {
            l1 += arraylist.size() + 1;
        } else
        {
            if (!(hprofobject1 instanceof HprofArrayInstance))
            {
                continue; /* Loop/switch isn't completed */
            }
            l1 += arraylist.size() + 1 + arraylist1.size();
        }
_L5:
        k2 = hprofobject1.rootTag;
        obj1 = intobjectmap.values[intobjectmap.findKeyIndex(k2)];
        parseresult = ((ParseResult) (obj1));
        if (obj1 == IntObjectMap.DELETED)
        {
            parseresult = null;
        }
        if (parseresult != null && parseresult != IntObjectMap.DELETED)
        {
            k2 = 1;
        } else
        {
            k2 = 0;
        }
        if (k2 == 0)
        {
            intobjectmap.putIfAbsent(hprofobject1.rootTag, new ArrayList());
        }
        k2 = hprofobject1.rootTag;
        obj1 = intobjectmap.values[intobjectmap.findKeyIndex(k2)];
        parseresult = ((ParseResult) (obj1));
        if (obj1 == IntObjectMap.DELETED)
        {
            parseresult = null;
        }
        ((List)parseresult).add(Integer.valueOf(l1));
        if (true) goto _L2; else goto _L1
_L1:
        if (!(hprofobject1 instanceof HprofPrimitiveArrayInstance)) goto _L4; else goto _L3
_L3:
        break MISSING_BLOCK_LABEL_1156;
_L4:
        break; /* Loop/switch isn't completed */
        l1 += arraylist.size() + 1 + arraylist1.size() + arraylist2.size();
          goto _L5
        parseresult = new Root[intobjectmap.size];
        parsecontext = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(intobjectmap.keys, intobjectmap.values);
        for (int i2 = 0; parsecontext.next(); i2++)
        {
            Root root = new Root();
            root.tag = ((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).key;
            root.nodes = new int[((List)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).size()];
            for (int l2 = 0; l2 < root.nodes.length; l2++)
            {
                root.nodes[l2] = ((Integer)((List)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (parsecontext)).value).get(l2)).intValue();
            }

            parseresult[i2] = root;
        }

        parsecontext = new PrimesHeapDump();
        parsecontext.classInfo = (ClassInfo[])arraylist.toArray(new ClassInfo[arraylist.size()]);
        parsecontext.classInstance = (ClassInstance[])arraylist1.toArray(new ClassInstance[arraylist1.size()]);
        parsecontext.arrayInstance = (ArrayInstance[])arraylist2.toArray(new ArrayInstance[arraylist2.size()]);
        parsecontext.primitiveArrayInstance = (PrimitiveArrayInstance[])arraylist3.toArray(new PrimitiveArrayInstance[arraylist3.size()]);
        parsecontext.roots = parseresult;
        return parsecontext;
    }

    public final PrimesHeapDump serializeTopRooted(File file)
        throws IOException
    {
        ParseResult parseresult;
        file = ParseContext.prepareContext(file);
        parseresult = HprofParser.parseBuffer(file, Collections.emptyList(), Arrays.asList(new String[] {
            "java.lang.Class"
        }), Collections.emptyList());
        trim(parseresult, file);
        HprofObject.parseContext = file;
        HprofObject.parseResult = parseresult;
        SuperRoot superroot = new SuperRoot(parseresult.roots);
        DominatorsComputation.computeDominators(superroot);
        HprofObject.computeRetainedSizes(superroot, file);
        Object obj;
        HprofObject.parseContext = null;
        HprofObject.parseResult = null;
        obj = new PriorityQueue(10, new HprofGraphAnalyzer._cls1());
        Object obj1 = parseresult.classInstances;
        obj1 = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (obj1)).keys, ((IntObjectMap) (obj1)).values);
        IntObjectMap intobjectmap = parseresult.classes;
        obj1 = new MergedEnumerable(Arrays.asList(new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerable[] {
            obj1, new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(intobjectmap.keys, intobjectmap.values)
        }).iterator());
        do
        {
            if (!((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerable) (obj1)).next())
            {
                break;
            }
            HprofObject hprofobject = (HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerable) (obj1)).getValue();
            if (hprofobject.immediateDominator instanceof SuperRoot)
            {
                ((PriorityQueue) (obj)).offer(hprofobject);
                if (((PriorityQueue) (obj)).size() > 10)
                {
                    ((PriorityQueue) (obj)).poll();
                }
            }
        } while (true);
        break MISSING_BLOCK_LABEL_233;
        file;
        HprofObject.parseContext = null;
        HprofObject.parseResult = null;
        throw file;
        Object obj2;
        HprofObject hprofobject1;
        int i;
        Object obj4 = new ArrayList(((java.util.Collection) (obj)));
        obj = new HashSet();
        obj2 = parseresult.classInstances;
        obj2 = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (obj2)).keys, ((IntObjectMap) (obj2)).values);
        do
        {
            if (!((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).next())
            {
                break;
            }
            if ((((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).value instanceof HprofPrimitiveArrayInstance) && ((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).value).getChildCount(file) > 10000)
            {
                ((Set) (obj)).add((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj2)).value);
            }
        } while (true);
        obj2 = new ArrayDeque();
        for (obj4 = ((List) (obj4)).iterator(); ((Iterator) (obj4)).hasNext(); ((Deque) (obj2)).addLast((HprofObject)((Iterator) (obj4)).next())) { }
_L4:
        if (((Deque) (obj2)).isEmpty())
        {
            break; /* Loop/switch isn't completed */
        }
        hprofobject1 = (HprofObject)((Deque) (obj2)).removeFirst();
        i = 0;
        Iterator iterator1 = hprofobject1.immediatelyDominated.iterator();
        while (iterator1.hasNext()) 
        {
            HprofObject hprofobject4 = (HprofObject)iterator1.next();
            if ((double)hprofobject4.retainedHeapSize >= (double)hprofobject1.retainedHeapSize * 0.75D || hprofobject4.retainedHeapSize >= 0x100000)
            {
                ((Deque) (obj2)).addFirst(hprofobject4);
                i++;
            }
        }
        if (true) goto _L2; else goto _L1
_L1:
        break; /* Loop/switch isn't completed */
_L2:
        if (i == 0)
        {
            ((Set) (obj)).add(hprofobject1);
        }
        if (true) goto _L4; else goto _L3
_L3:
        Object obj3 = new ArrayList(((java.util.Collection) (obj)));
        obj = new IntObjectMap();
        for (Iterator iterator = ((List) (obj3)).iterator(); iterator.hasNext();)
        {
            HprofObject hprofobject5 = (HprofObject)iterator.next();
            ArrayDeque arraydeque1 = new ArrayDeque();
            arraydeque1.addLast(hprofobject5);
            hprofobject5.visited = true;
            while (!arraydeque1.isEmpty()) 
            {
                Object obj7 = (HprofObject)arraydeque1.removeFirst();
                boolean flag;
                if (!(obj7 instanceof HprofClass))
                {
                    flag = true;
                } else
                {
                    flag = false;
                }
                if (flag)
                {
                    ((IntObjectMap) (obj)).putIfAbsent(file.readId(((HprofObject) (obj7)).position), obj7);
                }
                obj7 = ((HprofObject) (obj7)).immediatelyDominated.iterator();
                while (((Iterator) (obj7)).hasNext()) 
                {
                    arraydeque1.addLast((HprofObject)((Iterator) (obj7)).next());
                }
            }
        }

        HprofTraverser.clearTraversal(parseresult.classInstances, parseresult.classes);
        HprofTraverser.bfs(file, parseresult.classInstances, parseresult.classes, HprofTraverser.getRootsQueue(parseresult.roots), new HprofTraverser._cls2());
        HprofTraverser.clearTraversal(parseresult.classInstances, parseresult.classes);
        for (obj3 = ((List) (obj3)).iterator(); ((Iterator) (obj3)).hasNext();)
        {
            HprofObject hprofobject2 = (HprofObject)((Iterator) (obj3)).next();
            ArrayDeque arraydeque = new ArrayDeque();
            arraydeque.addLast(hprofobject2);
            hprofobject2.visited = true;
            while (!arraydeque.isEmpty()) 
            {
                Object obj6 = (HprofObject)arraydeque.removeFirst();
                boolean flag1;
                if (!(obj6 instanceof HprofClass))
                {
                    flag1 = true;
                } else
                {
                    flag1 = false;
                }
                if (flag1)
                {
                    ((IntObjectMap) (obj)).putIfAbsent(file.readId(((HprofObject) (obj6)).position), obj6);
                }
                obj6 = ((HprofObject) (obj6)).parents.iterator();
                while (((Iterator) (obj6)).hasNext()) 
                {
                    HprofObject hprofobject6 = (HprofObject)((Iterator) (obj6)).next();
                    if (hprofobject6 != null && !hprofobject6.visited)
                    {
                        hprofobject6.visited = true;
                        arraydeque.addLast(hprofobject6);
                    }
                }
            }
        }

        obj3 = new ArrayList();
        Object obj5 = parseresult.roots.iterator();
        do
        {
            if (!((Iterator) (obj5)).hasNext())
            {
                break;
            }
            HprofObject hprofobject3 = (HprofObject)((Iterator) (obj5)).next();
            if (hprofobject3 instanceof HprofClass)
            {
                ((List) (obj3)).add(hprofobject3);
            }
        } while (true);
        obj5 = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (obj)).keys, ((IntObjectMap) (obj)).values);
        do
        {
            if (!((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj5)).next())
            {
                break;
            }
            boolean flag2;
            if ((((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj5)).value).flags & 1) != 0)
            {
                flag2 = true;
            } else
            {
                flag2 = false;
            }
            if (flag2)
            {
                ((List) (obj3)).add((HprofObject)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj5)).value);
            }
        } while (true);
        return serialize(new ParseResult(parseresult.classes, ((IntObjectMap) (obj)), ((List) (obj3)), parseresult.instancesFound), file);
    }
}
