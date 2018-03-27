package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.util.Comparator;
import java.util.List;

public abstract class AbstractStorage implements Storage {

    private  static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public abstract void clear();

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> list = getStorage();
        list.sort(RESUME_COMPARATOR);
        return list;
    }

    protected abstract List<Resume> getStorage();

    public abstract int size();

    protected abstract boolean isExist(Object searchKey);

    protected abstract Resume getResume(Object index);

    protected abstract void saveResume(Object index, Resume r);

    protected abstract void deleteResume(Object index);

    protected abstract void updateResume(Object index, Resume r);

    protected abstract Object getSearchKey(String uuid);

    @Override
    public Resume get(String uuid) {
        Object searchKey = getExistedKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public void save(Resume r) {
        Object searchKey = getNotExistedKey(r.getUuid());
        saveResume(searchKey, r);
    }

    @Override
    public void update(Resume r) {
        Object searchKey = getExistedKey(r.getUuid());
        updateResume(searchKey, r);
    }

    @Override
    public void delete(String uuid) {
        Object searchKey = getExistedKey(uuid);
        deleteResume(searchKey);
    }

    private Object getExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) throw new NotExistStorageException(uuid);
        return searchKey;
    }

    private Object getNotExistedKey(String uuid) {
        Object searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) throw new ExistStorageException(uuid);
        return searchKey;
    }

}
