package edu.mum.cs.waa.project.bazargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //todo transient field receiptEntry

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<ReceiptEntry> receiptEntries = new ArrayList<>();
    private Double total;
    @OneToOne(mappedBy = "receipt")
    private ProductOrder productOrder;
}
