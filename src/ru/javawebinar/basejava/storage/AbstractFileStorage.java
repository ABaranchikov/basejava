package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class AbstractFileStorage extends AbstractStorage<File> {

    private File directory;

    protected AbstractFileStorage(File directory) {
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
    }

    @Override
    public void clear() {
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    boolean b = file.delete();
                }
            }
        }
    }

    @Override
    protected List<Resume> getStorage() {
        List<Resume> storage = new ArrayList<>();
        File[] list = directory.listFiles();
        if (list != null) {
            for (File file : list) {
                if (file.isFile()) {
                    storage.add(new Resume(file.getName(), ""));
                }
            }
        }
        return storage;
    }

    @Override
    public int size() {

        return Objects.requireNonNull(directory.listFiles()).length;

    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected Resume getResume(File file) {
        return new Resume(file.getName(), "");
    }

    @Override
    protected void saveResume(File file, Resume r) {
        try {
            file.createNewFile();
            doWrite(r, file);
        } catch (IOException e) {
            throw new StorageException("IO error", file.getName(), e);
        }
    }

    protected abstract void doWrite(Resume r, File file) throws IOException;

    @Override
    protected void deleteResume(File file) {
        file.delete();

    }

    @Override
    protected void updateResume(File file, Resume r) {
        saveResume(file, r);
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }
}
