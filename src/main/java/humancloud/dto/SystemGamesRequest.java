package humancloud.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SystemGamesRequest {
    private long businessId;
    private String facilityName;
    private String sportType;
    private int capacity;
    private String facilityStatus;
    private double hourlyRate;
}
