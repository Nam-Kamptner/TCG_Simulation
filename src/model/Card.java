package model;

import java.util.Objects;

public class Card {
    private int id;
    private String name;
    private String type;
    private int attack;
    private int defense;
    private int cost;
    private int pitch;


    public Card(String name, String type, int attack, int defense, int cost, int pitch) {
        if (attack < 0 || defense < 0 || cost < 0) {
            throw new IllegalArgumentException("Attack, defense, and cost must be non-negative.");
        }
        if (pitch < 1 || pitch > 3) {
            throw new IllegalArgumentException("Pitch Value must be between 1 and 3.");
        }
        this.name = name;
        this.type = type;
        this.attack = attack;
        this.defense = defense;
        this.cost = cost;
        this.pitch = pitch;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return id == card.id && attack == card.attack && defense == card.defense && cost == card.cost && Objects.equals(name, card.name) && Objects.equals(type, card.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, attack, defense, cost);
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
