package com.Model;

import java.awt.*;
import java.awt.Point;
import java.awt.geom.Point2D;

abstract public class Brick  {
    public static final int DEF_CRACK_DEPTH = 1;
    public static final int DEF_STEPS = 35;

    public static final int UP_IMPACT = 100;
    public static final int DOWN_IMPACT = 200;
    public static final int LEFT_IMPACT = 300;
    public static final int RIGHT_IMPACT = 400;

    protected Shape brickFace;
    protected boolean broken;

    private Color border;
    private Color inner;
    private int fullStrength;
    private int strength;

    public Brick(Point pos, Dimension size, Color border, Color inner, int strength){
        broken = false;
        brickFace = makeBrickFace(pos,size);
        this.border = border;
        this.inner = inner;
        this.fullStrength = this.strength = strength;
    }

    protected abstract Shape makeBrickFace(Point pos,Dimension size);

    //Color
    public Color getBorderColor(){
        return  border;
    }
    public Color getInnerColor(){
        return inner;
    }

    public abstract Shape getBrick();

    //Impact
    public  boolean setImpact(Point2D point , int dir){
        if(broken)
            return false;
        impact();
        return  broken;
    }

    public final int findImpact(Ball b){
        if(broken)
            return 0;
        int out  = 0;
        if(brickFace.contains(b.getLeft()))
            out = LEFT_IMPACT;
        else if(brickFace.contains(b.getLeft()))
            out = RIGHT_IMPACT;
        else if(brickFace.contains(b.getUp()))
            out = DOWN_IMPACT;
        else if(brickFace.contains(b.getDown()))
            out = UP_IMPACT;
        return out;
    }

    public void impact(){
        strength--;
        broken = (strength == 0);
    }

    public final boolean isBroken(){
        return broken;
    }

    //Repair
    public void repair() {
        broken = false;
        strength = fullStrength;
    }
}





