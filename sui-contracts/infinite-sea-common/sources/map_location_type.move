// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module infinite_sea_common::map_location_type {
    use std::vector;

    public fun island(): u32 {
        0
    }

    public fun is_valid(v: u32): bool {
        v == island()
    }

    public fun are_all_valid(vs: &vector<u32>): bool {
        let i = 0;
        let l = vector::length(vs);
        while (i < l) {
            if (!is_valid(*vector::borrow(vs, i))) {
                return false
            };
            i = i + 1;
        };
        true
    }
}
