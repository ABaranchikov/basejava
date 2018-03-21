package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.Map;

public class MapStorage extends MapUuidStorage {


    @Override
    protected boolean isExist(Object index) {

        for (Map.Entry<String, Resume> entry : map.entrySet()) {
            String key = entry.getKey();
            Resume value = entry.getValue();
            if (value.getUuid().equals(index)) return true;
        }

        return false;
    }

}