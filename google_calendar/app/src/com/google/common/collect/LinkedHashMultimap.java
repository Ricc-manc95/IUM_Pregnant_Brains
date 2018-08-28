// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.common.collect;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

// Referenced classes of package com.google.common.collect:
//            LinkedHashMultimapGwtSerializationDependencies, CompactLinkedHashMap, CollectPreconditions, AbstractMapBasedMultimap, 
//            AbstractMultimap, AbstractSetMultimap, CompactLinkedHashSet

public final class LinkedHashMultimap extends LinkedHashMultimapGwtSerializationDependencies
{

    public static final long serialVersionUID = 1L;
    public transient ValueEntry multimapHeaderEntry;
    private transient int valueSetCapacity;

    public LinkedHashMultimap(int i, int j)
    {
        super(new CompactLinkedHashMap(16));
        valueSetCapacity = 2;
        CollectPreconditions.checkNonnegative(2, "expectedValuesPerKey");
        valueSetCapacity = 2;
        multimapHeaderEntry = new ValueEntry(null, null, 0, null);
        ValueEntry valueentry = multimapHeaderEntry;
        ValueEntry valueentry1 = multimapHeaderEntry;
        valueentry.successorInMultimap = valueentry1;
        valueentry1.predecessorInMultimap = valueentry;
    }

    private final void readObject(ObjectInputStream objectinputstream)
        throws IOException, ClassNotFoundException
    {
        objectinputstream.defaultReadObject();
        multimapHeaderEntry = new ValueEntry(null, null, 0, null);
        Object obj = multimapHeaderEntry;
        ValueEntry valueentry = multimapHeaderEntry;
        obj.successorInMultimap = valueentry;
        valueentry.predecessorInMultimap = ((ValueEntry) (obj));
        valueSetCapacity = 2;
        int k = objectinputstream.readInt();
        obj = new CompactLinkedHashMap(12);
        for (int i = 0; i < k; i++)
        {
            Object obj1 = objectinputstream.readObject();
            ((Map) (obj)).put(obj1, createCollection(obj1));
        }

        k = objectinputstream.readInt();
        for (int j = 0; j < k; j++)
        {
            Object obj2 = objectinputstream.readObject();
            Object obj3 = objectinputstream.readObject();
            ((Collection)((Map) (obj)).get(obj2)).add(obj3);
        }

        setMap(((Map) (obj)));
    }

    private final void writeObject(ObjectOutputStream objectoutputstream)
        throws IOException
    {
        objectoutputstream.defaultWriteObject();
        objectoutputstream.writeInt(keySet().size());
        for (Iterator iterator = keySet().iterator(); iterator.hasNext(); objectoutputstream.writeObject(iterator.next())) { }
        objectoutputstream.writeInt(size());
        java.util.Map.Entry entry;
        for (Iterator iterator1 = ((Set)entries()).iterator(); iterator1.hasNext(); objectoutputstream.writeObject(entry.getValue()))
        {
            entry = (java.util.Map.Entry)iterator1.next();
            objectoutputstream.writeObject(entry.getKey());
        }

    }

    public final volatile Map asMap()
    {
        return super.asMap();
    }

    public final void clear()
    {
        super.clear();
        ValueEntry valueentry = multimapHeaderEntry;
        ValueEntry valueentry1 = multimapHeaderEntry;
        valueentry.successorInMultimap = valueentry1;
        valueentry1.predecessorInMultimap = valueentry;
    }

    public final volatile boolean containsEntry(Object obj, Object obj1)
    {
        return super.containsEntry(obj, obj1);
    }

    final volatile Collection createCollection()
    {
        return createCollection();
    }

    final Collection createCollection(Object obj)
    {
        return new ValueSet(obj, valueSetCapacity);
    }

    final Set createCollection()
    {
        return new CompactLinkedHashSet(valueSetCapacity);
    }

    public final volatile Collection entries()
    {
        return entries();
    }

    public final Set entries()
    {
        return super.entries();
    }

    final Iterator entryIterator()
    {
        return new _cls1();
    }

    public final volatile boolean equals(Object obj)
    {
        return super.equals(obj);
    }

    public final volatile Set get(Object obj)
    {
        return super.get(obj);
    }

    public final volatile int hashCode()
    {
        return super.hashCode();
    }

    public final Set keySet()
    {
        return super.keySet();
    }

    public final volatile boolean put(Object obj, Object obj1)
    {
        return super.put(obj, obj1);
    }

    public final volatile boolean remove(Object obj, Object obj1)
    {
        return super.remove(obj, obj1);
    }

    public final volatile Set removeAll(Object obj)
    {
        return super.removeAll(obj);
    }

    public final volatile int size()
    {
        return super.size();
    }

    public final volatile String toString()
    {
        return super.toString();
    }

    private class ValueEntry extends ImmutableEntry
        implements ValueSetLink
    {

        public ValueEntry nextInValueBucket;
        public ValueEntry predecessorInMultimap;
        private ValueSetLink predecessorInValueSet;
        public final int smearedValueHash;
        public ValueEntry successorInMultimap;
        public ValueSetLink successorInValueSet;

        public final ValueSetLink getPredecessorInValueSet()
        {
            return predecessorInValueSet;
        }

        public final ValueSetLink getSuccessorInValueSet()
        {
            return successorInValueSet;
        }

        final boolean matchesValue(Object obj, int i)
        {
            if (smearedValueHash == i)
            {
                Object obj1 = getValue();
                if (obj1 == obj || obj1 != null && obj1.equals(obj))
                {
                    i = 1;
                } else
                {
                    i = 0;
                }
                if (i != 0)
                {
                    return true;
                }
            }
            return false;
        }

        public final void setPredecessorInValueSet(ValueSetLink valuesetlink)
        {
            predecessorInValueSet = valuesetlink;
        }

        public final void setSuccessorInValueSet(ValueSetLink valuesetlink)
        {
            successorInValueSet = valuesetlink;
        }

        ValueEntry(Object obj, Object obj1, int i, ValueEntry valueentry)
        {
            super(obj, obj1);
            smearedValueHash = i;
            nextInValueBucket = valueentry;
        }
    }


    private class ValueSet extends Sets.ImprovedAbstractSet
        implements ValueSetLink
    {

        public ValueSetLink firstEntry;
        private ValueEntry hashTable[];
        private final Object key;
        private ValueSetLink lastEntry;
        public int modCount;
        private int size;
        private final LinkedHashMultimap this$0;

        public final boolean add(Object obj)
        {
            boolean flag1 = false;
            ValueEntry valueentry1;
            int i;
            int l;
            if (obj == null)
            {
                i = 0;
            } else
            {
                i = obj.hashCode();
            }
            i = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
            l = i & hashTable.length - 1;
            valueentry1 = hashTable[l];
            for (ValueEntry valueentry = valueentry1; valueentry != null; valueentry = valueentry.nextInValueBucket)
            {
                if (valueentry.matchesValue(obj, i))
                {
                    return false;
                }
            }

            obj = new ValueEntry(key, obj, i, valueentry1);
            Object obj1 = lastEntry;
            ((ValueSetLink) (obj1)).setSuccessorInValueSet(((ValueSetLink) (obj)));
            ((ValueSetLink) (obj)).setPredecessorInValueSet(((ValueSetLink) (obj1)));
            ((ValueSetLink) (obj)).setSuccessorInValueSet(this);
            setPredecessorInValueSet(((ValueSetLink) (obj)));
            obj1 = multimapHeaderEntry.predecessorInMultimap;
            obj1.successorInMultimap = ((ValueEntry) (obj));
            obj.predecessorInMultimap = ((ValueEntry) (obj1));
            obj1 = multimapHeaderEntry;
            obj.successorInMultimap = ((ValueEntry) (obj1));
            obj1.predecessorInMultimap = ((ValueEntry) (obj));
            hashTable[l] = ((ValueEntry) (obj));
            size = size + 1;
            modCount = modCount + 1;
            l = size;
            int i1 = hashTable.length;
            boolean flag = flag1;
            if ((double)l > 1.0D * (double)i1)
            {
                flag = flag1;
                if (i1 < 0x40000000)
                {
                    flag = true;
                }
            }
            if (flag)
            {
                ValueEntry avalueentry[] = new ValueEntry[hashTable.length << 1];
                hashTable = avalueentry;
                int j = avalueentry.length;
                for (obj = firstEntry; obj != this; obj = ((ValueSetLink) (obj)).getSuccessorInValueSet())
                {
                    ValueEntry valueentry2 = (ValueEntry)obj;
                    int k = valueentry2.smearedValueHash & j - 1;
                    valueentry2.nextInValueBucket = avalueentry[k];
                    avalueentry[k] = valueentry2;
                }

            }
            return true;
        }

        public final void clear()
        {
            Arrays.fill(hashTable, null);
            size = 0;
            for (ValueSetLink valuesetlink = firstEntry; valuesetlink != this; valuesetlink = valuesetlink.getSuccessorInValueSet())
            {
                ValueEntry valueentry1 = (ValueEntry)valuesetlink;
                ValueEntry valueentry = valueentry1.predecessorInMultimap;
                valueentry1 = valueentry1.successorInMultimap;
                valueentry.successorInMultimap = valueentry1;
                valueentry1.predecessorInMultimap = valueentry;
            }

            setSuccessorInValueSet(this);
            setPredecessorInValueSet(this);
            modCount = modCount + 1;
        }

        public final boolean contains(Object obj)
        {
            boolean flag1 = false;
            ValueEntry valueentry;
            int i;
            if (obj == null)
            {
                i = 0;
            } else
            {
                i = obj.hashCode();
            }
            i = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
            valueentry = hashTable[hashTable.length - 1 & i];
            do
            {
label0:
                {
                    boolean flag = flag1;
                    if (valueentry != null)
                    {
                        if (!valueentry.matchesValue(obj, i))
                        {
                            break label0;
                        }
                        flag = true;
                    }
                    return flag;
                }
                valueentry = valueentry.nextInValueBucket;
            } while (true);
        }

        public final ValueSetLink getPredecessorInValueSet()
        {
            return lastEntry;
        }

        public final ValueSetLink getSuccessorInValueSet()
        {
            return firstEntry;
        }

        public final Iterator iterator()
        {
            class _cls1
                implements Iterator
            {

                private int expectedModCount;
                private ValueSetLink nextEntry;
                private final ValueSet this$1;
                private ValueEntry toRemove;

                public final boolean hasNext()
                {
                    if (modCount != expectedModCount)
                    {
                        throw new ConcurrentModificationException();
                    }
                    return nextEntry != ValueSet.this;
                }

                public final Object next()
                {
                    if (!hasNext())
                    {
                        throw new NoSuchElementException();
                    } else
                    {
                        ValueEntry valueentry = (ValueEntry)nextEntry;
                        Object obj = valueentry.getValue();
                        toRemove = valueentry;
                        nextEntry = valueentry.successorInValueSet;
                        return obj;
                    }
                }

                public final void remove()
                {
                    if (modCount != expectedModCount)
                    {
                        throw new ConcurrentModificationException();
                    }
                    boolean flag;
                    if (toRemove != null)
                    {
                        flag = true;
                    } else
                    {
                        flag = false;
                    }
                    if (!flag)
                    {
                        throw new IllegalStateException(String.valueOf("no calls to next() since the last call to remove()"));
                    } else
                    {
                        ValueSet.this.remove(toRemove.getValue());
                        expectedModCount = modCount;
                        toRemove = null;
                        return;
                    }
                }

                _cls1()
                {
                    this$1 = ValueSet.this;
                    super();
                    nextEntry = firstEntry;
                    expectedModCount = modCount;
                }
            }

            return new _cls1();
        }

        public final boolean remove(Object obj)
        {
            boolean flag1 = false;
            ValueEntry valueentry;
            Object obj1;
            int i;
            int j;
            if (obj == null)
            {
                i = 0;
            } else
            {
                i = obj.hashCode();
            }
            i = (int)(0x1b873593L * (long)Integer.rotateLeft((int)((long)i * 0xffffffffcc9e2d51L), 15));
            j = i & hashTable.length - 1;
            obj1 = null;
            valueentry = hashTable[j];
            do
            {
label0:
                {
                    boolean flag = flag1;
                    if (valueentry != null)
                    {
                        if (!valueentry.matchesValue(obj, i))
                        {
                            break label0;
                        }
                        if (obj1 == null)
                        {
                            hashTable[j] = valueentry.nextInValueBucket;
                        } else
                        {
                            obj1.nextInValueBucket = valueentry.nextInValueBucket;
                        }
                        obj = valueentry.getPredecessorInValueSet();
                        obj1 = valueentry.getSuccessorInValueSet();
                        ((ValueSetLink) (obj)).setSuccessorInValueSet(((ValueSetLink) (obj1)));
                        ((ValueSetLink) (obj1)).setPredecessorInValueSet(((ValueSetLink) (obj)));
                        obj = valueentry.predecessorInMultimap;
                        valueentry = valueentry.successorInMultimap;
                        obj.successorInMultimap = valueentry;
                        valueentry.predecessorInMultimap = ((ValueEntry) (obj));
                        size = size - 1;
                        modCount = modCount + 1;
                        flag = true;
                    }
                    return flag;
                }
                ValueEntry valueentry1 = valueentry.nextInValueBucket;
                obj1 = valueentry;
                valueentry = valueentry1;
            } while (true);
        }

        public final void setPredecessorInValueSet(ValueSetLink valuesetlink)
        {
            lastEntry = valuesetlink;
        }

        public final void setSuccessorInValueSet(ValueSetLink valuesetlink)
        {
            firstEntry = valuesetlink;
        }

        public final int size()
        {
            return size;
        }

        ValueSet(Object obj, int i)
        {
            this$0 = LinkedHashMultimap.this;
            super();
            size = 0;
            modCount = 0;
            key = obj;
            firstEntry = this;
            lastEntry = this;
            hashTable = new ValueEntry[Hashing.closedTableSize(i, 1.0D)];
        }

        private class ValueSetLink
        {

            public abstract ValueSetLink getPredecessorInValueSet();

            public abstract ValueSetLink getSuccessorInValueSet();

            public abstract void setPredecessorInValueSet(ValueSetLink valuesetlink);

            public abstract void setSuccessorInValueSet(ValueSetLink valuesetlink);
        }

    }


    private class _cls1
        implements Iterator
    {

        private ValueEntry nextEntry;
        private final LinkedHashMultimap this$0;
        private ValueEntry toRemove;

        public final boolean hasNext()
        {
            return nextEntry != multimapHeaderEntry;
        }

        public final Object next()
        {
            if (!hasNext())
            {
                throw new NoSuchElementException();
            } else
            {
                ValueEntry valueentry = nextEntry;
                toRemove = valueentry;
                nextEntry = nextEntry.successorInMultimap;
                return valueentry;
            }
        }

        public final void remove()
        {
            boolean flag;
            if (toRemove != null)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            if (!flag)
            {
                throw new IllegalStateException(String.valueOf("no calls to next() since the last call to remove()"));
            } else
            {
                AbstractMultimap.this.remove(toRemove.getKey(), toRemove.getValue());
                toRemove = null;
                return;
            }
        }

        _cls1()
        {
            this$0 = LinkedHashMultimap.this;
            super();
            nextEntry = multimapHeaderEntry.successorInMultimap;
        }
    }

}
