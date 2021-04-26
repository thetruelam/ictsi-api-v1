package com.justanalytics.controller;


import com.justanalytics.entity.Container;
import com.justanalytics.response.RestEnvelope;
import com.justanalytics.service.ContainerService;
import com.justanalytics.types.CtxPath;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping({CtxPath.INTERNAL, CtxPath.EXTERNAL})
public class DataController {

    private static final String PRODUCT_ID_HEADER = "test-subscription";

    @Autowired
    private final ContainerService containerService;

    @Autowired
    public DataController(ContainerService containerService) {
        this.containerService = containerService;
    }


//    @GetMapping(path = "/api/v1/getTopContainerDetails", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<RestEnvelope> getTopContainerDetails() {
//        List<Container> containers = containerService.getTopContainer();
//        return ResponseEntity.ok(RestEnvelope.of(containers));
//    }
//
//    @GetMapping(path = "/api/v1/getTopContainerDetailsCustom", produces = MediaType.APPLICATION_JSON_VALUE)
//    public ResponseEntity<RestEnvelope> getTopContainerDetails(
//            @RequestHeader(PRODUCT_ID_HEADER) String subscriptionId,
//            @RequestParam(value = "container-number", required = false) String containerNumber
//    ) {
//        List<Container> containers = containerService.getTopContainerCustom(containerNumber);
//        return ResponseEntity.ok(RestEnvelope.of(containers));
//    }

    @GetMapping(path = "/api/v1/getContainerBol", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<RestEnvelope> getCustomContainer(
            @RequestHeader(PRODUCT_ID_HEADER) String subscriptionId,
            @RequestParam(value = "container-type", defaultValue = "simple") String containerType,
            @RequestParam(value = "container-number", required = false) String containerNumber,
            @RequestParam(value = "bol", required = false) String billOfLadingNbr,
            @RequestParam(value = "size", required = false, defaultValue = "10") String size
    ) {
        List<Map<String, Object>> containers = containerService.findContainerBol(containerType, containerNumber, billOfLadingNbr, size);
        return ResponseEntity.ok()
                .header("row-count", "" + containers.size())
                .body(RestEnvelope.of(containers));
    }

}
