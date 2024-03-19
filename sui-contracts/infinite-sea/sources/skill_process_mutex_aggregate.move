// <autogenerated>
//   This file was generated by dddappp code generator.
//   Any changes made to this file manually will be lost next time the file is regenerated.
// </autogenerated>

#[allow(unused_mut_parameter)]
module infinite_sea::skill_process_mutex_aggregate {
    use infinite_sea::skill_process_mutex;
    use infinite_sea::skill_process_mutex_create_logic;
    use infinite_sea::skill_process_mutex_lock_logic;
    use infinite_sea::skill_process_mutex_unlock_logic;
    use sui::object::ID;
    use sui::tx_context;

    friend infinite_sea::skill_process_start_mutex_creation_logic;
    friend infinite_sea::skill_process_complete_mutex_creation_logic;
    friend infinite_sea::skill_process_service;

    public entry fun create(
        player_id: ID,
        skill_process_mutex_table: &mut skill_process_mutex::SkillProcessMutexTable,
        ctx: &mut tx_context::TxContext,
    ) {
        let skill_process_mutex_created = skill_process_mutex_create_logic::verify(
            player_id,
            skill_process_mutex_table,
            ctx,
        );
        let skill_process_mutex = skill_process_mutex_create_logic::mutate(
            &skill_process_mutex_created,
            skill_process_mutex_table,
            ctx,
        );
        skill_process_mutex::set_skill_process_mutex_created_id(&mut skill_process_mutex_created, skill_process_mutex::id(&skill_process_mutex));
        skill_process_mutex::share_object(skill_process_mutex);
        skill_process_mutex::emit_skill_process_mutex_created(skill_process_mutex_created);
    }

    public(friend) fun lock(
        skill_process_mutex: &mut skill_process_mutex::SkillProcessMutex,
        skill_type: u8,
        ctx: &mut tx_context::TxContext,
    ) {
        let skill_process_mutex_locked = skill_process_mutex_lock_logic::verify(
            skill_type,
            skill_process_mutex,
            ctx,
        );
        skill_process_mutex_lock_logic::mutate(
            &skill_process_mutex_locked,
            skill_process_mutex,
            ctx,
        );
        skill_process_mutex::update_object_version(skill_process_mutex);
        skill_process_mutex::emit_skill_process_mutex_locked(skill_process_mutex_locked);
    }

    public(friend) fun unlock(
        skill_process_mutex: &mut skill_process_mutex::SkillProcessMutex,
        skill_type: u8,
        ctx: &mut tx_context::TxContext,
    ) {
        let skill_process_mutex_unlocked = skill_process_mutex_unlock_logic::verify(
            skill_type,
            skill_process_mutex,
            ctx,
        );
        skill_process_mutex_unlock_logic::mutate(
            &skill_process_mutex_unlocked,
            skill_process_mutex,
            ctx,
        );
        skill_process_mutex::update_object_version(skill_process_mutex);
        skill_process_mutex::emit_skill_process_mutex_unlocked(skill_process_mutex_unlocked);
    }

}
