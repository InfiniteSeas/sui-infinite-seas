#!/bin/bash

# Before running this script, please make sure that the "Move.toml" files of all the projects (packages) to be published
# have been adjusted as follows:
#
#[package]
##published-at = ... # <- This line does not exist or has been commented out.
#
#[addresses]
#{PACKAGE_NAME} = "0x0"


# -------- Constants --------
move_toml_file="Move.toml"
move_toml_temp="Move-temp.toml"

#player_owner="0xfc50aa2363f3b3c5d80631cae512ec51a8ba94080500a981f4ae1a2ce4d201c2"
# External Package IDs
# ...

# -------------------------
# ENERGY coin object Ids
#energy_coin_object_id_1="0x4414dc3e17211ecf8b27466774309cee213befd2cd5b4f331e68ee0f050f7604"

# The following are the object IDs of the SUI objects that are used in the following script.
# Make sure the amounts of the following SUI objects are greater than xxx
#sui_coin_object_id_1="0xfdf3344392babaf053e0293218cb901236dd43c3abf52a1cf3b5af17ee1b9e20"
#sui_coin_object_id_2="0xdd4aea51975a506e1e5451e38f340c3444ae03c4b2533afd04253f7a53a8b4d0"

# ----------------------------------------------------------------------------------------
# Publish coin package
cd infinite-sea-coin
log_file="../testnet-publish.log"
echo "#-------- publish coin package --------" | tee -a "$log_file"
publish_json_file="testnet_coin_publish.json"

sui client publish --gas-budget 200000000 --skip-fetch-latest-git-deps --skip-dependency-verification --json > "$publish_json_file"

publish_coin_txn_digest=$(jq -r '.digest' "$publish_json_file")
echo "publish coin package txn_digest: $publish_coin_txn_digest" | tee -a "$log_file"

if [ -z "$publish_coin_txn_digest" ]
then
echo "The publish_coin_txn_digest is empty, exit the script." | tee -a "$log_file"
exit 1
fi

coin_package_id=$(jq -r '.objectChanges[] | select(.type == "published").packageId' "$publish_json_file")
echo "coin package_id: $coin_package_id" | tee -a "$log_file"
echo "" | tee -a "$log_file"

energy_coin_treasury_cap_object_id=""

while read -r line
do
  objectType=$(echo "$line" | jq -r '.objectType')
  echo "objectType: $objectType" | tee -a "$log_file"
  objectId=$(echo "$line" | jq -r '.objectId')
  echo "objectId: $objectId" | tee -a "$log_file"
  echo "" | tee -a "$log_file"
  if [[ $objectType == *::coin::TreasuryCap* ]]
  then
    energy_coin_treasury_cap_object_id=$objectId
  fi
done < <(jq -c '.objectChanges[] | select(.type == "created")' "$publish_json_file")
echo "energy_coin_treasury_cap_object_id: $energy_coin_treasury_cap_object_id" | tee -a "$log_file"

# -------- Mint ENERGY coins --------
sui client call --package "$coin_package_id" --module energy --function mint \
--args "$energy_coin_treasury_cap_object_id" '1000000000' \
--gas-budget 10000000 --json  > testnet_mint_energy_coin.json

energy_coin_object_id_1=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("coin::Coin<")).objectId' testnet_mint_energy_coin.json)
echo "energy_coin_object_id_1: $energy_coin_object_id_1" | tee -a "$log_file"

sui client call --package "$coin_package_id" --module energy --function mint \
--args "$energy_coin_treasury_cap_object_id" '1000000000' \
--gas-budget 10000000 --json  > testnet_mint_energy_coin.json

energy_coin_object_id_2=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("coin::Coin<")).objectId' testnet_mint_energy_coin.json)
echo "energy_coin_object_id_2: $energy_coin_object_id_2" | tee -a "$log_file"


# -------- update coin Move.toml --------
# read every line of Move.toml
while read -r line
do
# if the line starts with "published-at =", then add "#" in front of it
if [[ $line == "published-at ="* ]]
  then
    echo "#$line" >> $move_toml_temp
# if the line is "[package]", then add "published-at = $package_id" below it
elif [[ $line == "[package]" ]]
  then
    echo "$line" >> $move_toml_temp
    echo "published-at = \"$coin_package_id\"" >> $move_toml_temp
elif [[ $line == "infinite_sea_coin ="* ]]
  then
    echo "#$line" >> $move_toml_temp
# if the line is "[addresses]", then add a line below it
elif [[ $line == "[addresses]" ]]
  then
    echo "$line" >> $move_toml_temp
    echo "infinite_sea_coin = \"$coin_package_id\"" >> $move_toml_temp
# else, keep the line unchanged
else
  echo "$line" >> $move_toml_temp
fi
done < $move_toml_file

# replace the original file with the temp file
mv $move_toml_temp $move_toml_file
cd ..

##exit 1


# ----------------------------------------------------------------------------------------
# Publish common package

cd infinite-sea-common
log_file="../testnet-publish.log"
echo "#-------- publish common package --------" | tee -a "$log_file"
publish_json_file="testnet_common_publish.json"

sui client publish --gas-budget 400000000 --skip-fetch-latest-git-deps --skip-dependency-verification --json > "$publish_json_file"

publish_common_txn_digest=$(jq -r '.digest' "$publish_json_file")
echo "publish common package txn_digest: $publish_common_txn_digest" | tee -a "$log_file"

if [ -z "$publish_common_txn_digest" ]
then
echo "The publish_common_txn_digest is empty, exit the script." | tee -a "$log_file"
exit 1
fi

common_package_id=$(jq -r '.objectChanges[] | select(.type == "published").packageId' "$publish_json_file")
echo "common package_id: $common_package_id" | tee -a "$log_file"
echo "" | tee -a "$log_file"

common_publisher_object_id=""
experience_table_object_id=""
item_table_object_id=""
item_production_table_object_id=""
item_creation_table_object_id=""

while read -r line
do
  objectType=$(echo "$line" | jq -r '.objectType')
  echo "objectType: $objectType" | tee -a "$log_file"
  objectId=$(echo "$line" | jq -r '.objectId')
  echo "objectId: $objectId" | tee -a "$log_file"
  echo "" | tee -a "$log_file"
  if [[ $objectType == *::package::Publisher ]]
  then
    common_publisher_object_id=$objectId
  elif [[ $objectType == *::experience_table::ExperienceTable ]]
  then
    experience_table_object_id=$objectId
  elif [[ $objectType == *::item::ItemTable ]]
  then
    item_table_object_id=$objectId
  elif [[ $objectType == *::item_production::ItemProductionTable ]]
  then
    item_production_table_object_id=$objectId
  elif [[ $objectType == *::item_creation::ItemCreationTable ]]
  then
    item_creation_table_object_id=$objectId
  fi
done < <(jq -c '.objectChanges[] | select(.type == "created")' "$publish_json_file")
echo "common_publisher_object_id: $common_publisher_object_id" | tee -a "$log_file"
echo "experience_table_object_id: $experience_table_object_id" | tee -a "$log_file"
echo "item_table_object_id: $item_table_object_id" | tee -a "$log_file"
echo "item_production_table_object_id: $item_production_table_object_id" | tee -a "$log_file"
echo "item_creation_table_object_id: $item_creation_table_object_id" | tee -a "$log_file"

# -------- update common Move.toml --------
while read -r line
do
if [[ $line == "published-at ="* ]]
  then
    echo "#$line" >> $move_toml_temp
elif [[ $line == "[package]" ]]
  then
    echo "$line" >> $move_toml_temp
    echo "published-at = \"$common_package_id\"" >> $move_toml_temp
elif [[ $line == "infinite_sea_common ="* ]]
  then
    echo "#$line" >> $move_toml_temp
elif [[ $line == "[addresses]" ]]
  then
    echo "$line" >> $move_toml_temp
    echo "infinite_sea_common = \"$common_package_id\"" >> $move_toml_temp
else
  echo "$line" >> $move_toml_temp
fi
done < $move_toml_file

mv $move_toml_temp $move_toml_file
cd ..


# ----------------------------------------------------------------------------------------
# Publish default package

cd infinite-sea
log_file="../testnet-publish.log"
echo "#-------- publish default package --------" | tee -a "$log_file"
publish_json_file="testnet_default_publish.json"

sui client publish --gas-budget 400000000 --skip-fetch-latest-git-deps --skip-dependency-verification --json > "$publish_json_file"

publish_default_txn_digest=$(jq -r '.digest' "$publish_json_file")
echo "publish default package txn_digest: $publish_default_txn_digest" | tee -a "$log_file"

if [ -z "$publish_default_txn_digest" ]
then
echo "The publish_default_txn_digest is empty, exit the script." | tee -a "$log_file"
exit 1
fi

default_package_id=$(jq -r '.objectChanges[] | select(.type == "published").packageId' "$publish_json_file")
echo "default package_id: $default_package_id" | tee -a "$log_file"
echo "" | tee -a "$log_file"

default_publisher_object_id=""
skill_process_table_object_id=""
skill_process_mutex_table_object_id=""

while read -r line
do
  objectType=$(echo "$line" | jq -r '.objectType')
  echo "objectType: $objectType" | tee -a "$log_file"
  objectId=$(echo "$line" | jq -r '.objectId')
  echo "objectId: $objectId" | tee -a "$log_file"
  echo "" | tee -a "$log_file"
  if [[ $objectType == *::package::Publisher ]]
  then
    default_publisher_object_id=$objectId
  elif [[ $objectType == *::skill_process::SkillProcessTable ]]
  then
    skill_process_table_object_id=$objectId
  elif [[ $objectType == *::skill_process_mutex::SkillProcessMutexTable ]]
  then
    skill_process_mutex_table_object_id=$objectId
  fi
done < <(jq -c '.objectChanges[] | select(.type == "created")' "$publish_json_file")
echo "default_publisher_object_id: $default_publisher_object_id" | tee -a "$log_file"
echo "skill_process_table_object_id: $skill_process_table_object_id" | tee -a "$log_file"
echo "skill_process_mutex_table_object_id: $skill_process_mutex_table_object_id" | tee -a "$log_file"


# -------- update default Move.toml --------
while read -r line
do
if [[ $line == "published-at ="* ]]
  then
    echo "#$line" >> $move_toml_temp
elif [[ $line == "[package]" ]]
  then
    echo "$line" >> $move_toml_temp
    echo "published-at = \"$default_package_id\"" >> $move_toml_temp
elif [[ $line == "infinite_sea ="* ]]
  then
    echo "#$line" >> $move_toml_temp
elif [[ $line == "[addresses]" ]]
  then
    echo "$line" >> $move_toml_temp
    echo "infinite_sea = \"$default_package_id\"" >> $move_toml_temp
else
  echo "$line" >> $move_toml_temp
fi
done < $move_toml_file

mv $move_toml_temp $move_toml_file
cd ..

# --------------------------------------------
log_file="./testnet-publish.log"


# ---------- Add XP levels -------------
sui client call --package "$common_package_id" --module experience_table_aggregate --function add_level \
--args "$experience_table_object_id" "$common_publisher_object_id" '0' '0' '0' \
--gas-budget 11000000

sui client call --package "$common_package_id" --module experience_table_aggregate --function add_level \
--args "$experience_table_object_id" "$common_publisher_object_id" '1' '0' '0' \
--gas-budget 11000000

sui client call --package "$common_package_id" --module experience_table_aggregate --function add_level \
--args "$experience_table_object_id" "$common_publisher_object_id" '2' '83' '83' \
--gas-budget 11000000

sui client call --package "$common_package_id" --module experience_table_aggregate --function add_level \
--args "$experience_table_object_id" "$common_publisher_object_id" '3' '174' '91' \
--gas-budget 11000000

# ------------ Add items ------------
sui client call --package "$common_package_id" --module item_aggregate --function create \
--args \
'0' \
"$common_publisher_object_id" \
'"UNUSED_ITEM"'  \
'false' \
'0' \
"$item_table_object_id" \
--gas-budget 11000000 --json > testnet_create_item_0.json
item_object_id_0=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("item::Item")).objectId' testnet_create_item_0.json)
echo "item_object_id_0: $item_object_id_0" | tee -a "$log_file"

sui client call --package "$common_package_id" --module item_aggregate --function create \
--args \
'1' \
"$common_publisher_object_id" \
'"PotatoSeeds"'  \
'false' \
'10' \
"$item_table_object_id" \
--gas-budget 11000000 --json > testnet_create_item_1.json
item_object_id_1=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("item::Item")).objectId' testnet_create_item_1.json)
echo "item_object_id_1: $item_object_id_1" | tee -a "$log_file"

sui client call --package "$common_package_id" --module item_aggregate --function create \
--args \
'2' \
"$common_publisher_object_id" \
'"Potatoes"'  \
'false' \
'80' \
"$item_table_object_id" \
--gas-budget 11000000 --json > testnet_create_item_2.json
item_object_id_2=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("item::Item")).objectId' testnet_create_item_2.json)
echo "item_object_id_2: $item_object_id_2" | tee -a "$log_file"


sui client call --package "$common_package_id" --module item_aggregate --function create \
--args \
'30' \
"$common_publisher_object_id" \
'"CoalOre"'  \
'false' \
'80' \
"$item_table_object_id" \
--gas-budget 11000000 --json > testnet_create_item_30.json

item_object_id_30=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("item::Item")).objectId' testnet_create_item_30.json)
echo "item_object_id_30: $item_object_id_30" | tee -a "$log_file"


# -------- Add resource type --------

# RESOURCE_TYPE_MINING 2_000_000_003
sui client call --package "$common_package_id" --module item_aggregate --function create \
--args \
'"2000000003"' \
"$common_publisher_object_id" \
'"RESOURCE_TYPE_MINING"'  \
'false' \
'80' \
"$item_table_object_id" \
--gas-budget 11000000 --json > testnet_create_RESOURCE_TYPE_MINING.json

RESOURCE_TYPE_MINING_object_id=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("item::Item")).objectId' testnet_create_RESOURCE_TYPE_MINING.json)
echo "RESOURCE_TYPE_MINING_object_id: $RESOURCE_TYPE_MINING_object_id" | tee -a "$log_file"


# ------------- Add item production ----------------
sui client call --package "$common_package_id" --module item_production_aggregate --function create \
--args '0' '2' "$common_publisher_object_id" \
'[1]' '[3]' \
'1' '10' '85' '5' '5' '100' \
"$item_production_table_object_id" \
--gas-budget 11000000 --json > testnet_create_item_production.json

item_production_id_1=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("item_production::ItemProduction")).objectId' testnet_create_item_production.json)
echo "item_production_id_1: $item_production_id_1" | tee -a "$log_file"


# ------------- Add item creation ----------------
sui client call --package "$common_package_id" --module item_creation_aggregate --function create \
--args '3' '30' "$common_publisher_object_id" \
'1' \
'1' '5' '85' '5' '5' '100' \
"$item_creation_table_object_id" \
--gas-budget 11000000 --json > testnet_create_item_creation.json

item_creation_id_1=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("item_creation::ItemCreation")).objectId' testnet_create_item_creation.json)
echo "item_creation_id_1: $item_creation_id_1" | tee -a "$log_file"


# ------------------- Create player -------------------
sui client call --package "$default_package_id" --module player_aggregate --function create \
--gas-budget 11000000 --json > testnet_create_player.json

player_id=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("player::Player")).objectId' testnet_create_player.json)
echo "player_id: $player_id" | tee -a "$log_file"

# --------------- Airdrop player --------------------
sui client call --package "$default_package_id" --module player_aggregate --function airdrop \
--args "$player_id" \
"$default_publisher_object_id" \
'1' '100' \
--gas-budget 11000000

# RESOURCE_TYPE_MINING 2_000_000_003
sui client call --package "$default_package_id" --module player_aggregate --function airdrop \
--args "$player_id" \
"$default_publisher_object_id" \
'"2000000003"' '10' \
--gas-budget 11000000

# --------------- Create skill process ---------------
sui client call --package "$default_package_id" --module skill_process_aggregate --function create \
--args '0' "$player_id" \
"$player_id" \
"$skill_process_table_object_id" \
--gas-budget 11000000 --json > testnet_create_skill_process_1.json

skill_process_object_id_1=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("skill_process::SkillProcess")).objectId' testnet_create_skill_process_1.json)
echo "skill_process_object_id_1: $skill_process_object_id_1" | tee -a "$log_file"


sui client call --package "$default_package_id" --module skill_process_aggregate --function create \
--args '3' "$player_id" \
"$player_id" \
"$skill_process_table_object_id" \
--gas-budget 11000000 --json > testnet_create_skill_process_2.json

skill_process_object_id_2=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("skill_process::SkillProcess")).objectId' testnet_create_skill_process_2.json)
echo "skill_process_object_id_2: $skill_process_object_id_2" | tee -a "$log_file"


# -------------- Start production process ----------------
sui client call --package "$default_package_id" --module skill_process_service --function start_production \
--args "$skill_process_object_id_1" \
"$player_id" \
"$item_production_id_1" \
"0x6" \
"$energy_coin_object_id_1" \
--gas-budget 11000000 #--json > testnet_start_skill_process_1.json


# -------------- Complete production process ----------------
sleep 8 # wait for 8 seconds

sui client call --package "$default_package_id" --module skill_process_aggregate --function complete_production \
--args "$skill_process_object_id_1" \
"$player_id" \
"$item_production_id_1" \
"$experience_table_object_id" \
"0x6" \
--gas-budget 11000000 #--json > testnet_complete_skill_process_1.json


# -------------- Create skill process mutex ----------------
# player_id: ID,
# skill_process_mutex_table: &mut skill_process_mutex::SkillProcessMutexTable,
sui client call --package "$default_package_id" --module skill_process_mutex_aggregate --function create \
--args "$player_id" \
"$skill_process_mutex_table_object_id" \
--gas-budget 11000000 --json > testnet_create_skill_process_mutex_1.json

skill_process_mutex_object_id_1=$(jq -r '.objectChanges[] | select(.type == "created") | select(.objectType | test("skill_process_mutex::SkillProcessMutex")).objectId' testnet_create_skill_process_mutex_1.json)
echo "skill_process_mutex_object_id_1: $skill_process_mutex_object_id_1" | tee -a "$log_file"


# -------------- Start mutex creation process ----------------
sui client call --package "$default_package_id" --module skill_process_service --function start_mutex_creation \
--args "$skill_process_object_id_2" \
"$skill_process_mutex_object_id_1" \
"$player_id" \
"$item_creation_id_1" \
"0x6" \
"$energy_coin_object_id_2" \
--gas-budget 11000000 #--json > testnet_start_skill_process_2.json

# -------------- Complete mutex creation process ----------------
sleep 8 # wait for 8 seconds

sui client call --package "$default_package_id" --module skill_process_aggregate --function complete_mutex_creation \
--args "$skill_process_object_id_2" \
"$skill_process_mutex_object_id_1" \
"$player_id" \
"$item_creation_id_1" \
"$experience_table_object_id" \
"0x6" \
--gas-budget 11000000 #--json > testnet_complete_skill_process_2.json


