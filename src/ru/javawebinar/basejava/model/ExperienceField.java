package ru.javawebinar.basejava.model;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class ExperienceField extends Section {
    private static final long serialVersionUID = 1L;

    private List<Experience> experiences;

    public ExperienceField() {}

    public ExperienceField(Experience... experiences) {
        this(Arrays.asList(experiences));
    }

    public ExperienceField(List<Experience> experiences) {
        Objects.requireNonNull(experiences, "organizations must not be null");
        this.experiences = experiences;
    }

    public List<Experience> getExperiences() {
        return experiences;
    }

    @Override
    public List<String> getAllFields(){
        List<String> list = new ArrayList<>();
        list.add(String.valueOf(experiences.size()));
        for(Experience exp: experiences){
            list.addAll(exp.getExperience());
        }
        return list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ExperienceField)) return false;
        ExperienceField that = (ExperienceField) o;
        return Objects.equals(experiences, that.experiences);
    }

    @Override
    public int hashCode() {
        return experiences.hashCode();
    }

    @Override
    public String toString() {
        return experiences.toString();
    }
}
