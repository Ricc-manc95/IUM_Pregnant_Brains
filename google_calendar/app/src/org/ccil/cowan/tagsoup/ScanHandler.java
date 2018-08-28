// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package org.ccil.cowan.tagsoup;

import org.xml.sax.SAXException;

public interface ScanHandler
{

    public abstract void adup$51DK6IA955B0____0()
        throws SAXException;

    public abstract void aname(char ac[], int i, int j)
        throws SAXException;

    public abstract void aval(char ac[], int i, int j)
        throws SAXException;

    public abstract void cmnt(char ac[], int i, int j)
        throws SAXException;

    public abstract void decl(char ac[], int i, int j)
        throws SAXException;

    public abstract void entity(char ac[], int i, int j)
        throws SAXException;

    public abstract void eof$51DK6IA955B0____0()
        throws SAXException;

    public abstract void etag(char ac[], int i, int j)
        throws SAXException;

    public abstract int getEntity();

    public abstract void gi(char ac[], int i, int j)
        throws SAXException;

    public abstract void pcdata(char ac[], int i, int j)
        throws SAXException;

    public abstract void pi(char ac[], int i, int j)
        throws SAXException;

    public abstract void pitarget(char ac[], int i, int j)
        throws SAXException;

    public abstract void stagc(char ac[], int i, int j)
        throws SAXException;

    public abstract void stage(char ac[], int i, int j)
        throws SAXException;
}
