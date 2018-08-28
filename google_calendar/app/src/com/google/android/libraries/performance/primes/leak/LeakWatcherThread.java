// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.libraries.performance.primes.leak;

import android.os.Debug;
import android.support.v4.util.SparseArrayCompat;
import com.android.ahat.dominators.DominatorsComputation;
import com.google.android.libraries.performance.primes.PrimesLog;
import com.google.android.libraries.performance.primes.hprof.HprofAnalyzer;
import com.google.android.libraries.performance.primes.hprof.HprofArrayInstance;
import com.google.android.libraries.performance.primes.hprof.HprofClass;
import com.google.android.libraries.performance.primes.hprof.HprofClassInstance;
import com.google.android.libraries.performance.primes.hprof.HprofObject;
import com.google.android.libraries.performance.primes.hprof.HprofParser;
import com.google.android.libraries.performance.primes.hprof.HprofPrimitiveArrayInstance;
import com.google.android.libraries.performance.primes.hprof.HprofTraverser;
import com.google.android.libraries.performance.primes.hprof.ParseContext;
import com.google.android.libraries.performance.primes.hprof.ParseResult;
import com.google.android.libraries.performance.primes.hprof.SuperRoot;
import com.google.android.libraries.performance.primes.hprof.collect.IntIntMap;
import com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap;
import com.google.android.libraries.performance.primes.hprof.collect.TrieMap;
import java.io.File;
import java.lang.ref.ReferenceQueue;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Referenced classes of package com.google.android.libraries.performance.primes.leak:
//            GarbageReference, LeakListener, LeakInfo

public final class LeakWatcherThread extends Thread
{

    private final Deque dummyQueue = new ArrayDeque(20);
    private final Deque garbageListQueue = new ArrayDeque(3);
    public File hprofFile;
    public final GarbageReference incomingList;
    private final LeakListener leakListener;
    public final GarbageReference queueForDump;
    public final GarbageReferenceFactory referenceFactory;
    public final ReferenceQueue referenceQueue;

    LeakWatcherThread(ReferenceQueue referencequeue, GarbageReferenceFactory garbagereferencefactory, LeakListener leaklistener)
    {
        boolean flag = false;
        super();
        setName("Primes-Watcher");
        referenceQueue = referencequeue;
        leakListener = leaklistener;
        referenceFactory = garbagereferencefactory;
        incomingList = new GarbageReference("Sentinel", "Sentinel", referencequeue);
        queueForDump = new GarbageReference("Sentinel", "Sentinel", referencequeue);
        int i = 0;
        int j;
        do
        {
            j = ((flag) ? 1 : 0);
            if (i >= 20)
            {
                break;
            }
            dummyQueue.add(new Object());
            i++;
        } while (true);
        for (; j < 3; j++)
        {
            garbageListQueue.add(new GarbageReference("Sentinel", "Sentinel", referencequeue));
        }

    }

    private final void advanceListQueue()
    {
        int i = 0;
        GarbageReference garbagereference3 = (GarbageReference)garbageListQueue.poll();
        GarbageReference garbagereference;
        int j;
        boolean flag;
        if (garbagereference3.next != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        garbagereference = queueForDump.next;
        do
        {
            j = i;
            if (garbagereference == null)
            {
                break;
            }
            garbagereference = garbagereference.next;
            i++;
        } while (true);
        do
        {
            if (garbagereference3.next == null)
            {
                break;
            }
            GarbageReference garbagereference1 = garbagereference3.next.removeSelf();
            leakListener.onLeaked(garbagereference1.name);
            if (j < 500)
            {
                garbagereference1.insertAfter(queueForDump);
                j++;
            }
        } while (true);
        garbageListQueue.offer(garbagereference3);
        synchronized (incomingList)
        {
            if (incomingList.next != null)
            {
                garbagereference3.next = incomingList.next;
                garbagereference3.next.prev = garbagereference3;
                incomingList.next = null;
            }
        }
        leakListener.onBatchComplete(flag);
        return;
        exception;
        garbagereference2;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private final void dumpAndAnalyzeHeap()
    {
        GarbageReference garbagereference;
        boolean flag;
        if (hprofFile != null)
        {
            flag = true;
        } else
        {
            flag = false;
        }
        if (!flag)
        {
            throw new IllegalStateException();
        }
        if (hprofFile.exists())
        {
            PrimesLog.log(3, "LeakWatcherThread", "Abort dumping heap because heapdump file %s exists", new Object[] {
                hprofFile.getName()
            });
            hprofFile = null;
            return;
        }
        garbagereference = new GarbageReference("Sentinel", "Sentinel", referenceQueue);
        synchronized (incomingList)
        {
            garbagereference.insertAfter(incomingList);
            incomingList.next = null;
            garbagereference.prev = null;
        }
        Object obj2;
        ParseContext parsecontext;
        Object obj3;
        System.nanoTime();
        Debug.dumpHprofData(hprofFile.getAbsolutePath());
        System.nanoTime();
        obj = new HprofAnalyzer(hprofFile);
        obj2 = com/google/android/libraries/performance/primes/leak/GarbageReference.getName();
        parsecontext = ParseContext.prepareContext(((HprofAnalyzer) (obj)).hprofFile);
        obj = Collections.singleton(obj2);
        obj3 = new HprofParser(parsecontext, HprofAnalyzer.NON_LEAK_ROOT_TAGS, Collections.emptyList(), ((Iterable) (obj)));
_L9:
        if (!((HprofParser) (obj3)).buffer.hasRemaining()) goto _L2; else goto _L1
_L1:
        int i;
        i = ((HprofParser) (obj3)).buffer.get();
        ((HprofParser) (obj3)).buffer.getInt();
        if (((HprofParser) (obj3)).buffer.getInt(((HprofParser) (obj3)).buffer.position()) < 0)
        {
            throw new RuntimeException("Length too large to parse.");
        }
          goto _L3
        obj;
        PrimesLog.log(3, "LeakWatcherThread", ((Throwable) (obj)), "Failed to analyze dump", new Object[0]);
        obj = incomingList;
        obj;
        JVM INSTR monitorenter ;
        while (garbagereference.next != null) 
        {
            garbagereference.next.removeSelf().insertAfter(incomingList);
        }
          goto _L4
        Exception exception;
        exception;
        obj;
        JVM INSTR monitorexit ;
        throw exception;
        obj;
        File file = hprofFile;
        hprofFile = null;
        file.delete();
        throw obj;
        exception1;
        obj;
        JVM INSTR monitorexit ;
        throw exception1;
_L3:
        i;
        JVM INSTR lookupswitch 4: default 364
    //                   1: 387
    //                   2: 452
    //                   12: 744
    //                   28: 744;
           goto _L5 _L6 _L7 _L8 _L8
_L5:
        i = ((HprofParser) (obj3)).buffer.getInt();
        ((HprofParser) (obj3)).parseContext.skipBytes(i);
          goto _L9
_L6:
        i = ((HprofParser) (obj3)).buffer.position();
        int j = ((HprofParser) (obj3)).buffer.getInt();
        int l = ((HprofParser) (obj3)).parseContext.readId();
        ((HprofParser) (obj3)).stringPositions.putIfAbsent(l, i);
        ((HprofParser) (obj3)).parseContext.skipBytes(j - ((HprofParser) (obj3)).parseContext.idSize);
          goto _L9
_L7:
        Object obj1;
        Object obj4;
        int k;
        int i1;
        int j1;
        int k1;
        ((HprofParser) (obj3)).buffer.getInt();
        ((HprofParser) (obj3)).buffer.getInt();
        k = ((HprofParser) (obj3)).buffer.position();
        i1 = ((HprofParser) (obj3)).parseContext.readId();
        ((HprofParser) (obj3)).buffer.getInt();
        obj = ((HprofParser) (obj3)).stringPositions;
        i = ((HprofParser) (obj3)).parseContext.readId();
        i = ((IntIntMap) (obj)).values[((IntIntMap) (obj)).findKeyIndex(i)];
        obj1 = new HprofClass(k, i);
        ((HprofParser) (obj3)).classes.putIfAbsent(i1, obj1);
        obj = ((HprofParser) (obj3)).parseContext;
        j1 = ((ParseContext) (obj)).buffer.getInt(i);
        k1 = ((ParseContext) (obj)).idSize;
        k = ((HprofParser) (obj3)).parseContext.idSize + (i + 4);
        obj = ((HprofParser) (obj3)).actionsForClass;
        obj4 = ((HprofParser) (obj3)).buffer;
        obj = ((TrieMap) (obj)).head;
        i = k;
_L111:
        if (i >= (j1 - k1) + k) goto _L11; else goto _L10
_L10:
        int l1 = ((ByteBuffer) (obj4)).get(i);
        if (((com.google.android.libraries.performance.primes.hprof.collect.TrieMap.Edge) (obj)).edges == null) goto _L13; else goto _L12
_L12:
        obj = (com.google.android.libraries.performance.primes.hprof.collect.TrieMap.Edge)((com.google.android.libraries.performance.primes.hprof.collect.TrieMap.Edge) (obj)).edges.get(l1);
        if (obj != null) goto _L14; else goto _L13
_L17:
        obj = (com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction)obj;
        if (obj != com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction.IDENTIFY_OBJECT_CLASS) goto _L16; else goto _L15
_L15:
        obj3.objectClassId = i1;
          goto _L9
_L11:
        obj = ((com.google.android.libraries.performance.primes.hprof.collect.TrieMap.Edge) (obj)).value;
          goto _L17
_L16:
        if (obj != com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction.IDENTIFY_JAVA_LANG_CLASS) goto _L19; else goto _L18
_L18:
        HprofClass.javaLangClass = ((HprofClass) (obj1));
          goto _L9
_L19:
        if (obj != com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction.CLASSIFY_REF) goto _L21; else goto _L20
_L20:
        obj1.flags = ((HprofClass) (obj1)).flags | 2;
          goto _L9
_L21:
        if (obj == null) goto _L9; else goto _L22
_L22:
        ((HprofParser) (obj3)).id2Actions.putIfAbsent(i1, obj);
          goto _L9
_L8:
        i = ((HprofParser) (obj3)).buffer.getInt();
        j1 = ((HprofParser) (obj3)).buffer.position() + i;
_L29:
        if (((HprofParser) (obj3)).buffer.position() >= j1) goto _L24; else goto _L23
_L23:
        i = ((HprofParser) (obj3)).buffer.get() & 0xff;
        obj = ((HprofParser) (obj3)).parseContext.rootTagSizes;
        if (((IntIntMap) (obj)).values[((IntIntMap) (obj)).findKeyIndex(i)] == ((IntIntMap) (obj)).emptyValue) goto _L26; else goto _L25
_L25:
        obj = ((HprofParser) (obj3)).parseContext.rootTagSizes;
        k = ((IntIntMap) (obj)).values[((IntIntMap) (obj)).findKeyIndex(i)];
        obj = ((HprofParser) (obj3)).rootTagsToExclude;
        if (((IntIntMap) (obj)).values[((IntIntMap) (obj)).findKeyIndex(i)] == ((IntIntMap) (obj)).emptyValue) goto _L28; else goto _L27
_L27:
        ((HprofParser) (obj3)).parseContext.skipBytes(k);
          goto _L29
_L28:
        i1 = ((HprofParser) (obj3)).parseContext.readId();
        ((HprofParser) (obj3)).rootIds.putIfAbsent(i1, i);
        ((HprofParser) (obj3)).parseContext.skipBytes(k - ((HprofParser) (obj3)).parseContext.idSize);
          goto _L29
_L112:
        throw new IllegalArgumentException((new StringBuilder(23)).append("Unknown tag ").append(i).toString());
_L113:
        i = ((HprofParser) (obj3)).parseContext.readId();
        if (i != ((HprofParser) (obj3)).objectClassId) goto _L31; else goto _L30
_L30:
        obj = ((HprofParser) (obj3)).classes;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        Object obj5;
        Object obj6;
        obj4 = (HprofClass)obj;
        obj5 = ((HprofParser) (obj3)).parseContext;
        obj = ((HprofParser) (obj3)).classes;
        obj6 = ((ParseContext) (obj5)).buffer;
        obj4.position = ((ByteBuffer) (obj6)).position() - ((ParseContext) (obj5)).idSize;
        ((ByteBuffer) (obj6)).getInt();
        i = ((ParseContext) (obj5)).readId();
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj4.superClass = (HprofClass)obj;
        ((ParseContext) (obj5)).skipBytes(((ParseContext) (obj5)).idSize * 5);
        obj4.instanceSize = ((ByteBuffer) (obj6)).getInt();
        HprofClass.skipClassConstants(((ParseContext) (obj5)));
        obj = ((ParseContext) (obj5)).buffer;
        i1 = ((ByteBuffer) (obj)).getShort();
        i = 0;
_L35:
        if (i >= (i1 & 0xffff))
        {
            break; /* Loop/switch isn't completed */
        }
        ((ParseContext) (obj5)).readId();
        k = ((ByteBuffer) (obj)).get();
        k1 = ((ParseContext) (obj5)).typeSizes[k];
        ByteBuffer bytebuffer;
        int i2;
        int j2;
        int k2;
        if (k1 > 0)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0) goto _L33; else goto _L32
_L32:
        throw new IllegalStateException();
_L33:
        ((ParseContext) (obj5)).skipBytes(k1);
        i++;
        if (true) goto _L35; else goto _L34
_L34:
        obj4.staticValueIds = new int[0];
        obj4.staticFieldNamePositions = new int[0];
        obj = ((ParseContext) (obj5)).buffer;
        i1 = ((ByteBuffer) (obj)).getShort();
        obj4.totalOffset = 0;
        i = 0;
_L39:
        if (i >= (i1 & 0xffff))
        {
            break; /* Loop/switch isn't completed */
        }
        ((ParseContext) (obj5)).readId();
        k = ((ByteBuffer) (obj)).get();
        k1 = ((HprofClass) (obj4)).totalOffset;
        l1 = ((ParseContext) (obj5)).typeSizes[k];
        if (l1 > 0)
        {
            k = 1;
        } else
        {
            k = 0;
        }
        if (k != 0) goto _L37; else goto _L36
_L36:
        throw new IllegalStateException();
_L37:
        obj4.totalOffset = k1 + l1;
        i++;
        if (true) goto _L39; else goto _L38
_L38:
        obj4.fieldNamePositions = new int[0];
        obj4.offsets = new int[0];
          goto _L29
_L31:
        obj = ((HprofParser) (obj3)).classes;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj4 = (HprofClass)obj;
        obj5 = ((HprofParser) (obj3)).parseContext;
        obj = ((HprofParser) (obj3)).classes;
        obj6 = ((HprofParser) (obj3)).stringPositions;
        bytebuffer = ((ParseContext) (obj5)).buffer;
        obj4.position = bytebuffer.position() - ((ParseContext) (obj5)).idSize;
        bytebuffer.getInt();
        i = ((ParseContext) (obj5)).readId();
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj4.superClass = (HprofClass)obj;
        ((ParseContext) (obj5)).skipBytes(((ParseContext) (obj5)).idSize * 5);
        obj4.instanceSize = bytebuffer.getInt();
        HprofClass.skipClassConstants(((ParseContext) (obj5)));
        obj = ((ParseContext) (obj5)).buffer;
        k1 = ((ByteBuffer) (obj)).getShort() & 0xffff;
        obj4.staticValueIds = new int[k1];
        obj4.staticFieldNamePositions = new int[k1];
        k = 0;
        i = 0;
_L118:
        if (i >= k1) goto _L41; else goto _L40
_L40:
        l1 = ((ParseContext) (obj5)).readId();
        i2 = ((ByteBuffer) (obj)).get();
        j2 = ((HprofClass) (obj4)).staticFieldsSize;
        k2 = ((ParseContext) (obj5)).typeSizes[i2];
        if (k2 > 0)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 != 0) goto _L43; else goto _L42
_L42:
        throw new IllegalStateException();
_L43:
        obj4.staticFieldsSize = j2 + k2;
        if (i2 != 2) goto _L45; else goto _L44
_L44:
        i2 = ((ParseContext) (obj5)).readId();
        i1 = k;
        if (i2 == 0) goto _L47; else goto _L46
_L46:
        ((HprofClass) (obj4)).staticValueIds[k] = i2;
        ((HprofClass) (obj4)).staticFieldNamePositions[k] = ((IntIntMap) (obj6)).values[((IntIntMap) (obj6)).findKeyIndex(l1)];
        i1 = k + 1;
          goto _L47
_L45:
        l1 = ((ParseContext) (obj5)).typeSizes[i2];
        if (l1 > 0)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 != 0) goto _L49; else goto _L48
_L48:
        throw new IllegalStateException();
_L49:
        ((ParseContext) (obj5)).skipBytes(l1);
        i1 = k;
          goto _L47
_L41:
        if (k != k1) goto _L51; else goto _L50
_L50:
        obj = ((HprofClass) (obj4)).staticValueIds;
_L58:
        obj4.staticValueIds = ((int []) (obj));
        if (k != k1) goto _L53; else goto _L52
_L52:
        obj = ((HprofClass) (obj4)).staticFieldNamePositions;
_L59:
        obj4.staticFieldNamePositions = ((int []) (obj));
        obj = ((ParseContext) (obj5)).buffer;
        k1 = ((ByteBuffer) (obj)).getShort() & 0xffff;
        obj4.fieldNamePositions = new int[k1];
        obj4.offsets = new int[k1];
        i1 = 0;
        obj4.totalOffset = 0;
        i = 0;
_L60:
        if (i >= k1) goto _L55; else goto _L54
_L54:
        l1 = ((ParseContext) (obj5)).readId();
        i2 = ((ByteBuffer) (obj)).get();
        k = i1;
        if (i2 != 2)
        {
            break MISSING_BLOCK_LABEL_1859;
        }
        ((HprofClass) (obj4)).fieldNamePositions[i1] = ((IntIntMap) (obj6)).values[((IntIntMap) (obj6)).findKeyIndex(l1)];
        ((HprofClass) (obj4)).offsets[i1] = ((HprofClass) (obj4)).totalOffset;
        k = i1 + 1;
        l1 = ((HprofClass) (obj4)).totalOffset;
        i2 = ((ParseContext) (obj5)).typeSizes[i2];
        if (i2 > 0)
        {
            i1 = 1;
        } else
        {
            i1 = 0;
        }
        if (i1 != 0) goto _L57; else goto _L56
_L56:
        throw new IllegalStateException();
_L51:
        obj = Arrays.copyOf(((HprofClass) (obj4)).staticValueIds, k);
          goto _L58
_L53:
        obj = Arrays.copyOf(((HprofClass) (obj4)).staticFieldNamePositions, k);
          goto _L59
_L57:
        obj4.totalOffset = l1 + i2;
        i++;
        i1 = k;
          goto _L60
_L55:
        if (i1 != k1) goto _L62; else goto _L61
_L61:
        obj = ((HprofClass) (obj4)).fieldNamePositions;
_L65:
        obj4.fieldNamePositions = ((int []) (obj));
        if (i1 != k1) goto _L64; else goto _L63
_L63:
        obj = ((HprofClass) (obj4)).offsets;
_L66:
        obj4.offsets = ((int []) (obj));
          goto _L29
_L62:
        obj = Arrays.copyOf(((HprofClass) (obj4)).fieldNamePositions, i1);
          goto _L65
_L64:
        obj = Arrays.copyOf(((HprofClass) (obj4)).offsets, i1);
          goto _L66
_L114:
        i = ((HprofParser) (obj3)).buffer.position();
        k = ((HprofParser) (obj3)).parseContext.readId();
        ((HprofParser) (obj3)).buffer.getInt();
        i1 = ((HprofParser) (obj3)).parseContext.readId();
        k1 = ((HprofParser) (obj3)).buffer.getInt();
        obj = ((HprofParser) (obj3)).classes;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i1)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj4 = (HprofClass)obj;
        obj = ((HprofParser) (obj3)).id2Actions;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i1)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj = (com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction)obj;
        if (obj4 == null)
        {
            break MISSING_BLOCK_LABEL_2258;
        }
        if (obj == com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction.EXCLUDE_INSTANCE)
        {
            break MISSING_BLOCK_LABEL_2258;
        }
        obj5 = new HprofClassInstance(i, ((HprofClass) (obj4)));
        obj5.heapName = ((HprofParser) (obj3)).heapName;
        ((HprofParser) (obj3)).instances.putIfAbsent(k, obj5);
        if (obj != com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction.FIND_INSTANCE)
        {
            break MISSING_BLOCK_LABEL_2258;
        }
        obj4 = ((HprofParser) (obj3)).parseContext.readString(((HprofClass) (obj4)).classNamePosition);
        obj1 = (List)((HprofParser) (obj3)).instancesFound.get(obj4);
        obj = obj1;
        if (obj1 != null) goto _L68; else goto _L67
_L67:
        obj = new ArrayList();
        ((HprofParser) (obj3)).instancesFound.put(obj4, obj);
_L68:
        ((List) (obj)).add(obj5);
        ((HprofParser) (obj3)).parseContext.skipBytes(k1);
          goto _L29
_L115:
        k = ((HprofParser) (obj3)).buffer.position();
        i1 = ((HprofParser) (obj3)).parseContext.readId();
        ((HprofParser) (obj3)).buffer.getInt();
        k1 = ((HprofParser) (obj3)).buffer.getInt();
        l1 = ((HprofParser) (obj3)).parseContext.readId();
        obj = ((HprofParser) (obj3)).id2Actions;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(l1)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj4 = (com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction)obj;
        obj = ((HprofParser) (obj3)).classes;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(l1)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        if (obj == null) goto _L70; else goto _L69
_L69:
        if (obj == IntObjectMap.DELETED) goto _L70; else goto _L71
_L71:
        i = 1;
_L119:
        if (i == 0)
        {
            break MISSING_BLOCK_LABEL_2475;
        }
        if (obj4 == com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction.EXCLUDE_INSTANCE)
        {
            break MISSING_BLOCK_LABEL_2475;
        }
        obj = ((HprofParser) (obj3)).classes;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(l1)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj = new HprofArrayInstance(k, (HprofClass)obj);
        obj.heapName = ((HprofParser) (obj3)).heapName;
        ((HprofParser) (obj3)).instances.putIfAbsent(i1, obj);
        ((HprofParser) (obj3)).parseContext.skipBytes(((HprofParser) (obj3)).parseContext.idSize * k1);
          goto _L29
_L116:
        k = ((HprofParser) (obj3)).buffer.position();
        i1 = ((HprofParser) (obj3)).parseContext.readId();
        ((HprofParser) (obj3)).buffer.getInt();
        k1 = ((HprofParser) (obj3)).buffer.getInt();
        i = ((HprofParser) (obj3)).buffer.get();
        obj = ((HprofParser) (obj3)).type2Actions;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj = (com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction)obj;
        obj1 = ((HprofParser) (obj3)).parseContext;
        l1 = ((HprofParser) (obj3)).parseContext.typeSizes[i];
        if (l1 > 0)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L73; else goto _L72
_L72:
        throw new IllegalStateException();
_L73:
        ((ParseContext) (obj1)).skipBytes(k1 * l1);
        if (obj != com.google.android.libraries.performance.primes.hprof.HprofParser.ParseAction.EXCLUDE_INSTANCE)
        {
            obj = new HprofPrimitiveArrayInstance(k);
            obj.heapName = ((HprofParser) (obj3)).heapName;
            ((HprofParser) (obj3)).instances.putIfAbsent(i1, obj);
        }
          goto _L29
_L117:
        ((HprofParser) (obj3)).buffer.getInt();
        i = ((HprofParser) (obj3)).parseContext.readId();
        obj = ((HprofParser) (obj3)).parseContext;
        obj1 = ((HprofParser) (obj3)).stringPositions;
        obj3.heapName = ((ParseContext) (obj)).readString(((IntIntMap) (obj1)).values[((IntIntMap) (obj1)).findKeyIndex(i)]);
          goto _L29
_L24:
        if (((HprofParser) (obj3)).buffer.position() == j1)
        {
            i = 1;
        } else
        {
            i = 0;
        }
        if (i != 0) goto _L9; else goto _L74
_L74:
        throw new IllegalStateException();
_L2:
        obj = ((HprofParser) (obj3)).classes;
        for (obj = new com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator(((IntObjectMap) (obj)).keys, ((IntObjectMap) (obj)).values); ((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).next(); ((HprofClass)((com.google.android.libraries.performance.primes.hprof.collect.IntObjectMap.Enumerator) (obj)).value).resolveSuperClasses()) { }
        obj4 = new ArrayList();
        obj = ((HprofParser) (obj3)).rootIds;
        obj5 = new com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator(((IntIntMap) (obj)).keys, ((IntIntMap) (obj)).values, ((IntIntMap) (obj)).emptyValue);
_L79:
        for (obj5.value = ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).emptyValue; ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).value == ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).emptyValue && ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).nextIndex < ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).values.length; obj5.value = obj[i])
        {
            obj = ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).values;
            i = ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).nextIndex;
            obj5.nextIndex = i + 1;
        }

        if (((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).nextIndex > 0)
        {
            obj5.key = ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).keys[((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).nextIndex - 1];
        }
        if (((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).value == ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).emptyValue) goto _L76; else goto _L75
_L75:
        i = ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).key;
        obj = ((HprofParser) (obj3)).classes;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj1 = (HprofObject)obj;
        obj = obj1;
        if (obj1 != null) goto _L78; else goto _L77
_L77:
        obj = ((HprofParser) (obj3)).instances;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj = (HprofObject)obj;
        if (obj == null) goto _L79; else goto _L78
_L78:
        obj.flags = ((HprofObject) (obj)).flags | 1;
        obj.rootTag = ((com.google.android.libraries.performance.primes.hprof.collect.IntIntMap.Enumerator) (obj5)).value;
        ((List) (obj4)).add(obj);
          goto _L79
_L76:
        ((HprofParser) (obj3)).stringPositions.init();
        ((HprofParser) (obj3)).id2Actions.init();
        obj3 = new ParseResult(((HprofParser) (obj3)).classes, ((HprofParser) (obj3)).instances, ((List) (obj4)), ((HprofParser) (obj3)).instancesFound);
        obj = (List)((ParseResult) (obj3)).instancesFound.get(obj2);
        if (obj != null) goto _L81; else goto _L80
_L80:
        obj = Collections.emptyList();
_L99:
        if (!((List) (obj)).isEmpty())
        {
            leakListener.onHeapDumpResult(((List) (obj)));
        }
        for (obj = garbageListQueue.iterator(); ((Iterator) (obj)).hasNext(); ((GarbageReference)((Iterator) (obj)).next()).removeSelf()) { }
          goto _L82
_L81:
        obj2 = new ArrayList();
        obj4 = ((List) (obj)).iterator();
_L92:
        if (!((Iterator) (obj4)).hasNext()) goto _L84; else goto _L83
_L83:
        obj = (HprofObject)((Iterator) (obj4)).next();
        if ("referent" != null) goto _L86; else goto _L85
_L85:
        throw new NullPointerException();
_L86:
        k = ((HprofObject) (obj)).getChildCount(parsecontext);
        i = 0;
_L120:
        if (i >= k) goto _L88; else goto _L87
_L87:
        if (!"referent".equals(((HprofObject) (obj)).getChildName(parsecontext, i))) goto _L90; else goto _L89
_L89:
        i = ((HprofObject) (obj)).getChildValue(parsecontext, i);
_L121:
        obj = ((ParseResult) (obj3)).classInstances;
        obj1 = ((IntObjectMap) (obj)).values[((IntObjectMap) (obj)).findKeyIndex(i)];
        obj = obj1;
        if (obj1 == IntObjectMap.DELETED)
        {
            obj = null;
        }
        obj1 = (HprofObject)obj;
        if (obj1 == null) goto _L92; else goto _L91
_L91:
        obj = "";
        if (!(obj1 instanceof HprofClassInstance)) goto _L94; else goto _L93
_L93:
        obj = parsecontext.readString(((HprofClassInstance)obj1).clazz.classNamePosition);
_L96:
        if (!HprofAnalyzer.NON_LEAK_CONTAINER.contains(obj))
        {
            ((List) (obj2)).add(obj1);
        }
          goto _L92
_L94:
        if (!(obj1 instanceof HprofArrayInstance)) goto _L96; else goto _L95
_L95:
        obj = parsecontext.readString(((HprofArrayInstance)obj1).clazz.classNamePosition);
          goto _L96
_L84:
        if (!((List) (obj2)).isEmpty()) goto _L98; else goto _L97
_L97:
        obj = Collections.emptyList();
          goto _L99
_L98:
        HprofObject.parseContext = parsecontext;
        HprofObject.parseResult = ((ParseResult) (obj3));
        DominatorsComputation.computeDominators(new SuperRoot(((ParseResult) (obj3)).roots));
        HprofObject.parseContext = null;
        HprofObject.parseResult = null;
        HprofTraverser.bfs(parsecontext, ((ParseResult) (obj3)).classInstances, ((ParseResult) (obj3)).classes, HprofTraverser.getRootsQueue(((ParseResult) (obj3)).roots), new com.google.android.libraries.performance.primes.hprof.HprofTraverser._cls1());
        obj1 = new ArrayList();
        obj3 = ((List) (obj2)).iterator();
_L103:
        if (!((Iterator) (obj3)).hasNext()) goto _L101; else goto _L100
_L100:
        obj2 = (HprofObject)((Iterator) (obj3)).next();
        if (((HprofObject) (obj2)).parent == null || !(obj2 instanceof HprofClassInstance)) goto _L103; else goto _L102
_L102:
        obj4 = new StringBuilder();
        ((StringBuilder) (obj4)).append(((HprofObject) (obj2)).buildLeakSegment(parsecontext, -1));
        obj = obj2;
_L110:
        if (((HprofObject) (obj)).parent == null) goto _L105; else goto _L104
_L104:
        ((StringBuilder) (obj4)).append('\n');
        obj5 = ((HprofObject) (obj)).parent;
        k = parsecontext.readId(((HprofObject) (obj)).position);
        i1 = ((HprofObject) (obj5)).getChildCount(parsecontext);
        i = 0;
_L122:
        if (i >= i1) goto _L107; else goto _L106
_L106:
        if (k != ((HprofObject) (obj5)).getChildValue(parsecontext, i)) goto _L109; else goto _L108
_L108:
        ((StringBuilder) (obj4)).append(((HprofObject) (obj)).parent.buildLeakSegment(parsecontext, i));
        obj = ((HprofObject) (obj)).parent;
          goto _L110
_L105:
        obj = ((StringBuilder) (obj4)).toString();
        HprofObject.computeRetainedSizes(((HprofObject) (obj2)), parsecontext);
        ((List) (obj1)).add(new LeakInfo(((String) (obj)), ((HprofObject) (obj2)).retainedHeapSize));
          goto _L103
_L82:
        queueForDump.removeSelf();
        obj = hprofFile;
        hprofFile = null;
        ((File) (obj)).delete();
        return;
_L4:
        obj;
        JVM INSTR monitorexit ;
        obj = hprofFile;
        hprofFile = null;
        ((File) (obj)).delete();
        return;
_L13:
        obj = null;
          goto _L17
_L14:
        i++;
          goto _L111
_L26:
        i;
        JVM INSTR lookupswitch 6: default 3780
    //                   32: 956
    //                   33: 2017
    //                   34: 2271
    //                   35: 2497
    //                   195: 2497
    //                   254: 2670;
           goto _L112 _L113 _L114 _L115 _L116 _L116 _L117
_L47:
        i++;
        k = i1;
          goto _L118
_L70:
        i = 0;
          goto _L119
_L90:
        i++;
          goto _L120
_L88:
        i = 0;
          goto _L121
_L109:
        i++;
          goto _L122
_L107:
        i = -1;
          goto _L108
_L101:
        obj = obj1;
          goto _L99
    }

    private final void readAndProcessQueueTillDummyCollected()
        throws InterruptedException
    {
        GarbageReference garbagereference;
        boolean flag;
        Object obj = dummyQueue.poll();
        dummyQueue.offer(new Object());
        garbagereference = referenceFactory.newReference(obj, "", referenceQueue);
        flag = false;
_L5:
        Object obj1;
        if (flag)
        {
            break; /* Loop/switch isn't completed */
        }
        obj1 = null;
_L2:
        if (obj1 != null)
        {
            break; /* Loop/switch isn't completed */
        }
        java.lang.ref.Reference reference = referenceQueue.remove();
        obj1 = reference;
        continue; /* Loop/switch isn't completed */
        InterruptedException interruptedexception;
        interruptedexception;
        if (hprofFile != null)
        {
            dumpAndAnalyzeHeap();
        } else
        {
            throw interruptedexception;
        }
        if (true) goto _L2; else goto _L1
_L1:
label0:
        {
            if (obj1 == null)
            {
                break MISSING_BLOCK_LABEL_164;
            }
            if (obj1 != garbagereference)
            {
                break label0;
            }
            if (!flag)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("Only one dummy released at a time."));
            }
        }
        flag = true;
_L3:
        obj1 = referenceQueue.poll();
          goto _L1
        obj1 = removeRef((GarbageReference)obj1);
        leakListener.onReleased(((String) (obj1)));
          goto _L3
        if (!flag)
        {
            leakListener.onBatchComplete(false);
        }
        if (true) goto _L5; else goto _L4
_L4:
    }

    private final String removeRef(GarbageReference garbagereference)
    {
        if (garbagereference.prev == incomingList)
        {
            synchronized (incomingList)
            {
                garbagereference.removeSelf();
            }
        } else
        {
            garbagereference.removeSelf();
        }
        return garbagereference.name;
        garbagereference;
        garbagereference1;
        JVM INSTR monitorexit ;
        throw garbagereference;
    }

    public final void run()
    {
        do
        {
            if (isInterrupted())
            {
                break;
            }
            try
            {
                Thread.sleep(5000L);
                readAndProcessQueueTillDummyCollected();
                advanceListQueue();
            }
            catch (InterruptedException interruptedexception)
            {
                interrupt();
                if (hprofFile != null)
                {
                    interrupted();
                    dumpAndAnalyzeHeap();
                }
            }
        } while (true);
        synchronized (incomingList)
        {
            incomingList.next = null;
        }
        dummyQueue.clear();
        garbageListQueue.clear();
        return;
        exception;
        garbagereference;
        JVM INSTR monitorexit ;
        throw exception;
    }

    private class GarbageReferenceFactory
    {

        public abstract GarbageReference newReference(Object obj, String s, ReferenceQueue referencequeue);
    }

}
