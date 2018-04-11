package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    //protected final Logger log = Logger.getLogger(getClass().getName());
    private static final Logger LOGGER = Logger.getLogger(AbstractStorage.class.getName());

    private  static final Comparator<Resume> RESUME_COMPARATOR = Comparator.comparing(Resume::getFullName).thenComparing(Resume::getUuid);

    public abstract void clear();

    protected abstract List<Resume> getStorage();

    public abstract int size();

    protected abstract boolean isExist(SK searchKey);

    protected abstract Resume getResume(SK index);

    protected abstract void saveResume(SK index, Resume r);

    protected abstract void deleteResume(SK index);

    protected abstract void updateResume(SK index, Resume r);

    protected abstract SK getSearchKey(String uuid);

    @Override
    public Resume get(String uuid) {
        LOGGER.info("Get " + uuid);
        SK searchKey = getExistedKey(uuid);
        return getResume(searchKey);
    }

    @Override
    public void save(Resume r) {
        LOGGER.info("Save "+r);
        SK searchKey = getNotExistedKey(r.getUuid());
        saveResume(searchKey, r);
    }

    @Override
    public void update(Resume r) {
        LOGGER.info("Update " + r);
        SK searchKey = getExistedKey(r.getUuid());
        updateResume(searchKey, r);
    }

    @Override
    public void delete(String uuid) {
        LOGGER.info("Delete "+uuid);
        SK searchKey = getExistedKey(uuid);
        deleteResume(searchKey);
    }

    private SK getExistedKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOGGER.warning("Resume " + uuid + " already exist");
            throw new NotExistStorageException(uuid);
        }
        return searchKey;
    }

    private SK getNotExistedKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOGGER.warning("Resume " + uuid + " not exist");
            throw new ExistStorageException(uuid);
        }
        return searchKey;
    }

    @Override
    public List<Resume> getAllSorted() {
        LOGGER.info("getAllSorted");
        List<Resume> list = getStorage();
        list.sort(RESUME_COMPARATOR);
        return list;
    }
}
