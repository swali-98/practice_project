package com.billing.project.repository;

import com.billing.project.entity.BIllingAccount;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Repository
public class BIllingRepositoryImpl implements BillingRepository{

    private static Long accountNo=1L;

    private static Map<Long,BIllingAccount> bIllingAccountMap=new HashMap<>();
    @Override
    public void save(BIllingAccount bIllingAccount) {
        bIllingAccount.setAccountNo(accountNo);
        Long id=bIllingAccount.getPatient().getPatientId();
        bIllingAccountMap.put(id,bIllingAccount);
        System.out.println("billingaccount added and id is"+id +"  "+bIllingAccount.toString());
        accountNo++;
    }

    @Override
    public BIllingAccount findByPatientId(Long id) {
        BIllingAccount bIllingAccount=bIllingAccountMap.get(id);
        return bIllingAccount;
    }

    @Override
    public BIllingAccount deleteById(Long id) {
     return   bIllingAccountMap.remove(id);
    }

    @Override
    public void update(BigDecimal transactons) {
       BIllingAccount bIllingAccount= bIllingAccountMap.get(1L);
       bIllingAccount.setTransactions(BigDecimal.valueOf(0).subtract(transactons));
       bIllingAccountMap.put(1L,bIllingAccount);
    }


}
