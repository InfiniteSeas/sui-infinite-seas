// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.player;

import java.util.*;
import org.dddml.suiinfinitesea.domain.*;
import java.math.BigInteger;
import java.util.Date;
import org.dddml.suiinfinitesea.specialization.Event;

public interface PlayerEvent extends Event, SuiEventEnvelope, SuiMoveEvent, HasEventStatus {

    interface SqlPlayerEvent extends PlayerEvent {
        PlayerEventId getPlayerEventId();

        boolean getEventReadOnly();

        void setEventReadOnly(boolean readOnly);
    }

    interface PlayerCreated extends PlayerEvent {
        String getName();

        void setName(String value);

        String getOwner();

        void setOwner(String value);

    }

    interface IslandClaimed extends PlayerEvent {
        Coordinates getCoordinates();

        void setCoordinates(Coordinates value);

        BigInteger getClaimedAt();

        void setClaimedAt(BigInteger value);

    }

    interface PlayerAirdropped extends PlayerEvent {
        Long getItemId();

        void setItemId(Long value);

        Long getQuantity();

        void setQuantity(Long value);

    }

    String getId();

    //void setId(String id);

    BigInteger getVersion();
    
    //void setVersion(BigInteger version);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getCommandId();

    void setCommandId(String commandId);


}

