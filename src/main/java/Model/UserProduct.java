package Model;

public class UserProduct {

    private int id;
    private String userProduct;
    private int userId;

    public UserProduct(int id, String userProduct, int userId) {
        this.id = id;
        this.userProduct = userProduct;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public String getUserProduct() {
        return userProduct;
    }

    public int getUserId() {
        return userId;
    }
}

