/**
 * com.urise.webapp.model.Resume class
 */
public class Resume {

    // Unique identifier
    private String uuid;

    @Override
    public String toString() {
        return uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @Override
    //нужно будетв переопределить в следующем уроке
    public boolean equals(Object o) {
        return super.equals(o);
    }
}
