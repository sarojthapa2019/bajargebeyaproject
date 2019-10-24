package edu.mum.cs.waa.project.bajargebeyaproject.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //not possible-->todo transient field receiptEntry

    @OneToMany(mappedBy = "receipt", cascade = CascadeType.ALL)
    private List<ReceiptEntry> receiptEntries = new ArrayList<>();
    private Double total=0.0;
    @OneToOne(mappedBy = "receipt")
    private ProductOrder productOrder;

    private LocalDate date;
}
