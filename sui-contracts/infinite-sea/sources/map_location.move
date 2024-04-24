// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module infinite_sea::map_location {
    use infinite_sea_common::coordinates::Coordinates;
    use infinite_sea_common::item_id_quantity_pair::ItemIdQuantityPair;
    use std::option::Option;
    use sui::object::ID;
    friend infinite_sea::map;

    #[allow(unused_const)]
    const EDataTooLong: u64 = 102;

    struct MapLocation has store {
        coordinates: Coordinates,
        type: u32,
        occupied_by: Option<ID>,
        resources: vector<ItemIdQuantityPair>,
        gathered_at: u64,
    }

    public fun coordinates(map_location: &MapLocation): Coordinates {
        map_location.coordinates
    }

    public fun type(map_location: &MapLocation): u32 {
        map_location.type
    }

    public(friend) fun set_type(map_location: &mut MapLocation, type: u32) {
        map_location.type = type;
    }

    public fun occupied_by(map_location: &MapLocation): Option<ID> {
        map_location.occupied_by
    }

    public(friend) fun set_occupied_by(map_location: &mut MapLocation, occupied_by: Option<ID>) {
        map_location.occupied_by = occupied_by;
    }

    public fun borrow_resources(map_location: &MapLocation): &vector<ItemIdQuantityPair> {
        &map_location.resources
    }

    public(friend) fun borrow_mut_resources(map_location: &mut MapLocation): &mut vector<ItemIdQuantityPair> {
        &mut map_location.resources
    }

    public fun resources(map_location: &MapLocation): vector<ItemIdQuantityPair> {
        map_location.resources
    }

    public(friend) fun set_resources(map_location: &mut MapLocation, resources: vector<ItemIdQuantityPair>) {
        map_location.resources = resources;
    }

    public fun gathered_at(map_location: &MapLocation): u64 {
        map_location.gathered_at
    }

    public(friend) fun set_gathered_at(map_location: &mut MapLocation, gathered_at: u64) {
        map_location.gathered_at = gathered_at;
    }

    public(friend) fun new_map_location(
        coordinates: Coordinates,
        type: u32,
        occupied_by: Option<ID>,
        resources: vector<ItemIdQuantityPair>,
        gathered_at: u64,
    ): MapLocation {
        MapLocation {
            coordinates,
            type,
            occupied_by,
            resources,
            gathered_at,
        }
    }

    public(friend) fun drop_map_location(map_location: MapLocation) {
        let MapLocation {
            coordinates: _,
            type: _,
            occupied_by: _,
            resources: _,
            gathered_at: _,
        } = map_location;
    }


}
