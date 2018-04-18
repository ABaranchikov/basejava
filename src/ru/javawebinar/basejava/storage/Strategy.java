package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.model.Resume;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

interface Strategy {
    Resume doRead(InputStream inputStream) throws IOException;

    void doWrite(Resume r, OutputStream outputStream) throws IOException;
}