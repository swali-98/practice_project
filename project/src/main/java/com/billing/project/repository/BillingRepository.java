package com.billing.project.repository;

import com.billing.project.entity.BIllingAccount;

import java.math.BigDecimal;

public interface BillingRepository {

    public void save(BIllingAccount bIllingAccount);

    public BIllingAccount findByPatientId(Long id);

    public BIllingAccount deleteById(Long id);

    public void update(BigDecimal transactons);
}
