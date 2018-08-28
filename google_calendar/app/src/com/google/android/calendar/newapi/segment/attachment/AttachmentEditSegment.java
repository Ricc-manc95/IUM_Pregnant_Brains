// Decompiled by Jad v1.5.8e. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.geocities.com/kpdus/jad.html
// Decompiler options: braces fieldsfirst space lnc 

package com.google.android.calendar.newapi.segment.attachment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.PopupMenu;
import com.google.android.calendar.api.event.attachment.Attachment;
import com.google.android.calendar.tiles.view.TextTileView;
import com.google.android.calendar.viewedit.segment.edit.EditSegment;
import com.google.android.libraries.view.horizontalcarousel.HorizontalCarouselAdapter;

// Referenced classes of package com.google.android.calendar.newapi.segment.attachment:
//            AttachmentTileView

public class AttachmentEditSegment extends EditSegment
    implements android.view.View.OnClickListener, com.google.android.libraries.view.horizontalcarousel.HorizontalCarouselAdapter.OnCarouselTileClickListener
{
    public static interface Listener
    {

        public abstract void onNewAttachmentClicked();

        public abstract void onOpenAttachment(Attachment attachment);

        public abstract void onRemoveAttachment(Attachment attachment);
    }


    public AttachmentTileView attachmentTile;
    public TextTileView newAttachmentTextTile;

    public AttachmentEditSegment(Context context, AttributeSet attributeset)
    {
        super(context, attributeset);
    }

    public final void onCarouselTileClick(View view, Object obj)
    {
        obj = (Attachment)obj;
        if (obj != null)
        {
            view = new PopupMenu(getContext(), view);
            Menu menu = view.getMenu();
            view.setOnMenuItemClickListener(new _cls1());
            view.show();
        }
    }

    public void onClick(View view)
    {
        ((Listener)mListener).onNewAttachmentClicked();
    }

    protected void onFinishInflate()
    {
        super.onFinishInflate();
        attachmentTile = (AttachmentTileView)findViewById(0x7f100244);
        attachmentTile.adapter.onCarouselTileClickListener = this;
        newAttachmentTextTile = (TextTileView)findViewById(0x7f100245);
        newAttachmentTextTile.setOnClickListener(this);
    }

    private class _cls1
        implements android.widget.PopupMenu.OnMenuItemClickListener
    {

        private final AttachmentEditSegment this$0;
        private final Attachment val$item;
        private final MenuItem val$removeMenuItem;
        private final MenuItem val$viewMenuItem;

        public final boolean onMenuItemClick(MenuItem menuitem)
        {
            if (menuitem == viewMenuItem)
            {
                ((Listener)mListener).onOpenAttachment(item);
                return true;
            }
            if (menuitem == removeMenuItem)
            {
                ((Listener)mListener).onRemoveAttachment(item);
                return true;
            } else
            {
                return false;
            }
        }

        _cls1()
        {
            this$0 = AttachmentEditSegment.this;
            viewMenuItem = menuitem;
            item = attachment;
            removeMenuItem = menuitem1;
            super();
        }
    }

}
