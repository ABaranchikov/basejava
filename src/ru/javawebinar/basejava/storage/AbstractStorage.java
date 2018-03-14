package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    @Override
    public abstract void clear();

    @Override
    public Resume get(String uuid) {

        int index = getIndex(uuid);
        if (index < 0) throw new NotExistStorageException(uuid);
        return getResume(index);
    }

    @Override
    public void save(Resume r) {
        int index = getIndex(r.getUuid());
        if (index > 0) throw new ExistStorageException(r.getUuid());
        saveResume(index, r);
    }

    @Override
    public void update(Resume r) {
        int index = getIndex(r.getUuid());
        if (index < 0) {
            throw new NotExistStorageException(r.getUuid());
        }
        updateResume(index, r);
    }

    @Override
    public void delete(String uuid) {
        int index = getIndex(uuid);
        if (index < 0) {
            throw new NotExistStorageException(uuid);
        }
        deleteResume(index);
    }

    @Override
    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract Resume getResume(int index);

    protected abstract void saveResume(int index, Resume r);

    protected abstract void deleteResume(int index);

    protected abstract void updateResume(int index, Resume r);

    protected abstract int getIndex(String uuid);
}
