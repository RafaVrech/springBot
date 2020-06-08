package com.vrechbot.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PriceActionID implements Serializable {

    private String botNo;

    @JsonFormat(pattern = "M/d/yyyy h:m:sa")
    private LocalDateTime data;
}
