// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.player;

import java.util.*;
import java.util.Date;
import org.dddml.suiinfinitesea.domain.*;
import org.dddml.suiinfinitesea.specialization.Event;

public interface PlayerItemEvent extends Event {

    interface SqlPlayerItemEvent extends PlayerItemEvent {
        PlayerItemEventId getPlayerItemEventId();

        boolean getEventReadOnly();

        void setEventReadOnly(boolean readOnly);
    }

    Long getItemId();

    //void setItemId(Long itemId);

    String getCreatedBy();

    void setCreatedBy(String createdBy);

    Date getCreatedAt();

    void setCreatedAt(Date createdAt);

    String getCommandId();

    void setCommandId(String commandId);


}
