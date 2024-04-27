// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module infinite_sea::roster_aggregate {
    use infinite_sea::roster;
    use infinite_sea::roster_add_ship_logic;
    use infinite_sea::roster_create_logic;
    use infinite_sea::ship::Ship;
    use infinite_sea_common::coordinates::Coordinates;
    use infinite_sea_common::roster_id::{Self, RosterId};
    use std::option::Option;
    use sui::object::ID;
    use sui::tx_context;

    friend infinite_sea::player_create_logic;
    friend infinite_sea::player_claim_island_logic;
    friend infinite_sea::skill_process_complete_ship_production_logic;
    friend infinite_sea::skill_process_service;

    public(friend) fun create(
        roster_id_player_id: ID,
        roster_id_sequence_number: u8,
        status: u8,
        speed: u32,
        updated_coordinates: Coordinates,
        coordinates_updated_at: u64,
        target_coordinates: Option<Coordinates>,
        ship_battle_id: Option<ID>,
        roster_table: &mut roster::RosterTable,
        ctx: &mut tx_context::TxContext,
    ): roster::Roster {
        let roster_id: RosterId = roster_id::new(
            roster_id_player_id,
            roster_id_sequence_number,
        );

        let roster_created = roster_create_logic::verify(
            roster_id,
            status,
            speed,
            updated_coordinates,
            coordinates_updated_at,
            target_coordinates,
            ship_battle_id,
            roster_table,
            ctx,
        );
        let roster = roster_create_logic::mutate(
            &mut roster_created,
            roster_table,
            ctx,
        );
        roster::set_roster_created_id(&mut roster_created, roster::id(&roster));
        roster::emit_roster_created(roster_created);
        roster
    }

    public(friend) fun add_ship(
        roster: &mut roster::Roster,
        ship: Ship,
        position: Option<u64>,
        ctx: &mut tx_context::TxContext,
    ) {
        let roster_ship_added = roster_add_ship_logic::verify(
            &ship,
            position,
            roster,
            ctx,
        );
        roster_add_ship_logic::mutate(
            &mut roster_ship_added,
            ship,
            roster,
            ctx,
        );
        roster::update_object_version(roster);
        roster::emit_roster_ship_added(roster_ship_added);
    }

}