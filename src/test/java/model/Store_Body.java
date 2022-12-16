package model;

import java.util.List;

public class Store_Body {
    private int id;
    private int petId;
    private int quantity;
    private String shipData;
    private String status;
    private boolean complete;

    public Store_Body() {
    }

    public Store_Body(int id, int petId, int quantity,
                      String shipData, String status,
                      boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipData = shipData;
        this.status = status;
        this.complete = complete;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getShipData() {
        return shipData;
    }

    public void setShipData(String shipData) {
        this.shipData = shipData;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public boolean getComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
