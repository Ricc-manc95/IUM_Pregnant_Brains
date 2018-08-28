// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;


// Referenced classes of package org.ccil.cowan.tagsoup:
//            AttributesImpl, ElementType

public final class Element
{

    public boolean preclosed;
    public AttributesImpl theAtts;
    public Element theNext;
    public ElementType theType;

    public Element(ElementType elementtype, boolean flag)
    {
        theType = elementtype;
        if (flag)
        {
            theAtts = new AttributesImpl(elementtype.theAtts);
        } else
        {
            theAtts = new AttributesImpl();
        }
        theNext = null;
        preclosed = false;
    }
}
