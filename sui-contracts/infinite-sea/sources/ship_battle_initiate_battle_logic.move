#[allow(unused_variable, unused_use, unused_assignment, unused_mut_parameter)]
module infinite_sea::ship_battle_initiate_battle_logic {
    use std::option;

    use sui::clock;
    use sui::clock::Clock;
    use sui::tx_context::TxContext;
    use infinite_sea_common::battle_status;
    use infinite_sea_common::roster_status;

    use infinite_sea::permission_util;
    use infinite_sea::player::Player;
    use infinite_sea::roster::{Self, Roster};
    use infinite_sea::ship_battle;
    use infinite_sea::ship_battle_util;

    friend infinite_sea::ship_battle_aggregate;

    const FIRST_ROUND_NUMBER: u32 = 1;

    public(friend) fun verify(
        player: &Player,
        initiator: &mut Roster,
        responder: &mut Roster,
        clock: &Clock,
        ctx: &mut TxContext,
    ): ship_battle::ShipBattleInitiated {
        permission_util::assert_sender_is_player_owner(player, ctx);
        permission_util::assert_player_is_roster_owner(player, initiator);
        //
        // todo Update the positions of the two rosters and then determine if they are close enough to each other
        //
        // todo more checks???

        let (attacker_ship_id, defender_ship_id, roster_indicator) = ship_battle_util::determine_attacker_and_defender(
            initiator, responder, clock, FIRST_ROUND_NUMBER
        );
        let first_round_mover = if (roster_indicator == 1) {
            ship_battle_util::initiator()
        } else {
            ship_battle_util::responder()
        };
        ship_battle::new_ship_battle_initiated(
            roster::id(initiator),
            roster::id(responder),
            clock::timestamp_ms(clock) / 1000,
            option::some(first_round_mover),
            option::some(attacker_ship_id),
            option::some(defender_ship_id),
        )
    }

    public(friend) fun mutate(
        ship_battle_initiated: &mut ship_battle::ShipBattleInitiated,
        initiator: &mut Roster,
        responder: &mut Roster,
        ctx: &mut TxContext,
    ): ship_battle::ShipBattle {
        let initiator_id = ship_battle::ship_battle_initiated_initiator_id(ship_battle_initiated);
        let responder_id = ship_battle::ship_battle_initiated_responder_id(ship_battle_initiated);
        let started_at = ship_battle::ship_battle_initiated_started_at(ship_battle_initiated);
        let first_round_mover = ship_battle::ship_battle_initiated_first_round_mover(ship_battle_initiated);
        let first_round_attacker_ship = ship_battle::ship_battle_initiated_first_round_attacker_ship(
            ship_battle_initiated
        );
        let first_round_defender_ship = ship_battle::ship_battle_initiated_first_round_defender_ship(
            ship_battle_initiated
        );
        let battle = ship_battle::new_ship_battle(
            initiator_id, responder_id,
            battle_status::in_progress(),
            started_at,
            first_round_mover, first_round_attacker_ship, first_round_defender_ship,
            ctx
        );
        ship_battle::set_round_number(&mut battle, FIRST_ROUND_NUMBER);
        let battle_id = ship_battle::id(&battle);
        // update rosters with battle ID
        roster::set_status(initiator, roster_status::in_battle());
        roster::set_ship_battle_id(initiator, option::some(battle_id));
        roster::set_status(responder, roster_status::in_battle());
        roster::set_ship_battle_id(responder, option::some(battle_id));
        battle
    }
}
