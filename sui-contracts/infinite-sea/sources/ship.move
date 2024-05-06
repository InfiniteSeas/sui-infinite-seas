// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module infinite_sea::ship {
    use infinite_sea_common::item_id_quantity_pair::ItemIdQuantityPair;
    use infinite_sea_common::item_id_quantity_pairs::ItemIdQuantityPairs;
    use std::option;
    use sui::event;
    use sui::object::{Self, ID, UID};
    use sui::transfer;
    use sui::tx_context::TxContext;

    struct SHIP has drop {}

    friend infinite_sea::ship_create_logic;
    friend infinite_sea::ship_aggregate;

    friend infinite_sea::ship_battle_make_move_logic;
    friend infinite_sea::ship_battle_take_loot_logic;
    friend infinite_sea::roster_put_in_ship_inventory_logic;
    friend infinite_sea::roster_take_out_ship_inventory_logic;
    friend infinite_sea::roster_transfer_ship_inventory_logic;

    #[allow(unused_const)]
    const EDataTooLong: u64 = 102;
    #[allow(unused_const)]
    const EInappropriateVersion: u64 = 103;
    const EEmptyObjectID: u64 = 107;

    fun init(otw: SHIP, ctx: &mut TxContext) {
        sui::package::claim_and_keep(otw, ctx);
    }

    struct Ship has key, store {
        id: UID,
        version: u64,
        owner: ID,
        health_points: u32,
        attack: u32,
        protection: u32,
        speed: u32,
        building_expenses: ItemIdQuantityPairs,
        inventory: vector<ItemIdQuantityPair>,
    }

    public fun id(ship: &Ship): object::ID {
        object::uid_to_inner(&ship.id)
    }

    public fun version(ship: &Ship): u64 {
        ship.version
    }

    public fun owner(ship: &Ship): ID {
        ship.owner
    }

    public(friend) fun set_owner(ship: &mut Ship, owner: ID) {
        ship.owner = owner;
    }

    public fun health_points(ship: &Ship): u32 {
        ship.health_points
    }

    public(friend) fun set_health_points(ship: &mut Ship, health_points: u32) {
        ship.health_points = health_points;
    }

    public fun attack(ship: &Ship): u32 {
        ship.attack
    }

    public(friend) fun set_attack(ship: &mut Ship, attack: u32) {
        ship.attack = attack;
    }

    public fun protection(ship: &Ship): u32 {
        ship.protection
    }

    public(friend) fun set_protection(ship: &mut Ship, protection: u32) {
        ship.protection = protection;
    }

    public fun speed(ship: &Ship): u32 {
        ship.speed
    }

    public(friend) fun set_speed(ship: &mut Ship, speed: u32) {
        ship.speed = speed;
    }

    public fun building_expenses(ship: &Ship): ItemIdQuantityPairs {
        ship.building_expenses
    }

    public(friend) fun set_building_expenses(ship: &mut Ship, building_expenses: ItemIdQuantityPairs) {
        ship.building_expenses = building_expenses;
    }

    public fun borrow_inventory(ship: &Ship): &vector<ItemIdQuantityPair> {
        &ship.inventory
    }

    public(friend) fun borrow_mut_inventory(ship: &mut Ship): &mut vector<ItemIdQuantityPair> {
        &mut ship.inventory
    }

    public fun inventory(ship: &Ship): vector<ItemIdQuantityPair> {
        ship.inventory
    }

    public(friend) fun set_inventory(ship: &mut Ship, inventory: vector<ItemIdQuantityPair>) {
        ship.inventory = inventory;
    }

    public(friend) fun new_ship(
        owner: ID,
        health_points: u32,
        attack: u32,
        protection: u32,
        speed: u32,
        building_expenses: ItemIdQuantityPairs,
        ctx: &mut TxContext,
    ): Ship {
        Ship {
            id: object::new(ctx),
            version: 0,
            owner,
            health_points,
            attack,
            protection,
            speed,
            building_expenses,
            inventory: std::vector::empty(),
        }
    }

    struct ShipCreated has copy, drop {
        id: option::Option<object::ID>,
        owner: ID,
        health_points: u32,
        attack: u32,
        protection: u32,
        speed: u32,
        building_expenses: ItemIdQuantityPairs,
    }

    public fun ship_created_id(ship_created: &ShipCreated): option::Option<object::ID> {
        ship_created.id
    }

    public(friend) fun set_ship_created_id(ship_created: &mut ShipCreated, id: object::ID) {
        ship_created.id = option::some(id);
    }

    public fun ship_created_owner(ship_created: &ShipCreated): ID {
        ship_created.owner
    }

    public fun ship_created_health_points(ship_created: &ShipCreated): u32 {
        ship_created.health_points
    }

    public fun ship_created_attack(ship_created: &ShipCreated): u32 {
        ship_created.attack
    }

    public fun ship_created_protection(ship_created: &ShipCreated): u32 {
        ship_created.protection
    }

    public fun ship_created_speed(ship_created: &ShipCreated): u32 {
        ship_created.speed
    }

    public fun ship_created_building_expenses(ship_created: &ShipCreated): ItemIdQuantityPairs {
        ship_created.building_expenses
    }

    public(friend) fun new_ship_created(
        owner: ID,
        health_points: u32,
        attack: u32,
        protection: u32,
        speed: u32,
        building_expenses: ItemIdQuantityPairs,
    ): ShipCreated {
        ShipCreated {
            id: option::none(),
            owner,
            health_points,
            attack,
            protection,
            speed,
            building_expenses,
        }
    }


    #[lint_allow(custom_state_change)]
    public(friend) fun transfer_object(ship: Ship, recipient: address) {
        assert!(ship.version == 0, EInappropriateVersion);
        transfer::transfer(ship, recipient);
    }

    #[allow(unused_function)]
    fun update_object_version(ship: &mut Ship) {
        ship.version = ship.version + 1;
        //assert!(ship.version != 0, EInappropriateVersion);
    }

    public(friend) fun drop_ship(ship: Ship) {
        let Ship {
            id,
            version: _version,
            owner: _owner,
            health_points: _health_points,
            attack: _attack,
            protection: _protection,
            speed: _speed,
            building_expenses: _building_expenses,
            inventory: _inventory,
        } = ship;
        object::delete(id);
    }

    public(friend) fun emit_ship_created(ship_created: ShipCreated) {
        assert!(std::option::is_some(&ship_created.id), EEmptyObjectID);
        event::emit(ship_created);
    }

}
