package lk.ijse.project.dto.tm;

import lombok.*;

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @ToString
    public class DeliveryTM {
        private String dId;
        private String oId;
        private int distance;
        private double amount;
    }
