// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.sui.contract;

import java.util.*;
import java.math.*;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.github.wubuku.sui.bean.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RosterIdForEvent {
    private String playerId;

    private Integer sequenceNumber;

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public Integer getSequenceNumber() {
        return sequenceNumber;
    }

    public void setSequenceNumber(Integer sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
    }

    @Override
    public String toString() {
        return "RosterIdFields{" +
                "playerId=" + '\'' + playerId + '\'' +
                ", sequenceNumber=" + sequenceNumber +
            '}';
    }

}

