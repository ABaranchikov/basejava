package ru.javawebinar.basejava.storage.Serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Strategy {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            writeCollection(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });
            //  dos.writeInt(contacts.size());
            //  for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
            //      dos.writeUTF(entry.getKey().name());
            //      dos.writeUTF(entry.getValue());
            //  }

            writeCollection(dos, r.getSections().entrySet(), entry -> {
                SectionType sectionType = entry.getKey();
                Section values = entry.getValue();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case PERSONAL:
                    case OBJECTIVE:
                        dos.writeUTF(values.toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((ListField) values).getItems(), dos::writeUTF
                        );
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        writeCollection(dos, ((ExperienceField) values).getExperiences(), (exp) -> {
                            dos.writeUTF(exp.getHomePage().getName());
                            dos.writeUTF(exp.getHomePage().getUrl()== null ? "null" : exp.getHomePage().getUrl());

                            writeCollection(dos, exp.getPeriods(), period -> {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription() == null ? "null" : period.getDescription());
                            });
                        });
                        break;
                }
            });
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            readItem(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            //    int size = dis.readInt();
            //   for (int i = 0; i < size; i++) {
            //       resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            //   }

            readItem(dis, () ->{
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                resume.addSection(sectionType, readSection(dis, sectionType));
            });
            return resume;
        }
    }

    private Section readSection(DataInputStream dis, SectionType sectionType) throws IOException {
        switch (sectionType) {
            case PERSONAL:
            case OBJECTIVE:
                return new StringField(dis.readUTF());
            case ACHIEVEMENT:
            case QUALIFICATIONS:
                return new ListField(readList(dis, dis::readUTF));
            case EXPERIENCE:
            case EDUCATION:
                return new ExperienceField(
                        readList(dis, ()->new Experience(
                            new Link(dis.readUTF(), dis.readUTF()),
                            readList(dis, () -> new Experience.Periods(
                                    LocalDate.parse(dis.readUTF()), LocalDate.parse(dis.readUTF()), dis.readUTF(), dis.readUTF()
                            ))
                    )));
                default: throw new IllegalStateException();
        }
    }

    private <T> List<T> readList(DataInputStream dis, ElementReader<T> reader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int k = 0; k<size; k++) {
            list.add(reader.read());
        }
        return  list;
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private interface ElementReader<T> {
        T read() throws IOException;
    }

    private interface ElementProcessor {
        void process() throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.write(item);
        }
    }

    private void readItem(DataInputStream dis, ElementProcessor processor) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            processor.process();
        }
    }
}