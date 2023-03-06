package com.happy.base;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@ToString
public class BasePO {
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
