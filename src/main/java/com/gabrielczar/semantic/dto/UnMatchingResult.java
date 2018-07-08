package com.gabrielczar.semantic.dto;

import com.gabrielczar.semantic.entities.UnMatchingEntry;
import lombok.Data;

import java.util.List;

@Data
public class UnMatchingResult {
    private String key;
    private List<UnMatchingEntry> entries;
}
