package com.melot.kk.draw.tool.domain;

import java.io.Serializable;

/**
 * @author melot
 */

public class DrawOddsDTO implements Serializable {

    private static final long serialVersionUID = -4984008963242824921L;

    private Double startRange;

    private Double endRange;

    private DrawLevelDTO drawLevelDTO;

    @Override
    public String toString() {
        return "DrawOddsDTO{" +
                "startRange=" + startRange +
                ", endRange=" + endRange +
                ", drawLevelDTO=" + drawLevelDTO +
                '}';
    }

    public Double getStartRange() {
        return startRange;
    }

    public void setStartRange(Double startRange) {
        this.startRange = startRange;
    }

    public Double getEndRange() {
        return endRange;
    }

    public void setEndRange(Double endRange) {
        this.endRange = endRange;
    }

    public DrawLevelDTO getDrawLevelDTO() {
        return drawLevelDTO;
    }

    public void setDrawLevelDTO(DrawLevelDTO drawLevelDTO) {
        this.drawLevelDTO = drawLevelDTO;
    }
}
