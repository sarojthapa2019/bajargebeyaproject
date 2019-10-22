package edu.mum.cs.waa.project.bajargebeyaproject.service.serviceImpl;

import edu.mum.cs.waa.project.bajargebeyaproject.domain.Receipt;
import edu.mum.cs.waa.project.bajargebeyaproject.repository.ReceiptRepo;
import edu.mum.cs.waa.project.bajargebeyaproject.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    ReceiptRepo receiptRepo;

    @Override
    public Receipt saveReceipt(Receipt rc) {
        return receiptRepo.save(rc);
    }
}
