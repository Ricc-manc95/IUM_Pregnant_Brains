package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.Serializable;

public final class Token implements Serializable{
    private static final long serialVersionUID = 5115756536497241452L;

    private PointF location = new PointF(0, 0);
    private float size = 1.0f; // Relative diameter of the token (1.0 = occupies one grid square
    private int color;
    private String name;
    private String letters;

    public Token(int c, String name) {
        this.color = c;
        this.name = name;
        setLetters(name);
    }

    public void drawInPosition(Canvas c, CoordinateTransformer transformer) {
        PointF center = transformer.worldSpaceToScreenSpace(location);
        float radius = transformer.worldSpaceToScreenSpace(this.size * 0.9f / 2);

        Paint p = new Paint();
        p.setColor(color);

        c.drawCircle(center.x, center.y, radius, p);
        p.setTextSize(radius);
        p.setStrokeWidth(2);
        p.setStyle(Paint.Style.FILL);
        p.setColor(Color.WHITE);
        c.drawText(letters, center.x-radius/4, center.y+radius/4, p);
    }

    public void move(float distanceX, float distanceY) {
        location = new PointF(location.x - distanceX, location.y - distanceY);

    }

    public Token clone() {
        return new Token(color, name);
    }

    /**
     * Draws an indication of a past location of the token.
     * @param c
     * @param transformer
     * @param ghostPoint Location to draw the ghost, in world space
     */
    public void drawGhost(Canvas c, CoordinateTransformer transformer, PointF ghostPoint) {
        Paint p = new Paint();
        p.setStrokeWidth(2);
        p.setColor(color);
        p.setStyle(Paint.Style.STROKE);
        PointF center = transformer.worldSpaceToScreenSpace(ghostPoint);

        float radius = transformer.worldSpaceToScreenSpace(this.size * 0.9f / 2);

        c.drawCircle(center.x, center.y, radius, p);
    }

    public void draw(Canvas c, float x, float y, float radius) {
        Paint p = new Paint();
        p.setColor(color);
        c.drawCircle(x, y, radius, p);
    }

    public void drawPreview(Canvas c, float x, float y, float radius) {
        Paint p = new Paint();
        p.setColor(color);
        c.drawCircle(x, y, radius, p);
    }

    public void setSize(float d) {
        this.size = d;
    }

    public void setLetters (String name) {
        this.letters = String.valueOf(name.charAt(0));
    }

    public float getSize() {
        return this.size;
    }

    public PointF getLocation() {
        return this.location;
    }

    public void setLocation(PointF location) {
        this.location = location;
    }

    public BoundingRectangle getBoundingRectangle() {
        BoundingRectangle r = new BoundingRectangle();
        r.updateBounds(new PointF(location.x - size/2, location.y - size/2));
        r.updateBounds(new PointF(location.x + size/2, location.y + size/2));
        return r;
    }
}