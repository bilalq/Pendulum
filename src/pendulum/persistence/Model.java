package pendulum.persistence;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Base class for data types that you want to be able to have persist to disk
 */
public abstract class Model implements Serializable {

    /** Serialization id */
    private static final long serialVersionUID = -7314038647882308441L;

    /** Unique identifier of the model instance */
    protected final String id;

    /**
     * Base constructor for models
     *
     * Assigns this.id to be a random UUID.
     */
    public Model() {
        this.id = java.util.UUID.randomUUID().toString();
    }

    /**
     * Saves model instance to disk
     */
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

    /**
     * Deletes this model instance from disk and returns true or false based on
     * success
     *
     * @return Boolean flag representing whether or not deletion was successful
     */
    public boolean delete() {
        return delete(this);
    }


    /**
     * Static method to find and delete a model instance
     *
     * @param bucket The bucket under which the model falls
     * @param id The unique id of the model instance
     * @return Boolean flag representing whether or not deletion was successful
     */
    public static boolean delete(String bucket, String id) {
        String path = getPath(bucket, id);
        File file = new File(path);
        if (!file.exists()){
            return false;
        } else {
            return file.delete();
        }
    }

    /**
     * Static method to delete a model instance
     *
     * @param model The model instance to be deleted
     * @return Boolean flag representing whether or not deletion was successful
     */
    public static boolean delete(Model model) {
        return delete(model.getBucketName(), model.getId());
    }

    /**
     * Finds and retrieves a model instance from disk
     *
     * @param bucket The bucket under which the model falls
     * @param id The unique id of the model instance
     * @return The model instance if it exists, otherwise, null
     */
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

    /**
     * Getter for the unique id of the model instance
     */
    public String getId() {
        return this.id;
    };

    /**
     * Returns the path to where this model instance is stored on disk
     */
    private static String getPath(String bucket, String id) {
        return "data/" + bucket + "/" + id + ".dat";
    }

    /**
     * Getter for the model's bucket name.
     *
     * This should be implemented in each model class. It is recommended to
     * return the name of the subclass as a String.
     *
     * Example:
     *
     * public class User extends Model {
     *     protected static final String bucketName = "user";
     *     protected String getBucketName() {
     *          return bucketName;
     *     }
     * }
     *
     */
    protected abstract String getBucketName();
}
