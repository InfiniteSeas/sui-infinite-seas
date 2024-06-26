// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

package org.dddml.suiinfinitesea.sui.contract.service;

import com.github.wubuku.sui.bean.*;
import com.github.wubuku.sui.utils.*;
import org.dddml.suiinfinitesea.domain.shipbattle.*;
import org.dddml.suiinfinitesea.domain.*;
import org.dddml.suiinfinitesea.sui.contract.DomainBeanUtils;
import org.dddml.suiinfinitesea.sui.contract.ShipBattle;

import java.util.*;
import java.math.*;
import java.util.function.*;

public class SuiShipBattleStateRetriever {

    private SuiJsonRpcClient suiJsonRpcClient;

    private Function<String, ShipBattleState.MutableShipBattleState> shipBattleStateFactory;

    public SuiShipBattleStateRetriever(SuiJsonRpcClient suiJsonRpcClient,
                                  Function<String, ShipBattleState.MutableShipBattleState> shipBattleStateFactory
    ) {
        this.suiJsonRpcClient = suiJsonRpcClient;
        this.shipBattleStateFactory = shipBattleStateFactory;
    }

    public ShipBattleState retrieveShipBattleState(String objectId) {
        SuiMoveObjectResponse<ShipBattle> getObjectDataResponse = suiJsonRpcClient.getMoveObject(
                objectId, new SuiObjectDataOptions(true, true, true, true, true, true, true), ShipBattle.class
        );
        if (getObjectDataResponse.getData() == null) {
            return null;
        }
        ShipBattle shipBattle = getObjectDataResponse.getData().getContent().getFields();
        return toShipBattleState(shipBattle);
    }

    private ShipBattleState toShipBattleState(ShipBattle shipBattle) {
        ShipBattleState.MutableShipBattleState shipBattleState = shipBattleStateFactory.apply(shipBattle.getId().getId());
        shipBattleState.setVersion(shipBattle.getVersion());
        shipBattleState.setInitiator(shipBattle.getInitiator());
        shipBattleState.setResponder(shipBattle.getResponder());
        shipBattleState.setStatus(shipBattle.getStatus());
        shipBattleState.setInitiatorExperiences(Arrays.asList(shipBattle.getInitiatorExperiences()));
        shipBattleState.setResponderExperiences(Arrays.asList(shipBattle.getResponderExperiences()));
        shipBattleState.setEndedAt(shipBattle.getEndedAt());
        shipBattleState.setWinner(shipBattle.getWinner());
        shipBattleState.setRoundNumber(shipBattle.getRoundNumber());
        shipBattleState.setRoundStartedAt(shipBattle.getRoundStartedAt());
        shipBattleState.setRoundMover(shipBattle.getRoundMover());
        shipBattleState.setRoundAttackerShip(shipBattle.getRoundAttackerShip());
        shipBattleState.setRoundDefenderShip(shipBattle.getRoundDefenderShip());
        return shipBattleState;
    }

    
}

