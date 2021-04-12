package com.example.data.models

data class DeliveryFeeDetails(
    val discount: Discount,
    val final_fee: FinalFee,
    val original_fee: OriginalFee,
    val surge_fee: SurgeFee
)