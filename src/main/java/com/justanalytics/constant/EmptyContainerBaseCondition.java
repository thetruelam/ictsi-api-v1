package com.justanalytics.constant;

public final class EmptyContainerBaseCondition {

    public static final String DEFAULT_CONDITION = "1=1";

    public static final String EMPTY_CONTAINER_BASE_QUERY = "SELECT c.unique_key,\n" +
            "c.operator_id,\n" +
            "c.complex_id,\n" +
            "c.facility_id,\n" +
            "c.visit_state,\n" +
            "c.container_nbr,\n" +
            "c.equipment_type,\n" +
            "c.teu,\n" +
            "c.operator_line_id,\n" +
            "c.operator_name,\n" +
            "c.create_time,\n" +
            "c.category,\n" +
            "c.freight_kind,\n" +
            "c.seal_nbr1,\n" +
            "c.seal_nbr2,\n" +
            "c.seal_nbr3,\n" +
            "c.seal_nbr4,\n" +
            "c.stopped_vessel,\n" +
            "c.stopped_rail,\n" +
            "c.stopped_road,\n" +
            "c.imped_vessel,\n" +
            "c.imped_rail,\n" +
            "c.imped_road,\n" +
            "c.arrive_pos_loctype,\n" +
            "c.arrive_pos_locid,\n" +
            "c.arrive_pos_slot,\n" +
            "c.last_pos_loctype,\n" +
            "c.last_pos_locid,\n" +
            "c.last_pos_slot,\n" +
            "c.time_in,\n" +
            "c.time_out,\n" +
            "c.booking_number,\n" +
            "c.time_state_change,\n" +
            "c.transit_state,\n" +
            "c.nominal_length,\n" +
            "c.reefer_type,\n" +
            "c.iso_group,\n" +
            "c.shipper_declared_vgm,\n" +
            "c.terminal_measured_vgm,\n" +
            "c.last_free_day,\n" +
            "c.paid_thru_day,\n" +
            "c.power_last_free_day,\n" +
            "c.power_paid_thru_day,\n" +
            "c.ib_registry_nbr,\n" +
            "c.ob_registry_nbr,\n" +
            "c.entry_no,\n" +
            "c.appointment_start_date,\n" +
            "c.appointment_end_date,\n" +
            "c.shipper,\n" +
            "c.consignee,\n" +
            "c.show_tvarrival_status,\n" +
            "c.tv_arrival_status,\n" +
            "c.tv_arrival_remarks " +
            "FROM api_container c " +
            "WHERE ((c.category = 'STRGE' AND IS_DEFINED(c.category)) AND ((c.freight_kind = 'MTY' AND IS_DEFINED(c.freight_kind)))) AND c.delete_flag = 'N'";
    public static final String EMPTY_CONTAINER_NAME = "api_container";

    public static final String EMPTY_CONTAINER_VISIT_STATE = "(c.visit_state IN (%s) AND IS_DEFINED(c.visit_state))";
    public static final String EMPTY_CONTAINER_TRANSIT_STATE = "(c.transit_state IN (%s) AND IS_DEFINED(c.transit_state))";
    public static final String EMPTY_CONTAINER_ISO_GROUP = "(c.iso_group IN (%s) AND IS_DEFINED(c.iso_group))";
    public static final String EMPTY_CONTAINER_ARRIVE_POS_LOCTYPE = "(c.arrive_pos_loctype IN (%s) AND IS_DEFINED(c.arrive_pos_loctype))";
    public static final String EMPTY_CONTAINER_DEPART_POST_LOCTYPE = "((c.visit_state = '3DEPARTED' AND IS_DEFINED(c.visit_state)) AND (c.last_pos_loctype IN (%s) AND IS_DEFINED(c.last_pos_loctype)))";
    public static final String EMPTY_CONTAINER_DEPART_POST_LOC_ID = "((c.visit_state = '3DEPARTED' AND IS_DEFINED(c.visit_state)) AND (c.last_pos_locid IN (%s) AND IS_DEFINED(c.last_pos_locid)))";
    public static final String EMPTY_CONTAINER_ARRIVE_POS_LOC_ID = "(c.arrive_Pos_Locid IN (%s) AND IS_DEFINED(c.arrive_Pos_Locid))";
    public static final String EMPTY_CONTAINER_NUMBER = "(c.ContainerNbr IN (%s) AND IS_DEFINED(c.ContainerNbr))";
    public static final String EMPTY_CONTAINER_EQUIPMENT_TYPE = "(c.EquipmentType IN (%s) AND IS_DEFINED(c.EquipmentType))";
    public static final String EMPTY_CONTAINER_OPERATION_LINE_ID = "(c.OperatorLineID IN (%s) AND IS_DEFINED(c.OperatorLineID))";

    public static final String EMPTY_CONTAINER_TIME_IN = "((c.time_in >= '%s' AND c.time_in <= '%s') AND IS_DEFINED(c.time_in))";
    public static final String EMPTY_CONTAINER_TIME_OUT = "((c.time_out >= '%s' AND c.time_out <= '%s') AND IS_DEFINED(c.time_out))";



}
