package humancloud.util;

import humancloud.dto.SystemGamesRequest;
import humancloud.model.SystemGames;

public class SystemGamesUtils {

    public static final SystemGamesRequest mapToDto(SystemGames entity) {
        SystemGamesRequest dto = new SystemGamesRequest();
        dto.setBusinessId(entity.getBusinessId());
        dto.setFacilityName(entity.getFacilityName());
        dto.setSportType(entity.getSportType());
        dto.setCapacity(entity.getCapacity());
        dto.setFacilityStatus(entity.getFacilityStatus());
        dto.setHourlyRate(entity.getHourlyRate());
        return dto;
    }

    public static final SystemGames mapToEntity(SystemGamesRequest dto) {
        SystemGames entity = new SystemGames();
        entity.setBusinessId(dto.getBusinessId());
        entity.setFacilityName(dto.getFacilityName());
        entity.setSportType(dto.getSportType());
        entity.setCapacity(dto.getCapacity());
        entity.setFacilityStatus(dto.getFacilityStatus());
        entity.setHourlyRate(dto.getHourlyRate());
        return entity;
    }
}
