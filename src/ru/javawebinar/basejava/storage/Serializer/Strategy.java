package ru.javawebinar.basejava.storage.Serializer;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public interface Strategy {
    Resume doRead(InputStream inputStream) throws IOException;

    void doWrite(Resume r, OutputStream outputStream) throws IOException;
}