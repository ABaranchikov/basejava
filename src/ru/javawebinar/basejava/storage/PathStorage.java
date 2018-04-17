package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.model.Resume;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.*;

public class PathStorage extends AbstractStorage<Path> {

    private Path directory;
    private Strategy strategy;

    public PathStorage(String dir, Strategy strategy) {
        directory = Paths.get(dir);
        Objects.requireNonNull(directory, "directory must not be null");
        if (!Files.isDirectory(directory) || !Files.isWritable(directory)) {
            throw new IllegalArgumentException(dir + " is not directory or is not writable");
        }
        this.strategy = strategy;
    }

    @Override
    public void clear() {
        try {
            Files.list(directory)
                    .filter(Files::isRegularFile)
                    .forEach(this::deleteResume);
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
    }

    @Override
    protected List<Resume> getStorage() {
        List<Resume> storage = new ArrayList<>();
        try (Stream<Path> paths = Files.walk(directory)) {
            List<Path> list = paths.filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            for (Path file : list) {
                storage.add(getResume(file));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return storage;
    }

    @Override
    public int size() {
        long size = 0;
        try {
            size = Files.list(directory)
                    .filter(Files::isRegularFile)
                    .count();
        } catch (IOException e) {
            throw new StorageException("Path delete error", null);
        }
        return (int) size;
    }

    @Override
    protected boolean isExist(Path path) {
        return Files.exists(path, LinkOption.NOFOLLOW_LINKS);
        //return Files.exists(path);
    }

    @Override
    protected Resume getResume(Path path) {
        try {
            return strategy.doRead(Files.newInputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path read error", path.toAbsolutePath().toString(), e);
        }
    }

    @Override
    protected void saveResume(Path path, Resume r) {
        try {
            Files.createFile(path);
        } catch (IOException e) {
            throw new StorageException("Couldn't create file " + path.toAbsolutePath().toString(), path.toAbsolutePath().toString(), e);
        }
        updateResume(path, r);
    }

    @Override
    protected void deleteResume(Path path) {
        try {
            Files.delete(path);
        } catch (IOException e) {
            throw new StorageException("Path delete error", path.toAbsolutePath().toString(), e);
        }

    }

    @Override
    protected void updateResume(Path path, Resume r) {
        try {
            strategy.doWrite(r, Files.newOutputStream(path));
        } catch (IOException e) {
            throw new StorageException("Path write error", r.getUuid(), e);
        }
    }

    @Override
    protected Path getSearchKey(String uuid) {
        return Paths.get(directory.toAbsolutePath().toString() + "//" + uuid);
    }

}
