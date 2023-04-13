package com.example.lcsserver.service;

import com.example.lcsserver.model.StringValue;

import java.util.List;

public interface ComputeLSCService {
    public List<String> findLCS(List<StringValue> value);
}
