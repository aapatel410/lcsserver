package com.example.lcsserver.service;

import com.example.lcsserver.model.StringValue;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class ComputeLCSServiceImpl implements ComputeLSCService{

    @Override
    public List<String> findLCS(List<StringValue> value) {
        List<String> commonLongestSSList = new ArrayList<>();
        if (value == null || value.size() == 0) {
            return commonLongestSSList;
        }

        // Find the smallest string
        String smallest = value.get(0).getValue();
        for (int i = 1; i < value.size(); i++) {
            if (value.get(i).getValue().length() < smallest.length()) {
                smallest = value.get(i).getValue();
            }
        }

        // Loop through each substring of smallest string and check if it is a substring of all other values
        for (int i = smallest.length(); i > 0; i--) {
            for (int j = 0; j <= smallest.length() - i; j++) {
                String substring = smallest.substring(j, j + i);
                boolean found = true;
                for (StringValue s : value) {
                    if (!s.contains(substring)) {
                        found = false;
                        break;
                    }
                }
                if (found) {
                    commonLongestSSList.add(substring);
                    return commonLongestSSList;
                }
            }
        }

        return commonLongestSSList;
    }


}
