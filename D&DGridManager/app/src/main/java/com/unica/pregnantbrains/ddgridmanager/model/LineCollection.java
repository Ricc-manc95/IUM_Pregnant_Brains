package com.unica.pregnantbrains.ddgridmanager.model;

import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.drawable.shapes.Shape;

import com.google.common.collect.Lists;
import com.unica.pregnantbrains.ddgridmanager.model.primitives.StraightLine;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class LineCollection {
    private static final String TAG = "LineCollection";
    /**
     * The internal list of lines.
     */
    private List<StraightLine> mLines = Lists.newArrayList();
    /**
            * Lines deleted in this operation.
         */
    private final Collection<StraightLine> mDeleted = new ArrayList<StraightLine>();

    /**
     * Cache of lines that should be drawn above the grid.
     */
    private final List<StraightLine> mAboveGridLines = Lists.newArrayList();
    /**
     * Cache of lines that should be drawn below the grid.
     */
    private final List<StraightLine> mBelowGridLines = Lists.newArrayList();
    /**
     * Factory method that creates a straight line, adds it to the list of
     * lines, and returns the newly created line
     */
    public Shape createStraightLine(int newLineColor, float newLineStrokeWidth) {
        StraightLine l = new StraightLine(newLineColor, newLineStrokeWidth);
        return l;
    }
    /**
     * Deletes the given shape.
     *
     * @param l
     *            The shape to delete.
     */
    public void deleteShape(Shape l) {
        if (this.mLines.contains(l)) {

        }
    }

    /**
     * Draws all lines on the given canvas.
     *  @param canvas
     *            The canvas to draw on.
     *
     */
    public void drawAllLines(final Canvas canvas) {
        for (StraightLine line: mLines) {
            line.applyDrawOffsetToCanvas(canvas);
            line.draw(canvas);
            line.revertDrawOffsetFromCanvas(canvas);
        }
    }
    /**
     * Draws all lines on the given canvas that should be drawn above the grid.
     *  @param canvas
     *            The canvas to draw on.
     *
     */
    public void drawAllLinesAboveGrid(final Canvas canvas) {
        for (StraightLine line: mAboveGridLines) {
            line.applyDrawOffsetToCanvas(canvas);
            line.draw(canvas);
            line.revertDrawOffsetFromCanvas(canvas);
        }
    }
    /**
     * Draws all lines on the given canvas that should be drawn below the grid.
     *  @param canvas
     *            The canvas to draw on.
     *
     */
    public void drawAllLinesBelowGrid(final Canvas canvas) {
        for (StraightLine line: mBelowGridLines) {
            line.applyDrawOffsetToCanvas(canvas);
            line.draw(canvas);
            line.revertDrawOffsetFromCanvas(canvas);
        }
    }
    /**
     * @return The bounding rectangle that bounds all lines in the collection.
     */
    public BoundingRectangle getBoundingRectangle() {
        BoundingRectangle r = new BoundingRectangle();
        for (StraightLine l : this.mLines) {
            r.updateBounds(l.getBoundingRectangle());
        }
        return r;
    }
    /**
     * Performs an optimization pass on the lines. This removes all erased
     * points (rather than keeping them marked as not drawn), and splits each
     * line with erased points into individual lines representing the newly
     * disjoint sections.
     */
    /*public void optimize() {
        for (StraightLine line: this.mLines) {
            if (!line.isValid()) {
                this.addDeletedShape(line);
            } else if (line.needsOptimization()) {
                List<Shape> optimizedLines =
                        line.removeErasedPoints();
                this.addDeletedShape(line);
                this.addCreatedShapes(optimizedLines);
            } else if (line.hasOffset()) {
                this.addDeletedShape(line);
                this.addCreatedShape(line.commitDrawOffset());
            }
        }
    }
    /**
     * Adds a line to the list of lines removed by this command.
     *
     * @param l
     *            The line to remove.
     */
    public void addDeletedShape(final StraightLine l) {
        this.mDeleted.add(l);
    }
    /**
     * Erases all points on lines centered at a given location.
     *
     * @param location
     *            The point in world space to center the erase on.
     * @param radius
     *            Radius around the point to erase, in world space.
     */
    public void erase(final PointF location, final float radius) {
        for (StraightLine mLine : this.mLines) {
            mLine.erase(location, radius);
        }
    }
    /**
     * Adds a line to the list of lines created by this command.
     *
     * @param l
     *            The line to add.
     *
    public void addCreatedShape(final Shape l) {
        this.mCreated.add(l);
    }VEDERE COMMANDS */
}
