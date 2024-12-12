package humancloud.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "system_game")
public class SystemGames {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long gameId;
    private long businessId;
    private String facilityName;
    private String sportType;
    private int capacity;
    private String facilityStatus;
    private double hourlyRate;
}
