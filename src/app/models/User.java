package app.models;

import pendulum.persistence.Model;

public class User extends Model {

    private static final long serialVersionUID = 1L;
    protected static final String bucketName = "user";
    protected String name;

    public User(String name) {
        this.name = name;
    }

    protected String getBucketName() {
        return bucketName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
