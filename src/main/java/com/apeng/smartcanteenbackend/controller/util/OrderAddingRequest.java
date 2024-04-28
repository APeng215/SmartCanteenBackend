package com.apeng.smartcanteenbackend.controller.util;

import java.util.List;

public record OrderAddingRequest(List<Long> dishIds) {
}
