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
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) throw new NotExistStorageException(uuid);
        return getResume(searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (isExist(searchKey)) throw new ExistStorageException(r.getUuid());
        saveResume(searchKey, r);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getSearchKey(r.getUuid());
        if (!isExist(searchKey)) throw new NotExistStorageException(r.getUuid());
        updateResume(searchKey, r);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) throw new NotExistStorageException(uuid);
        deleteResume(searchKey);
    }

    @Override
    public abstract Resume[] getAll();

    public abstract int size();

    protected abstract boolean isExist(Object searchKey);

    protected abstract Resume getResume(Object index);

    protected abstract void saveResume(Object index, Resume r);

    protected abstract void deleteResume(Object index);

    protected abstract void updateResume(Object index, Resume r);

    protected abstract Object getSearchKey(String uuid);
}
