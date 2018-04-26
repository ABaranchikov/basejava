package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.storage.Serializer.Strategy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class FileStorage extends AbstractStorage<File> {

    private File directory;
    private Strategy strategy;

    public FileStorage(String dir, Strategy strategy) {
        File directory = new File(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!directory.isDirectory()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not directory");
        }
        if (!directory.canRead() || !directory.canWrite()) {
            throw new IllegalArgumentException(directory.getAbsolutePath() + " is not readable/writable");
        }
        this.directory = directory;
        this.strategy = strategy;
    }

    @Override
    public void clear() {
        File[] files = fileList();
        for (File file : files) {
            deleteResume(file);
        }
    }

    @Override
    protected List<Resume> getStorage() {
        File[] files = fileList();
        List<Resume> storage = new ArrayList<>(files.length);
        for (File file : files) {
            if (file.isFile()) {
                storage.add(getResume(file));
            }
        }
        return storage;
    }

    @Override
    public int size() {
        File[] files = fileList();
        return files.length;
    }

    @Override
    protected boolean isExist(File file) {
        return file.exists();
    }

    @Override
    protected Resume getResume(File file) {
        try {
            return strategy.doRead(new BufferedInputStream(new FileInputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File read error", file.getName(), e);
        }
    }

    @Override
    protected void saveResume(File file, Resume r) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + file.getAbsolutePath(), file.getName(), e);
        }
        updateResume(file, r);
    }

    @Override
    protected void deleteResume(File file) {
        if (!file.delete()) {
            throw new StorageException("File delete error", file.getName());
        }
    }

    @Override
    protected void updateResume(File file, Resume r) {
        try {
            strategy.doWrite(r, new BufferedOutputStream(new FileOutputStream(file)));
        } catch (IOException e) {
            throw new StorageException("File write error", r.getUuid(), e);
        }
    }

    @Override
    protected File getSearchKey(String uuid) {
        return new File(directory, uuid);
    }

    private File[] fileList() {
        File[] files = directory.listFiles();
        if (files == null) throw new StorageException("Directory is empty", "");
        return files;
    }
}
