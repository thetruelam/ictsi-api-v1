package com.justanalytics.service;

import com.justanalytics.dto.FieldChanges;
import com.justanalytics.dto.LanguageDescription;
import com.justanalytics.dto.VesselEventDto;
import com.justanalytics.query.Query;
import com.justanalytics.repository.DataRepository;
import com.justanalytics.utils.QueryBuilder;
import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.justanalytics.constant.VesselEventBaseCondition.*;

@Service
public class VesselEventServiceImpl implements VesselEventService {

    Logger logger = LoggerFactory.getLogger(VesselEventServiceImpl.class);

    private static final DateTimeFormatter iso_formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Autowired
    private DataRepository dataRepository;

    private String parseParams(String params) {
        if (params != null && !params.isBlank())
            return String.join(", ",
                    Arrays.stream(params.split(","))
                            .map(element -> ("'" + element + "'"))
                            .collect(Collectors.toList()));
        else return "";

    }

    private String buildFilter(String filter, String input) {
        if (input != null && !input.isBlank())
            return String.format(filter, input);
        else return "";
    }

    private List<VesselEventDto> getVesselEventDto(List<JSONObject> rawData) {

        List<VesselEventDto> results = new ArrayList<>(rawData.size());

        for (JSONObject data : rawData) {
            String uniqueKey = String.valueOf(data.get("unique_key"));
            String operator = String.valueOf(data.get("operator_id"));
            String complex = String.valueOf(data.get("complex_id"));
            String facility = String.valueOf(data.get("facility_id"));
            String yard = String.valueOf(data.get("yard_id"));
            String placedBy = String.valueOf(data.get("placed_by"));
            String placedTime = String.valueOf(data.get("placed_time"));
            String eventType = String.valueOf(data.get("event_type"));
            Boolean notifiable = Objects.nonNull(data.get("notifiable")) ? Boolean.valueOf(String.valueOf(data.get("notifiable"))) : null;
            String vesselGkey = String.valueOf(data.get("vessel_gkey"));
            String appliedToId = String.valueOf(data.get("applied_to_id"));
            String notes = String.valueOf(data.get("notes"));
            String category = String.valueOf(data.get("category"));
            String subCategory = String.valueOf(data.get("sub_category"));

            List<FieldChanges> fieldChanges = new ArrayList<>();
            List<FieldChanges> rawFieldChanges = (List<FieldChanges>) data.get("field_changes");
            if (rawFieldChanges != null) fieldChanges = rawFieldChanges;

            List<LanguageDescription> eventDescriptions = new ArrayList<>();
            List<LanguageDescription> raweventDescriptions = (List<LanguageDescription>) data.get("event_descriptions");
            if (raweventDescriptions != null) eventDescriptions = raweventDescriptions;

            results.add(VesselEventDto.builder()
                    .uniqueKey(uniqueKey)
                    .operatorId(operator)
                    .complexId(complex)
                    .facilityId(facility)
                    .yardId(yard)
                    .placedBy(placedBy)
                    .placedTime(placedTime)
                    .eventType(eventType)
                    .eventDescriptions(eventDescriptions)
                    .notifiable(notifiable)
                    .vesselGkey(vesselGkey)
                    .appliedToId(appliedToId)
                    .notes(notes)
                    .fieldChanges(fieldChanges)
                    .category(category)
                    .subCategory(subCategory)
                    .build());

        }
        return results;

    }

    @Override
    public List<VesselEventDto> findVesselEvent(
            String uniqueKey,
            String language,
            String operationType,
            Query query) {

        // Main query
        StringBuilder queryBuilder = new StringBuilder();
        queryBuilder.append(VESSEL_EVENT_BASE_QUERY);

        // Persona filter
        List<String> personaFilters = new ArrayList<>();

        String personaUniqueKey = buildFilter(UNIQUE_KEY, parseParams(uniqueKey));
        String personaLanguage = buildFilter(LANGUAGE, parseParams(language));

        personaFilters.add(personaUniqueKey);
        personaFilters.add(personaLanguage);

        personaFilters = personaFilters.stream()
                .filter(e -> !e.equalsIgnoreCase(""))
                .filter(e -> !e.equalsIgnoreCase("1=1"))
                .collect(Collectors.toList());

        if (personaFilters.size() == 0) {
            queryBuilder.append(" AND ");
        }
        else {
            queryBuilder.append(String.format(" AND %s", "(" + String.join(" " + operationType + " ", personaFilters) + ")"));
            queryBuilder.append(" AND ");
        }

        // Search filter
        QueryBuilder filterBuilder = new QueryBuilder();

        if (query.filter != null) {
            String filter = filterBuilder.buildCosmosSearchFilter(query);
            queryBuilder.append(filter);
        }
        else queryBuilder.append("1=1");

        // Order
        if (!query.sort.isEmpty()) {
            String sortBy = filterBuilder.buildOrderByString(query.sort);
            queryBuilder.append(String.format(" ORDER BY %s", sortBy));
        }

        // Offset limit
        queryBuilder.append(String.format(" OFFSET %s LIMIT %s", query.offset, query.limit));

        String sql = queryBuilder.toString();
        logger.info("Cosmos SQL statement: {}", sql);
        List<JSONObject> rawData = dataRepository.getSimpleDataFromCosmos(VESSEL_EVENT_CONTAINER_NAME, sql);
        return getVesselEventDto(rawData);

    }
}
