// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.domain.map;

import java.util.List;
import java.util.Date;
import java.math.BigInteger;
import org.dddml.suiinfinitesea.domain.*;
import org.dddml.suiinfinitesea.specialization.Event;
import org.dddml.suiinfinitesea.domain.Command;

public interface MapAggregate {
    MapState getState();

    List<Event> getChanges();

    void addIsland(Coordinates coordinates, ItemIdQuantityPairs resources, Long offChainVersion, String commandId, String requesterId, MapCommands.AddIsland c);

    void claimIsland(Coordinates coordinates, String claimedBy, BigInteger claimedAt, Long offChainVersion, String commandId, String requesterId, MapCommands.ClaimIsland c);

    void gatherIslandResources(String player, String clock, Long offChainVersion, String commandId, String requesterId, MapCommands.GatherIslandResources c);

    void throwOnInvalidStateTransition(Command c);
}

