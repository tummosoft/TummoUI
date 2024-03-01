package com.tummosoft.control.line;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;

public class DividerLayout {
     public static final int ORIENTATION_HORIZONTAL = 0;
    public static final int ORIENTATION_VERTICAL = 1;
    public static final int MATCH_PARENT = -1;
    public static final int ALIGN_PARENT_LEFT = 1;
    public static final int ALIGN_PARENT_TOP = 1 << 1;
    public static final int ALIGN_PARENT_RIGHT = 1 << 2;
    public static final int ALIGN_PARENT_BOTTOM = 1 << 3;
    public static final int CENTER_NONE = 0;
    public static final int CENTER_HORIZONTAL = 1;
    public static final int CENTER_VERTICAL = 1 << 1;
    public static final int CENTER_IN_PARENT = 0b11;


    @IntDef({ORIENTATION_HORIZONTAL, ORIENTATION_VERTICAL})
    public @interface Orientation {
    }

    @IntDef({CENTER_NONE, CENTER_IN_PARENT, CENTER_HORIZONTAL, CENTER_VERTICAL})
    public @interface Center {
    }

    @Orientation
    private int orientation = ORIENTATION_HORIZONTAL;
    private int length = MATCH_PARENT;
    private int align = 0;
    @Center
    private int center = CENTER_NONE;
    private int marginLeft = 0;
    private int marginTop = 0;
    private int marginRight = 0;
    private int marginBottom = 0;


    public int getOrientation() {
        return orientation;
    }

    @NonNull
    public DividerLayout setOrientation(int orientation) {
        this.orientation = orientation;
        return this;
    }

    public int getLength() {
        return length;
    }

    @NonNull
    public DividerLayout setLength(int length) {
        this.length = (length >= MATCH_PARENT ? length : 0);
        return this;
    }

    @NonNull
    public DividerLayout setLengthDp(int lengthDp) {
        this.length = (lengthDp >= MATCH_PARENT ? (int) DividerUtils.dp2px(lengthDp) : 0);
        return this;
    }

    public int getAlign() {
        return align;
    }

    @NonNull
    public DividerLayout setAlign(int align) {
        this.align = align;
        return this;
    }

    public int getCenter() {
        return center;
    }

    @NonNull
    public DividerLayout setCenter(int center) {
        this.center = center;
        return this;
    }

    public int getMarginLeft() {
        return marginLeft;
    }

    @NonNull
    public DividerLayout setMarginLeft(int marginLeft) {
        this.marginLeft = marginLeft;
        return this;
    }

    @NonNull
    public DividerLayout setMarginLeftDp(int marginLeftDp) {
        this.marginLeft = (int) DividerUtils.dp2px(marginLeftDp);
        return this;
    }

    public int getMarginTop() {
        return marginTop;
    }

    @NonNull
    public DividerLayout setMarginTop(int marginTop) {
        this.marginTop = marginTop;
        return this;
    }

    @NonNull
    public DividerLayout setMarginTopDp(int marginTopDp) {
        this.marginTop = (int) DividerUtils.dp2px(marginTopDp);
        return this;
    }

    public int getMarginRight() {
        return marginRight;
    }

    @NonNull
    public DividerLayout setMarginRight(int marginRight) {
        this.marginRight = marginRight;
        return this;
    }

    @NonNull
    public DividerLayout setMarginRightDp(int marginRightDp) {
        this.marginRight = (int) DividerUtils.dp2px(marginRightDp);
        return this;
    }

    public int getMarginBottom() {
        return marginBottom;
    }

    @NonNull
    public DividerLayout setMarginBottom(int marginBottom) {
        this.marginBottom = marginBottom;
        return this;
    }

    @NonNull
    public DividerLayout setMarginBottomDp(int marginBottomDp) {
        this.marginBottom = (int) DividerUtils.dp2px(marginBottomDp);
        return this;
    }

    /**
     * @return int[] {startX, startY, stopX, stopY}
     */
    public int[] layout(int width, int height, int strokeWidth) {
        final boolean alignLeft = !((align & ALIGN_PARENT_RIGHT) > 0);
        final boolean alignTop = !((align & ALIGN_PARENT_BOTTOM) > 0);
        final boolean h = orientation == ORIENTATION_HORIZONTAL;
        final boolean ch = (center & CENTER_HORIZONTAL) > 0;
        final boolean cv = (center & CENTER_VERTICAL) > 0;

        final int[] xs, ys;
        xs = h ? layoutHorizontalXAxis(ch, alignLeft, width) : layoutVerticalXAxis(ch, alignLeft, width, strokeWidth);
        ys = h ? layoutHorizontalYAxis(cv, alignTop, height, strokeWidth) : layoutVerticalYAxis(cv, alignTop, height);

        return new int[]{xs[0], ys[0], xs[1], ys[1]};
    }

    private int[] layoutHorizontalXAxis(boolean centerHorizontal, boolean alignLeft, int width) {
        int sx, ex;
        if (!centerHorizontal) {
            sx = getMarginLeft();
            ex = width - getMarginRight();
            if (length != MATCH_PARENT) {
                if (alignLeft) {
                    ex = Math.min(ex, sx + length);
                } else {
                    sx = Math.max(sx, ex - length);
                }
            }

        } else {
            int len = width - Math.max(getMarginLeft(), getMarginRight()) * 2;
            if (length != MATCH_PARENT) {
                len = Math.min(len, length);
            }

            sx = (width / 2) - (len / 2);
            ex = (width / 2) + (len / 2);
        }

        return new int[]{sx, ex};
    }

    private int[] layoutHorizontalYAxis(boolean centerVertical, boolean alignTop, int height, int strokeWidth) {
        int sy, ey;
        if (!centerVertical) {
            if (alignTop) {
                sy = getMarginTop();
                ey = sy + strokeWidth;
            } else {
                ey = height - getMarginBottom();
                sy = ey - strokeWidth;
            }

        } else {
            sy = (height - strokeWidth) / 2;
            ey = sy + strokeWidth;
        }

        return new int[]{sy, ey};
    }

    private int[] layoutVerticalXAxis(boolean centerHorizontal, boolean alignLeft, int width, int strokeWidth) {
        int sx, ex;
        if (!centerHorizontal) {
            if (alignLeft) {
                sx = getMarginLeft();
                ex = sx + strokeWidth;
            } else {
                ex = width - getMarginRight();
                sx = ex - strokeWidth;
            }

        } else {
            sx = (width - strokeWidth) /2;
            ex = sx + strokeWidth;
        }

        return new int[]{sx, ex};
    }

    private int[] layoutVerticalYAxis(boolean centerVertical, boolean alignTop, int height) {
        int sy, ey;
        if (!centerVertical) {
            sy = getMarginTop();
            ey = height - getMarginBottom();
            if (length != MATCH_PARENT) {
                if (alignTop) {
                    ey = Math.min(ey, sy + length);
                } else {
                    sy = Math.max(sy, ey - length);
                }
            }

        } else {
            int len = height - Math.max(getMarginTop(), getMarginBottom()) * 2;
            if (length != MATCH_PARENT) {
                len = Math.min(len, length);
            }
            sy = (height / 2) - (len / 2);
            ey = (height / 2) + (len / 2);
        }

        return new int[]{sy, ey};
    }
}
