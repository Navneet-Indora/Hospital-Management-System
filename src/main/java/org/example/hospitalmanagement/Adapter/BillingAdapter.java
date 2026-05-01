package org.example.hospitalmanagement.Adapter;

import org.example.hospitalmanagement.Entity.Billing;
import org.example.hospitalmanagement.dto.request.BillingRequestDto;
import org.example.hospitalmanagement.dto.response.BillingResponseDto;

public interface BillingAdapter {
    Billing toEntity(BillingRequestDto billingRequestDto);

    BillingResponseDto toResponseDto(Billing billing);
}
