package ru.javawebinar.basejava.storage.Serializer;

import ru.javawebinar.basejava.model.*;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements Strategy {
    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());

            for (Map.Entry<SectionType, Section> section : sections.entrySet()) {
                SectionType sectionType = section.getKey();
                dos.writeUTF(sectionType.name());
                Section values = section.getValue();
                switch (sectionType){
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(values.toString());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> items = ((ListField) values).getItems();
                        dos.writeInt(items.size());
                        for (String item : items) {
                            dos.writeUTF(item);
                        }
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        List<Experience> experiences = ((ExperienceField) values).getExperiences();
                        dos.writeInt(experiences.size());
                        for (Experience exp : experiences) {
                            dos.writeUTF(exp.getHomePage().getName());
                            dos.writeUTF(exp.getHomePage().getUrl()== null ? "null" : exp.getHomePage().getUrl());

                            List<Experience.Periods> periods = exp.getPeriods();
                            dos.writeInt(periods.size());
                            for (Experience.Periods period : periods) {
                                dos.writeUTF(period.getStartDate().toString());
                                dos.writeUTF(period.getEndDate().toString());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription() == null ? "null" : period.getDescription());
                            }
                        }
                        break;
                    default:
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                String st1 = dis.readUTF();
                SectionType sType = SectionType.valueOf(st1);
                switch (sType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(sType, new StringField(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        List<String> list = new ArrayList<>();
                        int count = dis.readInt();
                        for (int k = 0; k<count; k++) {
                            list.add(dis.readUTF());
                        }
                        resume.addSection(sType, new ListField(list));
                        break;
                    case EXPERIENCE:
                    case EDUCATION:
                        List<Experience> expList = new ArrayList<>();
                        int count2 = dis.readInt();;
                        for (int k=0; k<count2; k++){
                            String linkName = dis.readUTF();
                            String linkURL = dis.readUTF();
                            Link link = new Link(linkName, linkURL.equals("null") ? null : linkURL);
                            List<Experience.Periods> periodList = new ArrayList<>();
                            int periodsSize = dis.readInt();
                            for (int j = 0; j < periodsSize; j++) {
                                LocalDate d1 = LocalDate.parse(dis.readUTF());
                                LocalDate d2 = LocalDate.parse(dis.readUTF());
                                String title = dis.readUTF();
                                String desc = dis.readUTF();
                                Experience.Periods p = new Experience.Periods(d1, d2, title, desc.equals("null") ? null : desc);
                                periodList.add(p);
                            }
                            expList.add(new Experience(link, periodList));
                        }
                        resume.addSection(sType, new ExperienceField(expList));
                        break;
                    default:
                }
            }
            return resume;
        }
    }




}