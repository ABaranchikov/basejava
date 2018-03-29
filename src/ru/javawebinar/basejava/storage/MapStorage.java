package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MapStorage extends AbstractStorage<Resume> {

    protected Map<String, Resume> map = new HashMap<>();

    @Override
    protected Resume getSearchKey(String uuid) {
        return map.get(uuid);
    }

    @Override
    protected void updateResume(Resume resume, Resume r) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected boolean isExist(Resume resume) {

        return resume != null;
    }

    @Override
    protected void saveResume(Resume resume, Resume r) {
        map.put(r.getUuid(), r);
    }

    @Override
    protected Resume getResume(Resume resume) {
        return resume;
    }

    @Override
    protected void deleteResume(Resume resume) {
        map.remove(resume.getUuid());
    }

    @Override
    public void clear() {
        map.clear();
    }

    @Override
    protected List<Resume> getStorage() {
        return new ArrayList<>(map.values());
    }

    @Override
    public int size() {
        return map.size();
    }
}