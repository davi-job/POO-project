package player;

import java.util.concurrent.TimeUnit;
import system.system;

public class player {

    public String name;

    public float health;

    public float maxHealth;

    public String[] inventory;

    public float atkPower;
    public float atkCost;

    public player(String name, float health, float atkPower, String[] inventory) {

        this.name = name;
        this.health = health;
        this.atkPower = atkPower;
        this.inventory = inventory;

        this.maxHealth = health;
    }

    public void atk(player target) throws InterruptedException {
        system sys = new system();
        sys.clearConsole();

        target.health -= this.atkPower;

        System.out.println(target.name + " sofreu " + this.atkPower + " de dano!!");
        TimeUnit.SECONDS.sleep(2);
    }

    public void rest() throws InterruptedException {
        system sys = new system();
        sys.clearConsole();

        this.health += this.maxHealth * .2f;

        if (this.health > this.maxHealth)
            this.health = this.maxHealth;

        System.out.println(this.name + " está descansando");
        TimeUnit.SECONDS.sleep(1);
    }

    public void trade(player target, String item) throws InterruptedException {

        system system = new system();

        for (int i = 0; i < this.inventory.length; i++) {

            if (this.inventory[i].equals(item)) {

                String[] newInventory = new String[this.inventory.length - 1];

                for (int j = 0; j < i; j++) {

                    newInventory[j] = this.inventory[j];
                }

                for (int j = i + 1; j < this.inventory.length; j++) {

                    newInventory[j - 1] = this.inventory[j];
                }

                target.addToInventory(item);
                this.inventory = newInventory;

                break;
            }

            if (i == this.inventory.length - 1) {
                system.clearConsole();

                System.out.println("!! FALHA !! - Item não encontrado");
                TimeUnit.SECONDS.sleep(2);
            }
        }
    }

    public void addToInventory(String item) {

        String[] newInventory = new String[this.inventory.length + 1];

        for (int i = 0; i < this.inventory.length; i++) {

            newInventory[i] = this.inventory[i];
        }

        newInventory[newInventory.length - 1] = item;
        this.inventory = newInventory;
    }

}
