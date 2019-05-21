package Enity;

public class Message {
    private int id;
    private String Mes;

    public Message(int id, String mes) {
        this.id = id;
        Mes = mes;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMes() {
        return Mes;
    }

    public void setMes(String mes) {
        Mes = mes;
    }
}
