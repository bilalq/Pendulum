package pendulum.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public abstract class Model implements Serializable {

    private static final long serialVersionUID = -7314038647882308441L;
    protected final String id;

    public Model() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    public void save() throws IOException {
        ObjectOutputStream oos = null;
        String path = getPath(this.getBucketName(), this.getId());

        File file = new File(path);
        if (!file.exists()) {
            new File("data/" + this.getBucketName()).mkdirs();
            file.createNewFile();
        }

        try {
            oos = new ObjectOutputStream(new FileOutputStream(path));
            oos.writeObject(this);
        } catch (FileNotFoundException e) {
            throw new IOException(e.getMessage());
        } finally {
            try {
                if (oos != null) {
                    oos.close();
                }
            } catch (IOException e) {}
        }
    }

    public boolean delete() {
        return delete(this);
    }

    public static boolean delete(String bucket, String id) {
        String path = getPath(bucket, id);
        File file = new File(path);
        if (!file.exists()){
            return false;
        } else {
            return file.delete();
        }
    }

    public static boolean delete(Model model) {
        return delete(model.getBucketName(), model.getId());
    }

    public static Model find(String bucket, String id) {
        ObjectInputStream ois = null;
        String path = getPath(bucket, id);
        try {
            ois = new ObjectInputStream(new FileInputStream(path));
            Model model = (Model) ois.readObject();
            return model;
        } catch (IOException e){
            return null;
        } catch (ClassNotFoundException e) {
            return null;
        } finally {
            try {
                if (ois != null) {
                    ois.close();
                }
            } catch (IOException e) {}
        }
    }

    public String getId() {
        return this.id;
    };

    private static String getPath(String bucket, String id) {
        return "data/" + bucket + "/" + id + ".dat";
    }

    protected abstract String getBucketName();
}
