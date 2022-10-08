package com.example.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import com.example.model.ProcessingStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Define order
 */
@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name = "order")
public class Order extends BaseEntity {

    /**
     * entity's identification
     */
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * Order number
     */
    @NonNull
    private String orderNo;
    /**
     * Status of order
     */
    @NonNull
    private String status = ProcessingStatus.NEW.getKey();
    /**
     * Station which perform order
     */
    @Nullable
    private Integer stationId;
    /**
     * User creates order
     */
    @NonNull
    private Integer creator;

    // For display on GUI
    /**
     * Status label
     */
    @Transient
    private String statusLabel;
    /**
     * Creator label
     */
    @Transient
    private String creatorLabel;
    /**
     * Station label
     */
    @Transient
    private String stationLabel;

    /**
     * Get order processing status
     *
     * @return processing status
     */
    public ProcessingStatus getStatus() {
        return ProcessingStatus.typeOf(status);
    }

    /**
     * Set order processing status
     *
     * @param status status
     */
    public void setStatus(@NonNull ProcessingStatus status) {
        this.status = status.getKey();
    }

}
