// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

module infinite_sea::skill_process {
    use infinite_sea_common::skill_type_player_id_pair::SkillTypePlayerIdPair;
    use std::option;
    use sui::event;
    use sui::object::{Self, UID};
    use sui::table;
    use sui::transfer;
    use sui::tx_context::TxContext;

    struct SKILL_PROCESS has drop {}

    friend infinite_sea::skill_process_create_logic;
    friend infinite_sea::skill_process_start_production_logic;
    friend infinite_sea::skill_process_complete_production_logic;
    friend infinite_sea::skill_process_aggregate;

    const EIdAlreadyExists: u64 = 101;
    #[allow(unused_const)]
    const EDataTooLong: u64 = 102;
    const EInappropriateVersion: u64 = 103;
    const EEmptyObjectID: u64 = 107;

    struct SkillProcessTable has key {
        id: UID,
        table: table::Table<SkillTypePlayerIdPair, object::ID>,
    }

    struct SkillProcessTableCreated has copy, drop {
        id: object::ID,
    }

    fun init(otw: SKILL_PROCESS, ctx: &mut TxContext) {
        sui::package::claim_and_keep(otw, ctx);
        let id_generator_table = SkillProcessTable {
            id: object::new(ctx),
            table: table::new(ctx),
        };
        let id_generator_table_id = object::uid_to_inner(&id_generator_table.id);
        transfer::share_object(id_generator_table);
        event::emit(SkillProcessTableCreated {
            id: id_generator_table_id,
        });
    }

    struct SkillProcess has key {
        id: UID,
        skill_process_id: SkillTypePlayerIdPair,
        version: u64,
        item_id: u32,
        started_at: u64,
        creation_time: u64,
        completed: bool,
        ended_at: u64,
    }

    public fun id(skill_process: &SkillProcess): object::ID {
        object::uid_to_inner(&skill_process.id)
    }

    public fun skill_process_id(skill_process: &SkillProcess): SkillTypePlayerIdPair {
        skill_process.skill_process_id
    }

    public fun version(skill_process: &SkillProcess): u64 {
        skill_process.version
    }

    public fun item_id(skill_process: &SkillProcess): u32 {
        skill_process.item_id
    }

    public(friend) fun set_item_id(skill_process: &mut SkillProcess, item_id: u32) {
        skill_process.item_id = item_id;
    }

    public fun started_at(skill_process: &SkillProcess): u64 {
        skill_process.started_at
    }

    public(friend) fun set_started_at(skill_process: &mut SkillProcess, started_at: u64) {
        skill_process.started_at = started_at;
    }

    public fun creation_time(skill_process: &SkillProcess): u64 {
        skill_process.creation_time
    }

    public(friend) fun set_creation_time(skill_process: &mut SkillProcess, creation_time: u64) {
        skill_process.creation_time = creation_time;
    }

    public fun completed(skill_process: &SkillProcess): bool {
        skill_process.completed
    }

    public(friend) fun set_completed(skill_process: &mut SkillProcess, completed: bool) {
        skill_process.completed = completed;
    }

    public fun ended_at(skill_process: &SkillProcess): u64 {
        skill_process.ended_at
    }

    public(friend) fun set_ended_at(skill_process: &mut SkillProcess, ended_at: u64) {
        skill_process.ended_at = ended_at;
    }

    fun new_skill_process(
        skill_process_id: SkillTypePlayerIdPair,
        ctx: &mut TxContext,
    ): SkillProcess {
        SkillProcess {
            id: object::new(ctx),
            skill_process_id,
            version: 0,
            item_id: infinite_sea_common::item_id::unused_item(),
            started_at: 0,
            creation_time: 0,
            completed: true,
            ended_at: 0,
        }
    }

    struct SkillProcessCreated has copy, drop {
        id: option::Option<object::ID>,
        skill_process_id: SkillTypePlayerIdPair,
    }

    public fun skill_process_created_id(skill_process_created: &SkillProcessCreated): option::Option<object::ID> {
        skill_process_created.id
    }

    public(friend) fun set_skill_process_created_id(skill_process_created: &mut SkillProcessCreated, id: object::ID) {
        skill_process_created.id = option::some(id);
    }

    public fun skill_process_created_skill_process_id(skill_process_created: &SkillProcessCreated): SkillTypePlayerIdPair {
        skill_process_created.skill_process_id
    }

    public(friend) fun new_skill_process_created(
        skill_process_id: SkillTypePlayerIdPair,
    ): SkillProcessCreated {
        SkillProcessCreated {
            id: option::none(),
            skill_process_id,
        }
    }

    struct ProductionProcessStarted has copy, drop {
        id: object::ID,
        skill_process_id: SkillTypePlayerIdPair,
        version: u64,
        item_id: u32,
        energy_cost: u64,
        started_at: u64,
        creation_time: u64,
    }

    public fun production_process_started_id(production_process_started: &ProductionProcessStarted): object::ID {
        production_process_started.id
    }

    public fun production_process_started_skill_process_id(production_process_started: &ProductionProcessStarted): SkillTypePlayerIdPair {
        production_process_started.skill_process_id
    }

    public fun production_process_started_item_id(production_process_started: &ProductionProcessStarted): u32 {
        production_process_started.item_id
    }

    public fun production_process_started_energy_cost(production_process_started: &ProductionProcessStarted): u64 {
        production_process_started.energy_cost
    }

    public fun production_process_started_started_at(production_process_started: &ProductionProcessStarted): u64 {
        production_process_started.started_at
    }

    public fun production_process_started_creation_time(production_process_started: &ProductionProcessStarted): u64 {
        production_process_started.creation_time
    }

    public(friend) fun new_production_process_started(
        skill_process: &SkillProcess,
        item_id: u32,
        energy_cost: u64,
        started_at: u64,
        creation_time: u64,
    ): ProductionProcessStarted {
        ProductionProcessStarted {
            id: id(skill_process),
            skill_process_id: skill_process_id(skill_process),
            version: version(skill_process),
            item_id,
            energy_cost,
            started_at,
            creation_time,
        }
    }

    struct ProductionProcessCompleted has copy, drop {
        id: object::ID,
        skill_process_id: SkillTypePlayerIdPair,
        version: u64,
        item_id: u32,
        started_at: u64,
        creation_time: u64,
        ended_at: u64,
        successful: bool,
    }

    public fun production_process_completed_id(production_process_completed: &ProductionProcessCompleted): object::ID {
        production_process_completed.id
    }

    public fun production_process_completed_skill_process_id(production_process_completed: &ProductionProcessCompleted): SkillTypePlayerIdPair {
        production_process_completed.skill_process_id
    }

    public fun production_process_completed_item_id(production_process_completed: &ProductionProcessCompleted): u32 {
        production_process_completed.item_id
    }

    public fun production_process_completed_started_at(production_process_completed: &ProductionProcessCompleted): u64 {
        production_process_completed.started_at
    }

    public fun production_process_completed_creation_time(production_process_completed: &ProductionProcessCompleted): u64 {
        production_process_completed.creation_time
    }

    public fun production_process_completed_ended_at(production_process_completed: &ProductionProcessCompleted): u64 {
        production_process_completed.ended_at
    }

    public fun production_process_completed_successful(production_process_completed: &ProductionProcessCompleted): bool {
        production_process_completed.successful
    }

    public(friend) fun new_production_process_completed(
        skill_process: &SkillProcess,
        item_id: u32,
        started_at: u64,
        creation_time: u64,
        ended_at: u64,
        successful: bool,
    ): ProductionProcessCompleted {
        ProductionProcessCompleted {
            id: id(skill_process),
            skill_process_id: skill_process_id(skill_process),
            version: version(skill_process),
            item_id,
            started_at,
            creation_time,
            ended_at,
            successful,
        }
    }


    public(friend) fun create_skill_process(
        skill_process_id: SkillTypePlayerIdPair,
        skill_process_table: &mut SkillProcessTable,
        ctx: &mut TxContext,
    ): SkillProcess {
        let skill_process = new_skill_process(
            skill_process_id,
            ctx,
        );
        asset_skill_process_id_not_exists_then_add(skill_process_id, skill_process_table, object::uid_to_inner(&skill_process.id));
        skill_process
    }

    public(friend) fun asset_skill_process_id_not_exists(
        skill_process_id: SkillTypePlayerIdPair,
        skill_process_table: &SkillProcessTable,
    ) {
        assert!(!table::contains(&skill_process_table.table, skill_process_id), EIdAlreadyExists);
    }

    fun asset_skill_process_id_not_exists_then_add(
        skill_process_id: SkillTypePlayerIdPair,
        skill_process_table: &mut SkillProcessTable,
        id: object::ID,
    ) {
        asset_skill_process_id_not_exists(skill_process_id, skill_process_table);
        table::add(&mut skill_process_table.table, skill_process_id, id);
    }

    public(friend) fun transfer_object(skill_process: SkillProcess, recipient: address) {
        assert!(skill_process.version == 0, EInappropriateVersion);
        transfer::transfer(skill_process, recipient);
    }

    public(friend) fun update_version_and_transfer_object(skill_process: SkillProcess, recipient: address) {
        update_object_version(&mut skill_process);
        transfer::transfer(skill_process, recipient);
    }

    #[lint_allow(share_owned)]
    public(friend) fun share_object(skill_process: SkillProcess) {
        assert!(skill_process.version == 0, EInappropriateVersion);
        transfer::share_object(skill_process);
    }

    public(friend) fun freeze_object(skill_process: SkillProcess) {
        assert!(skill_process.version == 0, EInappropriateVersion);
        transfer::freeze_object(skill_process);
    }

    public(friend) fun update_version_and_freeze_object(skill_process: SkillProcess) {
        update_object_version(&mut skill_process);
        transfer::freeze_object(skill_process);
    }

    public(friend) fun update_object_version(skill_process: &mut SkillProcess) {
        skill_process.version = skill_process.version + 1;
        //assert!(skill_process.version != 0, EInappropriateVersion);
    }

    public(friend) fun drop_skill_process(skill_process: SkillProcess) {
        let SkillProcess {
            id,
            skill_process_id: _skill_process_id,
            version: _version,
            item_id: _item_id,
            started_at: _started_at,
            creation_time: _creation_time,
            completed: _completed,
            ended_at: _ended_at,
        } = skill_process;
        object::delete(id);
    }

    public(friend) fun emit_skill_process_created(skill_process_created: SkillProcessCreated) {
        assert!(std::option::is_some(&skill_process_created.id), EEmptyObjectID);
        event::emit(skill_process_created);
    }

    public(friend) fun emit_production_process_started(production_process_started: ProductionProcessStarted) {
        event::emit(production_process_started);
    }

    public(friend) fun emit_production_process_completed(production_process_completed: ProductionProcessCompleted) {
        event::emit(production_process_completed);
    }

}
