module infinite_sea_common::ship_util {

    use std::vector;

    use infinite_sea_common::item_id;
    use infinite_sea_common::item_id_quantity_pair;
    use infinite_sea_common::item_id_quantity_pair::ItemIdQuantityPair;
    use infinite_sea_common::sorted_vector_util;

    const DEFAULT_SHIP_HEALTH_POINTS: u32 = 20;

    const ENormalLogsNotFound: u64 = 1;
    const ECottonsNotFound: u64 = 2;
    const ECopperOresNotFound: u64 = 3;

    public fun calculate_ship_attributes(building_expenses: &vector<ItemIdQuantityPair>): (u32, u32, u32, u32) {
        let copper_ore = sorted_vector_util::get_item_id_quantity_pair_or_else_abort(
            building_expenses, item_id::copper_ore(), ECopperOresNotFound);
        let copper_ore_quantity = item_id_quantity_pair::quantity(&copper_ore);
        let normal_logs = sorted_vector_util::get_item_id_quantity_pair_or_else_abort(
            building_expenses, item_id::normal_logs(), ENormalLogsNotFound);
        let normal_log_quantity = item_id_quantity_pair::quantity(&normal_logs);
        let cottons = sorted_vector_util::get_item_id_quantity_pair_or_else_abort(
            building_expenses, item_id::cottons(), ECottonsNotFound);
        let cottons_quantity = item_id_quantity_pair::quantity(&cottons);

        let health_points: u32 = DEFAULT_SHIP_HEALTH_POINTS; //todo ???
        let attack = copper_ore_quantity;
        let protection = normal_log_quantity;
        let speed = cottons_quantity;
        (health_points, attack, protection, speed)
    }

    /// Calculate the experience that can be gained by the building_expenses of the defeated environment ship.
    public fun calculate_environment_ship_experience(building_expenses: &vector<ItemIdQuantityPair>): u32 {
        let i = 0;
        let sum = 0;
        let l = vector::length(building_expenses);
        while (i < l) {
            let item = vector::borrow(building_expenses, i);
            //let item_id = item_id_quantity_pair::item_id(item); // ignore item_id
            let quantity = item_id_quantity_pair::quantity(item);
            sum = sum + quantity;
            i = i + 1;
        };
        if (sum >= 16) {
            6
        } else if (sum >= 12) {
            3
        } else if (sum >= 8) {
            2
        } else {
            1
        }
    }
}
