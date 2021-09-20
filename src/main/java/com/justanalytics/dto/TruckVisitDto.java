package com.justanalytics.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

@Data
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonPropertyOrder({"unique_key", "facility_id", "truck_id", "visit_nbr", "visit_phase", "carrier_operator_id",
        "carrier_operator_name", "ata", "atd", "driver_license_nbr", "truck_license_nbr", "entered_yard",
        "exited_yard", "placed_time", "to_location", "move_kind", "from_location", "category", "freight_kind", "placed_by",
        "event_type", "applied_to_id"})
public class TruckVisitDto {

    @JsonProperty(value = "unique_key")
    private String uniqueKey;
    @JsonProperty(value = "facility_id")
    private String facilityId;
    @JsonProperty(value = "truck_id")
    private String truckId;
    @JsonProperty(value = "visit_nbr")
    private String visitNbr;
    @JsonProperty(value = "visit_phase")
    private String visitPhase;
    @JsonProperty(value = "carrier_operator_id")
    private String carrierOperatorId;
    @JsonProperty(value = "carrier_operator_name")
    private String carrierOperatorName;
    @JsonProperty(value = "ata")
    private String ata;
    @JsonProperty(value = "atd")
    private String atd;
    @JsonProperty(value = "driver_license_nbr")
    private String driverLicenseNbr;
    @JsonProperty(value = "truck_license_nbr")
    private String truckLicenseNbr;
    @JsonProperty(value = "entered_yard")
    private String enteredYard;
    @JsonProperty(value = "exited_yard")
    private String exitedYard;
    @JsonProperty(value = "placed_time")
    private String placedTime;
    @JsonProperty(value = "to_location")
    private String toLocation;
    @JsonProperty(value = "move_kind")
    private String moveKind;
    @JsonProperty(value = "from_location")
    private String fromLocation;
    @JsonProperty(value = "category")
    private String category;
    @JsonProperty(value = "freight_kind")
    private String freightKind;
    @JsonProperty(value = "placed_by")
    private String placedBy;
    @JsonProperty(value = "event_type")
    private String eventType;
    @JsonProperty(value = "applied_to_id")
    private String appliedToId;




}
