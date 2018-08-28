// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.apps.calendar.util.collect;

import com.google.common.base.FinalizableReferenceQueue;
import java.lang.ref.Reference;

// Referenced classes of package com.google.android.apps.calendar.util.collect:
//            ReferenceCache

public abstract class <init> extends Enum
{

    private static final SOFT $VALUES[];
    public static final SOFT SOFT;
    public static final SOFT WEAK;

    public static <init>[] values()
    {
        return (<init>[])$VALUES.clone();
    }

    public abstract Reference createReference(ReferenceCache referencecache, Object obj, Object obj1, FinalizableReferenceQueue finalizablereferencequeue);

    static 
    {
        class _cls1 extends ReferenceCache.Type
        {

            final Reference createReference(ReferenceCache referencecache, Object obj, final Object final_obj, final FinalizableReferenceQueue final_finalizablereferencequeue)
            {
                class _cls1 extends FinalizableWeakReference
                {

                    private final ReferenceCache val$cache;
                    private final Object val$key;

                    public final void finalizeReferent()
                    {
                        cache.keyToValueReferenceMap.remove(key);
                    }

                        _cls1(ReferenceCache referencecache, Object obj1)
                        {
                            cache = referencecache;
                            key = obj1;
                            super(final_obj, final_finalizablereferencequeue);
                        }
                }

                return new _cls1(referencecache, obj);
            }

            _cls1(String s, int i)
            {
                super(s, 0, (byte)0);
            }
        }

        WEAK = new _cls1("WEAK", 0);
        class _cls2 extends ReferenceCache.Type
        {

            final Reference createReference(ReferenceCache referencecache, Object obj, final Object final_obj, final FinalizableReferenceQueue final_finalizablereferencequeue)
            {
                class _cls1 extends FinalizableSoftReference
                {

                    private final ReferenceCache val$cache;
                    private final Object val$key;

                    public final void finalizeReferent()
                    {
                        cache.keyToValueReferenceMap.remove(key);
                    }

                        _cls1(ReferenceCache referencecache, Object obj1)
                        {
                            cache = referencecache;
                            key = obj1;
                            super(final_obj, final_finalizablereferencequeue);
                        }
                }

                return new _cls1(referencecache, obj);
            }

            _cls2(String s, int i)
            {
                super(s, 1, (byte)0);
            }
        }

        SOFT = new _cls2("SOFT", 1);
        $VALUES = (new .VALUES[] {
            WEAK, SOFT
        });
    }

    private _cls2(String s, int i)
    {
        super(s, i);
    }

    _cls2(String s, int i, byte byte0)
    {
        this(s, i);
    }
}
