// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.android.ahat.dominators;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Queue;

public class DominatorsComputation
{
    static final class Link
    {

        public Node dst;
        public NodeS srcS;

        public Link(NodeS nodes, Node node)
        {
            srcS = nodes;
            dst = node;
        }
    }

    public static interface Node
    {

        public abstract Object getDominatorsComputationState();

        public abstract Iterable getReferencesForDominators();

        public abstract void setDominator(Node node);

        public abstract void setDominatorsComputationState(Object obj);
    }

    static final class NodeS
    {

        public NodeS domS;
        public long domid;
        public long id;
        public Node node;
        public long seenid;
        public NodeS srcS;
        public List undom;

        NodeS()
        {
            undom = new ArrayList();
        }
    }


    public static void computeDominators(Node node)
    {
        ArrayList arraylist;
        ArrayDeque arraydeque;
        ArrayDeque arraydeque1;
        long l;
        arraylist = new ArrayList();
        arraydeque = new ArrayDeque();
        NodeS nodes = new NodeS();
        nodes.node = node;
        long l1 = 1L;
        nodes.id = 0L;
        node.setDominatorsComputationState(nodes);
        arraydeque1 = new ArrayDeque();
        Iterator iterator = node.getReferencesForDominators().iterator();
        do
        {
            l = l1;
            if (!iterator.hasNext())
            {
                break;
            }
            arraydeque1.push(new Link(nodes, (Node)iterator.next()));
        } while (true);
          goto _L1
_L3:
        l = 1L + l;
_L1:
        do
        {
            if (arraydeque1.isEmpty())
            {
                break;
            }
            Object obj2 = (Link)arraydeque1.pop();
            NodeS nodes5 = (NodeS)((Link) (obj2)).dst.getDominatorsComputationState();
            if (nodes5 == null)
            {
                NodeS nodes1 = new NodeS();
                nodes1.node = ((Link) (obj2)).dst;
                nodes1.id = l;
                nodes1.domid = ((Link) (obj2)).srcS.id;
                nodes1.domS = ((Link) (obj2)).srcS;
                nodes1.srcS = ((Link) (obj2)).srcS;
                nodes1.seenid = nodes1.domid;
                arraylist.add(nodes1);
                ((Link) (obj2)).dst.setDominatorsComputationState(nodes1);
                obj2 = ((Link) (obj2)).dst.getReferencesForDominators().iterator();
                while (((Iterator) (obj2)).hasNext()) 
                {
                    arraydeque1.push(new Link(nodes1, (Node)((Iterator) (obj2)).next()));
                }
                continue; /* Loop/switch isn't completed */
            }
            NodeS nodes2 = ((Link) (obj2)).srcS;
            boolean flag;
            if (nodes5.domid < nodes5.domS.id)
            {
                flag = true;
            } else
            {
                flag = false;
            }
            for (; nodes2.id > nodes5.seenid; nodes2 = nodes2.srcS)
            {
                nodes2.undom.add(nodes5);
            }

            nodes5.seenid = ((Link) (obj2)).srcS.id;
            if (nodes2.id < nodes5.domid)
            {
                nodes5.domid = nodes2.id;
                if (!flag)
                {
                    arraydeque.add(nodes5);
                }
            }
        } while (true);
        while (!arraydeque.isEmpty()) 
        {
            Object obj1 = (NodeS)arraydeque.poll();
            NodeS nodes3;
            for (nodes3 = ((NodeS) (obj1)).domS; nodes3.id > ((NodeS) (obj1)).domid; nodes3 = nodes3.srcS)
            {
                if (nodes3.domS.id < ((NodeS) (obj1)).domid)
                {
                    obj1.domid = nodes3.domS.id;
                }
                nodes3.undom.add(obj1);
            }

            obj1.domS = nodes3;
            obj1.domid = nodes3.id;
            obj1 = ((NodeS) (obj1)).undom.iterator();
            while (((Iterator) (obj1)).hasNext()) 
            {
                NodeS nodes4 = (NodeS)((Iterator) (obj1)).next();
                if (nodes3.id < nodes4.domid)
                {
                    boolean flag1;
                    if (nodes4.domid < nodes4.domS.id)
                    {
                        flag1 = true;
                    } else
                    {
                        flag1 = false;
                    }
                    nodes4.domid = nodes3.id;
                    if (!flag1)
                    {
                        arraydeque.add(nodes4);
                    }
                }
            }
        }
        node.setDominatorsComputationState(null);
        node = (ArrayList)arraylist;
        int j = node.size();
        for (int i = 0; i < j;)
        {
            Object obj = node.get(i);
            i++;
            obj = (NodeS)obj;
            ((NodeS) (obj)).node.setDominator(((NodeS) (obj)).domS.node);
            ((NodeS) (obj)).node.setDominatorsComputationState(null);
        }

        return;
        if (true) goto _L3; else goto _L2
_L2:
    }
}
