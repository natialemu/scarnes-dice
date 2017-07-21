package com.natialemu.scarnesdice.Model;

/**
 * Created by Nathnael on 6/24/2017.
 */

public class PlayerImpl implements Player{
    private String name;
    private int points;

    @Override
    public void setPoints(int points) {
        this.points = points;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPoints() {
        return points;
    }
}
