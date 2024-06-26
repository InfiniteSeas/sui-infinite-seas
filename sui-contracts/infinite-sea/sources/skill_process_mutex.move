// // <autogenerated>
// //   This file was generated by dddappp code generator.
// //   Any changes made to this file manually will be lost next time the file is regenerated.
// // </autogenerated>
//
// module infinite_sea::skill_process_mutex {
//     use std::option::{Self, Option};
//     use sui::event;
//     use sui::object::{Self, ID, UID};
//     use sui::table;
//     use sui::transfer;
//     use sui::tx_context::TxContext;
//     friend infinite_sea::skill_process_mutex_create_logic;
//     friend infinite_sea::skill_process_mutex_lock_logic;
//     friend infinite_sea::skill_process_mutex_unlock_logic;
//     friend infinite_sea::skill_process_mutex_aggregate;
//
//     const EIdAlreadyExists: u64 = 101;
//     #[allow(unused_const)]
//     const EDataTooLong: u64 = 102;
//     #[allow(unused_const)]
//     const EInappropriateVersion: u64 = 103;
//     const EInvalidEnumValue: u64 = 106;
//     const EEmptyObjectID: u64 = 107;
//
//     struct SkillProcessMutexTable has key {
//         id: UID,
//         table: table::Table<ID, object::ID>,
//     }
//
//     struct SkillProcessMutexTableCreated has copy, drop {
//         id: object::ID,
//     }
//
//     fun init(ctx: &mut TxContext) {
//         let id_generator_table = SkillProcessMutexTable {
//             id: object::new(ctx),
//             table: table::new(ctx),
//         };
//         let id_generator_table_id = object::uid_to_inner(&id_generator_table.id);
//         transfer::share_object(id_generator_table);
//         event::emit(SkillProcessMutexTableCreated {
//             id: id_generator_table_id,
//         });
//     }
//
//     struct SkillProcessMutex has key {
//         id: UID,
//         player_id: ID,
//         version: u64,
//         active_skill_type: Option<u8>,
//     }
//
//     public fun id(skill_process_mutex: &SkillProcessMutex): object::ID {
//         object::uid_to_inner(&skill_process_mutex.id)
//     }
//
//     public fun player_id(skill_process_mutex: &SkillProcessMutex): ID {
//         skill_process_mutex.player_id
//     }
//
//     public fun version(skill_process_mutex: &SkillProcessMutex): u64 {
//         skill_process_mutex.version
//     }
//
//     public fun active_skill_type(skill_process_mutex: &SkillProcessMutex): Option<u8> {
//         skill_process_mutex.active_skill_type
//     }
//
//     public(friend) fun set_active_skill_type(skill_process_mutex: &mut SkillProcessMutex, active_skill_type: Option<u8>) {
//         if (option::is_some(&active_skill_type)) {
//             assert!(infinite_sea_common::skill_type::is_valid(*option::borrow(&active_skill_type)), EInvalidEnumValue);
//         };
//         skill_process_mutex.active_skill_type = active_skill_type;
//     }
//
//     fun new_skill_process_mutex(
//         player_id: ID,
//         ctx: &mut TxContext,
//     ): SkillProcessMutex {
//         SkillProcessMutex {
//             id: object::new(ctx),
//             player_id,
//             version: 0,
//             active_skill_type: std::option::none(),
//         }
//     }
//
//     struct SkillProcessMutexCreated has copy, drop {
//         id: option::Option<object::ID>,
//         player_id: ID,
//     }
//
//     public fun skill_process_mutex_created_id(skill_process_mutex_created: &SkillProcessMutexCreated): option::Option<object::ID> {
//         skill_process_mutex_created.id
//     }
//
//     public(friend) fun set_skill_process_mutex_created_id(skill_process_mutex_created: &mut SkillProcessMutexCreated, id: object::ID) {
//         skill_process_mutex_created.id = option::some(id);
//     }
//
//     public fun skill_process_mutex_created_player_id(skill_process_mutex_created: &SkillProcessMutexCreated): ID {
//         skill_process_mutex_created.player_id
//     }
//
//     public(friend) fun new_skill_process_mutex_created(
//         player_id: ID,
//     ): SkillProcessMutexCreated {
//         SkillProcessMutexCreated {
//             id: option::none(),
//             player_id,
//         }
//     }
//
//     struct SkillProcessMutexLocked has copy, drop {
//         id: object::ID,
//         player_id: ID,
//         version: u64,
//         skill_type: u8,
//     }
//
//     public fun skill_process_mutex_locked_id(skill_process_mutex_locked: &SkillProcessMutexLocked): object::ID {
//         skill_process_mutex_locked.id
//     }
//
//     public fun skill_process_mutex_locked_player_id(skill_process_mutex_locked: &SkillProcessMutexLocked): ID {
//         skill_process_mutex_locked.player_id
//     }
//
//     public fun skill_process_mutex_locked_skill_type(skill_process_mutex_locked: &SkillProcessMutexLocked): u8 {
//         skill_process_mutex_locked.skill_type
//     }
//
//     public(friend) fun new_skill_process_mutex_locked(
//         skill_process_mutex: &SkillProcessMutex,
//         skill_type: u8,
//     ): SkillProcessMutexLocked {
//         SkillProcessMutexLocked {
//             id: id(skill_process_mutex),
//             player_id: player_id(skill_process_mutex),
//             version: version(skill_process_mutex),
//             skill_type,
//         }
//     }
//
//     struct SkillProcessMutexUnlocked has copy, drop {
//         id: object::ID,
//         player_id: ID,
//         version: u64,
//         skill_type: u8,
//     }
//
//     public fun skill_process_mutex_unlocked_id(skill_process_mutex_unlocked: &SkillProcessMutexUnlocked): object::ID {
//         skill_process_mutex_unlocked.id
//     }
//
//     public fun skill_process_mutex_unlocked_player_id(skill_process_mutex_unlocked: &SkillProcessMutexUnlocked): ID {
//         skill_process_mutex_unlocked.player_id
//     }
//
//     public fun skill_process_mutex_unlocked_skill_type(skill_process_mutex_unlocked: &SkillProcessMutexUnlocked): u8 {
//         skill_process_mutex_unlocked.skill_type
//     }
//
//     public(friend) fun new_skill_process_mutex_unlocked(
//         skill_process_mutex: &SkillProcessMutex,
//         skill_type: u8,
//     ): SkillProcessMutexUnlocked {
//         SkillProcessMutexUnlocked {
//             id: id(skill_process_mutex),
//             player_id: player_id(skill_process_mutex),
//             version: version(skill_process_mutex),
//             skill_type,
//         }
//     }
//
//
//     public(friend) fun create_skill_process_mutex(
//         player_id: ID,
//         skill_process_mutex_table: &mut SkillProcessMutexTable,
//         ctx: &mut TxContext,
//     ): SkillProcessMutex {
//         let skill_process_mutex = new_skill_process_mutex(
//             player_id,
//             ctx,
//         );
//         asset_player_id_not_exists_then_add(player_id, skill_process_mutex_table, object::uid_to_inner(&skill_process_mutex.id));
//         skill_process_mutex
//     }
//
//     public(friend) fun asset_player_id_not_exists(
//         player_id: ID,
//         skill_process_mutex_table: &SkillProcessMutexTable,
//     ) {
//         assert!(!table::contains(&skill_process_mutex_table.table, player_id), EIdAlreadyExists);
//     }
//
//     fun asset_player_id_not_exists_then_add(
//         player_id: ID,
//         skill_process_mutex_table: &mut SkillProcessMutexTable,
//         id: object::ID,
//     ) {
//         asset_player_id_not_exists(player_id, skill_process_mutex_table);
//         table::add(&mut skill_process_mutex_table.table, player_id, id);
//     }
//
//     #[lint_allow(share_owned)]
//     public(friend) fun share_object(skill_process_mutex: SkillProcessMutex) {
//         assert!(skill_process_mutex.version == 0, EInappropriateVersion);
//         transfer::share_object(skill_process_mutex);
//     }
//
//     public(friend) fun update_object_version(skill_process_mutex: &mut SkillProcessMutex) {
//         skill_process_mutex.version = skill_process_mutex.version + 1;
//         //assert!(skill_process_mutex.version != 0, EInappropriateVersion);
//     }
//
//     public(friend) fun drop_skill_process_mutex(skill_process_mutex: SkillProcessMutex) {
//         let SkillProcessMutex {
//             id,
//             player_id: _player_id,
//             version: _version,
//             active_skill_type: _active_skill_type,
//         } = skill_process_mutex;
//         object::delete(id);
//     }
//
//     public(friend) fun emit_skill_process_mutex_created(skill_process_mutex_created: SkillProcessMutexCreated) {
//         assert!(std::option::is_some(&skill_process_mutex_created.id), EEmptyObjectID);
//         event::emit(skill_process_mutex_created);
//     }
//
//     public(friend) fun emit_skill_process_mutex_locked(skill_process_mutex_locked: SkillProcessMutexLocked) {
//         event::emit(skill_process_mutex_locked);
//     }
//
//     public(friend) fun emit_skill_process_mutex_unlocked(skill_process_mutex_unlocked: SkillProcessMutexUnlocked) {
//         event::emit(skill_process_mutex_unlocked);
//     }
//
//     #[test_only]
//     /// Wrapper of module initializer for testing
//     public fun test_init(ctx: &mut TxContext) {
//         init(ctx)
//     }
//
// }
