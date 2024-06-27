package model;

public class Card {
    private int id;
    private String name;
    private String type;
    private int attack;
    private int defense;
    private int cost;
    private int pitch;


    public Card(int id, String name, String type, int attack, int defense, int cost, int pitch) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.cost = cost;
        this.pitch = pitch;
    }

    public int getId() {return id;}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getPitch() {
        return pitch;
    }

    public void setPitch(int pitch) {
        this.pitch = pitch;
    }

    public String toString() {
        return "Card{" +
                "id:" + id +
                ", name:" + name +
                ", type:" + type +
                ", attack:" + attack +
                ", defense:" + defense +
                ", cost:" + cost +
                ", pitch:" + pitch +
                '}';
    }
}
