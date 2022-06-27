package com.amjad.valguide.data.remote.response

data class GunsResponse(
    val status: String,
    val data: List<GunList>
)

data class GunList(
    val uuid: String,
    val displayName: String,
    val displayIcon:String,
    val weaponStats: WeaponStat,
    val shopData: ShopData
)

data class WeaponStat(
    val fireRate: String,
    val magazineSize: String,
    val runSpeedMultiplier: String,
    val equipTimeSeconds: String,
    val reloadTimeSeconds: String,
    val firstBulletAccuracy: String,
    val wallPenetration: String,
    val damageRanges: List<DamageRange>
)

data class DamageRange(
    val rangeStartMeters: String,
    val rangeEndMeters: String,
    val headDamage: String,
    val bodyDamage: String,
    val legDamage: String
)

data class ShopData(
    val cost: String,
    val category: String
)